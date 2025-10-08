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
 * @author migue
 */
public class MultiplicacionTest {

    @Test
    public void testMultiplicar() {
        System.out.println("multiplicar");
        double primerNumero = 5.0;
        double segundoNumero = 2.0;
        Multiplicacion instance = new Multiplicacion();
        double expResult = 10.0;
        double result = instance.multiplicar(primerNumero, segundoNumero);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
