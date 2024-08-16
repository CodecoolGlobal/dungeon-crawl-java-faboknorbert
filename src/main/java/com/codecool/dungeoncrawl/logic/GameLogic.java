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

public class GameLogic {
    private GameMap map;
    private int mapNum;
    private int maxMapNum;
    private Player player;

    private final static List<KeyCode> directions = List.of(
            KeyCode.LEFT,  KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN,
            KeyCode.A,  KeyCode.D, KeyCode.W, KeyCode.S
    );

    public GameLogic(String mapName) {
        this.map = MapLoader.loadMap(mapName);
        this.mapNum = 1;
        this.maxMapNum = 3;
        this.player = map.getPlayer();
    }

    public void moveEnemy(KeyEvent event, GameMap map){
        if (directions.contains(event.getCode())) {
            Random random = new Random();
            for (Actor enemy : map.getEnemies()) {

                List<Cell> neighbourCells = new ArrayList<>();
                List<Cell> validCells = new ArrayList<>();
                neighbourCells.add(enemy.getCell().getNeighbor(1, 0));
                neighbourCells.add(enemy.getCell().getNeighbor(0, 1));
                neighbourCells.add(enemy.getCell().getNeighbor(-1, 0));
                neighbourCells.add(enemy.getCell().getNeighbor(0, -1));

                for (Cell neighbourCell : neighbourCells) {
                    if (neighbourCell.canMoveTo()) {
                        validCells.add(neighbourCell);
                    }
                }
                if(!validCells.isEmpty()) {
                    int randomNum = random.nextInt(validCells.size());
                    Cell neighbourCell = validCells.get(randomNum);
                    enemy.move(neighbourCell);
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
