package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    LOCKED_DOOR("lockedDoor"),
    OPEN_DOOR("openDoor"),
    LOAD_NEW_MAP("newMap"),
    SAVE_CELL("saveCell");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
