/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.DichvuDao;
import DTO.Dichvu;
import DTO.User;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Kiennguyen
 */
public class GoiCuoiHoiPanel extends javax.swing.JPanel {

    private DichvuDao dao;
    private JTable table;
    private DefaultTableModel model = null;
    private JTextField txtSearch;
    private JComboBox<String> cmbLoai;

    private JTextField txtMaDichVu, txtTenDichVu, txtGiaThue;
    private JComboBox<String> cmbLoaiDichVu;
    private JTextField txtMoTa;
    private JLabel lblHinhAnh;
    private JRadioButton rdoConHang, rdoHetHang, rdoBaoTri;
    private ButtonGroup groupTrangThai;

    List<Dichvu> dvuList = new ArrayList<>();

    private JTabbedPane tabbedPane;
    private JLabel lblImage;

    public GoiCuoiHoiPanel(Connection conn) {
        initComponents();
        this.dao = new DichvuDao(conn);
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JPanel topWrapper = new JPanel();
        topWrapper.setLayout(new BorderLayout());
        topWrapper.add(createHeaderPanel(), BorderLayout.NORTH);
        topWrapper.add(createSearchFilterPanel(), BorderLayout.SOUTH);

        add(topWrapper, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Danh sách", createDanhSachPanel());
        tabbedPane.addTab("Chi tiết", createChiTietPanel());

        add(tabbedPane, BorderLayout.CENTER);
        loadTable();

    }

    private void loadTable() {
        model.setRowCount(0);
        try {
            dvuList = dao.getAllDichvu();
            for (Dichvu dv : dvuList) {
                ImageIcon imageIcon = null;
                if (dv.getImagePath() != null && !dv.getImagePath().isEmpty()) {

                    File imgFile = new File("src/Icon/" + dv.getImagePath());

                    URL url = getClass().getResource("/Icon/" + dv.getImagePath());

                    if (imgFile.exists()) {
                        Image img = new ImageIcon(imgFile.getAbsolutePath())
                                .getImage()
                                .getScaledInstance(100, 80, Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(img);
                    } else if (url != null) {
                        Image img = new ImageIcon(url)
                                .getImage()
                                .getScaledInstance(100, 80, Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(img);
                    }
                }
                String trangThai = dv.getTrangthai() != null ? dv.getTrangthai() : "Không xác định";

                model.addRow(new Object[]{
                    dv.getMaDichvu(),
                    dv.getTendichvu(),
                    dv.getLoaidichvu(),
                    dv.getGiathue(),
                    imageIcon,
                    trangThai,});
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoiCuoiHoiPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 102, 102));
        headerPanel.setPreferredSize(new Dimension(100, 60));

        JLabel lblHeader = new JLabel("Quản Lý Sản Phẩm");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setBorder(new EmptyBorder(10, 20, 10, 0));

        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        headerRight.setOpaque(false);
        JButton btnTaoMoi = new JButton("Tạo mới SP");
        JButton btnXuatExcel = new JButton("Xuất Excel");

        btnTaoMoi.addActionListener(e -> {
            try {
                addDichvu();
            } catch (SQLException ex) {
                Logger.getLogger(GoiCuoiHoiPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        headerRight.add(btnTaoMoi);
        headerRight.add(btnXuatExcel);

        headerPanel.add(lblHeader, BorderLayout.WEST);
        headerPanel.add(headerRight, BorderLayout.EAST);
        return headerPanel;
    }

    private JPanel createSearchFilterPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(Color.WHITE);
        JLabel lblSearch = new JLabel("Nhập mã / Tên");
        txtSearch = new JTextField(15);
        JButton btnSearch = new JButton("Tìm Kiếm");
        leftPanel.add(lblSearch);
        leftPanel.add(txtSearch);
        leftPanel.add(btnSearch);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(Color.WHITE);
        cmbLoai = new JComboBox<>(new String[]{"Tất cả trạng thái" , "Còn hàng", "Hết hàng", "Bảo trì"});
        rightPanel.add(cmbLoai);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);
        
        btnSearch.addActionListener(e -> SearchDichvu());
        cmbLoai.addActionListener(e -> filterByLoai());
        return topPanel;
    }

    private JPanel createDanhSachPanel() {

        String[] columns = {"Mã Dịch Vụ", "Tên Dịch Vụ", "Loại Dịch Vụ", "Giá Thuê", "Hình Ảnh", "Trạng Thái"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 4 ? ImageIcon.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(80);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setSelectionBackground(new Color(204, 255, 255));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    // Gọi hàm fill dữ liệu từ dòng được chọn
                    fillFormFromSelectedRow(row);

                    // Chuyển sang tab "Chi tiết"
                    tabbedPane.setSelectedIndex(1);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createChiTietPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMaDichVu = new JTextField(20);
        txtTenDichVu = new JTextField(20);
        txtGiaThue = new JTextField(20);
        cmbLoaiDichVu = new JComboBox<>(new String[]{
            "Thuê váy cưới", "Thuê vest chú rể", "Thuê áo dài cưới",
            "Thuê phụ kiện cưới", "Trang điểm cô dâu", "Thuê đồ bê tráp"
        });
        txtMoTa = new JTextField(20);

        lblHinhAnh = new JLabel();
        lblHinhAnh.setPreferredSize(new Dimension(120, 120));
        lblHinhAnh.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblHinhAnh.setHorizontalAlignment(JLabel.CENTER);

        JButton btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//                File file = fileChooser.getSelectedFile();
//                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
//                Image img = icon.getImage().getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH);
//                lblHinhAnh.setIcon(new ImageIcon(img));
                File file = fileChooser.getSelectedFile();
                ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
                originalIcon.setDescription(file.getAbsolutePath()); // Đặt description chứa path

                Image img = originalIcon.getImage().getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(img);
                resizedIcon.setDescription(originalIcon.getDescription()); // Giữ lại path gốc

                lblHinhAnh.setIcon(resizedIcon);
            }
        });

        // Trạng thái radio buttons
        JLabel lblTrangThai = new JLabel("Trạng thái:");
        rdoConHang = new JRadioButton("Còn hàng");
        rdoHetHang = new JRadioButton("Hết hàng");
        rdoBaoTri = new JRadioButton("Bảo trì");

        groupTrangThai = new ButtonGroup();
        groupTrangThai.add(rdoConHang);
        groupTrangThai.add(rdoHetHang);
        groupTrangThai.add(rdoBaoTri);
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        statusPanel.setBackground(Color.WHITE);
        statusPanel.add(rdoConHang);
        statusPanel.add(rdoHetHang);
        statusPanel.add(rdoBaoTri);

        JButton btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.addActionListener(e -> updateDichvu());

        JButton btnMoi = new JButton("Mới");
        btnMoi.addActionListener(e -> clearForm());

        JButton btnXoa = new JButton("Xoá");
        btnXoa.addActionListener(e -> deleteDichvu());

        int y = 0;
        gbc.gridx = 0;
        gbc.gridy = y;
        formPanel.add(new JLabel("Mã dịch vụ:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtMaDichVu, gbc);

        gbc.gridy = ++y;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Tên dịch vụ:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtTenDichVu, gbc);

        gbc.gridy = ++y;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Loại dịch vụ:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cmbLoaiDichVu, gbc);

        gbc.gridy = ++y;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Giá thuê:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtGiaThue, gbc);

        gbc.gridy = ++y;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Mô tả:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtMoTa, gbc);

        gbc.gridy = ++y;
        gbc.gridx = 0;
        formPanel.add(lblTrangThai, gbc);
        gbc.gridx = 1;
        formPanel.add(statusPanel, gbc);

        gbc.gridy = ++y;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Hình ảnh:"), gbc);
        gbc.gridx = 1;
        formPanel.add(lblHinhAnh, gbc);

        gbc.gridy = ++y;
        gbc.gridx = 1;
        formPanel.add(btnChonAnh, gbc);

        // Panel chứa nút
        gbc.gridy = ++y;
        gbc.gridx = 1;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnCapNhat);
        btnPanel.add(btnMoi);
        btnPanel.add(btnXoa);
        formPanel.add(btnPanel, gbc);

        return formPanel;
    }

