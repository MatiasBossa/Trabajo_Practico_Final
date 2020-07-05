/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

/**
 *
 * @author HP
 */
public class Usuario {
    private String mail;
    private String password;
    private String apellido;
    private String nombre;
    private String areaDeTrabajo;
    private int idUsuario;

    public Usuario() {
    }

    public Usuario(String mail, String password, String apellido, String nombre, String areaDeTrabajo, int idUsuario) {
        this.mail = mail;
        this.password = password;
        this.apellido = apellido;
        this.nombre = nombre;
        this.areaDeTrabajo = areaDeTrabajo;
        this.idUsuario = idUsuario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAreaDeTrabajo() {
        return areaDeTrabajo;
    }

    public void setAreaDeTrabajo(String areaDeTrabajo) {
        this.areaDeTrabajo = areaDeTrabajo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    
    
    @Override
    public String toString() {
        return idUsuario + " " + apellido + " " + nombre +" " + mail + " ( " + areaDeTrabajo + " )";
    }
    
    
}
