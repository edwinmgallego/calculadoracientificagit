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
 * @author efrai
 */
public class ArcoCosenoTest {

    @Test
    void testArcocosenoValorMedio() {
        double resultado = ArcoCoseno.calcularArcoCoseno(0.5);
        assertEquals(1.047197551, resultado, 0.0001);
    }

    @Test
    void testArcocosenoValorLimite1() {
        double resultado = ArcoCoseno.calcularArcoCoseno(1.0);
        assertEquals(0.0, resultado, 0.0001);
    }

    @Test
    void testArcocosenoValorLimiteMenos1() {
        double resultado = ArcoCoseno.calcularArcoCoseno(-1.0);
        assertEquals(3.141592654, resultado, 0.0001);
    }
}
