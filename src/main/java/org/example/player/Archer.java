package org.example.player;

import java.util.HashMap;
import java.util.Map;

public class Archer extends Hero {

    private static final char PLAYER_CHAR          = 'A';
    private static final int  INITIAL_HEALTH       = 65;
    private static final int  INITIAL_EXP          = 0;
    private static final int  INITIAL_MANA         = 0;
    private static final int  INITIAL_STRENGTH     = 9;
    private static final int  INITIAL_DEXTERITY    = 25;
    private static final int  INITIAL_INTELLIGENCE = 7;
    private static final int  INITIAL_LUCK         = 2;

    private static final Map<String, Integer> SKILLS = getSkills();

    private static Map<String, Integer> getSkills() {
        Map<String, Integer> skills = new HashMap<>();
        skills.put("Eagle eye", 30);
        skills.put("Triple shoot", 60);
        skills.put("Sharp arrow", 40);
        return skills;
    }

    public Archer(String name) {
        super(
                name, INITIAL_HEALTH, INITIAL_EXP, INITIAL_MANA,
                INITIAL_STRENGTH, INITIAL_DEXTERITY, INITIAL_INTELLIGENCE,
                INITIAL_LUCK, PLAYER_CHAR, SKILLS);
    }

    @Override
    public int attack() {
        int damage = dexterity * baseAttack;
        damage *= (criticalStrike() == 1) ? 2 : 1;
        return damage;
    }

    @Override
    public void levelUp() {
        this.dexterity += 5;
        this.healthPoints += 10;
    }
}
