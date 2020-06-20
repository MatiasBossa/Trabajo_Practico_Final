/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.Entities.Especialidad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author matia
 */
public class EspecialidadData extends Conexion {
    private Connection con = null;

    public EspecialidadData() {
        this.con = getConexion();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Create"> 
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
    }// </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Create con titulo"> 
    public int guardarEspecialidad(String titulo) {
        int id=0;
        String SQL_INSERT = "INSERT INTO especialidad(titulo) VALUES(?)";
        try {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, titulo);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);
            else
                System.out.println("ERROR al obtener el ID generado.");

            ps.close();

        } catch (SQLException ex) {
            System.out.println("ERROR al guardar la Especialidad");
            ex.printStackTrace();
        }
        return id;
    }// </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Read con id"> 
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
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Read con titulo"> 
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
             if(esp==null){//Si no encontro el titulo la 1ra vez guarda el nuevo titulo y
                 //llama nuevamente este metodo. Tal vez seria mejor usar buscarEspecialidad que
                 //usa eel parametro int???????
                 
                 guardarEspecialidad(titulo);
                 
                 esp=buscarEspecialidad(titulo);
             }
             
             rs.close();
             ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al encontrar la Especialidad");
        }
        return esp;
    }// </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Create con id"> 
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
    }// </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Update"> 
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
    }// </editor-fold> 
    
    
    public void listarEspecialidades(JComboBox<String> combo){
        String SQL_SELECT = "SELECT * FROM especialidad";
        ResultSet rs;
        try{
            PreparedStatement ps = con.prepareStatement(SQL_SELECT);
            
             rs = ps.executeQuery();
             
             while(rs.next()){
                 combo.addItem(rs.getString("titulo"));
             }
             
             rs.close();
             ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al encontrar la Especialidad");
            
        }
        
    }
    
   public ArrayList<Especialidad> listarEspecialidades(){
        ArrayList<Especialidad> especialidades = new ArrayList<Especialidad>();
        String SQL_SELECT = "SELECT * FROM especialidad";
        
        try{
            PreparedStatement ps = con.prepareStatement(SQL_SELECT);
            ResultSet rs = ps.executeQuery();
            Especialidad especialidad;
            
            while(rs.next()){
                 especialidad = new Especialidad();
                 especialidad.setIdEspecialidad(rs.getInt("idEspecialidad"));
                 especialidad.setTitulo(rs.getString("titulo"));
                 especialidades.add(especialidad);                                
             }
             rs.close();
             ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al encontrar la Especialidad");            
        }
        return especialidades;
    }
}
