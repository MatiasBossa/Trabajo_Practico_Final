/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Conexion {
    private String HOST = "localhost:3306";
    private String DB = "obrasocial";
    private String URL = "jdbc:mariadb://" + HOST + "/" + DB;
    //private String URL = "jdbc:mysql://" + HOST + "/" + DB;
    private String user = "root";
    private String password = "";
    //private Connection conexion;
    private Connection con = null;

    
    
    
    public Connection getConexion()
    {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            //Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.URL, this.user, this.password);
            
        } catch(SQLException e)
        {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
      return con;  
    }
}
