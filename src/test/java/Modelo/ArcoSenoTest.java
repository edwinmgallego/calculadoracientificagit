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
 * @author LENOVO
 */
public class ArcoSenoTest {
    
    @Test
    void testArcosenoValorMedio() {
        double resultado = ArcoSeno.calcularArcoSeno(0.5);
        assertEquals(0.5235987756, resultado, 0.0001);
    }

    @Test
    void testArcosenoValorLimite1() {
        double resultado = ArcoSeno.calcularArcoSeno(1.0);
        assertEquals(1.570796327, resultado, 0.0001);
    }

    @Test
    void testArcosenoValorLimiteMenos1() {
        double resultado = ArcoSeno.calcularArcoSeno(-1.0);
        assertEquals(-1.570796327, resultado, 0.0001);
    }
    
}
