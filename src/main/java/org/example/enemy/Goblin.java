package org.example.enemy;

public class Goblin extends BaseEnemy {

    private static final int EXPERIENCE     = 10;
    private static final char ENEMY_CHAR    = 'g';
    private static final char HEALTH_POINTS = 20;
    private static final int ATTACK_VALUE   = 5;

    public Goblin(String name) {
        super(name, ENEMY_CHAR, HEALTH_POINTS, ATTACK_VALUE, EXPERIENCE);
    }
}
