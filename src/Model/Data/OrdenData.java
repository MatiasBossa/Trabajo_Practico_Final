/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Data;

import Model.Entities.Orden;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class OrdenData extends Conexion {
    private Connection con = null;

    public OrdenData() {
        this.con = getConexion();
    }


    public int guardarOrden(Orden orden) {
        int id=0;
        String sql = "INSERT INTO ORDEN "
                + "(fechaEmision, idAfiliado, idHorario, formaPago, totalPagar, anulada) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, (Date)orden.getFechaEmision());
            ps.setInt(2, orden.getAfiliado().getId());
            ps.setInt(3, orden.getHorario().getIdHorario());
            ps.setString(4, orden.getFormaPago());
            ps.setDouble(5, orden.getTotalPagar());
            ps.setBoolean(6, orden.getAnulado());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);
            else
                System.out.println("ERROR al obtener el ID generado.");
            
            ps.close();
            
        } catch (SQLException ex) {
            System.out.println("ERROR al guardar Orden.");
        }
        
        return id;
    }
    
    public void modificarOrden(Orden orden) {
        String sql = "UPDATE orden SET fechaEmision = ? , idAfiliado = ? , idHorario = ? ,"
                + " formaPago = ? , totalPagar = ? , anulada = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, (Date)orden.getFechaEmision());
            ps.setInt(2, orden.getAfiliado().getId());
            ps.setInt(3, orden.getHorario().getIdHorario());
            ps.setString(4, orden.getFormaPago());
            ps.setDouble(5, orden.getTotalPagar());
            ps.setBoolean(6, orden.getAnulado());
            
            ps.executeUpdate();
            
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(OrdenData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarOrden(int idOrden) {
        String sql = "DELETE FROM orden WEHRE idOrden =  ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idOrden);
            
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException ex) {
            System.out.println("La orden NO pudo ser eliminada. En su lugar se cambio el estado a anulado.");            
            //... aqui va el método para cambiar el estado de la orden a anulado.
        }
    }
    
    public void anularOrden(int idOrden) {
        String sql = "UPDATE orden SET anulado = ? WHERE idOrden = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, idOrden);
            
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException ex) {
            System.out.println("ERROR al anular Orden.");
        }
    }
    
    /**
     * Busca una orden a partir de parámetro idOrden
     * @param idOrden
     * @return un objeto Orden
     */
    public Orden buscarOrden(int idOrden) {
        Orden orden = null;
        String sql = "SELECT * FROM orden WHERE idOrden = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idOrden);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orden = new Orden();
                orden.setIdOrden(rs.getInt("idOrden"));
                orden.setFechaEmision(rs.getDate("fechaEmision"));
                AfiliadoData ad = new AfiliadoData();
                //orden.setAfiliado(ad.buscarAfiliado(rs.getInt("idAfiliado")));
                HorarioData hd = new HorarioData();
                //orden.setHorario(hd.buscarHorario(rs.getInt("idHorario")));
                orden.setFormaPago(rs.getString("formaPago"));
                orden.setTotalPagar(rs.getDouble("totalPagar"));
                orden.setAnulado(rs.getBoolean("anulado"));
            }
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdenData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orden;
    }
    
    /**
     * Busca todas las ordenes de un afiliado a partir del idAfiliado pasado por parámetro
     * @param idAfiliado
     * @return un alista de ordenes
     */
    public List<Orden> listarOrdenes(int idAfiliado) {
        List<Orden> movimientos = new ArrayList<Orden>();
        String sql = "SELECT * FROM orden WHERE idAfiliado = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAfiliado);
            
            ResultSet rs = ps.executeQuery();
            AfiliadoData ad = new AfiliadoData();
            HorarioData hd = new HorarioData();
            Orden orden = null;
            while(rs.next()) {
                orden = new Orden();
                orden.setIdOrden(rs.getInt("idOrden"));
                orden.setFechaEmision(rs.getDate("fechaEmision"));
                //orden.setAfiliado(ad.buscarAfiliado(rs.getInt("idAfiliado")));
                //orden.setHorario(hd.buscarHorario(rs.getInt("idHorario")));
                orden.setFormaPago(rs.getString("formaPago"));
                orden.setTotalPagar(rs.getDouble("totalPagar"));
                orden.setAnulado(rs.getBoolean("anulado"));
                movimientos.add(orden);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movimientos;
    }

}