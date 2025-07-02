/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 *   Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;
import datos.ArticuloDAO;
import datos.CategoriaDAO;
import datos.IngresoDAO;
import entidades.Articulo;
import entidades.Categoria;
import entidades.Ingreso;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author oscar
 */
public class IngresoControl {
    private final IngresoDAO DATOS;
    private final Ingreso obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    
    public IngresoControl(){
        this.DATOS=new IngresoDAO();
        this.obj= new Ingreso();         
        this.registrosMostrados=0;
    }
    
    public DefaultTableModel  listar(String texto,int totalPorPagina,int numPagina){
        List<Ingreso> lista=new ArrayList();
        lista.addAll(DATOS.listar(texto,totalPorPagina,numPagina));
        
        String[ ] titulos={"Id","Usuario ID","Usuario","Proveedor ID","Provedor","Tipo Comprobante","Serie","Numero","Fecha","Impuesto","Total","Estado"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String[] registro = new String[12];
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        
        this.registrosMostrados=0;
        for (Ingreso item:lista) {
            registro[0]=Integer.toString(item.getId());
            registro[1]=Integer.toString(item.getUsuarioId());
            registro[2]=item.getUsuarioNombre();
            registro[3]=Integer.toString(item.getPersonaId());
            registro[4]=item.getPersonaNombre();
            registro[5]=item.getTipoComprobante();
            registro[6]=item.getSerieComprobante();
            registro[7]=item.getNumComprobante();
            registro[8]=sdf.format(item.getFecha());
            registro[9]=Double.toString(item.getImpuesto());
            registro[10]=Double.toString(item.getTotal());
            registro[11]=item.getEstado();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
    public String insertar(int personaId, String tipoComprobante, String serieComprobante, String numComprobante, double impuesto, double total, DefaultTableModel modeloDetalles){
        if (DATOS.existe(serieComprobante,numComprobante)) {
            return "El registro ya existe.";
        } else {
            obj.setCategoriaId(categoriaId);
            obj.setCodigo(codigo);
            obj.setNombre(nombre);
            obj.setPrecioVenta(precioVenta);
            obj.setStock(stock);
            obj.setDescripcion(descripcion);
            obj.setImagen(imagen);
            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro";
            }
            
        }
    }
    public String actualizar(int id, int categoriaId, String codigo, String nombre,String nombreAnt, double precioVenta, int stock, String descripcion, String imagen){
        if (nombre.equals( nombreAnt)) {
            
            obj.setId(id);
            obj.setCategoriaId(categoriaId);
            obj.setCodigo(codigo);
            obj.setNombre(nombre);
            obj.setPrecioVenta(precioVenta);
            obj.setStock(stock);
            obj.setDescripcion(descripcion);
            obj.setImagen(imagen); 
            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "ERROR en el actualizacion.";
            }
        } else {
            if (DATOS.existe(nombre)) {
                return "El registro ya existe";
            } else {
                obj.setId(id);
                obj.setCategoriaId(categoriaId);
                obj.setCodigo(codigo);
                obj.setNombre(nombre);
                obj.setPrecioVenta(precioVenta);
                obj.setStock(stock);
                obj.setDescripcion(descripcion);
                obj.setImagen(imagen);
                if (DATOS.actualizar(obj)) {
                    return "OK";
                } else {
                    return "ERROR en la actualizacion.";
                }
            }
        }
    }
    public String desactivar(int id ){
        if (DATOS.desactivar(id)) {
            return"OK";
        } else {
            return "No se puede desactivar el registro.";
        }
    }
    
     public String activar(int id ){
         if (DATOS.activar(id)) {
            return"OK";
        } else {
            return "No se puede desactivar el registro.";
        }
    }
     public int total(){
                  return DATOS.total();
     }
     public int totalMostrados(){
         return this.registrosMostrados;
     }
}
