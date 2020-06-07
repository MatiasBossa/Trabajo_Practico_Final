/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.Entities.Prestador;
import java.sql.*;

/**
 *
 * @author matia
 */
public class PrestadorData {
    
    private Connection con = null;
    private Conexion conexion;
    
    private final String SQL_INSERT = "INSERT INTO prestador(nombre, apellido, dni, activo, idEspecialidad) VALUES(?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE prestador SET nombre = ?, apellido = ?, dni = ?, activo = ?, idEspecialidad = ? WHERE idPrestador = ?";
    private final String SQL_DELETE = "DELETE FROM prestador WHERE idPrestador = ?";
    private final String SQL_SELECT = "SELECT * FROM prestador WHERE idPrestador = ?";
    
    public PrestadorData(Conexion conexion) {
        try {
            this.conexion=conexion;
            con = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    public void guardarPrestador(Prestador prestador){
        try{
            PreparedStatement ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, prestador.getNombre());
            ps.setString(2, prestador.getApellido());
            ps.setLong(3, prestador.getDni());
            ps.setBoolean(4, true);
            ps.setInt(5, prestador.getEspecialidad().getIdEspecialidad());
            
            ps.executeUpdate();
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al guardar el prestador");
            e.printStackTrace();
        }
        
    }
    
    public void modificarPrestador(Prestador prestador){
        try{
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, prestador.getNombre());
            ps.setString(2, prestador.getApellido());
            ps.setLong(3, prestador.getDni());
            ps.setBoolean(4, true);
            ps.setInt(5, prestador.getEspecialidad().getIdEspecialidad());
            ps.setInt(6, prestador.getId());
            
            ps.executeUpdate();
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al modificar el prestador");
            e.printStackTrace();
        }
    }
    
    public void eliminarPrestador(Prestador prestador){
        try{
            PreparedStatement ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1, prestador.getId());
            
            ps.executeUpdate();
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al borrar el prestador");
            e.printStackTrace();
        }
    }
    
    public Prestador buscarPrestador(int id){
        Prestador aux = null;
        try{
            PreparedStatement ps = con.prepareStatement(SQL_SELECT);
            ResultSet rs;
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                aux = new Prestador();
                aux.setId(rs.getInt(1));
                aux.setNombre(rs.getString(2));
                aux.setApellido(rs.getString(3));
                aux.setDni(rs.getLong(4));
                aux.setActivo(rs.getBoolean(5));
                //aux.setEspecialidad(especialidad);
                //aux.setHorarios(horarios);
                
            }
        }catch(SQLException e){
            System.out.println("ERROR al buscar el prestador");
            e.printStackTrace();
        }
        
        return aux;
    }
    
}
