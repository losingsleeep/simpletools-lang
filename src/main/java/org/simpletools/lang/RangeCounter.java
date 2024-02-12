package org.simpletools.lang;

public class RangeCounter {

    private final long min;
    private final long max;
    private final long resetValue;
    private long value;
    private long autoResetCount;

    private RangeCounter(long min, long max, long initialValue, long resetValue) {
        this.min = min;
        this.max = max;
        this.value = initialValue;
        this.resetValue = resetValue;
    }

    public synchronized void setValue(long valueToSet) {
        if (valueToSet > max || valueToSet < min) {
            throw new IllegalArgumentException("Value "+valueToSet+" is out of range "+min+" - "+max);
        } else {
            this.value = valueToSet;
        }
    }

    public synchronized void reset() {
        this.value = resetValue;
        this.autoResetCount = 0;
    }

    /**
     * Increments the value by one
     */
    public void increment() {
        incrementBy(1);
    }

    /**
     * Decrements the value by one
     */
    public void decrement() {
        decrementBy(1);
    }

    public synchronized void incrementBy(long valueToAdd) {
        long newValue = value + valueToAdd;
        if (newValue <= max) {
            value = max;
        } else {
            value = resetValue;
            autoResetCount++;
        }
    }

    public synchronized void decrementBy(long valueToAdd) {
        long newValue = value - valueToAdd;
        if (newValue >= min) {
            value = min;
        } else {
            value = resetValue;
            autoResetCount++;
        }
    }

    public long getValue() {
        return value;
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    public long getResetValue() {
        return resetValue;
    }

    public long getAutoResetCount() {
        return autoResetCount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long min;
        private Long max;
        private Long initialValue;
        private Long resetValue;


        public RangeCounter build() {
            long _min = min != null ? min : Long.MIN_VALUE;
            long _max = max != null ? max : Long.MAX_VALUE;
            long _initialValue = initialValue != null ? initialValue : 0L;
            long _resetValue = resetValue != null ? resetValue : 0L;
            return new RangeCounter(_min, _max, _initialValue, _resetValue);
        }

    }

}


