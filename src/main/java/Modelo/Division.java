/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author juanj
 */
public class Division {
    private double primerNumero;
    private double segundoNumero;
    private double resultado;

    public double getPrimerNumero() {
        return primerNumero;
    }

    public void setPrimerNumero(double primerNumero) {
        this.primerNumero = primerNumero;
    }

    public double getSegundoNumero() {
        return segundoNumero;
    }

    public void setSegundoNumero(double segundoNumero) {
        this.segundoNumero = segundoNumero;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public Division(double primerNumero, double segundoNumero, double resultado) {
        this.primerNumero = primerNumero;
        this.segundoNumero = segundoNumero;
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Division{" + "primerNumero=" + primerNumero + ", segundoNumero=" + segundoNumero + ", resultado=" + resultado + '}';
    }
     public double Division(double primerNumero, double segundoNumero){
        resultado=primerNumero/segundoNumero;
        return resultado;
}
}