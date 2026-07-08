package org.example;

import org.example.service.Calculadora;
import java.util.Scanner;

public class App {
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

            // 1ª Cláusula de Guarda: Se for 5, sai imediatamente
            if (opcao == 5) {
                System.out.println("\nObrigado por usar a calculadora!");
                break;
            }

            // 2ª Cláusula de Guarda : Trata a exceção logo no topo
            if (opcao < 1 || opcao > 4) {
                System.out.println("⚠️ Essa opção não existe! Escolha um número entre 1 a 5.");
                continue; // 🔄 Volta para o início do 'while', ignorando tudo o que está abaixo
            }

            String valor = null;

            // --- PRIMEIRO NÚMERO ---
            System.out.print("Digite o primeiro número: ");
            while (!leitor.hasNextDouble()) {
                System.out.print("⚠️ Entrada inválida! Digite apenas números: ");
                valor = leitor.next();
            }
            valor = leitor.next();
            double num1 = Double.parseDouble(valor);

            // --- SEGUNDO NÚMERO ---
            double num2 = 0;
            while (true) {
                System.out.print("Digite o segundo número: ");

                while (!leitor.hasNextDouble()) {
                    System.out.print("⚠️ Entrada inválida! Digite apenas números: ");
                    leitor.next();
                }
                valor = leitor.next();
                num2 = Double.parseDouble(valor);

                if (opcao == 4 && num2 == 0) {
                    System.out.println("❌ Erro Matemático: Não é possível dividir por zero. Tente outro valor!");
                } else {
                    break;
                }
            }

            System.out.println("\n--- Processando Resultado ---");

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

        leitor.close();
        System.out.println("--- Programa Encerrado ---");
    }
}