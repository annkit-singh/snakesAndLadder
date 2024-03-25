package com.explore.snakesladder.utils;



import com.explore.snakesladder.enums.MovementStrategy;
import com.explore.snakesladder.service.MovementStrategyDiceCalculator;
import com.explore.snakesladder.service.impl.MovementStrategyDiceCalculatorImpl;

import java.util.Random;

public class DiceRoller {
    // Method to roll dice and return dice value based on movement strategy
   private final static MovementStrategyDiceCalculator movementStrategyDiceCalculator = new MovementStrategyDiceCalculatorImpl();
    public static int rollDice(int numberOfDice, MovementStrategy movementStrategy) {
        Random random = new Random();
        int[] diceValues = new int[numberOfDice];
        for (int i = 0; i < numberOfDice; i++) {
            diceValues[i] = random.nextInt(6) + 1; // Generate number b/w 1 and 6
        }
        // Calculate dice roll based on movement strategy
        int diceRoll = 0;
        switch (movementStrategy) {
            case SUM:
                diceRoll = movementStrategyDiceCalculator.sum(diceValues);
                break;
            case MAX:
                diceRoll = movementStrategyDiceCalculator.max(diceValues);
                break;
            case MIN:
                diceRoll = movementStrategyDiceCalculator.min(diceValues);
                break;
            default:
                System.out.println("Invalid movement strategy.");
        }
        return diceRoll;
    }
}
