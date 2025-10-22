/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.Scanner;
/**
 *
 * @author USUARIO
 */
public class Seno {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double grados = scanner.nextDouble(); 

        double resultado = calcularSeno(grados);  

    }

    public static double calcularSeno(double grados) {
        double radianes = Math.toRadians(grados);
        return Math.sin(radianes);
    }
    
}