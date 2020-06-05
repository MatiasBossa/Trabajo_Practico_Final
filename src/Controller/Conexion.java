/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Conexion {
    private String HOST = "localhost:3306";
    private String DB = "obrasocial";
    private String URL = "jdbc:mariadb://" + HOST + "/" + DB;
    private String usuario = "root";
    private String password = "";
    private Connection conexion;

    
    public Conexion() throws ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        
    }

    public Conexion(String url, String usuario, String password) throws ClassNotFoundException {
        this.URL = url;
        this.usuario = usuario;
        this.password = password;

        //Cargamos las clases de mariadb que implementan JDBC
        Class.forName("org.mariadb.jdbc.Driver");

    }
    
    public Connection getConexion() throws SQLException{
        if(conexion == null){
            // Setup the connection with the DB
            conexion = DriverManager
                .getConnection(URL + "?useLegacyDatetimeCode=false&serverTimezone=UTC"
                        + "&user=" + usuario + "&password=" + password);

        }
        return conexion;
    }
    
}