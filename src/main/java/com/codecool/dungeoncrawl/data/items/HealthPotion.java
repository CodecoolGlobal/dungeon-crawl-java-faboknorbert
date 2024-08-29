package com.codecool.dungeoncrawl.data.items;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;

public class HealthPotion extends Item {
    private static final int healValue = 3;

    public HealthPotion(Cell cell) {
        super(cell, "Health Potion");
    }

    @Override
    public void equip(Player player) {
    }

    @Override
    public void unequip(Player player) {
    }

    @Override
    public void pickup(Player player) {
        player.setHealth(player.getHealth() + healValue);
    }
}
