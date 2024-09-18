package org.example.enemy;

public class Spider extends BaseEnemy {


    private static final int EXPERIENCE     = 40;
    private static final char ENEMY_CHAR    = 's';
    private static final char HEALTH_POINTS = 40;
    private static final int ATTACK_VALUE   = -1;

    public Spider(String name) {
        super(name, ENEMY_CHAR, HEALTH_POINTS, ATTACK_VALUE, EXPERIENCE);
    }

}
