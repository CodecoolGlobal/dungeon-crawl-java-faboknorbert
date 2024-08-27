package com.codecool.dungeoncrawl.data.items;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class Key extends Item {

    public Key(Cell cell) {
        super(cell, "Key");
    }

    @Override
    public void pickUp(GameMap gameMap) {
        gameMap.getPlayer().getInventory().add(this);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
