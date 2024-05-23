// 318936507 Adir Tamam
package Base;

import Collidable.Block;
import Sprites.Ball;

/**
 * The HitListener interface represents an object that listens for hit events.
 * Objects implementing this interface will be notified when a collision occurs
 * between a block and a ball in the game.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit The block that was hit.
     * @param hitter   The ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
