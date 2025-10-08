package Modelo;

public class Parentesis {
    public static double evaluar(String expresion) {
        expresion = expresion.replace(" ", "");

        expresion = insertarMultiplicacionImplicita(expresion);

        while (expresion.contains("(")) {
            int inicio = expresion.lastIndexOf("(");
            int fin = expresion.indexOf(")", inicio);

            if (fin == -1) {
                throw new IllegalArgumentException("Falta un paréntesis de cierre");
            }

            String sub = expresion.substring(inicio + 1, fin);
            double resultadoSub = evaluarSinParentesis(sub);
            expresion = expresion.substring(0, inicio) + resultadoSub + expresion.substring(fin + 1);
        }

        return evaluarSinParentesis(expresion);
    }

    private static String insertarMultiplicacionImplicita(String expr) {
        StringBuilder nueva = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char actual = expr.charAt(i);
            nueva.append(actual);

            if (i < expr.length() - 1) {
                char siguiente = expr.charAt(i + 1);

                if (actual == ')' && (siguiente == '(' || Character.isDigit(siguiente))) {
                    nueva.append('*');
                }

                if (Character.isDigit(actual) && siguiente == '(') {
                    nueva.append('*');
                }
            }
        }

        return nueva.toString();
    }

    private static double evaluarSinParentesis(String expr) {
        double resultado = 0;
        char operacion = '+';
        String numero = "";

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                numero += c;
            } else if ("+-*/".indexOf(c) >= 0) {
                if (numero.isEmpty()) continue;
                double valor = Double.parseDouble(numero);
                resultado = aplicarOperacion(resultado, valor, operacion);
                operacion = c;
                numero = "";
            }
        }

        if (!numero.isEmpty()) {
            double valor = Double.parseDouble(numero);
            resultado = aplicarOperacion(resultado, valor, operacion);
        }

        return resultado;
    }

    private static double aplicarOperacion(double acumulado, double valor, char operador) {
        switch (operador) {
            case '+': return acumulado + valor;
            case '-': return acumulado - valor;
            case '*': return acumulado * valor;
            case '/': return acumulado / valor;
            default: return valor;
        }
    }
}
