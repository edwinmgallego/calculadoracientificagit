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
 * @author Vero
 */
public class EulerTest {
    @Test
    void testConstanteEuler() {
        // Valor esperado de e
        double esperado = Math.E;

        // Valor obtenido de nuestra clase Euler
        double obtenido = Euler.E;

        // Verifica que sean iguales (dentro de una tolerancia)
        assertEquals(esperado, obtenido, 1e-15,
            "El valor de Euler.E debe ser igual a Math.E");
    }

    @Test
    void testMetodoGetValor() {
        assertEquals(Math.E, Euler.getE(), 1e-15,
            "El método getValor() debe devolver Math.E");
    }
}

    
   
    

