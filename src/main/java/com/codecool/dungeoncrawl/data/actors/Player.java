package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Key;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private final List<Item> inventory;
    private Item equippedItem;

    public Player(Cell cell, int health, int strength) {
        super(cell, health, strength);
        this.inventory = new ArrayList<>();
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

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public void changeEquippedItem() {
        if(equippedItem != null && !inventory.isEmpty()) {
            for (int i = 0; i < inventory.size(); i++) {
                if(inventory.get(i) == equippedItem && inventory.size() != 1 && i == inventory.size() - 1) {
                    equippedItem.unequip(this);
                    equippedItem = inventory.get(0);
                    inventory.get(0).equip(this);
                    break;
                }else if(inventory.get(i) == equippedItem && inventory.size() != 1){
                    equippedItem.unequip(this);
                    equippedItem = inventory.get(i + 1);
                    inventory.get(i + 1).equip(this);
                    break;
                }
            }
            return;
        }

        this.equippedItem = inventory.get(0);
        inventory.get(0).equip(this);


    }

    public Item getEquippedItem() {
        return equippedItem;
    }
}
