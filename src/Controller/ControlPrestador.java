package Controller;

import Model.Data.PrestadorData;
import Model.Entities.Especialidad;
import Model.Entities.Prestador;
import View.vistaPrestador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

//@author Nicolas
public class ControlPrestador implements ActionListener{
    private Prestador modE;
    private PrestadorData modD;
    private vistaPrestador frm;

    public ControlPrestador(Prestador modE, PrestadorData modD,vistaPrestador frm) {
        this.modE = modE;
        this.modD = modD;
        this.frm = frm;
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnBorrar.addActionListener(this);
        this.frm.btnAnular.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        //this.frm.chkActivo.addActionListener((ActionListener) this);

    }

    public void Iniciar() {
        frm.setTitle("Prestador");
        frm.setLocationRelativeTo(null);
        //frm.txtId.setVisible(true);//se cambiara a falso ya que el id no debe ser visto por el usuario
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
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
            limpiar();
        }
        
        if (e.getSource() == frm.btnModificar) {
            txtAEntidad();
            modD.modificarPrestador(modE);
            JOptionPane.showMessageDialog(null, "Prestador modificado.");
            limpiar();
        }
        
        if (e.getSource() == frm.btnBorrar) {
            
            int idPrestador = Integer.parseInt(frm.txtId.getText());
            modD.borrarPrestador(idPrestador);
            JOptionPane.showMessageDialog(null, "Prestador borrado.");
            limpiar();
        }

        if (e.getSource() == frm.btnAnular) {
            int idPrestador = Integer.parseInt(frm.txtId.getText());
            modD.desactivarPrestador(idPrestador);
            JOptionPane.showMessageDialog(null, "Prestador anulado.");
            limpiar();
        }
        
        if (e.getSource() == frm.btnBuscar) {
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
            }
            
            
        }
        
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }
    }
    
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
    
}
