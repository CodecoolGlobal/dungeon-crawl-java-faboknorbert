package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell, int health, int strength) {
        super(cell, health, strength);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
