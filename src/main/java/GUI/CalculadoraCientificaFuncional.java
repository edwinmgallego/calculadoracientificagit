package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Modelo.Parentesis;

/**
 * Calculadora que permite introducir toda la expresión antes de calcular.
 */
public class CalculadoraCientificaFuncional extends JFrame implements ActionListener {

    private JTextField display;
    // ya no usamos operador/primerNumero para cálculos intermedios
    private boolean nuevoInput = true;

    public CalculadoraCientificaFuncional() {
        setTitle("Calculadora Científica");
        setSize(420, 560);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8,8));

        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Consolas", Font.PLAIN, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(7, 5, 6, 6));
        String[] botones = {
            "(", ")", "C", "←", "÷",
            "7", "8", "9", "×", "√",
            "4", "5", "6", "-", "x²",
            "1", "2", "3", "+", "xʸ",
            "0", ".", "±", "=", "%",
            "sin", "cos", "tan", "sinh", "cosh",
            "tanh", "log", "ln", "exp", "1/x"
        };

        for (String texto : botones) {
            JButton b = new JButton(texto);
            b.setFont(new Font("Consolas", Font.BOLD, 16));
            b.addActionListener(this);
            panelBotones.add(b);
        }

        add(panelBotones, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        String textoDisplay = display.getText();

        try {
            switch (comando) {
                // Números
                case "0": case "1": case "2": case "3": case "4":
                case "5": case "6": case "7": case "8": case "9":
                    if (nuevoInput || "0".equals(textoDisplay)) {
                        display.setText(comando);
                        nuevoInput = false;
                    } else {
                        display.setText(textoDisplay + comando);
                    }
                    break;

                // Decimal
                case ".":
                    if (nuevoInput) {
                        display.setText("0.");
                        nuevoInput = false;
                    } else {
                        display.setText(textoDisplay + ".");
                    }
                    break;

                // Paréntesis
                case "(":
                case ")":
                    if (nuevoInput && comando.equals("(")) {
                        display.setText(comando);
                        nuevoInput = false;
                    } else {
                        display.setText(textoDisplay + comando);
                    }
                    break;

                // Clear
                case "C":
                    display.setText("0");
                    nuevoInput = true;
                    break;

                // Backspace
                case "←":
                    if (textoDisplay.length() > 1) {
                        display.setText(textoDisplay.substring(0, textoDisplay.length() - 1));
                    } else {
                        display.setText("0");
                        nuevoInput = true;
                    }
                    break;

                // Insertar funciones (no calcular aún) - se agrega "(" esperando argumento
                case "sin": case "cos": case "tan":
                case "sinh": case "cosh": case "tanh":
                case "log": case "ln": case "exp":
                    insertText(comando + "(");
                    break;

                // Raíz visual (insertamos "√(" )
                case "√":
                    insertText("√(");
                    break;

                // 1/x: se aplica al último operando (se envuelve)
                case "1/x":
                    wrapLastOperandWith("1/(", ")");
                    break;

                // x²: elevar al cuadrado (sustituye último operando por (op*op))
                case "x²":
                    squareLastOperand();
                    break;

                // ± : cambiar signo del último operando (sin evaluar toda la expresión)
                case "±":
                    toggleSignLastOperand();
                    break;

                // % : transformar último operando en (op/100)
                case "%":
                    percentLastOperand();
                    break;

                // Operadores binarios: simplemente insertarlos (reemplazan si ya hay un operador al final)
                case "+": case "-": case "×": case "÷": case "xʸ":
                    insertOperator(comando);
                    break;

                // Igual: evaluar toda la expresión (preprocesando funciones y %)
                case "=": {
                    String expr = display.getText();
                    double result = evaluateExpressionFull(expr);
                    display.setText(formatNumber(result));
                    nuevoInput = true;
                    break;
                }

                default:
                    // Si llegó algo inesperado, intentar insertar como texto
                    insertText(comando);
                    break;
            }
        } catch (ArithmeticException ax) {
            display.setText("Error: " + ax.getMessage());
            nuevoInput = true;
        } catch (Exception ex) {
            display.setText("Error");
            nuevoInput = true;
        }
    }

    // Inserta texto en el display (respetando nuevoInput)
    private void insertText(String txt) {
        String cur = display.getText();
        if (nuevoInput || "0".equals(cur)) {
            display.setText(txt);
            nuevoInput = false;
        } else {
            display.setText(cur + txt);
            nuevoInput = false;
        }
    }

    // Inserta un operador; si el último carácter ya es un operador, lo reemplaza.
    private void insertOperator(String op) {
        String cur = display.getText();
        if (cur.isEmpty()) {
            display.setText("0" + op);
            nuevoInput = false;
            return;
        }
        // Mapa visual: dejamos los símbolos tal cual para mostrar al usuario
        char last = cur.charAt(cur.length() - 1);
        if (isOperatorChar(last)) {
            // reemplazar operador final
            display.setText(cur.substring(0, cur.length() - 1) + op);
        } else {
            display.setText(cur + op);
        }
        nuevoInput = false;
    }

    private boolean isOperatorChar(char c) {
        return c == '+' || c == '-' || c == '×' || c == '÷' || c == '^' || c == 'x' || c == 'y';
    }

    // Envuelve el último operando con prefijo + sufijo, por ejemplo "1/(" + operand + ")"
    private void wrapLastOperandWith(String prefix, String suffix) {
        String cur = display.getText();
        int[] range = findLastOperandRange(cur);
        if (range == null) {
            // si no hay operando, aplicar a todo
            display.setText(prefix + cur + suffix);
        } else {
            String before = cur.substring(0, range[0]);
            String operand = cur.substring(range[0], range[1] + 1);
            display.setText(before + prefix + operand + suffix);
        }
        nuevoInput = false;
    }

    // Square last operand by replacing it with (op*op)
    private void squareLastOperand() {
        String cur = display.getText();
        int[] range = findLastOperandRange(cur);
        if (range == null) {
            display.setText("(0*0)");
        } else {
            String before = cur.substring(0, range[0]);
            String operand = cur.substring(range[0], range[1] + 1);
            display.setText(before + "(" + operand + "*" + operand + ")");
        }
        nuevoInput = false;
    }

    // Percent: replace last operand with (operand/100)
    private void percentLastOperand() {
        String cur = display.getText();
        int[] range = findLastOperandRange(cur);
        if (range == null) {
            display.setText("(0/100)");
        } else {
            String before = cur.substring(0, range[0]);
            String operand = cur.substring(range[0], range[1] + 1);
            display.setText(before + "(" + operand + "/100)");
        }
        nuevoInput = false;
    }

    // Toggle sign of last operand: if operand is "(...)" and already negative wrapper, remove; else wrap with (-...)
    private void toggleSignLastOperand() {
        String cur = display.getText();
        int[] range = findLastOperandRange(cur);
        if (range == null) {
            // whole display
            String s = cur;
            if (s.startsWith("(-") && s.endsWith(")")) {
                display.setText(s.substring(2, s.length()-1));
            } else {
                display.setText("(-" + s + ")");
            }
        } else {
            String before = cur.substring(0, range[0]);
            String operand = cur.substring(range[0], range[1] + 1);
            if (operand.startsWith("(-") && operand.endsWith(")")) {
                operand = operand.substring(2, operand.length() - 1);
                display.setText(before + operand);
            } else {
                display.setText(before + "(-" + operand + ")");
            }
        }
        nuevoInput = false;
    }

    /**
     * Encuentra el rango [start,end] del último operando en la expresión.
     * Si la expresión termina en ')', busca el paréntesis de apertura correspondiente.
     * Si termina en dígitos/., toma la secuencia de dígitos y puntos.
     * Devuelve null si no encuentra un operando claro.
     */
    private int[] findLastOperandRange(String expr) {
        if (expr == null || expr.isEmpty()) return null;
        int i = expr.length() - 1;
        char c = expr.charAt(i);

        // Si termina en paréntesis de cierre, encontrar '(' correspondiente
        if (c == ')') {
            int depth = 0;
            for (int j = i; j >= 0; j--) {
                char ch = expr.charAt(j);
                if (ch == ')') depth++;
                else if (ch == '(') {
                    depth--;
                    if (depth == 0) {
                        return new int[]{j, i};
                    }
                }
            }
            return null; // paréntesis desbalanceados
        }

        // Si termina en dígito o '.', recoger la secuencia
        if (Character.isDigit(c) || c == '.') {
            int j = i;
            while (j >= 0 && (Character.isDigit(expr.charAt(j)) || expr.charAt(j) == '.')) j--;
            // incluir posible signo unario inmediatamente anterior
            if (j >= 0 && expr.charAt(j) == '-') {
                // asegurarnos que ese '-' es parte del número (precedido por operador o inicio)
                if (j == 0 || isOperatorChar(expr.charAt(j-1)) || expr.charAt(j-1) == '(') {
                    // incluir '-'
                    return new int[]{j, i};
                }
            }
            return new int[]{j + 1, i};
        }

        // Si termina en letra (ej. parte de "sin(" si el usuario no escribió el '('), no consideramos operando
        return null;
    }

    /**
     * Evalúa la expresión completa que está en el display:
     *  - procesa funciones (sin, cos, tan, sinh, cosh, tanh, log, ln, exp, √)
     *  - procesa porcentajes que hallamos dejado como (n/100) o las que el usuario escribió
     *  - mapea símbolos visuales × ÷ a * /
     *  - finalmente llama a Parentesis.evaluar para calcular
     */
    private double evaluateExpressionFull(String exprOriginal) {
        if (exprOriginal == null || exprOriginal.trim().isEmpty()) return 0.0;
        String expr = exprOriginal;

        // Mapeo visual -> parser
        expr = expr.replace("×", "*").replace("÷", "/");

        // Reemplazar secuencias de espacio accidental
        expr = expr.replaceAll("\\s+", "");

        // 1) Resolver funciones anidadas: buscar la función más interna (última aparición de "func(")
        String[] funciones = {"sin","cos","tan","sinh","cosh","tanh","log","ln","exp"};
        // también manejamos "√(" como función raíz
        while (true) {
            int lastFuncPos = -1;
            String foundFunc = null;
            for (String f : funciones) {
                int pos = expr.lastIndexOf(f + "(");
                if (pos > lastFuncPos) {
                    lastFuncPos = pos;
                    foundFunc = f;
                }
            }
            int posSqrt = expr.lastIndexOf("√(");
            if (posSqrt > lastFuncPos) {
                lastFuncPos = posSqrt;
                foundFunc = "√";
            }

            if (lastFuncPos == -1) break; // no hay funciones

            int startArgs = lastFuncPos + (foundFunc.equals("√") ? 2 : foundFunc.length() + 1); // índice del primer char del argumento
            // encontrar paréntesis de cierre correspondiente (desde startArgs-1 que es '(' )
            int openIndex = (foundFunc.equals("√") ? lastFuncPos + 1 : lastFuncPos + foundFunc.length());
            if (openIndex >= expr.length() || expr.charAt(openIndex) != '(') {
                throw new IllegalArgumentException("Función mal formateada");
            }
            int closeIndex = findMatchingParen(expr, openIndex);
            if (closeIndex == -1) throw new IllegalArgumentException("Paréntesis desbalanceados en función");

            String inside = expr.substring(openIndex + 1, closeIndex);
            // Evaluar recursivamente el contenido de la función
            double valInside = evaluateExpressionFull(inside);

            // Aplicar la función correspondiente (para trig usamos grados por consistencia con la UI)
            double res;
            switch (foundFunc) {
                case "sin": res = Math.sin(Math.toRadians(valInside)); break;
                case "cos": res = Math.cos(Math.toRadians(valInside)); break;
                case "tan": res = Math.tan(Math.toRadians(valInside)); break;
                case "sinh": res = Math.sinh(valInside); break;
                case "cosh": res = Math.cosh(valInside); break;
                case "tanh": res = Math.tanh(valInside); break;
                case "log": res = Math.log10(valInside); break;
                case "ln": res = Math.log(valInside); break;
                case "exp": res = Math.exp(valInside); break;
                case "√":
                    if (valInside < 0) throw new ArithmeticException("Raíz de negativo");
                    res = Math.sqrt(valInside); break;
                default: res = valInside; break;
            }

            // <<-- Aquí estaba el problema: Double.toString(res) podía producir notación científica
            // Reemplazamos por formatNumber(res) para evitar 'E' y que Parentesis pueda parsear -->
            String before = expr.substring(0, lastFuncPos);
            String after = expr.substring(closeIndex + 1);
            expr = before + formatNumber(res) + after;
        }

        // 2) Procesar porcentajes restantes: si hay un '%' suelto (por si el usuario lo escribió), transformarlo:
        // Buscamos '%' desde el final y reemplazamos el operando anterior por (operando/100)
        while (expr.contains("%")) {
            int p = expr.lastIndexOf('%');
            // encontrar operando a la izquierda de p
            int[] range = findLastOperandRangeBeforeIndex(expr, p - 1);
            if (range == null) throw new IllegalArgumentException("Sintaxis de % inválida");
            String before = expr.substring(0, range[0]);
            String operand = expr.substring(range[0], range[1] + 1);
            String after = expr.substring(p + 1);
            expr = before + "(" + operand + "/100)" + after;
        }

        // Ahora expr solo contiene números, paréntesis y operadores * / + -
        // Llamar al evaluador
        return Parentesis.evaluar(expr);
    }

    // Encuentra paréntesis correspondiente ')' para un '(' en la posición openIdx
    private int findMatchingParen(String s, int openIdx) {
        if (s == null || openIdx < 0 || openIdx >= s.length() || s.charAt(openIdx) != '(') return -1;
        int depth = 0;
        for (int i = openIdx; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') depth++;
            else if (c == ')') {
                depth--;
                if (depth == 0) return i;
            }
        }
        return -1;
    }

    // Similar a findLastOperandRange pero buscando el operando que finaliza en o antes de `idx`
    private int[] findLastOperandRangeBeforeIndex(String expr, int idx) {
        if (expr == null || expr.isEmpty() || idx < 0) return null;
        int i = idx;
        // saltar espacios
        while (i >= 0 && Character.isWhitespace(expr.charAt(i))) i--;
        if (i < 0) return null;
        char c = expr.charAt(i);

        if (c == ')') {
            // encontrar '(' correspondiente
            int depth = 0;
            for (int j = i; j >= 0; j--) {
                char ch = expr.charAt(j);
                if (ch == ')') depth++;
                else if (ch == '(') {
                    depth--;
                    if (depth == 0) {
                        return new int[]{j, i};
                    }
                }
            }
            return null;
        }

        if (Character.isDigit(c) || c == '.') {
            int j = i;
            while (j >= 0 && (Character.isDigit(expr.charAt(j)) || expr.charAt(j) == '.')) j--;
            if (j >= 0 && expr.charAt(j) == '-') {
                if (j == 0 || isOperatorChar(expr.charAt(j-1)) || expr.charAt(j-1) == '(') {
                    return new int[]{j, i};
                }
            }
            return new int[]{j + 1, i};
        }
        return null;
    }

    // Formatea salida: si entero, sin .0; si decimal, 8 decimales máx y sin ceros finales.
    private String formatNumber(double v) {
        if (Double.isNaN(v)) return "NaN";
        if (Double.isInfinite(v)) return v > 0 ? "Infinity" : "-Infinity";
        if (v == (long) v) return String.format("%d", (long) v);
        String s = String.format("%.8f", v);
        s = s.replaceAll("\\.?0+$", "");
        return s;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraCientificaFuncional());
    }
}
