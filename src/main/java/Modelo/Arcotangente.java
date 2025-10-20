/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Jonatan
 */
public class Arcotangente {
    
    private double valor;

    public Arcotangente() {}

    public Arcotangente(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double calcular() {
        return Math.atan(valor);
    }

    public static double calcularArcotangente(double valor) {
        return Math.atan(valor);
    }
    
}
