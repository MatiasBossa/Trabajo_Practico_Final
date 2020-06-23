package Controller;

import Model.Data.EspecialidadData;
import Model.Data.HorarioData;
import Model.Data.PrestadorData;
import Model.Entities.Especialidad;
import Model.Entities.Prestador;
import View.frmPrestador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

//@author Nicolas
public class ControlPrestador implements ActionListener{
    private Prestador preE;
    private PrestadorData preD;
    private frmPrestador frm;
    private DefaultTableModel modelo;
    private DefaultTableModel prestador;
    private List<Prestador> listado;
    public ControlPrestador(Prestador preE, PrestadorData preD,frmPrestador frm) {
        this.preE = preE;
        this.preD = preD;
        this.frm = frm;
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnBorrar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.chkActivo.addActionListener(this);
        this.frm.chkActivoBuscar.addActionListener(this);
        this.frm.jComboBoxEspecialidad.addActionListener(this);
        this.frm.jComboBoxBuscar.addActionListener(this);
        this.frm.jTPrestador.addMouseListener(new java.awt.event.MouseAdapter(){});
        //listado=frm.getListado();
        
        listado = new ArrayList<Prestador>();
    }

    public void Iniciar() {
        frm.setTitle("Prestador");
        //frm.setLocationRelativeTo(null);
        //frm.txtId.setVisible(true);//se cambiara a falso ya que el id no debe ser visto por el usuario
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("evento "+e);
        //System.out.println("pretador n 1 "+listado.get(1));
        //System.out.println("tamaño de la tabla "+frm.jTPrestador.getModel().getRowCount());
        /*int j = listado.size();
        for(int i = j;i>0;i--){
            listado.remove(i);
        }*/
        //listado = new ArrayList<Prestador>();
        listado.clear();
        for(int i=0;i<frm.jTablePrestador.getModel().getRowCount();i++){
            System.out.println("pretador N°"+(i+1)+" "+frm.jTablePrestador.getModel().getValueAt(i, 0));
            listado.add((Prestador)frm.jTablePrestador.getModel().getValueAt(i, 0));
        }
        System.out.println("tamaño de listado "+listado.size());
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Guardar">
        if (e.getSource() == frm.btnGuardar) {
            //aca se asegura de no guardar nada si alguno los txtField estan vacios
            if(txtAEntidad() && comprobarDni()){
                preD.guardarPrestador(preE);
                listado.add(preE);
                JOptionPane.showMessageDialog(null, "Prestador guardado.");
                cargarDatosTabla();
                limpiar();
                
            }
        }// </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Modificar">
        if (e.getSource() == frm.btnModificar) {
            if (frm.txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Para modificar un Prestador tiene que seleccionar uno de la lista");
            }
            else if(txtAEntidad()){
                
                preD.modificarPrestador(preE);
                JOptionPane.showMessageDialog(null, "Prestador modificado.");
                for(int i =0;i<listado.size();i++){
                    if(listado.get(i).equals(preE)){
                        System.out.println("Son iguales ");
                        listado.set(i, preE);
                        break;
                    }
                }
                cargarDatosTabla();
                limpiar();
                
                
            }
        }// </editor-fold> 

        
        // <editor-fold defaultstate="collapsed" desc="Boton Borrar">
        if (e.getSource() == frm.btnBorrar) {
            //aca se asegura de no borrar nada si alguno de los txtField estan vacios
            if (!frm.txtId.getText().trim().isEmpty()) {
                HorarioData hd = new HorarioData();
                if (!hd.existePrestador(Integer.parseInt(frm.txtId.getText()))) {
                    int idPrestador = Integer.parseInt(frm.txtId.getText());
                    preD.borrarPrestador(idPrestador);
                    JOptionPane.showMessageDialog(null, "Prestador borrado.");
                    for (int i = 0; i < listado.size(); i++) {
                        if (listado.get(i).getId() == idPrestador) {
                            System.out.println("Son iguales ");
                            listado.remove(i);
                            break;
                        }

                    }
                    cargarDatosTabla();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "no se puede borrar el Prestador por que tiene un horario.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un Prestador de lista para borrar.");
            }
        }// </editor-fold> 

        
        // <editor-fold defaultstate="collapsed" desc="Cambio el checkBox del listado">
        if(e.getSource() == frm.chkActivoBuscar)
            //System.out.println("Evento en el checkBox del listado");
            cargarDatosTabla();
        // </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Cambio el ComboBoxBuscar del listado">
        if(e.getSource() == frm.jComboBoxBuscar){
            //System.out.println("Evento en el comboBox del listado");
            cargarDatosTabla();
        }
        // </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Se clickeo jTPrestador">
        if (e.getSource() == frm.jTPrestador) {
            System.out.println("Evento en el jTPrestador");
            cargarDatosTabla();
        }
        // </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Buscar">
        if (e.getSource() == frm.btnBuscar) {
            DefaultTableModel tabla = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            frm.jTPrestador.setModel(tabla);

            tabla.addColumn("Id");
            tabla.addColumn("Nombre");
            tabla.addColumn("Apellido");
            tabla.addColumn("Dni");
            tabla.addColumn("Especialidad");
            tabla.addColumn("Activo");

            int anchos[] = {0, 100, 100, 80, 120, 100};

            for (int i = 0; i < 6; i++) {
                frm.jTPrestador.getColumnModel().getColumn(i).setWidth(anchos[i]);
                frm.jTPrestador.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
                frm.jTPrestador.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
            }
            List<Prestador> listado = (List<Prestador>) preD.listarPrestador();
            //System.out.println("indice seleccionado " + frm.jComboBoxBuscar.getSelectedIndex());
            //System.out.println("item seleccionado " + frm.jComboBoxBuscar.getItemAt(frm.jComboBoxBuscar.getSelectedIndex()));

            for (int i = 0; i < listado.size(); ++i) {
                Object[] ob = {listado.get(i).getId(),listado.get(i).getNombre(), listado.get(i).getApellido(), listado.get(i).getDni(), listado.get(i).getEspecialidad().getTitulo(), listado.get(i).getActivo()};

                if (frm.chkActivoBuscar.isSelected() == true && frm.jComboBoxBuscar.getSelectedIndex() == 0) {
                    if (listado.get(i).getActivo() == true) {
                        //System.out.println("lista solo si esta activo");
                        tabla.addRow(ob);
                    }
                } else if (frm.jComboBoxBuscar.getSelectedIndex() != 0) {
                    if (String.valueOf(frm.jComboBoxBuscar.getItemAt(frm.jComboBoxBuscar.getSelectedIndex())).equals(listado.get(i).getEspecialidad().getTitulo())) {
                        //System.out.println("lista solo por especialidad");
                        if (frm.chkActivoBuscar.isSelected() == true) {
                            if (listado.get(i).getActivo() == true) {
                                //System.out.println("solo si esta activo");
                                tabla.addRow(ob);
                            }
                        } else {
                            //System.out.println("solo si esta inactivo");
                            tabla.addRow(ob);
                        }
                    }
                } else {
                    tabla.addRow(ob);
                }
                ob = null;
            }
        }// </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Limpiar">
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }// </editor-fold> 
        
        
    }
    
    private void limpiar() {
        frm.txtId.setText(null);
        frm.txtNombre.setText(null);
        frm.txtApellido.setText(null);
        frm.txtDni.setText(null);
        frm.chkActivo.setSelected(false);
        
    }
    
    /**
     * Este metodo pasa al informacion hacia preE desde los textField si ninguno de estos esta vacio o tiene 8 numeros
     * @return 
     */
    private boolean txtAEntidad() {
        System.out.println("Lo q hay en nombre " + frm.txtNombre.getText());
        if (frm.txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ingreso un Nombre");
            return false;
        }
        System.out.println("Lo q hay en apellido " + frm.txtApellido.getText());
        if (frm.txtApellido.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ingreso un Apellido");
            return false;
        }
        System.out.println("Lo q hay en dni " + frm.txtDni.getText());
        if (frm.txtDni.getText().length() != 8) {
            JOptionPane.showMessageDialog(null, "El Dni tiene que tener 8 numeros");
            return false;
        }
        preE.setId(Integer.parseInt(frm.txtId.getText()));
        preE.setNombre(frm.txtNombre.getText());
        preE.setApellido(frm.txtApellido.getText());
        preE.setDni(Integer.parseInt(frm.txtDni.getText()));
        preE.setEspecialidad(frm.jComboBoxEspecialidad.getItemAt(frm.jComboBoxEspecialidad.getSelectedIndex()));
        preE.setActivo(frm.chkActivo.isSelected());
        return true;
    }
    
    private void txtATabla(){//provoca inestabilidad debido a un problema logico
        Object[] fila = new Object[5];
        fila[0]=frm.txtNombre.getText();
        fila[1]=frm.txtApellido.getText();
        fila[2]=frm.txtDni.getText();
        //fila[3]=frm.txtEspecialidad.getText();
        fila[4]=frm.chkActivo.isSelected();
        
        DefaultTableModel tabla = new DefaultTableModel();
        frm.jTPrestador.setModel(tabla);
        
        tabla.addRow(fila);
        
    }
    
    private void cargarDatosTabla() {
        //borrarFilasTabla();
        modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        frm.jTPrestador.setModel(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Dni");
        modelo.addColumn("Especialidad");
        modelo.addColumn("Activo");
        int anchos[] = {0, 100, 100, 80, 120, 100};

        for (int i = 0; i < 6; i++) {
            frm.jTPrestador.getColumnModel().getColumn(i).setWidth(anchos[i]);
            frm.jTPrestador.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
            frm.jTPrestador.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
        }
        
        prestador = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        frm.jTablePrestador.setModel(prestador);
        prestador.addColumn("Prestador");
        frm.jTablePrestador.getColumnModel().getColumn(0).setWidth(0);
        frm.jTablePrestador.getColumnModel().getColumn(0).setMinWidth(0);
        frm.jTablePrestador.getColumnModel().getColumn(0).setMaxWidth(0);
        
        
        //listado = this.preD.listarPrestador();
        
        for (int i = 0; i < listado.size(); ++i) {
            Object[] ob = {listado.get(i).getId(), listado.get(i).getNombre(), listado.get(i).getApellido(), listado.get(i).getDni(), listado.get(i).getEspecialidad().getTitulo(), listado.get(i).getActivo()};
            Object[] ob0 ={listado.get(i)};
            prestador.addRow(ob0);
            if (frm.chkActivoBuscar.isSelected() == true && frm.jComboBoxBuscar.getSelectedIndex() == 0) {
                    if (listado.get(i).getActivo() == true) {
                        //System.out.println("lista solo si esta activo");
                        modelo.addRow(ob);
                    }
                } else if (frm.jComboBoxBuscar.getSelectedIndex() != 0) {
                    if (String.valueOf(frm.jComboBoxBuscar.getItemAt(frm.jComboBoxBuscar.getSelectedIndex())).equals(listado.get(i).getEspecialidad().getTitulo())) {
                        //System.out.println("lista solo por especialidad");
                        if (frm.chkActivoBuscar.isSelected() == true) {
                            if (listado.get(i).getActivo() == true) {
                                //System.out.println("solo si esta activo");
                                modelo.addRow(ob);
                            }
                        } else {
                            //System.out.println("solo si esta inactivo");
                            modelo.addRow(ob);
                        }
                    }
                } else {
                    modelo.addRow(ob);
                }
            ob = null;
            ob0 = null;
        }
        
    }
    
    private boolean comprobarDni(){
        for(int i = 0; i < listado.size(); i++) {
            if (preE.getDni() == listado.get(i).getDni()) {
                JOptionPane.showMessageDialog(null, "ya hay un Prestador con ese Dni.");
                return false;
            }
        }
        return true;
    }
}
