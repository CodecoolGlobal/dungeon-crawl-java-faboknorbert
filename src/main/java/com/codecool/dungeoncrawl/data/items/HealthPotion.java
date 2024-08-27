package com.codecool.dungeoncrawl.data.items;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

public class HealthPotion extends Item {
    private static final int healValue = 3;

    public HealthPotion(Cell cell) {
        super(cell, "Health Potion");
    }

    @Override
    public void pickUp(GameMap gameMap){
        gameMap.getPlayer().setHealth(gameMap.getPlayer().getHealth() + healValue);
    }
}
