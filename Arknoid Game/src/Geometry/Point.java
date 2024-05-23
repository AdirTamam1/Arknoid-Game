// 318936507 Adir Tamam

package Geometry;

/**
 * Represents a point in a two-dimensional coordinate system.
 */
public class Point {
    private final double x;
    private final double y;

    static final double EPSILON = 0.0000001;

    /**
     * Constructs a new point with the specified coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates and returns the Euclidean distance between this point and another point.
     *
     * @param other The other point for calculating the distance.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt((x - other.getX()) * (x - other.getX()) + (y - other.getY()) * (y - other.getY()));
    }

    /**
     * Checks if two points are approximately equal within a small epsilon value.
     *
     * @param other The other point to compare for equality.
     * @return {@code true} if the points are equal, {@code false} otherwise.
     */
    public boolean equals(Point other) {

        return Math.abs(x - other.getX()) < EPSILON && Math.abs(y - other.getY()) < EPSILON;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return The x-coordinate of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return The y-coordinate of this point.
     */
    public double getY() {
        return y;
    }

}