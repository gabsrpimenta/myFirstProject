package org.example;

import org.example.service.Calculadora;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        Calculadora calc = new Calculadora();
        Scanner leitor = new Scanner(System.in);

        int opcao = 0;

        System.out.println("--- CALCULADORA VIA TERMINAL ---");

        while (opcao != 5) {

            System.out.println("\nEscolha a operação desejada:");
            System.out.println("1 - Soma (+)");
            System.out.println("2 - Subtração (-)");
            System.out.println("3 - Multiplicação (*)");
            System.out.println("4 - Divisão (/)");
            System.out.println("5 - Sair ❌");
            System.out.print("Digite o número da opção: ");

            while (!leitor.hasNextInt()) {
                System.out.print("⚠️ Opção inválida! Digite um número de 1 a 5: ");
                leitor.next();
            }
            opcao = leitor.nextInt();

            if (opcao == 5) {
                System.out.println("\nObrigado por usar a calculadora!");
                break;
            }

            if (opcao < 1 || opcao > 4) {
                System.out.println("⚠️ Essa opção não existe! Escolha um número entre 1 a 5.");
                continue;
            }

            // --- PRIMEIRO NÚMERO ---
            System.out.print("Digite o primeiro número: ");
            double num1 = getNextDouble(leitor);

            // --- SEGUNDO NÚMERO ---
            double num2 = 0;
            while (true) {
                System.out.print("Digite o segundo número: ");
                num2 = getNextDouble(leitor);

                if (opcao == 4 && num2 == 0) {
                    System.out.println("❌ Erro Matemático: Não é possível dividir por zero. Tente outro valor!");
                } else {
                    break;
                }
            }

            logger.info("Processando Resultado para a opção: {}", opcao);

            executarEExibirOperacao(opcao, num1, num2, calc);
        }

        leitor.close();
        System.out.println("--- Programa Encerrado ---");
    }

    private static void executarEExibirOperacao(int opcao, double num1, double num2, Calculadora calc) {
        String nomeOperacao = "";
        double resultado = 0;

        if (opcao == 1) {
            nomeOperacao = "Soma";
            resultado = calc.soma(num1, num2);
        } else if (opcao == 2) {
            nomeOperacao = "Subtração";
            resultado = calc.subtracao(num1, num2);
        } else if (opcao == 3) {
            nomeOperacao = "Multiplicação";
            resultado = calc.multiplicacao(num1, num2);
        } else if (opcao == 4) {
            nomeOperacao = "Divisão";
            resultado = calc.divisao(num1, num2);
        }

        System.out.println("✅ O resultado da " + nomeOperacao + " é: " + resultado);
    }

    private static double getNextDouble(Scanner leitor) {
        String valor;
        while (!leitor.hasNextDouble()) {
            System.out.print("⚠️ Entrada inválida! Digite apenas números: ");
            valor = leitor.next();
        }
        valor = leitor.next();
        return Double.parseDouble(valor);
    }
}