package com.company.books.app.examples.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssertTrueFalseTeoriaTest {

    @Test
    void test1() {
        assertTrue(true);
        assertFalse(false);
    }

    @Test
    void test2() {
        boolean expression = 4 == 4;
        boolean expression2 = 4 == 3;
        assertTrue(expression);
        assertFalse(expression2);
    }
}
