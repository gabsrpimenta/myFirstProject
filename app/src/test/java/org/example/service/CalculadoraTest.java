package org.example.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    // Criamos uma instância da calculadora aqui em cima para usar em todos os testes
    private final Calculadora calc = new Calculadora();

    @Test
    void testSoma() {
        // Testa uma soma simples
        assertEquals(15.0, calc.soma(10, 5), "A soma de 10 + 5 deve ser 15.0");
        // Testa uma soma com números negativos
        assertEquals(0.0, calc.soma(-5, 5), "A soma de -5 + 5 deve ser 0.0");
    }

    @Test
    void testSubtracao() {
        // Testa uma subtração simples
        assertEquals(5.0, calc.subtracao(10, 5), "A subtração de 10 - 5 deve ser 5.0");
        // Testa um resultado que deve dar negativo
        assertEquals(-3.0, calc.subtracao(2, 5), "A subtração de 2 - 5 deve ser -3.0");
    }

    @Test
    void testMultiplicacao() {
        // Testa uma multiplicação simples
        assertEquals(50.0, calc.multiplicacao(10, 5), "A multiplicação de 10 * 5 deve ser 50.0");
        // Testa a multiplicação por zero
        assertEquals(0.0, calc.multiplicacao(10, 0), "Qualquer número multiplicado por 0 deve ser 0.0");
    }

    @Test
    void testDivisao() {
        // Testa uma divisão exata
        assertEquals(2.0, calc.divisao(10, 5), "A divisão de 10 / 5 deve ser 2.0");
        // Testa uma divisão que gera número quebrado
        assertEquals(2.5, calc.divisao(5, 2), "A divisão de 5 / 2 deve ser 2.5");
    }

    @Test
    void testDivisaoPorZero() {
        // Garante que o sistema joga o erro correto se o divisor for zero
        assertThrows(IllegalArgumentException.class, () -> {
            calc.divisao(10, 0);
        }, "Dividir por zero deve lançar uma exceção");
    }
}