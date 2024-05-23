// 318936507 Adir Tamam
package Game;

import java.util.ArrayList;
import java.util.List;

import Geometry.Rectangle;
import Geometry.Line;
import Geometry.Point;
import Collidable.Collidable;
import Collidable.CollisionInfo;

/**
 * The GameEnvironment class represents the environment of the game, managing a list of collidable objects.
 */
public class GameEnvironment {
    private List<Collidable> collidableList;

    /**
     * Constructs a new GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * Returns the list of collidables managed by this GameEnvironment.
     *
     * @return The list of collidables in the game environment.
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param collidables The collidable object to be added.
     */

    public void setCollidables(List<Collidable> collidables) {
        this.collidableList = collidables;
    }

    /**
     * Adds a collidable object to the list of collidables in the game environment.
     *
     * @param c The Collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }



    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * Finds the closest collision point between a given trajectory and the collidables in the game environment.
     *
     * @param trajectory The trajectory to check for collisions.
     * @return A CollisionInfo object containing information about the closest collision,
     * or null if there are no collisions.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidableList.isEmpty()) {
            return null;
        }

        // Initialize arrays to store intersection points and distances
        int size = collidableList.size();
        int[] intersectionIndex = new int[size];
        int intersectionSize = 0;

        // Check each collidable for intersections with the trajectory
        for (int i = 0; i < size; i++) {
            Rectangle rect = collidableList.get(i).getCollisionRectangle();
            if (trajectory.closestIntersectionToStartOfLine(rect) != null) {
                intersectionIndex[intersectionSize] = i;
                intersectionSize++;
            }
        }

        // If no intersections found, return null
        if (intersectionSize == 0) {
            return null;
        }

        // Retrieve intersection points
        Point[] intersections = new Point[intersectionSize];
        for (int i = 0; i < intersectionSize; i++) {
            Rectangle rect = collidableList.get(intersectionIndex[i]).getCollisionRectangle();
            intersections[i] = trajectory.closestIntersectionToStartOfLine(rect);
        }

        // Find the closest intersection to the start of the trajectory
        double[] distances = new double[intersectionSize];
        int index = 0;
        Point start = trajectory.start();
        for (int i = 0; i < intersectionSize; i++) {
            distances[i] = start.distance(intersections[i]);
            if (distances[i] < distances[index]) {
                index = i;
            }
        }

        // Construct and return a CollisionInfo object with information about the closest collision
        Point collisionPoint = intersections[index];
        Collidable collisionObject = collidableList.get(intersectionIndex[index]);
        return new CollisionInfo(collisionPoint, collisionObject);
    }


}