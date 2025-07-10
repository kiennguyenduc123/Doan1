/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.DichvuDao;
import DAO.LichdatDao;
import DAO.UserDao;
import DTO.Chitietlichdat;
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
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class lichdatPanel extends javax.swing.JPanel {

    private JTextField tfMaKH, tfTenKH, tfSDT, tfKhuyenMai, tfKhachTra, tfTraLai;
    private JLabel lblPointValue = new JLabel("0");
    private JLabel lblDiemSD = new JLabel("-0 VND");
    private JLabel lblGiamGia = new JLabel("0 VND");
    private JLabel lblTongCong = new JLabel("0 VND");
    private JLabel lblThanhToan = new JLabel("0 VND");
    private JLabel lblThanhTien = new JLabel("0 VND");
    private int diemKhachHang = 0;
    private long tongTien = 0;

    private JTable tableLichThue;
    private JTable tableChiTietLich;
    private JTable tableHoaDon;

    private DefaultTableModel modelLichThue;
    private DefaultTableModel modelChiTiet;
    private DefaultTableModel modelHoaDon;

    private JTextField txtTimKiem;

    private LichdatDao dao;

    List<Lichdat> lichdatList = new ArrayList<>();
    List<Chitietlichdat> chiTietList = new ArrayList<>();

    public lichdatPanel() {
        initComponents();

        dao = new LichdatDao();

        setLayout(new BorderLayout());

        // Khởi tạo Tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: Lịch Thuê
        JPanel panelLichThue = taoTabLichThue();
        tabbedPane.addTab("📅 Quản lý lịch thuê", panelLichThue);

        // Tab 2: Hóa Đơn
        JPanel panelHoaDon = taoTabHoaDon();
        tabbedPane.addTab(" Quản lý hóa đơn", panelHoaDon);

        add(tabbedPane, BorderLayout.CENTER);

        loadLichThue();
        tableLichThue.getSelectionModel().addListSelectionListener(e -> {
            int row = tableLichThue.getSelectedRow();
            if (row >= 0) {
                String maLich = modelLichThue.getValueAt(row, 0).toString();
                loadChiTietLich(maLich);
            }
        });

    }

    private JPanel taoTabLichThue() {
        JPanel panel = new JPanel(new BorderLayout());

        // ===== Thanh tìm kiếm trên cùng =====
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtTimKiem = new JTextField(20);
        JButton btnTimKiem = new JButton(" Tìm kiếm");
        JButton btnTaoMoi = new JButton(" Tạo lịch thuê");
        topPanel.add(txtTimKiem);
        topPanel.add(btnTimKiem);
        topPanel.add(btnTaoMoi);
        panel.add(topPanel, BorderLayout.NORTH);

        // ===== LEFT: bảng lịch + bảng chi tiết =====
        String[] colsLich = {"Mã Lịch", "Khách Hàng", "SĐT", "Ngày Dùng"};
        modelLichThue = new DefaultTableModel(colsLich, 0);
        tableLichThue = new JTable(modelLichThue);
        JScrollPane scrollLich = new JScrollPane(tableLichThue);

        String[] colsChiTiet = {"Mã DV", "Tên Dịch Vụ", "SL", "Đơn Giá", "Ngày", "Ghi Chú", "Tổng Tiền"}; // thêm cột cuối
        modelChiTiet = new DefaultTableModel(colsChiTiet, 0);
        tableChiTietLich = new JTable(modelChiTiet);
        JScrollPane scrollChiTiet = new JScrollPane(tableChiTietLich);

        JPanel chiTietWrapper = new JPanel(new BorderLayout());
        chiTietWrapper.add(scrollChiTiet, BorderLayout.CENTER);
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.add(new JButton("+ Thêm dịch vụ"));
        btnPanel.add(new JButton("🗑 Xoá dòng"));
        chiTietWrapper.add(btnPanel, BorderLayout.SOUTH);

        JSplitPane leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollLich, chiTietWrapper);
        leftSplit.setDividerLocation(250);
        leftSplit.setResizeWeight(0.5);

        // ===== RIGHT: Form thanh toán =====
        JPanel formPanel = taoFormThanhToan();

        // ===== Main Split: chia làm 2 cột =====
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplit, formPanel);

        mainSplit.setResizeWeight(0.7);

        panel.add(mainSplit, BorderLayout.CENTER);

