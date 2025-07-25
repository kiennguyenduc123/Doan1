/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.UserDao;
import DTO.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kiennguyen
 */
public class quanlyNhanvien extends javax.swing.JPanel {

    List<User> listKH = new ArrayList<>();
    DefaultTableModel table = null;
    UserDao dao;

    public quanlyNhanvien() throws SQLException {
        initComponents();

        dao = new UserDao(); // <-- phải khởi tạo đối tượng

        table = (DefaultTableModel) this.tbNhanvien.getModel();
        initButtons();
        loadTablenv();
    }

    public void initButtons() {
        btnThemnv.setBackground(new Color(0, 102, 102));
        btnThemnv.setForeground(Color.WHITE);

        this.btnLammoi.setBackground(new Color(0, 102, 102));
        btnLammoi.setForeground(Color.WHITE);

        btnSuanv.setBackground(null);
        btnSuanv.setForeground(Color.BLACK);

        btnXoanv.setBackground(null);
        btnXoanv.setForeground(Color.BLACK);

        btnSuanv.setEnabled(false);
        btnXoanv.setEnabled(false);
    }

    private void loadTablenv() throws SQLException {
        table.setRowCount(0);
        listKH = dao.getNhanvienList();
        for (User a : listKH) {
            table.addRow(new Object[]{
                a.getMakhachhang(),
                a.getTendangnhap(),
                a.getHoten(),
                a.getEmail(),
                a.getSdt(),
                a.getDiachi(),
                a.getMatkhau(),
                a.getVaitro()
            });
        }
    }

