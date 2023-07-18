package entities;

import utility.Coordinate;

import java.util.HashMap;
import java.util.Map;

public class Ground extends Entity {
    private String ground = "\uD83C\uDF3F";
    private static Map<Coordinate, Entity> groundMap = new HashMap<>();

    @Override
    public String toString() {
        return ground;
    }

    @Override
    public Map<Coordinate, Entity> createEntity() {
        for (int i = 0; i < Coordinate.MAX_X; i++) {
            for (int j = 0; j < Coordinate.MAX_Y; j++) {
                if (!(i == 0) && !(j == 0) && !(i == Coordinate.MAX_X - 1) && !(j == Coordinate.MAX_Y - 1)) {
                    groundMap.put(new Coordinate(i, j), new Ground());
                }
            }
        }
        return groundMap;
    }
}
