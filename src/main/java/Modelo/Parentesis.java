package Modelo;

public class Parentesis {
    public static double evaluar(String expresion) {
        expresion = expresion.replace(" ", "");

        // Validar paréntesis balanceados
        long abiertos = expresion.chars().filter(c -> c == '(').count();
        long cerrados = expresion.chars().filter(c -> c == ')').count();
        if (abiertos != cerrados) {
            throw new IllegalArgumentException("Paréntesis desbalanceados");
        }

        // Manejar signos negativos al inicio o después de un operador o paréntesis
        expresion = normalizarSignos(expresion);

        // Insertar multiplicación implícita: ej. 2(3+4), (2+3)(4+5)
        expresion = insertarMultiplicacionImplicita(expresion);

        // Resolver paréntesis de adentro hacia afuera
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

    // --- Corrige signos negativos unarios ---
    private static String normalizarSignos(String expr) {
        StringBuilder sb = new StringBuilder(expr.length());
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '-' && (i == 0 || "+-*/(".indexOf(expr.charAt(i - 1)) >= 0)) {
                sb.append("0-");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // --- Detecta multiplicaciones implícitas como 2(3+4) o (2+3)(4+5) ---
    private static String insertarMultiplicacionImplicita(String expr) {
        StringBuilder nueva = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char actual = expr.charAt(i);
            nueva.append(actual);

            if (i < expr.length() - 1) {
                char siguiente = expr.charAt(i + 1);

                // Ej: ")(" o ")2" o "2("
                if ((actual == ')' && (siguiente == '(' || Character.isDigit(siguiente))) ||
                    (Character.isDigit(actual) && siguiente == '(')) {
                    nueva.append('*');
                }
            }
        }

        return nueva.toString();
    }

    // --- Evalúa expresión simple sin paréntesis ---
    private static double evaluarSinParentesis(String expr) {
        java.util.List<Double> valores = new java.util.ArrayList<>();
        java.util.List<Character> ops = new java.util.ArrayList<>();
        StringBuilder numero = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                numero.append(c);
            } else if ("+-*/".indexOf(c) >= 0) {
                if (numero.length() == 0)
                    throw new IllegalArgumentException("Error de sintaxis: operador inesperado en " + expr);
                valores.add(Double.parseDouble(numero.toString()));
                ops.add(c);
                numero.setLength(0);
            }
        }
        if (numero.length() > 0) {
            valores.add(Double.parseDouble(numero.toString()));
        }

        // Resolver * y /
        for (int i = 0; i < ops.size(); i++) {
            char op = ops.get(i);
            if (op == '*' || op == '/') {
                double a = valores.get(i);
                double b = valores.get(i + 1);
                double r = (op == '*') ? a * b : a / b;
                valores.set(i, r);
                valores.remove(i + 1);
                ops.remove(i);
                i--;
            }
        }

        // Resolver + y -
        double resultado = valores.get(0);
        for (int i = 0; i < ops.size(); i++) {
            char op = ops.get(i);
            double b = valores.get(i + 1);
            resultado = (op == '+') ? resultado + b : resultado - b;
        }

        return resultado;
    }

}