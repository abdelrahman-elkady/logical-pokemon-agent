

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kady on 14/10/16.
 *
 * @author kady
 */
public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Location location = (Location) obj;

        return location.getX() == this.x && location.getY() == this.y;
    }

    // ============ Setters ============

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // ============ Getters ============

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // ============ Static Methods ============

    /**
     * Generates a new location with random coordinates
     * bounded within x and y max values
     *
     * @param upperX maximum x value
     * @param upperY maximum y value
     * @return Location
     */
    public static Location generateRandomLocation(int upperX, int upperY) {
        int x = ThreadLocalRandom.current().nextInt(0, upperX);
        int y = ThreadLocalRandom.current().nextInt(0, upperY);

        return new Location(x,y);
    }

}