// Đặt lại vị trí divider sau khi hiển thị GUI
        SwingUtilities.invokeLater(() -> {
            int width = panel.getWidth();
            mainSplit.setDividerLocation((int) (width * 0.7));
        });

        return panel;

    }

    private JPanel taoFormThanhToan() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(320, 0));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.PLAIN, 13);
        Font boldFont = new Font("Arial", Font.BOLD, 14);
        int row = 0;

        // ==== THỜI GIAN: đỏ label, giờ đậm ====
        JLabel lblTime = new JLabel();
        lblTime.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        formPanel.add(lblTime, gbc);

        Timer timer = new Timer(1000, e -> {
            ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");
            LocalTime now = LocalTime.now(vietnamZone);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText("<html><span style='color:red;'>Thời gian:</span> <b>" + now.format(formatter) + "</b></html>");
        });
        timer.start();

        // ==== MÃ KH ====
        gbc.gridwidth = 1;
        formPanel.add(makeLabel("MÃ KH:", labelFont), gbcConfig(gbc, 0, row));
        tfMaKH = makeTextField(12);
        formPanel.add(tfMaKH, gbcConfig(gbc, 1, row++));

        tfMaKH.addActionListener(e -> {
            String maKH = tfMaKH.getText().trim();
            if (!maKH.isEmpty()) {
                User kh = new UserDao().getById(maKH);
                if (kh != null) {
                    tfTenKH.setText(kh.getHoten());
                    tfSDT.setText(kh.getSdt());
                    diemKhachHang = kh.getDiemtichluy();
                    lblPointValue.setText(String.valueOf(diemKhachHang));
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy KH");
                }
            }
        });

        // ==== TÊN KH ====
        formPanel.add(makeLabel("Tên KH:", labelFont), gbcConfig(gbc, 0, row));
        tfTenKH = makeTextField(12);
        formPanel.add(tfTenKH, gbcConfig(gbc, 1, row++));

        // ==== SDT ====
        formPanel.add(makeLabel("SDT:", labelFont), gbcConfig(gbc, 0, row));
        tfSDT = makeTextField(12);
        formPanel.add(tfSDT, gbcConfig(gbc, 1, row++));

        // ==== Nút Sử dụng điểm (ở giữa) ====
        JButton btnUsePoint = new JButton("Sử dụng điểm");
        btnUsePoint.setBackground(Color.MAGENTA);
        btnUsePoint.setForeground(Color.WHITE);
        btnUsePoint.setPreferredSize(new Dimension(130, 28));
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnUsePoint, gbc);

        btnUsePoint.addActionListener(e -> {
            if (diemKhachHang <= 0) {
                JOptionPane.showMessageDialog(this, "Khách không có điểm để sử dụng");
                return;
            }

            String input = JOptionPane.showInputDialog(this, "Nhập số điểm muốn dùng (Tối đa: " + diemKhachHang + "):", "Sử dụng điểm", JOptionPane.QUESTION_MESSAGE);
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int diemMuonDung = Integer.parseInt(input);
                    if (diemMuonDung > diemKhachHang || diemMuonDung < 0) {
                        JOptionPane.showMessageDialog(this, "Số điểm không hợp lệ!");
                        return;
                    }

                    lblDiemSD.setText("-" + diemMuonDung * 1000 + " VND");
                    diemKhachHang -= diemMuonDung;
                    lblPointValue.setText(String.valueOf(diemKhachHang));
                    tinhThanhTien(diemMuonDung * 1000); // truyền giảm giá
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ.");
                }
            }
        });

        // Trong hàm tạo form:
        JPanel pointPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pointPanel.setBackground(Color.WHITE);

        JLabel lblPointLabel = new JLabel("Số điểm: ");
        lblPointLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        lblPointLabel.setForeground(Color.BLACK);

        lblPointValue.setFont(new Font("Arial", Font.BOLD, 14));
        lblPointValue.setForeground(Color.RED);

        pointPanel.add(lblPointLabel);
        pointPanel.add(lblPointValue);

        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(pointPanel, gbc);

        // ==== KHUYẾN MÃI ====
        // --- Dòng Khuyến mãi (label + ô nhập) ---
        formPanel.add(makeLabel("Khuyến mãi:", labelFont), gbcConfig(gbc, 0, row));
        JTextField tfKM = makeTextField(10);
        formPanel.add(tfKM, gbcConfig(gbc, 1, row++)); // Chỉ thêm tfKM ở dòng này

