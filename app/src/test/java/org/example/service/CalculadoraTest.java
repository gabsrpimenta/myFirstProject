package org.example.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    private final Calculadora calc = new Calculadora();

    @Test
    void testSoma() {
        assertEquals(15.0, calc.soma(10, 5), "A soma de 10 + 5 deve ser 15.0");
        assertEquals(0.0, calc.soma(-5, 5), "A soma de -5 + 5 deve ser 0.0");
    }

    @Test
    void testSubtracao() {
        assertEquals(5.0, calc.subtracao(10, 5), "A subtração de 10 - 5 deve ser 5.0");
        assertEquals(-3.0, calc.subtracao(2, 5), "A subtração de 2 - 5 deve ser -3.0");
    }

    @Test
    void testMultiplicacao() {
        assertEquals(50.0, calc.multiplicacao(10, 5), "A multiplicação de 10 * 5 deve ser 50.0");
        assertEquals(0.0, calc.multiplicacao(10, 0), "Qualquer número multiplicado por 0 deve ser 0.0");
    }

    @Test
    void testDivisao() {
        assertEquals(2.0, calc.divisao(10, 5), "A divisão de 10 / 5 deve ser 2.0");
        assertEquals(2.5, calc.divisao(5, 2), "A divisão de 5 / 2 deve ser 2.5");
    }

    @Test
    void testDivisaoPorZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.divisao(10, 0);
        }, "Dividir por zero deve lançar uma exceção");
    }
}