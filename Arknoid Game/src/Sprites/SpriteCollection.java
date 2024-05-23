// 318936507 Adir Tamam

package Sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;


/**
 * The SpriteCollection class represents a collection of sprites in the game.
 * It provides methods to manage and interact with the collection of sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs a new SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Returns the list of sprites in the collection.
     *
     * @return The list of sprites in the collection.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param sprites The sprite to be added.
     */

    public void setSprites(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Adds a Sprite to the collection of sprites.
     *
     * @param s The Sprite object to be added to the collection.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the collection.
     * This method is typically used to update the state of the sprites as time passes in the game.
     */
    public void notifyAllTimePassed() {
        List<Sprite> list = new ArrayList<>(sprites);
        for (Sprite s : list) {
            s.timePassed();
        }
    }

    /**
     * Calls the drawOn(d) method on all sprites in the collection.
     * This method is used to draw all sprites on a given DrawSurface.
     *
     * @param d The DrawSurface on which the sprites are drawn.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
