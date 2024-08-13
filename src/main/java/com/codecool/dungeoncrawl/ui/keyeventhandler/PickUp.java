package com.codecool.dungeoncrawl.ui.keyeventhandler;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PickUp implements KeyHandler{
    public static final KeyCode code = KeyCode.E;


    @Override
    public void perform(KeyEvent event, GameMap map) {
        if(code.equals(event.getCode())) {
            List<Cell> cells = new ArrayList<Cell>();
            cells.add(map.getPlayer().getCell().getNeighbor(0, 1));
            cells.add(map.getPlayer().getCell().getNeighbor(1, 0));
            cells.add(map.getPlayer().getCell().getNeighbor(-1, 0));
            cells.add(map.getPlayer().getCell().getNeighbor(0, -1));

            for(Cell cell : cells) {
                if(cell.getItem() != null){
                    cell.getItem().pickUp(map);
                    cell.setItem(null);
                }
            }
        }

    }
}
