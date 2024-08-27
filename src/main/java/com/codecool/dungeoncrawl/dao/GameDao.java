package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.logic.GameLogic;

import java.sql.*;
import java.util.List;

public class GameDao {
    private final String url = "jdbc:postgresql://localhost:5432/dungeon_crawler";
    private final String user = "postgres";
    private final String password = "Admin";

    public void savePlayerState(int playerX, int playerY, int playerHealth, int playerStrength) {
        String query = "INSERT INTO player (x, y, health, strength) " +
                "VALUES (?, ?, ?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET " +
                "x = EXCLUDED.x, " +
                "y = EXCLUDED.y, health = EXCLUDED.health, strength = EXCLUDED.strength";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, playerX);
            pstmt.setInt(2, playerY);
            pstmt.setInt(3, playerHealth);
            pstmt.setInt(4, playerStrength);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int playerId = rs.getInt(1);
                saveItems(playerId, Game.getPlayer().getInventory());
                saveMonsters(playerId, Game.getMonsters());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveItems(int playerId, List<Item> items) {
        String query = "INSERT INTO items (player_id, item_type, x, y) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (Item item : items) {
                pstmt.setInt(1, playerId);
                pstmt.setString(2, item.getItemName());
                pstmt.setInt(3, item.getX());
                pstmt.setInt(4, item.getY());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveMonsters(int playerId, List<Actor> monsters) {
        String query = "INSERT INTO monsters (player_id, monster_type, health, x, y) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (Actor monster : monsters) {
                pstmt.setInt(1, playerId);
                pstmt.setString(2, monster.getTileName());
                pstmt.setInt(3, monster.getHealth());
                pstmt.setInt(4, monster.getX());
                pstmt.setInt(5, monster.getY());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Player loadPlayer() {
        String query = "SELECT * FROM player ORDER BY id DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                int playerId = rs.getInt("id");
                int playerX = rs.getInt("position_x");
                int playerY = rs.getInt("position_y");
                int health = rs.getInt("health");
                int strength = rs.getInt("strength");

                return new Player(playerX, playerY, health, strength);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
