package Modelo;

public class Parentesis {
    

    public static double evaluar(String expresion) {
        if (expresion == null || expresion.trim().isEmpty()) {
            throw new IllegalArgumentException("Expresión vacía");
        }
        
        // Eliminar espacios en blanco
        expresion = expresion.replace(" ", "");
        
        // Validar caracteres permitidos
        if (!expresion.matches("[0-9+\\-*/().]+")) {
            throw new IllegalArgumentException("Expresión contiene caracteres no permitidos");
        }
        
        // Validar paréntesis balanceados
        if (!validarParentesis(expresion)) {
            throw new IllegalArgumentException("Paréntesis desbalanceados");
        }
        
        // Normalizar signos negativos
        expresion = normalizarSignos(expresion);
        
        // Insertar multiplicación implícita
        expresion = insertarMultiplicacionImplicita(expresion);
        
        // Resolver paréntesis de adentro hacia afuera
        while (expresion.contains("(")) {
            int inicio = expresion.lastIndexOf("(");
            int fin = expresion.indexOf(")", inicio);
            
            if (fin == -1) {
                throw new IllegalArgumentException("Falta paréntesis de cierre");
            }
            
            String subExpresion = expresion.substring(inicio + 1, fin);
            double resultadoSub = evaluarSinParentesis(subExpresion);
            
            // Reemplazar la subexpresión con su resultado
            expresion = expresion.substring(0, inicio) + 
                       resultadoSub + 
                       expresion.substring(fin + 1);
        }
        
        return evaluarSinParentesis(expresion);
    }
    
    private static boolean validarParentesis(String expr) {
        int contador = 0;
        for (char c : expr.toCharArray()) {
            if (c == '(') contador++;
            if (c == ')') contador--;
            if (contador < 0) return false; // Más cierres que aperturas
        }
        return contador == 0;
    }
    
    /**
     * Normaliza signos negativos unarios
     * Convierte expresiones como "-5" en "0-5"
     */
    private static String normalizarSignos(String expr) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            
            // Si es un '-' al inicio o después de operador/paréntesis
            if (c == '-' && (i == 0 || "+-*/(".indexOf(expr.charAt(i - 1)) >= 0)) {
                sb.append("0-");
            } else if (c == '+' && (i == 0 || "+-*/(".indexOf(expr.charAt(i - 1)) >= 0)) {
                // Ignorar '+' unario
                continue;
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Inserta operadores de multiplicación implícitos
     * Ej: "2(3)" -> "2*(3)", ")()" -> ")*("
     */
    private static String insertarMultiplicacionImplicita(String expr) {
        StringBuilder resultado = new StringBuilder();
        
        for (int i = 0; i < expr.length(); i++) {
            char actual = expr.charAt(i);
            resultado.append(actual);
            
            if (i < expr.length() - 1) {
                char siguiente = expr.charAt(i + 1);
                

                boolean necesitaMultiplicacion = 
                    (Character.isDigit(actual) && siguiente == '(') ||
                    (actual == ')' && siguiente == '(') ||
                    (actual == ')' && Character.isDigit(siguiente)) ||
                    (actual == '.' && siguiente == '('); // Para decimales como "3.5("
                
                if (necesitaMultiplicacion) {
                    resultado.append('*');
                }
            }
        }
        
        return resultado.toString();
    }
    
    /**
     * Evalúa una expresión simple sin paréntesis
     * Respeta el orden de operaciones: primero * y /, luego + y -
     */
    private static double evaluarSinParentesis(String expr) {
        if (expr == null || expr.trim().isEmpty()) {
            throw new IllegalArgumentException("Expresión vacía");
        }
        
        java.util.List<Double> valores = new java.util.ArrayList<>();
        java.util.List<Character> operadores = new java.util.ArrayList<>();
        StringBuilder numeroActual = new StringBuilder();
        
        // Parsear la expresión
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            
            if (Character.isDigit(c) || c == '.') {
                numeroActual.append(c);
            } else if ("+-*/".indexOf(c) >= 0) {
                if (numeroActual.length() == 0) {
                    throw new IllegalArgumentException("Operador inesperado en posición " + i);
                }
                
                valores.add(Double.parseDouble(numeroActual.toString()));
                operadores.add(c);
                numeroActual.setLength(0);
            } else {
                throw new IllegalArgumentException("Carácter inválido: " + c);
            }
        }
        
        // Agregar el último número
        if (numeroActual.length() > 0) {
            valores.add(Double.parseDouble(numeroActual.toString()));
        }
        
        // Validar que haya números suficientes
        if (valores.isEmpty()) {
            throw new IllegalArgumentException("No hay valores en la expresión");
        }
        
        if (valores.size() != operadores.size() + 1) {
            throw new IllegalArgumentException("Expresión mal formada");
        }
        
        // Paso 1: Resolver multiplicaciones y divisiones (izquierda a derecha)
        for (int i = 0; i < operadores.size(); i++) {
            char op = operadores.get(i);
            
            if (op == '*' || op == '/') {
                double a = valores.get(i);
                double b = valores.get(i + 1);
                
                if (op == '/' && b == 0) {
                    throw new ArithmeticException("División por cero");
                }
                
                double resultado = (op == '*') ? a * b : a / b;
                
                // Reemplazar los dos valores con el resultado
                valores.set(i, resultado);
                valores.remove(i + 1);
                operadores.remove(i);
                i--; // Retroceder para verificar el nuevo operador en esta posición
            }
        }
        
        // Paso 2: Resolver sumas y restas (izquierda a derecha)
        double resultado = valores.get(0);
        for (int i = 0; i < operadores.size(); i++) {
            char op = operadores.get(i);
            double b = valores.get(i + 1);
            
            resultado = (op == '+') ? resultado + b : resultado - b;
        }
        
        return resultado;
    }
    
    /**
     * Método auxiliar para verificar si una cadena es una expresión válida
     */
    public static boolean esExpresionValida(String expresion) {
        try {
            evaluar(expresion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}