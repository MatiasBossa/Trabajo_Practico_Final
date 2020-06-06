package Controller;

import Model.Afiliado;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Matias
 */
public class AfiliadoData {
    private Connection con;
    
    public AfiliadoData(Conexion conexion) {
        try {
            con = conexion.getConexion();
        } catch(SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion. " + ex.getMessage());
        }

    }
    
    public void altaAfiliado(Afiliado afiliado){
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
    
    public void bajaAfiliado(long dni) {  //Borramos con DNI
        try {
            String sql = "DELETE FROM `afiliado` WHERE dni = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, dni);
        
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException ex){
            Logger.getLogger(AfiliadoData.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void actualizarAfiliado(Afiliado afiliado){  
        try {
            String sql = "UPDATE `afiliado` SET nombre`=?,"
                    + "`apellido`=?,`activo`=? WHERE `dni`=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, afiliado.getNombre());
            ps.setString(2, afiliado.getApellido());
            ps.setBoolean(3, afiliado.getActivo());
            ps.setLong(4, afiliado.getDni());
            
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException ex){
            Logger.getLogger(AfiliadoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
