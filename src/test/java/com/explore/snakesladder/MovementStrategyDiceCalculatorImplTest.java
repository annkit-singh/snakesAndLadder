package com.explore.snakesladder;

import com.explore.snakesladder.service.impl.MovementStrategyDiceCalculatorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovementStrategyDiceCalculatorImplTest {

    private final MovementStrategyDiceCalculatorImpl calculator = new MovementStrategyDiceCalculatorImpl();

    @Test
    public void testSum() {
        int[] diceValues = {1, 2, 3, 4, 5};
        assertEquals(15, calculator.sum(diceValues));
        int[] emptyValues = {};
        assertEquals(0, calculator.sum(emptyValues));
    }

    @Test
    public void testMax() {
        int[] diceValues = {1, 6, 3, 4, 5};
        assertEquals(6, calculator.max(diceValues));
        int[] emptyValues = {};
        assertEquals(Integer.MIN_VALUE, calculator.max(emptyValues));
    }

    @Test
    public void testMin() {
        int[] diceValues = {1, 2, 3, 4, 5};
        assertEquals(1, calculator.min(diceValues));
        int[] emptyValues = {};
        assertEquals(Integer.MAX_VALUE, calculator.min(emptyValues));
    }
}