/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for class FuncHiperb.
 */
public class FuncHiperbTest {

    private static final double DELTA = 1e-9; // tolerance for floating-point comparison

    @Test
    public void testSinh() {
        double input = 1.0;
        FuncHiperb f = new FuncHiperb(input, 1); // 1 → sinh
        double expected = Math.sinh(input);
        assertEquals(expected, f.getOutput(), DELTA, "sinh() calculation failed");
    }

    @Test
    public void testCosh() {
        double input = 1.0;
        FuncHiperb f = new FuncHiperb(input, 2); // 2 → cosh
        double expected = Math.cosh(input);
        assertEquals(expected, f.getOutput(), DELTA, "cosh() calculation failed");
    }

    @Test
    public void testTanh() {
        double input = 1.0;
        FuncHiperb f = new FuncHiperb(input, 3); // 3 → tanh
        double expected = Math.tanh(input);
        assertEquals(expected, f.getOutput(), DELTA, "tanh() calculation failed");
    }

    @Test
    public void testNegativeInput() {
        double input = -2.5;
        FuncHiperb sinh = new FuncHiperb(input, 1);
        FuncHiperb cosh = new FuncHiperb(input, 2);
        FuncHiperb tanh = new FuncHiperb(input, 3);

        assertEquals(Math.sinh(input), sinh.getOutput(), DELTA);
        assertEquals(Math.cosh(input), cosh.getOutput(), DELTA);
        assertEquals(Math.tanh(input), tanh.getOutput(), DELTA);
    }

    @Test
    public void testZeroInput() {
        FuncHiperb sinh = new FuncHiperb(0.0, 1);
        FuncHiperb cosh = new FuncHiperb(0.0, 2);
        FuncHiperb tanh = new FuncHiperb(0.0, 3);

        assertEquals(0.0, sinh.getOutput(), DELTA);
        assertEquals(1.0, cosh.getOutput(), DELTA);
        assertEquals(0.0, tanh.getOutput(), DELTA);
    }
}
