/**
 * Author: Mahmoud Oraby
 * Course: CST338-40_2253 (Summer 2025)
 * Project: Legally Distinct Pocket Monsters (LDPM)
 * File: FireLizard.java
 * Description: Concrete implementation of Monster representing a Fire-type monster.
 */

public class FireLizard extends Monster {

    private static final int DEFENSE_MIN = 4;
    private static final int DEFENSE_MAX = 10;
    private static final int ATTACK_MIN = 8;
    private static final int ATTACK_MAX = 16;

    /**
     * Constructor sets name and type, then initializes min/max stats.
     * @param name The name of the FireLizard
     */
    public FireLizard(String name) {
        super(name, ElementalType.FIRE);
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
        return "Deal with it. Deal with it.";
    }
}
