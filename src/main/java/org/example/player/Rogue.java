package org.example.player;

import java.util.HashMap;
import java.util.Map;

public class Rogue extends Hero {

    private static final char PLAYER_CHAR          = 'R';
    private static final int  INITIAL_HEALTH       = 50;
    private static final int  INITIAL_EXP          = 0;
    private static final int  INITIAL_MANA         = 0;
    private static final int  INITIAL_STRENGTH     = 5;
    private static final int  INITIAL_DEXTERITY    = 35;
    private static final int  INITIAL_INTELLIGENCE = 12;
    private static final int  INITIAL_LUCK         = 10;

    private static final Map<String, Integer> SKILLS = getSkills();

    private static Map<String, Integer> getSkills() {
        Map<String, Integer> skills = new HashMap<>();
        skills.put("Sneak attack", 90);
        skills.put("I am here too", 75);
        skills.put("It is over....", 9999999);
        return skills;
    }

    public Rogue(String name) {
        super(
                name, INITIAL_HEALTH, INITIAL_EXP, INITIAL_MANA,
                INITIAL_STRENGTH, INITIAL_DEXTERITY, INITIAL_INTELLIGENCE,
                INITIAL_LUCK, PLAYER_CHAR, SKILLS);
    }


    @Override
    public void levelUp() {
        this.dexterity    += 5;
        this.healthPoints += 10;
        this.luck         += 1;
    }

    @Override
    public int cast(String skill) {
        return super.cast(skill);
    }

    @Override
    public int attack() {
        int damage = dexterity * baseAttack;
        damage *= (criticalStrike() == 1) ? 2 : 1;
        return damage;
    }
}
