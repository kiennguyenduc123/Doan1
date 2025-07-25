/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.ChiTietHoaDonDao;
import DAO.DichvuDao;
import DAO.HoadonDao;
import DAO.UserDao;
import DTO.Chitiethoadon;
import DTO.Dichvu;
import DTO.Hoadon;
import DTO.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author Kiennguyen
 */
public class TrangChuPanel extends javax.swing.JPanel {

   private final HoadonDao hoadonDao = new HoadonDao();
    private final UserDao userDao = new UserDao();
    private final DichvuDao dichvuDao = new DichvuDao();
    private final ChiTietHoaDonDao chitietHoaDonDao = new ChiTietHoaDonDao();
    private JTable table; // Bảng dữ liệu để xuất Excel
//    DecimalFormat formatter = new DecimalFormat("#,###");

    public TrangChuPanel() {
        initComponents();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(createTopStatsPanel(), BorderLayout.NORTH);

        add(createChartPanel(), BorderLayout.CENTER);

        add(createBottomTablesPanel(), BorderLayout.SOUTH);
    }

    private JPanel createTopStatsPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 5, 10, 10)); // 4 cột + 1 nút xuất Excel
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(Color.WHITE);

        Map<String, Integer> stats = getThongKeTongQuat();

        topPanel.add(createStatBox("Tổng đơn hàng", String.valueOf(stats.get("donhang")), new Color(244, 67, 54)));
        topPanel.add(createStatBox("Khách hàng", String.valueOf(stats.get("khachhang")), new Color(33, 150, 243)));
        topPanel.add(createStatBox("Dịch vụ", String.valueOf(stats.get("dichvu")), new Color(76, 175, 80)));
        DecimalFormat formatter = new DecimalFormat("#,###");
        String doanhThuFormatted = formatter.format(stats.get("doanhthu"));
        topPanel.add(createStatBox("Tổng doanh thu", doanhThuFormatted + " VNĐ", new Color(255, 152, 0)));

        // Tạo nút xuất Excel
        JButton btnExportExcel = new JButton("Xuất Excel");
        btnExportExcel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExportExcel.setForeground(Color.WHITE);
        btnExportExcel.setBackground(new Color(0, 150, 0));
        btnExportExcel.setFocusPainted(false);
        btnExportExcel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnExportExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hiệu ứng hover
        btnExportExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportExcel.setBackground(new Color(0, 180, 0));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportExcel.setBackground(new Color(0, 150, 0));
            }
        });

        // Gắn sự kiện xuất Excel
        btnExportExcel.addActionListener(e -> exportToExcel());

        // Bọc nút trong JPanel để đồng bộ layout
        JPanel btnPanel = new JPanel(new BorderLayout());
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnExportExcel, BorderLayout.CENTER);

        topPanel.add(btnPanel);

        return topPanel;
    }


    private Map<String, Integer> getThongKeTongQuat() {
        Map<String, Integer> result = new HashMap<>();
        try {
            int tongDonHang = hoadonDao.getAllHoadon().size();
            int tongKhachHang = userDao.getUserList().size();
            int tongDichVu = dichvuDao.getAllDichvu().size();
            double tongDoanhThu = hoadonDao.getAllHoadon()
                    .stream()
                    .mapToDouble(h -> h.getTongTien())
                    .sum();

            result.put("donhang", tongDonHang);
            result.put("khachhang", tongKhachHang);
            result.put("dichvu", tongDichVu);
            result.put("doanhthu", (int) tongDoanhThu);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("donhang", 0);
            result.put("khachhang", 0);
            result.put("dichvu", 0);
            result.put("doanhthu", 0);
        }
        return result;
    }

    private JPanel createStatBox(String title, String value, Color color) {
        JPanel box = new JPanel();
        box.setLayout(new BorderLayout());
        box.setBackground(color);
        box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        box.setPreferredSize(new Dimension(100, 80));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));

        box.add(titleLabel, BorderLayout.NORTH);
        box.add(valueLabel, BorderLayout.CENTER);
        return box;
    }

    private JPanel createChartPanel() {
        JPanel chartContainer = new JPanel(new BorderLayout());
        chartContainer.setBorder(BorderFactory.createTitledBorder("Biểu đồ Thống Kê Dịch Vụ"));

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> serviceCountMap = new HashMap<>();

        try {
            List<Hoadon> hoadonList = hoadonDao.getAllHoadon();

            for (Hoadon hd : hoadonList) {
                List<Chitiethoadon> chiTietList = chitietHoaDonDao.getChiTietHoaDonById(String.valueOf(hd.getId()));

                for (Chitiethoadon ct : chiTietList) {
                    String tenDichVu = ct.getTen_dich_vu();
                    serviceCountMap.put(tenDichVu, serviceCountMap.getOrDefault(tenDichVu, 0) + ct.getSoLuong());
                }
            }

            for (Map.Entry<String, Integer> entry : serviceCountMap.entrySet()) {
                dataset.addValue(entry.getValue(), "Số lượt đặt", entry.getKey());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "", "Dịch vụ", "Số lượt đặt",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartContainer.add(chartPanel, BorderLayout.CENTER);

        return chartContainer;
    }

    private JPanel createBottomTablesPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.setPreferredSize(new Dimension(1000, 250));

        String[] col = {"Mã KH", "Tên KH", "Dịch vụ", "Ngày"};
        Object[][] data;

        try {
            List<Hoadon> hoadons = hoadonDao.getAllHoadon();
            List<User> users = userDao.getUserList();
            List<Chitiethoadon> chitiets = chitietHoaDonDao.getAllChitiet();
            List<Dichvu> dichvus = dichvuDao.getAllDichvu();

            int size = Math.min(hoadons.size(), 5);
            data = new Object[size][4];

            for (int i = 0; i < size; i++) {
                Hoadon hd = hoadons.get(hoadons.size() - 1 - i);

                String tenKH = users.stream()
                        .filter(u -> u.getMakhachhang().equalsIgnoreCase(hd.getMaKH()))
                        .map(User::getHoten)
                        .findFirst().orElse("Không rõ");

                List<Chitiethoadon> chitietTheoHD = chitiets.stream()
                        .filter(ct -> ct.getId_hoa_don() == hd.getId())
                        .collect(Collectors.toList());

                String tenDichVu = chitietTheoHD.stream()
                        .map(ct -> {
                            Optional<Dichvu> dv = dichvus.stream()
                                    .filter(d -> d.getId() == ct.getId_dich_vu())
                                    .findFirst();
                            return dv.map(Dichvu::getTendichvu).orElse(null);
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.joining(", "));

                data[i][0] = hd.getMaKH();
                data[i][1] = tenKH;
                data[i][2] = tenDichVu;
                data[i][3] = hd.getNgayttoan();
            }

        } catch (Exception e) {
            e.printStackTrace();
            data = new Object[][]{};
        }

        table = new JTable(data, col);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Đơn đặt gần đây"));
        bottomPanel.add(scroll, BorderLayout.CENTER);
        return bottomPanel;
    }

    private void exportToExcel() {
        if (table == null || table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu để xuất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu file Excel");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("ThongKe");

                // Tạo tiêu đề
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(table.getColumnName(i));
                }

                // Ghi dữ liệu
                for (int rowIdx = 0; rowIdx < table.getRowCount(); rowIdx++) {
                    Row row = sheet.createRow(rowIdx + 1);
                    for (int colIdx = 0; colIdx < table.getColumnCount(); colIdx++) {
                        Object value = table.getValueAt(rowIdx, colIdx);
                        row.createCell(colIdx).setCellValue(value != null ? value.toString() : "");
                    }
                }

                // Tự động điều chỉnh độ rộng cột
                for (int i = 0; i < table.getColumnCount(); i++) {
                    sheet.autoSizeColumn(i);
                }

                // Lưu file
                String filePath = fileChooser.getSelectedFile().getAbsolutePath() + ".xlsx";
                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    workbook.write(fos);
                }

                JOptionPane.showMessageDialog(this, "Xuất Excel thành công: " + filePath);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất Excel!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
