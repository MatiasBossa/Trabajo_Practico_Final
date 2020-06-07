package Model.Data;

import Model.Entities.Afiliado;
import java.sql.*;
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
            String sql = "DELETE FROM `afiliado` WHERE idAfiliado = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, idAfiliado);
        
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException ex){
            Logger.getLogger(AfiliadoData.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void modificarAfiliado(Afiliado afiliado){  
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