// --- Dòng nút Áp dụng + Hủy mã (tách riêng) ---
        JPanel kmButtonRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // spacing đẹp hơn
        kmButtonRow.setBackground(Color.WHITE);

        JButton btnApDung = new JButton("Áp dụng");
        JButton btnHuyMa = new JButton("Hủy mã");

        btnApDung.setMargin(new Insets(5, 8, 5, 8));
        btnHuyMa.setMargin(new Insets(5, 8, 5, 8));

        kmButtonRow.add(btnApDung);
        kmButtonRow.add(btnHuyMa);

        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(kmButtonRow, gbc);

        // ==== TỔNG KẾT ====
        // TỔNG CỘNG
        formPanel.add(makeLabel("TỔNG CỘNG:", labelFont), gbcConfig(gbc, 0, row));
        lblTongCong.setFont(boldFont);
        lblTongCong.setForeground(Color.BLUE);
        formPanel.add(lblTongCong, gbcConfig(gbc, 1, row++));

// GIẢM GIÁ
        formPanel.add(makeLabel("GIẢM GIÁ:", labelFont), gbcConfig(gbc, 0, row));
        lblGiamGia.setForeground(Color.RED);
        formPanel.add(lblGiamGia, gbcConfig(gbc, 1, row++));

// SỬ DỤNG ĐIỂM
        formPanel.add(makeLabel("SỬ DỤNG ĐIỂM:", labelFont), gbcConfig(gbc, 0, row));
        lblDiemSD.setForeground(Color.MAGENTA);
        formPanel.add(lblDiemSD, gbcConfig(gbc, 1, row++));

        // THÀNH TIỀN
        formPanel.add(makeLabel("THÀNH TIỀN:", boldFont), gbcConfig(gbc, 0, row));
        lblThanhToan.setFont(boldFont);
        lblThanhToan.setForeground(new Color(0, 102, 0)); // xanh đậm
        formPanel.add(lblThanhToan, gbcConfig(gbc, 1, row++));

        // KHÁCH TRẢ:
        formPanel.add(makeLabel("KHÁCH TRẢ:", labelFont), gbcConfig(gbc, 0, row));
        JTextField tfKhachTra = makeTextField(15);
        formPanel.add(tfKhachTra, gbcConfig(gbc, 1, row++));

        // TRẢ LẠI KHÁCH:
        formPanel.add(makeLabel("TRẢ LẠI KHÁCH:", labelFont), gbcConfig(gbc, 0, row));
        tfTraLai = makeTextField(15); // <-- Gán vào biến thành viên
        tfTraLai.setEditable(false);
        formPanel.add(tfTraLai, gbcConfig(gbc, 1, row++));

            
        tfKhachTra.addActionListener(e -> {
            try {
                long khachTra = Long.parseLong(tfKhachTra.getText().trim().replace(",", ""));
                long thanhTien = Long.parseLong(lblThanhToan.getText().replace(",", "").replace(" VND", ""));
                long traLai = khachTra - thanhTien;
                if (traLai < 0) {
                    tfTraLai.setText("Thiếu " + Math.abs(traLai) + " VND");
                } else {
                    tfTraLai.setText(traLai + " VND");
                }
            } catch (NumberFormatException ex) {
                tfTraLai.setText("Sai định dạng");
            }
        });
        
        // ==== NÚT TẠO HĐ + HỦY (giữa, to hơn) ====
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnHoaDon = new JButton("F5 - Tạo HĐ");
        JButton btnHuy = new JButton("F0 - Hủy");
        btnHoaDon.setPreferredSize(new Dimension(130, 35));
        btnHuy.setPreferredSize(new Dimension(130, 35));
        btnHuy.setBackground(Color.RED);
        btnHuy.setForeground(Color.WHITE);
        btnRow.add(btnHoaDon);
        btnRow.add(Box.createHorizontalStrut(15));
        btnRow.add(btnHuy);
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        formPanel.add(btnRow, gbc);

        // ==== NÚT THANH TOÁN ====
        JButton btnThanhToan = new JButton("F12 - Thanh toán");
        btnThanhToan.setBackground(new Color(0, 128, 128));
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.setFont(boldFont);
        btnThanhToan.setPreferredSize(new Dimension(280, 40));
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        formPanel.add(btnThanhToan, gbc);

        return formPanel;
    }

