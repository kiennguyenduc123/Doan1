/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.DichvuDao;
import DAO.NhaCCDao;
import DAO.PhieunhapkhoDao;
import DTO.Dichvu;
import DTO.NhaCC;
import DTO.Phieunhapkho;
import DTO.Session;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kiennguyen
 */
public class NhapkhoFrame extends javax.swing.JFrame {

    private JTextField txtMaPhieu, txtNgayNhap, txtSoLuong, txtGiaNhap, txtNgaySX, txtNgayHH, txtTongTien;
    private JTextArea txtGhiChu;
    private JDateChooser dateNgaySX;
    private JComboBox<NhaCC> cbNhaCC;
    private JComboBox<Dichvu> cbDichVu;
    private JTable table;
    private DefaultTableModel model;

    public PhieunhapkhoDao dao;
    public NhaCCDao nccDao;
    public DichvuDao dvDao;

    private int userId;
    private quanlyKhoPanel parentPanel;
    public NhapkhoFrame(int userId, quanlyKhoPanel parentPanel) {
        initComponents();
        this.userId = userId;
        this.parentPanel = parentPanel;
        dao = new PhieunhapkhoDao();
        nccDao = new NhaCCDao();
        dvDao = new DichvuDao();

        setTitle("Phiếu Nhập Dịch Vụ Vào Kho");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===================== THÔNG TIN PHIẾU NHẬP =====================
        JPanel panelThongTin = new JPanel(new GridBagLayout());
        panelThongTin.setBorder(BorderFactory.createTitledBorder("Thông Tin Phiếu Nhập"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Hàng 1: Mã phiếu và Ngày nhập
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelThongTin.add(new JLabel("Mã phiếu nhập:"), gbc);

        gbc.gridx = 1;
        txtMaPhieu = new JTextField(20);
        txtMaPhieu.setEnabled(false);
        panelThongTin.add(txtMaPhieu, gbc);

        gbc.gridx = 2;
        panelThongTin.add(new JLabel("Ngày Nhập:"), gbc);

        gbc.gridx = 3;
        txtNgayNhap = new JTextField(20);
        txtNgayNhap.setEnabled(false);
        panelThongTin.add(txtNgayNhap, gbc);

        // Hàng 2: Nhà cung cấp
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelThongTin.add(new JLabel("Nhà Cung Cấp:"), gbc);

        gbc.gridx = 1;
        cbNhaCC = new JComboBox<>();
        loadComboBoxNhaCC();
        panelThongTin.add(cbNhaCC, gbc);

        // Hàng 3: Ghi chú
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelThongTin.add(new JLabel("Ghi Chú:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        txtGhiChu = new JTextArea(3, 40);
        panelThongTin.add(new JScrollPane(txtGhiChu), gbc);
        gbc.gridwidth = 1;

        // Hàng 4: Tổng tiền
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelThongTin.add(new JLabel("Tổng Tiền:"), gbc);

        gbc.gridx = 1;
        txtTongTien = new JTextField("0", 20);
        txtTongTien.setEnabled(false);
        panelThongTin.add(txtTongTien, gbc);

        // ===================== CHỌN DỊCH VỤ =====================
        JPanel panelDichVu = new JPanel(new GridLayout(2, 5, 10, 10));
        panelDichVu.setBorder(BorderFactory.createTitledBorder("Chọn Dịch Vụ"));

        cbDichVu = new JComboBox<>();
        try {
            loadComboBoxDichVu();
        } catch (SQLException ex) {
            Logger.getLogger(NhapkhoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtSoLuong = new JTextField();
        txtGiaNhap = new JTextField();
//        txtNgaySX = new JTextField();
        dateNgaySX = new JDateChooser();
        dateNgaySX.setDateFormatString("yyyy-MM-dd");

        JButton btnThem = new JButton("Thêm Dịch Vụ");
        JButton btnXoa = new JButton("Xóa Dịch Vụ");

        btnThem.addActionListener(e -> themDichvu());
        btnXoa.addActionListener(e -> {
            try {
                xoaDichvu();
            } catch (SQLException ex) {
                Logger.getLogger(NhapkhoFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NhapkhoFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        panelDichVu.add(new JLabel("Chọn Dịch Vụ:"));
        panelDichVu.add(cbDichVu);
        panelDichVu.add(new JLabel("Số Lượng:"));
        panelDichVu.add(txtSoLuong);
        panelDichVu.add(btnThem);

        panelDichVu.add(new JLabel("Giá Nhập:"));
        panelDichVu.add(txtGiaNhap);
        panelDichVu.add(new JLabel("Ngày Sản Xuất:"));
        panelDichVu.add(dateNgaySX);
        panelDichVu.add(btnXoa);

        // ===================== BẢNG DANH SÁCH =====================
        String[] cols = {"Mã Dịch Vụ", "Số Lượng", "Giá Nhập", "Ngày SX", "Tổng Tiền"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách Dịch Vụ"));

        // ===================== NÚT BÊN PHẢI =====================
        JPanel panelNut = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnTaoPhieu = new JButton("TẠO PHIẾU");
        JButton btnHoanTat = new JButton("Hoàn tất");
        JButton btnHuy = new JButton("Hủy");

        btnTaoPhieu.setBackground(new Color(0, 102, 102));
        btnHoanTat.setBackground(new Color(0, 102, 102));
        btnHuy.setBackground(Color.RED);

        btnTaoPhieu.setForeground(Color.WHITE);
        btnHoanTat.setForeground(Color.WHITE);
        btnHuy.setForeground(Color.WHITE);

        panelNut.add(btnTaoPhieu);
        panelNut.add(btnHoanTat);
        panelNut.add(btnHuy);

        // ===================== ACTION TẠO PHIẾU =====================
        btnTaoPhieu.addActionListener(e -> {
            txtMaPhieu.setText(dao.getNextMaPhieu());
            txtNgayNhap.setText(LocalDate.now().toString());
        });

        btnHuy.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Hủy thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dispose();  // Đóng cửa sổ hiện tại
        });
        
        btnHoanTat.addActionListener(e -> {
            if (parentPanel != null) {
                parentPanel.loadPhieu();  // Gọi lại load danh sách
            }
            dispose();  // Đóng form
        });
        

        // ===================== ADD TO FRAME =====================
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(panelDichVu, BorderLayout.NORTH);
        centerPanel.add(scroll, BorderLayout.CENTER);

        add(panelThongTin, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(panelNut, BorderLayout.EAST);
    }

    private void loadComboBoxNhaCC() {
        List<NhaCC> list = nccDao.getAllNhaCC();
        DefaultComboBoxModel<NhaCC> model = new DefaultComboBoxModel<>();
        for (NhaCC ncc : list) {
            model.addElement(ncc);
        }
        cbNhaCC.setModel(model);
    }
    
     public void loadPhieu() {
        model.setRowCount(0);
        List<Phieunhapkho> phieunhapList = dao.getAllPhieu();
        for (Phieunhapkho pn : phieunhapList) {
            model.addRow(new Object[]{
                pn.getMaphieuNhap(),
                pn.getTenNhacc(),
                pn.getNgayNhap(),
                pn.getTongtien(),
                pn.getVaiTro(),
                pn.getGhiChu()
            });
        }
    }

    private void loadComboBoxDichVu() throws SQLException {
        List<Dichvu> list = dvDao.getAllDichvu();
        DefaultComboBoxModel<Dichvu> model = new DefaultComboBoxModel<>();
        for (Dichvu dv : list) {
            model.addElement(dv);
        }
        cbDichVu.setModel(model);
    }

    private void loadBang() {
        try {
            model.setRowCount(0);
            List<Phieunhapkho> list = dao.getByMaPhieu(txtMaPhieu.getText());
            for (Phieunhapkho k : list) {
                String maDV = dvDao.getMaById(k.getDichvu_id()); // cần viết hàm này trong DichvuDao
                double tong = k.getGiaNhap() * k.getSoLuong();
                model.addRow(new Object[]{maDV, k.getSoLuong(), k.getGiaNhap(), k.getNgayNhap(), tong});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void themDichvu() {
        try {
            Phieunhapkho obj = new Phieunhapkho();
            obj.setMaphieuNhap(txtMaPhieu.getText());
            obj.setDichvu_id(((Dichvu) cbDichVu.getSelectedItem()).getId());
            obj.setUser_id(this.userId); // tạm thời
            obj.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
            obj.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
            String ngayNhapStr = txtNgayNhap.getText();
            java.sql.Date ngayNhap = java.sql.Date.valueOf(ngayNhapStr);
            obj.setNgayNhap(ngayNhap);
            obj.setGhiChu(txtGhiChu.getText());
            obj.setNhacc_id(((NhaCC) cbNhaCC.getSelectedItem()).getId());
            
            System.out.println("Session.id: " + Session.id);

            if (dao.insert(obj)) {
                JOptionPane.showMessageDialog(this, "Thêm dịch vụ thành công!");
                loadBang();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
            }

            double tongTienHienTai = Double.parseDouble(txtTongTien.getText());
            double tongTienMoi = tongTienHienTai + (obj.getGiaNhap() * obj.getSoLuong());
            txtTongTien.setText(String.valueOf(tongTienMoi));
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi thêm: " + ex.getMessage());
        }
    }

    private void xoaDichvu() throws SQLException, ClassNotFoundException {
        int row = table.getSelectedRow();
        if (row == -1) {
            return;
        }

        String maPhieu = txtMaPhieu.getText();
        String maDV = model.getValueAt(row, 0).toString();
        int dichVuId = dvDao.getIdByMa(maDV);

        try {
            if (dao.delete(maPhieu, dichVuId)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadBang();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 945, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 631, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
