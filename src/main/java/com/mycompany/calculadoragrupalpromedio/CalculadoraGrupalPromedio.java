/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculadoragrupalpromedio;

import Modelo.Promedio;

public class CalculadoraGrupalPromedio {

    public static void main(String[] args) {
        Promedio p = new Promedio();
        double[] valores = {5, 10, 15, 20};
        p.setNumeros(valores);
        System.out.println("El promedio es: " + p.calcular());
    }
}
