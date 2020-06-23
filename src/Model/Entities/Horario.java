/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;


import java.time.LocalTime;

/**
 *
 * @author matia
 */
public class Horario {
    
    private int idHorario;
    private String dia;
    private LocalTime horarioAtencion;
    private Prestador prestador;

    public Horario(int idHorario, String dia, LocalTime horarioAtencion, Prestador prestador) {
        this.idHorario = idHorario;
        this.dia = dia;
        this.horarioAtencion = horarioAtencion;
        this.prestador = prestador;
    }

    public Horario(String dia, LocalTime horarioAtencion, Prestador prestador) {
        this.dia = dia;
        this.horarioAtencion = horarioAtencion;
        this.prestador = prestador;
    }

    public Horario() {
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalTime getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(LocalTime horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    @Override
    public String toString() {
        return dia + " - " + horarioAtencion ;
    }

/*
    @Override
    public boolean equals(Object obj) {
        return getIdHorario() == ((Horario) obj).getIdHorario();
    }
*/
}
