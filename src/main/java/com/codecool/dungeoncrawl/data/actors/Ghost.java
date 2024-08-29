package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends Actor implements Monster{
    public Ghost(Cell cell, int health, int strength) {
        super(cell, health, strength);
    }
    Random random = new Random();

    @Override
    public String getTileName() {
        return "ghost";
    }


    @Override
    public void move(){
        List<Cell> neighbourCells = new ArrayList<>();

        for (Cell neighbourCell : this.getCell().getNeighbors()) {
            if (neighbourCell.canMoveToCellType(this)) {
                neighbourCells.add(neighbourCell);
            }
        }

        if(!neighbourCells.isEmpty()) {
            int randomNum = random.nextInt(neighbourCells.size());
            Cell neighbourCell = neighbourCells.get(randomNum);
            this.getCell().setActor(null);
            this.setCell(neighbourCell);
            neighbourCell.setActor(this);
        }
    }
}
