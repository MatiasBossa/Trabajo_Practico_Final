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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//@author Nicolas
public class ControlPrestador implements ActionListener, KeyListener, MouseListener{
    private Prestador preE;
    private PrestadorData preD;
    private frmPrestador frm;
    private DefaultTableModel modelo;
    private ArrayList<Prestador> listado;
    public ControlPrestador(Prestador preE, PrestadorData preD,frmPrestador frm) {
        this.preE = preE;
        this.preD = preD;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnBorrar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.chkActivo.addActionListener(this);
        this.frm.chkActivoBuscar.addActionListener(this);
        //creo q los actionlistener para los combobox no son necesarios
        this.frm.jComboBoxEspecialidad.addActionListener(this);
        this.frm.jComboBoxBuscar.addActionListener(this);
        this.frm.jTPrestador.addMouseListener(this);
        
        this.frm.txtDni.addKeyListener( this);
        this.frm.txtApellido.addKeyListener( this);
        this.frm.txtNombre.addKeyListener( this);
        
        bajarDatos();
        cargarDatosTabla();
        cargarDatosComboBox();
        Iniciar();
    }

    private void Iniciar() {
        this.frm.setTitle("Formulario de prestadores");
        //-+
        
        
        System.out.println("Controller.ControlPrestador.Iniciar()");
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        /*for(int i=0;i<listado.size();i++){
            System.out.println("pretador N°"+(i+1)+" "+listado.get(i).getId()+" "+listado.get(i)+" "+listado.get(i).getDni()+" "+listado.get(i).getEspecialidad());
            //listado.add((Prestador)frm.jTablePrestador.getModel().getValueAt(i, 0));
        }*/
        
        // <editor-fold defaultstate="collapsed" desc="Boton Guardar">
        if (e.getSource() == this.frm.btnGuardar) {
            //aca se asegura de no guardar nada si alguno los txtField estan vacios
            if(txtAEntidad() /*&& comprobarDni()*/){
                this.preE.setId(-1);
                if(comprobarDni()) {
                    this.preD.guardarPrestador(this.preE);//guarda el Prestador en la base de datos
                    this.listado.add(this.preE);//guarda el Prestador en el ArrayList
                    JOptionPane.showMessageDialog(null, "Prestador guardado.");
                    cargarDatosTabla();
                    limpiar();
                }
            }
        }// </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Modificar">
        if (e.getSource() == this.frm.btnModificar) {
            if (this.frm.txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Para modificar un Prestador tiene que seleccionar uno de la lista");
            }
            else if(txtAEntidad()&& comprobarDni()){
                this.preD.modificarPrestador(this.preE);//modifica el Prestador en la base de datos
                for(int i =0;i<this.listado.size();i++){
                    if(this.listado.get(i).equals(this.preE)){
                        System.out.println("Son iguales ");
                        this.listado.set(i, this.preE);//modifica el Prestador en el ArrayList
                        break;
                    }
                }
                JOptionPane.showMessageDialog(null, "Prestador modificado.");
                cargarDatosTabla();
                limpiar();
            }
        }// </editor-fold> 

        
        // <editor-fold defaultstate="collapsed" desc="Boton Borrar">
        if (e.getSource() == this.frm.btnBorrar) {
            //aca se asegura de no borrar nada si alguno de los txtField estan vacios
            if (!this.frm.txtId.getText().trim().isEmpty()) {
                HorarioData hd = new HorarioData();
                if (!hd.existePrestador(Integer.parseInt(this.frm.txtId.getText()))) {
                    int idPrestador = Integer.parseInt(this.frm.txtId.getText());
                    this.preD.borrarPrestador(idPrestador);//borra el Prestador en la base de datos
                    for (int i = 0; i < this.listado.size(); i++) {
                        if (this.listado.get(i).getId() == idPrestador) {
                            System.out.println("Son iguales para borrar");
                            this.listado.remove(i);//borra el Prestador en el ArrayList
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Prestador borrado.");
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
        if(e.getSource() == this.frm.chkActivoBuscar)
            //System.out.println("Evento en el checkBox del listado");
            cargarDatosTabla();
        // </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Cambio el ComboBoxBuscar del listado">
        if(e.getSource() == this.frm.jComboBoxBuscar){
            //System.out.println("Evento en el comboBox del listado");
            cargarDatosTabla();
        }
        // </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Se clickeo jTPrestador">
        if (e.getSource() == this.frm.jTPrestador) {
            System.out.println("Evento en el jTPrestador");
            cargarDatosTabla();
        }
        // </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Limpiar">
        if (e.getSource() == this.frm.btnLimpiar) {
            limpiar();
            cargarDatosTabla();
        }// </editor-fold> 
        
        
    }
    
    @Override
    public void keyTyped(KeyEvent k) {
        char caracter = k.getKeyChar();
        if (k.getSource() == this.frm.txtNombre || k.getSource() == this.frm.txtApellido) {
            if (((caracter < 'A') || (caracter > 'Z')) && ((caracter < 'a') || (caracter > 'z'))) {
                k.consume();
            }
            //System.out.println("tecla presionada para nombre o apellido");
        }
        if (k.getSource() == this.frm.txtDni) {
            if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
                k.consume();  // ignorar el evento de teclado
            }
            //System.out.println("tecla presionada para dni");
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent m) {
        int fila = frm.jTPrestador.getSelectedRow();

        frm.txtId.setText(String.valueOf(frm.jTPrestador.getValueAt(fila, 0)));
        frm.txtNombre.setText((String) frm.jTPrestador.getValueAt(fila, 1));
        frm.txtApellido.setText((String) frm.jTPrestador.getValueAt(fila, 2));
        frm.txtDni.setText(String.valueOf(frm.jTPrestador.getValueAt(fila, 3)));

        for (int i = 0; i < +frm.jComboBoxEspecialidad.getModel().getSize(); i++) {
            Especialidad esp = new Especialidad(frm.jComboBoxEspecialidad.getModel().getElementAt(i).getIdEspecialidad(), frm.jComboBoxEspecialidad.getModel().getElementAt(i).getTitulo());
            //System.out.println("contenido del ComboBoxEspecialidad " + jComboBoxEspecialidad.getModel().getElementAt(i));
            if (esp.getTitulo().equals(frm.jTPrestador.getValueAt(fila, 4))) {
                //System.out.println("la especialidad en la tabla y en el comboc son iguales");
                frm.jComboBoxEspecialidad.setSelectedIndex(i);
                break;
            }
        }

        frm.chkActivo.setSelected((boolean) frm.jTPrestador.getValueAt(fila, 5));
    }
    
    
    private void limpiar() {
        frm.txtId.setText(null);
        frm.txtNombre.setText(null);
        frm.txtApellido.setText(null);
        frm.txtDni.setText(null);
        frm.chkActivo.setSelected(false);
        
    }
    
    /**
     * Este metodo guarda informacion en preE desde los textField si ninguno de estos esta vacio o tiene 8 numeros
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc="txtAEntidad()">
    private boolean txtAEntidad() {
        System.out.println("Lo q hay en nombre " + this.frm.txtNombre.getText());
        if (this.frm.txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ingreso un Nombre");
            return false;
        }
        System.out.println("Lo q hay en apellido " + this.frm.txtApellido.getText());
        if (this.frm.txtApellido.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se ingreso un Apellido");
            return false;
        }
        System.out.println("Lo q hay en dni " + this.frm.txtDni.getText());
        if (this.frm.txtDni.getText().length() != 8) {
            JOptionPane.showMessageDialog(null, "El Dni tiene que tener 8 numeros");
            return false;
        }
        this.preE.setId(Integer.parseInt(this.frm.txtId.getText()));
        this.preE.setNombre(this.frm.txtNombre.getText());
        this.preE.setApellido(this.frm.txtApellido.getText());
        this.preE.setDni(Integer.parseInt(frm.txtDni.getText()));
        this.preE.setEspecialidad(this.frm.jComboBoxEspecialidad.getItemAt(this.frm.jComboBoxEspecialidad.getSelectedIndex()));
        this.preE.setActivo(this.frm.chkActivo.isSelected());
        return true;
    }// </editor-fold>
 
    
    // <editor-fold defaultstate="collapsed" desc="Setea la JTable">
    private void seteoJTable(){
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
        int anchos[] = {0, 95, 95, 80, 130, 100};
        
        for (int i = 0; i < 6; i++) {
            this.frm.jTPrestador.getColumnModel().getColumn(i).setWidth(anchos[i]);
            this.frm.jTPrestador.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
            this.frm.jTPrestador.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
        }
    }// </editor-fold> 
    
    /**
     * Este metodo baja los datos una sola vez desde la base de datos
     */
    // <editor-fold defaultstate="collapsed" desc="bajarDatos()">
    private void bajarDatos(){
        System.out.println("datos bajados");
        this.listado = (ArrayList<Prestador>) this.preD.listarPrestador();
    }// </editor-fold> 
    
    
    // <editor-fold defaultstate="collapsed" desc="Carga los datos en la JTable">
    private void cargarDatosTabla() {
        seteoJTable();
        for (int i = 0; i < listado.size(); ++i) {
            Object[] ob = {listado.get(i).getId(), listado.get(i).getNombre(), listado.get(i).getApellido(), listado.get(i).getDni(), listado.get(i).getEspecialidad().getTitulo(), listado.get(i).getActivo()};
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
        }
        
    }// </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="comprueba si el Dni ya se guardo anteriormente">
    private boolean comprobarDni(){
        for(int i = 0; i < listado.size(); i++) {
            if (preE.getDni() == listado.get(i).getDni()&&!preE.equals(listado.get(i))) {
                //if(!preE.equals(listado.get(i))) {
                    JOptionPane.showMessageDialog(null, "ya hay un Prestador con ese Dni.");
                    return false;
                //}
            }
        }
        return true;
    }// </editor-fold> 
    
    
    // <editor-fold defaultstate="collapsed" desc="Carga los datos en el ComboBox">
    private void cargarDatosComboBox(){
        System.out.println("se bajaron los datos a los combobox");
        frm.jComboBoxBuscar.addItem(new Especialidad("Todos"));
        //jComboBoxBuscar.
        EspecialidadData espD = new EspecialidadData();
        ArrayList<Especialidad> listado = espD.listarEspecialidades();
        for (Especialidad espE: listado) {
            frm.jComboBoxBuscar.addItem(espE);
            frm.jComboBoxEspecialidad.addItem(espE);
        }
    }// </editor-fold> 

    
    // <editor-fold defaultstate="collapsed" desc="Metodos de evento no usados">
    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // </editor-fold>
    
    
}
