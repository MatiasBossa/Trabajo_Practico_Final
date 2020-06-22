package Controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import View.frmAfiliado;
import Model.Data.AfiliadoData;
import Model.Entities.Afiliado;
import java.awt.event.ActionListener;

public class ControlAfiliado implements ActionListener
{
    private Afiliado modE;
    private AfiliadoData modD;
    private frmAfiliado frm;
    
    public ControlAfiliado(final Afiliado modE, final AfiliadoData modD, final frmAfiliado frm) {
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
    }
    
    public void Iniciar() {
        this.frm.setTitle("Afiliado");
        
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        
        if (e.getSource() == this.frm.btnGuardar) {
            this.txtAEntidad();
            this.modD.guardarAfiliado(this.modE);
            JOptionPane.showMessageDialog(null, "Afiliado guardado.");
            this.txtATabla();
            this.limpiar();
        }
        
        
        if (e.getSource() == this.frm.btnModificar) {
            this.txtAEntidad();
            this.modD.modificarAfiliado(this.modE);
            JOptionPane.showMessageDialog(null, "Afiliado modificado.");
            this.limpiar();
        }
        
        

        if (e.getSource() == this.frm.btnBorrar) {
            final int idAfiliado = Integer.parseInt(this.frm.txtIdAfiliado.getText());
            this.modD.borrarAfiliado(idAfiliado);
            JOptionPane.showMessageDialog(null, "Afiliado borrado.");
            this.limpiar();
            
        }
        
        
        if (e.getSource() == this.frm.btnAnular) {
            final int idAfiliado = Integer.parseInt(this.frm.txtIdAfiliado.getText());
            this.modD.desactivarAfiliado(idAfiliado);
            JOptionPane.showMessageDialog(null, "Afiliado anulado.");
            this.limpiar();
        }
        
        
        if (e.getSource() == this.frm.btnBuscar) {
            
            final DefaultTableModel tabla = new DefaultTableModel(){
                
                public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;           
                 }
            };
            
            this.frm.tblAfiliados.setModel(tabla);
            final Afiliado a = new Afiliado();
            final AfiliadoData ad = new AfiliadoData();
            final Afiliado listado = ad.buscarAfiliadoDni(Integer.parseInt(this.frm.buscarId.getText()));
            
            tabla.addColumn("Nombre");
            tabla.addColumn("Apellido");
            tabla.addColumn("DNI");
            tabla.addColumn("Activo");
            for (int i = 0; i < 1; ++i) {
                Object[] ob = { listado.getNombre(), listado.getApellido(), listado.getDni(), listado.getActivo() };
                tabla.addRow(ob);
                ob = null;
            }
        }
        if (e.getSource() == this.frm.btnLimpiar) {
            this.limpiar();
        }
    }
    
    private void limpiar() {
        this.frm.txtIdAfiliado.setText(null);
        this.frm.txtNombre.setText(null);
        this.frm.txtApellido.setText(null);
        this.frm.txtDni.setText(null);
        this.frm.chkActivo.setSelected(false);
    }
    
    private void txtAEntidad() {
        this.modE.setId(Integer.parseInt(this.frm.txtIdAfiliado.getText()));
        this.modE.setNombre(this.frm.txtNombre.getText());
        this.modE.setApellido(this.frm.txtApellido.getText());
        this.modE.setDni((long)Integer.parseInt(this.frm.txtDni.getText()));
        this.modE.setActivo(this.frm.chkActivo.isSelected());
    }
    
    private void txtATabla() {
        final Object[] fila = new Object[5];
        fila[0] = this.frm.txtIdAfiliado.getText();
        fila[1] = this.frm.txtNombre.getText();
        fila[2] = this.frm.txtApellido.getText();
        fila[3] = this.frm.txtDni.getText();
        fila[4] = this.frm.chkActivo.isSelected();
        final DefaultTableModel tabla = new DefaultTableModel();
        this.frm.tblAfiliados.setModel(tabla);
        tabla.addRow(fila);
    }
}