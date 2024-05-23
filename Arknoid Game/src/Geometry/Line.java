// 318936507 Adir Tamam

package Geometry;

import java.util.List;


/**
 * Represents a line segment defined by two points in a 2D space.
 */
public class Line {
    private final Point start;
    private final Point end;

    static final double EPSILON = 0.0000001;


    /**
     * Constructs a line segment with specified start and end points.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line segment with specified coordinates for the start and end points.
     *
     * @param x1 The x-coordinate of the start point.
     * @param y1 The y-coordinate of the start point.
     * @param x2 The x-coordinate of the end point.
     * @param y2 The y-coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates and returns the length of the line.
     *
     * @return The length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return The middle point of the line.
     */
    public Point middle() {
        double mx = (start.getX() + end.getX()) / 2.0;
        double my = (start.getY() + end.getY()) / 2.0;
        return new Point(mx, my);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return The starting point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return The ending point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * Checks if two lines intersect.
     *
     * @param other The other line to check for intersection.
     * @return {@code true} if the lines intersect, {@code false} otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) {
            return true;
        }
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        if (isVertical(this) || isVertical(other)) {
            if ((isVertical(this)) && (inRange(x1, x2, x3, x4)) && (inRange(y1, y2, y3, y4))) {
                return true;
            } else if ((isVertical(other)) && (inRange(x1, x2, x3, x4)) && (inRange(y1, y2, y3, y4))) {
                return true;
            }
            return false;
        }
        double m1 = (y2 - y1) / (x2 - x1);
        double b1 = y1 - m1 * x1;
        double m2 = (y4 - y3) / (x4 - x3);
        double b2 = y3 - m2 * x3;
        if (doubleEqual(m1, m2) && doubleEqual(b1, b2)) {
            return inRange(x1, x2, x3, x4) && inRange(y1, y2, y3, y4);
        } else if (!doubleEqual(m1, m2)) {
            double intersectionX = (b2 - b1) / (m1 - m2);
            double intersectionY = m1 * intersectionX + b1;
            Point intersection = new Point(intersectionX, intersectionY);
            return (checkIntersection(intersection, this) && checkIntersection(intersection, other));
        } else {
            return false;
        }
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 The first line to check for intersection.
     * @param other2 The second line to check for intersection.
     * @return {@code true} if the lines intersect, {@code false} otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (isIntersecting(other1) && isIntersecting(other2)) {
            if ((intersectionWith(other1) != null) && (intersectionWith(other2) != null)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a point is within a specified range on the x-axis.
     *
     * @param x1 The x-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param x3 The x-coordinate of the third point.
     * @param x4 The x-coordinate of the fourth point.
     * @return {@code true} if the point is within the range, {@code false} otherwise.
     */
    public boolean inRange(double x1, double x2, double x3, double x4) {
        if (Math.max(x3, x4) - EPSILON <= Math.max(x1, x2) && Math.min(x1, x2) <= Math.max(x3, x4) + EPSILON) {
            return true;
        } else if (Math.max(x1, x2) - EPSILON <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2) + EPSILON) {
            return true;
        }
        return false;
    }


    /**
     * Checks if a line is vertical.
     *
     * @param other The line to check for verticality.
     * @return {@code true} if the line is vertical, {@code false} otherwise.
     */

    public boolean isVertical(Line other) {
        if (doubleEqual(other.start.getX(), other.end.getX())) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the slope of a line.
     *
     * @param other The line to calculate the slope for.
     * @return The slope of the line.
     */
    public double calcSlope(Line other) {
        double x1 = other.start.getX();
        double y1 = other.start.getY();
        double x2 = other.end.getX();
        double y2 = other.end.getY();
        return (y2 - y1) / (x2 - x1);
    }


    /**
     * Calculates the intersection point with another line.
     *
     * @param other The other line to find the intersection point with.
     * @return The intersection point if the lines intersect, or {@code null} otherwise.
     */
    public Point intersectionWith(Line other) {
        // if they are the same line and the line have different start and end points infinite intersection
        if (this.equals(other) && !this.start.equals(this.end())) {
            return null;
        }
        // if they don't intersect
        if (!isIntersecting(other)) {
            return null;
            // both not vertical they have defined slope
        } else if (!isVertical(this) && (!isVertical(other))) {
            double m1 = calcSlope(this);
            double m2 = calcSlope(other);
            if (doubleEqual(m1, m2)) {
                return sameSlopeCheck(this, other);
            }
            double b1 = this.start.getY() - m1 * this.start.getX();
            double b2 = other.start.getY() - m2 * other.start.getX();
            double intersectionX = (b2 - b1) / (m1 - m2);
            double intersectionY = m1 * intersectionX + b1;
            Point intersection = new Point(intersectionX, intersectionY);
            if (checkIntersection(intersection, this) && checkIntersection(intersection, other)) {
                return intersection;
            } else {
                return null;
            }
        } else if (!isVertical(this) && (isVertical(other))) {
            double m1 = calcSlope(this);
            double b1 = this.start.getY() - m1 * this.start.getX();
            double intersectionX = other.start.getX();
            double intersectionY = m1 * intersectionX + b1;
            return new Point(intersectionX, intersectionY);
        } else if (isVertical(this) && !(isVertical(other))) {
            double m1 = calcSlope(other);
            double b1 = other.start.getY() - m1 * other.start.getX();
            double intersectionX = this.start.getX();
            double intersectionY = m1 * intersectionX + b1;
            return new Point(intersectionX, intersectionY);
        } else {
            return sameSlopeCheck(this, other);
        }
    }

    /**
     * Checks for intersection points when two lines have the same slope.
     *
     * @param l1 The first line for comparison.
     * @param l2 The second line for comparison.
     * @return The intersection point if found, or {@code null} if there is no intersection.
     */

    // l1 is the trajectory of the ball
    public Point sameSlopeCheck(Line l1, Line l2) {
        double intersectionX;
        double intersectionY;
        double x1 = l1.start().getX();
        double y1 = l1.start().getY();
        double x2 = l1.end().getX();
        double y2 = l1.end().getY();
        double x3 = l2.start().getX();
        double y3 = l2.start().getY();
        double x4 = l2.end().getX();
        double y4 = l2.end().getY();
        intersectionX = getIntersectionSlope(x1, x2, x3, x4);
        intersectionY = getIntersectionSlope(y1, y2, y3, y4);
        return new Point(intersectionX, intersectionY);
    }


    /**
     * Calculates the intersection point on the x-axis of two line segments defined by their x-coordinates.
     * Assumes that either x1 or x2 is in the range of x3 and x4.
     *
     * @param x1 The x-coordinate of the first endpoint of the first line segment.
     * @param x2 The x-coordinate of the second endpoint of the first line segment.
     * @param x3 The x-coordinate of the first endpoint of the second line segment.
     * @param x4 The x-coordinate of the second endpoint of the second line segment.
     * @return The x-coordinate of the intersection point on the x-axis.
     */
    public double getIntersectionSlope(double x1, double x2, double x3, double x4) {
        // the function assumes that x1 or x2 will be in the range of x3 and x4
        if (x3 < x4) {
            // Both x1 and x2 are in the range [x3, x4]
            if (tripleRange(x3, x1, x4) && tripleRange(x3, x2, x4)) {
                if (Math.abs(x3 - x1) < Math.abs(x3 - x2)) {
                    return x1;
                }
                return x2;
            }

            // Only x1 is in the range [x3, x4]
            if (tripleRange(x3, x1, x4) && !tripleRange(x3, x2, x4)) {
                return x1;
            }

            // Only x2 is in the range [x3, x4]
            if (!tripleRange(x3, x1, x4) && tripleRange(x3, x2, x4)) {
                return x2;
            }
        }

        // in case x3 equals x4 or x4 is less than x3
        return x3;
    }


    /**
     * Checks if a given value falls within a range, allowing for a small epsilon difference.
     *
     * @param x1 The lower bound of the range.
     * @param x2 The value to check against the range.
     * @param x3 The upper bound of the range.
     * @return {@code true} if x2 is within the range [x1 - EPSILON, x3 + EPSILON], {@code false} otherwise.
     */
    public boolean tripleRange(double x1, double x2, double x3) {
        // Check if x2 is within the range [x1 - EPSILON, x3 + EPSILON]
        if (x1 - EPSILON <= x2 && x2 <= x3 + EPSILON) {
            return true;
        }
        // x2 is outside the range [x1 - EPSILON, x3 + EPSILON]
        return false;
    }


    /**
     * Checks if a point intersects with a given line segment.
     *
     * @param p1 The point to check for intersection.
     * @param l1 The line segment to check against.
     * @return {@code true} if the point intersects with the line segment, {@code false} otherwise.
     */
    public boolean checkIntersection(Point p1, Line l1) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double l1x1 = l1.start().getX();
        double l1y1 = l1.start().getY();
        double l1x2 = l1.end().getX();
        double m = l1.calcSlope(l1);
        double b = l1y1 - m * l1x1;
        // x1,y1 is the point that needs to be on the line
        double y2 = m * x1 + b;

        if ((Math.min(l1x1, l1x2)) - EPSILON <= x1 && (x1 <= Math.max(l1x1, l1x2) + EPSILON)) {
            if (Math.abs(y2 - y1) < EPSILON) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if two double values are approximately equal within a small epsilon value.
     *
     * @param x The first double value.
     * @param y The second double value.
     * @return {@code true} if the values are approximately equal, {@code false} otherwise.
     */
    public boolean doubleEqual(double x, double y) {
        return (Math.abs(Math.abs(x) - Math.abs(y)) < EPSILON);

    }

    /**
     * Checks if two lines are equal by comparing their start and end points.
     *
     * @param other The other line to compare for equality.
     * @return {@code true} if the lines are equal, {@code false} otherwise.
     */
    public boolean equals(Line other) {
        Point p1 = other.start;
        Point p2 = other.end;
        return (start.equals(p1) && end.equals(p2)) || (start.equals(p2) && end.equals(p1));
    }

    /**
     * for the lines of the rectangles checks if a given point lies on the line segment.
     *
     * @param point The point to check against the line segment.
     * @return {@code true} if the point lies on the line segment, {@code false} otherwise.
     */
    public boolean onLine(Point point) {
        // Extract coordinates of start and end points
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        // Check for horizontal line in the rectangle
        if (x1 == x2 && Math.abs(point.getX() - x1) < EPSILON) {
            // Check if the point's y-coordinate is within the range [min(y1, y2) - EPSILON, max(y1, y2) + EPSILON]
            if (Math.min(y1, y2) - EPSILON <= point.getY() && point.getY() <= Math.max(y1, y2) + EPSILON) {
                return true;
            }
        } else if (y1 == y2 && Math.abs(point.getY() - y1) < EPSILON) {
            // Check if the point's x-coordinate is within the range [min(x1, x2) - EPSILON, max(x1, x2) + EPSILON]
            if (Math.min(x1, x2) - EPSILON <= point.getX() && point.getX() <= Math.max(x1, x2) + EPSILON) {
                return true;
            }
        }

        // The point does not lie on the line segment
        return false;
    }


    /**
     * Finds the closest intersection point between the line segment and a given rectangle.
     *
     * @param rect The rectangle to check for intersection with the line segment.
     * @return The closest intersection point to the start point of the line segment,
     * or {@code null} if there is no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Get a list of intersection points between the line segment and the rectangle
        List<Point> intersectionPoints = rect.intersectionPoints(this);

        // If no intersection points are found, return null
        if (intersectionPoints.size() == 0) {
            return null;
        }

        // Calculate distances from the start point to each intersection point
        double[] distances = new double[intersectionPoints.size()];
        for (int i = 0; i < intersectionPoints.size(); i++) {
            distances[i] = this.start.distance(intersectionPoints.get(i));
        }

        // Find the index of the minimum distance
        int minIndex = 0;
        double min = distances[0];
        for (int i = 0; i < intersectionPoints.size(); i++) {
            if (distances[i] < min) {
                min = distances[i];
                minIndex = i;
            }
        }

        // Return the closest intersection point to the start point
        return intersectionPoints.get(minIndex);
    }

}


