// 318936507 Adir Tamam
package Collidable;

import Geometry.Point;

/**
 * The CollisionInfo class represents information about a collision between a ball and a collidable object.
 * It stores the collision point and the collidable object involved in the collision.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint  The point at which the collision occurred.
     * @param collisionObject The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the collision point where the collision occurred.
     *
     * @return The collision point.
     */
    public Point getCollisionPoint() {
        return collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return The collidable object.
     */
    public Collidable getCollisionObject() {
        return collisionObject;
    }


}
