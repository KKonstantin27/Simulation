package entities;

import utility.Coordinate;
import utility.WorldMap;

import java.util.*;

public abstract class Creature extends Entity {
    private int hp;
    private int speed;
    private Coordinate targetCoordinate;
    private Queue<Coordinate> searchQueue = new LinkedList<>();
    private Set<Coordinate> visitedCoordinates = new HashSet<>();
    private Map<Coordinate, Coordinate> previousCoordinate = new HashMap<>();
    private Stack<Coordinate> pathToFood = new Stack<>();

    public Creature(int hp, int speed) {
        this.hp = hp;
        this.speed = speed;
    }
    public void findFood(Coordinate coordinate) {
        Map<Coordinate, Entity> wordlMap = WorldMap.getWorldMap();
        visitedCoordinates.add(coordinate);
        int x = coordinate.getX();
        int y = coordinate.getY();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1 ; j++) {
                if ((i == x - 1 && j == y) || (i == x + 1 && j == y) ||
                        (i == x && j == y - 1) || (i == x && j == y) || (i == x && j == y + 1)) {
                    Coordinate checkCoordinate = new Coordinate(i, j);
                    if ((wordlMap.get(checkCoordinate) instanceof Fruit && this instanceof Herbivore) ||
                            (wordlMap.get(checkCoordinate) instanceof Herbivore && this instanceof Predator)) {
                        targetCoordinate = checkCoordinate;
                        previousCoordinate.put(checkCoordinate, coordinate);
                        System.out.println(targetCoordinate);
                        return;
                    } else if (wordlMap.get(checkCoordinate) instanceof Ground
                            && !(visitedCoordinates.contains(checkCoordinate))
                            && !(searchQueue.contains(checkCoordinate))) {
                        searchQueue.add(checkCoordinate);
                        previousCoordinate.put(checkCoordinate, coordinate);
                    }
                }
            }
        }
        findFood(searchQueue.poll());
    }
    public void makeMove(Coordinate coordinate) {
        buildPathToFood(coordinate);

    }
    public void buildPathToFood(Coordinate coordinate) {
        Coordinate startCoordinate = coordinate;
        pathToFood.add(targetCoordinate);
        Coordinate nextCoordinate = previousCoordinate.get(targetCoordinate);
        while (!(nextCoordinate.equals(startCoordinate))) {
            pathToFood.add(nextCoordinate);
            nextCoordinate = previousCoordinate.get(nextCoordinate);
        }
        System.out.println(pathToFood);
    }
}
