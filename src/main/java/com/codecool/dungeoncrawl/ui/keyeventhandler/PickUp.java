package com.codecool.dungeoncrawl.ui.keyeventhandler;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.List;

public class PickUp implements KeyHandler{
    public static final KeyCode code = KeyCode.E;


    @Override
    public void perform(KeyEvent event, GameMap map) {
        if(code.equals(event.getCode())) {
            List<Cell> neighbors = map.getPlayer().getCell().getNeighbors();

            for(Cell cell : neighbors) {
                if(cell.getItem() != null){
                    cell.getItem().pickup(map.getPlayer());
                    cell.setItem(null);
                }
            }
        }

    }
}
