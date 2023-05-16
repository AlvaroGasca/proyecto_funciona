/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alvarogasca
 */
public class Conexion {

    Connection conn1 = null;

    public Conexion() {
        conn1 = crearConexion();
    }

    private Connection crearConexion() {
        if (conn1 != null) {
            return conn1;
        }
        Connection con = null;
        try {
            String url1 = "jdbc:mysql://localhost:3306/proyecto_hospital";
            String user = "root";
            String password = "";
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Conectado a la bd");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR:La dirección no es válida o el usuario y clave");
            ex.printStackTrace();
        }
        return con;
    }

    public void cerrarConexion() {

        try {
            conn1.close();
            conn1=null;
        } catch (SQLException ex) {
            System.out.println("ERROR:al cerrar la conexión");
            ex.printStackTrace();
        }
    }
    public Connection getConexion() {
        return crearConexion();
    }
}

