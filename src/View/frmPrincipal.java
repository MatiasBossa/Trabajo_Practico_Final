/*//GEN-LINE:variables
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControlAfiliado;
import Controller.ControlEspecialidad0;
import Controller.ControlHorarios;
import Controller.ControlOrden;
import Controller.ControlPrestador;
import Model.Data.AfiliadoData;
import Model.Data.EspecialidadData;
import Model.Data.HorarioData;
import Model.Data.OrdenData;
import Model.Data.PrestadorData;
import Model.Entities.Afiliado;
import Model.Entities.Especialidad;
import Model.Entities.Horario;
import Model.Entities.Orden;
import Model.Entities.Prestador;
import java.awt.Dimension;

/**
 *
 * @author HP
 */
public class frmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form frmPrincipal
     */
    public frmPrincipal() {
        initComponents();
        this.setTitle("Sistema Mutual. V 1.0");
        this.setSize(1024, 720);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_archivo = new javax.swing.JMenu();
        item_salir = new javax.swing.JMenuItem();
        menu_afiliados = new javax.swing.JMenu();
        item_afiliados = new javax.swing.JMenuItem();
        menu_especialidades = new javax.swing.JMenu();
        item_especialidades = new javax.swing.JMenuItem();
        menu_prestadores = new javax.swing.JMenu();
        item_prestadores = new javax.swing.JMenuItem();
        item_horarios = new javax.swing.JMenuItem();
        menu_ordenes = new javax.swing.JMenu();
        item_ordenes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        menu_archivo.setText("Archivo");

        item_salir.setText("Salir del sistema");
        item_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_salirActionPerformed(evt);
            }
        });
        menu_archivo.add(item_salir);

        jMenuBar1.add(menu_archivo);

        menu_afiliados.setText("Afiliados");

        item_afiliados.setText("Formulario de afiliados");
        item_afiliados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_afiliadosActionPerformed(evt);
            }
        });
        menu_afiliados.add(item_afiliados);

        jMenuBar1.add(menu_afiliados);

        menu_especialidades.setText("Especialidades");

        item_especialidades.setText("Formulario de especialidades");
        item_especialidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_especialidadesActionPerformed(evt);
            }
        });
        menu_especialidades.add(item_especialidades);

        jMenuBar1.add(menu_especialidades);

        menu_prestadores.setText("Prestadores");

        item_prestadores.setText("Formulario de prestadores");
        item_prestadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_prestadoresActionPerformed(evt);
            }
        });
        menu_prestadores.add(item_prestadores);

        item_horarios.setText("Formulario de horarios");
        item_horarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_horariosActionPerformed(evt);
            }
        });
        menu_prestadores.add(item_horarios);

        jMenuBar1.add(menu_prestadores);

        menu_ordenes.setText("Ordenes");

        item_ordenes.setText("Formulario de ordenes");
        item_ordenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ordenesActionPerformed(evt);
            }
        });
        menu_ordenes.add(item_ordenes);

        jMenuBar1.add(menu_ordenes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
    }// </editor-fold>                        

    private void item_salirActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        System.exit(WIDTH);
    }                                          

    private void item_afiliadosActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        escritorio.removeAll();
        escritorio.repaint();

        Afiliado mod = new Afiliado();
        AfiliadoData modC = new AfiliadoData();
        frmAfiliado frm = new frmAfiliado();
        ControlAfiliado ctrl = new ControlAfiliado(mod, modC, frm);
        frm.setVisible(true);

        escritorio.add(frm);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        frm.show();
        escritorio.moveToFront(frm);
    }                                              

    private void item_especialidadesActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        escritorio.removeAll();
        escritorio.repaint();

        Especialidad mod = new Especialidad();
        EspecialidadData modC = new EspecialidadData();
        frmEspecialidades frm = new frmEspecialidades();
        ControlEspecialidad0 ctrl = new ControlEspecialidad0(mod, modC, frm);
        frm.setVisible(true);

        escritorio.add(frm);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        frm.show();
        escritorio.moveToFront(frm);
    }                                                   

    private void item_prestadoresActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:                                          
        // TODO add your handling code here:
        escritorio.removeAll();
        escritorio.repaint();
        
        Prestador modE = new Prestador();
        PrestadorData modD = new PrestadorData();
        frmPrestador frm = new frmPrestador();
        ControlPrestador ctrl = new ControlPrestador(modE, modD, frm);
        frm.setVisible(true);
        
        escritorio.add(frm);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        frm.show();
        escritorio.moveToFront(frm);
    }                                                

    private void item_horariosActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        escritorio.removeAll();
        escritorio.repaint();
        Horario horaE = new Horario();
        HorarioData horaD = new HorarioData();
        //frmHorarios frm = new frmHorarios();
        viewHorario frm = new viewHorario();
        ControlHorarios ctrl = new ControlHorarios(horaE, horaD, frm);
        
        escritorio.add(frm);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        frm.show();
        escritorio.moveToFront(frm);
    }                                             

    private void item_ordenesActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        escritorio.removeAll();
        escritorio.repaint();

        Orden mod = new Orden();
        OrdenData modC = new OrdenData();
        frmOrden frm = new frmOrden();
        ControlOrden ctrl = new ControlOrden(mod, modC, frm);
        frm.setVisible(true);

        escritorio.add(frm);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        frm.show();
        escritorio.moveToFront(frm);
    }                                            

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuItem item_afiliados;
    private javax.swing.JMenuItem item_especialidades;
    private javax.swing.JMenuItem item_horarios;
    private javax.swing.JMenuItem item_ordenes;
    private javax.swing.JMenuItem item_prestadores;
    private javax.swing.JMenuItem item_salir;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menu_afiliados;
    private javax.swing.JMenu menu_archivo;
    private javax.swing.JMenu menu_especialidades;
    private javax.swing.JMenu menu_ordenes;
    private javax.swing.JMenu menu_prestadores;
    // End of variables declaration                   
}
