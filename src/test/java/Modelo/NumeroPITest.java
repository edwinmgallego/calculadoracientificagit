/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author eljul
 */
public class NumeroPITest {

    public static void main(String[] args) {
        double piAprox = NumeroPI.aproximarPi(1000000);
        String resultado = String.format("%.5f", piAprox);
        System.out.println("Resultado del test: " + resultado);

        // Comprobamos si está dentro de un margen de error razonable
        double valorRealPi = Math.PI;
        double diferencia = Math.abs(valorRealPi - piAprox);

        if (diferencia < 0.001) {
            System.out.println("✅ TEST APROBADO: La aproximación es correcta.");
        } else {
            System.out.println("❌ TEST FALLÓ: La aproximación no es precisa.");
        }
    }
}
