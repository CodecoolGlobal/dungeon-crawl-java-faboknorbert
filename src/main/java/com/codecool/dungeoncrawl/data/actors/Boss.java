package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Boss extends Actor implements Monster{

    public Boss(Cell cell, int health, int strength) {
        super(cell, health, strength);
    }

    @Override
    public String getTileName() {
        return "boss";
    }

    @Override
    public void move() {
    }
}
