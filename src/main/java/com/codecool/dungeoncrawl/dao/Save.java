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
    }*/
}
