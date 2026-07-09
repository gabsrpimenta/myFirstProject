package org.example.controller;

import org.example.dto.CalculationRequest;
import org.example.dto.CalculationResponse;
import org.example.service.Calculadora;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/calculator")
public class CalculatorController {

    private final Calculadora calculadora = new Calculadora();

    @PostMapping("/sum")
    public ResponseEntity<CalculationResponse> sum(@RequestBody CalculationRequest request) {
        Double a = parseOrNull(request.getA());
        Double b = parseOrNull(request.getB());

        if (a == null || b == null) {
            CalculationResponse erro = CalculationResponse.error("Os valores informados devem ser números válidos.");
            return ResponseEntity.badRequest().body(erro);
        }

        double resultado = calculadora.soma(a, b);
        return ResponseEntity.ok(CalculationResponse.success(resultado));
    }

    @PostMapping("/subtract")
    public ResponseEntity<CalculationResponse> subtract(@RequestBody CalculationRequest request) {
        Double a = parseOrNull(request.getA());
        Double b = parseOrNull(request.getB());

        if (a == null || b == null) {
            CalculationResponse erro = CalculationResponse.error("Os valores informados devem ser números válidos.");
            return ResponseEntity.badRequest().body(erro);
        }

        double resultado = calculadora.subtracao(a, b);
        return ResponseEntity.ok(CalculationResponse.success(resultado));
    }

    @PostMapping("/multiply")
    public ResponseEntity<CalculationResponse> multiply(@RequestBody CalculationRequest request) {
        Double a = parseOrNull(request.getA());
        Double b = parseOrNull(request.getB());

        if (a == null || b == null) {
            CalculationResponse erro = CalculationResponse.error("Os valores informados devem ser números válidos.");
            return ResponseEntity.badRequest().body(erro);
        }

        double resultado = calculadora.multiplicacao(a, b);
        return ResponseEntity.ok(CalculationResponse.success(resultado));
    }

    @PostMapping("/divide")
    public ResponseEntity<CalculationResponse> divide(@RequestBody CalculationRequest request) {
        Double a = parseOrNull(request.getA());
        Double b = parseOrNull(request.getB());

        if (a == null || b == null) {
            CalculationResponse erro = CalculationResponse.error("Os valores informados devem ser números válidos.");
            return ResponseEntity.badRequest().body(erro);
        }

        try {
            double resultado = calculadora.divisao(a, b);
            return ResponseEntity.ok(CalculationResponse.success(resultado));
        } catch (IllegalArgumentException e) {
            CalculationResponse erro = CalculationResponse.error(e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }

    // Converte String para Double; retorna null se não for um número válido
    private Double parseOrNull(String valor) {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }
}