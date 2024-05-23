// 318936507 Adir Tamam
package Base;

/**
 * The Counter class represents a simple counter that can be increased, decreased,
 * and queried for its current count.
 */
public class Counter {
    private int number;

    /**
     * Constructs a new Counter with an initial count of zero.
     */
    public Counter() {
        this.number = 0;
    }

    /**
     * Increases the current count by the specified amount.
     *
     * @param number The value to increase the count by.
     */
    public void increase(int number) {
        this.number += number;
    }

    /**
     * Decreases the current count by the specified amount. If the specified
     * amount is greater than the current count, the count becomes zero.
     *
     * @param number The value to decrease the count by.
     */
    public void decrease(int number) {
        this.number -= number;
    }

    /**
     * Returns the current count.
     *
     * @return The current count.
     */
    public int getValue() {
        return this.number;
    }

    /**
     * Returns a string representation of the current count.
     *
     * @return The string representation of the current count.
     */
    public String toString() {
        return String.valueOf(this.number);
    }
}
