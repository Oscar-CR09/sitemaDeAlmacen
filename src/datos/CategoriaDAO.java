/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import database.Conexion;
import datos.interfaces.CrudSimpleInterface;
import entidades.Categoria;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author oscar
 */
public class CategoriaDAO implements CrudSimpleInterface<Categoria>{
    private final Conexion CON;
    private PreparedStatement ps; 
    private ResultSet rs;
    private boolean resp;
    
    public CategoriaDAO(){
        CON=Conexion.getInstancia();
    }
    @Override
    public List<Categoria> listar(String texto) {
        List<Categoria> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("Select * FROM categoria WHERE nombre LIKE ?");
            ps.setString(1, "%"+texto+"%");
            rs=ps.executeQuery();
            while (rs.next()) {                
                registros.add(new Categoria(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
                
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Categoria obj) {
        
    }

    @Override
    public boolean actualizar(Categoria obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean desactivar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean activar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int total() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existe(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
