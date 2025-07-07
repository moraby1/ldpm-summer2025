/**
 * Author: Mahmoud Oraby
 * Course: CST338-40_2253 (Summer 2025)
 * Project: Legally Distinct Pocket Monsters (LDPM)
 * File: Monster.java
 * Description: Abstract base class representing a generic monster.
 *              Handles common logic for attack, defense, damage, and type system.
 */

import java.util.ArrayList;
import java.util.List;

public abstract class Monster {
    // ========== Fields with Default Values ==========
    protected String name = "";
    protected String phrase = "";
    protected int attackMin = 1;
    protected int attackMax = 10;
    protected int defenseMin = 1;
    protected int defenseMax = 10;
    protected int attackPoints = 10;
    protected int defensePoints = 10;
    protected boolean fainted = false;
    protected static final double MAX_HP = 20.0;
    protected double healthPoints = MAX_HP;
    protected List<ElementalType> elements = new ArrayList<>();

    /**
     * Enum for elemental types.
     */
    protected enum ElementalType {
        ELECTRIC, FIRE, GRASS, WATER
    }

    /**
     * Constructor initializes the monster name and elemental type.
     * @param name Monster name
     * @param element Initial elemental type
     */
    public Monster(String name, ElementalType element) {
        this.name = name;
        this.elements.add(element);
        setPhrase(this); // Set phrase based on initial type
    }

    // ========== Abstract Methods ==========
    public abstract void setAttackPoints();            // To be defined by child classes
    public abstract void setDefensePoints();           // To be defined by child classes
    public abstract void setDefensePoints(int value);  // Optional override

    // ========== Phrase Handling ==========

    /**
     * Assigns a phrase based on elemental type.
     */
    public void setPhrase(Monster monster) {
        switch (monster.elements.get(0)) {
            case ELECTRIC -> monster.setPhrase("'Lectric!");
            case FIRE -> monster.setPhrase("Deal with it.");
            case GRASS -> monster.setPhrase("Flowah!");
            case WATER -> monster.setPhrase("'Urtle!");
            default -> monster.setPhrase("No phrase for me!");
        }
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getPhrase() {
        return phrase + " " + phrase;
    }

    // ========== Attack and Damage Logic ==========

    /**
     * Attacks another monster and returns the damage dealt.
     */
    public double attack(Monster monster) {
        if (this.fainted) {
            System.out.println(name + " isn't conscious… it can't attack.");
            return 0.0;
        }

        System.out.println(name + " is attacking " + monster.name);
        System.out.println(getPhrase());

        double attackValue = calculateAttackPoints(this, monster.elements);
        System.out.println(name + " is attacking with " + attackValue);

        double result = monster.takeDamage(attackValue);

        if (this == monster && result > 0) {
            System.out.println(name + " hurt itself in the confusion.");
        }

        return result;
    }

    /**
     * Applies incoming damage and adjusts HP.
     */
    public double takeDamage(double attackValue) {
        double defense = calculateDefensePoints(this);
        double damage = attackValue - defense;

        if (damage > 0) {
            System.out.println(name + " is hit for " + damage + " damage!");
            healthPoints -= damage;
        } else if (damage == 0) {
            System.out.println(name + " is nearly hit!.");
        }

        if (damage < defense / 2) {
            System.out.println(name + " shrugs off the puny attack.");
        }

        if (healthPoints <= 0) {
            System.out.println(name + " has faint-- passed out. It's passed out.");
            fainted = true;
        } else {
            System.out.println(name + " has " + healthPoints + " / " + MAX_HP + " HP remaining");
        }

        return Math.max(damage, 0);
    }

    /**
     * Rolls a defense value and prints context-specific message.
     */
    public double calculateDefensePoints(Monster monster) {
        int defenseValue = Dice.roll(monster.defenseMin, monster.defenseMax);

        if (defenseValue % 2 == 0 && defenseValue < (monster.defenseMax / 2)) {
            defenseValue = (defenseValue + 1) * 2;
            System.out.println(monster.name + " finds courage in the desperate situation");
        } else if (defenseValue == monster.defenseMin) {
            System.out.println(monster.name + " is clearly not paying attention.");
        }

        return defenseValue;
    }

    /**
     * Calculates total attack value using attack modifier.
     */
    public double calculateAttackPoints(Monster monster, List<ElementalType> enemyTypes) {
        int attackVal = Dice.roll(monster.attackMin, monster.attackMax);
        System.out.println(monster.name + " rolls a " + attackVal);

        double modifier = 1.0;
        for (ElementalType type : enemyTypes) {
            modifier *= attackModifier(type);
        }

        if (modifier >= 2.0) {
            System.out.println("It's su-- *cough* very effective!");
        }

        return attackVal * modifier;
    }

    /**
     * Returns attack modifier based on elemental strengths/weaknesses.
     */
    public double attackModifier(ElementalType defending) {
        for (ElementalType attack : this.elements) {
            return switch (attack) {
                case ELECTRIC -> switch (defending) {
                    case WATER -> 2.0;
                    case ELECTRIC, GRASS -> 0.5;
                    default -> 1.0;
                };
                case FIRE -> switch (defending) {
                    case GRASS -> 2.0;
                    case WATER, FIRE -> 0.5;
                    default -> 1.0;
                };
                case GRASS -> switch (defending) {
                    case WATER -> 2.0;
                    case GRASS, FIRE -> 0.5;
                    default -> 1.0;
                };
                case WATER -> switch (defending) {
                    case FIRE -> 2.0;
                    case GRASS, WATER -> 0.5;
                    default -> 1.0;
                };
            };
        }
        return 1.0;
    }

    // ========== Type Handling and Output ==========

    /**
     * Adds a new elemental type if valid.
     */
    public int setType(ElementalType type) {
        if (elements.contains(type)) {
            System.out.println(type + " already set!");
            return 1;
        }

        if (attackModifier(type) > 1.0) {
            System.out.println("Can't have conflicting types!");
            return -1;
        }

        elements.add(type);
        System.out.println(name + " now has " + type);
        return 0;
    }

    public List<ElementalType> getElements() {
        return elements;
    }

    /**
     * Returns formatted string showing name, HP, and types.
     */
    public String toString() {
        if (fainted) {
            return name + " has fainted.\nElemental type: " + elements;
        } else {
            return name + " has " + healthPoints + "/" + MAX_HP + "hp.\nElemental type: " + elements;
        }
    }
}
