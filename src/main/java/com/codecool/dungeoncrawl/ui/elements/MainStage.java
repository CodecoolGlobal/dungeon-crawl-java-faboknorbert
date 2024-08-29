package com.codecool.dungeoncrawl.ui.elements;

import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class MainStage {
    private final Canvas canvas;
    private final Scene scene;
    private final StatusPane statusPane;
    private TextField nameField;
    private Button startButton;
    private VBox startScreen;
    private GameMap gameMap;


    public MainStage(Canvas canvas, GameMap map) {
        this.canvas = canvas;
        this.statusPane = new StatusPane();
        this.scene = setUpScene();
        this.gameMap = map;
    }

    private Scene setUpScene() {
        BorderPane borderPane = statusPane.build();
        borderPane.setStyle("-fx-background-color: " + "#482c3c" + ";");
        borderPane.setCenter(canvas);
        setupStartScreen(borderPane);
        Scene scene = new Scene(startScreen);
        return scene;
    }

    private void setupStartScreen(BorderPane gamePane) {
        nameField = new TextField();
        nameField.setPromptText("Enter your name");
        startButton = new Button("Start Game");
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.BASELINE_CENTER);
        startScreen = vbox;

        startScreen.getChildren().addAll(new Label("Welcome to Dungeon Crawl!"), nameField, startButton);

        startButton.setOnAction(event -> {
            if (!nameField.getText().trim().isEmpty()) {
                setNameLabelText(nameField.getText());
                if(Objects.equals(nameField.getText(), "Adem")){
                    gameMap.getPlayer().setName(nameField.getText());

                }
                switchToGameView(gamePane);
            } else {
                System.out.println("Name cannot be empty!");
            }
        });
    }

    private void switchToGameView(BorderPane gamePane) {
        scene.setRoot(gamePane);
    }

    public Scene getScene() {
        return scene;
    }

    public void setHealthLabelText(String text) {
        this.statusPane.setHealthValue(text);
    }

    public void setNameLabelText(String name) {
        this.statusPane.setNameValue(name);
    }

    public void setDamageLabelText(String text) {
        this.statusPane.setDamageValue(text);
    }

    public void setInventoryLabelText(String text) {
        this.statusPane.setInventoryValue(text);
    }
}
