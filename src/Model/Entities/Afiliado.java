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
public class Afiliado extends Persona {
    
    
    private List<Orden> movimientos;

    public Afiliado(List<Orden> movimientos, int id, String nombre, String apellido, long dni, boolean activo) {
        super(id, nombre, apellido, dni, activo);
        this.movimientos = movimientos;
    }

    public Afiliado(List<Orden> movimientos, String nombre, String apellido, long dni, boolean activo) {
        super(nombre, apellido, dni, activo);
        this.movimientos = movimientos;
    }

    public Afiliado(List<Orden> movimientos) {
        this.movimientos = movimientos;
    }

    public Afiliado() {
    }

    public List<Orden> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Orden> movimientos) {
        this.movimientos = movimientos;
    }

    @Override
    public String toString() {
        //return "Afiliado{ " + super.toString() + ", movimientos=" + movimientos + '}';
        return this.getApellido() + " " + this.getNombre();
    }

    @Override
    public boolean equals(Object obj) {
        return getId() == ((Afiliado) obj).getId();
    }    
}
