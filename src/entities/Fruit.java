package entities;

import utility.Coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Fruit extends Entity {
    public static final int MAX_FRUITS = 10;
    public static int fruitsCounter = -1;
    private Random random = new Random();
    private String fruit;
    private static Map<Coordinate, Entity> fruitMap = new HashMap<>();
    public Fruit() {
        int number = random.nextInt(3);
        if (number == 0) {
            fruit = "\uD83C\uDF53";
        } else if (number == 1) {
            fruit = "\uD83C\uDF52";
        } else {
            fruit = "\uD83C\uDF47";
        }
        fruitsCounter++;
    }

    @Override
    public String toString() {
        return fruit;
    }

    @Override
    public Map<Coordinate, Entity> createEntity() {
        Coordinate randomCoordinate;
        while (fruitsCounter < MAX_FRUITS) {
            randomCoordinate = Coordinate.getRandomFreeCoordinate();
            fruitMap.put(randomCoordinate, new Fruit());
        }
        return fruitMap;
    }
}
