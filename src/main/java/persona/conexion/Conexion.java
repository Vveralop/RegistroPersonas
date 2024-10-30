package persona.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection() {
        Connection conexion = null;
        var baseDatos = "javatest";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "admin";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            System.out.println("Error al  conectarse a la BBDDD: " + e.getMessage());
        }
        return conexion;
    }

//    public static void main(String[] args) {
//        var conexion = Conexion.getConnection();
//        if (conexion != null)
//            System.out.println("Conexión exitosa: " + conexion);
//        else
//            System.out.println("Conexión con error!");
//    }
}
