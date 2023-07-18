package utility;

import entities.*;
import utility.Coordinate;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private static Map<Coordinate, Entity> worldMap = new HashMap<>();

    public static void addEntity(Map<Coordinate, Entity> entityMap) {
        worldMap.putAll(entityMap);
    }
    public static void addEntity(Coordinate coordinate, Entity entity) {
        worldMap.put(coordinate, entity);
        System.out.println(worldMap);
    }

    public static void removeEntity (Coordinate coordinate) {
        if (worldMap.get(coordinate) instanceof Fruit) {
            Fruit.fruitsCounter--;
        } else if (worldMap.get(coordinate) instanceof Herbivore) {
            Herbivore.herbivoresCounter--;
        } else if (worldMap.get(coordinate) instanceof Predator) {
            Predator.predatorsCounter--;
        }
        worldMap.put(coordinate, new Ground());
    }
    public static void fillWorldMap() {
        while (Fruit.fruitsCounter < Fruit.MAX_FRUITS) {
            addEntity(Coordinate.getRandomFreeCoordinate(), new Fruit());
        }
        while (Herbivore.herbivoresCounter < Herbivore.MAX_HERBIVORES) {
            addEntity(Coordinate.getRandomFreeCoordinate(), new Herbivore());
        }
        while (Predator.predatorsCounter < Predator.MAX_PREDATORS) {
            addEntity(Coordinate.getRandomFreeCoordinate(), new Predator());
        }
    }
    public static Map<Coordinate, Entity> getWorldMap() {
        return worldMap;
    }
}
