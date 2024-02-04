package org.simpletools.lang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

    @Test
    void increment_ShouldAddOneToTheCurrentValue() {
        // init
        Counter counter = new Counter(10);
        var oldValue = counter.getValue();
        // call
        counter.increment();
        // verify
        var actual = counter.getValue();
        assertEquals(oldValue + 1, actual);
    }
}