/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Hoadon;
import java.util.List;
import java.sql.*;
import java.util.*;
import Utils.dbConnect;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kiennguyen
 */
public class HoadonDao {
    
    public List<Hoadon> getAllHoadon() {
        Connection conn = null;
        PreparedStatement statement = null;
        
        List<Hoadon> list = new ArrayList<>();
        
        try {
            conn = dbConnect.dbConnection();
            
        String sql = "SELECT hd.id, hd.ma_hoa_don, hd.id_don_hang, " +
             "hd.ngaythanhtoan, hd.tongtien, hd.ma_kh, hd.diem_sudung , u.id AS idUser, u.vai_tro, " +
             "km.id AS idMagg, km.magg " +
             "FROM hoa_don hd " +
             "JOIN user u ON hd.id_user = u.id " +
             "LEFT JOIN khuyenmai km ON hd.id_ma_gg = km.id " +
             "ORDER BY hd.id ASC";
            
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Hoadon hd = new Hoadon();
                
                hd.setId(rs.getInt("id"));
                hd.setMaHd(rs.getString("ma_hoa_don"));
                hd.setIddonHang(rs.getInt("id_don_hang"));
                hd.setNgayttoan(rs.getDate("ngaythanhtoan"));
                hd.setTongTien(rs.getDouble("tongtien"));
                hd.setMaKH(rs.getString("ma_kh")); // mới thêm
                hd.setIdUser(rs.getInt("idUser"));
                hd.setVaiTro(rs.getString("vai_tro"));
//                hd.setDiem_tichluy(rs.getInt("diem_tich_luy"));
                hd.setIdMagg(rs.getInt("idMagg"));
                hd.setMagg(rs.getString("magg"));
                hd.setDiemDadung(rs.getInt("diem_sudung")); // mới thêm
                list.add(hd);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoadonDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HoadonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    
      public boolean insertHoaDon(Hoadon hd) throws ClassNotFoundException {
        String sql = "INSERT INTO hoa_don (ma_hoa_don, id_don_hang, ngaythanhtoan, tongtien, id_user, id_ma_gg, ma_kh, diem_sudung) " +
                     "VALUES (?, ?, ?, ?, ?, ?,?,?)";

        try (Connection conn = dbConnect.dbConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hd.getMaHd());
            ps.setInt(2, hd.getIddonHang());
            ps.setDate(3, new java.sql.Date(hd.getNgayttoan().getTime()));
            ps.setDouble(4, hd.getTongTien());
            ps.setInt(5, hd.getIdUser());
            ps.setInt(6, hd.getIdMagg());
            ps.setString(7, hd.getMaKH());
            ps.setInt(8, hd.getDiemDadung());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
      
    public int getIdHoaDonByMa(String maHD) {
        int id = -1;
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement ps = conn.prepareStatement("SELECT id FROM hoa_don WHERE ma_hoa_don = ?")) {
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
      
    
    public List<Hoadon> filterbyNgay(LocalDate targetDate){
        Connection conn = null;
        PreparedStatement statement = null;
        
        List<Hoadon> list = new ArrayList<>();
        
        try {
            conn = dbConnect.dbConnection();
            String sql = "SELECT hd.id, hd.ma_hoa_don, hd.id_don_hang, "
                    + "hd.ngaythanhtoan, hd.tongtien, hd.ma_kh, hd.diem_sudung, "
                    + "u.id AS idUser, u.vai_tro, "
                    + "km.id AS idMagg, km.magg "
                    + "FROM hoa_don hd "
                    + "JOIN user u ON hd.id_user = u.id "
                    + "LEFT JOIN khuyenmai km ON hd.id_ma_gg = km.id "
                    + "WHERE hd.ngaythanhtoan = ? "
                    + "ORDER BY hd.id ASC";

            statement = conn.prepareStatement(sql);
            statement.setDate(1, java.sql.Date.valueOf(targetDate)); 
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Hoadon hd = new Hoadon();
                
                hd.setId(rs.getInt("id"));
                hd.setMaHd(rs.getString("ma_hoa_don"));
                hd.setIddonHang(rs.getInt("id_don_hang"));
                hd.setNgayttoan(rs.getDate("ngaythanhtoan"));
                hd.setTongTien(rs.getDouble("tongtien"));
                hd.setMaKH(rs.getString("ma_kh")); // mới thêm
                hd.setIdUser(rs.getInt("idUser"));
                hd.setVaiTro(rs.getString("vai_tro"));
//                hd.setDiem_tichluy(rs.getInt("diem_tich_luy"));
                hd.setIdMagg(rs.getInt("idMagg"));
                hd.setMagg(rs.getString("magg"));
                hd.setDiemDadung(rs.getInt("diem_sudung")); // mới thêm
                list.add(hd);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoadonDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HoadonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public List<Hoadon> filterByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Hoadon> list = new ArrayList<>();
        String sql = "SELECT hd.id, hd.ma_hoa_don, hd.id_don_hang, "
                + "hd.ngaythanhtoan, hd.tongtien, hd.ma_kh, hd.diem_sudung, "
                + "u.id AS idUser, u.vai_tro, "
                + "km.id AS idMagg, km.magg "
                + "FROM hoa_don hd "
                + "JOIN user u ON hd.id_user = u.id "
                + "LEFT JOIN khuyenmai km ON hd.id_ma_gg = km.id "
                + "WHERE DATE(hd.ngaythanhtoan) BETWEEN ? AND ? "
                + "ORDER BY hd.ngaythanhtoan ASC";

        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(startDate));
            stmt.setDate(2, java.sql.Date.valueOf(endDate));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Hoadon hd = new Hoadon();
                hd.setId(rs.getInt("id"));
                hd.setMaHd(rs.getString("ma_hoa_don"));
                hd.setIddonHang(rs.getInt("id_don_hang"));
                hd.setNgayttoan(rs.getDate("ngaythanhtoan"));
                hd.setTongTien(rs.getDouble("tongtien"));
                hd.setMaKH(rs.getString("ma_kh"));
                hd.setIdUser(rs.getInt("idUser"));
                hd.setVaiTro(rs.getString("vai_tro"));
                hd.setIdMagg(rs.getInt("idMagg"));
                hd.setMagg(rs.getString("magg"));
                hd.setDiemDadung(rs.getInt("diem_sudung"));

                list.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Hoadon> search(String mahd) {
        List<Hoadon> list = new ArrayList<>();
        String sql = "SELECT hd.id, hd.ma_hoa_don, hd.id_don_hang, "
                + "hd.ngaythanhtoan, hd.tongtien, hd.ma_kh, hd.diem_sudung, "
                + "u.id AS idUser, u.vai_tro, "
                + "km.id AS idMagg, km.magg "
                + "FROM hoa_don hd "
                + "JOIN user u ON hd.id_user = u.id "
                + "LEFT JOIN khuyenmai km ON hd.id_ma_gg = km.id "
                + "WHERE hd.ma_hoa_don like ? "
                + "ORDER BY hd.ngaythanhtoan ASC";

        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + mahd + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Hoadon hd = new Hoadon();
                hd.setId(rs.getInt("id"));
                hd.setMaHd(rs.getString("ma_hoa_don"));
                hd.setIddonHang(rs.getInt("id_don_hang"));
                hd.setNgayttoan(rs.getDate("ngaythanhtoan"));
                hd.setTongTien(rs.getDouble("tongtien"));
                hd.setMaKH(rs.getString("ma_kh"));
                hd.setIdUser(rs.getInt("idUser"));
                hd.setVaiTro(rs.getString("vai_tro"));
                hd.setIdMagg(rs.getInt("idMagg"));
                hd.setMagg(rs.getString("magg"));
                hd.setDiemDadung(rs.getInt("diem_sudung"));

                list.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    
}
