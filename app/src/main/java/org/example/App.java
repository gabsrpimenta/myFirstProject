package org.example;

import org.example.service.Calculadora;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Calculadora calc = new Calculadora();
        Scanner leitor = new Scanner(System.in);

        System.out.println("CALCULADORA TERMINAL");

        System.out.println("Escolha a operação desejada:");
        System.out.println("1. Soma (+)");
        System.out.println("2. Subtração (-)");
        System.out.println("3. Multiplicação (*)");
        System.out.println("4. Divisão (/)");
        System.out.print("Digite o número da opção: ");

        int opcao = leitor.nextInt();

        System.out.print("Digite o primeiro número: ");
        double num1 = leitor.nextDouble();

        System.out.print("Digite o segundo número: ");
        double num2 = leitor.nextDouble();

        System.out.println("\n--- Processando Resultado ---");

        if (opcao == 1) {
            double resultado = calc.soma(num1, num2);
            System.out.println("Resultado da Soma: " + resultado);
        }
        else if (opcao == 2) {
            double resultado = calc.subtracao(num1, num2);
            System.out.println("Resultado da Subtração: " + resultado);
        }
        else if (opcao == 3) {
            double resultado = calc.multiplicacao(num1, num2);
            System.out.println("Resultado da Multiplicação: " + resultado);
        }
        else if (opcao == 4) {
            double resultado = calc.divisao(num1, num2);
            System.out.println("Resultado da Divisão: " + resultado);
        }
        else {
            System.out.println("⚠️ Opção inválida! Reinicie o programa e escolha de 1 a 4.");
        }

        leitor.close();
        System.out.println("Programa Encerrado");
    }
}