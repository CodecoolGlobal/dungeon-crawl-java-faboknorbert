package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.data.actors.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDaoTest {

    PlayerDao playerDao = new PlayerDao();

    @Test
    void savePlayerTest() throws SQLException {
        Player player = new Player(1, 2, 3, 4);
        playerDao.savePlayer(player);
    }

    @Test
    void loadPlayerTest() {
        Player reloadedPlayer = playerDao.loadPlayer();
        assertEquals(5, reloadedPlayer.getX());
        assertEquals(6, reloadedPlayer.getY());
        assertEquals(7, reloadedPlayer.getHealth());
        assertEquals(8, reloadedPlayer.getStrength());
    }
}