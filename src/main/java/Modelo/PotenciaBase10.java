package Modelo;



public class PotenciaBase10 {
    
    // Atributos
    private double exponente;
    private double resultado;
    
    // Constructor sin parámetros
    public PotenciaBase10() {
        this.exponente = 0.0;
        this.resultado = 1.0;
    }
    
    // Constructor con parámetros
    public PotenciaBase10(double exponente) {
        this.exponente = exponente;
        this.resultado = 0.0;
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
     * Calcula 10 elevado a la potencia del exponente
     * Utiliza Math.pow para el cálculo
     * @return resultado de 10^exponente
     */
    public double calcularPotencia() {
        this.resultado = Math.pow(10, this.exponente);
        return this.resultado;
    }
    
    /**
     * Calcula 10 elevado a un exponente específico sin modificar el atributo
     * @param exp el exponente a utilizar
     * @return resultado de 10^exp
     */
    public double calcularPotencia(double exp) {
        return Math.pow(10, exp);
    }
    
    /**
     * Calcula el logaritmo base 10 (operación inversa)
     * @param numero el número del cual calcular el logaritmo
     * @return log10(numero)
     */
    public double calcularLogaritmo(double numero) {
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
     * Verifica si el resultado es válido (no infinito ni NaN)
     * @return true si el resultado es válido
     */
    public boolean esResultadoValido() {
        return !Double.isInfinite(resultado) && !Double.isNaN(resultado);
    }
    
    // Método toString
    @Override
    public String toString() {
        return "PotenciaBase10{" +
                "exponente=" + exponente +
                ", resultado=" + resultado +
                '}';
    }
    
    /**
     * Método main para pruebas
     */
    public static void main(String[] args) {
        // Ejemplo de uso 1: Con constructor parametrizado
        PotenciaBase10 calc1 = new PotenciaBase10(3);
        double res1 = calc1.calcularPotencia();
        System.out.println(calc1.mostrarOperacion()); // 10^3 = 1000
        
        // Ejemplo de uso 2: Con setters
        PotenciaBase10 calc2 = new PotenciaBase10();
        calc2.setExponente(2.5);
        double res2 = calc2.calcularPotencia();
        System.out.println(calc2.mostrarOperacion()); // 10^2.5 = 316.2278
        
        // Ejemplo de uso 3: Método directo
        PotenciaBase10 calc3 = new PotenciaBase10();
        double res3 = calc3.calcularPotencia(-2);
        System.out.println("10^-2 = " + res3); // 10^-2 = 0.01
        
        // Ejemplo de uso 4: Logaritmo (operación inversa)
        double log = calc3.calcularLogaritmo(1000);
        System.out.println("log10(1000) = " + log); // log10(1000) = 3.0
        
        // Verificar validez
        System.out.println("¿Resultado válido? " + calc1.esResultadoValido());
    }
}
