/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author camilocardonasuarez
 */
public class raizEnesima {

    private double primerNumero;  
    private int segundoNumero;    

    public raizEnesima(double primerNumero, int segundoNumero) {
        this.primerNumero = primerNumero;
        this.segundoNumero = segundoNumero;
    }

    public double calcular() {
        if (segundoNumero <= 0) {
            System.out.println("Error: el índice debe ser mayor que cero.");
            return Double.NaN;
        }
        if (primerNumero < 0 && segundoNumero % 2 == 0) {
            System.out.println("Error: no se puede calcular la raíz par de un número negativo.");
            return Double.NaN;
        }
        if (primerNumero < 0 && segundoNumero % 2 != 0) {
            return -Math.pow(-primerNumero, 1.0 / segundoNumero);
        }
        return Math.pow(primerNumero, 1.0 / segundoNumero);
    }

    public void mostrarResultado() {
        double resultado = calcular();
        if (!Double.isNaN(resultado)) {
            System.out.println("La raíz " + segundoNumero + " de " + primerNumero + " es: " + resultado);
        }
    }

}
