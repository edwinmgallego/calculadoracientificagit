package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Modelo.Parentesis;

/**
 * Calculadora corregida. Usa Modelo.Parentesis para evaluar expresiones.
 */
public class CalculadoraCientificaFuncional extends JFrame implements ActionListener {

    private JTextField display;
    private double primerNumero = 0;
    private String operador = "";
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
                        // evitar dos puntos en el número final (no perfecto para expresiones complejas)
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
                    primerNumero = 0;
                    operador = "";
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

                // Cambiar signo en número actual (actúa sobre el resultado/evaluación actual)
                case "±": {
                    double val = Parentesis.evaluar(mapForParser(display.getText()));
                    val = -val;
                    display.setText(formatNumber(val));
                    nuevoInput = true;
                    break;
                }

                // Porcentaje (unario): convierte el valor actual a valor/100
                case "%": {
                    double val = Parentesis.evaluar(mapForParser(display.getText()));
                    val = val / 100.0;
                    display.setText(formatNumber(val));
                    nuevoInput = true;
                    break;
                }

                // Operadores binarios: + - × ÷ xʸ
                case "+": case "-": case "×": case "÷": case "xʸ":
                    handleOperator(comando);
                    break;

                // Funciones unarias (evaluar la expresión actual y aplicar la función)
                case "sin": case "cos": case "tan":
                case "sinh": case "cosh": case "tanh":
                case "log": case "ln": case "exp":
                case "√": case "x²": case "1/x": {
                    double val = Parentesis.evaluar(mapForParser(display.getText()));
                    double res;
                    switch (comando) {
                        case "sin": res = Math.sin(Math.toRadians(val)); break;   // grados -> rad
                        case "cos": res = Math.cos(Math.toRadians(val)); break;
                        case "tan": res = Math.tan(Math.toRadians(val)); break;
                        case "sinh": res = Math.sinh(val); break;
                        case "cosh": res = Math.cosh(val); break;
                        case "tanh": res = Math.tanh(val); break;
                        case "log": res = Math.log10(val); break;
                        case "ln": res = Math.log(val); break;
                        case "exp": res = Math.exp(val); break;
                        case "√":
                            if (val < 0) throw new ArithmeticException("Raíz de negativo");
                            res = Math.sqrt(val); break;
                        case "x²": res = Math.pow(val, 2); break;
                        case "1/x":
                            if (val == 0) throw new ArithmeticException("División por cero");
                            res = 1.0 / val; break;
                        default: res = val;
                    }
                    display.setText(formatNumber(res));
                    nuevoInput = true;
                    break;
                }

                // Igual
                case "=": {
                    // Si hay operador pendiente, evaluar operación binaria
                    if (!operador.isEmpty()) {
                        double segundo = Parentesis.evaluar(mapForParser(display.getText()));
                        double total = applyOperator(primerNumero, segundo, operador);
                        display.setText(formatNumber(total));
                        primerNumero = total;
                        operador = "";
                        nuevoInput = true;
                    } else {
                        // No hay operador: evaluar la expresión completa y mostrarla
                        double total = Parentesis.evaluar(mapForParser(display.getText()));
                        display.setText(formatNumber(total));
                        nuevoInput = true;
                    }
                    break;
                }

                default:
                    display.setText("Error");
                    nuevoInput = true;
                    break;
            }
        } catch (ArithmeticException ax) {
            display.setText("Error: " + ax.getMessage());
            nuevoInput = true;
            operador = "";
        } catch (Exception ex) {
            display.setText("Error");
            nuevoInput = true;
            operador = "";
        }
    }

    private void handleOperator(String op) {
        // Si ya hay operador pendiente y no estamos en nuevo input, primero calcula
        String texto = display.getText();
        double actual = Parentesis.evaluar(mapForParser(texto));

        if (!operador.isEmpty() && !nuevoInput) {
            primerNumero = applyOperator(primerNumero, actual, operador);
            display.setText(formatNumber(primerNumero));
        } else {
            primerNumero = actual;
        }

        operador = op;
        nuevoInput = true;
    }

    private double applyOperator(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "×": return a * b;
            case "÷": return a / b;
            case "xʸ": return Math.pow(a, b);
            default: return b;
        }
    }

    // Convierte símbolos visuales antes de pasarlos a Parentesis.evaluar
    private String mapForParser(String displayText) {
        if (displayText == null) return "0";
        return displayText.replace("×", "*").replace("÷", "/");
    }

    // Formatea salida: si entero, sin .0; si decimal, 6 decimales máx y sin ceros finales.
    private String formatNumber(double v) {
        if (Double.isNaN(v)) return "NaN";
        if (Double.isInfinite(v)) return v > 0 ? "Infinity" : "-Infinity";
        if (v == (long) v) return String.format("%d", (long) v);
        String s = String.format("%.8f", v); // 8 decimales para seguridad
        s = s.replaceAll("\\.?0+$", ""); // quitar zeros finales
        return s;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraCientificaFuncional());
    }
}
