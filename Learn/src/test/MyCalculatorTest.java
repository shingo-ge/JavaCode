package test;

import junitlearn.MyCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCalculatorTest {
    MyCalculator myCalculator;
    @BeforeEach
    public void setUp(){
        this.myCalculator = new MyCalculator();
    }
    @AfterEach
    public void remove(){
        this.myCalculator = null;
    }
    @Test
    void addTest(){
        assertEquals(100,myCalculator.add(100));
        assertEquals(150,myCalculator.add(50));
        assertEquals(130,myCalculator.add(-20));
    }
    @Test
    void subTest(){
        assertEquals(-100,myCalculator.sub(100));
        assertEquals(-150,myCalculator.sub(50));
        assertEquals(-50,myCalculator.sub(-100));
    }
}