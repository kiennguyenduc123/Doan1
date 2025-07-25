/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.DichvuDao;
import DAO.KhuyenmaiDao;
import DTO.DichVuItem;
import DTO.Dichvu;
import DTO.Khuyenmai;
import Utils.dbConnect;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kiennguyen
 */
public class KhuyenMaiDvuPanel extends javax.swing.JPanel {
    
    private JComboBox<String> cbLoaiDichVu;
    private JComboBox<DichVuItem> cbTenDichVu;
    private JTextField txtGiaTri;
    private JTextField txtNgayBD, txtNgayKT, txtMagg;
    private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi;
    private JTable table;
    private DefaultTableModel model;
    
    private int currentUserId;
    private String currentUsername;
    
    DichVuItem dichvu;
    private DichvuDao dichVuDao;
    private KhuyenmaiDao kmDao;
    
    public KhuyenMaiDvuPanel(int userId, String username) {
        initComponents();
        dichvu = new DichVuItem();
        dichVuDao = new DichvuDao();
        kmDao = new KhuyenmaiDao();
        
        this.currentUserId = userId;
        this.currentUsername = username;
        
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Khuyến Mãi Dịch Vụ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Form bên trái
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
        
        cbLoaiDichVu = new JComboBox<>();
        cbTenDichVu = new JComboBox<>();
        txtGiaTri = new JTextField(15);
        txtNgayBD = new JTextField(15);
        txtNgayKT = new JTextField(15);
        txtMagg = new JTextField(15); // khai báo ở đầu class

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Mã giảm giá (magg):"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtMagg, gbc);
        
        for (String tenLoai : dichVuDao.getAllTenLoaiDichVu()) {
            cbLoaiDichVu.addItem(tenLoai);
        }
        
        cbLoaiDichVu.addActionListener(e -> loadDichVuTheoLoai());
        
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Loại dịch vụ:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cbLoaiDichVu, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Dịch vụ:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cbTenDichVu, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Giá trị:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtGiaTri, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Ngày bắt đầu:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNgayBD, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Ngày kết thúc:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNgayKT, gbc);
        
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnThemMoi = new JButton("Thêm mới");
        btnXoa = new JButton("Xóa");
        btnCapNhat = new JButton("Cập nhật");
        btnLamMoi = new JButton("Làm mới");
        btnPanel.add(btnThemMoi);
        btnPanel.add(btnCapNhat);
        btnPanel.add(btnXoa);
        btnPanel.add(btnLamMoi);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        formPanel.add(btnPanel, gbc);
        
        add(formPanel, BorderLayout.WEST);

