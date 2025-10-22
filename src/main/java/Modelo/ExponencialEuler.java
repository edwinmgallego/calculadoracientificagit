/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author camilocardonasuarez
 */
public class ExponencialEuler {
    
    private double primerNumero;  

    public ExponencialEuler(double primerNumero) {
        this.primerNumero = primerNumero;
    }

    public double getPrimerNumero() {
        return primerNumero;
    }

    public void setPrimerNumero(double primerNumero) {
        this.primerNumero = primerNumero;
    }
    
    public double calcularExponencialEuler() {
        return Math.exp(primerNumero);
    }
    
}
