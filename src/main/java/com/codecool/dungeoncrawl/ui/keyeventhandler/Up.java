package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Up implements KeyHandler {
    public static final KeyCode code = KeyCode.UP;
    public static final KeyCode altCode = KeyCode.W;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if(code.equals(event.getCode()) || altCode.equals(event.getCode()))
            map.getPlayer().move(0, -1);
    }
}