    private void clearForm() {
        this.txtManhanvien.setText("");
        this.txtTendnhap.setText("");
        txtHotennv.setText("");
        txtEmailnv.setText("");
        txtSDTnv.setText("");
        txtDiachinv.setText("");
        txtMatkhaunv.setText("");
        this.txtVaitro.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        txtTendnhap = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtManhanvien = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtSDTnv = new javax.swing.JTextField();
        txtEmailnv = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtDiachinv = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtMatkhaunv = new javax.swing.JTextField();
        btnThemnv = new javax.swing.JButton();
        btnSuanv = new javax.swing.JButton();
        btnXoanv = new javax.swing.JButton();
        btnLammoi = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        txtHotennv = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtVaitro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanvien = new javax.swing.JTable();
        txtTimKiemnv = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Quản Lý Nhân Viên");

        jLabel45.setText("Tên đăng nhập:");

        jLabel46.setText("Họ tên:");

        txtManhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtManhanvientxtHotenActionPerformed(evt);
            }
        });

        jLabel47.setText("SĐT:");

        jLabel48.setText("Email:");

        jLabel49.setText("Địa chỉ:");

        jLabel50.setText("Mật khẩu:");

        btnThemnv.setBackground(new java.awt.Color(0, 102, 102));
        btnThemnv.setForeground(new java.awt.Color(255, 255, 255));
        btnThemnv.setText("Thêm");
        btnThemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemnvActionPerformed(evt);
            }
        });

        btnSuanv.setText("Cập Nhật");
        btnSuanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuanvActionPerformed(evt);
            }
        });

        btnXoanv.setText("Xoá");
        btnXoanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoanvActionPerformed(evt);
            }
        });

        btnLammoi.setText("Làm Mới");
        btnLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiActionPerformed(evt);
            }
        });

        jLabel51.setText("Mã nhân viên");

        txtHotennv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHotennvtxtHotenActionPerformed(evt);
            }
        });

        jLabel52.setText("Vai trò");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTendnhap, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDiachinv, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtManhanvien, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txtEmailnv, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHotennv)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel50)
                                .addComponent(jLabel47)
                                .addComponent(jLabel46)
                                .addComponent(txtMatkhaunv)
                                .addComponent(txtSDTnv, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel52))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtVaitro))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnThemnv)
                .addGap(18, 18, 18)
                .addComponent(btnSuanv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoanv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLammoi)
                .addGap(60, 60, 60))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtManhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHotennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTendnhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatkhaunv)
                    .addComponent(txtEmailnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiachinv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoanv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jLabel2.setBackground(new java.awt.Color(0, 102, 102));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Danh Sách Nhân Viên");
        jLabel2.setOpaque(true);

        tbNhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Đăng Nhập", "Họ Tên", "Email", "SĐT", "Địa Chỉ", "Mật Khẩu", "Vai trò"
            }
        ));
        tbNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanvien);

        btnTimKiem.setBackground(new java.awt.Color(0, 102, 102));
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnTimKiem)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimKiem)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtManhanvientxtHotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtManhanvientxtHotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtManhanvientxtHotenActionPerformed

    private void btnThemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemnvActionPerformed
        // TODO add your handling code here:
        try {
            String makh = this.txtManhanvien.getText().trim();
            String tendn = this.txtTendnhap.getText().trim();
            String hoten = this.txtHotennv.getText().trim();
            String email = this.txtEmailnv.getText().trim();
            String sdt = this.txtSDTnv.getText().trim();
            String diachi = this.txtDiachinv.getText().trim();
            String mkhau = this.txtMatkhaunv.getText().trim();
            String vaitro = this.txtVaitro.getText().trim();

            if (makh.isEmpty() || tendn.isEmpty() || hoten.isEmpty() || mkhau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin bắt buộc");
                return;
            }

            User a = new User();
            a.setMakhachhang(makh);
            a.setTendangnhap(tendn);
            a.setHoten(hoten);
            a.setEmail(email);
            a.setSdt(sdt);
            a.setDiachi(diachi);
            a.setMatkhau(mkhau);
            a.setVaitro(vaitro);
            dao.insertNhanvien(a);
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
            loadTablenv();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnThemnvActionPerformed

    private void btnSuanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuanvActionPerformed
        try {
            int selectedRow = tbNhanvien.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để cập nhật.");
                return;
            }

            String makh = this.txtManhanvien.getText().trim();
            String tendn = this.txtTendnhap.getText().trim();
            String hoten = this.txtHotennv.getText().trim();
            String email = this.txtEmailnv.getText().trim();
            String sdt = this.txtSDTnv.getText().trim();
            String diachi = this.txtDiachinv.getText().trim();
            String mkhau = this.txtMatkhaunv.getText().trim();
            String vaitro = this.txtVaitro.getText().trim();

            User a = new User();
            a.setMakhachhang(makh);
            a.setTendangnhap(tendn);
            a.setHoten(hoten);
            a.setEmail(email);
            a.setSdt(sdt);
            a.setDiachi(diachi);
            a.setMatkhau(mkhau);
            a.setVaitro(vaitro);
            dao.updateNhanvien(a);
            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công");
            loadTablenv();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSuanvActionPerformed

    private void btnXoanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoanvActionPerformed
        int selectedRow = tbNhanvien.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để xoá.");
            return;
        }

        String makh = (String) tbNhanvien.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.deleteNhanvien(makh);
            JOptionPane.showMessageDialog(this, "Xoá khách hàng thành công");
            try {
                loadTablenv();
                clearForm();
            } catch (SQLException ex) {
                Logger.getLogger(quanlyUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnXoanvActionPerformed

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed

    }//GEN-LAST:event_btnLammoiActionPerformed

    private void txtHotennvtxtHotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHotennvtxtHotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHotennvtxtHotenActionPerformed

    private void tbNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanvienMouseClicked
        int selectedRow = tbNhanvien.getSelectedRow();
        if (selectedRow >= 0) {
            this.txtManhanvien.setText(tbNhanvien.getValueAt(selectedRow, 0).toString());
            this.txtTendnhap.setText(tbNhanvien.getValueAt(selectedRow, 1).toString());
            this.txtHotennv.setText(tbNhanvien.getValueAt(selectedRow, 2).toString());
            txtEmailnv.setText(tbNhanvien.getValueAt(selectedRow, 3).toString());
            this.txtSDTnv.setText(tbNhanvien.getValueAt(selectedRow, 4).toString());
            this.txtDiachinv.setText(tbNhanvien.getValueAt(selectedRow, 5).toString());
            this.txtMatkhaunv.setText(tbNhanvien.getValueAt(selectedRow, 6).toString());
            this.txtVaitro.setText(tbNhanvien.getValueAt(selectedRow, 7).toString());

            btnSuanv.setEnabled(true);
            btnSuanv.setBackground(new Color(0, 102, 102));
            btnSuanv.setForeground(Color.WHITE);

            btnXoanv.setEnabled(true);
            btnXoanv.setBackground(new Color(0, 102, 102));
            btnXoanv.setForeground(Color.WHITE);

            // 👉 Đổi lại màu Thêm & Làm Mới về mặc định
            btnThemnv.setEnabled(false);
            btnThemnv.setBackground(null);
            btnThemnv.setForeground(Color.BLACK);

            btnLammoi.setEnabled(false);
            btnLammoi.setBackground(null);
            btnLammoi.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tbNhanvienMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String keyword = this.txtTimKiemnv.getText().trim();

        if (keyword.isEmpty()) {
            try {
                loadTablenv();
            } catch (SQLException ex) {
                Logger.getLogger(quanlyUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }

        if (keyword.length() > 0) {
            try {
                listKH = dao.searchUser(keyword);
                table.setRowCount(0);
                for (User a : listKH) {
                    table.addRow(new Object[]{
                        a.getMakhachhang(),
                        a.getTendangnhap(),
                        a.getHoten(),
                        a.getEmail(),
                        a.getSdt(),
                        a.getDiachi(),
                        a.getMatkhau(),
                        a.getDiemtichluy()
                    });
                }
            } catch (SQLException ex) {
                Logger.getLogger(quanlyUser.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnTimKiemActionPerformed
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnSuanv;
    private javax.swing.JButton btnThemnv;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoanv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbNhanvien;
    private javax.swing.JTextField txtDiachinv;
    private javax.swing.JTextField txtEmailnv;
    private javax.swing.JTextField txtHotennv;
    private javax.swing.JTextField txtManhanvien;
    private javax.swing.JTextField txtMatkhaunv;
    private javax.swing.JTextField txtSDTnv;
    private javax.swing.JTextField txtTendnhap;
    private javax.swing.JTextField txtTimKiemnv;
    private javax.swing.JTextField txtVaitro;
    // End of variables declaration//GEN-END:variables
}
