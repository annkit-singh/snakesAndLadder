package com.explore.snakesladder.service;

public interface MovementStrategyDiceCalculator {
    public int sum(int [] diceValues);
    public int max(int [] diceValues);
    public int min(int [] diceValues);
}
