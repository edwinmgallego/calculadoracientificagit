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
    public static String limpiarEntradaActual(String textoActual) {
        if (textoActual == null || textoActual.isEmpty() || textoActual.equals("0")) {
            return "0";
        }

        String nuevo = textoActual.substring(0, textoActual.length() - 1);

        if (nuevo.isEmpty() || nuevo.equals("-") || nuevo.equals(".") || nuevo.equals("-.")) {
            return "0";
        }

        return nuevo;
    }

    public static boolean necesitaLimpiar(String textoActual) {
        return textoActual != null && !textoActual.equals("0") && !textoActual.isEmpty();
    }
}