/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author camilocardonasuarez
 */
public class ExponencialEuler {

    private double exponente;  

    public ExponencialEuler(double exponente) {
        this.exponente = exponente;
    }

    public double getExponente() {
        return exponente;
    }

    public void setExponente(double exponente) {
        this.exponente = exponente;
    }
    public double calcularExponencialEuler() {
        return Math.exp(exponente);
    }

}
