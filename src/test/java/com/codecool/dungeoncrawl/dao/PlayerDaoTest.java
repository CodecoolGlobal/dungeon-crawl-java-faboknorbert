package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDaoTest {

    PlayerDao playerDao = new PlayerDao();

    @Test
    void savePlayerTest() throws SQLException {
        List<Item> inventory = new ArrayList<>();
        inventory.add(new Sword(new Cell(10, 10)));
        inventory.add(new Key(new Cell(11, 11)));
        Player player = new Player(15, 25, 35, 70, inventory);
        playerDao.savePlayer(player);
    }

    @Test
    void loadPlayerTest() {
        Player reloadedPlayer = playerDao.loadPlayer();
        assertEquals(10, reloadedPlayer.getX());
        assertEquals(20, reloadedPlayer.getY());
        assertEquals(30, reloadedPlayer.getHealth());
        assertEquals(40, reloadedPlayer.getStrength());
    }
}