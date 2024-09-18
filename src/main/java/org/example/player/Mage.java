package org.example.player;

import java.util.HashMap;
import java.util.Map;

public class Mage extends Hero {

    private static final char PLAYER_CHAR          = 'M';
    private static final int  INITIAL_HEALTH       = 75;
    private static final int  INITIAL_EXP          = 0;
    private static final int  INITIAL_MANA         = 150;
    private static final int  INITIAL_STRENGTH     = 5;
    private static final int  INITIAL_DEXTERITY    = 10;
    private static final int  INITIAL_INTELLIGENCE = 30;
    private static final int  INITIAL_LUCK         = 1;

    private static final Map<String, Integer> SKILLS = getSkills();

    private static Map<String, Integer> getSkills() {
        Map<String, Integer> skills = new HashMap<>();
        skills.put("Fireball", 35);
        skills.put("THE SUN THE SUN THE SUN THE SUN...", 666);
        skills.put("Ice storm", 50);
        return skills;
    }

    public Mage(String name) {
        super(
                name, INITIAL_HEALTH, INITIAL_EXP, INITIAL_MANA,
                INITIAL_STRENGTH, INITIAL_DEXTERITY, INITIAL_INTELLIGENCE,
                INITIAL_LUCK, PLAYER_CHAR, SKILLS);
    }

    @Override
    public void levelUp() {
        this.intelligence += 5;
        this.healthPoints += 5;
        this.manaPoints += 10;
    }

    @Override
    public int cast(String skill) {
        this.manaPoints -= 5;
        return super.cast(skill);
    }

    @Override
    public int attack() {
        int damage = intelligence * baseAttack;
        damage *= (criticalStrike() == 1) ? 2 : 1;
        return damage;
    }
}
