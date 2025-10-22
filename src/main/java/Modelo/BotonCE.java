/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 9spot
 */
public class BotonCE {
    public static String clearEntry(String textoActual) {
        return "0";
    }

    public static boolean needsClear(String textoActual) {
        return textoActual != null && !textoActual.equals("0") && !textoActual.isEmpty();
    }
}
