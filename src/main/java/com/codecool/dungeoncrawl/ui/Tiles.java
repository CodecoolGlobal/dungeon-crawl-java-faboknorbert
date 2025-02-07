package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("trap", new Tile(12, 12));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("boss", new Tile(24,2));
        tileMap.put("player", new Tile(20, 8)); //default 27/0, colorful 20/8
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("tarantula", new Tile(29, 5));
        tileMap.put("ghost", new Tile(27, 6));
        tileMap.put("lockedDoor", new Tile(3, 3));
        tileMap.put("openDoor", new Tile(4, 3));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("chest", new Tile(9, 6));
        tileMap.put("newMap", new Tile(3, 6));
        tileMap.put("saveCell", new Tile(26, 28));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
