package entities;

import utility.Coordinate;
import utility.WorldMap;

import java.util.*;

public abstract class Creature extends Entity {
    private int hp;
    private int speed;
    private Coordinate targetCoordinate;
    private static Map<Coordinate, Entity> creatureMap = new HashMap<>();
    private Queue<Coordinate> searchQueue = new LinkedList<>();
    private Set<Coordinate> visitedCoordinates = new HashSet<>();
    private Map<Coordinate, Coordinate> previousCoordinate = new HashMap<>();
    private Stack<Coordinate> pathToFood = new Stack<>();
    private Map<Coordinate, Entity> worldMap = WorldMap.getWorldMap();

    public Creature(int hp, int speed) {
        this.hp = hp;
        this.speed = speed;
    }
    public void findFood(Coordinate coordinate) {
        visitedCoordinates.add(coordinate);
        int x = coordinate.getX();
        int y = coordinate.getY();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1 ; j++) {
                if ((i == x - 1 && j == y) || (i == x + 1 && j == y) ||
                        (i == x && j == y - 1) || (i == x && j == y) || (i == x && j == y + 1)) {
                    Coordinate checkCoordinate = new Coordinate(i, j);
                    if ((worldMap.get(checkCoordinate) instanceof Fruit && this instanceof Herbivore) ||
                            (worldMap.get(checkCoordinate) instanceof Herbivore && this instanceof Predator)) {
                        targetCoordinate = checkCoordinate;
                        previousCoordinate.put(checkCoordinate, coordinate);
                        System.out.println(targetCoordinate);
                        return;
                    } else if (worldMap.get(checkCoordinate) instanceof Ground
                            && !(visitedCoordinates.contains(checkCoordinate))
                            && !(searchQueue.contains(checkCoordinate))) {
                        searchQueue.add(checkCoordinate);
                        previousCoordinate.put(checkCoordinate, coordinate);
                    }
                }
            }
        }
        if (targetCoordinate == null && searchQueue.isEmpty()) {
            targetCoordinate = coordinate;
            return;
        }
        findFood(searchQueue.poll());
    }
    public void makeMove(Coordinate coordinate) {
        buildPathToFood(coordinate);
        if (worldMap.get(pathToFood.peek()) instanceof Ground) {
            WorldMap.removeEntity(coordinate);
            if (this instanceof Herbivore) {
                WorldMap.addEntity(pathToFood.pop(), new Herbivore(hp));
            } else if (this instanceof Predator) {
                WorldMap.addEntity(pathToFood.pop(), new Predator(hp));
            }
        } else if (worldMap.get(pathToFood.peek()) instanceof Fruit && this instanceof Herbivore) {
            eat(coordinate, targetCoordinate);
        } else if (worldMap.get(pathToFood.peek()) instanceof Herbivore && this instanceof Predator) {
            eat(coordinate, targetCoordinate);
        } else if (coordinate.equals(targetCoordinate)) {
            WorldMap.removeEntity(coordinate);
            if (this instanceof Herbivore) {
                WorldMap.addEntity(pathToFood.pop(), new Herbivore(hp));
            } else if (this instanceof Predator) {
                WorldMap.addEntity(pathToFood.pop(), new Predator(hp));
            }
        }
    }
    public void buildPathToFood(Coordinate coordinate) {
        Coordinate startCoordinate = coordinate;
        pathToFood.add(targetCoordinate);
        if (previousCoordinate.isEmpty()) {
            return;
        }
        Coordinate nextCoordinate = previousCoordinate.get(targetCoordinate);
        while (!(nextCoordinate.equals(startCoordinate))) {
            pathToFood.add(nextCoordinate);
            nextCoordinate = previousCoordinate.get(nextCoordinate);
        }
        System.out.println(pathToFood);
    }

    public static void removeCreatureFromMap(Coordinate coordinate) {
        creatureMap.remove(coordinate);
    }

    public static void updateCreatureMap() {
        creatureMap.clear();
        for (Map.Entry<Coordinate, Entity> entry : WorldMap.getWorldMap().entrySet()) {
            if (entry.getValue() instanceof Herbivore || entry.getValue() instanceof Predator) {
                creatureMap.put(entry.getKey(), entry.getValue());
            }
        }
        System.out.println(creatureMap);
    }
    public abstract void eat(Coordinate coordinate, Coordinate targetCoordinate);

    public static Map<Coordinate, Entity> getCreatureMap() {
        return creatureMap;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
