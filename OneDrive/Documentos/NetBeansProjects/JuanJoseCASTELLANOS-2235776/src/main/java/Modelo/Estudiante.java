/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author juanj
 */
public class Estudiante extends Persona {
    
    
    public Estudiante(String nombre, int telefono, String direccion, String fecha) {
        super(nombre, direccion, telefono, fecha);
    }
    
    @Override
     public void tipo(){
        System.out.print("Soy un estudiante");
    }
}
