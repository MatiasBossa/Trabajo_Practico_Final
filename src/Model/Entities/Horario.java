/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author matia
 */
public class Horario {
    
    private int idHorario;
    private Prestador prestador;
    private LocalDate fecha;
    private String dia;
    private LocalTime hora;

    private String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};


    // Constructores
    public Horario() {
    }
    public Horario(Prestador prestador, LocalDate fecha, String dia, LocalTime hora) {
        this.prestador = prestador;
        this.fecha = fecha;
        this.dia = dia;
        this.hora = hora;
    }
    public Horario(int idHorario, Prestador prestador, LocalDate fecha, String dia, LocalTime hora) {
        this.idHorario = idHorario;
        this.prestador = prestador;
        this.fecha = fecha;
        this.dia = dia;
        this.hora = hora;
    }
 


    // Getter and Setter
    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    
    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime horaAtencion) {
        this.hora = horaAtencion;
    }


    @Override
    public String toString() {
        int d = fecha.getDayOfWeek().getValue();
        return fecha +" "+ dias[d-1] + " - " + hora ;
    }

/*
    @Override
    public boolean equals(Object obj) {
        return getIdHorario() == ((Horario) obj).getIdHorario();
    }
*/


}
