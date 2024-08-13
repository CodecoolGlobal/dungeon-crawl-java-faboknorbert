package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Left implements KeyHandler {
    public static final KeyCode code = KeyCode.LEFT;
    public static final KeyCode altCode = KeyCode.A;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if(code.equals(event.getCode()) || altCode.equals(event.getCode()))
            map.getPlayer().move(-1, 0);
    }
}
