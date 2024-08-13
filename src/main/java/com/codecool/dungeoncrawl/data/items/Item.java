package com.codecool.dungeoncrawl.data.items;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.GameMap;


public abstract class Item implements Drawable{
    private Cell cell;
    private String itemName;

    public Item(Cell cell, String itemName, String tileName) {
        this.cell = cell;
        this.cell.setItem(this);
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public abstract void pickUp(GameMap gameMap);

}