        // Table bên phải
        model = new DefaultTableModel(new String[]{"Mã DV", "Dịch Vụ", "Ngày BD", "Ngày KT", "Giá Trị", "Người tạo"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        btnThemMoi.addActionListener(e -> insertKM());
        this.btnCapNhat.addActionListener(e -> updateKM());
        this.btnXoa.addActionListener(e -> deleteKM());
        btnLamMoi.addActionListener(e -> clearForm());
        
        loadDichVuTheoLoai(); // Khởi tạo dữ liệu ban đầu
        loadTable();
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String maDV = table.getValueAt(selectedRow, 0).toString();
                    
                    for (int i = 0; i < cbTenDichVu.getItemCount(); i++) {
                        DichVuItem item = cbTenDichVu.getItemAt(i);
                        if (item.getMaDv().equals(maDV)) {
                            cbTenDichVu.setSelectedIndex(i);
                            break;
                        }
                    }
                    
                    DichVuItem selectedItem = (DichVuItem) cbTenDichVu.getSelectedItem();
                    if (selectedItem != null) {
                        int idDv = selectedItem.getId();
                        txtMagg.setText(kmDao.getMaggByMaDVAndDate(
                                idDv,
                                java.sql.Date.valueOf(table.getValueAt(selectedRow, 2).toString()),
                                java.sql.Date.valueOf(table.getValueAt(selectedRow, 3).toString())
                        ));
                    }
                    
                    txtGiaTri.setText(table.getValueAt(selectedRow, 4).toString());
                    txtNgayBD.setText(table.getValueAt(selectedRow, 2).toString());
                    txtNgayKT.setText(table.getValueAt(selectedRow, 3).toString());
                }
            }
        });
    }

    private void loadDichVuTheoLoai() {
        cbTenDichVu.removeAllItems();
        String selectedLoai = (String) cbLoaiDichVu.getSelectedItem();
        if (selectedLoai != null) {
            for (Dichvu dv : dichVuDao.getByTenLoai(selectedLoai)) {
                String display = dv.getTendichvu(); // tên hiển thị
                cbTenDichVu.addItem(new DichVuItem(dv.getId(), dv.getMaDichvu(), display)); // add đối tượng riêng
            }
        }
    }
    
    private void loadTable() {
        model.setRowCount(0); // clear table
        for (var km : kmDao.getAll()) {
            model.addRow(new Object[]{
                km.getMaDv(),
                km.getTenDv(),
                km.getNgaybdau(),
                km.getNgaykthuc(),
                km.getGiatri(),
                km.getVaitro()
            });
        }
    }
    
    private void insertKM() {
        try {
            DichVuItem selected = (DichVuItem) cbTenDichVu.getSelectedItem();
            if (selected == null) {
                throw new Exception("Vui lòng chọn dịch vụ");
            }
            
            String magg = txtMagg.getText().trim();
            if (magg.isEmpty()) {
                throw new Exception("Vui lòng nhập mã giảm giá (magg)");
            }
            
            int idDichVu = selected.getId(); // ✅ lấy id thật, không cần parse chuỗi

            double giaTri = Double.parseDouble(txtGiaTri.getText().trim());
            java.sql.Date ngayBD = java.sql.Date.valueOf(txtNgayBD.getText().trim());
            java.sql.Date ngayKT = java.sql.Date.valueOf(txtNgayKT.getText().trim());
            
            Khuyenmai km = new Khuyenmai();
            km.setIdDvu(idDichVu);
            km.setGiatri(giaTri);
            km.setNgaybdau(ngayBD);
            km.setNgaykthuc(ngayKT);
            km.setMaNV(currentUserId);
            km.setMagg(magg); // ← quan trọng
            if (kmDao.insertKhuyenMaiDV(km)) {
                loadTable();
                clearForm();
                JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu: " + ex.getMessage());
        }
    }
    
    private void updateKM() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần cập nhật!");
            return;
        }
        
        try {
            DichVuItem selected = (DichVuItem) cbTenDichVu.getSelectedItem();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dịch vụ!");
                return;
            }
            
            String maDv = selected.getMaDv(); // ✅ Lấy mã DV đúng dạng "MDV05"
            double giaTri = Double.parseDouble(txtGiaTri.getText().trim());
            java.sql.Date ngayBD = java.sql.Date.valueOf(txtNgayBD.getText().trim());
            java.sql.Date ngayKT = java.sql.Date.valueOf(txtNgayKT.getText().trim());
            
            int idDvu = selected.getId();  // Lấy ID số nguyên

            // Tìm idKM từ ma_dv chuỗi
            String magg = txtMagg.getText().trim();
            int idKM = kmDao.getIdByMaDV(magg);
            if (idKM == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khuyến mãi để cập nhật!");
                return;
            }
            
            Khuyenmai km = new Khuyenmai();
            km.setId(idKM);            // ✅ BỔ SUNG DÒNG NÀY
            km.setIdDvu(idDvu);        // Lấy đúng id dịch vụ (int)
            km.setMaDv(maDv);          // Gán mã dịch vụ (string)
            km.setGiatri(giaTri);
            km.setNgaybdau(ngayBD);
            km.setNgaykthuc(ngayKT);
            km.setMaNV(currentUserId); // Gán mã nhân viên

            System.out.println("ID DV: " + idDvu);
            System.out.println("ID KM: " + idKM);
            if (kmDao.updateKhuyenMaiDV(km)) {
                loadTable();
                clearForm();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi cập nhật: " + ex.getMessage());
        }
    }
    
    private void deleteKM() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xoá!");
            return;
        }
        
        try {
            String maDvStr = model.getValueAt(row, 0).toString();
            java.sql.Date ngayBD = java.sql.Date.valueOf(model.getValueAt(row, 2).toString());
            java.sql.Date ngayKT = java.sql.Date.valueOf(model.getValueAt(row, 3).toString());

//           // ✅ Lấy đúng ID dịch vụ từ mã dịch vụ
            int idDichVu = kmDao.getIdDichVuByMaDv(maDvStr);
            if (idDichVu == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dịch vụ tương ứng để xoá!");
                return;
            }
            
            int idKM = kmDao.getIdByMaDV(idDichVu, ngayBD, ngayKT);
            if (idKM == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khuyến mãi để xoá!");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá khuyến mãi này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (kmDao.deleteKhuyenMaiDV(idKM)) {
                    loadTable();
                    clearForm();
                    JOptionPane.showMessageDialog(this, "Xoá thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá thất bại!");
                }
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi xoá: " + ex.getMessage());
        }
    }
    
    private void clearForm() {
        cbLoaiDichVu.setSelectedIndex(0);
        txtGiaTri.setText("");
        txtNgayBD.setText("");
        txtNgayKT.setText("");
        cbTenDichVu.setSelectedIndex(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
