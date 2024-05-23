// 318936507 Adir Tamam
package Collidable;

import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import Geometry.Rectangle;
import Geometry.Line;
import Geometry.Point;
import Sprites.Ball;
import Game.Game;

import java.awt.Color;

/**
 * The Paddle class represents a player-controlled paddle in a game.
 * It implements both the Sprite and Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rectangle;
    private final Color color;

    private final double widthBound;
    private int velocity;
    private biuoop.KeyboardSensor keyboard;

    /**
     * Constructs a new Paddle with the specified parameters.
     *
     * @param rectangle  The rectangular shape defining the paddle.
     * @param color      The color of the paddle.
     * @param widthBound The width bound of the screen.
     * @param velocity   The initial velocity of the paddle.
     * @param keyboard   The keyboard sensor used to control the paddle.
     */
    public Paddle(Rectangle rectangle, Color color, double widthBound, int velocity, biuoop.KeyboardSensor keyboard) {
        this.rectangle = rectangle;
        this.color = color;
        this.widthBound = widthBound;
        this.velocity = velocity; // Consider using the provided velocity parameter
        this.keyboard = keyboard;
    }


    /**
     * Moves the paddle to the left, updating its position based on the current velocity.
     * The paddle's position is adjusted to stay within the screen bounds.
     */
    public void moveLeft() {
        // Calculate the next rectangle position
        Rectangle nextRec = new Rectangle(new Point(rectangle.getUpperLeft().getX() - this.velocity,
                rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());

        // Check if the next position is within the screen bounds
        if (nextRec.getUpperLeft().getX() >= 0) {
            this.rectangle = nextRec;
        } else {
            // If not, move the paddle to the rightmost position (wrap around)
            nextRec = new Rectangle(new Point(widthBound - rectangle.getWidth(), rectangle.getUpperLeft().getY()),
                    rectangle.getWidth(), rectangle.getHeight());
            this.rectangle = nextRec;
        }
    }


    /**
     * Moves the paddle to the right, updating its position based on the current velocity.
     * The paddle's position is adjusted to stay within the screen bounds.
     */
    public void moveRight() {
        // Calculate the next rectangle position
        Rectangle nextRec = new Rectangle(new Point(rectangle.getUpperLeft().getX() + this.velocity,
                rectangle.getUpperLeft().getY()), rectangle.getWidth(), rectangle.getHeight());

        // Check if the next position is within the screen bounds
        if (nextRec.getUpperLeft().getX() + nextRec.getWidth() <= this.widthBound) {
            this.rectangle = nextRec;
        } else {
            // If not, move the paddle to the leftmost position (wrap around)
            nextRec = new Rectangle(new Point(0, rectangle.getUpperLeft().getY()),
                    rectangle.getWidth(), rectangle.getHeight());
            this.rectangle = nextRec;
        }
    }

    /**
     * Sets the velocity of the paddle.
     *
     * @param velocity The new velocity to be set.
     */
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    // Sprite

    /**
     * Updates the paddle's position based on user input.
     * If the left key is pressed, the paddle moves to the left.
     * If the right key is pressed, the paddle moves to the right.
     */
    public void timePassed() {
        // Check if the left key is pressed and move the paddle left
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }

        // Check if the right key is pressed and move the paddle right
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }


    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param surface The DrawSurface on which the paddle is drawn.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);

        // Draw a filled rectangle representing the paddle
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());

        // Set color to black for drawing lines
        surface.setColor(Color.BLACK);

        // Draw the lines of the rectangle
        drawRectangleLines(surface);
    }

    private void drawRectangleLines(DrawSurface d) {
        drawLine(d, rectangle.getUpperLine());
        drawLine(d, rectangle.getBottomLine());
        drawLine(d, rectangle.getLeftLine());
        drawLine(d, rectangle.getRightLine());
    }


    /**
     * Draws the specified line on the given DrawSurface.
     *
     * @param d    The DrawSurface on which the line is drawn.
     * @param line The Line to be drawn.
     */
    public void drawLine(DrawSurface d, Line line) {
        // Extract coordinates from the line and draw it on the surface
        int x1 = (int) line.start().getX();
        int y1 = (int) line.start().getY();
        int x2 = (int) line.end().getX();
        int y2 = (int) line.end().getY();
        d.drawLine(x1, y1, x2, y2);
    }


    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return The collision rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * Returns the new velocity of the ball after hitting the paddle, based on the collision point.
     *
     * @param collision       The point of collision.
     * @param currentVelocity The current velocity of the ball.
     * @param hitter The ball that hit the block.
     * @return The new velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collision, Velocity currentVelocity) {
        double upperLeftX = rectangle.getUpperLeft().getX();
        double regionWidth = this.rectangle.getWidth() / 5;
        double region1 = upperLeftX + 1 * regionWidth;
        double region2 = upperLeftX + 2 * regionWidth;
        double region3 = upperLeftX + 3 * regionWidth;
        double region4 = upperLeftX + 4 * regionWidth;
        double region5 = upperLeftX + 5 * regionWidth;

        // Check which region the collision point is in and return a new velocity accordingly
        if (collision.getX() >= upperLeftX && collision.getX() <= region1) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (collision.getX() >= region1 && collision.getX() <= region2) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        if (collision.getX() >= region2 && collision.getX() <= region3) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        if (collision.getX() >= region3 && collision.getX() <= region4) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        if (collision.getX() >= region4 && collision.getX() <= region5) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }

        // Check if the collision point is on the left or right side
        // of the block, and return a new velocity accordingly.
        if (this.rectangle.getLeftLine().onLine(collision) || this.rectangle.getRightLine().onLine(collision)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }

        return currentVelocity;
    }


    // Add this paddle to the game.

    /**
     * Adds the paddle to the given game by registering it as both a collidable and a sprite.
     *
     * @param g The game to which the paddle is added.
     */
    public void addToGame(Game g) {
        // Register the paddle as a collidable and a sprite in the game
        g.addCollidable(this);
        g.addSprite(this);
    }

}