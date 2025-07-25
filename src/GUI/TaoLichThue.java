/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.KhuyenmaiDao;
import DAO.LichdatDao;
import DAO.UserDao;
import DTO.Chitietlichdat;
import DTO.Lichdat;
import DTO.Session;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kiennguyen
 */
public class TaoLichThue extends javax.swing.JFrame {

    private JTextField txtMaLich;
    private JSpinner spNgayDat, spNgaySuDung, spNgayTra;
    private JComboBox<String> cmbTinhTrang;
    private JLabel lblNhanVien;
    private JTextArea txtGhiChu;
    JTextField txtTenKhachHang ;
    
    private JTable tableLichThue;

    private DefaultTableModel modelLichThue;
   
    private LichdatDao lichdatDao;

    List<Lichdat> lichdatList = new ArrayList<>();
    
    private quanlyLichPanel parentPanel;
    
    public TaoLichThue(quanlyLichPanel parentPanel) {
        initComponents();
        this.parentPanel = parentPanel;
        lichdatDao = new LichdatDao();
        setTitle("Tạo Lịch Thuê");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Dòng 1: Mã lịch
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Mã lịch:"), gbc);
        txtMaLich = new JTextField(); gbc.gridx = 1;
        panel.add(txtMaLich, gbc); y++;

        // Dòng 2: Khách hàng
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Khách hàng:"), gbc);
        txtTenKhachHang = new JTextField(); gbc.gridx = 1;
        panel.add(txtTenKhachHang, gbc); y++;

        // Dòng 3: Ngày đặt
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Ngày đặt:"), gbc);
        spNgayDat = new JSpinner(new SpinnerDateModel()); gbc.gridx = 1;
        spNgayDat.setEditor(new JSpinner.DateEditor(spNgayDat, "dd/MM/yyyy HH:mm"));
        panel.add(spNgayDat, gbc); y++;

        // Dòng 4: Ngày sử dụng
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Ngày sử dụng:"), gbc);
        spNgaySuDung = new JSpinner(new SpinnerDateModel()); gbc.gridx = 1;
        spNgaySuDung.setEditor(new JSpinner.DateEditor(spNgaySuDung, "dd/MM/yyyy HH:mm"));
        panel.add(spNgaySuDung, gbc); y++;

        // Dòng 5: Ngày trả
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Ngày trả:"), gbc);
        spNgayTra = new JSpinner(new SpinnerDateModel()); gbc.gridx = 1;
        spNgayTra.setEditor(new JSpinner.DateEditor(spNgayTra, "dd/MM/yyyy HH:mm"));
        panel.add(spNgayTra, gbc); y++;

        // Dòng 6: Nhân viên (vai trò hiển thị sẵn)
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Nhân viên:"), gbc);
        lblNhanVien = new JLabel(Session.vaiTro); gbc.gridx = 1;
        panel.add(lblNhanVien, gbc); y++;

        // Dòng 7: Tình trạng
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Tình trạng:"), gbc);
        cmbTinhTrang = new JComboBox<>(new String[]{"đã đặt", "đã hoàn thành", "đã hủy"}); gbc.gridx = 1;
        panel.add(cmbTinhTrang, gbc); y++;

        // Dòng 8: Ghi chú
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Ghi chú:"), gbc);
        txtGhiChu = new JTextArea(3, 20); gbc.gridx = 1;
        panel.add(new JScrollPane(txtGhiChu), gbc); y++;

        // Nút tạo
        JButton btnTao = new JButton("Tạo");
        btnTao.setBackground(new Color(70, 130, 180));
        btnTao.setForeground(Color.white);
        btnTao.setFocusPainted(false);
        
        btnTao.addActionListener(e -> {
            try {
                insertLichThue();
            } catch (SQLException ex) {
                Logger.getLogger(TaoLichThue.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TaoLichThue.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        panel.add(btnTao, gbc);

        add(panel, BorderLayout.CENTER);
    
    }
    
     

     private void insertLichThue() throws SQLException, ClassNotFoundException {
         String tenKH = txtTenKhachHang.getText().trim();
         int idKhachHang = lichdatDao.getKhachHangIdByTen(tenKH);
         
         
        if (idKhachHang == -1) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Lichdat ld = new Lichdat();
        ld.setMaLichThue(this.txtMaLich.getText());
        ld.setIdKhachHang(idKhachHang);
        ld.setNgayDat((Date) spNgayDat.getValue());
        ld.setNgaySuDung((Date) spNgaySuDung.getValue());
        ld.setNgayTra((Date) spNgayTra.getValue());
        ld.setNhanVienId(Session.id); // bạn phải lấy từ phiên đăng nhập
        ld.setTinhTrang(cmbTinhTrang.getSelectedItem().toString());
        ld.setGhiChu(txtGhiChu.getText());
        
         boolean success = new LichdatDao().InsertLichdat(ld);
         if (success) {
             JOptionPane.showMessageDialog(this, "Tạo lịch thuê thành công!");
             if (parentPanel != null) {
                 parentPanel.loadLichThue(); // <- gọi về bảng gốc
             }
             dispose(); // đóng form sau khi tạo
         } else {
             JOptionPane.showMessageDialog(this, "Tạo thất bại!");
         }
//        JOptionPane.showMessageDialog(this, success ? "Tạo lịch thuê thành công!" : "Tạo thất bại!");
    }
     
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 857, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(TaoLichThue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaoLichThue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaoLichThue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaoLichThue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                lichdatPanel lichPanelCha = new lichdatPanel();
                
                quanlyLichPanel lichthuePanel = new quanlyLichPanel(lichPanelCha);
                
                new TaoLichThue(lichthuePanel).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
