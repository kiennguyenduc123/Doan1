/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.DichvuDao;
import DAO.KhuyenmaiDao;
import DAO.LichdatDao;
import DAO.UserDao;
import DTO.Chitietlichdat;
import DTO.Khuyenmai;
import DTO.Lichdat;
import DTO.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class lichdatPanel extends javax.swing.JPanel {

   

    private LichdatDao dao;
    
    private quanlyLichPanel panelLichThue;
    private quanlyHoaDonPanel panelHoaDon;
    private KhuyenMaiPanel panelKhuyenmai;
    private KhuyenMaiMainPanel khuyenMaiMainPanel;
    public lichdatPanel() {
        initComponents();

        dao = new LichdatDao();

        setLayout(new BorderLayout());

        // Khởi tạo Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Tab 1: Lịch Thuê
        panelLichThue = new quanlyLichPanel(this);

        tabbedPane.addTab("📅 Quản lý lịch thuê", panelLichThue);

        // Tab 2: Hóa Đơn
        panelHoaDon = new quanlyHoaDonPanel();
        tabbedPane.addTab(" Quản lý hóa đơn", panelHoaDon);

        add(tabbedPane, BorderLayout.CENTER);

    }
 

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản Lý Cho Thuê");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        
        lichdatPanel rootPanel = new lichdatPanel();
        frame.add(rootPanel);

        frame.setVisible(true);
    }

    
     public quanlyLichPanel getPanelLichThue() {
        return panelLichThue;
    }

    public quanlyHoaDonPanel getPanelHoaDon() {
        return panelHoaDon;
    }
    
    public KhuyenMaiMainPanel getPanelKhuyenMai() {
        return this.khuyenMaiMainPanel;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1048, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