// ======= Helper methods =======
    private JLabel makeLabel(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JLabel makeBoldLabel(String text, Color color, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        lbl.setForeground(color);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JTextField makeTextField(int cols) {
        JTextField tf = new JTextField(cols);
        tf.setMaximumSize(new Dimension(200, 25));
        tf.setAlignmentX(Component.LEFT_ALIGNMENT);
        return tf;
    }

    private GridBagConstraints gbcConfig(GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        return gbc;
    }

    private GridBagConstraints gbcConfig(GridBagConstraints gbc, int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        return gbc;
    }

    private JPanel taoTabHoaDon() {
        JPanel panel = new JPanel(new BorderLayout());

        // Bảng danh sách hóa đơn
        String[] colsHoaDon = {"Mã HĐ", "Khách Hàng", "Tổng tiền", "Còn lại", "Trạng thái"};
        modelHoaDon = new DefaultTableModel(colsHoaDon, 0);
        tableHoaDon = new JTable(modelHoaDon);
        JScrollPane scroll = new JScrollPane(tableHoaDon);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void loadLichThue() {
        modelLichThue = (DefaultTableModel) tableLichThue.getModel();
        modelLichThue.setRowCount(0);
        this.lichdatList = dao.getAllLichdat();
        for (Lichdat ld : lichdatList) {
            modelLichThue.addRow(new Object[]{
                ld.getMaLichThue(),
                ld.getTenKhachHang(),
                ld.getSdt(),
                ld.getNgaySuDung()
            });
        }

    }

    private void loadChiTietLich(String maLichThue) {
        modelChiTiet = (DefaultTableModel) tableChiTietLich.getModel();
        modelChiTiet.setRowCount(0);
        this.chiTietList = dao.getAllLichdatchitiet(maLichThue);
        for (Chitietlichdat ld : chiTietList) {
            modelChiTiet.addRow(new Object[]{
                ld.getMaDV(),
                ld.getTenDV(),
                ld.getSoLuong(),
                ld.getDonGia(),
                ld.getNgay(),
                ld.getGhiChu(),
                ld.getTinhTong()
            });
        }
    }

    private void tinhThanhTien(int giamTuDiem) {
        int tong = 0;

        for (int i = 0; i < modelChiTiet.getRowCount(); i++) {
            Object val = modelChiTiet.getValueAt(i, 6); // cột Tổng Tiền
            if (val != null) {
                tong += Double.parseDouble(val.toString());
            }
        }

        int giamGia = 0;
        try {
            giamGia = Integer.parseInt(tfKhuyenMai.getText().trim());
        } catch (Exception e) {
            // Không có khuyến mãi hoặc lỗi format
        }

        int thanhTien = tong - giamGia - giamTuDiem;

        lblTongCong.setText(tong + " VND");
        lblGiamGia.setText("-" + giamGia + " VND");
        lblThanhTien.setText(thanhTien + " VND");

        try {
            int khachTra = Integer.parseInt(tfKhachTra.getText());
            tfTraLai.setText((khachTra - thanhTien) + " VND");
        } catch (Exception e) {
            tfTraLai.setText("0");
        }
    }
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản Lý Cho Thuê");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(new lichdatPanel());
        frame.setVisible(true);
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
