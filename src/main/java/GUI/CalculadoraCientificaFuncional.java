/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author cript
 */
import Modelo.RetrocesUltimoDigito;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import Modelo.*;

/**
 * Clase que crea la interfaz y la funcionalidad de una calculadora científica.
 */
public class CalculadoraCientificaFuncional extends JFrame implements ActionListener {

    private JTextField display;

    // Variables para almacenar los operandos y la operación
    private double primerNumero;
    private String operador;
    private boolean nuevoInput; // Para controlar si se debe limpiar la pantalla

    // Historial
    private DefaultListModel<String> historyModel;
    private JList<String> historyList;
    private JDialog dialogHistorial;
    private JButton btnHist;
    private static final int HISTORY_LIMIT = 100;

    public CalculadoraCientificaFuncional() {
        // --- Configuración de la Ventana (JFrame) ---
        setTitle("Calculadora Científica Funcional");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // --- Pantalla de la Calculadora ---
        display = new JTextField("0");
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(new Color(50, 255, 50)); // Verde lima
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Monospaced", Font.BOLD, 40));

        // Panel superior: display + botón Hist
        JPanel northPanel = new JPanel(new BorderLayout(5,5));
        northPanel.add(display, BorderLayout.CENTER);
        btnHist = new JButton("Hist");
        btnHist.setFont(new Font("Arial", Font.BOLD, 12));
        btnHist.addActionListener((ActionEvent e) -> {
            dialogHistorial.setVisible(true);
        });
        northPanel.add(btnHist, BorderLayout.EAST);
        panelPrincipal.add(northPanel, BorderLayout.NORTH);

// --- Panel de Botones ---
        JPanel panelBotones = new JPanel(new GridLayout(7, 6, 5, 5));

// --- Etiquetas de los botones según las funciones solicitadas ---
        String[] botones = {
            // Fila 1 - Trigonométricas
            "sin", "cos", "tan", "asin", "acos", "atan",
            // Fila 2 - Hiperbólicas
            "sinh", "cosh", "tanh", "ln", "log", "eˣ",
            // Fila 3 - Potencias y raíces
            "xʸ", "√", "∛", "x√y", "10ˣ", "1/x",
            // Fila 4 - Factorial, porcentaje y clear
            "n!", "%", "C", "CE","<-" , "±", "/",
            // Fila 5 - Números 7 8 9
            "7", "8", "9", "*", "(", ")",
            // Fila 6 - Números 4 5 6
            "4", "5", "6", "-", "=", " ",
            // Fila 7 - Números 1 2 3 0 . =
            "1", "2", "3", "0", "+", "."
        };

        for (String textoBoton : botones) {
            JButton boton = new JButton(textoBoton);
            boton.setFont(new Font("Arial", Font.BOLD, 16));
            boton.addActionListener(this); // Añadimos el listener a cada botón

            if (textoBoton.equals("=")) {
                boton.setBackground(new Color(0, 150, 0));
                boton.setForeground(Color.BLACK);
            } else if (textoBoton.matches("C|CE")) {
                boton.setForeground(Color.BLACK);
            } else if (textoBoton.matches("[C]|CE")) {
                boton.setBackground(new Color(200, 50, 50));
                boton.setForeground(Color.BLACK);
            } else if (textoBoton.matches("[\\+\\-*%/]")) {
                boton.setBackground(new Color(240, 240, 240));
            }
            panelBotones.add(boton);
        }

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        add(panelPrincipal);

        // Inicializamos las variables de estado
        nuevoInput = true;
        operador = "";

        // Inicializar historial (modelo + dialog)
        initHistorial();
    }

    private void initHistorial() {
        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);

