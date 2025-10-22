/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author juanj
 */
public class Profesor extends Persona {
    private String encargo;
    private String cedula;
    private int HorasTrabajadas;
    public Profesor(String nombre, int edad, LocalDate fechaDeNacimiento, String Telefono) {
        super(nombre, edad, fechaDeNacimiento, Telefono);
    }

    public Profesor(String encargo, String cedula, int HorasTrabajadas, String nombre, int edad, LocalDate fechaDeNacimiento, String Telefono) {
        super(nombre, edad, fechaDeNacimiento, Telefono);
        this.encargo = encargo;
        this.cedula = cedula;
        this.HorasTrabajadas = HorasTrabajadas;
    }

    public String getEncargo() {
        return encargo;
    }

    public void setEncargo(String encargo) {
        this.encargo = encargo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getHorasTrabajadas() {
        return HorasTrabajadas;
    }

    public void setHorasTrabajadas(int HorasTrabajadas) {
        this.HorasTrabajadas = HorasTrabajadas;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int getEdad() {
        return edad;
    }

    @Override
    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    @Override
    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    @Override
    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    
}
