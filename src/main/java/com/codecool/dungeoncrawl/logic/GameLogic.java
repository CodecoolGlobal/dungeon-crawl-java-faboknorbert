package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameLogic {
    private GameMap map;

    public GameLogic() {
        this.map = MapLoader.loadMap();
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }

    public String getPlayerDamage(){
        return Integer.toString(map.getPlayer().getStrength());
    }

    public String getPlayerInventory(){
        return map.getPlayer().toString();
    }
    public GameMap getMap() {
        return map;
    }

    private Connection connect(){
        String url = "jdbc:postgresql://localhost/dungeon_crawler";
        String user = "postgres";
        String password = "Admin";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void saveGameState() {
        String sql = "INSERT INTO game_state (player_x, player_y, map_data, inventory) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, map.getPlayer().getX());
            pstmt.setInt(2, map.getPlayer().getY());
            pstmt.setString(3, serializeMap());
            pstmt.setString(4, serializeInventory());

            pstmt.executeUpdate();
            System.out.println("Game state saved!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String serializeMap() {
        return map.toString();
    }

    private String serializeInventory() {
        return map.getPlayer().getInventory().toString();
    }
}
