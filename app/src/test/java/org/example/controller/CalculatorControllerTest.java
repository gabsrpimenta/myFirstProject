package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveSomarDoisNumerosValidos() throws Exception {
        mockMvc.perform(post("/v1/calculator/sum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": \"10\", \"b\": \"5\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").value(15.0));
    }

    @Test
    void deveSubtrairDoisNumerosValidos() throws Exception {
        mockMvc.perform(post("/v1/calculator/subtract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": \"10\", \"b\": \"5\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").value(5.0));
    }

    @Test
    void deveMultiplicarDoisNumerosValidos() throws Exception {
        mockMvc.perform(post("/v1/calculator/multiply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": \"10\", \"b\": \"5\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").value(50.0));
    }

    @Test
    void deveDividirDoisNumerosValidos() throws Exception {
        mockMvc.perform(post("/v1/calculator/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": \"10\", \"b\": \"5\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").value(2.0));
    }

    @Test
    void deveRetornar400QuandoDivisorForZero() throws Exception {
        mockMvc.perform(post("/v1/calculator/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": \"10\", \"b\": \"0\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Erro: Não é possível dividir por zero!"));
    }

    @Test
    void deveRetornar400QuandoNumeroForInvalido() throws Exception {
        mockMvc.perform(post("/v1/calculator/sum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": \"abc\", \"b\": \"5\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Os valores informados devem ser números válidos."));
    }

    @Test
    void deveRetornar400QuandoAtributoForNulo() throws Exception {
        mockMvc.perform(post("/v1/calculator/sum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": \"10\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }
}