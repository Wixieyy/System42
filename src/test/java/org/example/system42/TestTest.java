package org.example.system42;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestTest {
    //simple addition test
    @Test
    public void testAddition() {
        int a = 1;
        int b = 2;
        int sum = a + b;
        assertEquals(3, sum);
    }
}
