package com.explore.snakesladder.service.impl;

import com.explore.snakesladder.service.MovementStrategyDiceCalculator;

public class MovementStrategyDiceCalculatorImpl implements MovementStrategyDiceCalculator {
    @Override
    public int sum(int[] diceValues) {
        int sum = 0;
        for (int value : diceValues) {
            sum += value;
        }
        return sum;
    }

    @Override
    public int max(int[] diceValues) {
        int max = Integer.MIN_VALUE;
        for (int value : diceValues) {
            max = Math.max(max, value);
        }
        return max;

    }

    @Override
    public int min(int[] diceValues) {
        int min = Integer.MAX_VALUE;
        for (int value : diceValues) {
            min = Math.min(min, value);
        }
        return min;
    }
}
