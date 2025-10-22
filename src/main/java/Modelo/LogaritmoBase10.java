package Modelo;


public class LogaritmoBase10 {

   
    private double numero;

   
    private double resultado;

    
    public LogaritmoBase10(double numero) {
        this.numero = numero;
        calcular();
    }

    
    private void calcular() {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número debe ser mayor que cero para calcular el logaritmo base 10.");
        }
        resultado = Math.log10(numero);
    }

    
    public double getResultado() {
        return resultado;
    }

   
    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
        calcular(); 
    }
}