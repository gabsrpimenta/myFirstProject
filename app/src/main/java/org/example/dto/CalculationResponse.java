package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculationResponse {

    private boolean success;
    private Double result;
    private String message;

    public CalculationResponse() {
    }

    // Construtor de conveniência para resposta de SUCESSO
    public static CalculationResponse success(double result) {
        CalculationResponse response = new CalculationResponse();
        response.success = true;
        response.result = result;
        return response;
    }

    // Construtor de conveniência para resposta de ERRO
    public static CalculationResponse error(String message) {
        CalculationResponse response = new CalculationResponse();
        response.success = false;
        response.message = message;
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public Double getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}