// 318936507 Adir Tamam
package Sprites;

import biuoop.DrawSurface;


/**
 * This interface represents a sprite in the game.
 * Sprites can be drawn on a DrawSurface and are notified when time has passed.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d The DrawSurface on which the sprite is drawn.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is called by the game to update the sprite's state.
     */
    void timePassed();
}
