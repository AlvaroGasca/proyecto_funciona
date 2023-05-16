/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvarogasca
 */
public class Cama {
    private int numeroCama;
    private int numeroHabitacion;
    private boolean disponible;
    private Paciente paciente;
    private Conexion conexion;
    public Cama(){
        conexion = new Conexion();
    }
    
    public Cama(int numeroCama, int numeroHabitacion) {
        this.numeroCama = numeroCama;
        this.numeroHabitacion = numeroHabitacion;
        this.disponible = true;
        this.paciente = null;
    }
    
    static Connection conexion(){
    Connection con = null;
    String url = "jdbc:mysql://localhost/proyecto_hospital";
    try{
    con = DriverManager.getConnection(url,"root","");
            System.out.println("Conexión realizada con éxito. ");
        } catch (SQLException ex){
            System.out.println("Error al conectar al SGBD. ");
        }
    return con;
    }
    
    public int getNumeroCama() {
        return this.numeroCama;
    }

    public void setNumeroCama(int numeroCama) {
        this.numeroCama = numeroCama;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }
    
    public boolean isDisponible() {
        return this.disponible;
    }
    
    public Paciente getPaciente() {
        return this.paciente;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    // Método para insertar una cama en la base de datos
    public void insertarCama() {
        try (Connection conn = conexion();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO cama (numero_cama, numero_habitacion, disponible) VALUES (?, ?, ?)")) {
            ps.setInt(1, this.numeroCama);
            ps.setInt(2, this.numeroHabitacion);
            ps.setBoolean(3, this.disponible);
            ps.executeUpdate();
            System.out.println("Cama insertada correctamente en la base de datos");
        } catch (SQLException ex) {
            System.out.println("Error al insertar la cama en la base de datos");
            ex.printStackTrace();
        }
    }

    // Método para actualizar el estado de disponibilidad de una cama en la base de datos
    public void actualizarDisponibilidad() {
    try (Connection conn = conexion();
         PreparedStatement ps = conn.prepareStatement(
                 "UPDATE cama SET disponible = ?, numero_habitacion = ? WHERE numero_cama = ?")) {
        ps.setBoolean(1, this.disponible);
        ps.setInt(2, this.numeroHabitacion);
        ps.setInt(3, this.numeroCama);
        ps.executeUpdate();
        System.out.println("Disponibilidad de la cama actualizada correctamente en la base de datos");
    } catch (SQLException ex) {
        System.out.println("Error al actualizar la disponibilidad de la cama en la base de datos");
        ex.printStackTrace();
    }
}

    // Método para asignar un paciente a una cama y actualizar su estado en la base de datos
    public void asignarPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.disponible = false;
        try (Connection conn = conexion();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE cama SET disponible = ?, paciente_id = ? WHERE numero_cama = ?")) {
            ps.setBoolean(1, this.disponible);
            ps.setInt(2, this.paciente.getId());
            ps.setInt(3, this.numeroCama);
            ps.executeUpdate();
            System.out.println("Paciente asignado correctamente a la cama y estado de disponibilidad actualizado en la base de datos");
        } catch (SQLException ex) {
            System.out.println("Error al asignar el paciente a la cama en la base de datos");
            ex.printStackTrace();
        }
    }
    
    public void liberarCama() {
    try {
        Connection con = conexion();
        String query = "UPDATE cama SET disponible = true, paciente_id = null WHERE numero_cama = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, this.numeroCama);
        ps.executeUpdate();
        con.close();
        System.out.println("Cama liberada correctamente en la base de datos");
    } catch (SQLException ex) {
        System.out.println("Error al liberar la cama en la base de datos");
        ex.printStackTrace();
    }
    this.paciente = null;
    this.disponible = true;
}
    
    public static Cama leerPorID(int numeroCama) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Cama cama = null;

    try {
        con = conexion();
        String sql = "SELECT * FROM cama WHERE numero_cama = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, numeroCama);
        rs = ps.executeQuery();

        if (rs.next()) {
            int numeroHabitacion = rs.getInt("numero_habitacion");
            boolean disponible = rs.getBoolean("disponible");
            int pacienteId = rs.getInt("paciente_id");

            Paciente paciente = null;
            if (!rs.wasNull()) {
                paciente = Paciente.leerPorID(pacienteId);
            }

            cama = new Cama(numeroCama, numeroHabitacion);
            cama.setDisponible(disponible);
            if (paciente != null) {
                cama.asignarPaciente(paciente);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error al leer la cama por su ID: " + ex.getMessage());
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el ResultSet: " + ex.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el PreparedStatement: " + ex.getMessage());
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }

    return cama;
}
    
    public static List<Cama> buscarCamasDisponibles() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Cama> camasDisponibles = new ArrayList<>();

     try {
        con = conexion();
        String sql = "SELECT * FROM cama WHERE disponible = true";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            int numeroCama = rs.getInt("numero_cama");
            int numeroHabitacion = rs.getInt("numero_habitacion");
            boolean disponible = rs.getBoolean("disponible");
            int pacienteId = rs.getInt("paciente_id");

            Paciente paciente = null;
            if (!rs.wasNull()) {
                paciente = Paciente.leerPorID(pacienteId);
            }

            Cama cama = new Cama(numeroCama, numeroHabitacion);
            cama.setDisponible(disponible);
            if (paciente != null) {
                cama.asignarPaciente(paciente);
            }

            camasDisponibles.add(cama);
        }
    } catch (SQLException ex) {
        System.out.println("Error al buscar las camas disponibles: " + ex.getMessage());
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el ResultSet: " + ex.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el PreparedStatement: " + ex.getMessage());
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }

    return camasDisponibles;
}

}
