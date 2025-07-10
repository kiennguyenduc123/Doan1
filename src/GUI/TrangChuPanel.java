/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Kiennguyen
 */
public class TrangChuPanel extends javax.swing.JPanel {

    /**
     * Creates new form TrangChuPanel
     */
    public TrangChuPanel() {
        initComponents();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Top - 4 ô thống kê
        add(createTopStatsPanel(), BorderLayout.NORTH);

        // Center - Biểu đồ dịch vụ
        add(createChartPanel(), BorderLayout.CENTER);

        // Bottom - 2 bảng: đơn đặt gần đây + đánh giá
        add(createBottomTablesPanel(), BorderLayout.SOUTH);
    }

    private JPanel createTopStatsPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(Color.WHITE);

        topPanel.add(createStatBox("Tổng đơn hàng", "152", new Color(244, 67, 54)));
        topPanel.add(createStatBox("Khách hàng", "87", new Color(33, 150, 243)));
        topPanel.add(createStatBox("Dịch vụ", "12", new Color(76, 175, 80)));
        topPanel.add(createStatBox("Đánh giá", "32", new Color(255, 193, 7)));

        return topPanel;
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

        // Dữ liệu giả
        Map<String, Integer> data = new HashMap<>();
        data.put("Chụp ảnh", 20);
        data.put("Thuê xe", 15);
        data.put("Trang trí", 25);
        data.put("Nhạc sống", 10);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Số lượng", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "", "Dịch vụ", "Số đơn",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartContainer.add(chartPanel, BorderLayout.CENTER);

        return chartContainer;
    }

    private JPanel createBottomTablesPanel() {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.setPreferredSize(new Dimension(1000, 250));

        // Bảng đơn đặt gần đây
        String[] col1 = {"Mã KH", "Tên KH", "Dịch vụ", "Ngày"};
        Object[][] data1 = {
            {"KH01", "Nguyễn Văn A", "Chụp ảnh", "2025-07-01"},
            {"KH02", "Trần Thị B", "Thuê xe", "2025-07-02"},
            {"KH03", "Phạm Văn C", "Trang trí", "2025-07-02"},};
        JTable table1 = new JTable(data1, col1);
        JScrollPane scroll1 = new JScrollPane(table1);
        scroll1.setBorder(BorderFactory.createTitledBorder("Đơn đặt gần đây"));

        // Bảng đánh giá
        String[] col2 = {"Mã KH", "Tên KH", "Nội dung", "Sao"};
        Object[][] data2 = {
            {"KH01", "Nguyễn Văn A", "Dịch vụ tốt", 5},
            {"KH03", "Phạm Văn C", "Tạm ổn", 3},};
        JTable table2 = new JTable(data2, col2);
        JScrollPane scroll2 = new JScrollPane(table2);
        scroll2.setBorder(BorderFactory.createTitledBorder("Đánh giá mới"));

        bottomPanel.add(scroll1);
        bottomPanel.add(scroll2);

        return bottomPanel;
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
