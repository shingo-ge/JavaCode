package test;

import junitlearn.MyFactorial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyFactorialTest {
    @Test
    void testFact() {
        assertEquals(1, MyFactorial.factorialCalculation(1));
        assertEquals(6,MyFactorial.factorialCalculation(3));
        assertEquals(3628800,MyFactorial.factorialCalculation(10));
    }
}