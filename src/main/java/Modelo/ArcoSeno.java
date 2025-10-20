/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LENOVO
 */
public class ArcoSeno {
    
    private double primerNumero;

    public double getPrimerNumero() {
        return primerNumero;
    }

    public void setPrimerNumero(double primerNumero) {
        this.primerNumero = primerNumero;
    }

    public static double calcularArcoSeno(double primerNumero) {
        if (primerNumero < -1 || primerNumero > 1) {
            throw new IllegalArgumentException("El valor debe estar entre -1 y 1");
        }
        return Math.asin(primerNumero);
    }   
}
