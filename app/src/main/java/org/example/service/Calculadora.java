package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calculadora {

    private static final Logger logger = LogManager.getLogger(Calculadora.class);

    public double soma(double a, double b) {
        logger.info("Realizando operação de soma entre {} e {}", a, b);
        return a + b;
    }

    public double subtracao(double a, double b) {
        logger.info("Realizando operação de subtração entre {} e {}", a, b);
        return a - b;
    }

    public double multiplicacao(double a, double b) {
        logger.info("Realizando operação de multiplicação entre {} e {}", a, b);
        return a * b;
    }

    public double divisao(double a, double b) {
        if (b == 0) {
            logger.error("Tentativa de divisão por zero abortada. Numerador: {}", a);
            throw new IllegalArgumentException("Erro: Não é possível dividir por zero!");
        }
        logger.info("Realizando operação de divisão entre {} e {}", a, b);
        return a / b;
    }
}