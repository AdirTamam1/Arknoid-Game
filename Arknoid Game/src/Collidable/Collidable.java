// 318936507 Adir Tamam
package Collidable;

import Geometry.Rectangle;
import Geometry.Point;
import Sprites.Ball;
import Sprites.Velocity;


/**
 * The Collidable interface represents an object that can be involved in collisions.
 * Implementing classes are required to provide methods for retrieving their collision rectangle
 * and handling collision events.
 */
public interface Collidable {

    /**
     * Returns the collision rectangle (collision shape) of the object.
     *
     * @return The collision rectangle of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified collision point
     * with a given velocity. The method returns the new velocity expected after the hit.
     *
     * @param hitter           The ball that collided with the object.
     * @param collisionPoint   The point of collision with the object.
     * @param currentVelocity  The current velocity of the colliding object.
     * @return The new velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
