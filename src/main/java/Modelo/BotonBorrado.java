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
    private double primerNumero = 1;
    private String operador = "+";
    private boolean nuevoInput;


    
    public void borrarPantalla() {

                    primerNumero = 0;
                    operador = "";
                  
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
