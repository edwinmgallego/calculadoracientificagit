/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Miguel
 */
public class FuncionTangente {

    private double primerNumero;

    public FuncionTangente(double primerNumero) {
        this.primerNumero = primerNumero;
    }

    public double getPrimerNumero() {
        return primerNumero;
    }

    public double calcularTangente() {
        //Validación
        if ((primerNumero - 90) % 180 == 0) {
            System.out.println("Error: La tangente no está definida para " + primerNumero + "°");
            return Double.NaN; // Retorna error
        }
        double radianes = Math.toRadians(primerNumero);
        return Math.tan(radianes);
    }
}
