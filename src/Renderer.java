import utility.Coordinate;
import entities.Entity;

import java.util.Map;

public class Renderer {
    public void renderWorld(Map<Coordinate, Entity> worldMap) {
        for (int i = 0; i < Coordinate.MAX_X; i++) {
            for (int j = 0; j < Coordinate.MAX_Y; j++) {
                System.out.print(worldMap.get(new Coordinate(i, j)));
            }
            System.out.println();
        }
    }
}
