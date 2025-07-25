/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.ChiTietHoaDonDao;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kiennguyen
 */
public class quanlyLichPanel extends javax.swing.JPanel {

   

 private JTextField tfMaKH, tfTenKH, tfSDT, tfKhuyenMai, tfKhachTra, tfTraLai;
    private JLabel lblPointValue = new JLabel("0");
    private JLabel lblDiemSD = new JLabel("-0 VND");
    private JLabel lblGiamGia = new JLabel("0 VND");
    private JLabel lblTongCong = new JLabel("0 VND");
    private JLabel lblThanhToan = new JLabel("0 VND");
    private JLabel lblThanhTien = new JLabel("0 VND");
    private int diemKhachHang = 0;
    private long tongTien = 0;
    private long tienGiamKhuyenMai = 0; // lưu giá trị tiền giảm do KM

    DecimalFormat df = new DecimalFormat("#,###");

    private JTable tableLichThue;
    private JTable tableChiTietLich;
    private JTable tableHoaDon;

    private DefaultTableModel modelLichThue;
    private DefaultTableModel modelChiTiet;
    private DefaultTableModel modelHoaDon;

    private JTextField txtTimKiem;

    private LichdatDao lichdatDao;
    private UserDao userDao;
    private KhuyenmaiDao khuyenmaiDao;
    public HoaDonFrame hoaDonFrame;
    public ChiTietHoaDonDao chiTietHoaDonDao;
    
    List<Lichdat> lichdatList = new ArrayList<>();
    List<Chitietlichdat> chiTietList = new ArrayList<>();

    lichdatPanel lichPanelCha ;

    
    public quanlyLichPanel(lichdatPanel lichPanelCha) {
        initComponents();
        lichdatDao = new LichdatDao();
        userDao = new UserDao();
        khuyenmaiDao = new KhuyenmaiDao();
        this.lichPanelCha = lichPanelCha;
        this. chiTietHoaDonDao = new ChiTietHoaDonDao();
        hoaDonFrame = new HoaDonFrame();
        setLayout(new BorderLayout());
        setupLichThuePanel();
        
        loadLichThue(); 

        // Các ListSelectionListener cũng cần được thiết lập sau khi tableLichThue được khởi tạo
        tableLichThue.getSelectionModel().addListSelectionListener(e -> {
            int row = tableLichThue.getSelectedRow();
            if (row >= 0) {
                String maLich = modelLichThue.getValueAt(row, 0).toString();
                loadChiTietLich(maLich);
                // Reset form thanh toán khi chọn một lịch đặt mới
                resetPaymentForm();
                tinhThanhTien(0);
            }
        });
    }
    
    

