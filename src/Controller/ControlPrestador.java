package Controller;

import Model.Data.EspecialidadData;
import Model.Data.PrestadorData;
import Model.Entities.Especialidad;
import Model.Entities.Prestador;
import View.frmPrestador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//@author Nicolas
public class ControlPrestador implements ActionListener{
    private Prestador preE;
    private PrestadorData preD;
    private frmPrestador frm;

    public ControlPrestador(Prestador preE, PrestadorData preD,frmPrestador frm) {
        this.preE = preE;
        this.preD = preD;
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
            //aca se asegura de no guardar nada si alguno los txtField estan vacios
            System.out.println("Lo q hay " + txtAEntidadC());
            switch (txtAEntidadC()) {
                //case 0:
                //JOptionPane.showMessageDialog(null, "Ingrese los datos para guardar un Prestador");
                //break;
                case 1:
                    JOptionPane.showMessageDialog(null, "No se ingreso un Nombre");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "No se ingreso un Apellido");
                    break;
                case 3:;
                    JOptionPane.showMessageDialog(null, "El Dni tiene que tener 8 numeros");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "No se ingreso una Especialidad");
                    break;
                default:
                    if (!frm.txtNombre.getText().trim().isEmpty() && !frm.txtApellido.getText().trim().isEmpty() && !frm.txtDni.getText().trim().isEmpty() && !frm.txtEspecialidad.getText().trim().isEmpty()) {

                        preD.guardarPrestador(preE);
                        JOptionPane.showMessageDialog(null, "Prestador guardado.");
                        limpiar();
                        break;
                    }
            }
            /*
            if (!frm.txtId.getText().trim().isEmpty() && !frm.txtNombre.getText().trim().isEmpty() && !frm.txtApellido.getText().trim().isEmpty() && !frm.txtDni.getText().trim().isEmpty() && !frm.txtEspecialidad.getText().trim().isEmpty()) {
                preD.guardarPrestador(preE);
                JOptionPane.showMessageDialog(null, "Prestador guardado.");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Faltan datos para poder guardar.");
            }*/
        }// </editor-fold> 
        
        
        // <editor-fold defaultstate="collapsed" desc="Boton Modificar">
        if (e.getSource() == frm.btnModificar) {
            switch (txtAEntidadU()) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Para modificar un Prestador tiene que seleccionar uno de la lista");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "No se ingreso un Nombre");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "No se ingreso un Apellido");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "El Dni tiene que tener 8 numeros");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "No se ingreso una Especialidad");
                    break;
                default:
                    preD.modificarPrestador(preE);
                    JOptionPane.showMessageDialog(null, "Prestador guardado.");
                    limpiar();
                    break;
            }
        }// </editor-fold> 

        
        // <editor-fold defaultstate="collapsed" desc="Boton Borrar">
        if (e.getSource() == frm.btnBorrar) {
            //aca se asegura de no borrar nada si alguno de los txtField estan vacios
            if (!frm.txtId.getText().trim().isEmpty() && !frm.txtNombre.getText().trim().isEmpty() && !frm.txtApellido.getText().trim().isEmpty() && !frm.txtDni.getText().trim().isEmpty() && !frm.txtEspecialidad.getText().trim().isEmpty()) {
                int idPrestador = Integer.parseInt(frm.txtId.getText());
                preD.borrarPrestador(idPrestador);
                JOptionPane.showMessageDialog(null, "Prestador borrado.");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un Prestador de lista para borrar.");
            }
        }// </editor-fold> 

        
        // <editor-fold defaultstate="collapsed" desc="Boton Anular">
        if (e.getSource() == frm.btnAnular) {
            int idPrestador = Integer.parseInt(frm.txtId.getText());
            preD.desactivarPrestador(idPrestador);
            JOptionPane.showMessageDialog(null, "Prestador anulado.");
            limpiar();
        }// </editor-fold> 
        
        
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

            int anchos[] = {0, 95, 95, 70, 120, 70};

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
                /*
                if (frm.chkActivoBuscar.isSelected() == false && frm.jComboBoxBuscar.getSelectedIndex() != 0) {
                    if (String.valueOf(frm.jComboBoxBuscar.getItemAt(frm.jComboBoxBuscar.getSelectedIndex())) == listado.get(i).getEspecialidad().getTitulo()) {
                        tabla.addRow(ob);
                    }
                }
                if (frm.chkActivoBuscar.isSelected() == true && frm.jComboBoxBuscar.getSelectedIndex() == 0) {
                    tabla.addRow(ob);
                }
                if (frm.chkActivoBuscar.isSelected() == true && frm.jComboBoxBuscar.getSelectedIndex() != 0) {
                    if (String.valueOf(frm.jComboBoxBuscar.getItemAt(frm.jComboBoxBuscar.getSelectedIndex())) == listado.get(i).getEspecialidad().getTitulo()) {
                        tabla.addRow(ob);
                    }
                }
                if (frm.chkActivoBuscar.isSelected() == false && frm.jComboBoxBuscar.getSelectedIndex() != 0) {
                    if (String.valueOf(frm.jComboBoxBuscar.getItemAt(frm.jComboBoxBuscar.getSelectedIndex())) == listado.get(i).getEspecialidad().getTitulo()) {
                        System.out.println("item seleccionado " + frm.jComboBoxBuscar.getItemAt(frm.jComboBoxBuscar.getSelectedIndex()));
                        System.out.println("especialidad "+listado.get(i).getEspecialidad().getTitulo());
                        tabla.addRow(ob);
                    }
                }*/
                
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
        frm.txtEspecialidad.setText(null);
    }
    
    private int txtAEntidadC() {
        //aca se asegura de que si el txtField esta vacio no setee nada
        System.out.println("Lo q hay en id " + frm.txtId.getText());
        if (frm.txtId.getText().trim().isEmpty()) {
            //return 0;
        }

        System.out.println("Lo q hay en nombre " + frm.txtNombre.getText());
        if (frm.txtNombre.getText().trim().isEmpty()) {
            return 1;
        }

        System.out.println("Lo q hay en apellido " + frm.txtApellido.getText());
        if (frm.txtApellido.getText().trim().isEmpty()) {
            return 2;
        }

        System.out.println("Lo q hay en dni " + frm.txtDni.getText());
        if (frm.txtDni.getText().length() !=8) {
            return 3;
        }

        preE.setActivo(frm.chkActivo.isSelected());

        System.out.println("Lo q hay en especialidad " + frm.txtEspecialidad.getText());
        if (frm.txtEspecialidad.getText().trim().isEmpty()) {
            return 4;
        }
            //preE.setId(Integer.parseInt(frm.txtId.getText()));
            preE.setNombre(frm.txtNombre.getText());
            preE.setApellido(frm.txtApellido.getText());
            preE.setDni(Integer.parseInt(frm.txtDni.getText()));
            preE.setEspecialidad(new Especialidad(frm.txtEspecialidad.getText()));
        return 10;
    }
    
    private int txtAEntidadU() {
        //aca se asegura de que si el txtField esta vacio no setee nada
        System.out.println("Lo q hay en id " + frm.txtId.getText());
        if (frm.txtId.getText().trim().isEmpty()) {
            return 0;
        }

        System.out.println("Lo q hay en nombre " + frm.txtNombre.getText());
        if (frm.txtNombre.getText().trim().isEmpty()) {
            return 1;
        }

        System.out.println("Lo q hay en apellido " + frm.txtApellido.getText());
        if (frm.txtApellido.getText().trim().isEmpty()) {
            return 2;
        }

        System.out.println("Lo q hay en dni " + frm.txtDni.getText());
        if (frm.txtDni.getText().length() !=8) {
            return 3;
        }

        preE.setActivo(frm.chkActivo.isSelected());

        System.out.println("Lo q hay en especialidad " + frm.txtEspecialidad.getText());
        if (frm.txtEspecialidad.getText().trim().isEmpty()) {
            return 4;
        }
            preE.setId(Integer.parseInt(frm.txtId.getText()));
            preE.setNombre(frm.txtNombre.getText());
            preE.setApellido(frm.txtApellido.getText());
            preE.setDni(Integer.parseInt(frm.txtDni.getText()));
            preE.setEspecialidad(new Especialidad(frm.txtEspecialidad.getText()));
        return 10;
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
