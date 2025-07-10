/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Chitietlichdat;
import DTO.Lichdat;
import Utils.dbConnect;
import java.util.List;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kiennguyen
 */
public class LichdatDao {

    private Connection connection;

    public LichdatDao() {
        // Nếu cần khởi tạo gì đó thì viết vào đây, còn không thì để trống
    }

    public LichdatDao(Connection connection) {
        this.connection = connection;
    }

    public List<Lichdat> getAllLichdat() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Lichdat> list = new ArrayList<>();

        try {
            connection = dbConnect.dbConnection();
            String sql = "SELECT l.ma_lich_thue, k.hoten, k.sdt, l.ngaysudung "
                    + "FROM lich_thue l "
                    + "JOIN user k ON l.id_khach_hang = k.id";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Lichdat a = new Lichdat();
                a.setMaLichThue(rs.getString("ma_lich_thue"));
                a.setTenKhachHang(rs.getString("hoten"));
                a.setSdt(rs.getInt("sdt"));
                a.setNgaySuDung(rs.getDate("ngaysudung"));

                list.add(a);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LichdatDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LichdatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Chitietlichdat> getAllLichdatchitiet(String maDichvu) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Chitietlichdat> list = new ArrayList<>();

        try {
            connection = dbConnect.dbConnection();
           String sql = "SELECT d.ma_dich_vu, d.tendichvu, c.soluong, c.dongia, c.ngay_su_dung, c.ghi_chu "
                   + "FROM chi_tiet_lich_thue c "
                   + "JOIN dich_vu d ON c.id_dich_vu = d.id "
                   + "JOIN lich_thue l ON c.id_lich_thue = l.id "
                   + "WHERE l.ma_lich_thue = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, maDichvu);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Chitietlichdat a = new Chitietlichdat();
                a.setMaDV(rs.getString("ma_dich_vu"));
                a.setTenDV(rs.getString("tendichvu"));
                a.setSoLuong(rs.getInt("soluong"));
                a.setDonGia(rs.getDouble("dongia"));
                a.setNgay(rs.getDate("ngay_su_dung"));
                a.setGhiChu(rs.getString("ghi_chu"));

                list.add(a);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LichdatDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LichdatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
