/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.Entities.Especialidad;
import Model.Entities.Orden;
import Model.Entities.Prestador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author matia
 */
public class PrestadorData extends Conexion {
    private Connection con = null;
    
    private final String SQL_INSERT = "INSERT INTO prestador(nombre, apellido, dni, activo, idEspecialidad) VALUES(?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE prestador SET nombre = ?, apellido = ?, dni = ?, activo = ?, idEspecialidad = ? WHERE idPrestador = ?";
    private final String SQL_DELETE = "DELETE FROM prestador WHERE idPrestador = ?";
    private final String SQL_SELECT = "SELECT * FROM prestador WHERE idPrestador = ?";

    public PrestadorData() {
        this.con = getConexion();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Create"> 
    public void guardarPrestador(Prestador prestador){
        try{
            PreparedStatement ps = con.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, prestador.getNombre());
            ps.setString(2, prestador.getApellido());
            ps.setLong(3, prestador.getDni());
            ps.setBoolean(4, prestador.getActivo());
            
            
            Especialidad esp = new Especialidad(prestador.getEspecialidad().getTitulo());
            EspecialidadData ed = new EspecialidadData();
            esp=ed.buscarEspecialidad(esp.getTitulo());
            
            
            
            
            ps.setInt(5, esp.getIdEspecialidad());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                prestador.setId(rs.getInt(1));
            else
                System.out.println("ERROR al obtener el ID generado.");
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al guardar el prestador");
            e.printStackTrace();
        }/*finally{
            //return id;
        }*/
                
    }// </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Read">
    public Prestador buscarPrestador(int idPrestador) {
        Prestador aux = null;
        EspecialidadData ed = null;
        try {
            PreparedStatement ps = con.prepareStatement(SQL_SELECT);
            ResultSet rs;
            ps.setInt(1, idPrestador);

            rs = ps.executeQuery();

            if (rs.next()) {
                ed = new EspecialidadData();
                aux = new Prestador();
                aux.setId(rs.getInt(1));
                aux.setNombre(rs.getString(2));
                aux.setApellido(rs.getString(3));
                aux.setDni(rs.getLong(4));
                aux.setActivo(rs.getBoolean(5));
                aux.setEspecialidad(ed.buscarEspecialidad(rs.getInt(6)));
                
                
                //aux.setHorarios(hd.listarHorarios(idPrestador));
                
            }
        }catch(SQLException e){
            System.out.println("ERROR al buscar el prestador");
            e.printStackTrace();
        }
        
        return aux;
    }// </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Read con dni">
    public Prestador buscarPrestador(Integer dni) {
        Prestador aux = null;
        EspecialidadData ed = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM prestador WHERE dni = ?");
            ResultSet rs;
            ps.setInt(1, dni);

            rs = ps.executeQuery();

            if (rs.next()) {
                ed = new EspecialidadData();
                aux = new Prestador();
                aux.setId(rs.getInt(1));
                aux.setNombre(rs.getString(2));
                aux.setApellido(rs.getString(3));
                aux.setDni(rs.getLong(4));
                aux.setActivo(rs.getBoolean(5));
                aux.setEspecialidad(ed.buscarEspecialidad(rs.getInt(6)));
                
                
                //aux.setHorarios(hd.listarHorarios(idPrestador));
                
            }
        }catch(SQLException e){
            System.out.println("ERROR al buscar el prestador");
            e.printStackTrace();
        }
        
        return aux;
    }// </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Listar"> 
    public List<Prestador> listarPrestador() {
        List<Prestador> lista = new ArrayList<Prestador>();
        String sql = "SELECT * FROM prestador;";
        try {
            Prestador pre;
            EspecialidadData ed;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pre = new Prestador();
                ed=new EspecialidadData();
                pre.setId(rs.getInt("idPrestador"));
                pre.setNombre(rs.getString("nombre"));
                pre.setApellido(rs.getString("apellido"));
                pre.setDni(rs.getLong("dni"));
                pre.setActivo(rs.getBoolean("activo"));
                pre.setEspecialidad(ed.buscarEspecialidad(rs.getInt("idEspecialidad")));
                //System.out.println("titulo "+pre);
                lista.add(pre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestadorData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }// </editor-fold> 
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Update"> 
    public void modificarPrestador(Prestador prestador) {
        try {
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, prestador.getNombre());
            ps.setString(2, prestador.getApellido());
            ps.setLong(3, prestador.getDni());
            ps.setBoolean(4, prestador.getActivo());

            Especialidad esp = new Especialidad(prestador.getEspecialidad().getTitulo());
            EspecialidadData ed = new EspecialidadData();
            esp = ed.buscarEspecialidad(esp.getTitulo());

            ps.setInt(5, esp.getIdEspecialidad());

            ps.setInt(6, prestador.getId());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println("ERROR al modificar el prestador");
            e.printStackTrace();
        }
    }// </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Delete"> 
    public void borrarPrestador(int idPrestador){
        try{
            PreparedStatement ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1, idPrestador);
            
            ps.executeUpdate();
            
            ps.close();
        }catch(SQLException e){
            System.out.println("ERROR al borrar el prestador");
            e.printStackTrace();
        }
    }// </editor-fold> 
    
    
    
    
    
    public void desactivarPrestador(int id){
        String sql = "UPDATE prestador SET activo = ? WHERE idPrestador = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("ERROR al desactivar Prestador.");
        }
        
    }
    
    public List<Prestador> listarPrestadores() {
        List<Prestador> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestador ORDER BY apellido, nombre;";
        try {
            Prestador prestador;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 prestador = new Prestador();
                 prestador.setId(rs.getInt("idPrestador"));
                 prestador.setNombre(rs.getString("nombre"));
                 prestador.setApellido(rs.getString("apellido"));
                 prestador.setDni(rs.getInt("dni"));
                 prestador.setActivo(rs.getBoolean("activo"));                 
                 EspecialidadData especial = new EspecialidadData();
                 prestador.setEspecialidad(especial.buscarEspecialidad(rs.getInt("idEspecialidad")));
                 lista.add(prestador);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestadorData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
