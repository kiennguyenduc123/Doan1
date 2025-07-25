/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DTO.Session;
import Utils.dbConnect;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminPanels extends javax.swing.JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    private int currentUserId;
    private String currentUsername;

    
    JButton btnTrangChu, btnQuanLyDichVu, btnLichDat, btnKhuyenMai;
    JButton btnQuanLyUser, btnQuanLyNhanVien, btnNhapKho, btnThietLap;
    
    public AdminPanels(int userId, String username) throws ClassNotFoundException, SQLException {
        initComponents();
        this.currentUserId = userId;
        this.currentUsername = username;

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
        // Panel dùng chung cho cả admin và nhân viên
        contentPanel.add(new GoiCuoiHoiPanel(con), "Quản lý dịch vụ");
        contentPanel.add(new lichdatPanel(), "Lịch thuê");
        contentPanel.add(new KhuyenMaiMainPanel(currentUserId, currentUsername), "Khuyễn mãi");

// Phân quyền riêng cho admin
        if (Session.vaiTro.equalsIgnoreCase("admin")) {
            contentPanel.add(new quanlyUser(), "Quản lý người dùng");
            contentPanel.add(new quanlyNhanvien(), "Quản lý nhân viên");
            contentPanel.add(new quanlyKhoPanel(), "Nhập kho");
            contentPanel.add(new ThietLapPanel(), "Thiết lập");
            contentPanel.add(new TrangChuPanel(), "Thống kê");
        }

// Đăng xuất luôn có
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
