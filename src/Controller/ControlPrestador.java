package Controller;

import Model.Data.EspecialidadData;
import Model.Data.PrestadorData;
import Model.Entities.Especialidad;
import Model.Entities.Prestador;
import View.frmPrestador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//@author Nicolas
public class ControlPrestador implements ActionListener{
    private Prestador modE;
    private PrestadorData modD;
    private frmPrestador frm;

    public ControlPrestador(Prestador modE, PrestadorData modD,frmPrestador frm) {
        this.modE = modE;
        this.modD = modD;
        this.frm = frm;
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnBorrar.addActionListener(this);
        this.frm.btnAnular.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.chkActivo.addActionListener(this);
        this.frm.chkActivoBuscar.addActionListener(this);

    }

    public void Iniciar() {
        frm.setTitle("Prestador");
        //frm.setLocationRelativeTo(null);
        //frm.txtId.setVisible(true);//se cambiara a falso ya que el id no debe ser visto por el usuario
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // <editor-fold defaultstate="collapsed" desc="Boton Guardar">
        if (e.getSource() == frm.btnGuardar) {
            txtAEntidad();
            modD.guardarPrestador(modE);
            /*int id = modD.guardarPrestador(modE);
            if (id != 0) {
                JOptionPane.showMessageDialog(null, "Registro guardado. idPrestador = " + id);
            } else {
                JOptionPane.showMessageDialog(null, "Error en el guardado del idPrestador");
            }*/
            JOptionPane.showMessageDialog(null, "Prestador guardado.");
            //txtATabla();
            limpiar();
        }// </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Modificar">
        if (e.getSource() == frm.btnModificar) {
            txtAEntidad();
            modD.modificarPrestador(modE);
            JOptionPane.showMessageDialog(null, "Prestador modificado.");
            //txtATabla();

            limpiar();
        }// </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Borrar">
        if (e.getSource() == frm.btnBorrar) {
            
            int idPrestador = Integer.parseInt(frm.txtId.getText());
            modD.borrarPrestador(idPrestador);
            JOptionPane.showMessageDialog(null, "Prestador borrado.");
            limpiar();
        }// </editor-fold> 

        
        // <editor-fold defaultstate="collapsed" desc="Boton Anular">
        if (e.getSource() == frm.btnAnular) {
            int idPrestador = Integer.parseInt(frm.txtId.getText());
            modD.desactivarPrestador(idPrestador);
            JOptionPane.showMessageDialog(null, "Prestador anulado.");
            limpiar();
        }// </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Buscar">
        if (e.getSource() == frm.btnBuscar) {

            DefaultTableModel tabla = new DefaultTableModel();
            frm.jTPrestador.setModel(tabla);

            Prestador p = new Prestador();
            PrestadorData pd = new PrestadorData();
            
            Especialidad esp = new Especialidad((String) frm.jComboBoxBuscar.getSelectedItem());
            
            EspecialidadData ed = new EspecialidadData();
            esp=ed.buscarEspecialidad(esp.getTitulo());
            
            System.out.println("item seleccionado "+esp.getTitulo());
            System.out.println("ESPECIALIDAD "+esp);
            
            p.setActivo(frm.chkActivoBuscar.isSelected());
            p.setEspecialidad(esp);

            tabla.addColumn("Nombre");
            tabla.addColumn("Apellido");
            tabla.addColumn("Dni");
            tabla.addColumn("Especialidad");
            tabla.addColumn("Activo");

            int anchos[] = {60, 60, 40, 80, 30};

            for (int i = 0; i < 5; i++) {
                frm.jTPrestador.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            tabla = pd.listarPrestador(p, tabla);

            //limpiar();
            /*
            if(frm.txtId.getText()==""){
                JOptionPane.showMessageDialog(null, "No se ingreso id.");
                limpiar();
            }else{
                int idPrestador = Integer.parseInt(frm.txtId.getText());
                modE = modD.buscarPrestador(idPrestador);
                if (modE != null) {
                    frm.txtId.setText(String.valueOf(modE.getId()));
                    frm.txtNombre.setText(modE.getNombre());
                    frm.txtApellido.setText(modE.getApellido());
                    frm.txtDni.setText(String.valueOf(modE.getDni()));
                    frm.chkActivo.setSelected(modE.getActivo());
                    frm.txtEspecialidad.setText(modE.getEspecialidad().getTitulo());
                    //frm.txtEspecialidad.setText("falta metodo");
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Prestador No encontrado. estaba"+frm.txtId.getText());
                    limpiar();
                }
            }*/
            
            
        }// </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Limpiar">
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }// </editor-fold> 
    }
    
    /*public void jComboBoxBuscarItemStateChanged(java.awt.event.ItemEvent evt){
        if(evt.getStateChange()==ItemEvent.SELECTED){
            Especialidad esp = (Especialidad) frm.jComboBoxBuscar.getSelectedItem();
            
        }
    }*/
    
    private void limpiar() {
        frm.txtId.setText(null);
        frm.txtNombre.setText(null);
        frm.txtApellido.setText(null);
        frm.txtDni.setText(null);
        frm.chkActivo.setSelected(false);
        frm.txtEspecialidad.setText(null);
    }
    
    private void txtAEntidad() {
        modE.setId(Integer.parseInt(frm.txtId.getText()));
        modE.setNombre(frm.txtNombre.getText());
        modE.setApellido(frm.txtApellido.getText());
        modE.setDni(Integer.parseInt(frm.txtDni.getText()));
        modE.setActivo(frm.chkActivo.isSelected());
        //esto deberia servir 
        modE.setEspecialidad(new Especialidad(frm.txtEspecialidad.getText()));
    }
    
    private void txtATabla(){//provoca inestabilidad debido a un problema logico
        Object[] fila = new Object[5];
        fila[0]=frm.txtNombre.getText();
        fila[1]=frm.txtApellido.getText();
        fila[2]=frm.txtDni.getText();
        fila[3]=frm.txtEspecialidad.getText();
        fila[4]=frm.chkActivo.isSelected();
        
        DefaultTableModel tabla = new DefaultTableModel();
        frm.jTPrestador.setModel(tabla);
        
        tabla.addRow(fila);
        
    }
    
}