    private void setupLichThuePanel() {
        // ===== Thanh tìm kiếm trên cùng =====
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtTimKiem = new JTextField(20);
        JButton btnTimKiem = new JButton(" Tìm kiếm");
        JButton btnTaoMoi = new JButton(" Tạo lịch thuê");
        btnTaoMoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi Form mới
                
                new TaoLichThue(quanlyLichPanel.this).setVisible(true);
            }
        });
        
        btnTimKiem.addActionListener(e -> {
            String keyword = this.txtTimKiem.getText().trim();
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng hoặc mã lịch thuê cần tìm!");
                loadLichThue();  // load lại toàn bộ dữ liệu
                return;
            }

            modelLichThue.setRowCount(0);
            this.lichdatList = lichdatDao.searchLichdat(keyword);
            for (Lichdat ld : lichdatList) {
                modelLichThue.addRow(new Object[]{
                    ld.getMaLichThue(),
                    ld.getTenKhachHang(),
                    ld.getSdt(),
                    ld.getNgaySuDung(),
                    ld.getTinhTrang()
                });
            }
        });
        
        
        
        topPanel.add(txtTimKiem);
        topPanel.add(btnTimKiem);
        topPanel.add(btnTaoMoi);
        add(topPanel, BorderLayout.NORTH);

        // ===== LEFT: bảng lịch + bảng chi tiết =====
        String[] colsLich = {"Mã Lịch", "Khách Hàng", "SĐT", "Ngày Dùng", "Trạng Thái"};
        modelLichThue = new DefaultTableModel(colsLich, 0);
        tableLichThue = new JTable(modelLichThue);
        JScrollPane scrollLich = new JScrollPane(tableLichThue);

        String[] colsChiTiet = {"Mã DV", "Tên Dịch Vụ", "SL", "Đơn Giá", "Ngày", "Ghi Chú", "Tổng Tiền"};
        modelChiTiet = new DefaultTableModel(colsChiTiet, 0);
        tableChiTietLich = new JTable(modelChiTiet);
        JScrollPane scrollChiTiet = new JScrollPane(tableChiTietLich);

        JPanel chiTietWrapper = new JPanel(new BorderLayout());
        chiTietWrapper.add(scrollChiTiet, BorderLayout.CENTER);
        JButton btnThem = new JButton("+ Thêm dịch vụ");
        JButton xoaDong = new JButton("🗑 Xoá dòng");
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.add(btnThem);
        btnPanel.add(xoaDong);
        chiTietWrapper.add(btnPanel, BorderLayout.SOUTH);
        
        
        btnThem.addActionListener(e -> {
            int row = tableLichThue.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn lịch thuê!");
                return;
            }

            String maLich = modelLichThue.getValueAt(row, 0).toString();
            int idChiTietHoaDon = chiTietHoaDonDao.getChiTietHoaDonIdByMaLich(maLich);
            if (idChiTietHoaDon == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy chi tiết hóa đơn cho lịch thuê này.");
                return;
            }
            new ThemDvuChitiet(maLich, idChiTietHoaDon, () -> loadChiTietLich(maLich)).setVisible(true);
        });
        
        

        JSplitPane leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollLich, chiTietWrapper);
        leftSplit.setDividerLocation(250);
        leftSplit.setResizeWeight(0.5);

        // ===== RIGHT: Form thanh toán =====
        JPanel formPanel = createPaymentForm();

        // ===== Main Split: chia làm 2 cột =====
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplit, formPanel);
        mainSplit.setResizeWeight(0.7);
        add(mainSplit, BorderLayout.CENTER);

        // Đặt lại vị trí divider sau khi hiển thị GUI
        SwingUtilities.invokeLater(() -> {
            int width = getWidth();
            mainSplit.setDividerLocation((int) (width * 0.7));
        });

    }

    private JPanel createPaymentForm() {
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
                User kh = userDao.getById(maKH);
                if (kh != null) {
                    tfTenKH.setText(kh.getHoten());
                    tfSDT.setText(kh.getSdt());
                    diemKhachHang = kh.getDiemtichluy();
                    lblPointValue.setText(String.valueOf(diemKhachHang));
                    lblDiemSD.setText("-0 VND"); // Reset points used display
                    tinhThanhTien(0); // Recalculate if customer changes
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy KH");
                    tfTenKH.setText("");
                    tfSDT.setText("");
                    diemKhachHang = 0;
                    lblPointValue.setText("0");
                    lblDiemSD.setText("-0 VND");
                    tinhThanhTien(0);
                }
            }
        });

        // ==== TÊN KH ====
        formPanel.add(makeLabel("Tên KH:", labelFont), gbcConfig(gbc, 0, row));
        tfTenKH = makeTextField(12);
        tfTenKH.setEditable(false); // Make it read-only
        formPanel.add(tfTenKH, gbcConfig(gbc, 1, row++));

        // ==== SDT ====
        formPanel.add(makeLabel("SDT:", labelFont), gbcConfig(gbc, 0, row));
        tfSDT = makeTextField(12);
        tfSDT.setEditable(false); // Make it read-only
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
            if (tfMaKH.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã Khách Hàng trước.");
                return;
            }
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
                    
