/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Suma;

/**
 *
 * @author crist
 */
public class Suma {
    private double primerNumero;
    private double segundoNumero;
    private double resultadosum;

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
        return resultadosum;
    }

    public void setResultado(double resultadosum) {
        this.resultadosum = resultadosum;
    }
    
    public double sumar (double primerNumero, double segundoNumero){
        resultadosum=primerNumero+segundoNumero;
        return resultadosum;
    }
}
