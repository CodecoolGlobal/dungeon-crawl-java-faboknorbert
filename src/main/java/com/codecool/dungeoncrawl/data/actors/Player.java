package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Key;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private final List<Item> inventory;

    public Player(Cell cell, int health, int strength) {
        super(cell, health, strength);
        this.inventory = new ArrayList<>();
    }

    public Player(int x, int y, int health, int strength, List<Item> inventory) {
        super(x, y, health, strength);
        this.inventory = inventory;
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

    public boolean hasKey() {
        for(Item item : inventory){
            if (item instanceof Key) {
                return true;
            }
        }
        return false;
    }
}
