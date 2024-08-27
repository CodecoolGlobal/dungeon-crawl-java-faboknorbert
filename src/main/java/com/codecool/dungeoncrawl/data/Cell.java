package com.codecool.dungeoncrawl.data;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.items.Item;
import java.util.ArrayList;
import java.util.List;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private final GameMap gameMap;
    private final int x, y;

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() { return type; }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Actor getActor() {
        return actor;
    }

    public Item getItem(){
        return item;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public Cell getNeighbor(int dx, int dy) {
        if(isOutOfBounds(dx, dy)) {
            return null;
        }
        return gameMap.getCell(x + dx, y + dy);
    }

    public List<Cell> getNeighbors() {
        ArrayList<Cell> neighbors = new ArrayList<>();
        neighbors.add(gameMap.getCell(x, y +1));
        neighbors.add(gameMap.getCell(x, y -1));
        neighbors.add(gameMap.getCell(x - 1 , y));
        neighbors.add(gameMap.getCell(x + 1, y));
        return neighbors;
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public boolean canMoveToCellType() {
       return type.canMoveTo() && actor == null && item == null;
    }

    private boolean isOutOfBounds(int dx,int dy){
       return x == 0 && dx < 0 || x == gameMap.getWidth() - 1 && dx > 0 || y == 0 && dy < 0 || y == gameMap.getHeight() - 1 && dy > 0;
    }
}
