package br.inatel.cdg.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CalculadoraIMCTest {
    // ========== TESTES POSITIVOS ========== //
    @Test
    void testCalcularIMC_PesoNormal() {
        double imc = CalculadoraIMC.calcularIMC(70, 1.75);
        assertEquals(22.86, imc, 0.01, "IMC deve ser 22.86 para  70kg e 1.75m");
    }

    @Test
    void testCalcularIMC_AbaixoPeso() {
        double imc = CalculadoraIMC.calcularIMC(50, 1.75);
        assertEquals(16.33, imc, 0.01, "IMC deve ser 16.33 para 50kg e 1.75m");
    }

    @Test
    void testCalcularIMC_Sobrepeso() {
        double imc = CalculadoraIMC.calcularIMC(80, 1.75);
        assertEquals(26.12, imc, 0.01, "IMC deve ser 26.12 para 80kg e 1.75m");
    }

    @Test
    void testCalcularIMC_Obesidade() {
        double imc = CalculadoraIMC.calcularIMC(100, 1.75);
        assertEquals(32.65, imc, 0.01, "IMC deve ser 32.65 para 100kg e 1.75m");
    }

    @Test
    void testClassificarIMC_AbaixoPeso() {
        String classificacao = CalculadoraIMC.classificarIMC(17.5);
        assertEquals("Abaixo do peso", classificacao, "IMC 17.5 deve ser classificado como 'Abaixo do peso'");
    }

    @Test
    void testClassificarIMC_PesoNormal() {
        String classificacao = CalculadoraIMC.classificarIMC(22.0);
        assertEquals("Peso normal", classificacao, "IMC 22.0 deve ser classificado como 'Peso normal'");
    }

    @Test
    void testClassificarIMC_Sobrepeso() {
        String classificacao = CalculadoraIMC.classificarIMC(27.5);
        assertEquals("Sobrepeso", classificacao, "IMC 27.5 deve ser classificado como 'Sobrepeso'");
    }

    // ========== TESTES NEGATIVOS ========== //

    @Test
    void testCalcularIMC_AlturaZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraIMC.calcularIMC(70, 0);
        });
        assertEquals("Altura deve ser maior que zero", exception.getMessage());
    }

    @Test
    void testCalcularIMC_AlturaNegativa() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraIMC.calcularIMC(70, -1.75);
        });
        assertEquals("Altura deve ser maior que zero", exception.getMessage());
    }

    @Test
    void testCalcularIMC_PesoZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraIMC.calcularIMC(0, 1.75);
        });
        assertEquals("Peso deve ser maior que zero", exception.getMessage());
    }

    @Test
    void testCalcularIMC_PesoNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraIMC.calcularIMC(-70, 1.75);
        });
        assertEquals("Peso deve ser maior que zero", exception.getMessage());
    }

    @Test
    void testCalcularIMC_AlturaMuitoPequena() {
        double imc = CalculadoraIMC.calcularIMC(50, 0.01);
        assertTrue(imc > 0, "IMC deve ser positivo mesmo com altura muito pequena");
    }

    @Test
    void testClassificarIMC_ValorNegativo() {
        String classificacao = CalculadoraIMC.classificarIMC(-5.0);
        assertEquals("Abaixo do peso", classificacao, "IMC negativo deve ser classificado como 'Abaixo do peso'");
    }

    @Test
    void testClassificarIMC_ValorZero() {
        String classificacao = CalculadoraIMC.classificarIMC(0.0);
        assertEquals("Abaixo do peso", classificacao, "IMC zero deve ser classificado como 'Abaixo do peso'");
    }

    @Test
    void testClassificarIMC_ValorMuitoAlto() {
        String classificacao = CalculadoraIMC.classificarIMC(1000.0);
        assertEquals("Obesidade Grau III (Mórbida)", classificacao, "IMC muito alto deve ser classificado como 'Obesidade Grau III (Mórbida)'");
    }

    @Test
    void testClassificarIMC_LimiteSobrepeso() {
        String classificacao = CalculadoraIMC.classificarIMC(29.9);
        assertEquals("Sobrepeso", classificacao, "IMC 29.9 deve ser classificado como 'Sobrepeso'");
    }

    // ========== TESTES PARA AS CATEGORIAS DE OBESIDADE ========== //

    @Test
    void testClassificarIMC_ObesidadeGrauI() {
        String classificacao = CalculadoraIMC.classificarIMC(32.0);
        assertEquals("Obesidade Grau I", classificacao, "IMC 32.0 deve ser classificado como 'Obesidade Grau I'");
    }

    @Test
    void testClassificarIMC_ObesidadeGrauII() {
        String classificacao = CalculadoraIMC.classificarIMC(37.5);
        assertEquals("Obesidade Grau II", classificacao, "IMC 37.5 deve ser classificado como 'Obesidade Grau II'");
    }

    @Test
    void testClassificarIMC_ObesidadeGrauIII() {
        String classificacao = CalculadoraIMC.classificarIMC(45.0);
        assertEquals("Obesidade Grau III (Mórbida)", classificacao, "IMC 45.0 deve ser classificado como 'Obesidade Grau III (Mórbida)'");
    }

    @Test
    void testClassificarIMC_LimiteInferiorObesidadeGrauI() {
        String classificacao = CalculadoraIMC.classificarIMC(30.0);
        assertEquals("Obesidade Grau I", classificacao, "IMC 30.0 deve ser classificado como 'Obesidade Grau I'");
    }

    @Test
    void testClassificarIMC_LimiteSuperiorObesidadeGrauI() {
        String classificacao = CalculadoraIMC.classificarIMC(34.9);
        assertEquals("Obesidade Grau I", classificacao, "IMC 34.9 deve ser classificado como 'Obesidade Grau I'");
    }

    @Test
    void testClassificarIMC_LimiteInferiorObesidadeGrauII() {
        String classificacao = CalculadoraIMC.classificarIMC(35.0);
        assertEquals("Obesidade Grau II", classificacao, "IMC 35.0 deve ser classificado como 'Obesidade Grau II'");
    }

    @Test
    void testClassificarIMC_LimiteSuperiorObesidadeGrauII() {
        String classificacao = CalculadoraIMC.classificarIMC(39.9);
        assertEquals("Obesidade Grau II", classificacao, "IMC 39.9 deve ser classificado como 'Obesidade Grau II'");
    }

    @Test
    void testClassificarIMC_LimiteInferiorObesidadeGrauIII() {
        String classificacao = CalculadoraIMC.classificarIMC(40.0);
        assertEquals("Obesidade Grau III (Mórbida)", classificacao, "IMC 40.0 deve ser classificado como 'Obesidade Grau III (Mórbida)'");
    }

    // ========== TESTES COM MOCK ========== //

    @Test
    void testMain_InputValido() {
        String input = "70\n1.75\n";
        java.io.InputStream originalIn = System.in;
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        try {
            CalculadoraIMC.main(new String[]{});
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testLogger_ErroAlturaZero() {
        Logger mockLogger = mock(Logger.class);
        double peso = 70;
        double altura = 0;

        try {
            CalculadoraIMC.calcularIMC(peso, altura);
        } catch (IllegalArgumentException e) {
            mockLogger.error("Erro: {}", e.getMessage());
        }

        verify(mockLogger).error("Erro: {}", "Altura deve ser maior que zero");
    }

}
