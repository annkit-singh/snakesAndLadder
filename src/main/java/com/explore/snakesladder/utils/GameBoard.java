package com.explore.snakesladder.utils;


import com.explore.snakesladder.configs.GameConfiguration;
import com.explore.snakesladder.enums.MovementStrategy;
import com.explore.snakesladder.models.Crocodile;
import com.explore.snakesladder.models.Ladder;
import com.explore.snakesladder.models.Mine;
import com.explore.snakesladder.models.Snake;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GameBoard {
    private int[] board;
    private List<Snake> snakes = new ArrayList<>();
    private List<Ladder> ladders = new ArrayList<>();
    private List<Crocodile> crocodiles = new ArrayList<>();
    private List<Mine> mines = new ArrayList<>();
    private MovementStrategy movementStrategy;

    public List<Crocodile> getCrocodiles() {
        return crocodiles;
    }

    public void setCrocodiles(List<Crocodile> crocodiles) {
        this.crocodiles = crocodiles;
    }

    public List<Mine> getMines() {
        return mines;
    }

    public void setMines(List<Mine> mines) {
        this.mines = mines;
    }

    public GameBoard(GameConfiguration gameConfiguration) {
        this.board = new int[gameConfiguration.getBoardSize()*gameConfiguration.getBoardSize()+1];
        if (gameConfiguration.getSnakes() != null) {
            this.snakes = gameConfiguration.getSnakes();
        }
        if (gameConfiguration.getLadders() != null) {
            this.ladders = gameConfiguration.getLadders();
        }
        if (gameConfiguration.getCrocodiles() != null) {
            this.crocodiles = gameConfiguration.getCrocodiles();
        }
        if (gameConfiguration.getMines() != null) {
            this.mines = gameConfiguration.getMines();
        };

        this.movementStrategy = gameConfiguration.getMovementStrategy();
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }


    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public int getLastCellPosition(){
        return this.board.length-1;
    }

    public int[] getBoard() {
        return board;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }
}