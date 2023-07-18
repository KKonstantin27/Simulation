package entities;

import utility.Coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Herbivore extends Creature {
    public static int herbivoresCounter = -1;
    private String herbivore = "\uD83D\uDC2D";
    private static Map<Coordinate, Entity> herbivoreMap = new HashMap<>();

//    Stack<Coordinate> path = new Stack<>(); aaaaaaaaaaaaaaaaaa


    public Herbivore(int hp) {
        super(hp, 1);
        herbivoresCounter++;
    }
    public Herbivore() {
        super(20, 1);
        herbivoresCounter++;
    }

    @Override
    public String toString() {
        return herbivore;
    }

    @Override
    public Map<Coordinate, Entity> createEntity() {
        Coordinate randomCoordinate;
        while (herbivoresCounter < 1) {
            randomCoordinate = Coordinate.getRandomFreeCoordinate();
            herbivoreMap.put(randomCoordinate, new Herbivore());
        }
        return herbivoreMap;
    }

    public static Map<Coordinate, Entity> getHerbivoreMap() { // УБРАТЬ ПОТОМ!!!!!!!!!!!!!!
        return herbivoreMap;
    }
}
