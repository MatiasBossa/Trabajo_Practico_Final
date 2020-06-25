/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

/**
 *
 * @author matia
 */
public class Especialidad {
    
    private int idEspecialidad;
    private String titulo;

    public Especialidad(int idEspecialidad, String titulo) {
        this.idEspecialidad = idEspecialidad;
        this.titulo = titulo;
    }

    public Especialidad(String titulo) {
        this.titulo = titulo;
    }
    public Especialidad() {
        this.titulo = titulo;
    }
    
    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        //se va a usar con el combobox
        return titulo;
        //return "idEspecialidad=" + idEspecialidad + ", titulo=" + titulo;
    }
    @Override
    public boolean equals(Object obj) {
        return this.idEspecialidad == ((Especialidad)obj).getIdEspecialidad();
    }

}
