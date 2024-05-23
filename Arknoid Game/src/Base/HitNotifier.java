// 318936507 Adir Tamam
package Base;

/**
 * The HitNotifier interface represents an object that can notify listeners
 * about hit events. Objects implementing this interface allow the addition
 * and removal of HitListeners to receive notifications about hits.
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners to hit events.
     *
     * @param hl The HitListener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners to hit events.
     *
     * @param hl The HitListener to be removed.
     */
    void removeHitListener(HitListener hl);
}

