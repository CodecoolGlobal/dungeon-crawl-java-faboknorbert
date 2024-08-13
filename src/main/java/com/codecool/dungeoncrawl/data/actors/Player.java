package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory;

    public Player(Cell cell, int health, int damage) {
        super(cell, health, damage);
        this.inventory = new ArrayList<Item>();
    }

    @Override
    public String getTileName() {
        return "player";
    }


    public List<Item> getInventory() {
        return inventory;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Item item : inventory){
            stringBuilder.append(item.getItemName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
