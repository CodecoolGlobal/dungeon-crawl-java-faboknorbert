package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.HealthPotion;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.items.Sword;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {
    private final String url = "jdbc:postgresql://localhost:5432/dungeon_crawler";
    private final String user = "postgres";
    private final String password = "Admin";

    public void savePlayer(Player player) throws SQLException {
        int playerX = player.getX();
        int playerY = player.getY();
        int playerHealth = player.getHealth();
        int playerStrength = player.getStrength();

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
                saveInventory(playerId, player.getInventory());
//                saveMonsters(playerId, Game.getMonsters());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveInventory(int playerId, List<Item> items) {
        String query = "INSERT INTO items (player_id, item_name, x, y) VALUES (?, ?, ?, ?)";

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

    public Player loadPlayer() {
        String query = "SELECT * FROM player ORDER BY id DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                int playerId = rs.getInt("id");
                int playerX = rs.getInt("x");
                int playerY = rs.getInt("y");
                int health = rs.getInt("health");
                int strength = rs.getInt("strength");

                List<Item> inventory = loadItems(playerId);
                return new Player(playerX, playerY, health, strength, inventory);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Item> loadItems(int playerId) {
        String query = "SELECT * FROM items WHERE player_id = ?";
        List<Item> items = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, playerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String itemType = rs.getString("item_name");
                    int x = rs.getInt("x");
                    int y = rs.getInt("y");

                    Item item = null;
                    switch (itemType) {
                        case "sword":
                            item = new Sword(new Cell(x, y));
                            break;
                        case "HealthPotion":
                            item = new HealthPotion(new Cell(x, y));
                            break;
                        case "key":
                            item = new Key(new Cell(x, y));
                            break;
                        default:
                            System.err.println("Unknown item type: " + itemType);
                            break;
                    }

                    if (item != null) {
                        items.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
