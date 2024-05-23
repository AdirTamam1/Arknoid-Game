// 318936507 Adir Tamam
package Collidable;

import biuoop.DrawSurface;
import Geometry.Rectangle;
import Geometry.Line;
import Geometry.Point;

import java.awt.Color;

import Sprites.Sprite;
import Sprites.Velocity;
import Game.Game;
import Sprites.Ball;
import Base.HitNotifier;
import Base.HitListener;

import java.util.List;
import java.util.ArrayList;


/**
 * A block that implements both the Collidable and Sprite interfaces.
 * Represents a rectangular object with a specified color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private final Rectangle rectangle;
    private final Color color;

    /**
     * Constructs a Block with the specified rectangle and color.
     *
     * @param rectangle The rectangular shape defining the block.
     * @param color     The color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }


    /**
     * Returns the collision rectangle associated with this Block.
     *
     * @return The collision rectangle of the Block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * Returns the color of this Block.
     *
     * @return The color of the Block.
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * Handles a collision with the block and returns the new velocity after the collision.
     *
     * @param collisionPoint  The point of collision with the block.
     * @param currentVelocity The current velocity of the colliding object.
     * @param hitter          The ball that hit the block.
     * @return The new velocity after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        // Check if the collision point is on the bottom or upper line of the block
        if (rectangle.getBottomLine().onLine(collisionPoint) || rectangle.getUpperLine().onLine(collisionPoint)) {
            newVelocity.setDy(-newVelocity.getDy());
        }

        // Check if the collision point is on the left or right line of the block
        if (rectangle.getLeftLine().onLine(collisionPoint) || rectangle.getRightLine().onLine(collisionPoint)) {
            newVelocity.setDx(-newVelocity.getDx());
        }

        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        return newVelocity;
    }


    /**
     * Draws the block on a given drawing surface.
     *
     * @param surface The drawing surface on which the block is to be drawn.
     */
    public void drawOn(DrawSurface surface) {
        // Set the color of the drawing surface to the color of the rectangle
        surface.setColor(this.getColor());

        // Draw a filled rectangle
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());

        // Set the color to BLACK for drawing lines
        surface.setColor(Color.BLACK);

        // Draw all four sides of the rectangle using the drawLine method
        drawRectangleLines(surface);
    }


    /**
     * Draws the lines of the rectangle on the specified DrawSurface.
     * This method draws the upper, bottom, left, and right lines of the rectangle.
     *
     * @param d The DrawSurface on which the rectangle lines will be drawn.
     */
    private void drawRectangleLines(DrawSurface d) {
        drawLine(d, rectangle.getUpperLine());
        drawLine(d, rectangle.getBottomLine());
        drawLine(d, rectangle.getLeftLine());
        drawLine(d, rectangle.getRightLine());
    }

    /**
     * Draws a line on the specified DrawSurface.
     *
     * @param d    The DrawSurface on which the line will be drawn.
     * @param line The Line object representing the line to be drawn.
     */
    private void drawLine(DrawSurface d, Line line) {
        int x1 = (int) line.start().getX();
        int y1 = (int) line.start().getY();
        int x2 = (int) line.end().getX();
        int y2 = (int) line.end().getY();
        d.drawLine(x1, y1, x2, y2);
    }



    /**
     * Empty implementation for the timePassed method.
     * (No actions are performed in this method.)
     */
    public void timePassed() {

    }


    /**
     * Adds this Block to the specified game, making it both a sprite and a collidable.
     * The block will be rendered on the screen, and it will participate in collision detection and resolution.
     *
     * @param game The Game to which this Block will be added.
     */
    public void addToGame(Game game) {
        // Add this Block as a sprite to be rendered.
        game.addSprite(this);

        // Add this Block as a collidable to participate in collision detection and resolution.
        game.addCollidable(this);
    }

    /**
     * Removes this Block from the specified game, removing it as both a sprite and a collidable.
     * The block will no longer render, and it won't participate in collision detection and resolution.
     *
     * @param game The Game from which this Block will be removed.
     */
    public void removeFromGame(Game game) {
        // Remove this Block as a sprite to stop rendering.
        game.removeSprite(this);

        // Remove this Block as a collidable to exclude it from collision detection and resolution.
        game.removeCollidable(this);
    }


    /**
     * Checks if the color of the ball matches the color of this Block.
     *
     * @param ball The Ball to check for color match.
     * @return True if colors match, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().getRGB() == this.getColor().getRGB();
    }

    /**
     * Adds a HitListener to the list of listeners for hit events.
     *
     * @param hl The HitListener to be added.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a HitListener from the list of listeners for hit events.
     *
     * @param hl The HitListener to be removed.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all HitListeners about a hit event with the specified hitter.
     *
     * @param hitter The Ball that hit this Block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


}