        dialogHistorial = new JDialog(this, "Historial de Operaciones", false);
        dialogHistorial.setSize(420, 480);
        dialogHistorial.setLocationRelativeTo(this);
        dialogHistorial.setLayout(new BorderLayout(5,5));
        dialogHistorial.add(new JScrollPane(historyList), BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> historyModel.clear());
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialogHistorial.setVisible(false));
        panelSur.add(btnLimpiar);
        panelSur.add(btnCerrar);

        dialogHistorial.add(panelSur, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        String textoDisplay = display.getText();

        try {
            switch (comando) {
                // --- Números ---
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    if (nuevoInput) {
                        display.setText(comando);
                        nuevoInput = false;
                    } else {
                        display.setText(textoDisplay + comando);
                    }
                    break;
                case ".":
                    if (nuevoInput) {
                        display.setText("0.");
                        nuevoInput = false;
                    } else if (!textoDisplay.contains(".")) {
                        display.setText(textoDisplay + ".");
                    }
                    break;

                // --- Operadores Binarios (+, -, *, /, %, x^y, x√y) ---
                case "+": case "-": case "*": case "/": case "%":
                case "xʸ": case "x√y":
                    calcular(); // resuelve operación pendiente antes de cambiar el operador
                    operador = comando;
                    primerNumero = Double.parseDouble(display.getText());
                    nuevoInput = true;
                    break;

                // --- Botón de Igual ---
                case "=":
                    calcular();
                    operador = "";
                    break;

                // --- Operadores Unarios (operan sobre el número actual) ---
                case "√": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.sqrt(in);
                    display.setText(formatNumber(res));
                    addToHistory("√(" + formatNumber(in) + ") = " + formatNumber(res));
                    nuevoInput = true;
                    break;
                }
                case "∛": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.cbrt(in);
                    display.setText(formatNumber(res));
                    addToHistory("∛(" + formatNumber(in) + ") = " + formatNumber(res));
                    nuevoInput = true;
                    break;
                }
                case "x²": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = in * in;
                    display.setText(formatNumber(res));
                    addToHistory(formatNumber(in) + "² = " + formatNumber(res));
                    nuevoInput = true;
                    break;
                }
                case "1/x": {
                    double in = Double.parseDouble(textoDisplay);
                    if (in == 0) {
                        display.setText("Error: División por cero");
                        nuevoInput = true;
                    } else {
                        double res = 1.0 / in;
                        display.setText(formatNumber(res));
                        addToHistory("1/(" + formatNumber(in) + ") = " + formatNumber(res));
                        nuevoInput = true;
                    }
                    break;
                }
                case "±": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = -in;
                    display.setText(formatNumber(res));
                    addToHistory("±(" + formatNumber(in) + ") = " + formatNumber(res));
                    break;
                }
                case "n!": {
                    double in = Double.parseDouble(textoDisplay);
                    if (in >= 0 && in == (int) in && in <= 20) {
                        long fact = factorial((int) in);
                        display.setText(String.valueOf(fact));
                        addToHistory((int) in + "! = " + fact);
                    } else {
                        display.setText("Error: n! solo para enteros >= 0 (<=20)");
                    }
                    nuevoInput = true;
                    break;
                }
                // --- Logaritmos y Exponenciales ---
                case "ln": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.log(in);
                    display.setText(formatNumber(res));
                    addToHistory("ln(" + formatNumber(in) + ") = " + formatNumber(res));
                    nuevoInput = true;
                    break;
                }
                case "log": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.log10(in);
                    display.setText(formatNumber(res));
                    addToHistory("log(" + formatNumber(in) + ") = " + formatNumber(res));
                    nuevoInput = true;
                    break;
                }
                case "eˣ": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.exp(in);
                    display.setText(formatNumber(res));
                    addToHistory("e^(" + formatNumber(in) + ") = " + formatNumber(res));
                    nuevoInput = true;
                    break;
                }
                case "10ˣ": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.pow(10, in);
                    display.setText(formatNumber(res));
                    addToHistory("10^(" + formatNumber(in) + ") = " + formatNumber(res));
                    nuevoInput = true;
                    break;
                }

                // --- Trigonométricas (usamos entrada en grados por usabilidad) ---
                case "sin": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.sin(Math.toRadians(in));
                    display.setText(formatNumber(res));
                    addToHistory("sin(" + formatNumber(in) + "°) = " + formatNumber(res));
                    nuevoInput = true;
                    break;
                }
                case "cos": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.cos(Math.toRadians(in));
                    display.setText(formatNumber(res));
                    addToHistory("cos(" + formatNumber(in) + "°) = " + formatNumber(res));
                    nuevoInput = true;
                    break;
                }
                case "tan": {
                     double in = Double.parseDouble(textoDisplay);
                    FuncionTangente tan = new FuncionTangente(in);
                    double res = tan.calcularTangente();
                    display.setText(formatNumber(res));
                    addToHistory("tan(" + formatNumber(in) + "°) = " + formatNumber(res));
                    nuevoInput = true;
                    break;
                }
                case "asin": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.toDegrees(Math.asin(in));
                    display.setText(formatNumber(res));
                    addToHistory("asin(" + formatNumber(in) + ") = " + formatNumber(res) + "°");
                    nuevoInput = true;
                    break;
                }
                case "acos": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.toDegrees(Math.acos(in));
                    display.setText(formatNumber(res));
                    addToHistory("acos(" + formatNumber(in) + ") = " + formatNumber(res) + "°");
                    nuevoInput = true;
                    break;
                }
                case "atan": {
                    double in = Double.parseDouble(textoDisplay);
                    double res = Math.toDegrees(Math.atan(in));
                    display.setText(formatNumber(res));
                    addToHistory("atan(" + formatNumber(in) + ") = " + formatNumber(res) + "°");
                    nuevoInput = true;
                    break;
                }

                // --- Control ---
                case "C":
                    primerNumero = 0;
                    operador = "";
                    display.setText("0");
                    nuevoInput = true;
                    break;
                case "CE":
                    display.setText("0");
                    nuevoInput = true;
                    break;
                case "<-":
                    display.setText(RetrocesUltimoDigito.borrarUltimoCaracter(textoDisplay));
                    break;

                // --- Funciones hiperbólicas ---
                case "sinh":
                    primerNumero = Double.parseDouble(textoDisplay);
                    FuncHiperb f1 = new FuncHiperb(primerNumero, 1);
                    display.setText(String.valueOf(f1.getOutput()));
                    break;
                case "cosh":
                    primerNumero = Double.parseDouble(textoDisplay);
                    FuncHiperb f2 = new FuncHiperb(primerNumero, 2);
                    display.setText(String.valueOf(f2.getOutput()));
                    break;
                case "tanh":
                    primerNumero = Double.parseDouble(textoDisplay);
                    FuncHiperb f3 = new FuncHiperb(primerNumero, 3);
                    display.setText(String.valueOf(f3.getOutput()));
                    break;
            }
        } catch (NumberFormatException ex) {
            display.setText("Error de sintaxis");
            nuevoInput = true;
        }
    }

    private void calcular() {
        if (operador.isEmpty() || nuevoInput) {
            return; // No hay operación pendiente o es un input nuevo
        }

        double segundoNumero = Double.parseDouble(display.getText());
        double resultado = 0.0;

        switch (operador) {
            case "+":
                resultado = primerNumero + segundoNumero;
                break;
            case "-":
                resultado = primerNumero - segundoNumero;
                break;
            case "*":
                resultado = primerNumero * segundoNumero;
                break;
            case "/":
                if (segundoNumero == 0) {
                    display.setText("Error: División por cero");
                    nuevoInput = true;
                    return;
                }
                resultado = primerNumero / segundoNumero;
                break;
            case "%":
                resultado = primerNumero % segundoNumero;
                break;
            case "xʸ":
                resultado = Math.pow(primerNumero, segundoNumero);
                break;
            case "x√y":
                resultado = Math.pow(primerNumero, 1.0 / segundoNumero);
                break;
        }

        // Guardar en historial (antes de cambiar primerNumero)
        String entradaHistorial = formatNumber(primerNumero) + " " + operador + " " + formatNumber(segundoNumero) + " = " + formatNumber(resultado);
        addToHistory(entradaHistorial);

        // Formateamos para no mostrar ".0" en números enteros
        display.setText(formatNumber(resultado));

        primerNumero = resultado; // Permite encadenar operaciones
        nuevoInput = true;
    }

    private long factorial(int n) {
        if (n > 20) {
            return -1; // Evitar overflow de tipo long
        }
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    private void addToHistory(String entry) {
        if (historyModel == null) return;
        if (historyModel.getSize() >= HISTORY_LIMIT) {
            historyModel.remove(0);
        }
        historyModel.addElement(entry);
    }

    private String formatNumber(double val) {
        if (Double.isNaN(val)) return "NaN";
        if (Double.isInfinite(val)) return val > 0 ? "Infinity" : "-Infinity";
        if (val == (long) val) {
            return String.format("%d", (long) val);
        } else {
            return String.valueOf(val);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CalculadoraCientificaFuncional().setVisible(true);
        });
    }
}
