package org.example;

import org.example.enemy.*;
import org.example.map.MapObject;
import org.example.map.Structure;
import org.example.map.Space;
import org.example.player.*;


import java.util.*;

public class Main {

    private static ArrayList<ArrayList<MapObject>> mapEntire = new ArrayList<>();
    private static Map<String, MapObject>          livingMap = new HashMap<>();


    public static void main(String[] args) {
        gameLoop();
    }

    /**
     * Main game loop.
     */
    public static void gameLoop() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Game is starting.\nPlease choose your character's class and name.");

        Hero player = null;

        String character;
        String name;

        // Get player class and name.
        System.out.println("Characters to choose:\nArcher\nMage\nRogue\nWarrior");
        character = scanner.nextLine();
        System.out.println("Please enter your name!");
        name = scanner.nextLine();
        player = getCharacter(character, name);

        if (player == null) {
            System.out.println(Colors.RED + "Such class does not exist." + Colors.RESET);
            gameLoop();
        }

        makeMap(player);

        boolean isStart = true;

        while (player != null && !player.isAlive()) {

            if (isStart) {
                System.out.println(player.getName() + " " + player.getHealthPoints() + " " + player.listSkills());
                isStart = false;
            }

            drawMap();
            playerInput(scanner, player);
            livingWorld();
        }
    }

    /**
     *
     * @param character Correct name of the class.
     * @param name Name of the new player character.
     * @return one of the Hero derived classes.
     */
    public static Hero getCharacter(String character, String name) {
        Hero hero;
        switch (character) {
            case "Archer":
                hero = new Archer(name);
                break;
            case "Mage":
                hero = new Mage(name);
                break;
            case "Rogue":
                hero = new Rogue(name);
                break;
            case "Warrior":
                hero = new Warrior(name);
                break;
            default:
                hero = null;
        }
        return hero;
    }

    private static void drawMap() {
        for (int i = 0; i < mapEntire.size(); i++) {
            for (int j = 0; j < mapEntire.get(i).size(); j++) {
                System.out.print(
                        mapEntire.get(i).get(j).colored()
                );
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void makeMap(Hero player) {
        char[][] dungeonMap = {
                {'=', '=', '=', '=', '=', '=', '=', '='},
                {'|', '.', 'z', '.', '.', '|', '.', '|'},
                {'|', '.', '.', '.', '.', '|', 'D', '|'},
                {'|', '.', '.', '.', '.', '|', '.', '|'},
                {'|', '.', 'z', '.', '.', '|', '.', '|'},
                {'|', '.', '.', 'z', '.', '.', '.', '|'},
                {'|', 'z', '.', '.', '.', '.', '.', '|'},
                {'|', '.', '.', '.', '.', '.', '.', '|'},
                {'|', '=', '=', '=', '.', '=', '=', '|'},
                {'|', '.', '.', 's', '.', '.', '.', '|'},
                {'|', 'g', '.', '.', 'g', '.', 'g', '|'},
                {'|', '.', '.', '.', '.', '.', '.', '|'},
                {'|', '.', '.', 'X', '.', '.', '.', '|'},
                {'=', '=', '=', '=', '=', '=', '=', '='},
        };

        for (int i = 0; i < dungeonMap.length; i++) {
            mapEntire.add(new ArrayList<>());
            for (int j = 0; j < dungeonMap[0].length; j++) {
                char mapObject = dungeonMap[i][j];

                MapObject temp = null;

                switch (mapObject) {
                    case 'z':
                        temp = new MapObject(new Zombie("Sniffer " + i), j, i);
                        mapEntire.get(i).add(temp);
                        livingMap.put("Sniffer " + i, temp);
                        break;
                    case 'g':
                        temp = new MapObject(new Goblin("Goblet " + i), j, i);
                        mapEntire.get(i).add(temp);
                        livingMap.put("Goblet " + i, temp);
                        break;
                    case 'D':
                        temp = new MapObject(new Dragon("Smoug " + i), j, i);
                        mapEntire.get(i).add(temp);
                        livingMap.put("Smoug " + i, temp);
                        break;
                    case 's':
                        temp = new MapObject(new Spider("Muffet " + i), j, i);
                        mapEntire.get(i).add(temp);
                        livingMap.put("Muffet " + i, temp);
                        break;
                    case 'X':
                        temp = new MapObject(player, j, i);
                        mapEntire.get(i).add(temp);
                        livingMap.put(player.getName(), temp);
                        break;
                    case '.':
                        mapEntire.get(i).add(new Space(j, i, mapObject));
                        break;
                    default:
                        mapEntire.get(i).add(new Structure(j, i, mapObject));
                        break;
                }
            }
        }
    }

    private static void playerInput(Scanner scanner, Hero player) {
        MapObject playerOnMap = livingMap.get(player.getName());
        int x = playerOnMap.getPositionX();
        int y = playerOnMap.getPositionY();
        System.out.printf("Your position\nX - %d\nY - %d\n", x, y);
        System.out.println("You can move one tile per turn or attack.\nEnter M/m to move or A/a to attack.");
        switch (scanner.next().toLowerCase()) {
            case "a":
                message("attack");
                switch (scanner.next().toLowerCase()) {
                    case "w":
                        y -= 1;
                        if (!attackEnemy(x, y, player)) {
                            System.out.println("Can't attack enemy");
                            playerInput(scanner, player);
                        }
                        break;
                    case "s":
                        y += 1;
                        attackEnemy(x, y, player);
                        if (!attackEnemy(x, y, player)) {
                            System.out.println("Can't attack enemy");
                            playerInput(scanner, player);
                        }
                        break;
                    case "d":
                        x += 1;
                        attackEnemy(x, y, player);
                        if (!attackEnemy(x, y, player)) {
                            System.out.println("Can't attack enemy");
                            playerInput(scanner, player);
                        }
                        break;
                    case "a":
                        x -= 1;
                        attackEnemy(x, y, player);
                        if (!attackEnemy(x, y, player)) {
                            System.out.println("Can't attack enemy");
                            playerInput(scanner, player);
                        }
                        break;
                    default:
                        System.out.println("No such command!");
                        playerInput(scanner, player);
                }
            case "m":
                message("move");
                switch (scanner.next().toLowerCase()) {
                    case "w":
                        y -= 1;
                        if (!movePlayer(x, y, playerOnMap)) {
                            System.out.printf("Can't move to position x %d y %d\n", x, y);
                            playerInput(scanner, player);
                        }
                        break;
                    case "s":
                        y += 1;
                        if (!movePlayer(x, y, playerOnMap)) {
                            System.out.printf("Can't move to position x %d y %d", x, y);
                            playerInput(scanner, player);
                        }
                        break;
                    case "d":
                        x += 1;
                        if (!movePlayer(x, y, playerOnMap)) {
                            System.out.printf("Can't move to position x %d y %d\n", x, y);
                            playerInput(scanner, player);
                        }
                        break;
                    case "a":
                        x -= 1;
                        if (!movePlayer(x, y, playerOnMap)) {
                            System.out.printf("Can't move to position x %d y %d\n", x, y);
                            playerInput(scanner, player);
                        }
                        break;
                    default:
                        System.out.println("No such command!");
                        playerInput(scanner, player);
                }
                break;
            default:
                System.out.println("No such command!");
                playerInput(scanner, player);
        }
    }

    private static boolean movePlayer(int x,int y, MapObject temp) {
        if (mapEntire.get(y).get(x).isPassable()) {

            mapEntire.get(temp.getPositionY()).set(
                            temp.getPositionX(),
                            new Space(temp.getPositionX(), temp.getPositionY(), '.')
                    );

            temp.setPositionX(x);
            temp.setPositionY(y);

            mapEntire.get(y).set(x, temp);

            return true;
        }
        return false;
    }


    private static boolean attackEnemy(int x, int y, Hero player) {
        if (mapEntire.get(y).get(x).getEnemy() != null) {
            MapObject enemyOnMap = mapEntire.get(y).get(x);
            BaseEnemy enemy = enemyOnMap.getEnemy();
            enemy.damaged(player.attack());
            player.takeDamage(enemy);
            System.out.printf("You attacked %s and inflicted %d damage.", enemy, player.attack());
            System.out.printf("%s strikes back and damaged you %d", enemy, enemy.attack());

            if (!enemy.isAlive()) {
                System.out.println(Colors.RED + "Enemy is DEAD!\n" + Colors.RESET);
                player.raiseExperience(enemy.getExperience());
                mapEntire.get(y).set(
                        x,
                        new Space(enemyOnMap.getPositionX(), enemyOnMap.getPositionY(), '.')
                );
                return true;
            }
            return true;
        }
        return false;
    }

    private static void livingWorld() {

    }

    private static void message(String action) {
        System.out.printf("Enter W to %s upward, " +
                "S to %s downward, " +
                "D to %s to the right and " +
                "A to %s to the left.\n",
                action, action, action, action);
    }
}