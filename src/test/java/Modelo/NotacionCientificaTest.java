package Modelo;

import Modelo.NotacionCientifica;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Clase de pruebas para NotacionCientifica
 */
public class NotacionCientificaTest {

    @Test
    public void testConvertirANotacionCientifica_PositiveNumber() {
        String result = NotacionCientifica.convertirANotacionCientifica(12345.678, 2);
        assertTrue(result.startsWith("1.23E+4"), 
                "Debería dar una notación con exponente +4, pero fue: " + result);
    }

    @Test
    public void testConvertirANotacionCientifica_NegativeNumber() {
        String result = NotacionCientifica.convertirANotacionCientifica(-0.00456, 3);
        assertTrue(result.startsWith("-4.560E-3"), 
                "Debería dar una notación con exponente -3, pero fue: " + result);
    }

    @Test
    public void testConvertirANotacionCientifica_Cero() {
        String result = NotacionCientifica.convertirANotacionCientifica(0);
        assertEquals("0.00E+0", result);
    }

    @Test
    public void testParseNotacionCientifica_Valida() {
        double valor = NotacionCientifica.parseNotacionCientifica("1.23E+3");
        assertEquals(1230.0, valor, 0.001);
    }

    @Test
    public void testParseNotacionCientifica_Invalida() {
        assertThrows(NumberFormatException.class, () -> {
            NotacionCientifica.parseNotacionCientifica("1,23E+3");
        });
    }

    @Test
    public void testSumar() {
        String resultado = NotacionCientifica.sumar("1.0E+3", "2.0E+3");
        assertTrue(resultado.startsWith("3.00E+3"), "Suma incorrecta: " + resultado);
    }

    @Test
    public void testRestar() {
        String resultado = NotacionCientifica.restar("5.0E+2", "2.0E+2");
        assertTrue(resultado.startsWith("3.00E+2"), "Resta incorrecta: " + resultado);
    }

    @Test
    public void testMultiplicar() {
        String resultado = NotacionCientifica.multiplicar("2.0E+2", "3.0E+1");
        assertTrue(resultado.startsWith("6.00E+3"), "Multiplicación incorrecta: " + resultado);
    }

    @Test
    public void testDividir() {
        String resultado = NotacionCientifica.dividir("9.0E+2", "3.0E+1");
        assertTrue(resultado.startsWith("3.00E+1"), "División incorrecta: " + resultado);
    }

    @Test
    public void testDividirPorCero() {
        String resultado = NotacionCientifica.dividir("1.0E+2", "0.0E+0");
        assertEquals("Error: División por cero", resultado);
    }

    @Test
    public void testObtenerMantisaYExponente() {
        double mantisa = NotacionCientifica.obtenerMantisa("3.45E+4");
        int exponente = NotacionCientifica.obtenerExponente("3.45E+4");
        assertEquals(3.45, mantisa, 0.01);
        assertEquals(4, exponente);
    }

    @Test
    public void testEsNotacionCientificaValida() {
        assertTrue(NotacionCientifica.esNotacionCientificaValida("1.23E-4"));
        assertFalse(NotacionCientifica.esNotacionCientificaValida("abcE+2"));
    }
}