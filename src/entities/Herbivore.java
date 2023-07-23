package entities;

import utility.Coordinate;
import utility.WorldMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Herbivore extends Creature {
    public static final int MAX_HERBIVORES = 4;
    public static int herbivoresCounter = -1;
    private String herbivore = "\uD83D\uDC2D";
    private static Map<Coordinate, Entity> herbivoreMap = new HashMap<>();





    public Herbivore(int hp) {
        super(hp, 1);
        herbivoresCounter++;
    }
    public Herbivore() {
        super(10, 1);
        herbivoresCounter++;
    }

    @Override
    public String toString() {
        return herbivore;
    }

    @Override
    public Map<Coordinate, Entity> createEntity() {
        Coordinate randomCoordinate;
        while (herbivoresCounter < MAX_HERBIVORES) {
            randomCoordinate = Coordinate.getRandomFreeCoordinate();
            herbivoreMap.put(randomCoordinate, new Herbivore());
        }
        return herbivoreMap;
    }

    public static Map<Coordinate, Entity> getHerbivoreMap() {
        return herbivoreMap;
    }

    @Override
    public void eat(Coordinate coordinate, Coordinate targetCoordinate) {
        WorldMap.removeEntity(coordinate);
        WorldMap.removeEntity(targetCoordinate);
        WorldMap.addEntity(targetCoordinate, new Herbivore());
    }
    public void reduceHP (int attackPower) {
        setHp(getHp() - attackPower);
    }
}
