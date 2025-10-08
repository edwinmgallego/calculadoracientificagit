/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import javax.swing.JTextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Usuario
 */
public class BotonBorradoTest {

   private final BotonBorrado botonBorrado = new BotonBorrado();

   

    @Test
    void testBorrarPantalla_RestableceValores() {
        
        botonBorrado.borrarPantalla();

        assertEquals(0, botonBorrado.getPrimerNumero(), "El primer número debe ser 0");
        assertEquals("", botonBorrado.getOperador(), "El operador debe estar vacío");
        assertTrue(botonBorrado.isNuevoInput(), "nuevoInput debe ser true");
    }

    @Test
    void testValoresIniciales_CorrectosAntesDeBorrar() {
        assertEquals(1, botonBorrado.getPrimerNumero(), "El primer número inicial debe ser 1");
        assertEquals("+", botonBorrado.getOperador(), "El operador inicial debe ser '+'");
        assertFalse(botonBorrado.isNuevoInput(), "nuevoInput inicial debe ser false");
    }
   
}
