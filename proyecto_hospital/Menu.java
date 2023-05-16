/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_hospital;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alvarogasca
 */
public class Menu {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("Menú:");
            System.out.println("1. Paciente");
            System.out.println("2. Médico");
            System.out.println("3. Ingreso");
            System.out.println("4. Cama");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            
            switch (opcion) {
                case 1:
                    mostrarMenuPaciente();
                    break;
                case 2:
                    mostrarMenuMedico();
                    break;
                case 3:
                    mostrarMenuIngreso();
                    break;
                case 4:
                    mostrarMenuCama();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            
            System.out.println();
        } while (opcion != 0);
        
        scanner.close();
    }
    
    public static void mostrarMenuPaciente() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("Menú Paciente:");
            System.out.println("1. Insertar");
            System.out.println("2. Actualizar");
            System.out.println("3. Eliminar");
            System.out.println("4. Mostrar información");
            System.out.println("5. Obtener todos los pacientes");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese los datos del paciente:");
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Apellidos: ");
                    String apellidos = scanner.nextLine();

                    System.out.print("Edad: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    System.out.print("Género: ");
                    String genero = scanner.nextLine();

                    System.out.print("Dirección: ");
                    String direccion = scanner.nextLine();

                    System.out.print("Población: ");
                    String poblacion = scanner.nextLine();

                    System.out.print("Provincia: ");
                    String provincia = scanner.nextLine();

                    System.out.print("Código Postal: ");
                    String codigoPostal = scanner.nextLine();

                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();

                    System.out.print("Estado: ");
                    String estado = scanner.nextLine();

                    // Crear una instancia de Paciente con los datos ingresados
                    Paciente paciente = new Paciente(
                        id, nombre, apellidos, edad, genero, direccion,
                        poblacion, provincia, codigoPostal, telefono, estado
                    );

                    // Insertar el paciente en la base de datos
                    paciente.insertar();
                break;
                    
                case 2:
                    System.out.println("Ingrese el ID del paciente que desea actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    // Obtener el paciente correspondiente al ID ingresado
                    Paciente pacienteActualizar = Paciente.leerPorID(idActualizar);

                    if (pacienteActualizar == null) {
                        System.out.println("No se encontró un paciente con el ID proporcionado.");
                    } else {
                        System.out.println("Ingrese los nuevos datos del paciente:");

                        System.out.print("Nombre: ");
                        String nombreActualizar = scanner.nextLine();
                        pacienteActualizar.setNombre(nombreActualizar);

                        System.out.print("Apellidos: ");
                        String apellidosActualizar = scanner.nextLine();
                        pacienteActualizar.setApellidos(apellidosActualizar);

                        System.out.print("Edad: ");
                        int edadActualizar = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        pacienteActualizar.setEdad(edadActualizar);

                        System.out.print("Género: ");
                        String generoActualizar = scanner.nextLine();
                        pacienteActualizar.setGenero(generoActualizar);

                        System.out.print("Dirección: ");
                        String direccionActualizar = scanner.nextLine();
                        pacienteActualizar.setDireccion(direccionActualizar);

                        System.out.print("Población: ");
                        String poblacionActualizar = scanner.nextLine();
                        pacienteActualizar.setPoblacion(poblacionActualizar);

                        System.out.print("Provincia: ");
                        String provinciaActualizar = scanner.nextLine();
                        pacienteActualizar.setProvincia(provinciaActualizar);

                        System.out.print("Código Postal: ");
                        String codigoPostalActualizar = scanner.nextLine();
                        pacienteActualizar.setCodigoPostal(codigoPostalActualizar);

                        System.out.print("Teléfono: ");
                        String telefonoActualizar = scanner.nextLine();
                        pacienteActualizar.setTelefono(telefonoActualizar);

                        System.out.print("Estado: ");
                        String estadoActualizar = scanner.nextLine();
                        pacienteActualizar.setEstado(estadoActualizar);

                        // Actualizar el paciente en la base de datos
                        pacienteActualizar.actualizar();
                        System.out.println("Paciente actualizado con éxito.");
                    }
                break;

                case 3:
                    System.out.println("Ingrese el ID del paciente que desea eliminar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    // Obtener el paciente correspondiente al ID ingresado
                    Paciente pacienteEliminar = Paciente.leerPorID(idEliminar);

                    if (pacienteEliminar == null) {
                        System.out.println("No se encontró un paciente con el ID proporcionado.");
                    } else {
                        // Eliminar el paciente de la base de datos
                        pacienteEliminar.eliminar();
                        System.out.println("Paciente eliminado con éxito.");
                    }
                break;

                case 4:
                    System.out.println("Ingrese el ID del paciente para mostrar su información: ");
                    int idMostrar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    // Obtener el paciente correspondiente al ID ingresado
                    Paciente pacienteMostrar = Paciente.leerPorID(idMostrar);

                    if (pacienteMostrar == null) {
                        System.out.println("No se encontró un paciente con el ID proporcionado.");
                    } else {
                        // Mostrar la información del paciente
                        pacienteMostrar.mostrarInformacion();
                    }
                break;
                    
                case 5:
                    ArrayList<Paciente> listaPacientes = Paciente.obtenerTodos();

                    if (listaPacientes.isEmpty()) {
                        System.out.println("No hay pacientes registrados.");
                    } else {
                        System.out.println("Lista de pacientes:");
                        for (Paciente p : listaPacientes) {
                            p.mostrarInformacion();
                            System.out.println("--------------------");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            
            System.out.println();
        } while (opcion != 0);
        
        scanner.close();
    }
    
    public static void mostrarMenuMedico() {
        Scanner scanner = new Scanner(System.in);
    int opcion;

    do {
        System.out.println("Menú Medico:");
        System.out.println("1. Insertar");
        System.out.println("2. Actualizar");
        System.out.println("3. Eliminar");
        System.out.println("4. Mostrar información por ID");
        System.out.println("5. Obtener todos los médicos");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el código del médico: ");
                int codigo = scanner.nextInt();
                scanner.nextLine(); // Limpiar el salto de línea

                System.out.print("Ingrese el nombre del médico: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el apellido del médico: ");
                String apellido = scanner.nextLine();

                System.out.print("Ingrese el teléfono del médico: ");
                String telefono = scanner.nextLine();

                System.out.print("Ingrese la especialidad del médico: ");
                String especialidad = scanner.nextLine();

                Medico medico = new Medico(codigo, nombre, apellido, telefono, Especialidad.valueOf(especialidad));

                if (medico.insertar()) {
                    System.out.println("Médico insertado correctamente.");
                } else {
                    System.out.println("Error al insertar el médico. Intente nuevamente.");
                }
            break;
            
            case 2:
                System.out.println("***** Actualizar médico existente *****");
                System.out.print("Ingrese el código del médico a actualizar: ");
                codigo = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea después de leer el entero

                medico = Medico.leerPorID(codigo);

                if (medico != null) {
                    System.out.println("Médico encontrado:");
                    System.out.println("Código: " + medico.getCodigo());
                    System.out.println("Nombre: " + medico.getNombre());
                    System.out.println("Apellido: " + medico.getApellido());
                    System.out.println("Teléfono: " + medico.getTelefono());
                    System.out.println("Especialidad: " + medico.getEspecialidad());

                    System.out.println("Ingrese los nuevos datos:");

                    System.out.print("Nuevo nombre: ");
                    nombre = scanner.nextLine();
                    System.out.print("Nuevo apellido: ");
                    apellido = scanner.nextLine();
                    System.out.print("Nuevo teléfono: ");
                    telefono = scanner.nextLine();
                    System.out.print("Nueva especialidad: ");
                    especialidad = scanner.nextLine();

                    medico.setNombre(nombre);
                    medico.setApellido(apellido);
                    medico.setTelefono(telefono);
                    medico.setEspecialidad(Especialidad.valueOf(especialidad));

                    if (medico.actualizar()) {
                        System.out.println("El médico se ha actualizado correctamente.");
                    } else {
                        System.out.println("No se pudo actualizar el médico. Intente nuevamente.");
                    }
                } else {
                    System.out.println("No se encontró ningún médico con el código especificado.");
                }
            break;
           
            case 3:
                System.out.println("***** Eliminar médico *****");
                System.out.print("Ingrese el código del médico a eliminar: ");
                codigo = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea después de leer el entero

                medico = Medico.leerPorID(codigo);

                if (medico != null) {
                    System.out.println("Médico encontrado:");
                    System.out.println("Código: " + medico.getCodigo());
                    System.out.println("Nombre: " + medico.getNombre());
                    System.out.println("Apellido: " + medico.getApellido());
                    System.out.println("Teléfono: " + medico.getTelefono());
                    System.out.println("Especialidad: " + medico.getEspecialidad());

                    System.out.print("¿Está seguro de que desea eliminar este médico? (S/N): ");
                    String confirmacion = scanner.nextLine();

                    if (confirmacion.equalsIgnoreCase("S")) {
                        if (medico.eliminar()) {
                            System.out.println("El médico se ha eliminado correctamente.");
                        } else {
                            System.out.println("No se pudo eliminar el médico. Intente nuevamente.");
                        }
                    } else {
                        System.out.println("La eliminación del médico ha sido cancelada.");
                    }
                } else {
                    System.out.println("No se encontró ningún médico con el código especificado.");
                }
            break;
            
            case 4:
                System.out.println("***** Buscar médico por ID *****");
                System.out.print("Ingrese el código del médico a buscar: ");
                codigo = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea después de leer el entero

                medico = Medico.leerPorID(codigo);

                if (medico != null) {
                    System.out.println("Médico encontrado:");
                    System.out.println("Código: " + medico.getCodigo());
                    System.out.println("Nombre: " + medico.getNombre());
                    System.out.println("Apellido: " + medico.getApellido());
                    System.out.println("Teléfono: " + medico.getTelefono());
                    System.out.println("Especialidad: " + medico.getEspecialidad());
                } else {
                    System.out.println("No se encontró ningún médico con el código especificado.");
                }
            break;
            
            case 5:
                System.out.println("***** Listado de todos los médicos *****");

                ArrayList<Medico> medicos = Medico.obtenerTodos();

                if (medicos.isEmpty()) {
                    System.out.println("No se encontraron médicos registrados.");
                } else {
                    System.out.println("Cantidad de médicos encontrados: " + medicos.size());
                    System.out.println("-------------------------------");
                    for (Medico m : medicos) {
                        System.out.println("Código: " + m.getCodigo());
                        System.out.println("Nombre: " + m.getNombre());
                        System.out.println("Apellido: " + m.getApellido());
                        System.out.println("Teléfono: " + m.getTelefono());
                        System.out.println("Especialidad: " + m.getEspecialidad());
                        System.out.println("-------------------------------");
                    }
                }
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción inválida.");
        }

        System.out.println();
    } while (opcion != 0);

    scanner.close();
    
    }
    
    
    public static void mostrarMenuIngreso() throws SQLException {
        Scanner scanner = new Scanner(System.in);
    int opcion;

    do {
        System.out.println("***** Menú Ingreso *****");
        System.out.println("1. Crear nuevo ingreso");
        System.out.println("2. Dar de alta un ingreso");
        System.out.println("3. Verificar si un ingreso está en curso");
        System.out.println("4. Calcular duración de un ingreso");
        System.out.println("5. Buscar ingreso por ID de paciente");
        System.out.println("0. Salir");
        System.out.println("Ingrese el número de la opción que desea ejecutar:");
        opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer la opción

        switch (opcion) {
            case 1:
                System.out.println("Ingrese la fecha de ingreso (formato: dd/mm/aaaa):");
                String fechaIngresoStr = scanner.nextLine();
                Date fechaIngreso = null;

                try {
                    fechaIngreso = new SimpleDateFormat("dd/MM/yyyy").parse(fechaIngresoStr);
                } catch (ParseException e) {
                    System.out.println("Formato de fecha inválido. Vuelva a intentarlo.");
                    break;
                }

                System.out.println("Ingrese el ID del paciente:");
                int pacienteId = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de leer el ID del paciente

                Paciente paciente = Paciente.leerPorID(pacienteId);
                if (paciente != null) {
                    System.out.println("Ingrese el código del médico:");
                    int medicoCodigo = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea después de leer el código del médico

                    Medico medico = Medico.leerPorID(medicoCodigo);
                    if (medico != null) {
                        System.out.println("Ingrese el número de la cama:");
                        int numeroCama = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea después de leer el número de la cama

                        Cama cama = Cama.leerPorID(numeroCama);
                        if (cama != null) {
                            Ingreso nuevoIngreso = new Ingreso();
                            nuevoIngreso.crearNuevoIngreso(fechaIngreso, paciente, medico, cama);

                            System.out.println("Nuevo ingreso creado correctamente.");
                        } else {
                            System.out.println("No se encontró ninguna cama con el número especificado.");
                        }
                    } else {
                        System.out.println("No se encontró ningún médico con el código especificado.");
                    }
                } else {
                    System.out.println("No se encontró ningún paciente con el ID especificado.");
                }
            break;
            
            case 2:
                System.out.println("Ingrese el número de ingreso:");
                int numeroIngreso = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de leer el número de ingreso

                Ingreso ingreso = Ingreso.leerPorNumero(numeroIngreso);
                if (ingreso != null) {
                    System.out.println("Ingrese la fecha de alta (formato: dd/mm/aaaa):");
                    String fechaAltaStr = scanner.nextLine();
                    Date fechaAlta = null;

                    try {
                        fechaAlta = new SimpleDateFormat("dd/MM/yyyy").parse(fechaAltaStr);
                    } catch (ParseException e) {
                        System.out.println("Formato de fecha inválido. Vuelva a intentarlo.");
                        break;
                    }

                    ingreso.darAlta(fechaAlta);
                    System.out.println("Ingreso dado de alta correctamente.");
                } else {
                    System.out.println("No se encontró ningún ingreso con el número especificado.");
                }
                break;
            case 3:
                System.out.println("Ingrese el número de ingreso:");
                numeroIngreso = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de leer el número de ingreso

                Ingreso i1 = Ingreso.leerPorNumero(numeroIngreso);
                if (i1 != null) {
                    if (i1.estaEnCurso()) {
                        System.out.println("El ingreso está en curso.");
                    } else {
                        System.out.println("El ingreso no está en curso.");
                    }
                } else {
                    System.out.println("No se encontró ningún ingreso con el número especificado.");
                }
            break;
            
            case 4:
                System.out.println("Ingrese el número de ingreso:");
                numeroIngreso = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de leer el número de ingreso

                Ingreso i2 = Ingreso.leerPorNumero(numeroIngreso);
                if (i2 != null) {
                    if (i2.getFechaAlta() != null) {
                        int duracion = i2.duracion();
                        System.out.println("Duración del ingreso: " + duracion + " días.");
                    } else {
                        System.out.println("El ingreso aún no ha finalizado.");
                    }
                } else {
                    System.out.println("No se encontró ningún ingreso con el número especificado.");
                }
            break;
            
            case 5:
                System.out.println("Ingrese el ID del paciente:");
                int idPaciente = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de leer el ID del paciente

                Ingreso i3 = Ingreso.buscarPorIdPaciente(idPaciente);
                if (i3 != null) {
                    System.out.println("Número de ingreso: " + i3.getNumeroIngreso());
                    System.out.println("Fecha de ingreso: " + i3.getFechaIngreso());
                    if (i3.getFechaAlta() != null) {
                        System.out.println("Fecha de alta: " + i3.getFechaAlta());
                    } else {
                        System.out.println("El ingreso aún no ha finalizado.");
                    }
                } else {
                    System.out.println("No se encontró ningún ingreso para el paciente con el ID especificado.");
                }
            break;
            
            case 6:
                List<Ingreso> ingresos = Ingreso.mostrarTodosLosIngresos();
                if (!ingresos.isEmpty()) {
                    System.out.println("Lista de ingresos:");
                    for (Ingreso i4 : ingresos) {
                        System.out.println("Número de ingreso: " + i4.getNumeroIngreso());
                        System.out.println("Fecha de ingreso: " + i4.getFechaIngreso());
                        if (i4.getFechaAlta() != null) {
                            System.out.println("Fecha de alta: " + i4.getFechaAlta());
                        } else {
                            System.out.println("El ingreso aún no ha finalizado.");
                        }
                        System.out.println("-------------------------------");
                    }
                } else {
                    System.out.println("No se encontraron ingresos en la base de datos.");
                }
            break;
            
            case 0:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
        System.out.println();
    } while (opcion != 0);

    scanner.close();
    }
    
    public static void mostrarMenuCama() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("***** Menú *****");
            System.out.println("1. Insertar una cama");
            System.out.println("2. Actualizar disponibilidad de una cama");
            System.out.println("3. Asignar un paciente a una cama");
            System.out.println("4. Liberar una cama");
            System.out.println("5. Buscar cama por ID");
            System.out.println("6. Buscar camas disponibles");
            System.out.println("0. Salir");
            System.out.println("Ingrese el número de la opción que desea ejecutar:");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de leer la opción

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el número de la cama:");
                    int numeroCama = scanner.nextInt();
                    System.out.println("Ingrese el número de la habitación:");
                    int numeroHabitacion = scanner.nextInt();
                    Cama nuevaCama = new Cama(numeroCama, numeroHabitacion);
                    nuevaCama.insertarCama();
                    System.out.println();
                break;
                
                case 2:
                    System.out.println("Ingrese el número de la cama:");
                    numeroCama = scanner.nextInt();
                    System.out.println("Ingrese el ID del paciente:");
                    int pacienteId = scanner.nextInt();
                    Cama cama = Cama.leerPorID(numeroCama);
                    Paciente paciente = Paciente.leerPorID(pacienteId);
                    if (cama != null && paciente != null) {
                        cama.asignarPaciente(paciente);
                        System.out.println("Paciente asignado correctamente a la cama.");
                    } else {
                        System.out.println("No se encontró la cama o el paciente con los IDs especificados.");
                    }
                    System.out.println();
                break;
                    
                case 3:
                    System.out.println("Ingrese el número de la cama:");
                    numeroCama = scanner.nextInt();
                    System.out.println("Ingrese el ID del paciente:");
                    pacienteId = scanner.nextInt();
                    Cama c1 = Cama.leerPorID(numeroCama);
                    Paciente p1 = Paciente.leerPorID(pacienteId);
                    if (p1 != null) {
                        c1.asignarPaciente(p1);
                        System.out.println("Paciente asignado correctamente a la cama.");
                    } else {
                        System.out.println("No se encontró ningún paciente con el ID especificado.");
                    }
                    System.out.println();
                break;
                
                case 4:
                    System.out.println("Ingrese el número de la cama que desea liberar:");
                    int numeroCamaLiberar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea después de leer el número de cama

                    Cama camaLiberar = Cama.leerPorID(numeroCamaLiberar);
                    if (camaLiberar != null) {
                        camaLiberar.liberarCama();
                        System.out.println("Cama liberada correctamente.");
                    } else {
                        System.out.println("No se encontró ninguna cama con el número especificado.");
                    }
                    System.out.println();
                break;
                
                case 5:
                    System.out.println("Ingrese el número de la cama que desea buscar:");
                    int numeroCamaBuscar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea después de leer el número de cama

                    Cama camaBuscar = Cama.leerPorID(numeroCamaBuscar);
                    if (camaBuscar != null) {
                        System.out.println("Información de la cama encontrada:");
                        System.out.println("Número de cama: " + camaBuscar.getNumeroCama());
                        System.out.println("Número de habitación: " + camaBuscar.getNumeroHabitacion());
                        System.out.println("Disponible: " + camaBuscar.isDisponible());
                        Paciente pacienteAsignado = camaBuscar.getPaciente();
                        if (pacienteAsignado != null) {
                            System.out.println("Paciente asignado:");
                            System.out.println("ID: " + pacienteAsignado.getId());
                            System.out.println("Nombre: " + pacienteAsignado.getNombre());
                            // Mostrar más información del paciente si es necesario
                        } else {
                            System.out.println("No hay paciente asignado a esta cama.");
                        }
                    } else {
                        System.out.println("No se encontró ninguna cama con el número especificado.");
                    }
                    System.out.println();
                break;
                
                case 6:
                    List<Cama> camasDisponibles = Cama.buscarCamasDisponibles();
                    if (!camasDisponibles.isEmpty()) {
                        System.out.println("Camas disponibles:");
                        for (Cama c2 : camasDisponibles) {
                            System.out.println("Número de cama: " + c2.getNumeroCama());
                            System.out.println("Número de habitación: " + c2.getNumeroHabitacion());
                            // Mostrar más información de la cama si es necesario
                            System.out.println();
                        }
                    } else {
                        System.out.println("No se encontraron camas disponibles.");
                    }
                    System.out.println();
                break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }
}


