/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_hospital;

import java.util.ArrayList;

/**
 *
 * @author alvarogasca
 */
public class Test {
    public static void main(String[] args) {
//    int codigo = 9;
//        String nombre = "Ana";
//        String apellido = "Perez";
//        String telefono = "678898787";
//        Especialidad especialidad = Especialidad.Cardiología;
//        Medico medico = new Medico(codigo, nombre, apellido, telefono, especialidad);
//        medico.insertar();
//        Medico medico = new Medico(); // Crea una instancia de Medico o utiliza una existente
//    medico.setCodigo(9); // Establece el código del médico a eliminar
//
//    boolean resultado = medico.eliminar(); // Llama al método eliminar()
//
//              ArrayList<Medico> medicos = Medico.obtenerTodos();

        // Imprimir los médicos obtenidos
//        for (Medico medico : medicos) {
//            System.out.println(medico.toString());
//        }


//    ArrayList<Paciente> pacientes = Paciente.obtenerTodos();
//
//    if (pacientes.isEmpty()) {
//        System.out.println("No se encontraron pacientes.");
//    } else {
//        System.out.println("Pacientes encontrados:");
//        for (Paciente paciente : pacientes) {
//            System.out.println("Id: " + paciente.getId());
//            System.out.println("Nombre: " + paciente.getNombre());
//            System.out.println("Edad: " + paciente.getEdad());
//            System.out.println("Género: " + paciente.getGenero());
//            System.out.println("Dirección: " + paciente.getDireccion());
//            System.out.println("Teléfono: " + paciente.getTelefono());
//            System.out.println("Estado: " + paciente.getEstado());
//            System.out.println("---------------------------");
//        }
//    }

    int idPaciente = 1; // ID del paciente a buscar


    Ingreso ingreso = Ingreso.buscarPorIdPaciente(idPaciente);

    if (ingreso != null) {
        System.out.println("Ingreso encontrado:");
        System.out.println("Número de ingreso: " + ingreso.getNumeroIngreso());
        System.out.println("Fecha de ingreso: " + ingreso.getFechaIngreso());
        System.out.println("Fecha de alta: " + ingreso.getFechaAlta());
        System.out.println("Paciente: " + ingreso.getPaciente());
        System.out.println("Médico: " + ingreso.getMedico());
        System.out.println("Cama: " + ingreso.getCama());
    } else {
        System.out.println("No se encontró ningún ingreso para el ID de paciente proporcionado.");
    }
}

}


       
    
    

