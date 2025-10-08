/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author cript
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
        panelPrincipal.add(display, BorderLayout.NORTH);

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
            "n!", "%", "C", "CE", "±", "/",
            // Fila 5 - Números 7 8 9
            "7", "8", "9", "*", "(", ")",
            // Fila 6 - Números 4 5 6
            "4", "5", "6", "-", " ", " ",
            // Fila 7 - Números 1 2 3 0 . =
            "1", "2", "3", "+", "0", "."
        };

        for (String textoBoton : botones) {
            JButton boton = new JButton(textoBoton);
            boton.setFont(new Font("Arial", Font.BOLD, 16));
            boton.addActionListener(this); // Añadimos el listener a cada botón

            if (textoBoton.equals("=")) {
                boton.setBackground(new Color(0, 150, 0));
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
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "xʸ":
                case "x√y":
                    calcular();
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
                case "√":
                    primerNumero = Double.parseDouble(textoDisplay);
                    display.setText(String.valueOf(Math.sqrt(primerNumero)));
                    break;
                case "∛":
                    primerNumero = Double.parseDouble(textoDisplay);
                    display.setText(String.valueOf(Math.cbrt(primerNumero)));
                    break;
                case "x²": // Este no estaba en la lista pero es común, lo añado como ejemplo
                    primerNumero = Double.parseDouble(textoDisplay);
                    display.setText(String.valueOf(primerNumero * primerNumero));
                    break;
                case "1/x":
                    primerNumero = Double.parseDouble(textoDisplay);
                    if (primerNumero == 0) {
                        display.setText("Error: División por cero");
                    } else {
                        display.setText(String.valueOf(1 / primerNumero));
                    }
                    break;
                case "±":
                    primerNumero = Double.parseDouble(textoDisplay);
                    display.setText(String.valueOf(-primerNumero));
                    break;
                case "n!":
                    primerNumero = Double.parseDouble(textoDisplay);
                    if (primerNumero >= 0 && primerNumero == (int) primerNumero) {
                        display.setText(String.valueOf(factorial((int) primerNumero)));
                    } else {
                        display.setText("Error: n! solo para enteros >= 0");
                    }
                    break;
                // --- Logaritmos y Exponenciales ---
                case "ln":
                    display.setText(String.valueOf(Math.log(Double.parseDouble(textoDisplay))));
                    break;
                case "log":
                    display.setText(String.valueOf(Math.log10(Double.parseDouble(textoDisplay))));
                    break;
                case "eˣ":
                    display.setText(String.valueOf(Math.exp(Double.parseDouble(textoDisplay))));
                    break;
                case "10ˣ":
                    display.setText(String.valueOf(Math.pow(10, Double.parseDouble(textoDisplay))));
                    break;

                // --- Trigonométricas (en radianes) ---
                case "sin":
                    display.setText(String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(textoDisplay)))));
                    break;
                case "cos":
                    display.setText(String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(textoDisplay)))));
                    break;
                case "tan":
                    display.setText(String.valueOf(Math.tan(Math.toRadians(Double.parseDouble(textoDisplay)))));
                    break;
                case "asin":
                    display.setText(String.valueOf(Math.toDegrees(Math.asin(Double.parseDouble(textoDisplay)))));
                    break;
                case "acos":
                    display.setText(String.valueOf(Math.toDegrees(Math.acos(Double.parseDouble(textoDisplay)))));
                    break;
                case "atan":
                    display.setText(String.valueOf(Math.toDegrees(Math.atan(Double.parseDouble(textoDisplay)))));
                    break;

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

        // Formateamos para no mostrar ".0" en números enteros
        if (resultado == (long) resultado) {
            display.setText(String.format("%d", (long) resultado));
        } else {
            display.setText(String.format("%s", resultado));
        }

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


/*
sadkjfa
 
 
 
 */
