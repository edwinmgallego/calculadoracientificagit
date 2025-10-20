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
 * @author Angello
 */
public class RaizCuadradaTest {
    /**
     * Test of raiz method, of class RaizCuadrada.
     */
    @org.junit.jupiter.api.Test
    public void testRaiz() {
        System.out.println("raiz");
        double primerNumero = 4;
        RaizCuadrada instance = new RaizCuadrada();
        double expResult = 2;
        double result = instance.raiz(primerNumero);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
