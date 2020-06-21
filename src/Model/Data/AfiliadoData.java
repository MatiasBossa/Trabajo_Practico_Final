package Model.Data;

import Model.Entities.Afiliado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Matias
 */
public class AfiliadoData extends Conexion {
    private Connection con = null;

    public AfiliadoData() {
        this.con = getConexion();
    }
    
    public void guardarAfiliado(Afiliado afiliado){
        try {
            String sql = "INSERT INTO `afiliado` (`nombre`, `apellido`, `dni`, `activo`) VALUES (?, ?, ?, ?);";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, afiliado.getNombre());
            ps.setString(2, afiliado.getApellido());
            ps.setLong(3, afiliado.getDni());
            ps.setBoolean(4, afiliado.getActivo());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
           
            if(rs.next()){
                afiliado.setId(rs.getInt(1));
            } else {
              System.out.println("No se pudo obtener el id luego de insertar un afiliado");
            }
                
        } catch (SQLException ex){
            Logger.getLogger(AfiliadoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarAfiliado(int idAfiliado) {  //Borramos con DNI
        try {
            String sql = "DELETE FROM `afiliado` WHERE idAfiliado = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAfiliado);
        
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException e){
            System.out.println("ERROR BORRAR");
        }        
    }
    
    public void modificarAfiliado(Afiliado afiliado){  
        try {
            String sql = "UPDATE `afiliado` SET `nombre`=?,"
                    + "`apellido`=?,`dni`=?, `activo`=? WHERE `idAfiliado`=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, afiliado.getNombre());
            ps.setString(2, afiliado.getApellido());
            ps.setLong(3, afiliado.getDni());
            ps.setBoolean(4, afiliado.getActivo());
            ps.setInt(5, afiliado.getId());
            
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException ex){
            Logger.getLogger(AfiliadoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Afiliado> listarAfiliados() {
        List<Afiliado> lista = new ArrayList<Afiliado>();
        String sql = "SELECT * FROM afiliado ORDER BY apellido, nombre;";
        try {
            Afiliado afiliado;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 afiliado = new Afiliado();
                 afiliado.setId(rs.getInt("idAfiliado"));
                 afiliado.setNombre(rs.getString("nombre"));
                 afiliado.setApellido(rs.getString("apellido"));
                 afiliado.setDni(rs.getInt("dni"));
                 afiliado.setActivo(rs.getBoolean("activo"));
                 lista.add(afiliado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfiliadoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public void desactivarAfiliado(int id){
        String sql = "UPDATE afiliado SET activo = ? WHERE idAfiliado = ?;";
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
    
    public Afiliado buscarAfiliado(int idAfiliado) {
        Afiliado aux = null;
        String sql = "SELECT * FROM afiliado WHERE idAfiliado = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs;
            ps.setInt(1, idAfiliado);

            rs = ps.executeQuery();

            if (rs.next()) {
                aux = new Afiliado();
                aux.setId(rs.getInt(1));
                aux.setNombre(rs.getString(2));
                aux.setApellido(rs.getString(3));
                aux.setDni(rs.getLong(4));
                aux.setActivo(rs.getBoolean(5));
            }
        }catch(SQLException e){
            System.out.println("ERROR al buscar el afiliado");
            e.printStackTrace();
        }
        
        return aux;
    }
    
    public Afiliado buscarAfiliadoDni(int dni) {
        Afiliado aux = null;
        String sql = "SELECT * FROM afiliado WHERE dni = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs;
            ps.setInt(1, dni);

            rs = ps.executeQuery();

            if (rs.next()) {
                aux = new Afiliado();
                aux.setId(rs.getInt(1));
                aux.setNombre(rs.getString(2));
                aux.setApellido(rs.getString(3));
                aux.setDni(rs.getLong(4));
                aux.setActivo(rs.getBoolean(5));
            }
        }catch(SQLException e){
            System.out.println("ERROR al buscar el afiliado");
            e.printStackTrace();
        }
        
        return aux;
    }
   
}

