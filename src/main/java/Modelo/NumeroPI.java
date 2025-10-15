/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author eljul
 */
public class NumeroPI {

    public static double aproximarPi(int iteraciones) {
        double pi = 0.0;
        boolean sumar = true;

        for (int i = 0; i < iteraciones; i++) {
            if (sumar) {
                pi += 1.0 / (2 * i + 1);
            } else {
                pi -= 1.0 / (2 * i + 1);
            }
            sumar = !sumar;
        }

        pi *= 4; // Fórmula de Leibniz
        return pi;

    }

    public static void main(String[] args) {
        double piAprox = aproximarPi(1000000);
        String resultado = String.format("%.5f", piAprox);
        System.out.println("Aproximación de π: " + resultado);

    }

}
