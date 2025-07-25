/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.KhuyenmaiDao;
import DTO.Khuyenmai;
import Utils.dbConnect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class KhuyenMaiPanel extends javax.swing.JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaGG, txtSoLuong, txtNgayBD, txtNgayKT, txtApDung, txtGiaTri, txtVaiTro;
    private JRadioButton rdTien, rdPhanTram;
    private JButton btnThem, btnCapNhat, btnLamMoi;
    private ButtonGroup group;

    private String currentUsername;
    private int currentID;

    KhuyenmaiDao dao;
    List<Khuyenmai> KMaiList = new ArrayList<>();

    public KhuyenMaiPanel(int userId,  String username) {
        initComponents();
        dao = new KhuyenmaiDao();
        this.currentID = userId;
        this.currentUsername = username;

      setLayout(new BorderLayout());

        // Tiêu đề lớn ở trên cùng (trái lề)
        JLabel titleLabel = new JLabel("Khuyến Mãi Hóa Đơn");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 102, 102));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Khởi tạo bảng
        String[] columnNames = {"Mã GG", "Số Lượng", "Ngày Bắt Đầu", "Ngày Kết Thúc",
                "Áp dụng từ", "Giá Trị", "Hình thức", "Người tạo"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Label phía trên bảng
        JLabel tableLabel = new JLabel("Danh Sách Mã KM");
        tableLabel.setFont(new Font("Arial", Font.BOLD, 16));
        tableLabel.setForeground(Color.WHITE);
        tableLabel.setOpaque(true);
        tableLabel.setBackground(new Color(0, 102, 102));
        tableLabel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        // Panel Form nhập
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMaGG = new JTextField();
        txtSoLuong = new JTextField();
        txtNgayBD = new JTextField();
        txtNgayKT = new JTextField();
        txtApDung = new JTextField();
        txtGiaTri = new JTextField();
        rdTien = new JRadioButton("Theo Tiền");
        rdPhanTram = new JRadioButton("Theo %");
        group = new ButtonGroup();
        group.add(rdTien);
        group.add(rdPhanTram);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Mã GG:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtMaGG, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Số lượng:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtSoLuong, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Ngày bắt đầu (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNgayBD, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Ngày kết thúc (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNgayKT, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Áp dụng đơn từ:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtApDung, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Giá trị:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtGiaTri, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Hình thức:"), gbc);
        gbc.gridx = 1;
        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelRadio.setBackground(formPanel.getBackground());
        panelRadio.add(rdTien);
        panelRadio.add(rdPhanTram);
        formPanel.add(panelRadio, gbc);

        // Panel nút bên trong form panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.setBackground(formPanel.getBackground());
        btnThem = new JButton("Thêm mới");
        btnCapNhat = new JButton("Cập nhật");
        btnLamMoi = new JButton("Làm mới");
        btnPanel.add(btnThem);
        btnPanel.add(btnCapNhat);
        btnPanel.add(btnLamMoi);
        formPanel.add(btnPanel, gbc);

        // Panel trái chứa form
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        leftPanel.add(formPanel, BorderLayout.NORTH);

        // Panel phải chứa bảng và label
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        rightPanel.add(tableLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Cố định chiều cao hai panel bằng nhau bằng cách dùng JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerSize(8);
        splitPane.setContinuousLayout(true);
        splitPane.setBorder(null);
        add(splitPane, BorderLayout.CENTER);

        loadTable();
        
        this.btnThem.addActionListener(e -> insertKM());
        
        this.btnLamMoi.addActionListener(e -> resertKM());
        
        this.btnCapNhat.addActionListener(e -> updateKM());
        
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0 && row < KMaiList.size()) {
                Khuyenmai km = KMaiList.get(row);
                txtMaGG.setText(km.getMagg());
                txtSoLuong.setText(String.valueOf(km.getSoluong()));
                txtNgayBD.setText(String.valueOf(km.getNgaybdau()));
                txtNgayKT.setText(String.valueOf(km.getNgaykthuc()));
                txtApDung.setText(String.valueOf(km.getApdunghd()));
                txtGiaTri.setText(String.valueOf(km.getGiatri()));
                if ("phan_tram".equals(km.getHinhThuc())) {
                    rdPhanTram.setSelected(true);
                } else {
                    rdTien.setSelected(true);
                }
            }
        });
    }

    public void loadTable() {
        model.setRowCount(0);
        KMaiList = dao.getAllKhuyenmai();
        for (Khuyenmai km : KMaiList) {
            model.addRow(new Object[]{
                km.getMagg(),
                km.getSoluong(),
                km.getNgaybdau(),
                km.getNgaykthuc(),
                km.getApdunghd(),
                km.getGiatri(),
                km.getHinhThuc(),
                km.getVaitro()
            });
        }
    }
    public void reloadTable() {
        loadTable();
    }
    
    private void insertKM() {

        String vaitro = getRoleByUserId(currentID);
        if (vaitro == null) {
            JOptionPane.showMessageDialog(this, "Không lấy được vai trò người dùng!");
            return;
        }
        try {
            Khuyenmai km = new Khuyenmai();
            km.setMagg(txtMaGG.getText());
            km.setSoluong(Integer.parseInt(txtSoLuong.getText()));
            km.setNgaybdau(Date.valueOf(txtNgayBD.getText()));
            km.setNgaykthuc(Date.valueOf(txtNgayKT.getText()));
            km.setApdunghd(Double.parseDouble(txtApDung.getText()));
            km.setGiatri(Double.parseDouble(txtGiaTri.getText()));
            km.setHinhThuc(rdPhanTram.isSelected() ? "phan_tram" : "tien");
            km.setVaitro(vaitro); // truyền role vừa lấy
            km.setMaNV(currentID); 

            KhuyenmaiDao dao = new KhuyenmaiDao();
            if (dao.InsertKhuyenMai(km)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi thêm: " + ex.getMessage());
        }
    }
    
      private String getRoleByUserId(int userId) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect.dbConnection();
            String sql = "select vai_tro from user where id = ?";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("vai_tro");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      
    private void resertKM() {
        txtMaGG.setText("");
        txtSoLuong.setText("");
        txtNgayBD.setText("");
        txtNgayKT.setText("");
        txtApDung.setText("");
        txtGiaTri.setText("");
        group.clearSelection();
        table.clearSelection();
    }
      
    private void updateKM() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để cập nhật");
            return;
        }

        try {
            Khuyenmai km = new Khuyenmai();
            km.setMagg(txtMaGG.getText());
            km.setSoluong(Integer.parseInt(txtSoLuong.getText()));
            km.setNgaybdau(Date.valueOf(txtNgayBD.getText()));
            km.setNgaykthuc(Date.valueOf(txtNgayKT.getText()));
            km.setApdunghd(Double.parseDouble(txtApDung.getText()));
            km.setGiatri(Double.parseDouble(txtGiaTri.getText()));
            km.setHinhThuc(rdPhanTram.isSelected() ? "phan_tram" : "tien");
            km.setMaNV(currentID);

            if (dao.updateKhuyenMai(km)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadTable();
                resertKM();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi cập nhật: " + ex.getMessage());
        }
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1119, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents




    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
