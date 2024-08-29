package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private GridPane ui;
    private Label nameTextLabel;
    private Label nameValueLabel;
    private Label healthTextLabel;
    private Label healthValueLabel;
    private Label damageTextLabel;
    private Label damageValueLabel;
    private Label inventoryTextLabel;
    private Label inventoryValueLabel;
    private Label controlLabel;

    public StatusPane() {
        ui = new GridPane();

        nameTextLabel = new Label("Name");
        nameValueLabel = new Label();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        damageTextLabel = new Label("Damage: ");
        damageValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory");
        inventoryValueLabel = new Label();
        controlLabel = new Label("Control:\n    arrows/WASD - move\n    SPACE - attack\n    E - equip/collect\n    O - open locked door");

        String whiteTextStyle = "-fx-text-fill: white;";
        nameTextLabel.setStyle(whiteTextStyle);
        nameValueLabel.setStyle(whiteTextStyle);
        healthTextLabel.setStyle(whiteTextStyle);
        healthValueLabel.setStyle(whiteTextStyle);
        damageTextLabel.setStyle(whiteTextStyle);
        damageValueLabel.setStyle(whiteTextStyle);
        inventoryTextLabel.setStyle(whiteTextStyle);
        inventoryValueLabel.setStyle(whiteTextStyle);
        controlLabel.setStyle(whiteTextStyle);
    }

    public BorderPane build() {
        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(nameTextLabel, 1, 0);
        ui.add(nameValueLabel, 2, 0);
        ui.add(healthTextLabel, 1, 1);
        ui.add(healthValueLabel, 2, 1);
        ui.add(damageTextLabel, 1, 2);
        ui.add(damageValueLabel, 2, 2);
        ui.add(inventoryTextLabel, 1, 3);
        ui.add(inventoryValueLabel, 2, 3);
        ui.add(controlLabel, 1, 6);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValue(String text) {
        healthValueLabel.setText(text);
    }

    public void setDamageValue(String text) {
        damageValueLabel.setText(text);
    }

    public void setInventoryValue(String text) {
        inventoryValueLabel.setText(text);
    }

    public void setNameValue(String text) {
        nameValueLabel.setText(text);
    }
}
