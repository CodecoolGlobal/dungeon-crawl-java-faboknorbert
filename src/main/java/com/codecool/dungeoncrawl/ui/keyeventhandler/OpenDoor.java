package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class OpenDoor implements KeyHandler{
    public static final KeyCode code = KeyCode.O;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        Player player = map.getPlayer();
        if (event.getCode() == code){
            List<Cell> cells = new ArrayList<Cell>();
            cells.add(map.getPlayer().getCell().getNeighbor(0, 1));
            cells.add(map.getPlayer().getCell().getNeighbor(1, 0));
            cells.add(map.getPlayer().getCell().getNeighbor(-1, 0));
            cells.add(map.getPlayer().getCell().getNeighbor(0, -1));

            for(Cell cell : cells) {
                if (cell.getType() == CellType.LOCKED_DOOR && player.hasKey()) {
                    cell.setType(CellType.OPEN_DOOR);
                }
            }
        }

    }
}
