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
import java.util.ArrayList;
import static proyecto_hospital.Paciente.conexion;

/**
 *
 * @author alvarogasca
 */
public class Medico {
    private Conexion conexion;
    public Medico(){
        conexion = new Conexion();
    }
    private int codigo;
    private String nombre;
    private String apellido;
    private String telefono;
    private Especialidad especialidad;

    public Medico(int codigo, String nombre, String apellido, String telefono, Especialidad especialidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.especialidad = especialidad;
        
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
    public boolean insertar() {
    boolean resultado = false;
    Connection con = null;
    try {
        con = conexion();
        String sql = "INSERT INTO medico (codigo, nombre, apellido, telefono, especialidad) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, codigo);
        ps.setString(2, nombre);
        ps.setString(3, apellido);
        ps.setString(4, telefono);
        ps.setString(5, especialidad.toString());
        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            resultado = true;
            System.out.println("Añadido con éxito. ");
        }
    } catch (SQLException ex) {
        System.out.println("Error al insertar el médico: " + ex.getMessage());
    } finally {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
    return resultado;
}

    public boolean actualizar() {
    boolean resultado = false;
    Connection con = null;
    try {
        con = conexion();
        String sql = "UPDATE medico SET nombre = ?, apellido = ?, telefono = ?, especialidad = ? WHERE codigo = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, telefono);
        ps.setString(4, especialidad.toString());
        ps.setInt(5, codigo);
        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            resultado = true;
        }
    } catch (SQLException ex) {
        System.out.println("Error al actualizar el médico: " + ex.getMessage());
    } finally {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
    return resultado;
}
    
    public static Medico leerPorID(int id) {
    Medico medico = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = conexion();
        String sql = "SELECT * FROM medico WHERE codigo = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            medico = new Medico(
                rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("telefono"),
                Especialidad.valueOf(rs.getString("especialidad"))
            );
        }
    } catch (SQLException ex) {
        System.out.println("Error al obtener el médico por ID: " + ex.getMessage());
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

    return medico;
}

    public boolean eliminar() {
    boolean resultado = false;
    Connection con = null;
    try {
        con = conexion();
        String sql = "DELETE FROM medico WHERE codigo = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, codigo);
        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            resultado = true;
        }
    } catch (SQLException ex) {
        System.out.println("Error al eliminar el médico: " + ex.getMessage());
    } finally {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
    return resultado;
}
    
   public static ArrayList<Medico> obtenerTodos() {
    ArrayList<Medico> medicos = new ArrayList<>();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = conexion();
        String sql = "SELECT * FROM medico";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
        Medico medico = new Medico(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), Especialidad.valueOf(rs.getString("especialidad")));
            medicos.add(medico);
        }
    } catch (SQLException ex) {
        System.out.println("Error al obtener los médicos: " + ex.getMessage());
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

    return medicos;
}


}
