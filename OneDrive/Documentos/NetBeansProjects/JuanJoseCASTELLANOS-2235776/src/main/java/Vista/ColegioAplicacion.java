/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Vista;

import Modelo.Estudiante;
import Modelo.Profesor;
import javax.swing.JOptionPane;

/**
 *
 * @author juanj
 */
public class ColegioAplicacion {
    private static Profesor[] listaProfesores;
    private static Estudiante[] listaEstudiantes;
    

    public static void main(String[] args) {
        int capProf=Integer.parseInt(JOptionPane.showInputDialog("Cantidad de profesores:"));
        int capEstu=Integer.parseInt(JOptionPane.showInputDialog("Cantidad estudiantes:"));
        listaProfesores= new Profesor[capProf];
        listaEstudiantes=new Estudiante[capEstu];
        int opcion;
        do {
            opcion = mostrarMenu();
            ejecutarOpcion(opcion);
        } while (opcion != 5);
    }

    private static int mostrarMenu() {
        String menu = "Menú Principal\n"
               
                + "1.Registrar Profesor\n"
                + "2.Registrar Estudiante\n"
                +"3.Registrar salarioMensual\n"
                +"4 modificar Prestaciones sociales\n"
                +"final programa\n";
        
        
        return Integer.parseInt(JOptionPane.showInputDialog(menu));
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarProfesor();
                break;
            case 2:
                registrarEstudiante();             
                break;
            case 3:
                SalarioMensual();
               

                break;
                
            case 4:
                PrestacionesSociales();
                
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Fin del programa.");
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
        }
    }
         private static  void registrarProfesor() {
        for (int i = 0; i < listaProfesores.length; i++) {
            String nombre =JOptionPane.showInputDialog("Ingrese su nombre:");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese edad:"));
            String direccion =JOptionPane.showInputDialog("Ingrese su direccion:");
            String fecha =JOptionPane.showInputDialog("Ingrese su fecha:");
            int cedula = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cedula:"));
            
            listaProfesores[i] = new Profesor(nombre,edad, direccion, fecha,cedula);
        }  
    }
        private static  void registrarEstudiante() {
        for (int i = 0; i < listaEstudiantes.length; i++) {
            String nombre =JOptionPane.showInputDialog("Ingrese su nombre:");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese edad:"));
            String direccion =JOptionPane.showInputDialog("Ingrese su direccion:");
            String fecha =JOptionPane.showInputDialog("Ingrese su fecha:");
            
            
            listaEstudiantes[i] = new Estudiante (nombre,edad, direccion, fecha);
        }
    }
         public static double SalarioMensual(){
             double salario=Double.parseDouble(JOptionPane.showInputDialog("Ingrese salario:"));
             int horasTrabajadas=Integer.parseInt(JOptionPane.showInputDialog("Ingrese horas Trabajadas:"));
          double base=(salario)+(horasTrabajadas);
          double SalarioTotal=base*1.20;
           JOptionPane.showMessageDialog(null,"Aqui esta tu salario mensual"+SalarioTotal);
           return SalarioTotal;
           
         }
         public static double PrestacionesSociales(){
        double PrestacionesSociales=SalarioMensual()*0.17;
        JOptionPane.showMessageDialog(null,"Aqui esta tu Prestacion social"+PrestacionesSociales);
        return PrestacionesSociales;
    }
}
