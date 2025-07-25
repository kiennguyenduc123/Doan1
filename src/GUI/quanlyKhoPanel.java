/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.PhieunhapkhoDao;
import DTO.Phieunhapkho;
import DTO.Session;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class quanlyKhoPanel extends javax.swing.JPanel {

    List<Phieunhapkho> phieunhapList = new ArrayList<>();
    DefaultTableModel tableModel;
    
    PhieunhapkhoDao dao;
    
    public quanlyKhoPanel() {
        initComponents();
        tableModel = (DefaultTableModel) this.tablePhieunhap.getModel();
        dao = new PhieunhapkhoDao();
        loadPhieu();
    }
    
    public void loadPhieu() {
        tableModel.setRowCount(0);
        phieunhapList = dao.getAllPhieu();
        for(Phieunhapkho pn : phieunhapList) {
            tableModel.addRow(new Object[] {
                pn.getMaphieuNhap(),
                pn.getTenNhacc(),
                pn.getNgayNhap(),
                pn.getTongtien(),
                pn.getVaiTro(),
                pn.getGhiChu()
            });
        }
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cbLocDate = new javax.swing.JComboBox<>();
        btnNhapSanPham = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnTimkiem = new javax.swing.JButton();
        txtFormattedTu = new javax.swing.JFormattedTextField();
        txtFormattedDen = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePhieunhap = new javax.swing.JTable();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Nhập Kho");

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(255, 0, 153)));

        cbLocDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbLocDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "1 Ngày trước ", "2 Ngày trước", " " }));
        cbLocDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocDateActionPerformed(evt);
            }
        });

        btnNhapSanPham.setBackground(new java.awt.Color(0, 102, 102));
        btnNhapSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhapSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapSanPham.setText("Nhập Dịch Vụ");
        btnNhapSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapSanPhamActionPerformed(evt);
            }
        });

        btnTimkiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimkiem.setForeground(new java.awt.Color(0, 102, 102));
        btnTimkiem.setText("Tìm Kiếm");
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        txtFormattedTu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFormattedTuActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 102));
        jButton2.setText("LỌC");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Từ ngày");

        jLabel3.setText("Đến ngày");

        jLabel4.setBackground(new java.awt.Color(0, 102, 102));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Phiếu Nhập Kho");
        jLabel4.setOpaque(true);

        tablePhieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Phiếu", "Nhà cung cấp", "Ngày nhập", "Tổng tiền", "Tên nhân viên", "Ghi chú"
            }
        ));
        jScrollPane1.setViewportView(tablePhieunhap);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbLocDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNhapSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btnTimkiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtFormattedTu, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtFormattedDen, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jButton2))
                            .addComponent(jLabel3))
                        .addGap(125, 125, 125))
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLocDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFormattedTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFormattedDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(46, 46, 46)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtFormattedTuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormattedTuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormattedTuActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
      String keyword = this.txtTimKiem.getText().trim();
    
    // Nếu không nhập từ khóa thì load lại danh sách
    if (keyword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mời bạn nhập từ khóa tìm kiếm!");
        loadPhieu();  // Load toàn bộ phiếu nhập
        return;
    }

    // Gọi hàm tìm kiếm nâng cao
    List<Phieunhapkho> searchResults = dao.searchPhieuNhapAdvanced(keyword);

    // Kiểm tra kết quả
    if (searchResults == null || searchResults.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        loadPhieu();  // Load lại toàn bộ dữ liệu khi không tìm thấy
        return;
    }

    // Xóa dữ liệu cũ trên table
    tableModel.setRowCount(0);

    // Đổ dữ liệu mới vào bảng
    for (Phieunhapkho pn : searchResults) {
        tableModel.addRow(new Object[]{
            pn.getMaphieuNhap(),
            pn.getTenNhacc(),
            pn.getNgayNhap(),
            pn.getGiaNhap() * pn.getSoLuong(),  // Nếu không có getTongtien()
            pn.getVaiTro(),
            pn.getGhiChu()
        });
    }

       
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void cbLocDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocDateActionPerformed
        
        String selectOption = (String) this.cbLocDate.getSelectedItem();
        int dayAgo = 0;

        if (selectOption.equals("Hôm nay")) {
            dayAgo = 0;
        } else if (selectOption.contains("Ngày trước")) {
            try {
                dayAgo = Integer.parseInt(selectOption.split(" ")[0]);
            } catch (NumberFormatException e) {
                System.out.println("Lỗi parse ngày: " + e.getMessage());
                return;
            }
        }

        ZoneId zoneVN = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDate today = LocalDate.now(zoneVN);

        LocalDate targetDate = today.minusDays(dayAgo);

        System.out.println("➡️ Today (VN): " + today);
        System.out.println("➡️ Target filter date: " + targetDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String filterDate = targetDate.format(formatter);

        System.out.println("Hôm nay theo hệ thống: " + LocalDate.now());

        System.out.println(" Lọc theo ngày: " + filterDate);
        
        List<Phieunhapkho> filterList = dao.filterbyNgay(targetDate);
        tableModel.setRowCount(0);
        for (Phieunhapkho pn : filterList) {
            Object[] row = new Object[]{
               pn.getMaphieuNhap(),
                pn.getTenNhacc(),
                pn.getNgayNhap(),
                pn.getTongtien(), 
                pn.getVaiTro(),
                pn.getGhiChu()
            };
            tableModel.addRow(row);
        }
    }//GEN-LAST:event_cbLocDateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
          String ngayBatDauStr = this.txtFormattedTu.getText().trim();
        String ngayKetThucStr = this.txtFormattedDen.getText().trim();

        if (ngayBatDauStr.isEmpty() || ngayKetThucStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ ngày bắt đầu và ngày kết thúc");
            return;
        }

//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate ngayBatDau;
        LocalDate ngayKetThuc;

        try {
            ngayBatDau = LocalDate.parse(ngayBatDauStr, inputFormatter);
            ngayKetThuc = LocalDate.parse(ngayKetThucStr, inputFormatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy");
            return;
        }

        if (ngayKetThuc.isBefore(ngayBatDau)) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau hoặc bằng ngày bắt đầu.");
            return;
        }
        
        List<Phieunhapkho> filterList = dao.filterByDateRange(ngayBatDau, ngayKetThuc);

        // Clear bảng cũ
        tableModel.setRowCount(0);

        for (Phieunhapkho pn : filterList) {
            Object[] row = new Object[]{
                pn.getMaphieuNhap(),
                pn.getTenNhacc(),
                pn.getNgayNhap(),
                pn.getTongtien(), 
                pn.getVaiTro(),
                pn.getGhiChu()
            };
            tableModel.addRow(row);
        }

        System.out.println("🔍 Lọc từ " + ngayBatDau + " đến " + ngayKetThuc + " → " + filterList.size() + " kết quả");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnNhapSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapSanPhamActionPerformed
        int userId = Session.id; // hoặc lấy từ user đang login
        NhapkhoFrame f = new NhapkhoFrame(userId, this);
        f.setVisible(true);
    }//GEN-LAST:event_btnNhapSanPhamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNhapSanPham;
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JComboBox<String> cbLocDate;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablePhieunhap;
    private javax.swing.JFormattedTextField txtFormattedDen;
    private javax.swing.JFormattedTextField txtFormattedTu;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
