/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import java.util.List;

/**
 *
 * @author matia
 */
public class Prestador extends Persona {
    
    private Especialidad especialidad;
    //List<Horario> horarios;

    public Prestador(Especialidad especialidad, int id, String nombre, String apellido, long dni, boolean activo) {
        super(id, nombre, apellido, dni, activo);
        this.especialidad = especialidad;
       // this.horarios = horarios;
    }

    public Prestador(Especialidad especialidad, String nombre, String apellido, long dni, boolean activo) {
        super(nombre, apellido, dni, activo);
        this.especialidad = especialidad;
        //this.horarios = horarios;
    }

    public Prestador(Especialidad especialidad) {
        this.especialidad = especialidad;
        //this.horarios = horarios;
    }
    
    public Prestador(){
        
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    /*public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }*/

    @Override
    public String toString() {
        //return "Prestador{" + super.toString() + " especialidad[" + especialidad.getTitulo() + "] }";
        return this.getId() + " - " + this.getApellido() + " " + this.getNombre();
    }

}
