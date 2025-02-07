package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Down implements KeyHandler {
    public static final KeyCode code = KeyCode.DOWN;
    public static final KeyCode altCode = KeyCode.S;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code.equals(event.getCode()) || altCode.equals(event.getCode()))
            map.getPlayer().move(0, 1);
    }
}
