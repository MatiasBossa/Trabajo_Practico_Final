/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Data.HorarioData;
import Model.Entities.Horario;
import View.frmHorarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Nicolas
 */
public class ControlHorarios implements ActionListener {
    private Horario horaE;
    private HorarioData horaD;
    private frmHorarios frm;

    public ControlHorarios(Horario horaE, HorarioData horaD, frmHorarios frm) {
        this.horaE = horaE;
        this.horaD = horaD;
        this.frm = frm;
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnBorrar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.chkEstado.addActionListener(this);
    }
    
    public void Iniciar() {
        frm.setTitle("Horarios");
        //frm.setLocationRelativeTo(null);
        //frm.txtId.setVisible(true);//se cambiara a falso ya que el id no debe ser visto por el usuario
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
    
}
