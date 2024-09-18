package org.example.player;

import org.example.Colors;
import org.example.Mortal;
import org.example.enemy.BaseEnemy;

import java.util.Map;
import java.util.Random;
import java.util.Set;

abstract public class Hero implements Mortal {

    // Player name.
    protected final String name;
    // Map representation.
    protected final char playerChar;

    // General player stats.
    protected int
            healthPoints,
            experience,
            manaPoints;

    // AbstractPlayer stats.
    protected int
            strength,
            dexterity,
            intelligence,
            luck;

    protected Map<String, Integer> skills;

    // Base stats for all characters.
    final static protected int lucky = 10;
    final static protected int baseAttack = 20;

    Hero(
            String name,
            int healthPoints, int experience, int manaPoints,
            int strength, int dexterity, int intelligence,
            int luck, char playerChar,
            Map<String, Integer> skills
    ) {
        this.name         = name;
        this.healthPoints = healthPoints;
        this.experience   = experience;
        this.manaPoints   = manaPoints;
        this.strength     = strength;
        this.dexterity    = dexterity;
        this.intelligence = intelligence;
        this.luck         = luck;
        this.playerChar   = playerChar;
        setSkills(skills);
    }


    /**
     * Different classes attack differently.
     * @return attack value.
     */
    abstract public int attack();

    /**
     * Get skill from the skills Map
     * @param skill name
     * @return attack value.
     */
    public int cast(String skill) {
        return this.skills.getOrDefault(skill, 1);
    }

    /**
     * Different class level up differently.
     */
    abstract public void levelUp();

    public void raiseExperience(int exp) {
        this.experience += exp;
        if (this.experience == 100) {
            levelUp();
        }
    }

    private void setSkills(Map<String, Integer> skills) {
        this.skills = skills;
    }

    public String getName() {
        return Colors.PURPLE + this.name + Colors.RESET;
    }

    public Set<String> listSkills() {
        return this.skills.keySet();
    }

    /**
     * Calculate the critical change using luck.
     */
    protected int criticalStrike() {
        java.util.Random rand = new Random();
        return rand.nextInt(lucky) / this.luck;
    }

    public void takeDamage(BaseEnemy enemy) {
        this.healthPoints -= enemy.attack();
    }

    public boolean isAlive() {
        return this.healthPoints <= 0;
    }

    public char toChar() {
        return this.playerChar;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }
}
