package br.inatel.cdg.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class CalculadoraIMC {
    private static final Logger logger = LoggerFactory.getLogger(CalculadoraIMC.class);

    public static double calcularIMC(double peso, double altura) {
        if (altura <= 0) {
            throw new IllegalArgumentException("Altura deve ser maior que zero");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso deve ser maior que zero");
        }
        return peso / (altura * altura);
    }

    public static String classificarIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        logger.info("=== Calculadora de IMC ===");

        System.out.print("Digite o seu peso (kg): ");
        double peso = sc.nextDouble();

        System.out.print("Digite a sua altura (m): ");
        double altura = sc.nextDouble();

        try {
            double imc = calcularIMC(peso, altura);
            String classificacao = classificarIMC(imc);

            logger.info("Seu IMC é: {}", imc);

            switch (classificacao) {
                case "Abaixo do peso":
                case "Sobrepeso":
                    logger.warn("Classificação: {}", classificacao);
                    break;
                case "Obesidade":
                    logger.error("Classificação: {}", classificacao);
                    break;
                default:
                    logger.info("Classificação: {}", classificacao);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Erro: {}", e.getMessage());
        }

        sc.close();
    }
}
