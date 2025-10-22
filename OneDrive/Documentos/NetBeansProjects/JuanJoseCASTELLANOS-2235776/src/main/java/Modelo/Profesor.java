/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.JOptionPane;

/**
 *
 * @author juanj
 */
public class Profesor extends Persona {
    private int cedula;
    private double salario;
    private int horasTrabajadas;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public Profesor(String nombre, int telefono, String direccion, String fecha, int cedula1) {
        super(nombre, direccion, telefono, fecha);
    }
    public double SalarioMensual(){
          double SalarioTotal=(salario)+(horasTrabajadas)*(0.20);
           JOptionPane.showMessageDialog(null,"Aqui esta tu salario mensual"+SalarioTotal);
           return SalarioTotal;
    }
    
    public double PrestacionesSociales(){
        double PrestacionesSociales=SalarioMensual()*0.17;
        JOptionPane.showMessageDialog(null,"Aqui esta tu Prestacion social"+PrestacionesSociales);
        return PrestacionesSociales;
    }

    @Override
    public String toString() {
        return "Profesor{" + "cedula=" + cedula + ", salario=" + salario + ", horasTrabajadas=" + horasTrabajadas + '}';
    }
    
    @Override
     public void tipo(){
        System.out.print("Soy un profesor");
    }        
}
    
   
