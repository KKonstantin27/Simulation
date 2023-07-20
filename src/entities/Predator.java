package entities;

import utility.Coordinate;
import utility.WorldMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Predator extends Creature {
    public static final int MAX_PREDATORS = 1;
    public static int predatorsCounter = -1;
    private String predator = "\uD83D\uDC31";
    private int attackPower;
    private static Map<Coordinate, Entity> predatorMap = new HashMap<>();

    public Predator(int hp) {
        super(hp, 2);
        attackPower = 10;
        predatorsCounter++;
    }

    public Predator() {
        super(40, 2);
        predatorsCounter++;
    }

    @Override
    public Map<Coordinate, Entity> createEntity() {
        Coordinate randomCoordinate;
        while (predatorsCounter < MAX_PREDATORS){
            randomCoordinate = Coordinate.getRandomFreeCoordinate();
            predatorMap.put(randomCoordinate, new Predator());
        }
        return predatorMap;
    }

    @Override
    public String toString() {
        return predator;
    }

    public static Map<Coordinate, Entity> getPredatorMap() {
        return predatorMap;
    }


    @Override
    public void eat(Coordinate coordinate, Coordinate targetCoordinate) {
        Herbivore prey = (Herbivore) WorldMap.getWorldMap().get(targetCoordinate);
        if (prey.getHp() <= attackPower) {
            WorldMap.removeEntity(coordinate);
            WorldMap.removeEntity(targetCoordinate);
            Creature.removeCreatureFromMap(targetCoordinate);
            WorldMap.addEntity(targetCoordinate, new Predator());
        } else {
            prey.reduceHP(attackPower);
        }
    }
}
