/**
 * Author: Mahmoud Oraby
 * Course: CST338-40_2253 (Summer 2025)
 * Project: Legally Distinct Pocket Monsters (LDPM)
 * File: ElectricRat.java
 * Description: Concrete implementation of Monster representing an Electric-type monster.
 */

public class ElectricRat extends Monster {

    // Default constants for ElectricRat stats
    private static final int DEFENSE_MIN = 5;
    private static final int DEFENSE_MAX = 8;
    private static final int ATTACK_MIN = 5;
    private static final int ATTACK_MAX = 8;

    /**
     * Constructor sets name and type, then initializes min/max stats.
     * @param name The name of the ElectricRat
     */
    public ElectricRat(String name) {
        super(name, ElementalType.ELECTRIC);
        this.defenseMin = DEFENSE_MIN;
        this.defenseMax = DEFENSE_MAX;
        this.attackMin = ATTACK_MIN;
        this.attackMax = ATTACK_MAX;
    }

    /**
     * Rolls and sets the attackPoints using ATTACK_MIN and ATTACK_MAX.
     */
    @Override
    public void setAttackPoints() {
        this.attackPoints = Dice.roll(attackMin, attackMax);
    }

    /**
     * Rolls and sets the defensePoints using DEFENSE_MIN and DEFENSE_MAX.
     */
    @Override
    public void setDefensePoints() {
        this.defensePoints = Dice.roll(defenseMin, defenseMax);
    }

    /**
     * Allows manual setting of defensePoints (e.g., for testing).
     * @param value The value to assign to defensePoints
     */
    @Override
    public void setDefensePoints(int value) {
        this.defensePoints = value;
    }

    /**
     * Returns ElectricRat's signature phrase.
     * @return The catchphrase string.
     */
    @Override
    public String getPhrase() {
        return "'Lectric! 'Lectric!";
    }
}
