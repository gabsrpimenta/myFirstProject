package org.example;

import org.example.service.Calculadora;

public class App {
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        // Cenários de teste
        System.out.println("--- Testando a Calculadora ---");
        System.out.println("Soma (10 + 5): " + calc.soma(10, 5));
        System.out.println("Subtração (10 - 5): " + calc.subtracao(10, 5));
        System.out.println("Multiplicação (10 * 5): " + calc.multiplicacao(10, 5));
        System.out.println("Divisão (10 / 5): " + calc.divisao(10, 5));

    }
}