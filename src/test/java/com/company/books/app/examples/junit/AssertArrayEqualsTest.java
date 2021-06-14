package com.company.books.app.examples.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AssertArrayEqualsTest {

    @Test
    void pruebaArregloIguales() {
        String[] arr1 = {"a", "b"};
        String[] arr2 = {"a", "b"};
        String[] arr3 = {"a", "b", "c"};

        assertArrayEquals(arr1, arr2);
        //assertArrayEquals(arr1, arr3);
        //assertArrayEquals(arr2, arr3);
    }
}
