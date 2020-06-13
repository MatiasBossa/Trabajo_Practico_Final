/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.Entities.Especialidad;
import java.sql.*;

/**
 *
 * @author matia
 */
public class EspecialidadData extends Conexion {
    private Connection con = null;

    public EspecialidadData() {
        this.con = getConexion();
    }
    
    public void guardarEspecialidad(Especialidad esp){
        String SQL_INSERT = "INSERT INTO especialidad(titulo) VALUES(?)";
        try{
            PreparedStatement ps  = con.prepareStatement(SQL_INSERT);
            ps.setString(1, esp.getTitulo());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                esp.setIdEspecialidad(rs.getInt(1));
            else
                System.out.println("ERROR al obtener el ID generado.");
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al guardar la Especialidad");
            e.printStackTrace();
        }/*finally{
            return esp.getIdEspecialidad();
        }*/
    }
    
    public Especialidad buscarEspecialidad(int id){
        String SQL_SELECT = "SELECT * FROM especialidad WHERE idEspecialidad = ?";
        ResultSet rs;
        Especialidad esp = null;
        try{
            PreparedStatement ps = con.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            
             rs = ps.executeQuery();
             
             if(rs.next()){
                 esp = new Especialidad(rs.getInt(1), rs.getString(2));
             }
             
             rs.close();
             ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al encontrar la Especialidad");
            
        }
        
        return esp;
    }
    
    public Especialidad buscarEspecialidad(String titulo){
        String SQL_SELECT = "SELECT * FROM especialidad WHERE titulo = ?";
        ResultSet rs;
        Especialidad esp = null;
        try{
            PreparedStatement ps = con.prepareStatement(SQL_SELECT);
            ps.setString(1, titulo);
            
             rs = ps.executeQuery();
             
             if(rs.next()){
                 esp = new Especialidad(rs.getInt(1), rs.getString(2));
             }
             
             rs.close();
             ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al encontrar la Especialidad");
            
        }
        
        return esp;
    }
    
    public void borrarEspecialidad(int id){
        String SQL_DELETE = "DELETE FROM especialidad WHERE idEspecialidad = ?";
        try{
            PreparedStatement ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al eliminar la especialidad");
            e.printStackTrace();
        }
    }
    
    public void modificarEspecialidad(Especialidad esp){
        String SQL_UPDATE = "UPDATE especialidad SET titulo = ? WHERE idEspecialidad = ?";
        try{
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, esp.getTitulo());
            ps.setInt(2, esp.getIdEspecialidad());
            
            ps.executeUpdate();
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al actualizar la especialidad");
            e.printStackTrace();
        }
    }
    
}
