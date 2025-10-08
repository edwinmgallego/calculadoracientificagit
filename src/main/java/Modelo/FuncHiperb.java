/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jose
 */
public class FuncHiperb {
    double output;

    public FuncHiperb(double input, int func) {
        switch (func) {
            case 1 -> output = sinh(input);
            case 2 -> output = cosh(input);
            case 3 -> output = tanh(input);
        }
    }
    
    private double sinh(double input) {
        double result = Math.sinh(input);
        return result;
    }
    
    private double cosh(double input) {
        double result = Math.cosh(input);
        return result;
    }
    
    private double tanh(double input) {
        double result = Math.tanh(input);
        return result;
    }

    public double getOutput() {
        return output;
    }
    
}
