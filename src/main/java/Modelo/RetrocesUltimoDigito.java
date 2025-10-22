/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Nicolas Castaño
 */
public class RetrocesUltimoDigito {
    public static String borrarUltimoCaracter(String textoDisplay) {
        
        if (textoDisplay == null || textoDisplay.isEmpty() || textoDisplay.equals("0")) {
            return "0";
        }

        char[] caracteres = textoDisplay.toCharArray();
        
        char[] nuevo = new char[caracteres.length - 1];
        
        for (int i = 0; i < nuevo.length; i++) {
            nuevo[i] = caracteres[i];
        }
 
        if (nuevo.length == 0) {
            return "0";
        }

        return new String(nuevo);    
    }
    
}
