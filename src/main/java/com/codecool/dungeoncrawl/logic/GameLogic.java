package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
    private GameMap map;
    private int mapNum;

    public GameLogic(String mapName) {
        this.map = MapLoader.loadMap(mapName);
        this.mapNum = 1;
    }

    public void moveEnemy(KeyEvent event, GameMap map){
        if (KeyCode.LEFT.equals(event.getCode()) || KeyCode.A.equals(event.getCode())
                || KeyCode.RIGHT.equals(event.getCode()) || KeyCode.D.equals(event.getCode())
                || KeyCode.UP.equals(event.getCode()) || KeyCode.W.equals(event.getCode())
                || KeyCode.DOWN.equals(event.getCode()) || KeyCode.S.equals(event.getCode())
        ) {

            Random random = new Random();
            for (Actor enemy : map.getEnemies()) {

                List<Cell> neighbourCells = new ArrayList<>();
                List<Cell> validCells = new ArrayList<>();
                neighbourCells.add(enemy.getCell().getNeighbor(1, 0));
                neighbourCells.add(enemy.getCell().getNeighbor(0, 1));
                neighbourCells.add(enemy.getCell().getNeighbor(-1, 0));
                neighbourCells.add(enemy.getCell().getNeighbor(0, -1));

                for (Cell neighbourCell : neighbourCells) {
                    if (neighbourCell.getType() != CellType.WALL &&
                            neighbourCell.getType() != CellType.SAVE_CELL &&
                            neighbourCell.getType() != CellType.LOCKED_DOOR &&
                            neighbourCell.getActor() == null &&
                            neighbourCell.getItem() == null) {
                        validCells.add(neighbourCell);
                    }
                }
                if(!validCells.isEmpty()) {
                    int randomNum = random.nextInt(validCells.size());
                    Cell neighbourCell = validCells.get(randomNum);
                    enemy.getCell().setActor(null);
                    enemy.setCell(neighbourCell);
                    neighbourCell.setActor(enemy);
                }
            }
        }
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

    public void setMap(String mapName){
        this.map = MapLoader.loadMap(mapName);
    }

    public void setNumMap(int numMap){
        this.mapNum = numMap;
    }

    public int getMapNumber(){
        return mapNum;
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
