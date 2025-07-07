// File: MonsterTest.java
// Author: Mahmoud Oraby
// Description: Unit tests for the Legally Distinct Pocket Monsters project

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    private ElectricRat electricRat;
    private FireLizard fireLizard;
    private FlowerDino flowerDino;
    private WeirdTurtle weirdTurtle;

    @BeforeEach
    void setUp() {
        electricRat = new ElectricRat("Electric Rat");
        fireLizard = new FireLizard("Clearly the best");
        flowerDino = new FlowerDino("Flower Dino");
        weirdTurtle = new WeirdTurtle("Weird Turtle Thing");
    }

    @Test
    void testConstructor() {
        assertEquals("Electric Rat", electricRat.getName());
        assertEquals("Clearly the best", fireLizard.getName());
        assertEquals("Flower Dino", flowerDino.getName());
        assertEquals("Weird Turtle Thing", weirdTurtle.getName());
    }

    @Test
    void testSetType() {
        assertEquals(1, electricRat.setType(Monster.ElementalType.ELECTRIC));
        assertEquals(-1, electricRat.setType(Monster.ElementalType.WATER)); // Conflicting type
        assertEquals(0, electricRat.setType(Monster.ElementalType.GRASS)); // Non-conflicting
    }

    @Test
    void testAttackPoints() {
        electricRat.setAttackPoints();
        assertTrue(electricRat.getAttackPoints() >= 5 && electricRat.getAttackPoints() <= 8);

        fireLizard.setAttackPoints();
        assertTrue(fireLizard.getAttackPoints() >= 8 && fireLizard.getAttackPoints() <= 16);
    }

    @Test
    void testDefensePoints() {
        weirdTurtle.setDefensePoints();
        assertTrue(weirdTurtle.getDefensePoints() >= 3 && weirdTurtle.getDefensePoints() <= 8);

        flowerDino.setDefensePoints();
        assertTrue(flowerDino.getDefensePoints() >= 4 && flowerDino.getDefensePoints() <= 8);
    }

    @Test
    void testAttackAndTakeDamage() {
        double initialHp = weirdTurtle.getHealthPoints();
        double damage = electricRat.attack(weirdTurtle);
        assertTrue(damage >= 0);
        assertTrue(weirdTurtle.getHealthPoints() <= initialHp);
    }

    @Test
    void testFainting() {
        double overkillDamage = 100.0;
        weirdTurtle.takeDamage(overkillDamage);
        assertTrue(weirdTurtle.isFainted());
    }

    @Test
    void testToStringOutput() {
        String description = flowerDino.toString();
        assertTrue(description.contains("Flower Dino"));
        assertTrue(description.contains("Elemental type:"));
    }

    @Test
    void testGetPhrase() {
        assertEquals("'Lectric! 'Lectric!", electricRat.getPhrase());
        assertEquals("Deal with it. Deal with it.", fireLizard.getPhrase());
        assertEquals("Flowah! Flowah!", flowerDino.getPhrase());
        assertEquals("'Urtle! 'Urtle!", weirdTurtle.getPhrase());
    }
}
