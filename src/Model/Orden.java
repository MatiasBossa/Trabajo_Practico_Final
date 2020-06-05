/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;



/**
 *
 * @author matia
 */

enum formaPago{
    EFECTIVO,
    DEBITO;
}

public class Orden {
    
    private int id;
    private Date fecha;
    private formaPago tipoPago;
    private boolean estado;
    private double totalPagar;
    private Afiliado afiliado;
    private Prestador prestador;
    private Horario horario;

    public Orden(int id, Date fecha, formaPago tipoPago, boolean estado, double totalPagar, Afiliado afiliado, Prestador prestador, Horario horario) {
        this.id = id;
        this.fecha = fecha;
        this.tipoPago = tipoPago;
        this.estado = estado;
        this.totalPagar = totalPagar;
        this.afiliado = afiliado;
        this.prestador = prestador;
        this.horario = horario;
    }

    public Orden(Date fecha, formaPago tipoPago, boolean estado, double totalPagar, Afiliado afiliado, Prestador prestador, Horario horario) {
        this.fecha = fecha;
        this.tipoPago = tipoPago;
        this.estado = estado;
        this.totalPagar = totalPagar;
        this.afiliado = afiliado;
        this.prestador = prestador;
        this.horario = horario;
    }

    public Orden() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public formaPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(formaPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Orden{" + "id=" + id + ", fecha=" + fecha + ", tipoPago=" + tipoPago + ", estado=" + estado + ", totalPagar=" + totalPagar + ", afiliado=" + afiliado + ", prestador=" + prestador + ", horario=" + horario + '}';
    }
    
}
