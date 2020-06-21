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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class ControlOrden implements ActionListener, ItemListener {

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

        this.frm.cbxAfiliado.addActionListener(this);
        this.frm.cbxPrestador.addActionListener(this);
        this.frm.tblOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {});


        modelo = new DefaultTableModel();
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

    }

    
    
    
    
    /**
     * Coloca el titulo del formario y setea la ubicacion
     */
    public void iniciar() {
        frm.setTitle("Orden");
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
            frm.txtIdOrden.setText("-1");
            txt_A_entidad();

            int idOrden = modD.guardarOrden(modE);

            JOptionPane.showMessageDialog(null, "Registro guardado. idOrden = " + idOrden);
            limpiar(); // Despues de guardar hay que limpiar los campos del forumarlio.
        }

        if (e.getSource() == frm.btnModificar) {
            txt_A_entidad();

            modD.modificarOrden(modE);
            cargarDatosTabla();

            JOptionPane.showMessageDialog(null, "Registro modificado.");
            limpiar(); // Despues de modificar hay que limpiar los campos del forumarlio.
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
            int idOrden = Integer.parseInt(frm.txtIdOrden.getText());

            modE = modD.buscarOrden(idOrden);

            if (modE != null) {
                frm.txtIdOrden.setText(String.valueOf(modE.getIdOrden()));
                //frm.jdcFechaEmision.setText(modE.getFechaEmision().toString());
                //frm.txtFormaPago.setText(modE.getFormaPago());
                frm.txtTotalPagar.setText(String.valueOf(modE.getTotalPagar()));                
                //frm.txtDia.setText(modE.getHorario().getDia());
                //frm.txtHorarioAtencion.setText(modE.getHorario().getHorarioAtencion().toString());
                frm.cbxPrestador.setSelectedItem(modE.getHorario().getPrestador());
                frm.chkAnulado.setSelected(modE.getAnulado());
            } else {
                JOptionPane.showMessageDialog(null, "Registro No encontrado.");
                limpiar(); // Despues de buscar hay que limpiar los campos del forumarlio.
            }
        }

        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }
    }

    /**
     * Limpia los campos del formulario
     */
    private void limpiar() {
        frm.txtDNI.setText(null);
        frm.txtIdOrden.setText("-1");
        Funciones funciones = new Funciones();
        //frm.jdcFechaEmision.setDate(null);
        //frm.cbxFormaPago.setSelectedItem(null);
        frm.txtTotalPagar.setText(null);
        //frm.cbxPrestador.setSelectedItem(null);
        //frm.cbxHorario.setSelectedItem(null);

        frm.chkAnulado.setSelected(false);
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
        columnas.add("ID");
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

    @Override
    public void itemStateChanged(ItemEvent ie) {
        
        if (ie.getSource() == frm.cbxAfiliado) {
            JOptionPane.showMessageDialog(null, "Hiazo clic en el COMBOBOX.");
        }        
        
        if (ie.getSource() == frm.tblOrdenes) {
            JOptionPane.showMessageDialog(null, "Hiazo clic en la tabla.");
        }
    }

}