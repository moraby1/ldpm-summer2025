// File: Monster.java
// Author: Mahmoud Oraby
// Description: Abstract base class for all monsters in LDPM project.

import java.util.Random;

public abstract class Monster {
    public enum ElementalType {
        ELECTRIC, WATER, FIRE, GRASS
    }

    protected String name;
    protected ElementalType type;
    protected double healthPoints;
    protected int attackPoints;
    protected int defensePoints;
    protected int attackMin;
    protected int attackMax;
    protected int defenseMin;
    protected int defenseMax;

    protected final Random rand = new Random();

    /**
     * Constructs a Monster with the given name and elemental type.
     * @param name The monster's name.
     * @param type The monster's elemental type.
     */
    public Monster(String name, ElementalType type) {
        this.name = name;
        this.type = type;
        this.healthPoints = 100.0;
    }

    /**
     * Gets the monster's name.
     * @return name of the monster.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current health points.
     * @return healthPoints value.
     */
    public double getHealthPoints() {
        return healthPoints;
    }

    /**
     * Gets the current attack points.
     * @return attackPoints value.
     */
    public int getAttackPoints() {
        return attackPoints;
    }

    /**
     * Gets the current defense points.
     * @return defensePoints value.
     */
    public int getDefensePoints() {
        return defensePoints;
    }

    /**
     * Sets the elemental type of the monster.
     * @param type The new elemental type
     * @return 1 if same as current type, -1 if it conflicts (e.g., electric vs water), 0 otherwise
     */
    public int setType(ElementalType type) {
        if (this.type == type) {
            return 1; // same type
        }

        boolean conflict =
                (this.type == ElementalType.ELECTRIC && type == ElementalType.WATER) ||
                        (this.type == ElementalType.WATER && type == ElementalType.ELECTRIC) ||
                        (this.type == ElementalType.FIRE && type == ElementalType.GRASS) ||
                        (this.type == ElementalType.GRASS && type == ElementalType.FIRE);

        if (conflict) {
            return -1;
        }

        this.type = type;
        return 0;
    }

    /**
     * Rolls for attack points and sets them.
     */
    public abstract void setAttackPoints();

    /**
     * Rolls for defense points and sets them.
     */
    public abstract void setDefensePoints();

    /**
     * Sets defense points manually for testing.
     * @param value value to assign to defense points.
     */
    public abstract void setDefensePoints(int value);

    /**
     * Performs an attack on another monster.
     * @param target the monster being attacked
     * @return the amount of damage dealt
     */
    public double attack(Monster target) {
        this.setAttackPoints();
        target.setDefensePoints();

        double damage = this.attackPoints - target.defensePoints;

        if (damage > 0) {
            target.takeDamage(damage);
        } else {
            damage = 0;
        }

        return damage;
    }

    /**
     * Applies damage to this monster, reducing health points.
     * @param damage amount of damage taken
     */
    public void takeDamage(double damage) {
        this.healthPoints -= damage;
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
        }
    }

    /**
     * Checks if the monster has fainted (HP <= 0).
     * @return true if fainted, false otherwise
     */
    public boolean isFainted() {
        return this.healthPoints <= 0;
    }

    /**
     * Returns a string representation of the monster.
     * @return formatted string with monster info
     */
    @Override
    public String toString() {
        return name + " - Elemental type: " + type +
                ", HP: " + healthPoints +
                ", ATK: " + attackPoints +
                ", DEF: " + defensePoints;
    }

    /**
     * Returns the monster's battle phrase.
     * @return string phrase for battle cry
     */
    public abstract String getPhrase();
}
