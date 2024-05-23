// 318936507 Adir Tamam
package Base;

import Collidable.Block;
import Sprites.Ball;
import Game.Game;


/**
 * This class is responsible for removing blocks that are hit in the game.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor for the BlockRemover class.
     *
     * @param game            The game from which blocks will be removed.
     * @param remainingBlocks Counter tracking the number of remaining blocks in the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the hit event for blocks. Removes the hit block from the game,
     * removes the HitListener from the block, and decreases the count of remaining blocks.
     * Additionally, sets the color of the hitter ball to the color of the beingHit block.
     *
     * @param beingHit The block that was hit.
     * @param hitter   The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // Set the color of the hitter ball to the color of the beingHit block (if needed).
        hitter.setColor(beingHit.getColor());

        // Remove the hit block from the game.
        beingHit.removeFromGame(this.game);

        // Remove this listener from the block.
        beingHit.removeHitListener(this);

        // Decrease the count of remaining blocks.
        this.remainingBlocks.decrease(1);
    }
}
