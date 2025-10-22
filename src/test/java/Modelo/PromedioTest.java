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
 * @author luisb
 */
public class PromedioTest {

    public PromedioTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test del método calcular, de la clase Promedio.
     */
    @Test
    public void testCalcular() {
        System.out.println("calcular");
        double[] numeros = {5, 10, 15, 20};
        Promedio instance = new Promedio();
        instance.setNumeros(numeros);
        double expResult = 12.5;
        double result = instance.calcular();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test del manejo de error con arreglo vacío.
     */
    @Test
    public void testArregloVacio() {
        System.out.println("arreglo vacio");
        double[] numeros = {};
        Promedio instance = new Promedio();
        instance.setNumeros(numeros);
        assertThrows(IllegalArgumentException.class, () -> instance.calcular());
    }
}
