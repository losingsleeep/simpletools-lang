package org.simpletools.lang;

public class Counter {

    private final long max;
    private long value;

    public Counter(long max) {
        this.max = max;
    }

    /**
     * Increments the value by one
     */
    public void increment() {
        incrementBy(1);
    }

    public synchronized void incrementBy(long valueToAdd) {
        long newValue = value + valueToAdd;
        value = newValue <= max ? newValue : 0;
    }

    public void reset() {
        this.value = 0;
    }

    public long getMax() {
        return max;
    }

    public long getValue() {
        return value;
    }

}
