/**
 * Author: Mahmoud Oraby
 * Course: CST338-40_2253 (Summer 2025)
 * File: Dice.java
 * Description: Utility class for rolling dice.
 */

import java.util.Random;

public class Dice {
    private static final Random rand = new Random();

    /**
     * Rolls a number between min and max inclusive.
     * @param min Minimum value
     * @param max Maximum value
     * @return A random int in the range [min, max]
     */
    public static int roll(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
}
