package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Save implements KeyHandler {
    public static final KeyCode code = KeyCode.ENTER;

    @Override
    public void perform(KeyEvent event, GameMap map) {
    }
/*
    public void performSave(KeyEvent event, GameMap map, GameLogic logic) {
        if(event.getCode() == code){
            Cell inFront = map.getPlayer().getCell().getNeighbor(0, -1);
            if (inFront.getType() == CellType.SAVE_CELL) {
                logic.saveGameState();
            }
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
    */
}
