package org.example.dto;

public class CalculationRequest {

    private String a;
    private String b;

    // Construtor vazio: o Jackson precisa dele para criar o objeto
    // antes de preencher os campos via setters
    public CalculationRequest() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}