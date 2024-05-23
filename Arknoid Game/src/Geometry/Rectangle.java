// 318936507 Adir Tamam

package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a geometric rectangle defined by an upper-left point,
 * width, and height. It also includes lines representing the four sides of the rectangle.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private final Line upperLine;
    private final Line leftLine;
    private final Line rightLine;
    private final Line bottomLine;

    /**
     * Constructs a new Rectangle with the specified upper-left point, width, and height.
     * The constructor also initializes lines representing the four sides of the rectangle.
     *
     * @param upperLeft The upper-left point of the rectangle.
     * @param width     The width of the rectangle.
     * @param height    The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.upperLine = new Line(upperLeft, upperRight);
        this.leftLine = new Line(upperLeft, bottomLeft);
        this.rightLine = new Line(upperRight, bottomRight);
        this.bottomLine = new Line(bottomLeft, bottomRight);
    }


    /**
     * Finds and returns a list of intersection points between this rectangle and a given line.
     *
     * @param line The line with which intersections are checked.
     * @return A list of intersection points, or an empty list if there are no intersections.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] lines = new Line[]{this.upperLine, this.leftLine, this.rightLine, this.bottomLine};
        List<Point> intersectionPoints = new ArrayList<>();
        for (Line value : lines) {
            Point intersectionPoint = line.intersectionWith(value);
            if (intersectionPoint != null) {
                intersectionPoints.add(intersectionPoint);
            }
        }
        return intersectionPoints;
    }


    // Return the width and height of the rectangle

    /**
     * Returns the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return The upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper line of the rectangle.
     *
     * @return The upper line of the rectangle.
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return The left line of the rectangle.
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return The right line of the rectangle.
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * Returns the bottom line of the rectangle.
     *
     * @return The bottom line of the rectangle.
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }

}