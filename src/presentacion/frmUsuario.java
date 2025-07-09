/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;
import entidades.Rol;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import negocio.UsuarioControl;
/**
 *
 * @author oscar
 */
public class frmUsuario extends javax.swing.JInternalFrame {
    
    private final UsuarioControl CONTROL;
    private String accion;
    private String emailAnt;
    
    private int totalPorPagina=10;
    private int numPagina=1;
    private boolean PrimeraCarga=true;
    private int TotalRegistro;
    
    
    /**
     * Creates new form frmCategoria
     */
    public frmUsuario() {
        initComponents();
        this.CONTROL=new UsuarioControl();
        this.paginar();
        this.listar("",false);
        this.PrimeraCarga=false;
        tabGeneral.setEnabledAt(1, false);
        this.accion="guardar";
        txtId.setVisible(false);
        this.cargarRoles();
        
    }
    
    private void ocultarColumnas(){
        tablaListado.getColumnModel().getColumn(1).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(1).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(9).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(9).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
    }
    
    private void paginar(){
       /* int totalPaginas;
        
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
           */
       
        int totalPaginas;
        
        this.TotalRegistro = this.CONTROL.total(); // Obtiene el total de registros de la BD
        // Asegúrate de que cboTotalPaginas.getSelectedItem() no sea null al inicio
        // y que el valor sea un String parseable a int.
        try {
            this.totalPorPagina = Integer.parseInt((String)cboTotalPaginas.getSelectedItem());
        } catch (NumberFormatException e) {
            // Manejar el caso donde el item no es un número válido o es null
            System.err.println("Error al parsear totalPorPagina: " + e.getMessage());
            this.totalPorPagina = 10; // Valor por defecto si hay un error
        }
        
        totalPaginas = (int)(Math.ceil((double)this.TotalRegistro / this.totalPorPagina));
        
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
        
        cboNumeroPaginas.removeAllItems(); // Limpia los items actuales
        
        for (int i = 1; i <= totalPaginas; i++) {
            cboNumeroPaginas.addItem(Integer.toString(i));
        }
        // Solo establece el índice seleccionado si hay elementos
        if (cboNumeroPaginas.getItemCount() > 0) {
            cboNumeroPaginas.setSelectedIndex(0);
        }
    }
            //Marca in error de parse int verificar que es lo que lo esta activando 
    private void listar(String texto, boolean paginar){
        //this.totalPorPagina=Integer.parseInt((String)cboTotalPaginas.getSelectedItem()); ANTERIOR
       /* if ((String )cboNumeroPaginas.getSelectedItem()!=null) {
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
        */
            try {
            this.totalPorPagina = Integer.parseInt((String)cboTotalPaginas.getSelectedItem());
        } catch (NumberFormatException e) {
            System.err.println("Error al parsear totalPorPagina en listar: " + e.getMessage());
            this.totalPorPagina = 10; // Default
        }

        // Si es la primera carga o si se está paginando
        if (paginar || this.PrimeraCarga) {
            // Asegúrate de que cboNumeroPaginas.getSelectedItem() no sea null
            if (cboNumeroPaginas.getSelectedItem() != null) {
                this.numPagina = Integer.parseInt((String)cboNumeroPaginas.getSelectedItem());
            } else {
                this.numPagina = 1; // Default a la primera página si no hay nada seleccionado
            }
        }
        // Si paginar es false (ej. después de activar/desactivar/guardar), mantén la numPagina actual
        // Si paginar es true (desde los controles de paginación), usa la numPagina actualizada

        tablaListado.setModel(this.CONTROL.listar(texto, this.totalPorPagina, this.numPagina));
        
        TableRowSorter orden = new TableRowSorter(tablaListado.getModel());
        tablaListado.setRowSorter(orden);
        this.ocultarColumnas();
        lblTotalRegistros.setText("Mostrando " + this.CONTROL.totalMostrados() + " de un total de " + this.CONTROL.total() + " registros");
    
       
       
    }
    
