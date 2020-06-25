package Controller;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import View.frmAfiliado;
import Model.Data.AfiliadoData;
import Model.Data.OrdenData;
import Model.Entities.Afiliado;
import Model.Entities.Orden;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControlAfiliado implements ActionListener
{
    private Afiliado modE;
    private AfiliadoData modD;
    private frmAfiliado frm;
    private List<Afiliado> listado = new ArrayList<>();
    private OrdenData od = new OrdenData();
    private List<Orden> listadoOrden = null;
    
    
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
        
       this.frm.tblAfiliados.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            
            public void valueChanged(ListSelectionEvent lse) {

                int fila = frm.tblAfiliados.getSelectedRow();
                String dni = String.valueOf(frm.tblAfiliados.getValueAt(fila, 3));
                //Afiliado a = modD.buscarAfiliadoDni(Integer.parseInt(dni));
                int index = 0;
               // frm.txtIdAfiliado.setText(String.valueOf(a.getId()));
                frm.txtNombre.setText(String.valueOf(frm.tblAfiliados.getValueAt(fila, index++)));
                frm.txtApellido.setText(String.valueOf(frm.tblAfiliados.getValueAt(fila, index++)));
                frm.txtDni.setText(String.valueOf(frm.tblAfiliados.getValueAt(fila, index++)));
                frm.chkActivo.setSelected((boolean) frm.tblAfiliados.getValueAt(fila, index++));

            }

        });

        
        this.listado = modD.listarAfiliados();
        cargarDatosTabla();
        
        Funciones.SNumero(frm.txtIdAfiliado);
        Funciones.SLetras(frm.txtNombre);
        Funciones.SLetras(frm.txtApellido);
        Funciones.SNumero(frm.txtDni);
        Funciones.SNumero(frm.buscarId);
        
        this.Iniciar();
        
    }
    
    public void Iniciar() {
        this.frm.setTitle("Afiliado");
        
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        
        if (e.getSource() == this.frm.btnGuardar) {
            if(txtAEntidad()){
                this.modE.setId(-1);
                if(comprobarDni()){
                this.modD.guardarAfiliado(this.modE);
                JOptionPane.showMessageDialog(null, "Afiliado guardado.");
                cargarDatosTabla();
                limpiar();
                }
            } 
        }
        
        
        if (e.getSource() == this.frm.btnModificar) {
            try{
                this.txtAEntidad();
                this.modD.modificarAfiliado(this.modE);

                JOptionPane.showMessageDialog(null, "Afiliado modificado.");
                cargarDatosTabla();
                limpiar();
            }catch(Exception ee){
                cargarDatosTabla();
                JOptionPane.showMessageDialog(frm, "ERROR AL MODFICAR EL AFILIADO", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
    }
        
        

        if (e.getSource() == this.frm.btnBorrar) {
            final int idAfiliado = Integer.parseInt(this.frm.txtIdAfiliado.getText());
            listadoOrden = od.listarOrdenes(idAfiliado);
                if(listadoOrden.isEmpty()){
                     this.modD.borrarAfiliado(idAfiliado);
                    JOptionPane.showMessageDialog(null, "Afiliado borrado.");
  
                }else{
                    JOptionPane.showMessageDialog(frm, "ERROR AL BORRAR EL AFILIADO", "Error", JOptionPane.ERROR_MESSAGE);
                }
                cargarDatosTabla();
                this.limpiar();
        }
        
        
        if (e.getSource() == this.frm.btnAnular) {
          
            final int idAfiliado = Integer.parseInt(this.frm.txtIdAfiliado.getText());
            if(this.frm.chkActivo.isSelected()){
                this.modD.desactivarAfiliado(idAfiliado);
                JOptionPane.showMessageDialog(null, "Afiliado anulado.");
            }else{
                JOptionPane.showMessageDialog(frm, "ERROR EL AFILIADO YA ESTA ANULADO", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            cargarDatosTabla();
            this.limpiar();
        }
        
        
        if (e.getSource() == this.frm.btnBuscar) {
            
            final DefaultTableModel tabla = new DefaultTableModel(){
                
                @Override
                public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;           
                 }
            };
           try{
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
                
           }catch(Exception ee){
               cargarDatosTabla();
               JOptionPane.showMessageDialog(frm, "ERROR AL ENCONTRAR EL AFILIADO", "Error", JOptionPane.ERROR_MESSAGE);
           } 
            
        }
        
        if (e.getSource() == this.frm.btnLimpiar) {
            this.limpiar();
            cargarDatosTabla();
        }
    }
    
    private void limpiar() {
        this.frm.txtIdAfiliado.setText(null);
        this.frm.txtNombre.setText(null);
        this.frm.txtApellido.setText(null);
        this.frm.txtDni.setText(null);
        this.frm.chkActivo.setSelected(false);
    }
    
    private boolean txtAEntidad() {
        
        if(this.frm.txtIdAfiliado.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(frm, "INGRESE UN ID VALIDO", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.frm.txtNombre.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(frm, "INGRESE UN NOMBRE VALIDO", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.frm.txtApellido.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(frm, "INGRESE UN APELLIDO VALIDO", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.frm.txtDni.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(frm, "INGRESE UN ID VALIDO", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        this.modE.setId(Integer.parseInt(this.frm.txtIdAfiliado.getText()));
        this.modE.setNombre(this.frm.txtNombre.getText());
        this.modE.setApellido(this.frm.txtApellido.getText());
        this.modE.setDni((long)Integer.parseInt(this.frm.txtDni.getText()));
        this.modE.setActivo(this.frm.chkActivo.isSelected());
        return true;
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
    
    private boolean comprobarDni(){
        for(int i = 0; i < listado.size(); i++) {
            if (modE.getDni() == listado.get(i).getDni()&&!modE.equals(listado.get(i))) {
                //if(!preE.equals(listado.get(i))) {
                    JOptionPane.showMessageDialog(null, "Ya hay un Afiliado con ese Dni.");
                    return false;
                //}
            }
        }
        return true;
    }
 
    private void cargarDatosTabla() {
         final DefaultTableModel tabla = new DefaultTableModel(){
                
                public boolean isCellEditable(int row, int column) {
                
                return false;           
                 }
            };
            frm.tblAfiliados.setModel(tabla);
            final Afiliado a = new Afiliado();
            final AfiliadoData ad = new AfiliadoData();
            final List<Afiliado> listado = (List<Afiliado>)ad.listarAfiliados();
            
            tabla.addColumn("Nombre");
            tabla.addColumn("Apellido");
            tabla.addColumn("DNI");
            tabla.addColumn("Activo");
            for (int i = 0; i < listado.size(); ++i) {
                Object[] ob = { listado.get(i).getNombre(), listado.get(i).getApellido(), listado.get(i).getDni(), listado.get(i).getActivo() };
                tabla.addRow(ob);
                ob = null;
            }
        
    }
    
    public void mouseClicked(MouseEvent m){
        int fila = frm.tblAfiliados.getSelectedRow();
        
        int indice = 0;
        frm.txtIdAfiliado.setText(String.valueOf(frm.tblAfiliados.getValueAt(fila, indice++)));
        frm.txtNombre.setText((String)(frm.tblAfiliados.getValueAt(fila, indice++)));
        frm.txtApellido.setText((String)(frm.tblAfiliados.getValueAt(fila, indice++)));
        frm.txtDni.setText(String.valueOf(frm.tblAfiliados.getValueAt(fila, indice++)));
        frm.chkActivo.setSelected(Boolean.valueOf((String) frm.tblAfiliados.getValueAt(fila, indice++)));
    }
}