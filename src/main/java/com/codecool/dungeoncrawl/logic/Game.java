package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.ui.UI;
import com.codecool.dungeoncrawl.ui.keyeventhandler.*;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.Set;

public class Game extends Application {
    private UI ui;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        this.keyHandlers = Set.of(new Up(), new Down(), new Left(), new Right(), new PickUp(), new AttackSpace(), new OpenDoor(), new ChangeWeapon());
        this.logic = new GameLogic("/map.txt");
        this.ui = new UI(logic, keyHandlers);
        ui.setUpPain(primaryStage);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.F5) {
                logic.saveGame();
            } else if (event.getCode() == KeyCode.F9) {
                logic.loadGame();
            }
            ui.refresh();
        });
    }
}
