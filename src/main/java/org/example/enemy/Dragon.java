package org.example.enemy;

public class Dragon extends BaseEnemy {

    private static final int EXPERIENCE     = 999999;
    private static final char ENEMY_CHAR    = 'D';
    private static final char HEALTH_POINTS = 500;
    private static final int ATTACK_VALUE   = 45;


    public Dragon(String name) {
        super(name, ENEMY_CHAR, HEALTH_POINTS, ATTACK_VALUE, EXPERIENCE);
    }

    public int breathFire() {
        return 45;
    }
}
