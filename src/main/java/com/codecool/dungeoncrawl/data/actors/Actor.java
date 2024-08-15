package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.logic.Game;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int strength;

    public Actor(Cell cell, int health, int strength) {
        this.cell = cell;
        this.health = health;
        this.strength = strength;
        this.cell.setActor(this);
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
                cell.getType() == CellType.OPEN_DOOR || cell.getType() == CellType.LOAD_NEW_MAP;
    }

    public void attack(Actor target, GameMap map) {
        target.takeDamage(target, map);
        if (target.getHealth() > 0) {
            this.takeDamage(target, map);
        }
    }

    public void takeDamage(Actor target, GameMap map) {
        this.health -= target.getStrength();
        if (this.health <= 0) {
            die(map, target);
        }
    }

    protected void die(GameMap map,Actor target) {
        map.removeEnemy(target);
        this.cell.setActor(null);
        target.getCell().setActor(null);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
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

    public void setCell(Cell cell){
        this.cell = cell;
    }
}
