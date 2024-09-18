package org.example.map;

import org.example.Colors;
import org.example.enemy.BaseEnemy;
import org.example.player.Hero;

public class MapObject
        implements Movable, Passable {

    private BaseEnemy enemy = null;
    private Hero hero       = null;

    private int
            positionX,
            positionY;

    public MapObject(
            BaseEnemy enemy,
            int positionX, int positionY) {
        this.enemy     = enemy;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public MapObject(
            Hero hero,
            int positionX,
            int positionY
    ) {
        this.hero      = hero;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public MapObject(
            int positionX,
            int positionY
    ) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Hero getHero() {
        return this.hero;
    }

    public BaseEnemy getEnemy() {
        return this.enemy;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public String colored() {
        if (enemy != null) {
            switch (enemy.toChar()) {
                case 'D':
                    return Colors.RED + enemy.toChar() + Colors.RESET;
                case 'g':
                    return Colors.GREEN + enemy.toChar() + Colors.RESET;
                case 'z':
                    return Colors.PURPLE + enemy.toChar() + Colors.RESET;
                case 's':
                    return Colors.WHITE + enemy.toChar() + Colors.RESET;
            }
        }
        if (hero != null) {
            return Colors.WHITE_BOLD + hero.toChar() + Colors.RESET;
        }
        return "";
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public boolean isPassable() {
        return false;
    }
}
