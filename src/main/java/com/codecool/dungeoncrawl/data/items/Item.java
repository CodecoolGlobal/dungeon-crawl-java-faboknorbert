package com.codecool.dungeoncrawl.data.items;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.actors.Player;


public abstract class Item implements Drawable{
    private Cell cell;
    private String itemName;
    private static final String tileName = "chest";

    public Item(Cell cell, String itemName) {
        this.cell = cell;
        this.cell.setItem(this);
        this.itemName = itemName;
    }

    public String getTileName(){
        return tileName;
    }

    public String getItemName() {
        return itemName;
    }


    public abstract void equip(Player player);
    public abstract void unequip(Player player);
    public void pickup(Player player) {
        player.getInventory().add(this);
    }
}
