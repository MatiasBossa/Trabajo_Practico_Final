/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Especialidad;
import java.sql.*;

/**
 *
 * @author matia
 */
public class EspecialidadData {
    
    private Connection con = null;
    private Conexion conexion;
    
    private final String SQL_INSERT = "INSERT INTO especialidad(titulo) VALUES(?)";
    
    public EspecialidadData(Conexion conexion) {
        try {
            this.conexion=conexion;
            con = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    public void guardarEspecialidad(Especialidad esp){
        try{
            PreparedStatement ps  = con.prepareStatement(SQL_INSERT);
            ps.setString(1, esp.getTitulo());
        }catch(SQLException e){
            System.out.println("ERROR al guardar la Especialidad");
            e.printStackTrace();
        }
    }
    
    
    
}
