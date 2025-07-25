/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.ChiTietHoaDonDao;
import static DAO.ChiTietHoaDonDao.getIdHoaDonByMa;
import DAO.HoadonDao;
import DTO.Chitiethoadon;
import DTO.Hoadon;
import DTO.Session;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kiennguyen
 */
public class quanlyHoaDonPanel extends javax.swing.JPanel {

    
    DefaultTableModel tableModel;
    
    HoadonDao dao;
    
    
    ChiTietHoaDonDao chiTietDao;
    List<Hoadon> hdList = new ArrayList<>();

    
    
    public quanlyHoaDonPanel() {
        initComponents();
        dao = new HoadonDao();
        chiTietDao = new ChiTietHoaDonDao(); // ✅ THÊM DÒNG NÀY
        tableModel = (DefaultTableModel) this.tableHoadon.getModel();
        loadHoadon();
        
    }

    public void loadHoadon() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        hdList = dao.getAllHoadon(); // Lấy danh sách hoá đơn

        for (Hoadon hd : hdList) {
            Object[] row = new Object[]{
                hd.getMaHd(), // Mã HĐ
                hd.getNgayttoan(), // Ngày lập
                hd.getMaKH(), // Mã khách hàng (đúng rồi)
                hd.getTongTien(), // Tổng tiền
//                hd.getIdUser(), // ID thu ngân
                hd.getVaiTro(), // Vai trò thu ngân
                hd.getMagg(), // Mã giảm giá
                hd.getDiemDadung() // Điểm đã dùn
            };
            tableModel.addRow(row);
        }
    }
    
 private void loadChiTietHoaDon(String maHD) {
        DefaultTableModel model = (DefaultTableModel) tableChitietHD.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        List<Chitiethoadon> ctList = chiTietDao.getChiTietHoaDonById(maHD);
        for (Chitiethoadon ct : ctList) {
            Object[] row = new Object[]{
                ct.getMa_dich_vu(),
                ct.getTen_dich_vu(),
                ct.getDonGia(),
                ct.getSoLuong(),
                ct.getTongTien()
            };
            model.addRow(row);

         
        }
    }
    
    public void reloadData() {
        loadHoadon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        FormatterNgayBdau = new javax.swing.JFormattedTextField();
        FormatterNgayKthuc = new javax.swing.JFormattedTextField();
        btnLoc = new javax.swing.JButton();
        cbLocDate = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoadon = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableChitietHD = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Hoá Đơn Bán Lẻ");

        jLabel2.setText("Mã HD");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(51, 153, 255));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("TÌM KIẾM");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jLabel3.setText("Ngày BĐ");

        jLabel4.setText("Ngày KT");

        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc.setForeground(new java.awt.Color(0, 102, 102));
        btnLoc.setText("LỌC");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        cbLocDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "1 Ngày trước", "2 Ngày trước", "" }));
        cbLocDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocDateActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 102, 102));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DANH SÁCH HOÁ ĐƠN");
        jLabel5.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        tableHoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ HĐ", "Ngày Lập", "MÃ KH", "Tổng Tiền", "Thu Ngân", "Mã GG", "Điểm SD"
            }
        ));
        tableHoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHoadon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CHI TIẾT HOÁ ĐƠN");
        jLabel6.setOpaque(true);

        tableChitietHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Dịch Vụ", "Tên Dịch Vụ", "Giá bán ", "Số Lượng", "Tổng tiền"
            }
        ));
        jScrollPane3.setViewportView(tableChitietHD);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(btnTimKiem)))
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(FormatterNgayBdau, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(FormatterNgayKthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 33, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnLoc)
                                .addGap(56, 56, 56)
                                .addComponent(cbLocDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FormatterNgayBdau)
                        .addComponent(FormatterNgayKthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiem)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbLocDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tableHoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoadonMouseClicked
        // TODO add your handling code here:
        int selectedRow = tableHoadon.getSelectedRow();
        if (selectedRow >= 0) {
            String maHDStr = tableHoadon.getValueAt(selectedRow, 0).toString();
            int idHoaDon = getIdHoaDonByMa(maHDStr);
            System.out.println("Mã hoá đơn : " + maHDStr);
            if (idHoaDon != -1) {
                loadChiTietHoaDon(String.valueOf(idHoaDon));
            } else {
                System.out.println("Không tìm thấy ID hóa đơn cho mã: " + maHDStr);
            }
        }
    }//GEN-LAST:event_tableHoadonMouseClicked

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

        
         List<Hoadon> filterList = dao.filterbyNgay(targetDate);
        tableModel.setRowCount(0);
        for (Hoadon hd : filterList) {
            Object[] row = new Object[]{
                hd.getMaHd(),
                hd.getNgayttoan(),
                hd.getMaKH(),
                hd.getTongTien(),
                hd.getVaiTro(),
                hd.getMagg(),
                hd.getDiemDadung()
            };
            tableModel.addRow(row);
        }
    }//GEN-LAST:event_cbLocDateActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        String ngayBatDauStr = this.FormatterNgayBdau.getText().trim();
        String ngayKetThucStr = this.FormatterNgayKthuc.getText().trim();

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

        // Gọi DAO để lọc
        List<Hoadon> filterList = dao.filterByDateRange(ngayBatDau, ngayKetThuc);

        // Clear bảng cũ
        tableModel.setRowCount(0);

        for (Hoadon hd : filterList) {
            Object[] row = new Object[]{
                hd.getMaHd(),
                hd.getNgayttoan(),
                hd.getMaKH(),
                hd.getTongTien(),
                hd.getVaiTro(),
                hd.getMagg(),
                hd.getDiemDadung()
            };
            tableModel.addRow(row);
        }

        System.out.println("🔍 Lọc từ " + ngayBatDau + " đến " + ngayKetThuc + " → " + filterList.size() + " kết quả");
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String mahd = this.txtTimKiem.getText().trim();
        
        if(mahd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ mã hoá đơn");
            this.loadHoadon();
            return;
        }
        
       List<Hoadon> searchss = dao.search(mahd);

        // Clear bảng cũ
        tableModel.setRowCount(0);

        for (Hoadon hd : searchss) {
            Object[] row = new Object[]{
                hd.getMaHd(),
                hd.getNgayttoan(),
                hd.getMaKH(),
                hd.getTongTien(),
                hd.getVaiTro(),
                hd.getMagg(),
                hd.getDiemDadung()
            };
            tableModel.addRow(row);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField FormatterNgayBdau;
    private javax.swing.JFormattedTextField FormatterNgayKthuc;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cbLocDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableChitietHD;
    private javax.swing.JTable tableHoadon;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
