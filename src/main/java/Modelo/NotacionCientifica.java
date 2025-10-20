package Modelo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Clase para manejar conversiones y operaciones con notación científica.
 * Autor: juank (mejorado por Chat Español - https://gptonline.ai/)
 */
public class NotacionCientifica {

    private static final MathContext CONTEXTO = new MathContext(12, RoundingMode.HALF_UP);

    /**
     * Convierte un número decimal a notación científica.
     *
     * @param numero Número a convertir
     * @param decimales Número de decimales de la mantisa
     * @return Cadena en formato notación científica, por ejemplo "1.23E+3"
     */
    public static String convertirANotacionCientifica(double numero, int decimales) {
        if (numero == 0) {
            return "0.00E+0";
        }

        boolean esNegativo = numero < 0;
        numero = Math.abs(numero);

        int exponente = (int) Math.floor(Math.log10(numero));
        double mantisa = numero / Math.pow(10, exponente);

        BigDecimal bdMantisa = new BigDecimal(mantisa, CONTEXTO);
        bdMantisa = bdMantisa.setScale(decimales, RoundingMode.HALF_UP);

        String signo = esNegativo ? "-" : "";
        String signoExp = exponente >= 0 ? "+" : "";

        return String.format("%s%sE%s%d", signo, bdMantisa.toPlainString(), signoExp, exponente);
    }

    /**
     * Convierte un número decimal a notación científica con 2 decimales por defecto.
     */
    public static String convertirANotacionCientifica(double numero) {
        return convertirANotacionCientifica(numero, 2);
    }

    /**
     * Convierte una cadena en notación científica a número decimal.
     */
    public static double parseNotacionCientifica(String notacion) throws NumberFormatException {
        if (notacion == null || notacion.trim().isEmpty()) {
            throw new NumberFormatException("La notación no puede ser nula o vacía");
        }

        notacion = notacion.trim().toUpperCase();

        // Expresión regular más completa
        if (!notacion.matches("[-+]?\\d*\\.?\\d+E[-+]?\\d+")) {
            throw new NumberFormatException("Formato inválido de notación científica");
        }

        return Double.parseDouble(notacion);
    }

    // ==== Operaciones con notación científica ====

    public static String sumar(String nc1, String nc2) {
        return operar(nc1, nc2, Operacion.SUMA);
    }

    public static String restar(String nc1, String nc2) {
        return operar(nc1, nc2, Operacion.RESTA);
    }

    public static String multiplicar(String nc1, String nc2) {
        return operar(nc1, nc2, Operacion.MULTIPLICACION);
    }

    public static String dividir(String nc1, String nc2) {
        return operar(nc1, nc2, Operacion.DIVISION);
    }

    private enum Operacion { SUMA, RESTA, MULTIPLICACION, DIVISION }

    private static String operar(String nc1, String nc2, Operacion tipo) {
        try {
            BigDecimal n1 = new BigDecimal(parseNotacionCientifica(nc1), CONTEXTO);
            BigDecimal n2 = new BigDecimal(parseNotacionCientifica(nc2), CONTEXTO);
            BigDecimal resultado;

            switch (tipo) {
                case SUMA:
                    resultado = n1.add(n2, CONTEXTO);
                    break;
                case RESTA:
                    resultado = n1.subtract(n2, CONTEXTO);
                    break;
                case MULTIPLICACION:
                    resultado = n1.multiply(n2, CONTEXTO);
                    break;
                case DIVISION:
                    if (n2.compareTo(BigDecimal.ZERO) == 0) {
                        return "Error: División por cero";
                    }
                    resultado = n1.divide(n2, CONTEXTO);
                    break;
                default:
                    throw new IllegalArgumentException("Operación no válida");
            }

            return convertirANotacionCientifica(resultado.doubleValue());
        } catch (NumberFormatException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Obtiene la mantisa de una notación científica.
     */
    public static double obtenerMantisa(String notacion) {
        double numero = parseNotacionCientifica(notacion);
        if (numero == 0) return 0;
        int exponente = (int) Math.floor(Math.log10(Math.abs(numero)));
        return numero / Math.pow(10, exponente);
    }

    /**
     * Obtiene el exponente de una notación científica.
     */
    public static int obtenerExponente(String notacion) {
        double numero = parseNotacionCientifica(notacion);
        if (numero == 0) return 0;
        return (int) Math.floor(Math.log10(Math.abs(numero)));
    }

    /**
     * Verifica si una cadena es notación científica válida.
     */
    public static boolean esNotacionCientificaValida(String notacion) {
        try {
            parseNotacionCientifica(notacion);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
