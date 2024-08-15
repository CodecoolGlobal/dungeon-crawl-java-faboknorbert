package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Ghost;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.items.HealthPotion;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapFile, Player player) {
        InputStream is = MapLoader.class.getResourceAsStream(mapFile);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    char tile = line.charAt(x);
                    setupCell(cell, tile, map, player);
                }
            }
        }

        if (player != null) {
            map.setPlayer(player);
        }

        return map;
    }

    public static GameMap loadMap(String mapFile) {
        return loadMap(mapFile, null);
    }

    private static void setupCell(Cell cell, char tile, GameMap map, Player player) {
        switch (tile) {
            case ' ':
                cell.setType(CellType.EMPTY);
                break;
            case '#':
                cell.setType(CellType.WALL);
                break;
            case '.':
                cell.setType(CellType.FLOOR);
                break;
            case 's':
                cell.setType(CellType.FLOOR);
                Skeleton skeleton = new Skeleton(cell, 5, 2);
                map.addEnemy(skeleton);
                break;
            case '@':
                cell.setType(CellType.FLOOR);
                if (player == null) {
                    player = new Player(cell, 10, 2);
                    map.setPlayer(player);
                } else {
                    player.setCell(cell);
                    cell.setActor(player);
                    map.setPlayer(player);
                }
                break;
            case 'd':
                cell.setType(CellType.LOCKED_DOOR);
                break;
            case 'o':
                cell.setType(CellType.OPEN_DOOR);
                break;
            case '$':
                cell.setType(CellType.FLOOR);
                Ghost ghost = new Ghost(cell, 10, 3);
                map.addEnemy(ghost);
                break;
            case 'w':
                cell.setType(CellType.FLOOR);
                new Sword(cell);
                break;
            case 'h':
                cell.setType(CellType.FLOOR);
                new HealthPotion(cell);
                break;
            case 'l':
                cell.setType(CellType.LOAD_NEW_MAP);
                break;
            case 'k':
                cell.setType(CellType.FLOOR);
                new Key(cell);
                break;
            case 'x':
                cell.setType(CellType.SAVE_CELL);
                break;
            default:
                throw new RuntimeException("Unrecognized character: '" + tile + "'");
        }
    }
}