//                    diemKhachHang -= diemMuonDung;
//
//                    lblPointValue.setText(String.valueOf(diemKhachHang)); // Cập nhật lại hiển thị

                    lblDiemSD.setText("-" + df.format((long)diemMuonDung * 1000) + " VND");
                    // Note: We don't actually deduct points from diemKhachHang here,
                    // as it represents the *current available points*.
                    // The actual deduction happens when the bill is paid.
                    tinhThanhTien((long)diemMuonDung * 1000); // truyền giảm giá từ điểm
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
        tfKhuyenMai = makeTextField(10);
        formPanel.add(tfKhuyenMai, gbcConfig(gbc, 1, row++));

        // --- Dòng nút Áp dụng + Hủy mã (tách riêng) ---
        JPanel kmButtonRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        kmButtonRow.setBackground(Color.WHITE);

        JButton btnApDung = new JButton("Áp dụng");
        JButton btnHuyMa = new JButton("Hủy mã");

        btnApDung.setMargin(new Insets(5, 8, 5, 8));
        btnHuyMa.setMargin(new Insets(5, 8, 5, 8));
        
        btnApDung.addActionListener(e -> {
            String magg = this.tfKhuyenMai.getText().trim();
            if (magg.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã giảm giá.");
                return;
            }

            Khuyenmai km = khuyenmaiDao.getKhuyenmai(magg);

            if (km == null) {
                JOptionPane.showMessageDialog(this, "Mã khuyến mãi không hợp lệ hoặc đã hết.");
                return;
            }
            Date today = new Date();
            // Check if today is within the start and end dates (inclusive)
            if (today.before(km.getNgaybdau()) || today.after(km.getNgaykthuc())) {
                JOptionPane.showMessageDialog(this, "Khuyến mãi không còn hiệu lực.");
                return;
            }
            if(km.getSoluong() <= 0){
                JOptionPane.showMessageDialog(this, "Mã khuyến mãi đã hết lượt sử dụng.");
                return;
            }

            long giamGia = 0;
            if (km.getHinhThuc().equalsIgnoreCase("tien")) {
                giamGia = (long) km.getGiatri();
            } else if (km.getHinhThuc().equalsIgnoreCase("phan_tram")) {
                giamGia = (long) (tongTien * (km.getGiatri() / 100.0));
            }
            
            // Ensure discount doesn't exceed total price
            if (giamGia > tongTien) {
                giamGia = tongTien;
            }

            tienGiamKhuyenMai = giamGia;
            lblGiamGia.setText("-" + df.format(giamGia) + " VND");

            // You might want to decrement the quantity in the database AFTER successful payment
            // For now, let's just apply it in the UI
            // if (khuyenmaiDao.giamSoluongKM(km.getId())) {
            JOptionPane.showMessageDialog(this, "Áp dụng khuyến mãi thành công!");
            // }

            tinhThanhTien(Long.parseLong(lblDiemSD.getText().replace("-", "").replace(" VND", "").replace(",", ""))); // cập nhật lại toàn bộ
        });
        
        btnHuyMa.addActionListener(e -> {
            tfKhuyenMai.setText("");
            tienGiamKhuyenMai = 0;
            lblGiamGia.setText("0 VND");
            JOptionPane.showMessageDialog(this, "Đã hủy mã giảm giá.");
            tinhThanhTien(Long.parseLong(lblDiemSD.getText().replace("-", "").replace(" VND", "").replace(",", "")));
        });

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
        tfKhachTra = makeTextField(15);
        formPanel.add(tfKhachTra, gbcConfig(gbc, 1, row++));
        
        tfKhachTra.addActionListener(e -> {
            calculateChange();
        });
        tfKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calculateChange();
            }
        });


        // TRẢ LẠI KHÁCH:
        formPanel.add(makeLabel("TRẢ LẠI KHÁCH:", labelFont), gbcConfig(gbc, 0, row));
        tfTraLai = makeTextField(15);
        tfTraLai.setEditable(false);
        formPanel.add(tfTraLai, gbcConfig(gbc, 1, row++));

        // ==== NÚT TẠO HĐ + HỦY (giữa, to hơn) ====
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnHoaDon = new JButton("F5 - Tạo HĐ");
        
        btnHoaDon.addActionListener(e -> {
            tfMaKH.setEnabled(true);
            btnUsePoint.setEnabled(true);
            tfKhuyenMai.setEnabled(true);
            btnApDung.setEnabled(true);
            btnHuyMa.setEnabled(true);
            tfKhachTra.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Tạo hoá đơn thanh toán thành công" );
        });
        JButton btnHuy = new JButton("F0 - Hủy");
        btnHuy.addActionListener(e -> {
            tfMaKH.setEnabled(false);
            btnUsePoint.setEnabled(false);
            tfKhuyenMai.setEnabled(false);
            btnApDung.setEnabled(false);
            btnHuyMa.setEnabled(false);
            tfKhachTra.setEnabled(false);
            
            // Reset toàn bộ
            tfMaKH.setText("");
            tfTenKH.setText("");
            tfSDT.setText("");
            tfKhuyenMai.setText("");
            lblGiamGia.setText("0 VND");
            lblDiemSD.setText("-0 VND");
            lblThanhToan.setText("0 VND");
            tfKhachTra.setText("");
            tfTraLai.setText("");
            lblPointValue.setText("0");
            diemKhachHang = 0;
            tienGiamKhuyenMai = 0;
            tongTien = 0;
            JOptionPane.showMessageDialog(this, "Huỷ tạo hoá đơn thành công");
        });
        
        
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
        
        tfMaKH.setEnabled(false);
        btnUsePoint.setEnabled(false);
        tfKhuyenMai.setEnabled(false);
        btnApDung.setEnabled(false);
        btnHuyMa.setEnabled(false);
        tfKhachTra.setEnabled(false);

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

        btnThanhToan.addActionListener(e -> {
            long thanhTien = 0;
            try {
                thanhTien = Long.parseLong(lblThanhToan.getText().replace(",", "").replace(" VND", ""));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi định dạng số tiền thanh toán!");
                return;
            }

            long tienKhachDua = 0;
            try {
                tienKhachDua = Long.parseLong(tfKhachTra.getText().trim().replace(",", ""));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền khách trả hợp lệ!");
                return;
            }

            if (tienKhachDua < thanhTien) {
                JOptionPane.showMessageDialog(this, "Khách trả chưa đủ tiền!");
                return;
            }

            long tienThua = tienKhachDua - thanhTien;

            // Giả sử tạo mã hóa đơn tạm
            String maHD = "HD" + System.currentTimeMillis();
            
            int selectedRow = tableLichThue.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một lịch thuê để thanh toán!");
                return;
            }
            String maLich = modelLichThue.getValueAt(selectedRow, 0).toString();
            
            
            int idLich = lichdatDao.getIdLichThueByMa(maLich);
            if (idLich == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy lịch thuê trong DB!");
                return;
            }
            
            // Trừ điểm KH nếu có
            if (!tfMaKH.getText().trim().isEmpty()) {
                String maKH = tfMaKH.getText().trim();
                User kh = userDao.getById(maKH);
                if (kh != null) {
                    String diemGiamStr = lblDiemSD.getText().replace("-", "").replace(",", "").replace(" VND", "").trim();
                    int diemDaDung = 0;
                    try {
                        diemDaDung = (int) (Long.parseLong(diemGiamStr) / 1000);
                    } catch (Exception ex) {
                        diemDaDung = 0;
                    }

                    if (diemDaDung > 0) {
                        int diemConLai = kh.getDiemtichluy() - diemDaDung;
                        if (diemConLai < 0) {
                            diemConLai = 0;
                        }

                        boolean ok = userDao.updateDiem(maKH, diemConLai);
                        if (!ok) {
                            JOptionPane.showMessageDialog(this, "Không thể cập nhật điểm tích lũy!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Thanh toán thành công!\nĐã trừ " + diemDaDung + " điểm tích lũy.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
            }
            
            System.out.println("maLich đang truyền vào hóa đơn: " + maLich);

            String magg = tfKhuyenMai.getText().trim(); // Lấy mã giảm giá từ text field

           
            
                int idMaGG = khuyenmaiDao.getIdByMaDV(magg);
                if (idMaGG == -1) {
                    JOptionPane.showMessageDialog(this, "Mã giảm giá không hợp lệ trong DB!");
                    return;
                }
            
               // Cập nhật số lượt sử dụng mã khuyến mãi
            if (!magg.isEmpty()) {
                boolean ok = khuyenmaiDao.giamSoluongKM(magg);
                if (!ok) {
                    System.err.println("⚠️ Không thể cập nhật số lượt mã khuyến mãi!");
                    // Có thể log thêm, không cần hiển thị lỗi vì đã thanh toán xong
                }
            }
                
            String maKH = tfMaKH.getText().trim();
            int diemDaDung = 0;
            try {
                String diemGiamStr = lblDiemSD.getText().replace("-", "").replace(",", "").replace(" VND", "").trim();
                diemDaDung = (int) (Long.parseLong(diemGiamStr) / 1000);
            } catch (Exception ex) {
                diemDaDung = 0;
            }

                new HoaDonFrame(maHD, "admin", chiTietList, tongTien, tienGiamKhuyenMai,
                thanhTien, tienKhachDua, tienThua, idLich, idMaGG,maKH,diemDaDung,
                lichPanelCha.getPanelHoaDon(),
                lichPanelCha.getPanelLichThue()).setVisible(true);
//                hoaDonFrame.setVisible(true);

            // 4. RESET GIAO DIỆN
            tfMaKH.setText("");
            tfTenKH.setText("");
            tfSDT.setText("");
            tfKhachTra.setText("");
            tfTraLai.setText("");
            tfKhuyenMai.setText("");
            lblPointValue.setText("0");
            lblDiemSD.setText("-0 VND");
            lblGiamGia.setText("0 VND");
            lblThanhToan.setText("0 VND");
            lblTongCong.setText("0 VND");

            tienGiamKhuyenMai = 0;
            diemKhachHang = 0;

            // Gọi lại load dữ liệu nếu cần (ví dụ danh sách hóa đơn mới...)
        });

        return formPanel;
    }

    // ======= Helper methods =======
    private JLabel makeLabel(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
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

    void loadLichThue() {
        modelLichThue.setRowCount(0);
        this.lichdatList = lichdatDao.getAllLichdat();
        for (Lichdat ld : lichdatList) {
            modelLichThue.addRow(new Object[]{
                ld.getMaLichThue(),
                ld.getTenKhachHang(),
                ld.getSdt(),
                ld.getNgaySuDung(),
                ld.getTinhTrang()
            });
        }
    }
    
//    private void showHoaDon(String maHD, String tenNhanVien, List<Chitietlichdat> chiTietList,
//                        long tongCong, long khuyenMai, long thanhTien,
//                        long tienKhachDua, long tienThua) {
//    StringBuilder sb = new StringBuilder();
//    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//
//    sb.append("              Cửa Hàng SH\n");
//    sb.append("    355 xxxx, Quận xx, TP Hồ Chí Minh\n");
//    sb.append("     SDT Cửa Hàng: 09765432\n\n");
//    sb.append("         Hóa Đơn Thanh Toán\n");
//    sb.append("--------------------------------------\n");
//    sb.append("Số Phiếu: ").append(maHD).append("\n");
//    sb.append("Ngày: ").append(sdf.format(new Date())).append("\n");
//    sb.append("Thu Ngân: ").append(tenNhanVien).append("\n");
//    sb.append("--------------------------------------\n");
//    sb.append(String.format("%-10s %-3s %-10s %-10s\n", "Tên SP", "SL", "Giá", "Thành Tiền"));
//
//    for (Chitietlichdat ct : chiTietList) {
//        long donGia = (long) ct.getDonGia();
//        long thanhTienSP = ct.getSoLuong() * donGia;
//        sb.append(String.format("%-10s %-3d %,10d %,10d\n",
//                ct.getTenDV(), ct.getSoLuong(), donGia, thanhTienSP));
//    }
//
//    sb.append("\n");
//    sb.append(String.format("Tổng cộng:       %,10d\n", tongCong));
//    sb.append(String.format("Khuyến Mãi:      %,10d\n", khuyenMai));
//    sb.append(String.format("Thành Tiền:      %,10d\n", thanhTien));
//    sb.append(String.format("Tiền Khách Đưa:  %,10d\n", tienKhachDua));
//    sb.append(String.format("Tiền Thừa:       %,10d\n", tienThua));
//    sb.append("\n");
//    sb.append("  Xin Cảm Ơn Quý Khách\n");
//    sb.append("       Và Hẹn Gặp Lại!\n");
//
//    JTextArea textArea = new JTextArea(sb.toString());
//    textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
//    textArea.setEditable(false);
//
//    JScrollPane scrollPane = new JScrollPane(textArea);
//    scrollPane.setPreferredSize(new Dimension(380, 420));
//
//    JOptionPane.showMessageDialog(null, scrollPane, "Hóa Đơn In Ra", JOptionPane.INFORMATION_MESSAGE);
//}


    private void loadChiTietLich(String maLichThue) {
        modelChiTiet.setRowCount(0);
        this.chiTietList = lichdatDao.getAllLichdatchitiet(maLichThue);
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
        
        // GỌI TÍNH LẠI TỔNG SAU KHI LOAD
        long diemGiam = 0;
        try {
            // Nếu có điểm đã nhập → dùng để giảm
            String diemStr = lblDiemSD.getText().replace(" VND", "").replace(",", "").replace("-", "").trim();
            if (!diemStr.isEmpty()) {
                diemGiam = Long.parseLong(diemStr);
            }
        } catch (Exception ex) {
            diemGiam = 0;
        }

        tinhThanhTien(diemGiam); // Tính lại tổng tiền và thanh toán
    }
    
    private void tinhThanhTien(long giamTuDiem) {
        long tong = 0;

        for (int i = 0; i < modelChiTiet.getRowCount(); i++) {
            Object val = modelChiTiet.getValueAt(i, 6); // cột Tổng Tiền
            if (val != null) {
                try {
                    tong += Double.parseDouble(val.toString());
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in 'Tổng Tiền' column: " + val);
                }
            }
        }

        this.tongTien = tong;
        lblTongCong.setText(df.format(tongTien) + " VND");

        long thanhTien = tongTien - giamTuDiem - tienGiamKhuyenMai;

        // If total amount is less than 0, set to 0
        if (thanhTien < 0) {
            thanhTien = 0;
        }

        lblThanhToan.setText(df.format(thanhTien) + " VND");
        // lblThanhTien.setText(df.format(thanhTien) + " VND"); // Redundant with lblThanhToan

        calculateChange();
    }
    
    private void calculateChange() {
        try {
            long khachTra = Long.parseLong(tfKhachTra.getText().trim().replace(",", ""));
            long thanhTien = Long.parseLong(lblThanhToan.getText().replace(",", "").replace(" VND", ""));
            long traLai = khachTra - thanhTien;
            if (traLai < 0) {
                tfTraLai.setText("Thiếu " + df.format(Math.abs(traLai)) + " VND");
            } else {
                tfTraLai.setText(df.format(traLai) + " VND");
            }
        } catch (NumberFormatException ex) {
            tfTraLai.setText("Sai định dạng hoặc chưa nhập");
        }
    }
    
    private void resetPaymentForm() {
        tfMaKH.setText("");
        tfTenKH.setText("");
        tfSDT.setText("");
        tfKhuyenMai.setText("");
        tfKhachTra.setText("");
        tfTraLai.setText("");
        
        diemKhachHang = 0;
        tongTien = 0;
        tienGiamKhuyenMai = 0;
        
        lblPointValue.setText("0");
        lblDiemSD.setText("-0 VND");
        lblGiamGia.setText("0 VND");
        lblTongCong.setText("0 VND");
        lblThanhToan.setText("0 VND");
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
