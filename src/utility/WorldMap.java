package utility;

import utility.Coordinate;
import entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private static Map<Coordinate, Entity> worldMap = new HashMap<>();

    public static void addEntity(Map<Coordinate, Entity> entityMap) {
        worldMap.putAll(entityMap);
    }
    public static void addEntity(Coordinate coordinate, Entity entity) {
        worldMap.put(coordinate, entity);
    }
    public void checkEntity(Coordinate coordinate, Entity entity) {
        //worldMap.get(coordinate) instanceof entity.getClass();
    }
    public void removeEntity (Coordinate coordinate) {

    }
    public static Map<Coordinate, Entity> getWorldMap() {
        return worldMap;
    }
}
