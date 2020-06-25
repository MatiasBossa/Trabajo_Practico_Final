/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data.HorarioData;
import Model.Data.OrdenData;
import Model.Data.PrestadorData;
import Model.Entities.Horario;
import Model.Entities.Prestador;
import View.viewHorario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicolas
 */
public class ControlHorarios implements ActionListener {
    private Horario modE;
    private HorarioData modD;
    private viewHorario frm;
    
    private DefaultTableModel modelo;
    
    private PrestadorData prestadorData;
    private HorarioData horarioData;
    private ArrayList<Prestador> listaPrestadores;
    private ArrayList<Horario> listaHorarios;
    
    private String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};

    public ControlHorarios(Horario modE, HorarioData modD, viewHorario frm) {
        this.modE = modE;
        this.modD = modD;
        this.frm = frm;
        this.frm.cbxPrestador.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnBorrar.addActionListener(this);
        
        this.frm.tblHorarios.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {

                int fila = frm.tblHorarios.getSelectedRow();
                if (fila != -1) {
                    frm.txtIdHorario.setText(frm.tblHorarios.getModel().getValueAt(fila, 0).toString());
                    String hora = " " + frm.tblHorarios.getModel().getValueAt(fila, 3).toString() + ":00";
                    Funciones funciones = new Funciones();
                    frm.jdcFecha.setDate( funciones.StringAFechaHora( frm.tblHorarios.getModel().getValueAt(fila, 1).toString()+ hora )  );

                }

            }
        });        
        
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return false;
            }            
        };
        prestadorData = new PrestadorData();
        listaPrestadores = (ArrayList)prestadorData.listarPrestadores();
        horarioData = new HorarioData();

        cargarPrestadores();
        armarCabezeraTabla();
        cargarDatosTabla();

        Funciones.SNumero(frm.txtDNI);
        Iniciar();
    }
    
    public void Iniciar() {
        frm.setTitle("Horarios");
        Calendar fechaHoy = new GregorianCalendar();
        frm.jdcFecha.setCalendar(fechaHoy);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frm.cbxPrestador) {
            cargarDatosTabla();
        }

        if (e.getSource() == frm.btnGuardar) {
            try {
                // Capturar dia de la semana
                int d = frm.jdcFecha.getCalendar().get(Calendar.DAY_OF_WEEK);
                // Capturar hora
                int h = frm.jdcFecha.getCalendar().get(Calendar.HOUR_OF_DAY);
                int m = frm.jdcFecha.getCalendar().get(Calendar.MINUTE);
                String hhmmss = (h<10 ? "0"+h : ""+h) + ":" + (m<10 ? "0"+m : ""+m+":00");

                modE.setIdHorario( -1 );
                modE.setPrestador( (Prestador)frm.cbxPrestador.getSelectedItem() );                
                Funciones f = new Funciones();
                modE.setFecha(LocalDate.parse( f.getFecha(frm.jdcFecha) )  );
                modE.setHora( LocalTime.parse(hhmmss) );
                
                if ( !modD.horarioDuplicado(modE) ) {
                    horarioData.guardarHorario(modE);
                    cargarDatosTabla();
                    JOptionPane.showMessageDialog(null, "Registro guardado.");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: Registro ya exite.");
                }
            } catch (java.lang.NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Fecha/hora invalida.");
            }

        }
        
        if (e.getSource() == frm.btnBuscar) {
            boolean encontrado = false;
            int dni = Integer.parseInt(frm.txtDNI.getText());
            for (Prestador prestador : listaPrestadores) {
                if (prestador.getDni() == dni) {
                    frm.cbxPrestador.setSelectedItem(prestador);
                    cargarDatosTabla();
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Prestador No encontrado.");
            }
        }


        if (e.getSource() == frm.btnModificar) {
            int fila = frm.tblHorarios.getSelectedRow();
            if (fila != -1) {
                try {
                    modE.setIdHorario( Integer.parseInt(frm.tblHorarios.getModel().getValueAt(fila, 0).toString()) );
                    modE.setPrestador( (Prestador)frm.tblHorarios.getModel().getValueAt(fila, 4) );
                    Funciones f = new Funciones();
                    modE.setFecha(LocalDate.parse( f.getFecha(frm.jdcFecha) )  );
                    // Capturar dia de la semana
                    int d = frm.jdcFecha.getCalendar().get(Calendar.DAY_OF_WEEK);
                    // Capturar hora
                    int h = frm.jdcFecha.getCalendar().get(Calendar.HOUR_OF_DAY);
                    int m = frm.jdcFecha.getCalendar().get(Calendar.MINUTE);
                    String hhmmss = (h<10 ? "0"+h : ""+h) + ":" + (m<10 ? "0"+m : ""+m+":00");
                    modE.setHora( LocalTime.parse(hhmmss) );

                    horarioData.modificarHorario(modE);
                    cargarDatosTabla();
                    JOptionPane.showMessageDialog(null, "Registro Modificado.");
                } catch (java.lang.NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Fecha/hora invalida.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: no ha seleccionado ninguna fila.");
            }
        }
        
        if (e.getSource() == frm.btnBorrar) {
            int fila = frm.tblHorarios.getSelectedRow();
            if (fila != -1) {
                int idHorario = Integer.parseInt(frm.tblHorarios.getModel().getValueAt(fila, 0).toString());
                OrdenData ordenData = new OrdenData();
                if ( !ordenData.existeHorario(idHorario) ) {
                    horarioData.borrarHorario( idHorario );
                    cargarDatosTabla();
                    JOptionPane.showMessageDialog(null, "Registro borrado.");
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: el horario no puede ser borraro porque hay una orden relacionada.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: no ha seleccionado ninguna fila.");
            }
        }

    }
    
    
    private void cargarPrestadores() {
        for (Prestador item:listaPrestadores) {
            frm.cbxPrestador.addItem(item);
        }
    }

    private void armarCabezeraTabla() {
        ArrayList<Object> columnas = new ArrayList<Object>();
        columnas.add("ID");
        columnas.add("FECHA");
        columnas.add("DIA");
        columnas.add("HORARIOATENCION");
        columnas.add("PRESTADOR");
        columnas.add("ASIGNADO");

        for(Object it:columnas) {
            modelo.addColumn(it);
        }
        frm.tblHorarios.setModel(modelo);
        // Para agregar JCheckBox a la tabla
        // https://www.youtube.com/watch?v=h7PvI9GZl90
        frm.tblHorarios.getColumnModel().getColumn(5).setCellEditor(new Clase_CellEditor());
        frm.tblHorarios.getColumnModel().getColumn(5).setCellRenderer(new Clase_CellRender());

        int anchos[] = {0, 100, 100, 150, 0, 100};
        for (int i = 0; i < 6; i++) {
            frm.tblHorarios.getColumnModel().getColumn(i).setWidth(anchos[i]);
            frm.tblHorarios.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
            frm.tblHorarios.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
        }

    }

    private void borrarFilasTabla() {
        int f = modelo.getRowCount() - 1;
        for (int i=f; i>=0; i--) {
            modelo.removeRow(i);
        }
    }

    private void cargarDatosTabla() {
        borrarFilasTabla();
        
        Prestador prestador = (Prestador)frm.cbxPrestador.getSelectedItem();
        listaHorarios = (ArrayList)horarioData.listarHorarios(prestador.getId());
        for(Horario h : listaHorarios) {
            OrdenData ordenData = new OrdenData();
            //ordenData.existeHorario( h.getIdHorario() );
            modelo.addRow(new Object[]{h.getIdHorario(), h.getFecha(), h.getDia(), h.getHora(), h.getPrestador(), ordenData.existeHorario( h.getIdHorario() )});
        }
    }
    
}
