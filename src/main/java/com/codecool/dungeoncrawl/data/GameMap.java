package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private final int width;
    private final int height;
    private final Cell[][] cells;
    private final List<Actor> enemies;
    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        this.enemies = new ArrayList<>();
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void addEnemy(Actor enemy){
        enemies.add(enemy);
    }

    public void removeEnemy(Actor enemy){
        enemies.removeIf(actor -> actor.getX() == enemy.getX() && actor.getY() == enemy.getY());
    }

    public List<Actor> getEnemies() {
        return enemies;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
