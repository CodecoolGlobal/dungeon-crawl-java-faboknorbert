package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Actor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameLogic {
    private GameMap map;
    private int mapNum;
    private final int maxMapNum;
    private final Player player;
    Random random = new Random();


    public GameLogic(String mapName) {
        this.map = MapLoader.loadMap(mapName);
        this.mapNum = 1;
        this.maxMapNum = 3;
        this.player = map.getPlayer();
    }

    public void moveEnemy(KeyEvent event, GameMap map){
        Set<KeyCode> movementKeys = Set.of(
                KeyCode.LEFT, KeyCode.A,
                KeyCode.RIGHT, KeyCode.D,
                KeyCode.UP, KeyCode.W,
                KeyCode.DOWN, KeyCode.S
        );

        if(movementKeys.contains(event.getCode())) {
            for (Actor enemy : map.getEnemies()) {
                List<Cell> neighbourCells = new ArrayList<>();

                for (Cell neighbourCell : enemy.getCell().getNeighbors()) {
                    if (neighbourCell.canMoveTo()) {
                        neighbourCells.add(neighbourCell);
                    }
                }

                if(!neighbourCells.isEmpty()) {
                    int randomNum = random.nextInt(neighbourCells.size());
                    Cell neighbourCell = neighbourCells.get(randomNum);
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

    public void setup() {}

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(player.getHealth());
    }

    public String getPlayerDamage(){
        return Integer.toString(player.getStrength());
    }

    public void setMap(String mapName){
        if (mapNum > maxMapNum){
            showWinMessage();
        }else{
            this.map = MapLoader.loadMap(mapName, player);
        }
    }

    public void setNumMap(int numMap){
        this.mapNum = numMap;
    }

    public int getMapNumber(){
        return mapNum;
    }

    public String getPlayerInventory(){
        return player.toString();
    }
    public GameMap getMap() {
        return map;
    }

    private void showWinMessage() {
        JOptionPane.showMessageDialog(
                null,
                "Congratulations! You Win!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public void checkPlayerHealth() {
        if (player.getHealth() <= 0) {
            int response = JOptionPane.showOptionDialog(
                null,
                "Game Over! You have died.",
                "Game Over",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"OK"},
                "OK"
            );
            if (response == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
    }
}
