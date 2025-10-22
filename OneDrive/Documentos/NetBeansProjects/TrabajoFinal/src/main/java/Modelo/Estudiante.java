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
public class Estudiante extends Persona {
    private String clase;
    private String codigo;
    private double nota;
    public Estudiante(String nombre, int edad, LocalDate fechaDeNacimiento, String Telefono) {
        super(nombre, edad, fechaDeNacimiento, Telefono);
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
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

    @Override
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public Estudiante(String clase, String codigo, double nota, String nombre, int edad, LocalDate fechaDeNacimiento, String Telefono) {
        super(nombre, edad, fechaDeNacimiento, Telefono);
        this.clase = clase;
        this.codigo = codigo;
        this.nota = nota;
    }
    
    
}
