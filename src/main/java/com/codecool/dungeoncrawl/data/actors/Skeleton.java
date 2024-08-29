package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Skeleton extends Actor implements Monster{
    public Skeleton(Cell cell, int health, int strength) {
        super(cell, health, strength);
    }
    Random random = new Random();

    @Override
    public String getTileName() {
        return "skeleton";
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
