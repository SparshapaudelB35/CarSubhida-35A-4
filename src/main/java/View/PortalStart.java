/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;


/**
 *
 * @author Acer
 */
public class PortalStart extends javax.swing.JFrame {

    /**
     * Creates new form PortalStart
     */
    public PortalStart() {
        initComponents();
    }   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        newadmin = new javax.swing.JButton();
        RegisteredAdmin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LOGO.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Magneto", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Who are you?");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 270, 110));

        jLabel1.setIcon(new FlatSVGIcon("1start.svg"));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 150, 70));

        newadmin.setFont(new java.awt.Font("Magneto", 0, 14));
        newadmin.setForeground(new java.awt.Color(255, 102, 102));
        newadmin.setIcon(new FlatSVGIcon("newuser2.svg "));
        newadmin.setText("New Admin");
        //newadmin.setToolTipText("Click here to get redirected into Registration page.");
        newadmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newadminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newadminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newadminMouseExited(evt);
            }
        });
        jPanel1.add(newadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 440, 230, 50));

        RegisteredAdmin.setFont(new java.awt.Font("Magneto", 0, 14)); // NOI18N
        RegisteredAdmin.setForeground(new java.awt.Color(255, 102, 102));
        RegisteredAdmin.setIcon(new FlatSVGIcon("reguser.svg "));
        RegisteredAdmin.setText("Registered Admin");
        //RegisteredAdmin.setToolTipText("Click here to get redirected to Login page.");
        RegisteredAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisteredAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RegisteredAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RegisteredAdminMouseExited(evt);
            }
        });
        RegisteredAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisteredAdminActionPerformed(evt);
            }
        });
        jPanel1.add(RegisteredAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 230, 50));

        jLabel5.setFont(new java.awt.Font("Magneto", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("CAR SUBHIDA");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 490, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1213, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void RegisteredAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisteredAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RegisteredAdminActionPerformed

    private void newadminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newadminMouseEntered
                                    
        newadmin.setBackground(new Color(204,204,255));
        
    }//GEN-LAST:event_newadminMouseEntered

    private void newadminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newadminMouseExited
        // TODO add your handling code here:
         newadmin.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_newadminMouseExited

    private void RegisteredAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisteredAdminMouseEntered
        RegisteredAdmin.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_RegisteredAdminMouseEntered

    private void RegisteredAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisteredAdminMouseExited
        // TODO add your handling code here:
        RegisteredAdmin.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_RegisteredAdminMouseExited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void RegisteredAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisteredAdminMouseClicked
        // TODO add your handling code here:
        Login log= new Login();
        log.setLocationRelativeTo(null);
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegisteredAdminMouseClicked

    private void newadminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newadminMouseClicked
        try {
            // TODO add your handling code here:
            Signup sp= new Signup();
            sp.setLocationRelativeTo(null);
            sp.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(PortalStart.class.getName()).log(Level.SEVERE, null, ex);
        }
                      
    }//GEN-LAST:event_newadminMouseClicked
                           
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
            java.util.logging.Logger.getLogger(PortalStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PortalStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PortalStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PortalStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PortalStart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RegisteredAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton newadmin;
    // End of variables declaration//GEN-END:variables
}
