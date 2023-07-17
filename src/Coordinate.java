import java.util.Random;

public class Coordinate {
    public static final int MAX_X = 10;
    public static final int MAX_Y = 10;
    private int x;
    private int y;
    private Random random = new Random();

    public Coordinate getRandomFreeCoordinate () {
        return new Coordinate(random.nextInt(MAX_X), random.nextInt(MAX_Y));
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
}
