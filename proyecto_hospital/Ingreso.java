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
import java.sql.Statement;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static proyecto_hospital.Paciente.conexion;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author alvarogasca
 */
public class Ingreso {
    private int numeroIngreso;
    private Date fechaIngreso;
    private Date fechaAlta;
    private Paciente paciente;
    private Medico medico;
    private Cama cama;
    private Conexion conexion;
    public Ingreso(){
        conexion = new Conexion();
    }

    public Ingreso(int numeroIngreso, Date fechaIngreso, Paciente paciente, Medico medico, Cama cama) {
        this.numeroIngreso = numeroIngreso;
        this.fechaIngreso = fechaIngreso;
        this.paciente = paciente;
        this.medico = medico;
        this.cama = cama;
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

    // Getters y setters
    public int getNumeroIngreso() {
        return numeroIngreso;
    }

    public void setNumeroIngreso(int numeroIngreso) {
        this.numeroIngreso = numeroIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Cama getCama() {
        return cama;
    }

    public void setCama(Cama cama) {
        this.cama = cama;
    }

    // Otros métodos
    public void crearNuevoIngreso(Date fechaIngreso, Paciente paciente, Medico medico, Cama cama) {
    try (Connection conn = conexion();
         PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO ingreso (fecha_ingreso, paciente_id, medico_id, cama_id) VALUES (?, ?, ?, ?)",
                 Statement.RETURN_GENERATED_KEYS)) {
        ps.setDate(1, new java.sql.Date(fechaIngreso.getTime()));
        ps.setInt(2, paciente.getId());
        ps.setInt(3, medico.getCodigo());
        ps.setInt(4, cama.getNumeroCama());
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Nuevo ingreso creado correctamente en la base de datos");
        } else {
            System.out.println("Error al crear el nuevo ingreso en la base de datos");
        }
        
        // Obtener el número de ingreso generado automáticamente
        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int numeroIngresoGenerado = generatedKeys.getInt(1);
                // Asignar el número de ingreso generado al objeto Ingreso
                this.setNumeroIngreso(numeroIngresoGenerado);
                System.out.println("Número de ingreso asignado: " + numeroIngresoGenerado);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error al crear el nuevo ingreso en la base de datos");
        ex.printStackTrace();
    }
}



    
    public void darAlta(Date fechaAlta) {
    this.fechaAlta = fechaAlta;

    try (Connection conn = conexion();
         PreparedStatement ps = conn.prepareStatement(
                 "UPDATE ingreso SET fecha_alta = ? WHERE numero_ingreso = ?")) {
        ps.setDate(1, new java.sql.Date(fechaAlta.getTime()));
        ps.setInt(2, this.numeroIngreso);
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Ingreso dado de alta correctamente en la base de datos");
        } else {
            System.out.println("No se encontró el ingreso en la base de datos");
        }
    } catch (SQLException ex) {
        System.out.println("Error al dar de alta el ingreso en la base de datos");
        ex.printStackTrace();
    }
}

    public boolean estaEnCurso() {
        return fechaAlta == null;
    }

    public int duracion() {
    if (fechaAlta == null) {
        throw new IllegalStateException("El ingreso no ha finalizado todavía.");
    }

    try (Connection conn = conexion();
         PreparedStatement ps = conn.prepareStatement(
                 "SELECT DATEDIFF(?, ?) AS duracion")) {
        ps.setDate(1, new java.sql.Date(fechaAlta.getTime()));
        ps.setDate(2, new java.sql.Date(fechaIngreso.getTime()));
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int duracion = rs.getInt("duracion");
            return duracion;
        }
    } catch (SQLException ex) {
        System.out.println("Error al obtener la duración del ingreso desde la base de datos");
        ex.printStackTrace();
    }

    return 0;
}
    
    public static Ingreso leerPorNumero(int numeroIngreso) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Ingreso ingreso = null;

    try {
        con = conexion();
        String sql = "SELECT * FROM ingreso WHERE numero_ingreso = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, numeroIngreso);
        rs = ps.executeQuery();

        if (rs.next()) {
            Date fechaIngreso = rs.getDate("fecha_ingreso");
            Date fechaAlta = rs.getDate("fecha_alta");
            int pacienteId = rs.getInt("paciente_id");
            int medicoId = rs.getInt("medico_id");
            int camaId = rs.getInt("cama_id");

            Paciente paciente = Paciente.leerPorID(pacienteId);
            Medico medico = Medico.leerPorID(medicoId);
            Cama cama = Cama.leerPorID(camaId);

            ingreso = new Ingreso(numeroIngreso, fechaIngreso, paciente, medico, cama);
            ingreso.setFechaAlta(fechaAlta);
        }
    } catch (SQLException ex) {
        System.out.println("Error al buscar el ingreso por número: " + ex.getMessage());
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

    return ingreso;
}
    
    public static Ingreso buscarPorIdPaciente(int idPaciente) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Ingreso ingreso = null;

    try {
        con = conexion();
        String sql = "SELECT * FROM ingreso WHERE paciente_id = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, idPaciente);
        rs = ps.executeQuery();

        if (rs.next()) {
            int numeroIngreso = rs.getInt("numero_ingreso");
            Date fechaIngreso = rs.getDate("fecha_ingreso");
            Date fechaAlta = rs.getDate("fecha_alta");
            int medicoId = rs.getInt("medico_id");
            int camaId = rs.getInt("cama_id");

            Medico medico = Medico.leerPorID(medicoId);
            Cama cama = Cama.leerPorID(camaId);
            Paciente paciente = Paciente.leerPorID(idPaciente);

            ingreso = new Ingreso(numeroIngreso, fechaIngreso, paciente, medico, cama);
            ingreso.setFechaAlta(fechaAlta);
        }
    } catch (SQLException ex) {
        System.out.println("Error al buscar el ingreso por ID del paciente: " + ex.getMessage());
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

    return ingreso;
}
    
    public static List<Ingreso> mostrarTodosLosIngresos() throws SQLException {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    List<Ingreso> ingresos = new ArrayList<>();

    try {
        con = conexion();
        stmt = con.createStatement();
        String sql = "SELECT * FROM ingreso";
        rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int numeroIngreso = rs.getInt("numero_ingreso");
            Date fechaIngreso = rs.getDate("fecha_ingreso");
            Date fechaAlta = rs.getDate("fecha_alta");
            int pacienteId = rs.getInt("paciente_id");
            int medicoId = rs.getInt("medico_id");
            int camaId = rs.getInt("cama_id");

            Medico medico = Medico.leerPorID(medicoId);
            Cama cama = Cama.leerPorID(camaId);
            Paciente paciente = Paciente.leerPorID(pacienteId);

            Ingreso ingreso = new Ingreso(numeroIngreso, fechaIngreso, paciente, medico, cama);
            ingreso.setFechaAlta(fechaAlta);
            ingresos.add(ingreso);
        }
    } catch (SQLException ex) {
        System.out.println("Error al obtener los ingresos desde la base de datos: " + ex.getMessage());
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar el ResultSet: " + ex.getMessage());
            }
        }
        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }

    return ingresos;
}



}
