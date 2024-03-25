package com.explore.snakesladder;

import static org.junit.Assert.*;

import com.explore.snakesladder.configs.GameConfiguration;
import com.explore.snakesladder.models.*;
import com.explore.snakesladder.service.impl.GameManagerImpl;
import com.explore.snakesladder.utils.GameBoard;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GameManagerImplTest {

    private GameManagerImpl gameManager;
    private List<Player> players;
    private GameBoard gameBoard;

    @Before
    public void setUp() {
        gameManager = new GameManagerImpl();
        players = new ArrayList<>();
        players.add(new Player("Player1", 1));
        players.add(new Player("Player2", 1));
        GameConfiguration gameConfiguration = new GameConfiguration();
        gameConfiguration.setBoardSize(10);
        gameBoard = new GameBoard(gameConfiguration);
    }

    @Test
    public void testIsGameOverPositiveCase() {
        assertFalse(gameManager.isGameOver(players, 100));
        players.get(0).setCurrentPosition(100);
        assertTrue(gameManager.isGameOver(players, 100));
    }

    @Test
    public void testMovePlayerSnakeEncounter() {
        List<Snake> snakes = new ArrayList<>();
        snakes.add(new Snake(14, 7));
        gameBoard.setSnakes(snakes);
        Player player = players.get(0);
        player.setCurrentPosition(13);
        gameManager.movePlayer(player, 1, gameBoard, players);
        assertEquals(7, player.getCurrentPosition());
    }

    @Test
    public void testMovePlayerLadderEncounter() {
        List<Snake> snakes = new ArrayList<>();
        gameBoard.setSnakes(snakes);
        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(5, 15));
        gameBoard.setLadders(ladders);
        Player player = players.get(0);
        player.setCurrentPosition(3);
        gameManager.movePlayer(player, 2, gameBoard, players);
        assertEquals(15, player.getCurrentPosition());
    }
    @Test
    public void testMovePlayerMineEncounter() {
        List<Mine> mines = new ArrayList<>();
        mines.add(new Mine(3));
        gameBoard.setMines(mines);
        Player player = players.get(0);
        player.setCurrentPosition(1);
        gameManager.movePlayer(player, 2, gameBoard, players);
        assertEquals(2, player.getOnHold());
    }

    @Test
    public void testMovePlayerCrocodileEncounter() {
        List<Crocodile> crocodiles = new ArrayList<>();
        crocodiles.add(new Crocodile(11));
        gameBoard.setCrocodiles(crocodiles);
        Player player = players.get(0);
        player.setCurrentPosition(10);
        gameManager.movePlayer(player, 1, gameBoard, players);
        assertEquals(6, player.getCurrentPosition());
    }



    @Test
    public void testMovePlayerNormalMove() {
        Player player = players.get(0);
        gameManager.movePlayer(player, 3, gameBoard, players);
        assertEquals(4, player.getCurrentPosition());
    }

    @Test
    public void testHandleOverlappingOfPositionsBetweenPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Akshay",10));
        Player currentPlayer = new Player("Vineet",8);
        gameManager.movePlayer(currentPlayer, 2, gameBoard, players);

        assertEquals(1,players.get(0).getCurrentPosition());
        assertEquals("Akshay",players.get(0).getName());
    }

    @Test
    public void testRollDice() {
        int[] diceValues = gameManager.rollDice(2);
        assertEquals(2, diceValues.length);
        for (int dieValue : diceValues) {
            assertTrue(dieValue >= 1 && dieValue <= 6);
        }
    }

    @Test
    public void testSwitchPlayer() {
        assertEquals(1, gameManager.switchPlayer(0, 2));
        assertEquals(0, gameManager.switchPlayer(1, 2));
    }
}
