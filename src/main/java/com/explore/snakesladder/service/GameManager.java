package com.explore.snakesladder.service;



import com.explore.snakesladder.models.Player;
import com.explore.snakesladder.utils.GameBoard;

import java.util.List;

public interface GameManager {
    public  boolean isGameOver(List<Player> players, int lastCellPosition);
    public  void movePlayer(Player player, int diceRoll, GameBoard gameBoard, List<Player> players);
    public  int[] rollDice(int numberOfDice);
    public int switchPlayer(int currentPlayerIndex, int totalPlayers);
}
