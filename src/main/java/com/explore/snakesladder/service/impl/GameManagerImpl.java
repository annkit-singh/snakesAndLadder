package com.explore.snakesladder.service.impl;


import com.explore.snakesladder.models.*;
import com.explore.snakesladder.service.GameManager;
import com.explore.snakesladder.utils.GameBoard;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GameManagerImpl implements GameManager {
    @Override
    public boolean isGameOver(List<Player> players, int lastCellPosition) {
        for(Player player :players) {
            if(player.getCurrentPosition()== lastCellPosition) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void movePlayer(Player player, int diceRoll, GameBoard gameBoard, List<Player> players) {
        int currentPosition = player.getCurrentPosition();
        //player is not on Hold
        if (player.getOnHold() == 0 && player.getCurrentPosition() + diceRoll <= gameBoard.getLastCellPosition()) {
            //handling snakes
            List<Snake> snakes = gameBoard.getSnakes();
            for (Snake snake : snakes) {
                if (player.getCurrentPosition() + diceRoll == snake.getHead()) {
                    System.out.println(player.getName() + " rolled a " + diceRoll + " and bittin by snake at "
                            + (currentPosition + diceRoll) + " moved from " + snake.getHead() + " to " + snake.getTail());
                    player.setCurrentPosition(snake.getTail());
                    handleOverlappingOfPositionsBetweenPlayers(player, players);
                    return;
                }
            }
            //handling ladders
            List<Ladder> ladders = gameBoard.getLadders();
            for (Ladder ladder : ladders) {
                if (player.getCurrentPosition() + diceRoll == ladder.getBottom()) {
                    System.out.println(player.getName() + " rolled a " + diceRoll + " and climbed the ladder at "
                            + ladder.getBottom() + " and moved from " + ladder.getBottom() + " to " + ladder.getTop());
                    player.setCurrentPosition(ladder.getTop());
                    handleOverlappingOfPositionsBetweenPlayers(player, players);
                    return;
                }
            }
            //handling crocodiles
            List<Crocodile> crocodiles = gameBoard.getCrocodiles();
            for(Crocodile crocodile: crocodiles) {
                if(crocodile.getPosition()==player.getCurrentPosition()+diceRoll){
                    System.out.println(player.getName() + " rolled a " + diceRoll + " and moved from "+player.getCurrentPosition()
                            +" to "+(player.getCurrentPosition()+diceRoll)+" eaten by crocodile  at "+crocodile.getPosition()+" and step back by 5 steps");
                    player.setCurrentPosition(player.getCurrentPosition() + diceRoll);
                    player.setCurrentPosition(player.getCurrentPosition() - 5);
                    handleOverlappingOfPositionsBetweenPlayers(player, players);
                    return;
                }
            }
            //handling mines
            List<Mine> mines = gameBoard.getMines();
            for(Mine mine: mines) {
                if (mine.getPosition()==player.getCurrentPosition() + diceRoll) {
                    System.out.println(player.getName() + " rolled a "+diceRoll+" and moved from "+player.getCurrentPosition()+" to "+(player.getCurrentPosition() + diceRoll) + " affected by mine and kept hold for next two tries");
                    player.setCurrentPosition(player.getCurrentPosition() + diceRoll);
                    player.setOnHold(2);
                    return;
                }
            }


        int newPosition = player.getCurrentPosition() + diceRoll;
        System.out.println(player.getName() + " rolled a " + diceRoll + " and moved from " + player.getCurrentPosition() + " to " + newPosition);
        player.setCurrentPosition(newPosition);
        handleOverlappingOfPositionsBetweenPlayers(player, players);
    }
    if(player.getOnHold()>0){
            System.out.println(player.getName()+" is on Hold");
            player.setOnHold(player.getOnHold()-1);
        }

    }
    public void handleOverlappingOfPositionsBetweenPlayers( Player currentPlayer, List<Player> players) {
        for(Player player :players) {
            if(!player.getName().equals(currentPlayer.getName()) && player.getCurrentPosition() == currentPlayer.getCurrentPosition()) {
                System.out.println(player.getName()+" restarted his position from "+player.getCurrentPosition()+" to 1 due to similar position");
                player.setCurrentPosition(1);
            }
        }
    }
    @Override
    public int[] rollDice(int numberOfDice) {
            int[] diceValues = new int[numberOfDice];
            Random random = new Random();
            for (int i = 0; i < numberOfDice; i++) {
                int dieValue = random.nextInt(6) + 1; // Random number between 1 and 6
                diceValues[i] = dieValue;
            }

            return diceValues;
        }

    @Override
    public int switchPlayer(int currentPlayerIndex, int totalPlayers) {
        return (currentPlayerIndex + 1) % totalPlayers;
    }


}
