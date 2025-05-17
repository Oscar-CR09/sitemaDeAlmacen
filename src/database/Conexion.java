/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author oscar
 */
public class Conexion {
    private final String DRIVER="com.mysql.cj.jdbc.Driver";//com.mysql.cj.jdbc.Driver
    private final String URL="jdbc:mysql://localhost:3306/";
    private final String DB="dbsystema";
    private final String USER="root";
    private final String PASSWORD="1989cero";
    
    public Connection cadena;
    public Conexion(){
        this.cadena=null;
    }   
    public Connection conectar(){
        try {
            Class.forName(DRIVER);
            this.cadena=DriverManager.getConnection(URL+DB,USER,PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.cadena;
    }
    public void desconectar(){
        try {
            this.cadena.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
}
