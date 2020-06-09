/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.Entities.Horario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matia
 */
public class HorarioData extends Conexion {
    private Connection con = null;

    public HorarioData() {
        this.con = getConexion();
    }
    
    public void guardarHorario(Horario horario){
        String SQL_INSERT = "INSERT INTO horario(dia, horarioAtencion, idPrestador) VALUES(?, ?, ?)";
        try{
            PreparedStatement ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, horario.getDia());
            Time time = Time.valueOf(horario.getHorarioAtencion()); // Convertimos el LocalTime a Time de SQL
            ps.setTime(2, time);
            ps.setInt(3, horario.getPrestador().getId());
            
            ps.executeUpdate();
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al guardar el horario");
            e.printStackTrace();
        }
    }
    
    public void borrarHorario(int id){
        String SQL_DELETE = "DELETE FROM horario WHERE idHorario = ?";
        try{
            PreparedStatement ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al borrar el horario");
            e.printStackTrace();
        }
    }
    
    public void modificarHorario(Horario horario){
        String SQL_UPDATE = "UPDATE horario SET dia = ?, horarioAtencion = ?, idPrestador = ? WHERE idHorario = ?";
        try{
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, horario.getDia());
            Time time = Time.valueOf(horario.getHorarioAtencion());
            ps.setTime(2, time);
            
        }catch(SQLException e){
            System.out.println("ERROR al modificar el horario");
            e.printStackTrace();
        }
    }
    
    public Horario buscarHorario(int id){
        String SQL_SELECT = "SELECT * FROM horario WHERE idHorario = ?";
        PrestadorData pd = new PrestadorData();
        ResultSet rs;
        Horario horario = null;
        try{
            PreparedStatement ps = con.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                horario = new Horario();
                horario.setIdHorario(rs.getInt(1));
                horario.setDia(rs.getString(2));
                horario.setHorarioAtencion(rs.getTime(3).toLocalTime());
                horario.setPrestador(pd.buscarPrestador(rs.getInt(4)));
            }
            
            rs.close();
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al obtener el horario");
            e.printStackTrace();
        }
        
        return horario;
    }
    
    public List<Horario> listarHorarios(int idPrestador){
        String SQL_SELECT = "SELECT * FROM horario WHERE idPrestador = ?";
        PrestadorData pd = new PrestadorData();
        ResultSet rs;
        List<Horario> horarios = null;
        Horario horario = null;
        try{
            PreparedStatement ps = con.prepareStatement(SQL_SELECT);
            ps.setInt(1, idPrestador);
            
            rs = ps.executeQuery();
            
            horarios = new ArrayList<>();
            while(rs.next()){
                horario = new Horario();
                horario.setIdHorario(rs.getInt(1));
                horario.setDia(rs.getString(2));
                horario.setHorarioAtencion(rs.getTime(3).toLocalTime());
                
                horario.setPrestador(pd.buscarPrestador(rs.getInt(4)));
                
                horarios.add(horario);
            }
            
            rs.close();
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al obtener el horario");
            e.printStackTrace();
        }
        
        return horarios;
    }
    
}
