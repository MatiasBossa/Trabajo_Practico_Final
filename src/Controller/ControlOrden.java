/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data.*;
import Model.Entities.*;
import View.frmOrden;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class ControlOrden implements ActionListener {

    private Orden modE; // modelo de la clase Entidad
    private OrdenData modD; // modelo de la clase Data
    private frmOrden frm;

    private DefaultTableModel modelo;

    private AfiliadoData afiliadoData;
    private PrestadorData prestadorData;
    private HorarioData horarioData;
    private OrdenData ordenData;
    private ArrayList<Afiliado> listaAfiliados;
    private ArrayList<Prestador> listaPrestadores;
    private ArrayList<Horario> listaHorarios;
    private ArrayList<Orden> listaOrdenes;
    

    public ControlOrden(Orden modE, OrdenData modD, frmOrden frm) {
        this.modE = modE;
        this.modD = modD;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnBorrar.addActionListener(this);
        this.frm.btnAnular.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnSalir.addActionListener(this);

        this.frm.cbxAfiliado.addActionListener(this);
        this.frm.cbxPrestador.addActionListener(this);
        this.frm.tblOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {});

        // Para hacer las celdas NO editables
        // https://www.youtube.com/watch?v=zcDAl0lSGhw
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                //if (columnas==1) return true; // Este indica que desde la columna 1 hacia la izquierda No seran editables
                //else return false;
                return false;
            }            
        };
        afiliadoData = new AfiliadoData();
        listaAfiliados = (ArrayList)afiliadoData.listarAfiliados();
        prestadorData = new PrestadorData();
        listaPrestadores = (ArrayList)prestadorData.listarPrestadores();
        horarioData = new HorarioData();
        //listaHorarios = (ArrayList)horarioData.listarHorarios( 0 );
        ordenData = new OrdenData();
        //listaOrdenes = (ArrayList)ordenData.listarOrdenes( 0 );
        
        cargarAfiliados();
        cargarPrestadores();
        //cargarHorarios();
        armarCabezeraTabla();
        cargarDatosTabla();

        Funciones.SNumero(frm.txtDNI);
        Funciones.SNumero(frm.txtTotalPagar);
        iniciar();

    }

    
    
    
    
    /**
     * Coloca el titulo del formario y setea la ubicacion
     */
    public void iniciar() {
        frm.setTitle("Orden");
        frm.txtDNI.requestFocus();
        //frm.setLocationRelativeTo(null);
        
    }

    /**
     * Sobreescribimos el métod que escucha los click a los botones
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frm.cbxAfiliado) {
            limpiar();
            cargarDatosTabla();
        }
        if (e.getSource() == frm.cbxPrestador) {
            cargarHorarios();
        }

        if (e.getSource() == frm.btnGuardar) {
            if (frm.btnGuardar.getText() == "Nuevo") {
                limpiar();
                frm.cbxFormaPago.setEnabled(true);
                frm.txtTotalPagar.setEnabled(true);
                frm.cbxPrestador.setEnabled(true);
                frm.cbxHorario.setEnabled(true);
                frm.btnGuardar.setText("Guardar");
                frm.btnModificar.setEnabled(false);
                frm.btnBorrar.setEnabled(false);
                frm.btnAnular.setEnabled(false);
                frm.txtTotalPagar.requestFocus();
            } else {
                if ( validarCampos() ) {
                    frm.txtIdOrden.setText("-1");
                    txt_A_entidad();
                    int idOrden = modD.guardarOrden(modE);
                    JOptionPane.showMessageDialog(null, "Registro guardado.");
                    limpiar(); // Despues de guardar hay que limpiar los campos del forumarlio.
                }
            }
        }

        if (e.getSource() == frm.btnModificar) {
            if ( validarCampos() ) {
                txt_A_entidad();

                modD.modificarOrden(modE);
                cargarDatosTabla();

                JOptionPane.showMessageDialog(null, "Registro modificado.");
                limpiar(); // Despues de modificar hay que limpiar los campos del forumarlio.
            }
        }

        if (e.getSource() == frm.btnBorrar) {
            int idOrden = Integer.parseInt(frm.txtIdOrden.getText());

            modD.borrarOrden(idOrden);
            cargarDatosTabla();
            
            JOptionPane.showMessageDialog(null, "Registro borrado.");
            limpiar(); // Despues de borrar hay que limpiar los campos del forumarlio.
        }

        if (e.getSource() == frm.btnAnular ) {
            if ( !frm.chkAnulado.isSelected() ) {
                int idOrden = Integer.parseInt(frm.txtIdOrden.getText());

                modD.anularOrden(idOrden);
                cargarDatosTabla();

                JOptionPane.showMessageDialog(null, "Registro anulado.");
                limpiar(); // Despues de anular hay que limpiar los campos del forumarlio.
            } else {
                JOptionPane.showMessageDialog(null, "La orden ya está anulada.");
            }
        }

        if (e.getSource() == frm.btnBuscar) {
            boolean encontrado = false;
            int dni = Integer.parseInt(frm.txtDNI.getText());
            for (Afiliado afiliado : listaAfiliados) {
                if (afiliado.getDni() == dni) {
                    frm.cbxAfiliado.setSelectedItem(afiliado);
                    cargarDatosTabla();
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Afiliado No encontrado.");
            }
            limpiar();        
        }

        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }
    
        if (e.getSource() == frm.btnSalir) {
            frm.dispose();
        }
    }

    /**
     * Limpia los campos del formulario
     */
    private void limpiar() {
        frm.txtDNI.setText(null);
        frm.txtIdOrden.setText("-1");
        frm.txtTotalPagar.setText(null);
                
        frm.cbxFormaPago.setEnabled(false);
        frm.txtTotalPagar.setEnabled(false);
        frm.cbxPrestador.setEnabled(false);
        frm.cbxHorario.setEnabled(false);
        
        frm.btnGuardar.setText("Nuevo");
        frm.btnModificar.setEnabled(false);
        frm.btnBorrar.setEnabled(false);
        frm.btnAnular.setEnabled(false);
    }

    /**
     * Pasa los datos de los campos del formulario a la Entidad
     */
    private void txt_A_entidad() {
        modE.setIdOrden(Integer.parseInt(frm.txtIdOrden.getText()));
        Funciones funciones = new Funciones();
        modE.setFechaEmision( Date.valueOf(funciones.getFecha(frm.jdcFechaEmision)) );
        modE.setAfiliado((Afiliado)frm.cbxAfiliado.getSelectedItem());
        modE.setFormaPago(frm.cbxFormaPago.getSelectedItem().toString());
        modE.setTotalPagar(Double.parseDouble(frm.txtTotalPagar.getText()));
        modE.setHorario((Horario)frm.cbxHorario.getSelectedItem());
        modE.setAnulado(frm.chkAnulado.isSelected());
    }

    private void cargarAfiliados() {
        for (Afiliado item:listaAfiliados) {
            frm.cbxAfiliado.addItem(item);
        }
    }
    private void cargarPrestadores() {
        for (Prestador item:listaPrestadores) {
            frm.cbxPrestador.addItem(item);
        }
        cargarHorarios();
    }
    private void cargarHorarios() {
        frm.cbxHorario.removeAllItems();        
        listaHorarios = (ArrayList)horarioData.listarHorarios( ((Prestador)frm.cbxPrestador.getSelectedItem()).getId() );
        for (Horario item : listaHorarios) {
            frm.cbxHorario.addItem(item);
        }
    }

    private void armarCabezeraTabla() {
        ArrayList<Object> columnas = new ArrayList<Object>();
        columnas.add("_");
        columnas.add("FECHAEMISION");
        columnas.add("PRESTADOR");
        columnas.add("HORARIO");
        columnas.add("FORMAPAGO");
        columnas.add("TOTALPAGAR");
        columnas.add("ANULADO");
        for(Object it:columnas) {
            modelo.addColumn(it);
        }
        frm.tblOrdenes.setModel(modelo);
        // Para agregar JCheckBox a la tabla
        // https://www.youtube.com/watch?v=h7PvI9GZl90
        frm.tblOrdenes.getColumnModel().getColumn(6).setCellEditor(new Clase_CellEditor());
        frm.tblOrdenes.getColumnModel().getColumn(6).setCellRenderer(new Clase_CellRender());
        
        frm.tblOrdenes.getColumnModel().getColumn(0).setPreferredWidth(5);
        frm.tblOrdenes.getColumnModel().getColumn(6).setPreferredWidth(30);
    }
    
    
    private void borrarFilasTabla() {
        int f = modelo.getRowCount() - 1;
        for (int i=f; i>=0; i--) {
            modelo.removeRow(i);
        }
    }

    private void cargarDatosTabla() {
        borrarFilasTabla();
        
        Afiliado afiliado = (Afiliado)frm.cbxAfiliado.getSelectedItem();
        listaOrdenes = (ArrayList)ordenData.listarOrdenes(afiliado.getId());
        for(Orden ord:listaOrdenes) {
            modelo.addRow(new Object[]{ord.getIdOrden(), ord.getFechaEmision(), ord.getHorario().getPrestador(), ord.getHorario(), ord.getFormaPago(), ord.getTotalPagar(), ord.getAnulado()});
        }
    }

    public boolean validarCampos() {
        boolean valido = true;

        try {
            if ( frm.txtTotalPagar.getText() == null || frm.txtTotalPagar.getText() == "" ) {
                JOptionPane.showMessageDialog(null, "ERROR. Debe ingresar un importe en el campo Total a pagar.");
                valido = false;
            } else if ( Float.parseFloat( frm.txtTotalPagar.getText() ) < 0 ) {
                JOptionPane.showMessageDialog(null, "ERROR. El importe ingresado en el campo Total a pagar es negativo.");
                valido = false;
            }
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ERROR. El campo importe NO es válido.");
        } 
        
        return valido;
    }
}