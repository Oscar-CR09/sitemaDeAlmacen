/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import database.Conexion;
import datos.interfaces.CrudPaginadoInterface;
import entidades.Articulo;
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
public class ArticuloDAO implements CrudPaginadoInterface<Articulo>{
    private final Conexion CON;
    private PreparedStatement ps; 
    private ResultSet rs;
    private boolean resp;
    
    public ArticuloDAO(){
        CON=Conexion.getInstancia();
    }
    @Override
    public List<Articulo> listar(String texto, int totalPorPagina, int numPagina) {
        List<Articulo> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("Select a.id, a.categoria_id, c.nombre as categoria_nombre, a.codigo,a.nombre, a.precio_venta, a.stock, a.descripcion, a.images, a.activo FROM articulo a inner join categoria c ON a.categoria_id=c.id  WHERE a.nombre LIKE ? ORDER BY a.id ASC LIMIT ?, ?");
            ps.setString(1, "%"+texto+"%");
            ps.setInt(2, (numPagina-1)*totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs=ps.executeQuery();
            while (rs.next()) {                
                registros.add(new Articulo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getBoolean(10)));
                
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
    public boolean insertar(Articulo obj) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("INSERT INTO articulo(categoria_id,codigo,nombre,precio_venta,stock,descripcion,images,activo) VALUES(?,?,?,?,?,?,?,1)");
            ps.setInt(1,obj.getCategoriaId());
            ps.setString(2, obj.getCodigo());
            ps.setString(3,obj.getNombre());
            ps.setDouble(4, obj.getPrecioVenta());
            ps .setInt(5, obj.getStock());
            ps.setString(6,obj.getDescripcion());
            ps.setString(7, obj.getImagen());
            if (ps.executeUpdate()>0) {
                resp=true;                
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Articulo obj) {
         resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE articulo SET categoria_id=?,codigo=?,nombre=?,precio_venta=?,stock=?,descripcion=?,images=? WHERE id=? ");
            ps.setInt(1,obj.getCategoriaId());
            ps.setString(2, obj.getCodigo());
            ps.setString(3,obj.getNombre());
            ps.setDouble(4, obj.getPrecioVenta());
            ps .setInt(5, obj.getStock());
            ps.setString(6,obj.getDescripcion());
            ps.setString(7, obj.getImagen());
            ps.setInt(8, obj.getId());
            if (ps.executeUpdate()>0) {
                resp=true;                
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean desactivar(int id) {
         resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE articulo SET activo=0 WHERE id=?");
            ps.setInt(1,id);
            if (ps.executeUpdate()>0) {
                resp=true;                
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean activar(int id) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE articulo SET activo=1 WHERE id=?");
            ps.setInt(1,id);
            if (ps.executeUpdate()>0) {
                resp=true;                
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public int total() {
        int totalRegistro=0;
        try {
            ps=CON.conectar().prepareStatement("SELECT COUNT(id) AS total_registros FROM articulo"); //"SELECT  COUNT (id) FROM categoria"
            rs=ps.executeQuery();
            while (rs.next()) {                
                totalRegistro=rs.getInt("total_registros");
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return totalRegistro;
    }

    @Override
    public boolean existe(String texto) {
         resp=false;
        try {
            ps=CON.conectar().prepareStatement("SELECT nombre FROM articulo WHERE nombre=?");
            ps.setString(1, texto);
            rs=ps.executeQuery();
            if (rs.getRow()>0) {
                resp=true;
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            ps=null;
            CON.desconectar();
        }
        return resp;
        
    }
    
}
