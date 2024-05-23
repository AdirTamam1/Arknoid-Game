// 318936507 Adir Tamam
package Base;

import Collidable.Block;

import Sprites.Ball;

/**
 * The ScoreTrackingListener class is responsible for tracking scores when a block is hit.
 * It implements the HitListener interface to respond to hit events.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener with the specified score counter.
     *
     * @param scoreCounter The Counter to track the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called when a block is hit. It increases the current score by 5.
     *
     * @param beingHit The block that was hit.
     * @param hitter   The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}

