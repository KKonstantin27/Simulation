package entities;

import utility.Coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Predator extends Creature {
    public static int predatorsCounter = -1;
    private String predator = "\uD83D\uDC31";
    private static Map<Coordinate, Entity> predatorMap = new HashMap<>();
//    Stack<Coordinate> path = new Stack<>(); aaaaaaaaaaaaaaa
    public Predator(int hp) {
        super(hp, 2);
        predatorsCounter++;
    }

    public Predator() {
        super(40, 2);
        predatorsCounter++;
    }

    @Override
    public Map<Coordinate, Entity> createEntity() {
        Coordinate randomCoordinate;
        while (predatorsCounter < 2){
            randomCoordinate = Coordinate.getRandomFreeCoordinate();
            predatorMap.put(randomCoordinate, new Predator());
        }
        return predatorMap;
    }




    @Override
    public String toString() {
        return predator;
    }
}
