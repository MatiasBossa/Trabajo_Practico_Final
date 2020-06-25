/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.Entities.Horario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author matia
 */
public class HorarioData extends Conexion {
    private Connection con = null;
    private String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    
    public HorarioData() {
        this.con = getConexion();
    }
    
    public void guardarHorario(Horario horario){
        String SQL_INSERT = "INSERT INTO horario(idPrestador, fecha, hora) VALUES(?, ?, ?)";
        try{
            PreparedStatement ps = con.prepareStatement(SQL_INSERT);
            Time time = Time.valueOf(horario.getHora()); // Convertimos el LocalTime a Time de SQL
            ps.setInt(1, horario.getPrestador().getId());
            ps.setDate(2, Date.valueOf( horario.getFecha() ) );
            ps.setTime(3, time);
            
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
        String SQL_UPDATE = "UPDATE horario SET idPrestador = ?, fecha = ?, hora = ?  WHERE idHorario = ?";
        try{
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
            ps.setInt(1, horario.getPrestador().getId());
            Time time = Time.valueOf(horario.getHora()); // Convertimos el LocalTime a Time de SQL
            ps.setInt(1, horario.getPrestador().getId());
            ps.setDate(2, Date.valueOf( horario.getFecha() ) );
            ps.setTime(3, time);
            ps.setInt(4, horario.getIdHorario());
            
            ps.executeUpdate();
            
            ps.close();
            
        }catch(SQLException e){
            System.out.println("ERROR al modificar el horario");
            e.printStackTrace();
        }
    }
    
    public Horario buscarHorario(int idHorario){
        String sql = "SELECT * FROM horario WHERE idHorario = ?";
        PrestadorData pd = new PrestadorData();
        ResultSet rs;
        Horario horario = null;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idHorario);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                horario = new Horario();
                horario.setIdHorario(rs.getInt("idHorario"));
                horario.setPrestador(pd.buscarPrestador(rs.getInt("idPrestador")));
                horario.setFecha( rs.getDate("fecha").toLocalDate() );                
                int d = rs.getDate("fecha").toLocalDate().getDayOfWeek().getValue();
                horario.setDia(dias[d-1]);                
                horario.setHora(rs.getTime("hora").toLocalTime());
            }
            
            rs.close();
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al obtener el horario");
            e.printStackTrace();
        }
        
        return horario;
    }
    public boolean horarioDuplicado(Horario horario){
        String sql = "SELECT * FROM horario WHERE idPrestador = ? AND fecha = ? AND hora = ?";
        boolean existe = false;
        Horario h = null;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            Time time = Time.valueOf(horario.getHora()); // Convertimos el LocalTime a Time de SQL
            ps.setInt(1, horario.getPrestador().getId());
            ps.setDate(2, Date.valueOf( horario.getFecha() ) );
            ps.setTime(3, time);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                existe = true;
            }
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al obtener el horario");
            e.printStackTrace();
        }
        
        return existe;
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
                horario.setIdHorario(rs.getInt("idHorario"));
                horario.setPrestador(pd.buscarPrestador(rs.getInt("idPrestador")));
                horario.setFecha( rs.getDate("fecha").toLocalDate() );                
                int d = rs.getDate("fecha").toLocalDate().getDayOfWeek().getValue();
                horario.setDia(dias[d-1]);
                horario.setHora(rs.getTime("hora").toLocalTime());

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
    public List<Horario> listarHorariosDisponibles(int idPrestador) {
        String SQL_SELECT = "SELECT * FROM horario WHERE idPrestador = ? "
                + "AND fecha > ?"
                + "AND idHorario NOT IN ( SELECT idHorario FROM orden WHERE idPrestador = ? )";
        PrestadorData pd = new PrestadorData();
        ResultSet rs;
        List<Horario> horarios = null;
        Horario horario = null;
        try{
            PreparedStatement ps = con.prepareStatement(SQL_SELECT);
            ps.setInt(1, idPrestador);
            Calendar fecha = new GregorianCalendar();
            int anio = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH)+1;
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            String fechaHoy = anio+"-"+ ( mes<10 ? "0"+mes : mes )+"-"+ ( dia<10 ? "0"+dia : dia );
            ps.setString(2, fechaHoy);
            ps.setInt(3, idPrestador);
            
            rs = ps.executeQuery();
            
            horarios = new ArrayList<>();
            while(rs.next()){
                horario = new Horario();
                horario.setIdHorario(rs.getInt("idHorario"));
                horario.setPrestador(pd.buscarPrestador(rs.getInt("idPrestador")));
                horario.setFecha( rs.getDate("fecha").toLocalDate() );                
                int d = rs.getDate("fecha").toLocalDate().getDayOfWeek().getValue();
                horario.setDia(dias[d-1]);
                horario.setHora(rs.getTime("hora").toLocalTime());

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



    /**
     * Esta función se utiliza para saber si el idPrestador esta asociado a algun horario, a partir
     * del idPrestador pasado por parámetro.
     * @param idPrestador
     * @return 
     */
    public boolean existePrestador(int idPrestador) {
        String sql = "SELECT idPrestador FROM horario WHERE idPrestador = ?;";
        boolean encontrado = false;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPrestador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                encontrado = true;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Horario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encontrado;
    }
}
