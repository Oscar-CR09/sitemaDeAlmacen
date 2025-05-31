/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;
import entidades.Categoria;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import negocio.ArticuloControl;
/**
 *
 * @author oscar
 */
public class frmArticulo extends javax.swing.JInternalFrame {
    private final ArticuloControl CONTROL;
    private String accion;
    private String nombreAnt;
    
    private String rutaOrigen;
    private String rutaDestino;
    private final String DIRECTORIO="src/files/articulos/";
    private String imagen="";
    private String imagenAnt;
    
    private int totalPorPagina=10;
    private int numPagina=1;
    private boolean PrimeraCarga=true;
    private int TotalRegistro;
    
    
    /**
     * Creates new form frmCategoria
     */
    public frmArticulo() {
        initComponents();
        this.CONTROL=new ArticuloControl();
        this.paginar();
        this.listar("",false);
        this.PrimeraCarga=false;
        tabGeneral.setEnabledAt(1, false);
        this.accion="guardar";
        txtId.setVisible(false);
        this.cargarCategorias();
        
    }
    
    private void ocultarColumnas(){
        tablaListado.getColumnModel().getColumn(1).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(1).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
    }
    
    private void paginar(){
        int totalPaginas;
        
        this.TotalRegistro=this.CONTROL.total();
        this.totalPorPagina=Integer.parseInt((String)cboTotalPaginas.getSelectedItem());
        totalPaginas=(int)(Math.ceil((double)this.TotalRegistro/this.totalPorPagina));
        
        if (totalPaginas==0) {
            totalPaginas=1;
        }
        cboTotalPaginas.removeAllItems();
        
        for (int i = 1; i <= totalPaginas; i++) {
            cboNumeroPaginas.addItem(Integer.toString(i));
            
        }
        cboNumeroPaginas.setSelectedIndex(0);
               
    }
            //Marca in error de parse int verificar que es lo que lo esta activando 
    private void listar(String texto, boolean paginar){
        this.totalPorPagina=Integer.parseInt((String)cboTotalPaginas.getSelectedItem());
        if ((String )cboNumeroPaginas.getSelectedItem()!=null) {
            this.numPagina=Integer.parseInt((String)cboNumeroPaginas.getSelectedItem());
        }
        if (paginar==true) {
            tablaListado.setModel(this.CONTROL.listar(texto, this.totalPorPagina, this.numPagina));
        }else{
              tablaListado.setModel(this.CONTROL.listar(texto, this.totalPorPagina, 1));            
        }
        
        TableRowSorter orden = new TableRowSorter(tablaListado.getModel());
        tablaListado.setRowSorter(orden);
        this.ocultarColumnas();
        lblTotalRegistros.setText("Mostrando " + this.CONTROL.totalMostrados() + " de un total de " + this.CONTROL.total() + " registros");
        
    }
    
    private void cargarCategorias(){
        DefaultComboBoxModel items = this.CONTROL.seleccionar();
        cboCategoria.setModel(items);
        
    }
    
    private void subirImagen(){
        File origen=new File(this.rutaOrigen);
        File destino=new File(this.rutaDestino);
        try {
            InputStream in =new FileInputStream(origen);
            OutputStream out = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len - in.read(buf))> 0) {
                out.write(buf,0,len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void limpiar(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDecripcion.setText("");
        txtId.setText("");
        txtPrecioVenta.setText("");
        txtStock.setText("");
        this.imagen="";
        this.imagenAnt="";
        lblImagen.setIcon(null);
        this.rutaDestino="";
        this.rutaOrigen="";
        this.accion="guardar";
    }
    private void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje, "Sistema",JOptionPane.ERROR_MESSAGE);
    }
    private void mensajeOk(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Sistema", JOptionPane.INFORMATION_MESSAGE);        
    }
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabGeneral = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        lblTotalRegistros = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        cboNumeroPaginas = new javax.swing.JComboBox<>();
        cboTotalPaginas = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDecripcion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        btbAgregar = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Articulos");

