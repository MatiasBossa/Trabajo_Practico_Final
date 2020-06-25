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

    // <editor-fold defaultstate="collapsed" desc="Constructor completo"> 
    public Prestador(Especialidad especialidad, int id, String nombre, String apellido, long dni, boolean activo) {
        super(id, nombre, apellido, dni, activo);
        this.especialidad = especialidad;
       // this.horarios = horarios;
    }// </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Constructor sin id"> 
    public Prestador(Especialidad especialidad, String nombre, String apellido, long dni, boolean activo) {
        super(nombre, apellido, dni, activo);
        this.especialidad = especialidad;
        //this.horarios = horarios;
    }// </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Constructor solo con Especialidad"> 
    public Prestador(Especialidad especialidad) {
        this.especialidad = especialidad;
        //this.horarios = horarios;
    }// </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Constructor vacio"> 
    public Prestador(){
        
    }// </editor-fold>  

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
        
        return this.getApellido() + " " + this.getNombre() + " - " + this.getEspecialidad().getTitulo();

        //return "Prestador{" + super.toString() + " especialidad[" + especialidad.getTitulo() + "] }";
    }

    @Override
    public boolean equals(Object obj) {
        return (getId() == ((Prestador) obj).getId()) /*&& getDni()==((Prestador) obj).getDni()*/;
    }

}
