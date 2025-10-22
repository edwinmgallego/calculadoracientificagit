/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author CamiLaNekoUwU_Gamer
 */
public class CambioSigno {

    private double numeroActual;
    private double resultado;
    
    
    
    
    
    
    
    

    public CambioSigno() {
        this.numeroActual = 0.0;
        this.resultado = 0.0;
    }
    

    public CambioSigno(double numeroActual) {
        this.numeroActual = numeroActual;
        this.resultado = 0.0;
    }
    

    public double calcularCambioSigno() {
        resultado = numeroActual * -1;
        return resultado;
    }
    

    public double calcularCambioSigno(double numero) {
        this.numeroActual = numero;
        resultado = numeroActual * -1;
        return resultado;
    }
    

    public double getNumeroActual() {
        return numeroActual;
    }
    
    public double getResultado() {
        return resultado;
    }
    

    public void setNumeroActual(double numeroActual) {
        this.numeroActual = numeroActual;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }
    
    @Override
    public String toString() {
        return "CambioSigno{" +
                "numeroActual=" + numeroActual +
                ", resultado=" + resultado +
                '}';
    }
}

