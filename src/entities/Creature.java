package entities;

import utility.Coordinate;
import utility.Renderer;
import utility.WorldMap;

import java.util.*;

public abstract class Creature extends Entity {
    private int hp;
    private int speed;
    private Coordinate targetCoordinate;
    private static List<Entity> creatureList = new ArrayList<>();
    private static List<Coordinate> coordinateList = new ArrayList<>();
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
        if (targetCoordinate != null) {
            clearMemory();
        }
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
                WorldMap.addEntity(pathToFood.peek(), new Herbivore(hp));
                updateCoordinate(coordinate, pathToFood.pop());
            } else if (this instanceof Predator) {
                WorldMap.addEntity(pathToFood.peek(), new Predator(hp));
                updateCoordinate(coordinate, pathToFood.pop());
            }
        } else if (worldMap.get(pathToFood.peek()) instanceof Fruit && this instanceof Herbivore) {
            eat(coordinate, targetCoordinate);
            updateCoordinate(coordinate, targetCoordinate);
        } else if (worldMap.get(pathToFood.peek()) instanceof Herbivore && this instanceof Predator) {
            eat(coordinate, targetCoordinate);
            updateCoordinate(coordinate, targetCoordinate);
        } else if (coordinate.equals(targetCoordinate)) {
            WorldMap.removeEntity(coordinate);
            if (this instanceof Herbivore) {
                WorldMap.addEntity(pathToFood.pop(), new Herbivore(hp));
            } else if (this instanceof Predator) {
                WorldMap.addEntity(pathToFood.pop(), new Predator(hp));
            }
        }
    }
    private void buildPathToFood(Coordinate coordinate) {
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
    }
    public static void removeCreatureFromList(Coordinate coordinate) {
        int idx = Creature.coordinateList.indexOf(coordinate);
        creatureList.set(idx, null);
        coordinateList.set(idx, null);
    }
    private void updateCoordinate(Coordinate prevCoordinate, Coordinate curCoordinate) {
        int idx = coordinateList.indexOf(prevCoordinate);
        coordinateList.set(idx, curCoordinate);
    }

    public static void updateCreatureList() {
        creatureList.clear();
        coordinateList.clear();
        int idx = 0;
        for (Map.Entry<Coordinate, Entity> entry : WorldMap.getWorldMap().entrySet()) {
            if (entry.getValue() instanceof Herbivore || entry.getValue() instanceof Predator) {
                creatureList.add(idx, entry.getValue());
                coordinateList.add(idx, entry.getKey());
                idx++;
            }
        }
    }

    private void clearMemory() {
        targetCoordinate = null;
        pathToFood.clear();
        previousCoordinate.clear();
        visitedCoordinates.clear();
        searchQueue.clear();
    }

    public abstract void eat(Coordinate coordinate, Coordinate targetCoordinate);

    public static List<Entity> getCreatureList() {
        return creatureList;
    }

    public static List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
