/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Clase que implementa la funcionalidad de Resta para la calculadora científica.
 * 
 * @author [Tu Nombre]
 */
public class Resta {
    
    private double primerNumero;
    private double segundoNumero;
    private double resultado;
    
 
    public Resta() {
        this.primerNumero = 0;
        this.segundoNumero = 0;
        this.resultado = 0;
    }
    
    public void setPrimerNumero(double primerNumero) {
        this.primerNumero = primerNumero;
    }
    
    public void setSegundoNumero(double segundoNumero) {
        this.segundoNumero = segundoNumero;
    }

}
