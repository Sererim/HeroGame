package org.example.enemy;

public class Zombie extends BaseEnemy {

    private static final int EXPERIENCE     = 50;
    private static final char ENEMY_CHAR    = 'z';
    private static final char HEALTH_POINTS = 90;
    private static final int ATTACK_VALUE   = 10;

    private boolean isResurrected = false;

    public Zombie(String name) {
        super(name, ENEMY_CHAR, HEALTH_POINTS, ATTACK_VALUE, EXPERIENCE);
    }

    @Override
    public boolean isAlive() {
        if (super.isAlive() && !isResurrected) {
            isResurrected = true;
            return false;
        }
        return super.isAlive() && isResurrected;
    }
}
