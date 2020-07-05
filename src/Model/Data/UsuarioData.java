/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.Entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class UsuarioData extends Conexion {
    private Connection con = null;
    
    public UsuarioData() {
        this.con = getConexion();
    }
    
    public List<Usuario> obetenerUsuariosPorArea(String areaDeTrabajo) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM Usuario WHERE areaDeTrabajo = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, areaDeTrabajo.trim());
            
            ResultSet rs = ps.executeQuery();

            Usuario usuario = null;
            while(rs.next()) {
                usuario = new Usuario();
                usuario.setMail(rs.getString("mail"));
                usuario.setPassword(rs.getString("password"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setAreaDeTrabajo(rs.getString("areaDeTrabajo"));
                usuario.setIdUsuario( Integer.parseInt(rs.getString("idUsuario")) );
                
                usuarios.add(usuario);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdenData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
}
