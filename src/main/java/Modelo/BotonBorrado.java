/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.JTextField;
/**
 *
 * @author Usuario
 */
public class BotonBorrado {
    
    private JTextField display;
    private double primerNumero;
    private String operador;
    private boolean nuevoInput;

    public BotonBorrado(JTextField display, double primerNumero, String operador, boolean nuevoInput, String opcion) {
        this.display = display;
        this.primerNumero = primerNumero;
        this.operador = operador;
        this.nuevoInput = nuevoInput;
    }
    
    public void borrarPantalla() {

                    primerNumero = 0;
                    operador = "";
                    display.setText("0");
                    nuevoInput = true;    

    }

    public JTextField getDisplay() {
        return display;
    }

    public double getPrimerNumero() {
        return primerNumero;
    }

    public String getOperador() {
        return operador;
    }

    public boolean isNuevoInput() {
        return nuevoInput;
    }

    public void setDisplay(JTextField display) {
        this.display = display;
    }

    public void setPrimerNumero(double primerNumero) {
        this.primerNumero = primerNumero;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public void setNuevoInput(boolean nuevoInput) {
        this.nuevoInput = nuevoInput;
    }
    
    
    
}