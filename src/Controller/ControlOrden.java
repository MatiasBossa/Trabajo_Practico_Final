/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data.*;
import Model.Entities.*;
import View.frmABMorden;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class ControlOrden implements ActionListener {

    private Orden modE; // modelo de la clase Entidad
    private OrdenData modD; // modelo de la clase Data
    private frmABMorden frm;
    
  

    public ControlOrden(Orden modE, OrdenData modD, frmABMorden frm) {
        this.modE = modE;
        this.modD = modD;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnBorrar.addActionListener(this);
        this.frm.btnAnular.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
    }

    
    
    
    
    /**
     * Coloca el titulo del formario y setea la ubicacion
     */
    public void iniciar() {
        frm.setTitle("Orden");
        frm.setLocationRelativeTo(null);
    }

    /**
     * Sobreescribimos el métod que escucha los click a los botones
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm.btnGuardar) {
            txt_A_entidad();

            int idOrden = modD.guardarOrden(modE);

            JOptionPane.showMessageDialog(null, "Registro guardado. idOrden = " + idOrden);
            limpiar(); // Despues de guardar hay que limpiar los campos del forumarlio.
        }

        if (e.getSource() == frm.btnModificar) {
            txt_A_entidad();

            modD.modificarOrden(modE);

            JOptionPane.showMessageDialog(null, "Registro modificado.");
            limpiar(); // Despues de modificar hay que limpiar los campos del forumarlio.
        }

        if (e.getSource() == frm.btnBorrar) {
            int idOrden = Integer.parseInt(frm.txtIdOrden.getText());

            modD.borrarOrden(idOrden);

            JOptionPane.showMessageDialog(null, "Registro borrado.");
            limpiar(); // Despues de borrar hay que limpiar los campos del forumarlio.
        }

        if (e.getSource() == frm.btnAnular) {
            int idOrden = Integer.parseInt(frm.txtIdOrden.getText());

            modD.anularOrden(idOrden);

            JOptionPane.showMessageDialog(null, "Registro anulado.");
            limpiar(); // Despues de anular hay que limpiar los campos del forumarlio.
        }

        if (e.getSource() == frm.btnBuscar) {
            int idOrden = Integer.parseInt(frm.txtIdOrden.getText());

            modE = modD.buscarOrden(idOrden);

            if (modE != null) {
                frm.txtIdOrden.setText(String.valueOf(modE.getIdOrden()));
                frm.txtFechaEmision.setText(modE.getFechaEmision().toString());
                frm.txtNombreAfiliado.setText("falta metodo!!!");
                frm.txtApellidoAfiliado.setText("falta metodo!!!");
                frm.txtFormaPago.setText(modE.getFormaPago());
                frm.txtTotalPagar.setText(String.valueOf(modE.getTotalPagar()));
                frm.txtDia.setText(modE.getHorario().getDia());
                frm.txtHorarioAtencion.setText(modE.getHorario().getHorarioAtencion().toString());
                frm.txtNombrePrestador.setText(modE.getHorario().getPrestador().getNombre());
                frm.txtApellidoPrestador.setText(modE.getHorario().getPrestador().getApellido());
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
        frm.txtIdOrden.setText(null);
        frm.txtFechaEmision.setText(null);
        frm.txtNombreAfiliado.setText(null);
        frm.txtApellidoAfiliado.setText(null);
        frm.txtFormaPago.setText(null);
        frm.txtTotalPagar.setText(null);
        frm.txtDia.setText(null);
        frm.txtHorarioAtencion.setText(null);
        frm.txtNombrePrestador.setText(null);
        frm.txtApellidoPrestador.setText(null);
        frm.chkAnulado.setSelected(false);
    }

    /**
     * Pasa los datos de los campos del formulario a la Entidad
     */
    private void txt_A_entidad() {
        modE.setIdOrden(Integer.parseInt(frm.txtIdOrden.getText()));
        modE.setFechaEmision(Date.valueOf(frm.txtFechaEmision.getText()));
        //modE.setAfiliado(afiliado);
        modE.setFormaPago(frm.txtFormaPago.getText());
        modE.setTotalPagar(Double.parseDouble(frm.txtTotalPagar.getText()));
        //modE.setHorario(horario);
        modE.setAnulado(frm.chkAnulado.isSelected());
    }

}
