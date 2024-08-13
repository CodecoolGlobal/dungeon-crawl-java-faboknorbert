package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.items.Item;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int damage;

    public Actor(Cell cell, int health, int damage) {
        this.cell = cell;
        this.cell.setActor(this);
        this.health = health;
        this.damage = damage;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (canMoveTo(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    private boolean canMoveTo(Cell cell) {
        return cell.getType() == CellType.FLOOR &&
                cell.getActor() == null  && cell.getItem() == null||
                cell.getType() == CellType.OPEN_DOOR;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getDamage(){
        return this.damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void setHealth(int health){
        this.health = health;
    }
}
