/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author juanc
 */
public class raizEnesima {
    private double numero;
    private int indice;

    public raizEnesima(double numero, int indice) {
        this.numero = numero;
        this.indice = indice;
    }

    public double calcular() {
        if (indice <= 0) {
            System.out.println("Error: el índice debe ser mayor que cero.");
            return Double.NaN;
        }
        if (numero < 0 && indice % 2 == 0) {
            System.out.println("Error: no se puede calcular la raíz par de un número negativo.");
            return Double.NaN;
        }
        if (numero < 0 && indice % 2 != 0) {
            return -Math.pow(-numero, 1.0 / indice);
        }
        return Math.pow(numero, 1.0 / indice);
    }

    public void mostrarResultado() {
        double resultado = calcular();
        if (!Double.isNaN(resultado)) {
            System.out.println("La raíz " + indice + " de " + numero + " es: " + resultado);
        }
    }
}
