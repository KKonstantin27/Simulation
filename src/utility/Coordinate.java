package utility;

import entities.Entity;
import entities.Ground;


import java.util.Map;
import java.util.Random;

public class Coordinate {
    public static final int MAX_X = 12;
    public static final int MAX_Y = 17;
    private int x;
    private int y;
    private static Random random = new Random();

    public static Coordinate getRandomFreeCoordinate () {
        Map<Coordinate, Entity> worldMap = WorldMap.getWorldMap();
        Coordinate randomCoordinate;
        do {
            randomCoordinate = new Coordinate(random.nextInt(MAX_X), random.nextInt(MAX_Y));
        } while (!(worldMap.get(randomCoordinate) instanceof Ground));
        return randomCoordinate;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " : " + y;
    }
}