    private void cargarRoles(){
        DefaultComboBoxModel items = this.CONTROL.seleccionar();
        cboRol.setModel(items);
        
    }
    //
    private void limpiar(){
        txtNombre.setText("");
        txtId.setText("");
        txtNumDocumento.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtClave.setText("");
        this.accion="guardar";
        
        // Al limpiar, también recarga la paginación para asegurar que los combos estén actualizados
        this.paginar(); 
        this.listar("", false); // Vuelve a la primera página con la vista limpia
   
        
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
        txtNombre = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboRol = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboTipoDocumento = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNumDocumento = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtClave = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuarios");

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesactivar)
                    .addComponent(btnActivar))
                .addContainerGap())
        );

        tabGeneral.addTab("Listado", jPanel1);

        jLabel2.setText("Nombre (*)");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

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

        jLabel5.setText("Rol:");

        jLabel7.setText("Número Documentos:");

        jLabel8.setText("Dirección:");

        jLabel9.setText("Teléfono:");

        jLabel6.setText("Tipo Documentos:");

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CEDULA", "PASAPORTE" }));

        jLabel12.setText("Email (*)");

        jLabel13.setText("Clave (*)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumDocumento)
                                    .addComponent(cboRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDireccion)
                                    .addComponent(txtTelefono)
                                    .addComponent(txtEmail)
                                    .addComponent(txtClave)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboTipoDocumento, 0, 285, Short.MAX_VALUE)
                                    .addComponent(txtNombre))))
                        .addGap(18, 18, 18)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(539, 539, 539))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(33, 33, 33)
                        .addComponent(btnCancelar))
                    .addComponent(jLabel4))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar))
                        .addGap(160, 160, 160))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tabGeneral.addTab("Mantenimiento", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
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
       /* if (txtNombre.getText().length()==0 || txtNombre.getText().length()>70) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un nombre y no debe de ser mayor a 70 caracteres, es obligatorio.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return;
        }
         if (txtEmail.getText().length()==0 || txtEmail.getText().length()>50) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un email y no debe de ser mayor de a 50 caracteres, es obligatorio.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return;
        }
          if (txtClave.getText().length()== 0 || txtClave.getText().length()>64) {
            JOptionPane.showMessageDialog(this, "Debes ingresar una clave, es obligatorio.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtClave.requestFocus();
            return;
        }
        if (txtNumDocumento.getText().length()>20) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un numero de documento no mayor a 20 caracteres.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNumDocumento.requestFocus();
            return;
        }
         if (txtDireccion.getText().length()>70) {
            JOptionPane.showMessageDialog(this, "Debes ingresar una derección no mayor de 70 caracteres.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtDireccion.requestFocus();
            return;
        }
           if (txtTelefono.getText().length()>15 ) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un teléfono no mayor a 15 caracteres.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtTelefono.requestFocus();
            return;
        }
        
        String resp;
        if (this.accion.equals("editar")) {
            //editar
            Rol seleccionado = (Rol)cboRol.getSelectedItem();
            resp=this.CONTROL.actualizar(Integer.parseInt(txtId.getText()),seleccionado.getId(),txtNombre.getText(),(String) cboTipoDocumento.getSelectedItem(), txtNumDocumento.getText(),txtDireccion.getText(),txtTelefono.getText(),txtEmail.getText(),this.emailAnt,txtClave.getText());
            resp="OK";
            if (resp.equals("OK")) {
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
            Rol seleccionado = (Rol)cboRol.getSelectedItem();
            resp=this.CONTROL.insertar(seleccionado.getId(),txtNombre.getText(),(String)cboTipoDocumento.getSelectedItem(),txtNumDocumento.getText(),txtDireccion.getText(),txtTelefono.getText(),txtEmail.getText(),txtClave.getText());
            resp="OK"; 
            if (resp.equals("OK")) {
                this.mensajeOk("Registrado correctamente");
                this.limpiar();
                this.listar("",false);
            } else {
                this.mensajeError(resp);
            }
        }
*/
       
       if (txtNombre.getText().length() == 0 || txtNombre.getText().length() > 70) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un nombre y no debe de ser mayor a 70 caracteres, es obligatorio.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return;
        }
        if (txtEmail.getText().length() == 0 || txtEmail.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un email y no debe de ser mayor de a 50 caracteres, es obligatorio.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return;
        }
        // IMPORTANTE: Para la clave, en modo edición, si el usuario no la cambia,
        // no debes enviarla vacía ni intentar hashear un string vacío.
        // La lógica en UsuarioControl.actualizar ya está esperando la clave hasheada.
        // Si txtClave está vacío en edición, puedes optar por:
        // 1. Pasar el hash antiguo (almacenado en emailAnt o en una nueva variable).
        // 2. Modificar UsuarioControl.actualizar para que ignore el campo clave si está vacío.
        // Actualmente, estás enviando txtClave.getText() que será "" si no se tocó,
        // lo que podría causar que se guarde un hash de cadena vacía o error.
        String claveParaGuardar = new String(txtClave.getPassword()); // Obtener el texto del JPasswordField

        if (this.accion.equals("guardar") && claveParaGuardar.length() == 0) { // Solo obligatorio si es un nuevo registro
            JOptionPane.showMessageDialog(this, "Debes ingresar una clave, es obligatorio.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtClave.requestFocus();
            return;
        }
        if (txtNumDocumento.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un numero de documento no mayor a 20 caracteres.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtNumDocumento.requestFocus();
            return;
        }
        if (txtDireccion.getText().length() > 70) {
            JOptionPane.showMessageDialog(this, "Debes ingresar una derección no mayor de 70 caracteres.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtDireccion.requestFocus();
            return;
        }
        if (txtTelefono.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un teléfono no mayor a 15 caracteres.", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtTelefono.requestFocus();
            return;
        }
        
        String resp;
        Rol seleccionado = (Rol)cboRol.getSelectedItem();

        if (this.accion.equals("editar")) {
            // Asegúrate de que tu UsuarioControl.actualizar tenga el parámetro extra para emailAnt
            // El problema aquí es que sobrescribías resp="OK" antes de la llamada a CONTROL.actualizar
            resp = this.CONTROL.actualizar(Integer.parseInt(txtId.getText()), seleccionado.getId(), txtNombre.getText(), (String)cboTipoDocumento.getSelectedItem(), txtNumDocumento.getText(), txtDireccion.getText(), txtTelefono.getText(), txtEmail.getText(), this.emailAnt, claveParaGuardar);
            
            if (resp.equals("OK")) {
                this.mensajeOk("Actualizado correctamente");
                this.limpiar();
                this.listar("", false); // Recargar la tabla en la página actual
                tabGeneral.setSelectedIndex(0);
                tabGeneral.setEnabledAt(1, false);
                tabGeneral.setEnabledAt(0, true);
            } else {
                this.mensajeError(resp);
            }
        } else { // Guardar (nuevo registro)
            // El problema aquí es que sobrescribías resp="OK" antes de la llamada a CONTROL.insertar
            resp = this.CONTROL.insertar(seleccionado.getId(), txtNombre.getText(), (String)cboTipoDocumento.getSelectedItem(), txtNumDocumento.getText(), txtDireccion.getText(), txtTelefono.getText(), txtEmail.getText(), claveParaGuardar);
            
            if (resp.equals("OK")) {
                this.mensajeOk("Registrado correctamente");
                this.limpiar();
                this.listar("", false); // Recargar la tabla en la página actual
                tabGeneral.setSelectedIndex(0); // Volver a la pestaña de listado
                tabGeneral.setEnabledAt(1, false); // Deshabilitar la pestaña de mantenimiento
                tabGeneral.setEnabledAt(0, true); // Habilitar la pestaña de listado
            } else {
                this.mensajeError(resp);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (tablaListado.getSelectedRowCount()==1) {
            String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
            int rolId=Integer.parseInt(String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1)));
            String rolNombre=String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 2));
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));
            String tipoDocumento = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 4));
            String numDocumento = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 5));
            String direccion = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 6));
            String telefono = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 7));
            String email = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 8));
            this.emailAnt = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 8));
            String clave = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 9));
            
            
            txtId.setText(id);
            Rol seleccionado= new Rol(rolId,rolNombre);
            cboRol.setSelectedItem(seleccionado);
            txtNombre.setText(nombre);
            cboTipoDocumento.setSelectedItem(tipoDocumento);
            txtNumDocumento.setText(numDocumento);
            txtDireccion.setText(direccion);
            txtTelefono.setText(telefono);
            txtEmail.setText(email);
            txtClave.setText(clave);
            
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
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));

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
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));

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

    private void cboNumeroPaginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumeroPaginasActionPerformed
        // TODO add your handling code here:
        if (this.PrimeraCarga==false) {
            this.listar("", true);
        }
    }//GEN-LAST:event_cboNumeroPaginasActionPerformed

    private void cboTotalPaginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPaginasActionPerformed
        // TODO add your handling code here:
        //this.paginar();
         if (this.PrimeraCarga == false) { // Asegúrate de que no se ejecute en la carga inicial
            this.paginar(); // Recalcula el número de páginas y rellena cboNumeroPaginas
            this.listar("", false); // Recarga la lista desde la página 1 con el nuevo totalPorPagina
        }
    }//GEN-LAST:event_cboTotalPaginasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboNumeroPaginas;
    private javax.swing.JComboBox<String> cboRol;
    private javax.swing.JComboBox<String> cboTipoDocumento;
    private javax.swing.JComboBox<String> cboTotalPaginas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumDocumento;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
