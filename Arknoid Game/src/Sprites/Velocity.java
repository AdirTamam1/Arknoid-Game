// 318936507 Adir Tamam

package Sprites;

import Geometry.Point;

/**
 * The Velocity class specifies the change in position on the `x` and the `y` axes.
 */

public class Velocity {
    // The change in position on the x-axis
    private double dx;

    // The change in position on the y-axis
    private double dy;

    /**
     * Constructor for creating a new Velocity instance.
     *
     * @param dx The change in position on the x-axis.
     * @param dy The change in position on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Create a Velocity instance from an angle and speed.
     *
     * @param angle The angle in degrees.
     * @param speed The speed of the velocity.
     * @return The Velocity instance calculated from the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = speed * -Math.cos(radians);
        return new Velocity(dx, dy);
    }

    /**
     * Get the change in position on the x-axis.
     *
     * @return The change in position on the x-axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get the change in position on the y-axis.
     *
     * @return The change in position on the y-axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the horizontal component of the velocity.
     *
     * @param dx The new value for the horizontal component of the velocity.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the vertical component of the velocity.
     *
     * @param dy The new value for the vertical component of the velocity.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Get the speed of the velocity.
     *
     * @return The speed of the velocity.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Take a point with position (x, y) and return a new point with position (x + dx, y + dy).
     *
     * @param p The point to which the velocity is applied.
     * @return The new point after applying the velocity.
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        return new Point(x, y);
    }
}
