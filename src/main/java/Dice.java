/**
 * Dice class for rolling random values between a minimum and maximum.
 * Used for attack and defense point calculations in Monsters.
 *
 * @author Mahmoud Oraby
 */
public class Dice {

    /**
     * Rolls a number between min and max (inclusive).
     *
     * @param min the minimum value for the roll
     * @param max the maximum value for the roll
     * @return a random double between min and max
     */
    public static double roll(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min cannot be greater than max.");
        }
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
}
