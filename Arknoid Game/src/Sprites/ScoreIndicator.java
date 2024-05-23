// 318936507 Adir Tamam
package Sprites;


import Base.Counter;
import biuoop.DrawSurface;
import Game.Game;

import java.awt.Color;

/**
 * A class representing a ScoreIndicator that implements the Sprite interface.
 * It displays the current score on the game screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructs a ScoreIndicator with the given Counter object.
     *
     * @param score The Counter object representing the score to be displayed.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the score indicator on the specified DrawSurface.
     *
     * @param d The DrawSurface on which the score indicator will be drawn.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.black);
        d.drawText(350, 15, "Score: " + this.score.toString(), 15);
    }

    /**
     * An empty implementation of the timePassed method, as it is not relevant for ScoreIndicator.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds the ScoreIndicator to the specified Game's sprite collection.
     *
     * @param game The Game object to which the ScoreIndicator will be added.
     */
    public void addToGame(Game game) {
        game.getSpriteCollection().getSprites().add(this);
    }
}
