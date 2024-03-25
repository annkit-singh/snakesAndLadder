package com.explore.snakesladder;



import com.explore.snakesladder.configs.ConfigurationLoader;
import com.explore.snakesladder.configs.GameConfiguration;
import com.explore.snakesladder.models.Player;
import com.explore.snakesladder.service.GameManager;
import com.explore.snakesladder.service.impl.GameManagerImpl;
import com.explore.snakesladder.utils.DiceRoller;
import com.explore.snakesladder.utils.GameBoard;

import java.io.IOException;
import java.util.List;

// GameDriver.java
public class Main {

    public void startTheGame(int currentPlayerIndex, GameManager gameManager, GameBoard gameBoard, GameConfiguration gameConfig){
        int lastPosition = gameBoard.getLastCellPosition();

        List<Player> players = gameConfig.getPlayers();

        // loop will continue until Game Is not Over
        while (!gameManager.isGameOver(players, lastPosition)) {
            // fetch current player
            Player currentPlayer = players.get(currentPlayerIndex);
            int diceRoll = DiceRoller.rollDice(gameConfig.getNumberOfDies(), gameConfig.getMovementStrategy());
            gameManager.movePlayer(currentPlayer, diceRoll, gameBoard,players);

            if (currentPlayer.getCurrentPosition() == gameBoard.getLastCellPosition()) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }
            //switch from one player to another as per round-robin-algorithm
            currentPlayerIndex = gameManager.switchPlayer(currentPlayerIndex,gameConfig.getNumberOfPlayers());
        }
    }

    public static void main(String[] args) throws IOException {
        // Load game configuration from YAML file
        Main main = new Main();
        GameConfiguration gameConfig = ConfigurationLoader.loadConfiguration();
        GameManager gameManager = new GameManagerImpl();
        GameBoard gameBoard = new GameBoard(gameConfig);
        //starting the game
        main.startTheGame(0,gameManager,gameBoard,gameConfig);

    }

}
