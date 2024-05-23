// 318936507 Adir Tamam
package Sprites;

import Collidable.CollisionInfo;
import Collidable.Collidable;
import Game.GameEnvironment;
import Game.Game;
import biuoop.DrawSurface;
import Geometry.Rectangle;
import Geometry.Line;
import Geometry.Point;

import java.awt.Color;


/**
 * The Ball class represents a ball in a two-dimensional space.
 */
public class Ball implements Sprite {
    // The center point of the ball
    private Point point;

    // The radius of the ball
    private final int size;

    // The color of the ball
    private java.awt.Color color;

    // The velocity of the ball
    private Velocity velocity;

    private final GameEnvironment gameEnvironment;

    /**
     * Constructor for creating a new Ball instance.
     *
     * @param x               The x-coordinate of the ball's center.
     * @param y               The y-coordinate of the ball's center.
     * @param r               The radius of the ball.
     * @param color           The color of the ball.
     * @param gameEnvironment the game environment
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.point = new Point(x, y);
        this.size = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Get the x-coordinate of the ball's center.
     *
     * @return The x-coordinate of the ball's center.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * Get the y-coordinate of the ball's center.
     *
     * @return The y-coordinate of the ball's center.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * Get the size (radius) of the ball.
     *
     * @return The size (radius) of the ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Get the color of the ball.
     *
     * @return The color of the ball.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get the velocity of the ball.
     *
     * @return The velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Set the velocity of the ball.
     *
     * @param v The new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the color of the object.
     *
     * @param color The Color to set for the object.
     */
    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * Set the velocity of the ball given its components.
     *
     * @param dx The change in x-coordinate.
     * @param dy The change in y-coordinate.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Move the ball one step according to its velocity.
     */
    public void moveOneStep() {
        Line trajectory = trajectory();
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.point = this.getVelocity().applyToPoint(this.point);
            // if the moving paddle is hiding the ball
        } else if (isInRec(collisionInfo.getCollisionObject().getCollisionRectangle())) {
            this.point = new Point(this.point.getX(), this.point.getY() - 50);
            setVelocity(collisionInfo.getCollisionObject().hit(this, collisionInfo.getCollisionPoint(), this.velocity));
        } else {
            Point collisionPoint = collisionInfo.getCollisionPoint();
            double dx = this.getVelocity().getDx();
            double dy = this.getVelocity().getDy();
            double x = collisionPoint.getX();
            double y = collisionPoint.getY();
            if (dx > 0) {
                x = x - 1;
            }
            if (dx < 0) {
                x = x + 1;
            }
            if (dy > 0) {
                y = y - 1;
            }
            if (dy < 0) {
                y = y + 1;
            }
            this.point = new Point(x, y);
            notifyME(collisionInfo);
            // if there is a sequence of collisions to make the animation look smooth it will move the ball further
            // so that the next step will make the look like it moves in the same speed
            collisionInfo = gameEnvironment.getClosestCollision(trajectory);
            if (collisionInfo != null) {
                moveOneStep();
            }

        }
    }


    /**
     * Notifies the object about a collision and updates its velocity accordingly.
     *
     * @param collisionInfo Information about the collision, including the colliding object and collision point.
     */
    public void notifyME(CollisionInfo collisionInfo) {
        // Retrieve the collidable object involved in the collision
        Collidable collisionObject = collisionInfo.getCollisionObject();

        // Update the velocity of the current object based on the collision
        this.setVelocity(collisionObject.hit(this, collisionInfo.getCollisionPoint(), this.velocity));
    }


    /**
     * Calculates the trajectory of the object based on its current velocity.
     *
     * @return A line representing the trajectory of the object from its current position to the next position.
     */
    public Line trajectory() {
        // Calculate the next point based on the current velocity
        Point nextPoint = this.getVelocity().applyToPoint(this.point);

        // Create a line representing the trajectory from the current position to the next position
        return new Line(this.point, nextPoint);
    }


    /**
     * Checks if the current point is within the boundaries of a given rectangle.
     *
     * @param rec The rectangle to check against for containment.
     * @return {@code true} if the current point is inside the specified rectangle, {@code false} otherwise.
     */
    /**
     * Checks if the current point is within the boundaries of a given rectangle.
     *
     * @param rec The rectangle to check against for containment.
     * @return {@code true} if the current point is inside the specified rectangle,
     * {@code false} otherwise.
     */
    private boolean isInRec(Rectangle rec) {
        // Extract coordinates of the current point and the upper-left corner of the rectangle
        double x = this.point.getX();
        double y = this.point.getY();
        double upperLeftX = rec.getUpperLeft().getX();
        double upperLeftY = rec.getUpperLeft().getY();

        // Check if the current point is within the boundaries of the rectangle
        return (x >= upperLeftX && x <= upperLeftX + rec.getWidth()
                && y >= upperLeftY && y <= upperLeftY + rec.getHeight());
    }


    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface The DrawSurface on which to draw the ball.
     */
    public void drawOn(DrawSurface surface) {
        // Set the color of the drawing surface to the color of the ball
        surface.setColor(this.getColor());

        // Draw a filled circle representing the ball on the surface
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }

    /**
     * Handles the passage of time by moving the object one step.
     */
    public void timePassed() {
        moveOneStep();
    }


    /**
     * Adds the current sprite to the specified game.
     *
     * @param game The game to which the sprite is added.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Removes the current sprite from the specified game.
     *
     * @param g The Game object from which the sprite will be removed.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }


}
