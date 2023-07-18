import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private Map<Coordinate, Entity> worldMap = new HashMap<>();

    public void addEntity(Map<Coordinate, Entity> entityMap) {
        worldMap.putAll(entityMap);
    }
    public void checkEntity(Coordinate coordinate, Entity entity) {
        worldMap.get(coordinate) instanceof entity.getClass();
    }
    public void removeEntity (Coordinate coordinate) {

    }
}
