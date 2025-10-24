/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
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

public class CalculadoraCientificaFuncional extends JFrame implements ActionListener {

    private JTextField display;
    private double primerNumero;
    private String operador;
    private boolean nuevoInput;
    private DefaultListModel<String> historyModel;
    private JList<String> historyList;
    private JDialog dialogHistorial;
    private JButton btnHist;
    private static final int HISTORY_LIMIT = 100;

    public CalculadoraCientificaFuncional() {
        setTitle("Calculadora Científica Funcional");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        display = new JTextField("0");
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(new Color(50, 255, 50));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Monospaced", Font.BOLD, 40));

        JPanel northPanel = new JPanel(new BorderLayout(5,5));
        northPanel.add(display, BorderLayout.CENTER);
        btnHist = new JButton("Hist");
        btnHist.setFont(new Font("Arial", Font.BOLD, 12));
        btnHist.addActionListener((ActionEvent e) -> {
            dialogHistorial.setVisible(true);
        });
        northPanel.add(btnHist, BorderLayout.EAST);
        panelPrincipal.add(northPanel, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(7, 6, 5, 5));
        
        String[] botones = {
            "sin", "cos", "tan", "asin", "acos", "atan",
            "xʸ", "√", "3√x", "x√y", "ln", "log",
            "eˣ", "10ˣ", "1/x", "n!", "%", "C",
            "7", "8", "9", "/", "CE", "±",
            "4", "5", "6", "*", "(", ")",
            "1", "2", "3", "-", "0", ".",
            "=", "+","<-",
        };

        for (String textoBoton : botones) {
            JButton boton = new JButton(textoBoton);
            boton.setFont(new Font("Arial", Font.BOLD, 16));
            boton.addActionListener(this);

            if (textoBoton.equals("=")) {
                boton.setBackground(new Color(0, 150, 0));
                boton.setForeground(Color.WHITE);
            } else if (textoBoton.matches("C|CE")) {
                boton.setBackground(new Color(200, 50, 50));
                boton.setForeground(Color.WHITE);
            } else if (textoBoton.matches("[\\+\\-*%/]")) {
                boton.setBackground(new Color(240, 240, 240));
            }
            
            panelBotones.add(boton);
        }

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        add(panelPrincipal);

        nuevoInput = true;
        operador = "";
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
        
        // Verificar si estamos en modo expresión (con paréntesis)
        boolean modoExpresion = textoDisplay.contains("(") || textoDisplay.contains(")");

        try {
            switch (comando) {
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
                    if (modoExpresion) {
                        display.setText(textoDisplay + comando);
                        nuevoInput = false;
                    } else {
                        if (nuevoInput) {
                            display.setText(comando);
                            nuevoInput = false;
                        } else {
                            display.setText(textoDisplay + comando);
                        }
                    }
                    break;

                case ".":
                    if (modoExpresion) {
                        display.setText(textoDisplay + ".");
                    } else {
                        if (nuevoInput) {
                            display.setText("0.");
                            nuevoInput = false;
                        } else if (!textoDisplay.contains(".")) {
                            display.setText(textoDisplay + ".");
                        }
                    }
                    break;

                case "+":
                case "-":
                case "*":
                case "/":
                    if (modoExpresion) {
                        // En modo expresión, solo agregar el operador
                        display.setText(textoDisplay + comando);
                        nuevoInput = false;
                    } else {
                        // Modo tradicional
                        if (!operador.isEmpty()) {
                            calcular();
                        }
                        operador = comando;
                        primerNumero = Double.parseDouble(display.getText());
                        nuevoInput = true;
                    }
                    break;

                case "%":
                case "xʸ":
                case "x√y":
                    if (modoExpresion) {
                        // No se pueden usar estos operadores en modo expresión
                        display.setText("Error: Operador no válido con paréntesis");
                        nuevoInput = true;
                    } else {
                        if (!operador.isEmpty()) {
                            calcular();
                        }
                        operador = comando;
                        primerNumero = Double.parseDouble(display.getText());
                        nuevoInput = true;
                    }
                    break;

                case "=":
                    try {
                        String expr = display.getText();
                        
                        if (expr.contains("(") || expr.contains(")")) {
                            double resultado = Parentesis.evaluar(expr);
                            display.setText(formatNumber(resultado));
                            addToHistory(expr + " = " + formatNumber(resultado));
                            operador = "";
                            primerNumero = 0;
                            nuevoInput = true;
                        } else if (!operador.isEmpty()) {
                            calcular();
                        } else {
                            nuevoInput = true;
                        }
                    } catch (ArithmeticException exArit) {
                        display.setText("Error: " + exArit.getMessage());
                        operador = "";
                        nuevoInput = true;
                    } catch (IllegalArgumentException exArg) {
                        display.setText("Error: " + exArg.getMessage());
                        operador = "";
                        nuevoInput = true;
                    } catch (Exception exGen) {
                        display.setText("Error de sintaxis");
                        operador = "";
                        nuevoInput = true;
                    }
                    break;

                case "√": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        double res = Math.sqrt(in);
                        display.setText(formatNumber(res));
                        addToHistory("√(" + formatNumber(in) + ") = " + formatNumber(res));
                        nuevoInput = true;
                    }
                    break;
                }
                
                case "3√x": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        RaizCubica rc = new RaizCubica();
                        double in = Double.parseDouble(textoDisplay);
                        rc.setPrimerNumero(in);
                        double res = rc.raizCubica();
                        display.setText(formatNumber(res));
                        addToHistory("³√(" + formatNumber(in) + ") = " + formatNumber(res));
                        nuevoInput = true;
                    }
                    break;
                }
                
                case "1/x": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
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
                    }
                    break;
                }
                
                case "±": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        CambioSigno operacion = new CambioSigno(in);
                        double res = operacion.calcularCambioSigno();
                        display.setText(formatNumber(res));
                        nuevoInput = false;
                    }
                    break;
                }
                
                case "n!": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        if (in >= 0 && in == (int) in && in <= 20) {
                            long fact = factorial((int) in);
                            display.setText(String.valueOf(fact));
                            addToHistory((int) in + "! = " + fact);
                        } else {
                            display.setText("Error: n! solo para enteros >= 0 (<=20)");
                        }
                        nuevoInput = true;
                    }
                    break;
                }

                case "ln": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        double res = Math.log(in);
                        display.setText(formatNumber(res));
                        addToHistory("ln(" + formatNumber(in) + ") = " + formatNumber(res));
                        nuevoInput = true;
                    }
                    break;
                }
                
                case "log": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        double res = Math.log10(in);
                        display.setText(formatNumber(res));
                        addToHistory("log(" + formatNumber(in) + ") = " + formatNumber(res));
                        nuevoInput = true;
                    }
                    break;
                }
                
                case "eˣ": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        double res = Math.exp(in);
                        display.setText(formatNumber(res));
                        addToHistory("e^(" + formatNumber(in) + ") = " + formatNumber(res));
                        nuevoInput = true;
                    }
                    break;
                }
                
                case "10ˣ": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        // Puedes usar directamente Math.pow o tu clase
                        double res = PotenciaBase10.calcularPotencia(in);
                        display.setText(formatNumber(res));
                        addToHistory("10^(" + formatNumber(in) + ") = " + formatNumber(res));
                        nuevoInput = true;
                    }
                    break;
}

                case "sin": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        double res = Seno.calcularSeno(in);
                        display.setText(formatNumber(res));
                        addToHistory("sin(" + formatNumber(in) + "°) = " + formatNumber(res));
                        nuevoInput = true;
                    }
                    break;
                }
                
                case "cos": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        double res = Math.cos(Math.toRadians(in));
                        display.setText(formatNumber(res));
                        addToHistory("cos(" + formatNumber(in) + "°) = " + formatNumber(res));
                        nuevoInput = true;
                    }
                    break;
                }
                
                case "tan": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        FuncionTangente tan = new FuncionTangente(in);
                        double res = tan.calcularTangente();
                        display.setText(formatNumber(res));
                        addToHistory("tan(" + formatNumber(in) + "°) = " + formatNumber(res));
                        nuevoInput = true;
                    }
                    break;
                }
                
                case "asin": {
                   if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        ArcoSeno arcoseno = new ArcoSeno(); // Cambiar esta línea
                        if (in < -1 || in > 1) {
                            display.setText("MathERROR");
                        } else {
                            double res = arcoseno.calcularArcoSeno(in); // Y esta
                            display.setText(formatNumber(res));
                            addToHistory("asin(" + formatNumber(in) + ") = " + formatNumber(res) + "°");
                        }
                        nuevoInput = true;
                    }
                    break;
                }
                
                case "acos": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        ArcoCoseno arcocoseno = new ArcoCoseno();
                        if (in < -1 || in > 1) {
                            display.setText("MathERROR");
                        } else {
                            double res = arcocoseno.calcularArcoCoseno(in);
                            display.setText(formatNumber(res));
                            addToHistory("acos(" + formatNumber(in) + ") = " + formatNumber(res) + "°");
                        }
                        nuevoInput = true;
                    }
                    break;
                }
                
                case "atan": {
                    if (modoExpresion) {
                        display.setText("Error: Use primero '='");
                        nuevoInput = true;
                    } else {
                        double in = Double.parseDouble(textoDisplay);
                        Arcotangente arcotangente = new Arcotangente();
                        double res = Math.toDegrees(arcotangente.calcularArcotangente(in));
                        display.setText(formatNumber(res));
                        addToHistory("atan(" + formatNumber(in) + ") = " + formatNumber(res) + "°");
                        nuevoInput = true;
                    }
                    break;
                }

                case "(":
                case ")":
                    if (nuevoInput && comando.equals("(")) {
                        display.setText(comando);
                        nuevoInput = false;
                    } else {
                        display.setText(textoDisplay + comando);
                    }
                    break;

                case "C":
                    primerNumero = 0;
                    operador = "";
                    display.setText("0");
                    nuevoInput = true;
                    break;
                    
                case "CE":
                    textoDisplay = BotonCE.limpiarEntradaActual(textoDisplay);
                    display.setText(textoDisplay);
                    nuevoInput = textoDisplay.equals("0");
                    break;
                    
                case "<-":
                    display.setText(RetrocesUltimoDigito.borrarUltimoCaracter(textoDisplay));
                    break;
            }
        } catch (NumberFormatException ex) {
            display.setText("Error: Entrada inválida");
            nuevoInput = true;
        } catch (Exception ex) {
            display.setText("Error: " + ex.getMessage());
            nuevoInput = true;
        }
    }

    private void calcular() {
        try {
            if (operador.isEmpty() || nuevoInput) {
                return;
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
                        operador = "";
                        return;
                    }
                    resultado = primerNumero / segundoNumero;
                    break;
                case "%":
                    resultado = primerNumero % segundoNumero;
                    break;
                case "xʸ":
                    Exponencial expo = new Exponencial(); 
                    resultado = expo.Expocinencial(primerNumero, segundoNumero);
                    break;
                case "x√y":
                    resultado = Math.pow(segundoNumero, 1.0 / primerNumero);
                    break;
            }

            String entradaHistorial = formatNumber(primerNumero) + " " + operador + " " 
                                    + formatNumber(segundoNumero) + " = " + formatNumber(resultado);
            addToHistory(entradaHistorial);

            display.setText(formatNumber(resultado));
            primerNumero = resultado;
            operador = "";
            nuevoInput = true;
        } catch (Exception ex) {
            display.setText("Error");
            operador = "";
            nuevoInput = true;
        }
    }

    private long factorial(int n) {
        if (n > 20) {
            return -1;
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