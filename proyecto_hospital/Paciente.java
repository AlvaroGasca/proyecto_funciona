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

/**
 *
 * @author alvarogasca
 */
public class Paciente {
    private int id;
    private String nombre;
    private String apellidos;
    private int edad;
    private String genero;
    private String direccion;
    private String poblacion;
    private String provincia;
    private String codigoPostal;
    private String telefono;
    private String estado;

    public Paciente(int id, String nombre, String apellidos, int edad, String genero, String direccion, String poblacion, String provincia, String codigoPostal, String telefono, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.estado = estado;
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
    

    // Getters y setters para cada atributo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void insertar() {
    String sql = "INSERT INTO paciente(id, nombre, apellidos, edad, genero, direccion, poblacion, provincia, codigoPostal, telefono, estado) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = conexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);
        pstmt.setString(2, nombre);
        pstmt.setString(3, apellidos);
        pstmt.setInt(4, edad);
        pstmt.setString(5, genero);
        pstmt.setString(6, direccion);
        pstmt.setString(7, poblacion);
        pstmt.setString(8, provincia);
        pstmt.setString(9, codigoPostal);
        pstmt.setString(10, telefono);
        pstmt.setString(11, estado);

        pstmt.executeUpdate();
        System.out.println("Paciente insertado con éxito");

    } catch (SQLException e) {
        System.out.println("Error al insertar el paciente: " + e.getMessage());
    }
}

public void actualizar() {
    String sql = "UPDATE paciente SET nombre=?, apellidos=?, edad=?, genero=?, direccion=?, poblacion=?, provincia=?, codigoPostal=?, telefono=?, estado=? WHERE id=?";

    try (Connection conn = conexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, nombre);
        pstmt.setString(2, apellidos);
        pstmt.setInt(3, edad);
        pstmt.setString(4, genero);
        pstmt.setString(5, direccion);
        pstmt.setString(6, poblacion);
        pstmt.setString(7, provincia);
        pstmt.setString(8, codigoPostal);
        pstmt.setString(9, telefono);
        pstmt.setString(10, estado);
        pstmt.setInt(11, id);

        pstmt.executeUpdate();
        System.out.println("Paciente actualizado con éxito");

    } catch (SQLException e) {
        System.out.println("Error al actualizar el paciente: " + e.getMessage());
    }
}


public void eliminar() {
    String sql = "DELETE FROM pacientes WHERE id=?";

    try (Connection conn = conexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Paciente eliminado con éxito");

    } catch (SQLException e) {
        System.out.println("Error al eliminar el paciente: " + e.getMessage());
    }
}
    // Métodos adicionales

    public void mostrarInformacion() {
    try (Connection conn = conexion()) {
        String query = "SELECT id, nombre, apellidos, edad, genero, direccion, poblacion, provincia, codigoPostal, telefono, estado FROM paciente WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Apellidos: " + rs.getString("apellidos"));
                System.out.println("Edad: " + rs.getInt("edad"));
                System.out.println("Género: " + rs.getString("genero"));
                System.out.println("Dirección: " + rs.getString("direccion"));
                System.out.println("Población: " + rs.getString("poblacion"));
                System.out.println("Provincia: " + rs.getString("provincia"));
                System.out.println("Código Postal: " + rs.getString("codigoPostal"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
                System.out.println("Estado: " + rs.getString("estado"));
            } else {
                System.out.println("No se encontró el paciente con el ID: " + id);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error al mostrar la información del paciente: " + ex.getMessage());
    }
}
    
    public static Paciente leerPorID(int id) {
    Paciente paciente = null;
    try (Connection con = conexion()) {
        String query = "SELECT id, nombre, apellidos, edad, genero, direccion, poblacion, provincia, codigoPostal, telefono, estado FROM paciente WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                paciente = new Paciente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getInt("edad"),
                    rs.getString("genero"),
                    rs.getString("direccion"),
                    rs.getString("poblacion"),
                    rs.getString("provincia"),
                    rs.getString("codigoPostal"),
                    rs.getString("telefono"),
                    rs.getString("estado")
                );
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return paciente;
}
 
   public static ArrayList<Paciente> obtenerTodos() {
    ArrayList<Paciente> pacientes = new ArrayList<>();
    try (Connection con = conexion()) {
        String query = "SELECT id, nombre, apellidos, edad, genero, direccion, poblacion, provincia, codigoPostal, telefono, estado FROM paciente";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Paciente paciente = new Paciente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getInt("edad"),
                    rs.getString("genero"),
                    rs.getString("direccion"),
                    rs.getString("poblacion"),
                    rs.getString("provincia"),
                    rs.getString("codigoPostal"),
                    rs.getString("telefono"),
                    rs.getString("estado")
                );
                pacientes.add(paciente);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return pacientes;
}
}
