package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.service.Calculadora;

class AppTest {

    @Test
    void testSomaCalculadora() {
        Calculadora calc = new Calculadora();
        assertEquals(15.0, calc.soma(10, 5), "A soma de 10 + 5 deveria ser 15.0");
    }

    @Test
    void testDivisaoPorZero() {
        Calculadora calc = new Calculadora();
        assertThrows(IllegalArgumentException.class, () -> {
            calc.divisao(10, 0);
        });
    }
}