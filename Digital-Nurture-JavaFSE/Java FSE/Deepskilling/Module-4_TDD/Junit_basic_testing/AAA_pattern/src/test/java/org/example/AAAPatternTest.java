package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AAAPatternTest {

    private int number1;
    private int number2;

    @BeforeEach
    public void setUp() {
        System.out.println("Setup Method Executed");
        number1 = 10;
        number2 = 20;
    }


    @Test
    public void testAddition() {


        int expected = 30;


        int actual = number1 + number2;

        assertEquals(expected, actual);
    }

    @Test
    public void testGreaterNumber() {


        boolean expected = true;


        boolean actual = number2 > number1;


        assertEquals(expected, actual);
    }
    @AfterEach
    public void tearDown() {
        System.out.println("Teardown Method Executed");
    }
}