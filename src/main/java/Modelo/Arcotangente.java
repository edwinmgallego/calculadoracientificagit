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
    
   
   private double primerNumero;

  
    public double getPrimerNumero() {
        return primerNumero;
    }

    
    public void setPrimerNumero(double primerNumero) {
        this.primerNumero = primerNumero;
    }

    
    public static double calcularArcotangente(double primerNumero) {
        return Math.atan(primerNumero);
    }
    
}
