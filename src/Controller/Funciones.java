/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class Funciones {
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Obtiene una fecha desde un JDateChoose y la devuelve como String.
     * @param jd
     * @return 
     */
    public String getFecha(JDateChooser jd) {
        if (jd.getDate() != null) {
            return Formato.format(jd.getDate());
        } else {
            return null;
        }
    }
    
    /**
     * Asigna una fecha a un JDateChoose a partir de un fecha en formato String.
     * @param fecha
     * @return 
     */
    public java.util.Date StringAFecha(String fecha) {
        SimpleDateFormat formato_de_Texto = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaE = null;
        try {
            fechaE = formato_de_Texto.parse(fecha);
            return fechaE;
        } catch (ParseException e) {
            return null;
        }
    }
    public java.util.Date StringAFechaHora(String fechaHora) {
        SimpleDateFormat formato_de_Texto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaE = null;
        try {
            fechaE = formato_de_Texto.parse(fechaHora);
            return fechaE;
        } catch (ParseException e) {
            return null;
        }
    }
    
    
    /**
     * Valida que solo se ingresen letras
     * @param a 
     */
    public static void SLetras(JTextField a) {
        a.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
               char c=ke.getKeyChar();
                if (Character.isDigit(c)){
                    Toolkit.getDefaultToolkit().beep();
                    ke.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
        );
    }
    
    /**
     * Valida que solo se ingresen numeros unicamente
     * @param a 
     */
    public static void SNumero(JTextField a) {
        a.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
               char c=ke.getKeyChar();
                if (Character.isLetter(c)){
                    Toolkit.getDefaultToolkit().beep();
                    ke.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //JOptionPane.showMessageDialog(null, "keyPressed");                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                //JOptionPane.showMessageDialog(null, "keyReleased");
            }
        }
        );        
    }
}