        tabGeneral.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Nombre:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaListado);

        lblTotalRegistros.setText("Registros");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDesactivar.setText("Desactivar");
        btnDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivarActionPerformed(evt);
            }
        });

        btnActivar.setText("Activar");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        cboNumeroPaginas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumeroPaginasActionPerformed(evt);
            }
        });

        cboTotalPaginas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "200", "500" }));
        cboTotalPaginas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTotalPaginasActionPerformed(evt);
            }
        });

        jLabel10.setText("#Paginas");

        jLabel11.setText("Total de Registros por Paginas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1061, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar)
                                .addGap(18, 18, 18)
                                .addComponent(btnNuevo)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(76, 76, 76)
                                        .addComponent(cboNumeroPaginas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnDesactivar)
                                        .addGap(70, 70, 70)
                                        .addComponent(btnActivar)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(215, 215, 215)
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboTotalPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNumeroPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTotalPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesactivar)
                    .addComponent(btnActivar))
                .addContainerGap())
        );

        tabGeneral.addTab("Listado", jPanel1);

        jLabel2.setText("Nombre (*)");

        jLabel3.setText("Descripcion");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtDecripcion.setColumns(20);
        txtDecripcion.setRows(5);
        jScrollPane2.setViewportView(txtDecripcion);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setText("(*) Indica que es un Campo obligatorio.");

        jLabel5.setText("Categoria:");

        jLabel6.setText("Código");

        jLabel7.setText("Precio Venta (*)");

        jLabel8.setText("Stock (*)");

        jLabel9.setText("Imagen");

        btbAgregar.setText("Agregar");
        btbAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbAgregarActionPerformed(evt);
            }
        });

        lblImagen.setBackground(new java.awt.Color(255, 255, 0));
        lblImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnGuardar)
                                        .addGap(33, 33, 33)
                                        .addComponent(btnCancelar))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtNombre)
                                                    .addComponent(txtCodigo)
                                                    .addComponent(txtPrecioVenta)
                                                    .addComponent(txtStock))
                                                .addGap(26, 26, 26)
                                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 100, Short.MAX_VALUE)))))
                        .addGap(621, 621, 621))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(btbAgregar)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(btbAgregar)
                        .addGap(118, 118, 118))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(47, 47, 47))
        );

        tabGeneral.addTab("Mantenimiento", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabGeneral)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        this.listar(txtBuscar.getText(),false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        tabGeneral.setEnabledAt(1, true);
        tabGeneral.setEnabledAt(0, false);
        tabGeneral.setSelectedIndex(1);
        this.accion="guardar";
        btnGuardar.setText("Guardar");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        tabGeneral.setEnabledAt(0, true);
        tabGeneral.setEnabledAt(1, false);
        tabGeneral.setSelectedIndex(0);
        this.limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (txtNombre.getText().length()==0 || txtNombre.getText().length()>100) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un nombre y no debe de ser mayor a 100 caracteres, es obligatorio.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return;
        }
         if (txtPrecioVenta.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un precio de venta, es obligatorio.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtPrecioVenta.requestFocus();
            return;
        }
          if (txtStock.getText().length()==0) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un stock del articulo, es obligatorio.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtStock.requestFocus();
            return;
        }
        if (txtDecripcion.getText().length()>255) {
            JOptionPane.showMessageDialog(this, "Debes ingresar una descripcion y no debe de ser mayor a 255 caracteres.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtDecripcion.requestFocus();
            return;
        }
        
        String resp;
        if (this.accion.equals("editar")) {
            //editar
            String imagenActual="";
            if (this.imagen.equals("")) {
                imagenActual=this.imagenAnt;
            } else {
                imagenActual=this.imagen;
            }
            Categoria seleccionado = (Categoria)cboCategoria.getSelectedItem();
            resp=this.CONTROL.actualizar(Integer.parseInt(txtId.getText()),seleccionado.getId(),txtCodigo.getText() ,txtNombre.getText(), this.nombreAnt,Double.parseDouble(txtPrecioVenta.getText()),Integer.parseInt(txtStock.getText()),txtDecripcion.getText(),imagenActual);
            resp="OK";
            if (resp.equals("OK")) {
                if (!this.imagen.equals("")) {
                    this.subirImagen();
                }
                this.mensajeOk("Actualizado correctamente");
                this.limpiar();
                this.listar("",false);
                tabGeneral.setSelectedIndex(0);
                tabGeneral.setEnabledAt(1, false);
                tabGeneral.setEnabledAt(0, true);
            } else {
                this.mensajeError(resp);
            }
        
        } else {
            //guardar
            Categoria seleccionado = (Categoria)cboCategoria.getSelectedItem();
            resp=this.CONTROL.insertar(seleccionado.getId(),txtCodigo.getText() ,txtNombre.getText(), Double.parseDouble(txtPrecioVenta.getText()),Integer.parseInt(txtStock.getText()),txtDecripcion.getText(),this.imagen);
            resp="OK"; 
            if (resp.equals("OK")) {
                if (!this.imagen.equals("")) {
                    this.subirImagen();
                }
                this.mensajeOk("Registrado correctamente");
                this.limpiar();
                this.listar("",false);
            } else {
                this.mensajeError(resp);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (tablaListado.getSelectedRowCount()==1) {
            String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
            int categoriaId=Integer.parseInt(String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1)));
            String categoriaNombre=String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 2));
            String codigo = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));
            this.nombreAnt = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));
            String precioVenta = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 5));
            String stock = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 6));
            String descripcion = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 7));
            this.imagenAnt = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 8));
            
            
            txtId.setText(id);
            Categoria seleccionado= new Categoria(categoriaId,categoriaNombre);
            cboCategoria.setSelectedItem(seleccionado);
            txtCodigo.setText(codigo);
            txtNombre.setText(nombre);
            txtPrecioVenta.setText(precioVenta);
            txtStock.setText(stock);            
            txtDecripcion.setText(descripcion);
            
            ImageIcon im =new ImageIcon(this.DIRECTORIO+this.imagenAnt);
            Icon icono = new ImageIcon(im.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
            lblImagen.setIcon(icono);
            lblImagen.repaint();
            
            tabGeneral.setEnabledAt(0, false);
            tabGeneral.setEnabledAt(1, true);
            tabGeneral.setSelectedIndex(1);
            this.accion="editar";
            btnGuardar.setText("Editar");
        } else {
            this.mensajeError("Seleccione 1 registro a editar.");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
        // TODO add your handling code here:}
        if (tablaListado.getSelectedRowCount()==1) {
            String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));

            if (JOptionPane.showConfirmDialog(this, "Deseas desactivar el registro " + nombre+ " ?","Desactivar", JOptionPane.YES_NO_OPTION)==0) {
                String resp=this.CONTROL.desactivar(Integer.parseInt(id));
                if (resp.equals("OK")) {
                    this.mensajeOk("Registro desactivado");
                    this.listar("",false);

                } else {
                    this.mensajeError(resp);
                }
            } 
        }else {
                this.mensajeError("Seleccione 1 registro a desactivar.");
            }
        
    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        // TODO add your handling code here:
        if (tablaListado.getSelectedRowCount()==1) {
            String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));

            if (JOptionPane.showConfirmDialog(this, "Deseas Activar el registro " + nombre+ " ?","Activar", JOptionPane.YES_NO_OPTION)==0) {
                String resp=this.CONTROL.activar(Integer.parseInt(id));
                if (resp.equals("OK")) {
                    this.mensajeOk("Registro Activado");
                    this.listar("",false);

                } else {
                    this.mensajeError(resp);
                }
            } 
        }else {
                this.mensajeError("Seleccione 1 registro Activado.");
            }
            
    }//GEN-LAST:event_btnActivarActionPerformed

    private void btbAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbAgregarActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        int estado =file.showOpenDialog(this);
        if (estado ==JFileChooser.APPROVE_OPTION) {
             this.imagen=file.getSelectedFile().getName();
             this.rutaOrigen=file.getSelectedFile().getAbsolutePath();
             this.rutaDestino=this.DIRECTORIO+ this.imagen;
             
             ImageIcon im =new ImageIcon(this.rutaOrigen);
             Icon icono = new ImageIcon(im.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
             lblImagen.setIcon(icono);
             lblImagen.repaint();
        }
    }//GEN-LAST:event_btbAgregarActionPerformed

    private void cboNumeroPaginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumeroPaginasActionPerformed
        // TODO add your handling code here:
        if (this.PrimeraCarga==false) {
            this.listar("", true);
        }
    }//GEN-LAST:event_cboNumeroPaginasActionPerformed

    private void cboTotalPaginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPaginasActionPerformed
        // TODO add your handling code here:
        //this.paginar();
    }//GEN-LAST:event_cboTotalPaginasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbAgregar;
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboNumeroPaginas;
    private javax.swing.JComboBox<String> cboTotalPaginas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDecripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
