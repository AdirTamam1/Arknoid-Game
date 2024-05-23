// 318936507 Adir Tamam
package Base;

import Collidable.Block;
import Game.Game;
import Sprites.Ball;


/**
 * The BallRemover class is responsible for removing balls that hit blocks
 * from the game, updating the game state and the remainingBalls counter.
 * It implements the HitListener interface to respond to hit events.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemover with the specified game and remainingBalls counter.
     *
     * @param game            The game from which balls should be removed.
     * @param remainingBalls  The counter tracking the remaining balls in the game.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Handles the hit event when a block is hit by a ball. Removes the ball from the game
     * and decreases the remainingBalls counter. It is essential to remove this listener
     * from the block that is being removed from the game to avoid memory leaks.
     *
     * @param beingHit  The block that is being hit.
     * @param hitter    The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove the ball from the game
        this.game.getSpriteCollection().getSprites().remove(hitter);

        // Decrease the remainingBalls counter
        this.remainingBalls.decrease(1);
    }
}
