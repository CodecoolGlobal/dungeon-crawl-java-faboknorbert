package com.codecool.dungeoncrawl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//WIP, CURRENTLY NO USAGE
public class Dao {
    private Connection connect(){
        String url = "jdbc:postgresql://localhost/dungeon_crawler";
        String user = "my_user";
        String password = "my_password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /*public void saveGameState() {
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


    private void onSave(KeyEvent keyEvent) {
        Save save = new Save();
        save.performSave(keyEvent, logic.getMap(), logic);
    }

     if (keyEvent.getCode() == KeyCode.ENTER) {
            onSave(keyEvent);
        }
     */
}
