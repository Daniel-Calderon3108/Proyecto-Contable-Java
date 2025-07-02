package conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    public String driver,user,password,bd,urlBd;
    
    public Connection conn;
    
    public Conexion () {
        driver="com.mysql.jdbc.Driver";
        user="root";
        password="";
        bd="estructuracontable";
        urlBd="jdbc:mysql://localhost/"+bd;
        
        try {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.conn = DriverManager.getConnection(urlBd, user, password);
            System.out.println("Conexion creada");
        } catch (SQLException ex) {
            System.out.println("Error al conectarse en la bd "+ex.toString());
        }
    }
    
    public Connection obtenerConexion() {
            return this.conn;
        }
    
     public Connection cerrarConexion() {
        try {
            this.conn.close();
            this.conn=null;
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion en la bd "+ex.toString());
        }
        return this.conn;
    }
}
