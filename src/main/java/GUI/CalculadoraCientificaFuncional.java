/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author cript
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup; 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * Clase que crea la interfaz y la funcionalidad de una calculadora científica.
 */
public class CalculadoraCientificaFuncional extends JFrame implements ActionListener {

    private JTextField display;

    // Variables para almacenar los operandos y la operación
    private double primerNumero;
    private String operador;
    private boolean nuevoInput; // Para controlar si se debe limpiar la pantalla

    // --- CÓDIGO AÑADIDO: Variable para el modo de ángulo ---
    private String modoAnguloActual = "DEG";

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

        //Panel intermedio para organizar los botones
        JPanel panelCentral = new JPanel(new BorderLayout());

        //Panel con los botones de modo de ángulo
        JPanel panelModoAngulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JRadioButton degRadio = new JRadioButton("DEG");
        degRadio.setSelected(true);
        JRadioButton radRadio = new JRadioButton("RAD");
        JRadioButton gradRadio = new JRadioButton("GRAD");

        ButtonGroup grupoAngulo = new ButtonGroup();
        grupoAngulo.add(degRadio);
        grupoAngulo.add(radRadio);
        grupoAngulo.add(gradRadio);

        degRadio.addActionListener(e -> modoAnguloActual = "DEG");
        radRadio.addActionListener(e -> modoAnguloActual = "RAD");
        gradRadio.addActionListener(e -> modoAnguloActual = "GRAD");

        panelModoAngulo.add(degRadio);
        panelModoAngulo.add(radRadio);
        panelModoAngulo.add(gradRadio);
        
        panelCentral.add(panelModoAngulo, BorderLayout.NORTH); // Añadimos el panel de angulos arriba

        // --- Panel de Botones ---
        JPanel panelBotones = new JPanel(new GridLayout(6, 6, 5, 5));

        // Etiquetas de los botones según las funciones solicitadas
        String[] botones = {
                "sin", "cos", "tan", "asin", "acos", "atan",
                "xʸ", "√", "∛", "x√y", "ln", "log",
                "eˣ", "10ˣ", "1/x", "n!", "%", "C",
                "7", "8", "9", "/", "CE", "±",
                "4", "5", "6", "*", "1", "2",
                "3", "-", "0", ".", "=", "+"
        };

        for (String textoBoton : botones) {
            JButton boton = new JButton(textoBoton);
            boton.setFont(new Font("Arial", Font.BOLD, 16));
            boton.addActionListener(this); // Añadimos el listener a cada botón

            if (textoBoton.equals("=")) {
                boton.setBackground(new Color(0, 150, 0));
                boton.setForeground(Color.WHITE);
            } else if (textoBoton.matches("[C]|CE")) {
                boton.setBackground(new Color(200, 50, 50));
                boton.setForeground(Color.WHITE);
            } else if (textoBoton.matches("[\\+\\-*%/]")) {
                boton.setBackground(new Color(240, 240, 240));
            }
            panelBotones.add(boton);
        }

        // Se añade el panel de botones al panel central
        panelCentral.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

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
                case "0": case "1": case "2": case "3": case "4":
                case "5": case "6": case "7": case "8": case "9":
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

                // Trigonométricas (en radianes) 
                case "sin":
                   // Modificado
                    double anguloSin = Double.parseDouble(textoDisplay);
                    if (modoAnguloActual.equals("DEG")) {
                        anguloSin = Math.toRadians(anguloSin);
                    } else if (modoAnguloActual.equals("GRAD")) {
                        anguloSin = anguloSin * (Math.PI / 200.0);
                    }
                    display.setText(String.valueOf(Math.sin(anguloSin)));
                    break;
                case "cos":
                    // Modificado
                    double anguloCos = Double.parseDouble(textoDisplay);
                    if (modoAnguloActual.equals("DEG")) {
                        anguloCos = Math.toRadians(anguloCos);
                    } else if (modoAnguloActual.equals("GRAD")) {
                        anguloCos = anguloCos * (Math.PI / 200.0);
                    }
                    display.setText(String.valueOf(Math.cos(anguloCos)));
                    break;
                case "tan":
                  // Modificado 
                    double anguloTan = Double.parseDouble(textoDisplay);
                    if (modoAnguloActual.equals("DEG")) {
                        anguloTan = Math.toRadians(anguloTan);
                    } else if (modoAnguloActual.equals("GRAD")) {
                        anguloTan = anguloTan * (Math.PI / 200.0);
                    }
                    display.setText(String.valueOf(Math.tan(anguloTan)));
                    break;
                case "asin":
                   // Modificado
                    double valorAsin = Double.parseDouble(textoDisplay);
                    double resultadoAsin = Math.asin(valorAsin); // Resultado en radianes
                    if (modoAnguloActual.equals("DEG")) {
                        resultadoAsin = Math.toDegrees(resultadoAsin);
                    } else if (modoAnguloActual.equals("GRAD")) {
                        resultadoAsin = resultadoAsin * (200.0 / Math.PI);
                    }
                    display.setText(String.valueOf(resultadoAsin));
                    break;
                case "acos":
                   // Modificado
                    double valorAcos = Double.parseDouble(textoDisplay);
                    double resultadoAcos = Math.acos(valorAcos); // Resultado en radianes
                    if (modoAnguloActual.equals("DEG")) {
                        resultadoAcos = Math.toDegrees(resultadoAcos);
                    } else if (modoAnguloActual.equals("GRAD")) {
                        resultadoAcos = resultadoAcos * (200.0 / Math.PI);
                    }
                    display.setText(String.valueOf(resultadoAcos));
                    break;
                case "atan":
                    // Modificado
                    double valorAtan = Double.parseDouble(textoDisplay);
                    double resultadoAtan = Math.atan(valorAtan); // Resultado en radianes
                    if (modoAnguloActual.equals("DEG")) {
                        resultadoAtan = Math.toDegrees(resultadoAtan);
                    } else if (modoAnguloActual.equals("GRAD")) {
                        resultadoAtan = resultadoAtan * (200.0 / Math.PI);
                    }
                    display.setText(String.valueOf(resultadoAtan));
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
        if (n > 20) return -1; // Evitar overflow de tipo long
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

