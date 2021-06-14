package com.company.books.app.examples.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    void calculadoraAssertEqualTest() {
        var calculadora = new Calculadora();

        assertEquals(2, calculadora.sumar(1, 1));
        assertEquals(3, calculadora.restar(4, 1));
        assertEquals(9, calculadora.multiplicar(3, 3));
        assertEquals(2, calculadora.dividir(4, 2));
    }

    @Test
    void calculadoraTrueFalse() {
        var calculadora = new Calculadora();
        
        assertTrue(calculadora.sumar(1, 1) == 2);
        assertTrue(calculadora.restar(2, 1) == 1);
        assertFalse(calculadora.multiplicar(3, 3) == 8);
        assertFalse(calculadora.dividir(4, 2) == 1);
    }
}
