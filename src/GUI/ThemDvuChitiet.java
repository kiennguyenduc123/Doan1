/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.ChiTietHoaDonDao;
import DAO.DichvuDao;
import DAO.LichdatDao;
import DTO.Chitietlichdat;
import DTO.Dichvu;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Kiennguyen
 */
public class ThemDvuChitiet extends javax.swing.JFrame {

    private JTextField txtMaDV, txtTenDV, txtSoLuong, txtDonGia, txtGhiChu, txtTongTien;
    private JButton btnThem, btnHuy;
    private JSpinner spnNgay;

    private String maLichThue;
    private LichdatDao lichdatDao;
    DecimalFormat df = new DecimalFormat("#.##");

    private Reloadable reloadCallback;

    private int idChiTietHoaDon; // thêm biến này

    private ChiTietHoaDonDao chiTietHoaDonDao;


   
    public ThemDvuChitiet(String maLichThue,int idChiTietHoaDon,Reloadable reloadCallback) {
        initComponents();
        this.maLichThue = maLichThue;
        this.idChiTietHoaDon = idChiTietHoaDon;
        
        this.chiTietHoaDonDao = new ChiTietHoaDonDao();
        this.lichdatDao = new LichdatDao();
        this.reloadCallback = reloadCallback;

        
        setTitle("Thêm Dịch Vụ cho lịch thuê: " + maLichThue);
    setSize(550, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new GridBagLayout());

    // Khởi tạo component
    txtMaDV = new JTextField(20);
    txtTenDV = new JTextField(20);
    txtSoLuong = new JTextField(20);
    txtDonGia = new JTextField(20);
    txtGhiChu = new JTextField(20);
    txtTongTien = new JTextField(20);
    txtTongTien.setEditable(false);
    spnNgay = new JSpinner(new SpinnerDateModel());
    JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnNgay, "dd/MM/yyyy");
    spnNgay.setEditor(dateEditor);

    btnThem = new JButton("Thêm");
    btnHuy = new JButton("Hủy");

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    int row = 0;

    // Add label + field từng dòng
    addRow(gbc, row++, "Mã Dịch Vụ:", txtMaDV);
    addRow(gbc, row++, "Tên Dịch Vụ:", txtTenDV);
    addRow(gbc, row++, "Số Lượng:", txtSoLuong);
    addRow(gbc, row++, "Đơn Giá:", txtDonGia);
    addRow(gbc, row++, "Ngày Sử Dụng:", spnNgay);
    addRow(gbc, row++, "Ghi Chú:", txtGhiChu);
    addRow(gbc, row++, "Tổng Tiền:", txtTongTien);

    // Thêm nút
    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.gridwidth = 1;
    add(btnThem, gbc);

    gbc.gridx = 1;
    add(btnHuy, gbc);

        // Tự động tính tổng tiền khi thay đổi số lượng hoặc đơn giá
        txtSoLuong.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateTongTien();
            }
        });

        txtDonGia.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateTongTien();
            }
        });

        // Xử lý nút
        btnThem.addActionListener(e -> themDichVu());
        btnHuy.addActionListener(e -> dispose());
        
       setVisible(true);

        txtMaDV.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String maDV = txtMaDV.getText();
                if (!maDV.isEmpty()) {
                    try {
                        DichvuDao dao = new DichvuDao();
                        Dichvu dv = dao.getDichVuByMa(maDV);
                        if (dv != null) {
                            txtTenDV.setText(dv.getTendichvu());
                            txtMaDV.putClientProperty("id_dich_vu", dv.getId()); // lưu tạm id để dùng khi thêm
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy dịch vụ!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
              
        
        txtTenDV.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String tenDV = txtTenDV.getText().trim();
                if (!tenDV.isEmpty()) {
                    DichvuDao dao = new DichvuDao();

                    Dichvu dv = dao.getDichVuByTen(tenDV);
                    if (dv != null) {
                        txtMaDV.setText(dv.getMaDichvu());
                        txtDonGia.setText(df.format(dv.getGiathue()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy dịch vụ: " + tenDV);
                    }
                }
            }
        });
    
              
              
    }
    
    
    
    
    private void addRow(GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(field, gbc);
    }
    
    private void updateTongTien() {
        try {
            int soLuong = Integer.parseInt(txtSoLuong.getText().replace(",", ""));
            double donGia = Double.parseDouble(txtDonGia.getText().replace(",", ""));
            txtTongTien.setText(String.valueOf(soLuong * donGia));
        } catch (NumberFormatException e) {
            txtTongTien.setText("0");
        }
    }

    private void themDichVu() {
        try {
            Chitietlichdat ct = new Chitietlichdat();
            ct.setMaDV(txtMaDV.getText());
            ct.setTenDV(txtTenDV.getText());
            ct.setSoLuong(Integer.parseInt(this.txtSoLuong.getText()));
            ct.setDonGia(Double.parseDouble(this.txtDonGia.getText()));
            ct.setNgay((Date) spnNgay.getValue());
            ct.setGhiChu(txtGhiChu.getText());
            ct.setIdChitiethdon(idChiTietHoaDon); // thêm dòng này để set đúng ID chi tiết hóa đơn

            int idDichVu = (int) txtMaDV.getClientProperty("id_dich_vu");
            ct.setIdDichVu(idDichVu);
            
            boolean ok = lichdatDao.insertChiTietLich(maLichThue, ct);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Thêm dịch vụ thành công!");
                // GỌI LẠI LOAD DỮ LIỆU
                if (reloadCallback != null) {
                    reloadCallback.reload();
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
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
            .addGap(0, 988, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
