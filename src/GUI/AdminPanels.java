/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Utils.dbConnect;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminPanels extends javax.swing.JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    public AdminPanels() throws ClassNotFoundException, SQLException {
        initComponents();

        setTitle("Unique Developer - Admin Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top bar
        TopBarPanel topBar = new TopBarPanel();
        add(topBar, BorderLayout.NORTH);

        // Content with sidebar and main panel
        JPanel centerPanel = new JPanel(new BorderLayout());

        SideMenuPanel sideMenu = new SideMenuPanel(this);
        contentPanel = new JPanel(new CardLayout());

        Connection con = dbConnect.dbConnection();
        // Add các panel con
        contentPanel.add(new TrangChuPanel(), "Trang Chủ");
        contentPanel.add(new GoiCuoiHoiPanel(con), "Quản Lý Dịch Vụ");
        contentPanel.add(new quanlyUser(), "Quản Lý Người Dùng");
        contentPanel.add(new lichdatPanel(), "Lịch đặt");
        contentPanel.add(new JPanel(), "Khuyễn Mãi" );
        contentPanel.add(new JPanel(), "Thống kê & báo cáo");
        contentPanel.add(new JPanel(), "Cài đặt hệ thống");
        contentPanel.add(new JPanel(), "Đăng xuất");
        centerPanel.add(sideMenu, BorderLayout.WEST);
        centerPanel.add(contentPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void showPanel(String name) {
        cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, name);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AdminPanels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPanels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPanels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPanels.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AdminPanels().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminPanels.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminPanels.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
