/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Clase para calcular el arco seno (inversa del seno)
 */
public class ArcoSeno {
    
    /**
     * Calcula el arco seno de un valor
     * @param valor Valor entre -1 y 1
     * @return Ángulo en grados
     */
    public double calcularArcoSeno(double valor) {
        if (valor < -1 || valor > 1) {
            throw new IllegalArgumentException("El valor debe estar entre -1 y 1");
        }
        return Math.toDegrees(Math.asin(valor));
    }
}
