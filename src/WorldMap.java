import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private Map<Coordinate, Entity> worldMap = new HashMap<>();

    public void addEntity(Coordinate coordinate, Entity entity) {
        worldMap.put(coordinate, entity);
    }
    public void checkEntity() {

    }
    public void removeEntity () {

    }
}
