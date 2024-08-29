package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY(false, "empty"),
    FLOOR(true, "floor"),
    WALL(false, "wall"),
    LOCKED_DOOR(false, "lockedDoor"),
    OPEN_DOOR(true, "openDoor"),
    LOAD_NEW_MAP(true, "newMap"),
    SAVE_CELL(false,"saveCell"),
    TRAP(false, "trap");

    private final boolean canMoveTo;
    private final String tileName;

    CellType(boolean canMoveTo, String tileName) {
        this.canMoveTo = canMoveTo;
        this.tileName = tileName;
    }

    public boolean canMoveTo() {
        return canMoveTo;
    }

    public String getTileName() {
        return tileName;
    }
}