    private void addDichvu() throws SQLException {
        ThemDichVuFrame frame = new ThemDichVuFrame(null);

        // Lắng nghe sự kiện khi frame bị đóng
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                Dichvu newDV = frame.getDichVu();

                if (frame.isSucceeded() && newDV != null) {
                    try {
                        if (dao.insert(newDV)) {
                            JOptionPane.showMessageDialog(GoiCuoiHoiPanel.this, "Thêm dịch vụ thành công!");
                            loadTable();
                        } else {
                            JOptionPane.showMessageDialog(GoiCuoiHoiPanel.this, "Lỗi khi thêm dịch vụ!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(GoiCuoiHoiPanel.this, "Lỗi truy vấn SQL!");
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    private void updateDichvu() {
        int row = table.getSelectedRow();
        if (row != -1) {
            try {
                Dichvu dv = new Dichvu();
                dv.setMaDichvu(txtMaDichVu.getText());
                dv.setTendichvu(txtTenDichVu.getText());
                dv.setLoaidichvu((String) cmbLoaiDichVu.getSelectedItem());
                // Sửa lỗi NumberFormatException nếu người dùng nhập trống
                try {
                    dv.setGiathue(Double.parseDouble(txtGiaThue.getText()));
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Giá thuê không hợp lệ!");
                    return;
                }

                dv.setMota(txtMoTa.getText());
                // Xử lý ảnh
                Icon icon = lblHinhAnh.getIcon();
                if (icon instanceof ImageIcon) {
                    String path = ((ImageIcon) icon).getDescription(); // đã được lưu từ trước
                    if (path != null && !path.isEmpty()) {
                        File file = new File(path);
                        dv.setImagePath(file.getName()); // Chỉ lấy tên file
                    }
                }

                if (rdoConHang.isSelected()) {
                    dv.setTrangthai("Còn hàng");
                } else if (rdoHetHang.isSelected()) {
                    dv.setTrangthai("Hết hàng");
                } else if (rdoBaoTri.isSelected()) {
                    dv.setTrangthai("Bảo trì");
                } else {
                    dv.setTrangthai("Không xác định");
                }

                if (dao.update(dv)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");

                    // Reload bảng và quay về tab "Danh sách"
                    loadTable();
                    tabbedPane.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dịch vụ cần cập nhật.");
        }
    }

    private void deleteDichvu() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá dịch vụ này?", "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    String madichvu = txtMaDichVu.getText();
                    if (dao.delete(madichvu)) {
                        JOptionPane.showMessageDialog(this, "Xoá thành công!");
                        loadTable();
                        clearForm();
                        tabbedPane.setSelectedIndex(0); // quay về tab Danh sách
                    } else {
                        JOptionPane.showMessageDialog(this, "Xoá thất bại!");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi xoá: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dịch vụ cần xoá.");
        }
    }

    private void fillFormFromSelectedRow(int row) {
        try {
            String maDichVu = (String) model.getValueAt(row, 0);

            for (Dichvu dv : dvuList) {
                if (dv.getMaDichvu() == maDichVu) {
                    txtMaDichVu.setText(maDichVu);
                    txtTenDichVu.setText(dv.getTendichvu());
                    cmbLoaiDichVu.setSelectedItem(dv.getLoaidichvu());
                    txtGiaThue.setText(String.valueOf(dv.getGiathue()));
                    txtMoTa.setText(dv.getMota());

                    if (dv.getImagePath() != null && !dv.getImagePath().isEmpty()) {
                        File imgFile = new File("src/Icon/" + dv.getImagePath());
                        if (imgFile.exists()) {
                            ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
                            Image img = icon.getImage().getScaledInstance(
                                    lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH
                            );
                            lblHinhAnh.setIcon(new ImageIcon(img));
                        } else {
                            lblHinhAnh.setIcon(null); // hoặc set ảnh mặc định
                        }
                    } else {
                        lblHinhAnh.setIcon(null);
                    }

                    String trangThai = dv.getTrangthai();
                    if ("Còn hàng".equalsIgnoreCase(trangThai)) {
                        rdoConHang.setSelected(true);
                    } else if ("Hết hàng".equalsIgnoreCase(trangThai)) {
                        rdoHetHang.setSelected(true);
                    } else if ("Bảo trì".equalsIgnoreCase(trangThai)) {
                        rdoBaoTri.setSelected(true);
                    } else {
                        groupTrangThai.clearSelection();
                    }
                    break;
                }
            }
         } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị chi tiết: " + e.getMessage());
        }
    }
    
    private void SearchDichvu() {
        String input = this.txtSearch.getText();
        
        if (input.isEmpty()) {
            loadTable();
            return;
        }
        
        if(input.length() > 0) {
            model.setRowCount(0);
            dvuList = dao.search(input);
            for (Dichvu dv : dvuList) {
                ImageIcon imageIcon = null;
                if (dv.getImagePath() != null && !dv.getImagePath().isEmpty()) {

                    File imgFile = new File("src/Icon/" + dv.getImagePath());

                    URL url = getClass().getResource("/Icon/" + dv.getImagePath());

                    if (imgFile.exists()) {
                        Image img = new ImageIcon(imgFile.getAbsolutePath())
                                .getImage()
                                .getScaledInstance(100, 80, Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(img);
                    } else if (url != null) {
                        Image img = new ImageIcon(url)
                                .getImage()
                                .getScaledInstance(100, 80, Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(img);
                    }
                }
                String trangThai = dv.getTrangthai() != null ? dv.getTrangthai() : "Không xác định";

                model.addRow(new Object[]{
                    dv.getMaDichvu(),
                    dv.getTendichvu(),
                    dv.getLoaidichvu(),
                    dv.getGiathue(),
                    imageIcon,
                    trangThai
                });
            }
        }
    }
    
    private void filterByLoai(){
        String selected = (String) cmbLoai.getSelectedItem();
        
        if("Tất cả trạng thái".equals(selected)) {
            try {
                dvuList = dao.getAllDichvu();
            } catch (SQLException ex) {
                Logger.getLogger(GoiCuoiHoiPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            dvuList = dao.filterByLoai(selected);
        }
        
            model.setRowCount(0);
            for (Dichvu dv : dvuList) {
                ImageIcon imageIcon = null;
                if (dv.getImagePath() != null && !dv.getImagePath().isEmpty()) {

                    File imgFile = new File("src/Icon/" + dv.getImagePath());

                    URL url = getClass().getResource("/Icon/" + dv.getImagePath());

                    if (imgFile.exists()) {
                        Image img = new ImageIcon(imgFile.getAbsolutePath())
                                .getImage()
                                .getScaledInstance(100, 80, Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(img);
                    } else if (url != null) {
                        Image img = new ImageIcon(url)
                                .getImage()
                                .getScaledInstance(100, 80, Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(img);
                    }
                }
                String trangThai = dv.getTrangthai() != null ? dv.getTrangthai() : "Không xác định";

                model.addRow(new Object[]{
                    dv.getMaDichvu(),
                    dv.getTendichvu(),
                    dv.getLoaidichvu(),
                    dv.getGiathue(),
                    imageIcon,
                    trangThai
                });
            }
    }

    private void clearForm() {
        txtMaDichVu.setText("");
        txtTenDichVu.setText("");
        txtGiaThue.setText("");
        cmbLoaiDichVu.setSelectedIndex(0);
        txtMoTa.setText("");
        lblHinhAnh.setIcon(null);
        table.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setAlignmentX(12.0F);
        setAlignmentY(12.0F);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 961, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
