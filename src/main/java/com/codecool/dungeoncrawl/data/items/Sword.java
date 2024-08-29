package com.codecool.dungeoncrawl.data.items;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Sword extends Item{
    private static final int damage = 5;

    public Sword(Cell cell){
        super(cell, "sword");
    }

    @Override
    public void equip(Player player) {
        player.setStrength(player.getStrength() + damage);
    }

    @Override
    public void unequip(Player player) {
        player.setStrength(player.getStrength() - damage);
    }
}
