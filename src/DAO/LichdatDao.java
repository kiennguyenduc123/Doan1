/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Chitiethoadon;
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
            String sql = "SELECT l.ma_lich_thue, k.hoten, k.sdt, l.ngaysudung, l.tinhtrang "
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
                a.setTinhTrang(rs.getString("tinhtrang"));
                
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
    
    
     public boolean insertChiTietLich( String maLichThue , Chitietlichdat chiTiet) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO chi_tiet_lich_thue(id_lich_thue, id_dich_vu, soluong, dongia, ngay_su_dung, ghi_chu) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn  = dbConnect.dbConnection();
            ps = conn.prepareStatement(sql);

            int idLichThue = getIdLichThueByMa(maLichThue);
            ps.setInt(1, idLichThue);
//            ps.setInt(1, chiTiet.getIdLichThue());                      // Mã lịch thuê
            ps.setInt(2, chiTiet.getIdDichVu());               // Mã dịch vụ
            ps.setInt(3, chiTiet.getSoLuong());              // Số lượng
            ps.setDouble(4, chiTiet.getDonGia());             // Đơn giá
            ps.setDate(5, new java.sql.Date(chiTiet.getNgay().getTime())); // Ngày sử dụng (java.sql.Date)
            ps.setString(6, chiTiet.getGhiChu());             // Ghi chú

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }
     
    public int getIdLichThueByMa(String maLichThue) {
        String sql = "SELECT id FROM lich_thue WHERE ma_lich_thue = ?";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maLichThue);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // hoặc throw exception
    }
     
    
    public boolean InsertLichdat(Lichdat ld) {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = dbConnect.dbConnection();
            String sql = "insert into lich_thue(ma_lich_thue, id_khach_hang, ngaydat, ngaysudung, ngay_tra, nhan_vien_id, tinhtrang) values(?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, ld.getMaLichThue());
            statement.setInt(2, ld.getIdKhachHang());
            statement.setDate(3, new java.sql.Date(ld.getNgayDat().getTime()));
            statement.setDate(4, new java.sql.Date(ld.getNgaySuDung().getTime()));
            statement.setDate(5, new java.sql.Date(ld.getNgayTra().getTime()));
            statement.setInt(6, ld.getNhanVienId());
            statement.setString(7, ld.getTinhTrang());
            
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LichdatDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LichdatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getKhachHangIdByTen(String tenKH) throws SQLException, ClassNotFoundException {
        Connection connection = dbConnect.dbConnection();
        String sql = "SELECT id FROM user WHERE hoten = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, tenKH);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }
    
    public List<Lichdat> searchLichdat(String keyword) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Lichdat> list = new ArrayList<>();

        try {
            connection = dbConnect.dbConnection();
            String sql = "SELECT l.ma_lich_thue, k.hoten, k.sdt, l.ngaysudung, l.tinhtrang "
                    + "FROM lich_thue l "
                    + "JOIN user k ON l.id_khach_hang = k.id "
                    + "WHERE k.hoten LIKE ? OR l.ma_lich_thue LIKE ?";

            statement = connection.prepareStatement(sql);
            String searchKey = "%" + keyword + "%";
            statement.setString(1, searchKey);
            statement.setString(2, searchKey);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Lichdat a = new Lichdat();
                a.setMaLichThue(rs.getString("ma_lich_thue"));
                a.setTenKhachHang(rs.getString("hoten"));
                a.setSdt(rs.getInt("sdt"));
                a.setNgaySuDung(rs.getDate("ngaysudung"));
                a.setTinhTrang(rs.getString("tinhtrang"));

                list.add(a);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LichdatDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    
//    public List<Chitiethoadon> getChiTietHoaDonTuLich(int idLich) {
//        List<Chitiethoadon> list = new ArrayList<>();
//        String sql = "SELECT ld.id_dich_vu, dv.ma_dich_vu, dv.ten_dich_vu, ld.so_luong, ld.don_gia "
//                + "FROM chi_tiet_lich_dat ld "
//                + "JOIN dich_vu dv ON ld.id_dich_vu = dv.id "
//                + "WHERE ld.id_lich_thue = ?";
//
//        try (Connection conn = dbConnect.dbConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, idLich);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Chitiethoadon ct = new Chitiethoadon();
//                ct.setId_dich_vu(rs.getInt("id_dich_vu"));
//                ct.setMa_dich_vu(rs.getString("ma_dich_vu"));
//                ct.setTen_dich_vu(rs.getString("ten_dich_vu"));
//                ct.setSoLuong(rs.getInt("so_luong"));
//                ct.setDonGia(rs.getDouble("don_gia"));
//                ct.setTongTien(ct.getSoLuong() * ct.getDonGia());
//                list.add(ct);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }

    
    
}
