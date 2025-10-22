/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculadoracienteficagit;

import GUI.CalculadoraCientificaFuncional;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author cript
 */
public class CalculadoraCienteficaGit {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CalculadoraCientificaFuncional().setVisible(true);
        });
    }
}
