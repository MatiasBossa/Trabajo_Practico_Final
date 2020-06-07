/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import java.util.Date;



/**
 *
 * @author matia
 */

enum FormaPago{
    EFECTIVO,
    DEBITO;
}

public class Orden {
    
    private int idOrden;
    private Date fechaEmision;
    private Afiliado afiliado;
    private Horario horario;
    private String formaPago;
    private double totalPagar;
    private boolean anulado;

    /**
     * Constructor vacio
     */
    public Orden() {
    }

    /**
     * Constructor sin el campo idOrden
     * @param fechaEmision
     * @param afiliado
     * @param horario
     * @param formaPago
     * @param totalPagar
     * @param anulado 
     */
    public Orden(Date fechaEmision, Afiliado afiliado, Horario horario, String formaPago, double totalPagar, boolean anulado) {
        this.fechaEmision = fechaEmision;
        this.afiliado = afiliado;
        this.horario = horario;
        this.formaPago = formaPago;
        this.totalPagar = totalPagar;
        this.anulado = anulado;
    }

    /**
     * Constructor con todos los campos
     * @param idOrden
     * @param fechaEmision
     * @param afiliado
     * @param horario
     * @param formaPago
     * @param totalPagar
     * @param anulado 
     */
    public Orden(int idOrden, Date fechaEmision, Afiliado afiliado, Horario horario, String formaPago, double totalPagar, boolean anulado) {
        this.idOrden = idOrden;
        this.fechaEmision = fechaEmision;
        this.afiliado = afiliado;
        this.horario = horario;
        this.formaPago = formaPago;
        this.totalPagar = totalPagar;
        this.anulado = anulado;
    }


    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }
    
    @Override
    public String toString() {
        return "Orden{" + "idOrden=" + idOrden + ", fechaEmision=" + fechaEmision + ", tipoPago=" + formaPago + ", estado=" + anulado + ", totalPagar=" + totalPagar + ", afiliado=" + afiliado + ", horario=" + horario + '}';
    }

}