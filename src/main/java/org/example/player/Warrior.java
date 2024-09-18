package org.example.player;

import java.util.HashMap;
import java.util.Map;

public class Warrior extends Hero {

    private static final char PLAYER_CHAR          = 'W';
    private static final int  INITIAL_HEALTH       = 120;
    private static final int  INITIAL_EXP          = 0;
    private static final int  INITIAL_MANA         = 0;
    private static final int  INITIAL_STRENGTH     = 25;
    private static final int  INITIAL_DEXTERITY    = 5;
    private static final int  INITIAL_INTELLIGENCE = 5;
    private static final int  INITIAL_LUCK         = 1;

    private static final Map<String, Integer> SKILLS = getSkills();

    private static Map<String, Integer> getSkills() {
        Map<String, Integer> skills = new HashMap<>();
        skills.put("Punch", 10);
        skills.put("Kick", 20);
        skills.put("Roundhouse kick", 40);
        skills.put("Deep Dark Fantasy", 10000);
        return skills;
    }


    public Warrior(String name) {
        super(
                name, INITIAL_HEALTH, INITIAL_EXP, INITIAL_MANA,
                INITIAL_STRENGTH, INITIAL_DEXTERITY, INITIAL_INTELLIGENCE,
                INITIAL_LUCK, PLAYER_CHAR, SKILLS);
    }

    @Override
    public void levelUp() {
        this.strength += 5;
        this.healthPoints += 15;
    }

    @Override
    public int attack() {
        int damage = strength * baseAttack;
        damage *= (criticalStrike() == 1) ? 2 : 1;
        return damage;
    }

}