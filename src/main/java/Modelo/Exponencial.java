/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


/**
 *
 * @author oeara
 */
public class Exponencial {
    
    private double primerNumero;
    private double resultado;
    private double segundoNumero;

    public double getPrimerNumero() {
        return primerNumero;
    }

    public double getResultado() {
        return resultado;
    }

    public double getSegundoNumero() {
        return segundoNumero;
    }

    public void setPrimerNumero(double primerNumero) {
        this.primerNumero = primerNumero;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public void setSegundoNumero(double segundoNumero) {
        this.segundoNumero = segundoNumero;
    }
    
    
    public double Expocinencial(double primerNumero, double segundoNumero) {
        resultado = Math.pow (primerNumero,segundoNumero);
        
        return resultado;
    }
    
}
