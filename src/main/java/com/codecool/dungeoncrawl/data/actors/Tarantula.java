package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Tarantula  extends Actor {
    public Tarantula(Cell cell, int health, int strength) {
        super(cell, health, strength);
    }

    @Override
    public String getTileName() {
        return "tarantula";
    }
}
