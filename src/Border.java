import java.util.HashMap;
import java.util.Map;

public class Border extends Entity {
    private String border = "\uD83C\uDF0A";

    @Override
    public String toString() {
        return border;
    }
    Map<Coordinate, Entity> borderMap = new HashMap<>();

    @Override
    public Map<Coordinate, Entity> createEntity() {
        for (int i = 0; i < Coordinate.MAX_X; i++) {
            for (int j = 0; j < Coordinate.MAX_Y; j++) {
                if (i == 0 || j == 0 || i == Coordinate.MAX_X - 1 || j == Coordinate.MAX_Y - 1) {
                    borderMap.put(new Coordinate(i, j), new Border());
                }
            }
        }
        return borderMap;
    }
}
