/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.DichvuDao;
import DAO.UserDao;
import DTO.User;
import java.awt.Color;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class quanlyUser extends javax.swing.JPanel {

    List<User> listKH = new ArrayList<>();
    DefaultTableModel table = null;
    UserDao dao;

    public quanlyUser() throws SQLException {
        initComponents();
        dao = new UserDao(); // <-- phải khởi tạo đối tượng

        table = (DefaultTableModel) this.tbKhachhang.getModel();
        initButton();
        loadTable();
    }

    public void initButton() {
        btnThem.setBackground(new Color(0, 102, 102));
        btnThem.setForeground(Color.WHITE);

        this.btnReset.setBackground(new Color(0, 102, 102));
        btnReset.setForeground(Color.WHITE);

        btnSua.setBackground(null);
        btnSua.setForeground(Color.BLACK);

        btnXoa.setBackground(null);
        btnXoa.setForeground(Color.BLACK);

        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    private void loadTable() throws SQLException {
        table.setRowCount(0);
        listKH = dao.getUserList();
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
    }

    private void clearForm() {
        txtMakhachhang.setText("");
        txtTendangnhap.setText("");
        txtHoten.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtDiachi.setText("");
        txtMatkhau.setText("");
        txtDiem.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        txtTendangnhap = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtMakhachhang = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtDiachi = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtMatkhau = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtDiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhachhang = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Quản Lý Khách Hàng");

        jLabel45.setText("Tên đăng nhập:");

        jLabel46.setText("Họ tên:");

        txtMakhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMakhachhangtxtHotenActionPerformed(evt);
            }
        });

        jLabel47.setText("SĐT:");

        jLabel48.setText("Email:");

        jLabel49.setText("Địa chỉ:");

        jLabel50.setText("Mật khẩu:");

        btnThem.setBackground(new java.awt.Color(0, 102, 102));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Cập Nhật");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel51.setText("Mã khách hàng: ");

        txtHoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHotentxtHotenActionPerformed(evt);
            }
        });

        jLabel52.setText("Điểm tích:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTendangnhap, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDiachi, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMakhachhang, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoten)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel50)
                                .addComponent(jLabel47)
                                .addComponent(jLabel46)
                                .addComponent(txtMatkhau)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel52))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtDiem))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset)
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
                    .addComponent(txtMakhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTendangnhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMatkhau)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jLabel2.setBackground(new java.awt.Color(0, 102, 102));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Danh Sách Khách Hàng");
        jLabel2.setOpaque(true);

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        tbKhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khách Hàng", "Tên Đăng Nhập", "Họ Tên", "Email", "SĐT", "Địa Chỉ", "Mật Khẩu", "Điểm Tích"
            }
        ));
        tbKhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhachhang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        btnTimKiem.setBackground(new java.awt.Color(0, 102, 102));
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(btnTimKiem))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMakhachhangtxtHotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMakhachhangtxtHotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMakhachhangtxtHotenActionPerformed

    private void tbKhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachhangMouseClicked
        int selectedRow = tbKhachhang.getSelectedRow();
        if (selectedRow >= 0) {
            this.txtMakhachhang.setText(tbKhachhang.getValueAt(selectedRow, 0).toString());
            this.txtTendangnhap.setText(tbKhachhang.getValueAt(selectedRow, 1).toString());
            this.txtHoten.setText(tbKhachhang.getValueAt(selectedRow, 2).toString());
            txtEmail.setText(tbKhachhang.getValueAt(selectedRow, 3).toString());
            this.txtSDT.setText(tbKhachhang.getValueAt(selectedRow, 4).toString());
            this.txtDiachi.setText(tbKhachhang.getValueAt(selectedRow, 5).toString());
            this.txtMatkhau.setText(tbKhachhang.getValueAt(selectedRow, 6).toString());
            this.txtDiem.setText(tbKhachhang.getValueAt(selectedRow, 7).toString());

            btnSua.setEnabled(true);
            btnSua.setBackground(new Color(0, 102, 102));
            btnSua.setForeground(Color.WHITE);

            btnXoa.setEnabled(true);
            btnXoa.setBackground(new Color(0, 102, 102));
            btnXoa.setForeground(Color.WHITE);

            // 👉 Đổi lại màu Thêm & Làm Mới về mặc định
            btnThem.setEnabled(false);
            btnThem.setBackground(null);
            btnThem.setForeground(Color.BLACK);

            btnReset.setEnabled(false);
            btnReset.setBackground(null);
            btnReset.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tbKhachhangMouseClicked

    private void txtHotentxtHotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHotentxtHotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHotentxtHotenActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try {
            String makh = this.txtMakhachhang.getText().trim();
            String tendn = this.txtTendangnhap.getText().trim();
            String hoten = this.txtHoten.getText().trim();
            String email = this.txtEmail.getText().trim();
            String sdt = this.txtSDT.getText().trim();
            String diachi = this.txtDiachi.getText().trim();
            String mkhau = this.txtMatkhau.getText().trim();
            int diem = Integer.parseInt(this.txtDiem.getText().trim());

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
            a.setDiemtichluy(diem);
            dao.insertUser(a);
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
            loadTable();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            int selectedRow = tbKhachhang.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để cập nhật.");
                return;
            }

            String makh = this.txtMakhachhang.getText().trim();
            String tendn = this.txtTendangnhap.getText().trim();
            String hoten = this.txtHoten.getText().trim();
            String email = this.txtEmail.getText().trim();
            String sdt = this.txtSDT.getText().trim();
            String diachi = this.txtDiachi.getText().trim();
            String mkhau = this.txtMatkhau.getText().trim();
            int diem = Integer.parseInt(this.txtDiem.getText().trim());

            User a = new User();
            a.setMakhachhang(makh);
            a.setTendangnhap(tendn);
            a.setHoten(hoten);
            a.setEmail(email);
            a.setSdt(sdt);
            a.setDiachi(diachi);
            a.setMatkhau(mkhau);
            a.setDiemtichluy(diem);
            dao.updateUser(a);
            JOptionPane.showMessageDialog(this, "Cập nhật khách hàng thành công");
            loadTable();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int selectedRow = tbKhachhang.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để xoá.");
            return;
        }

        String makh = (String) tbKhachhang.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá khách hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.deleteUser(makh);
            JOptionPane.showMessageDialog(this, "Xoá khách hàng thành công");
            try {
                loadTable();
                clearForm();
            } catch (SQLException ex) {
                Logger.getLogger(quanlyUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();    }//GEN-LAST:event_btnResetActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String keyword = this.txtTimKiem.getText().trim();
        
        if(keyword.isEmpty()) {
            try {
                loadTable();
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
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbKhachhang;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtMakhachhang;
    private javax.swing.JTextField txtMatkhau;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTendangnhap;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
