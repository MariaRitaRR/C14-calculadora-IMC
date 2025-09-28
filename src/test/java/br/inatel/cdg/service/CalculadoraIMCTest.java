package br.inatel.cdg.service;

import br.inatel.cdg.service.CalculadoraIMC;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    void testClassificarIMC_Obesidade() {
        String classificacao = CalculadoraIMC.classificarIMC(35.0);
        assertEquals("Obesidade", classificacao, "IMC 35.0 deve ser classificado como 'Obesidade'");
    }

    @Test
    void testClassificarIMC_LimiteInferiorPesoNormal() {
        String classificacao = CalculadoraIMC.classificarIMC(18.5);
        assertEquals("Peso normal", classificacao, "IMC 18.5 deve ser classificado como 'Peso normal'");
    }

    @Test
    void testClassificarIMC_LimiteSuperiorPesoNormal() {
        String classificacao = CalculadoraIMC.classificarIMC(24.9);
        assertEquals("Peso normal", classificacao, "IMC 24.9 deve ser classificado como 'Peso normal'");
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
        assertEquals("Obesidade", classificacao, "IMC muito alto deve ser classificado como 'Obesidade'");
    }

    @Test
    void testClassificarIMC_LimiteSobrepeso() {
        String classificacao = CalculadoraIMC.classificarIMC(29.9);
        assertEquals("Sobrepeso", classificacao, "IMC 29.9 deve ser classificado como 'Sobrepeso'");
    }

    @Test
    void testClassificarIMC_LimiteObesidade() {
        String classificacao = CalculadoraIMC.classificarIMC(30.0);
        assertEquals("Obesidade", classificacao, "IMC 30.0 deve ser classificado como 'Obesidade'");
    }
}

