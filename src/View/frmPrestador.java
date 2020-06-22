/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Data.EspecialidadData;
import Model.Data.PrestadorData;
import Model.Entities.Especialidad;
import Model.Entities.Prestador;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicolas
 */
public class frmPrestador extends javax.swing.JInternalFrame {

    /**
     * Creates new form vistaPrestador
     */
    public frmPrestador() {
        initComponents();
        
        DefaultTableModel tabla = new DefaultTableModel();
        jTPrestador.setModel(tabla);
        tabla.addColumn("Id");
        tabla.addColumn("Nombre");
        tabla.addColumn("Apellido");
        tabla.addColumn("Dni");
        tabla.addColumn("Especialidad");
        tabla.addColumn("Activo");
        int anchos[] = {0, 95, 95, 70, 120, 70};

        for (int i = 0; i < 6; i++) {
            jTPrestador.getColumnModel().getColumn(i).setWidth(anchos[i]);
            jTPrestador.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
            jTPrestador.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
        }

        jComboBoxBuscar.addItem(new Especialidad("Todos"));
        //jComboBoxBuscar.
        EspecialidadData espD = new EspecialidadData();
        List<Especialidad> listado = (List<Especialidad>) espD.listarEspecialidades();
        for (Especialidad espE: listado) {
            jComboBoxBuscar.addItem(espE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtEspecialidad = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        chkActivo = new javax.swing.JCheckBox();
        btnBorrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAnular = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTPrestador = new javax.swing.JTable();
        jComboBoxBuscar = new javax.swing.JComboBox<>();
        chkActivoBuscar = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("formulario de prestadores");
        setPreferredSize(new java.awt.Dimension(810, 400));

        txtId.setEditable(false);
        txtId.setEnabled(false);
        txtId.setOpaque(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        txtEspecialidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEspecialidadKeyTyped(evt);
            }
        });

        btnLimpiar.setText("Limpiar");

        btnBorrar.setText("Borrar");

        btnModificar.setText("Modificar");

        btnGuardar.setText("Guardar");

        jLabel5.setText("Activo");

        jLabel6.setText("Especialidad");

        btnAnular.setText("Anular");

        btnBuscar.setText("Buscar");

        jLabel4.setText("Dni");

        jLabel3.setText("Apellido");

        jLabel2.setText("Nombre");

        jLabel1.setText("idPrestador");

        jTPrestador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Apellido", "Dni", "Especialidad", "Activo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTPrestador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTPrestadorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTPrestador);
        if (jTPrestador.getColumnModel().getColumnCount() > 0) {
            jTPrestador.getColumnModel().getColumn(0).setResizable(false);
            jTPrestador.getColumnModel().getColumn(1).setResizable(false);
            jTPrestador.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTPrestador.getColumnModel().getColumn(2).setResizable(false);
            jTPrestador.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTPrestador.getColumnModel().getColumn(3).setResizable(false);
            jTPrestador.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTPrestador.getColumnModel().getColumn(4).setResizable(false);
            jTPrestador.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTPrestador.getColumnModel().getColumn(5).setResizable(false);
            jTPrestador.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        jComboBoxBuscar.setAutoscrolls(true);
        jComboBoxBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxBuscarItemStateChanged(evt);
            }
        });
        jComboBoxBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxBuscarMouseClicked(evt);
            }
        });
        jComboBoxBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBuscarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Listado de Prestadores");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Datos de Prestador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAnular))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(chkActivoBuscar)
                            .addGap(33, 33, 33)
                            .addComponent(btnBuscar)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(chkActivo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpiar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar)
                        .addGap(8, 8, 8)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpiar)
                            .addComponent(chkActivo)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBorrar)
                            .addComponent(btnModificar)
                            .addComponent(btnGuardar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAnular))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkActivoBuscar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed
    
    // <editor-fold defaultstate="collapsed" desc="Para pasar la info de la tabla al formulario">
    private void jTPrestadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPrestadorMouseClicked
                      
        int fila = jTPrestador.getSelectedRow();
        Integer id = (Integer) jTPrestador.getValueAt(fila, 0);
        //System.out.println("todo bien hasta aca ya q "+prestador);
        txtId.setText(String.valueOf(jTPrestador.getValueAt(fila, 0)));
        txtNombre.setText((String) jTPrestador.getValueAt(fila, 1));
        txtApellido.setText((String) jTPrestador.getValueAt(fila, 2));
        txtDni.setText(String.valueOf(jTPrestador.getValueAt(fila, 3)));
        txtEspecialidad.setText((String) jTPrestador.getValueAt(fila, 4));
        chkActivo.setSelected((boolean) jTPrestador.getValueAt(fila, 5));
        
    }//GEN-LAST:event_jTPrestadorMouseClicked
    // </editor-fold> 
    
    
    private void jComboBoxBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxBuscarActionPerformed

    private void jComboBoxBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxBuscarItemStateChanged
        
        /*
        if(evt.getStateChange()==ItemEvent.SELECTED){
            Especialidad esp = (Especialidad) jComboBoxBuscar.getSelectedItem();
            
        }*/
        
        
    }//GEN-LAST:event_jComboBoxBuscarItemStateChanged

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
      char caracter = evt.getKeyChar();

      // Verificar si la tecla pulsada no es un digito
      if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/))
      {
         evt.consume();  // ignorar el evento de teclado
      }
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
      char caracter = evt.getKeyChar();
      
      // Verificar si la tecla pulsada no es un digito
      if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/))
      {
         evt.consume();  // ignorar el evento de teclado
      }
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
      char caracter = evt.getKeyChar();

      // Verificar si la tecla pulsada no es un digito
      if( ((caracter < 'A') || (caracter > 'Z')) && ((caracter < 'a') || (caracter > 'z')) )
      {
         evt.consume();  // ignorar el evento de teclado
      }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
      char caracter = evt.getKeyChar();

      // Verificar si la tecla pulsada no es un digito
      if( ((caracter < 'A') || (caracter > 'Z')) && ((caracter < 'a') || (caracter > 'z')) )
      {
         evt.consume();  // ignorar el evento de teclado
      }
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtEspecialidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEspecialidadKeyTyped
      char caracter = evt.getKeyChar();

      // Verificar si la tecla pulsada no es un digito
      if( ((caracter < 'A') || (caracter > 'Z')) && ((caracter < 'a') || (caracter > 'z')) )
      {
         evt.consume();  // ignorar el evento de teclado
      }
    }//GEN-LAST:event_txtEspecialidadKeyTyped

    private void jComboBoxBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxBuscarMouseClicked
        // TODO add your handling code here:
        //este seria el caso cuando se clickea en cualquier parte de combobox exepto en este tipo de flecha hacia abajo
        /*EspecialidadData espD = new EspecialidadData();
        PrestadorData preD = new PrestadorData();
        List<Especialidad> listado = (List<Especialidad>) espD.listarEspecialidades();
        for (Especialidad espE: listado) {
            jComboBoxBuscar.addItem(espE);
        }*/


        
    }//GEN-LAST:event_jComboBoxBuscarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAnular;
    public javax.swing.JButton btnBorrar;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JCheckBox chkActivo;
    public javax.swing.JCheckBox chkActivoBuscar;
    public javax.swing.JComboBox<Especialidad> jComboBoxBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTPrestador;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtDni;
    public javax.swing.JTextField txtEspecialidad;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
