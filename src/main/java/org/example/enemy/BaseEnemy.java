package org.example.enemy;


import org.example.Mortal;

public class BaseEnemy implements Mortal {

    protected final String name;
    protected final char   enemyChar;

    protected int
            healthPoints,
            attackValue,
            experience;

    public BaseEnemy (
            String name, char enemyChar,
            int healthPoints, int attackValue,
            int experience
    ) {
        this.name         = name;
        this.enemyChar    = enemyChar;
        this.healthPoints = healthPoints;
        this.attackValue  = attackValue;
        this.experience   = experience;
    }

    public void lookAround() {

    }

    protected void move() {

    }

    public int attack() {
        return attackValue;
    }

    public void damaged(int attackValue) {
        this.healthPoints -= attackValue;
    }

    public boolean isAlive() {
        return this.healthPoints > 0;
    }

    public char toChar() {
        return this.enemyChar;
    }

    public int getExperience() {
        return this.experience;
    }

    @Override
    public String toString() {
        return this.name;
    }
}