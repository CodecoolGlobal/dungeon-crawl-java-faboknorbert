package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class AttackSpace implements KeyHandler{
    public static final KeyCode code = KeyCode.SPACE;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code.equals(event.getCode())) {
            ArrayList<Cell> neighbors = new ArrayList<>();
            neighbors.add(map.getPlayer().getCell().getNeighbor(0, 1));
            neighbors.add(map.getPlayer().getCell().getNeighbor(0, -1));
            neighbors.add(map.getPlayer().getCell().getNeighbor(1, 0));
            neighbors.add(map.getPlayer().getCell().getNeighbor(-1, 0));

            for (Cell cell : neighbors) {
                if (cell.getActor() != null) {
                    map.getPlayer().attack(cell.getActor(), map);
                }
            }
        }
    }
}
