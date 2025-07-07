/**
 * Author: Mahmoud Oraby
 * Course: CST338-40_2253 (Summer 2025)
 * Project: Legally Distinct Pocket Monsters (LDPM)
 * File: WeirdTurtle.java
 * Description: Concrete implementation of Monster representing a Water-type monster.
 */

public class WeirdTurtle extends Monster {

    private static final int DEFENSE_MIN = 3;
    private static final int DEFENSE_MAX = 8;
    private static final int ATTACK_MIN = 5;
    private static final int ATTACK_MAX = 11;

    /**
     * Constructor sets name and type, then initializes min/max stats.
     * @param name The name of the WeirdTurtle
     */
    public WeirdTurtle(String name) {
        super(name, ElementalType.WATER);
        this.defenseMin = DEFENSE_MIN;
        this.defenseMax = DEFENSE_MAX;
        this.attackMin = ATTACK_MIN;
        this.attackMax = ATTACK_MAX;
    }

    @Override
    public void setAttackPoints() {
        this.attackPoints = Dice.roll(attackMin, attackMax);
    }

    @Override
    public void setDefensePoints() {
        this.defensePoints = Dice.roll(defenseMin, defenseMax);
    }

    @Override
    public void setDefensePoints(int value) {
        this.defensePoints = value;
    }

    @Override
    public String getPhrase() {
        return "'Urtle! 'Urtle!";
    }
}
