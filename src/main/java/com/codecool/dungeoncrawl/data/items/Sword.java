package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Sword extends Item{
    private int damage = 5;



    public Sword(Cell cell){
        super(cell, "Sword", "chest");
    }

    @Override
    public String getTileName() {
        return "chest";
    }

    @Override
    public void pickUp(GameMap gameMap){
        gameMap.getPlayer().setDamage(gameMap.getPlayer().getDamage() + damage);
        gameMap.getPlayer().getInventory().add(this);
    }
}
