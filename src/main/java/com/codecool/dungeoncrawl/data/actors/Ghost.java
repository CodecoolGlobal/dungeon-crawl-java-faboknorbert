package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Ghost extends Actor {
    public Ghost(Cell cell, int health, int strength) {
        super(cell, health, strength);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
