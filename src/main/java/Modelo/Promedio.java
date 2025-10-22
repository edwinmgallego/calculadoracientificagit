/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author luisb
 */
public class Promedio {
     private double[] numeros;
    private double resultado;
    
    public Promedio() {
        this.resultado = 0;
    }
    
    public void setNumeros(double[] numeros) {
        this.numeros = numeros;
    }
    
    public double calcular() {
        if (numeros == null || numeros.length == 0) {
            throw new IllegalArgumentException("No hay números para calcular el promedio.");
        }
        double suma = 0;
        for (double n : numeros) {
            suma += n;
        }
        resultado = suma / numeros.length;
        return resultado;
    }

    public double getResultado() {
        return resultado;
    }
}
