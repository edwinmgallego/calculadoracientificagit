/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Clase para calcular potencias de base 10
 * Proporciona métodos para calcular 10^x y logaritmo base 10
 * @author cript
 */
public class PotenciaBase10 {
    
    private double exponente;
    private double resultado;
    
    // Constructor sin parámetros
    public PotenciaBase10() {
        this.exponente = 0.0;
        this.resultado = 1.0; // 10^0 = 1
    }
    
    // Constructor con parámetros
    public PotenciaBase10(double exponente) {
        this.exponente = exponente;
        this.resultado = Math.pow(10, exponente); // Calcular inmediatamente
    }
    
    // Métodos Getters y Setters
    public double getExponente() {
        return exponente;
    }
    
    public void setExponente(double exponente) {
        this.exponente = exponente;
    }
    
    public double getResultado() {
        return resultado;
    }
    
    /**
     * Calcula 10 elevado a la potencia del exponente almacenado
     * @return resultado de 10^exponente
     */
    public double calcularPotencia() {
        this.resultado = Math.pow(10, this.exponente);
        return this.resultado;
    }
    
    /**
     * Calcula 10 elevado a un exponente específico (método estático)
     * Este método es el recomendado para usar en la calculadora
     * @param exp el exponente a utilizar
     * @return resultado de 10^exp
     */
    public static double calcularPotencia(double exp) {
        return Math.pow(10, exp);
    }
    
    /**
     * Calcula el logaritmo base 10 (operación inversa de 10^x)
     * @param numero el número del cual calcular el logaritmo
     * @return log10(numero)
     * @throws IllegalArgumentException si el número es menor o igual a 0
     */
    public static double calcularLogaritmo(double numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El logaritmo solo acepta números positivos");
        }
        return Math.log10(numero);
    }
    
    /**
     * Método para mostrar el resultado formateado
     * @return String con el cálculo realizado
     */
    public String mostrarOperacion() {
        return String.format("10^%.2f = %.4f", exponente, resultado);
    }
    
    /**
     * Verifica si un resultado es válido (no infinito ni NaN)
     * @param valor el valor a verificar
     * @return true si el resultado es válido
     */
    public static boolean esResultadoValido(double valor) {
        return !Double.isInfinite(valor) && !Double.isNaN(valor);
    }
    
    @Override
    public String toString() {
        return "PotenciaBase10{" +
                "exponente=" + exponente +
                ", resultado=" + resultado +
                '}';
    }
}
