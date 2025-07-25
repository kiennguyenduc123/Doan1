/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Chitiethoadon;
import DTO.Chitietlichdat;
import Utils.dbConnect;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Kiennguyen
 */
public class ChiTietHoaDonDao {
    private List<Chitietlichdat> lichThueList;
  
    
    public ChiTietHoaDonDao() {
    }
    
  public List<Chitiethoadon> getChiTietHoaDonById(String idHoaDon) {
    List<Chitiethoadon> list = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = dbConnect.dbConnection();

        String sql = "SELECT cthd.id, dv.ma_dich_vu, dv.tendichvu, cthd.id_hoa_don, cthd.id_dich_vu , cthd.don_gia, cthd.so_luong "
                   + "FROM chi_tiet_hoa_don cthd "
                   + "JOIN dich_vu dv ON cthd.id_dich_vu = dv.id "
                   + "WHERE cthd.id_hoa_don = ?";
        
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, idHoaDon);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Chitiethoadon ct = new Chitiethoadon();
            ct.setId(rs.getInt("id"));
            ct.setMa_dich_vu(rs.getString("ma_dich_vu"));
            ct.setTen_dich_vu(rs.getString("tendichvu"));
            ct.setId_hoa_don(rs.getInt("id_hoa_don"));
            ct.setId_dich_vu(rs.getInt("id_dich_vu"));
            ct.setDonGia(rs.getDouble("don_gia"));
            ct.setSoLuong(rs.getInt("so_luong"));

            // Truy vấn thêm các lịch thuê tương ứng:
            List<Chitietlichdat> lichThueList = getLichThueByChiTietHoaDonId(ct.getId(), conn);
            ct.setLichThueList(lichThueList);

            list.add(ct);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return list;
} 
  
    private List<Chitietlichdat> getLichThueByChiTietHoaDonId(int idChiTietHoaDon, Connection conn) throws SQLException {
        List<Chitietlichdat> list = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_lich_thue WHERE id_chi_tiet_hoa_don = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idChiTietHoaDon);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Chitietlichdat lich = new Chitietlichdat();
                lich.setId(rs.getInt("id"));
                lich.setIdChitiethdon(rs.getInt("id_chi_tiet_hoa_don"));
                lich.setIdLichThue(rs.getInt("id_lich_thue"));
                lich.setSoLuong(rs.getInt("soluong"));
                lich.setDonGia(rs.getDouble("dongia"));
                lich.setNgay(rs.getDate("ngay_su_dung"));
                lich.setGhiChu(rs.getString("ghi_chu"));

                list.add(lich);
            }
        }
        return list;
    }
    
    public static int getIdHoaDonByMa(String maHoaDon) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int idHoaDon = -1;

        try {
            conn = dbConnect.dbConnection();
            
            String sql = "select id from hoa_don where ma_hoa_don = ?";
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, maHoaDon);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                idHoaDon = rs.getInt("id");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietHoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idHoaDon;
    }
    
        public boolean insertChiTietHoaDon(int idHoaDon, List<Chitiethoadon> chiTietList) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO chi_tiet_hoa_don (id_hoa_don, id_dich_vu, so_luong, don_gia) VALUES (?, ?, ?, ?)";

        try {
            conn = dbConnect.dbConnection();
            conn.setAutoCommit(false); // Tạm thời ngắt commit tự động để rollback nếu lỗi

            stmt = conn.prepareStatement(sql);
            for (Chitiethoadon ct : chiTietList) {
                stmt.setInt(1, idHoaDon);
                stmt.setInt(2, ct.getId_dich_vu());
                stmt.setInt(3, ct.getSoLuong());
                stmt.setDouble(4, ct.getDonGia());
                stmt.addBatch(); // Gộp để tăng hiệu suất
            }

            stmt.executeBatch(); // Chạy tất cả lệnh thêm
            conn.commit(); // Chỉ commit nếu không lỗi
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback nếu có lỗi
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true); // Khôi phục lại chế độ mặc định
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getChiTietHoaDonIdByMaLich(String maLich) {
        int id = -1;
        String sql = "SELECT ctl.id_chi_tiet_hoa_don "
                + "FROM chi_tiet_lich_thue ctl "
                + "JOIN lich_thue lt ON ctl.id_lich_thue = lt.id "
                + "WHERE lt.ma_lich_thue = ? ";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maLich);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_chi_tiet_hoa_don");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public List<Chitiethoadon> getChiTietHoaDonFromLich(int idLich) {
        List<Chitiethoadon> list = new ArrayList<>();
        String sql = "SELECT dv.id AS id_dich_vu, dv.tendichvu, dv.ma_dich_vu, ctl.soluong, ctl.dongia "
                + "FROM chi_tiet_lich_thue ctl "
                + "JOIN lich_thue lt ON ctl.id_lich_thue = lt.id "
                + "JOIN dich_vu dv ON ctl.id_dich_vu = dv.id "
                + // hoặc ctl.id_dich_vu nếu lưu ở đó
                "WHERE lt.id = ?";

        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLich);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Chitiethoadon ct = new Chitiethoadon();
                ct.setId_dich_vu(rs.getInt("id_dich_vu"));
                ct.setTen_dich_vu(rs.getString("tendichvu"));
                ct.setMa_dich_vu(rs.getString("ma_dich_vu"));
                ct.setSoLuong(rs.getInt("soluong"));
                ct.setDonGia(rs.getDouble("dongia"));

                list.add(ct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Chitiethoadon> getAllChitiet() {
    List<Chitiethoadon> list = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = dbConnect.dbConnection();

        String sql = "SELECT cthd.id, dv.ma_dich_vu, dv.tendichvu, cthd.id_hoa_don, cthd.id_dich_vu , cthd.don_gia, cthd.so_luong "
                   + "FROM chi_tiet_hoa_don cthd "
                   + "JOIN dich_vu dv ON cthd.id_dich_vu = dv.id";
        
        stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Chitiethoadon ct = new Chitiethoadon();
            ct.setId(rs.getInt("id"));
            ct.setMa_dich_vu(rs.getString("ma_dich_vu"));
            ct.setTen_dich_vu(rs.getString("tendichvu"));
            ct.setId_hoa_don(rs.getInt("id_hoa_don"));
            ct.setId_dich_vu(rs.getInt("id_dich_vu"));
            ct.setDonGia(rs.getDouble("don_gia"));
            ct.setSoLuong(rs.getInt("so_luong"));

            list.add(ct);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return list;
}

}
