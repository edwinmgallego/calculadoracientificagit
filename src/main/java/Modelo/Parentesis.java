package Modelo;

import java.util.*;

/**
 * Evaluador de expresiones con paréntesis, precedencia y signos unarios.
 * Soporta: + - * /, paréntesis, números decimales, multiplicación implícita.
 * Acepta símbolos visuales '×' y '÷' (los convierte internamente).
 */
public class Parentesis {

    public static double evaluar(String expresion) {
        if (expresion == null || expresion.trim().isEmpty()) return 0.0;
        // Normalizar símbolos y espacios
        expresion = expresion.replace("×", "*").replace("÷", "/").replaceAll("\\s+", "");
        // Balance básico de paréntesis
        long abiertos = expresion.chars().filter(c -> c == '(').count();
        long cerrados = expresion.chars().filter(c -> c == ')').count();
        if (abiertos != cerrados) throw new IllegalArgumentException("Paréntesis desbalanceados");

        // Normalizar signos unarios: se tratarán en tokenización (no por reemplazo textual)
        // Insertar multiplicación implícita (ej: 2(3) => 2*(3), )( => )*(, )2 => )*2)
        expresion = insertarMultiplicacionImplicita(expresion);

        // Tokenizar
        List<String> tokens = tokenize(expresion);

        // Convertir a RPN (shunting-yard)
        List<String> rpn = toRPN(tokens);

        // Evaluar RPN
        return evalRPN(rpn);
    }

    private static String insertarMultiplicacionImplicita(String expr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            sb.append(c);
            if (i < expr.length() - 1) {
                char nx = expr.charAt(i + 1);
                // digit or ')' followed by '(' or digit -> insert '*'
                if ((Character.isDigit(c) || c == ')' || c == '.') && (nx == '(')) {
                    sb.append('*');
                } else if ((c == ')' ) && (Character.isDigit(nx) || nx == '(')) {
                    sb.append('*');
                }
            }
        }
        return sb.toString();
    }

    private static List<String> tokenize(String s) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);

            // número (con decimal)
            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                    num.append(s.charAt(i));
                    i++;
                }
                tokens.add(num.toString());
                continue;
            }

            // operador o paréntesis
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                // Detectar signo unario: '-' es unario si:
                // - está al inicio, o precedido por '(' o por otro operador (+ - * /)
                if (c == '-') {
                    boolean esUnario = (tokens.isEmpty()) ||
                            "(".equals(lastToken(tokens)) ||
                            isOperator(lastToken(tokens));
                    if (esUnario) {
                        tokens.add("u-"); // marca de negativo unario
                        i++;
                        continue;
                    }
                }
                tokens.add(String.valueOf(c));
                i++;
                continue;
            }

            // Otros caracteres inesperados
            throw new IllegalArgumentException("Caracter inválido en expresión: '" + c + "'");
        }
        return tokens;
    }

    private static String lastToken(List<String> tokens) {
        if (tokens.isEmpty()) return null;
        return tokens.get(tokens.size() - 1);
    }

    private static boolean isOperator(String tok) {
        return tok != null && (tok.equals("+") || tok.equals("-") || tok.equals("*") || tok.equals("/"));
    }

    private static int precedence(String op) {
        switch (op) {
            case "u-": return 4; // unary minus (may consider más alto)
            case "*": case "/": return 3;
            case "+": case "-": return 2;
            default: return 0;
        }
    }

    private static boolean isRightAssociative(String op) {
        return "u-".equals(op); // unary is right-associative
    }

    private static List<String> toRPN(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        for (String tok : tokens) {
            if (isNumber(tok)) {
                output.add(tok);
            } else if (tok.equals("u-") || isOperator(tok)) {
                while (!stack.isEmpty() && isOperatorOrUnary(stack.peek())) {
                    String top = stack.peek();
                    int pTop = precedence(top);
                    int pTok = precedence(tok);
                    if ((isRightAssociative(tok) && pTok < pTop) ||
                        (!isRightAssociative(tok) && pTok <= pTop)) {
                        output.add(stack.pop());
                    } else break;
                }
                stack.push(tok);
            } else if (tok.equals("(")) {
                stack.push(tok);
            } else if (tok.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                if (stack.isEmpty() || !stack.peek().equals("("))
                    throw new IllegalArgumentException("Paréntesis desbalanceados");
                stack.pop(); // quitar "("
            } else {
                throw new IllegalArgumentException("Token inesperado: " + tok);
            }
        }

        while (!stack.isEmpty()) {
            String t = stack.pop();
            if (t.equals("(") || t.equals(")")) throw new IllegalArgumentException("Paréntesis desbalanceados");
            output.add(t);
        }
        return output;
    }

    private static boolean isOperatorOrUnary(String s) {
        return s.equals("u-") || isOperator(s);
    }

    private static boolean isNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        // simple check: starts with digit or dot and parsable
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private static double evalRPN(List<String> rpn) {
        Deque<Double> stack = new ArrayDeque<>();
        for (String tok : rpn) {
            if (isNumber(tok)) {
                stack.push(Double.parseDouble(tok));
            } else if (tok.equals("u-")) {
                if (stack.isEmpty()) throw new IllegalArgumentException("Operando faltante para unario");
                double v = stack.pop();
                stack.push(-v);
            } else if (isOperator(tok)) {
                if (stack.size() < 2) throw new IllegalArgumentException("Operando faltante para operador binario");
                double b = stack.pop();
                double a = stack.pop();
                switch (tok) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                    default: throw new IllegalArgumentException("Operador desconocido: " + tok);
                }
            } else {
                throw new IllegalArgumentException("Token inválido en RPN: " + tok);
            }
        }
        if (stack.size() != 1) throw new IllegalArgumentException("Expresión inválida");
        return stack.pop();
    }
}
