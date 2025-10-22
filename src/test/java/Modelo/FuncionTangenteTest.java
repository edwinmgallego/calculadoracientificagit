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
 * @author Miguel
 */
public class FuncionTangenteTest {
    private final FuncionTangente operacion = new FuncionTangente(4);
    
    @Test
    
    public void calcularTangente(){
        System.out.println("Test unitario para calcular la Tangente");
        double resultado_posible = operacion.calcularTangente();
        double resultado_esperado = 0.069;
        assertEquals(resultado_esperado, resultado_posible, 0.01);
    }
    
    
}
