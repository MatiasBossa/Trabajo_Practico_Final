/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//@author Nicolas

import Model.Data.EspecialidadData;
import Model.Entities.Especialidad;
import View.frmEspecialidades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControlEspecialidad0 implements ActionListener, KeyListener, MouseListener{
    private Especialidad espE;
    private EspecialidadData espD;
    private frmEspecialidades frm;
    private ArrayList<Especialidad>listado;
    private DefaultTableModel modelo;

    public ControlEspecialidad0(Especialidad espE, EspecialidadData espD, frmEspecialidades frm) {
        this.espE = espE;
        this.espD = espD;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        
        this.frm.txtEspecialidad.addKeyListener( this);
        
        this.frm.tblEspecialidades.addMouseListener(this);
        
        bajarDatos();
        cargarDatosTabla();
        Iniciar();
    }
    
    private void Iniciar() {
        this.frm.setTitle("Formularios de especialidades");
        this.frm.txtId.setVisible(false);
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // <editor-fold defaultstate="collapsed" desc="Boton Guardar">
        if (e.getSource() == this.frm.btnGuardar) {
            //aca se asegura de no guardar nada si alguno los txtField estan vacios
            if(txtAEntidad()){
                this.espE.setIdEspecialidad(-1);
                if(comprobarEspecilidad()){
                    this.espD.guardarEspecialidad(this.espE);//guarda el Prestador en la base de datos
                    this.listado.add(this.espE);//guarda el Prestador en el ArrayList
                    JOptionPane.showMessageDialog(null, "Especilidad guardada.");
                    cargarDatosTabla();
                    limpiar();
                }
            }
        }// </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Modificar">
        if (e.getSource() == this.frm.btnModificar) {
            if (this.frm.txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Para modificar una Especialidad tiene que seleccionar uno de la lista");
            } else if (txtAEntidad() && comprobarEspecilidad()) {
                this.espD.modificarEspecialidad(this.espE);
                for (int i = 0; i < this.listado.size(); i++) {
                    this.listado.set(i, this.espE);//modifica el Prestador en el ArrayList
                    break;
                }
                JOptionPane.showMessageDialog(null, "Especilidad modificada.");
                cargarDatosTabla();
                limpiar();
            }
        }// </editor-fold>
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Limpiar">
        if (e.getSource() == this.frm.btnLimpiar) {
            limpiar();
            cargarDatosTabla();
        }// </editor-fold> 
        
        
    }

    @Override
    public void keyTyped(KeyEvent k) {
        char caracter = k.getKeyChar();
        if (k.getSource() == this.frm.txtEspecialidad ) {
            if (((caracter < 'A') || (caracter > 'Z')) && ((caracter < 'a') || (caracter > 'z'))) {
                k.consume();
            }
            //System.out.println("tecla presionada para nombre o apellido");
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent m) {
        int fila = this.frm.tblEspecialidades.getSelectedRow();
        this.frm.txtId.setText(String.valueOf(this.frm.tblEspecialidades.getValueAt(fila, 0)));
        this.frm.txtEspecialidad.setText(String.valueOf(this.frm.tblEspecialidades.getValueAt(fila, 1)));
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="txtAEntidad()">
    private boolean txtAEntidad() {
        if (this.frm.txtEspecialidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Formulario vacio no se ingreso una Especialidad");
            return false;
        }
        this.frm.txtEspecialidad.setText(this.frm.txtId.getText());
        
        return true;
    }// </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="comprueba si la Especialidad ya se guardo anteriormente">
    private boolean comprobarEspecilidad() {
        for (int i = 0; i < listado.size(); i++) {
            if ( espE.getTitulo().equals(listado.get(i).getTitulo()) && !espE.equals(listado.get(i)) ){
                JOptionPane.showMessageDialog(null, "ya existe esa Especialidad.");
                return false;
            }
        }
        return true;
    }// </editor-fold> 
    
    
    // <editor-fold defaultstate="collapsed" desc="Setea la JTable">
    private void seteoJTable() {
        modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.frm.tblEspecialidades.setModel(modelo);

        modelo.addColumn("Id");
        modelo.addColumn("Especialidad");
        int anchos[] = {0, 240};

        for (int i = 0; i < 2; i++) {
            this.frm.tblEspecialidades.getColumnModel().getColumn(i).setWidth(anchos[i]);
            this.frm.tblEspecialidades.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
            this.frm.tblEspecialidades.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
        }
    }// </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Carga los datos en la JTable">
    private void cargarDatosTabla() {
        seteoJTable();
        for (int i = 0; i < listado.size(); ++i) {
            Object[] ob = {listado.get(i).getIdEspecialidad(), listado.get(i).getTitulo()};
            modelo.addRow(ob);
            ob = null;
        }

    }// </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="bajarDatos()">
    private void bajarDatos(){
        System.out.println("datos bajados");
        this.listado = this.espD.listarEspecialidades();
    }// </editor-fold> 
    
    
    // <editor-fold defaultstate="collapsed" desc="limpiar()">
    private void limpiar() {
        frm.txtId.setText(null);
        frm.txtEspecialidad.setText(null);
        
    }// </editor-fold> 
    
    

    
    // <editor-fold defaultstate="collapsed" desc="Metodos de evento no usados">
    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }


    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    // </editor-fold>
    
    
}
