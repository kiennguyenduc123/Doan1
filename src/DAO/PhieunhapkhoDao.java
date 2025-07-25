/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Hoadon;
import DTO.Phieunhapkho;
import Utils.dbConnect;
import java.util.List;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Kiennguyen
 */
public class PhieunhapkhoDao {
    public List<Phieunhapkho> getAllPhieu() {
        Connection con = null;
        PreparedStatement statement = null;
        List<Phieunhapkho> list = new ArrayList<>();
        
        try {
            con = dbConnect.dbConnection();
            String sql = "SELECT pn.id, pn.ma_phieu_nhap, dv.id AS dich_vu_id, dv.ma_dich_vu, pn.user_id, "
                    + "pn.so_luong, pn.ngay_nhap, pn.gia_nhap, pn.ghi_chu, pn.nha_cung_cap_id, "
                    + "ncc.tenncc, u.vai_tro "
                    + "FROM kho_nhap_dich_vu pn "
                    + "JOIN dich_vu dv ON pn.dich_vu_id = dv.id "
                    + "JOIN user u ON pn.user_id = u.id "
                    + "JOIN nhacc ncc ON pn.nha_cung_cap_id = ncc.id "
                    + "ORDER BY CAST(SUBSTRING(pn.ma_phieu_nhap, 4) AS UNSIGNED) ASC";

            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Phieunhapkho pnk = new Phieunhapkho();
                
                pnk.setId(rs.getInt("id"));
                pnk.setMaphieuNhap(rs.getString("ma_phieu_nhap"));
                pnk.setDichvu_id(rs.getInt("dich_vu_id"));
                pnk.setMaDichvu(rs.getString("ma_dich_vu"));  // giả sử cột mã trong bảng `dichvu`
                pnk.setUser_id(rs.getInt("user_id"));
                pnk.setSoLuong(rs.getInt("so_luong"));
                pnk.setNgayNhap(rs.getDate("ngay_nhap"));
                pnk.setGiaNhap(rs.getDouble("gia_nhap"));
                pnk.setGhiChu(rs.getString("ghi_chu"));
                pnk.setNhacc_id(rs.getInt("nha_cung_cap_id"));
                pnk.setTenNhacc(rs.getString("tenncc"));
                pnk.setVaiTro(rs.getString("vai_tro"));
                list.add(pnk);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieunhapkhoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieunhapkhoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
  public List<Phieunhapkho> searchPhieuNhapAdvanced(String keyword) {
    Connection con = null;
    PreparedStatement statement = null;
    List<Phieunhapkho> list = new ArrayList<>();

    try {
        con = dbConnect.dbConnection();
        String sql = "SELECT pn.id, pn.ma_phieu_nhap, dv.id AS dich_vu_id, dv.ma_dich_vu, pn.user_id, "
                   + "pn.so_luong, pn.ngay_nhap, pn.gia_nhap, pn.ghi_chu, pn.nha_cung_cap_id, "
                   + "ncc.tenncc, u.vai_tro "
                   + "FROM kho_nhap_dich_vu pn "
                   + "JOIN dich_vu dv ON pn.dich_vu_id = dv.id "
                   + "JOIN user u ON pn.user_id = u.id "
                   + "JOIN nhacc ncc ON pn.nha_cung_cap_id = ncc.id "
                   + "WHERE pn.ma_phieu_nhap LIKE ? "
                   + "OR dv.ma_dich_vu LIKE ? "
                   + "OR ncc.tenncc LIKE ? ";

        statement = con.prepareStatement(sql);
        String query = "%" + keyword + "%";
        for (int i = 1; i <= 4; i++) {
            statement.setString(i, query);
        }

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Phieunhapkho pnk = new Phieunhapkho();
            pnk.setId(rs.getInt("id"));
            pnk.setMaphieuNhap(rs.getString("ma_phieu_nhap"));
            pnk.setDichvu_id(rs.getInt("dich_vu_id"));
            pnk.setMaDichvu(rs.getString("ma_dich_vu"));
            pnk.setUser_id(rs.getInt("user_id"));
            pnk.setSoLuong(rs.getInt("so_luong"));
            pnk.setNgayNhap(rs.getDate("ngay_nhap"));
            pnk.setGiaNhap(rs.getDouble("gia_nhap"));
            pnk.setGhiChu(rs.getString("ghi_chu"));
            pnk.setNhacc_id(rs.getInt("nha_cung_cap_id"));
            pnk.setTenNhacc(rs.getString("tenncc"));
            list.add(pnk);
        }

    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(PhieunhapkhoDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (statement != null) statement.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return list;
}


    
     public List<Phieunhapkho> filterbyNgay(LocalDate targetDate){
        Connection conn = null;
        PreparedStatement statement = null;
        
        List<Phieunhapkho> list = new ArrayList<>();
        
        try {
            conn = dbConnect.dbConnection();
            String sql = "SELECT pn.id, pn.ma_phieu_nhap, dv.id AS dich_vu_id, dv.ma_dich_vu, pn.user_id, "
                    + "pn.so_luong, pn.ngay_nhap, pn.gia_nhap, pn.ghi_chu, pn.nha_cung_cap_id, "
                    + "ncc.tenncc, u.vai_tro "
                    + "FROM kho_nhap_dich_vu pn "
                    + "JOIN dich_vu dv ON pn.dich_vu_id = dv.id "
                    + "JOIN user u ON pn.user_id = u.id "
                    + "JOIN nhacc ncc ON pn.nha_cung_cap_id = ncc.id "
                    + "WHERE pn.ngay_nhap = ?";

            statement = conn.prepareStatement(sql);
            statement.setDate(1, java.sql.Date.valueOf(targetDate)); 
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Phieunhapkho pnk = new Phieunhapkho();
                pnk.setId(rs.getInt("id"));
                pnk.setMaphieuNhap(rs.getString("ma_phieu_nhap"));
                pnk.setDichvu_id(rs.getInt("dich_vu_id"));
                pnk.setMaDichvu(rs.getString("ma_dich_vu"));
                pnk.setUser_id(rs.getInt("user_id"));
                pnk.setSoLuong(rs.getInt("so_luong"));
                pnk.setNgayNhap(rs.getDate("ngay_nhap"));
                pnk.setGiaNhap(rs.getDouble("gia_nhap"));
                pnk.setGhiChu(rs.getString("ghi_chu"));
                pnk.setNhacc_id(rs.getInt("nha_cung_cap_id"));
                pnk.setTenNhacc(rs.getString("tenncc"));
                list.add(pnk);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoadonDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HoadonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
      public List<Phieunhapkho> filterByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Phieunhapkho> list = new ArrayList<>();
        
          String sql = "SELECT pn.id, pn.ma_phieu_nhap, dv.id AS dich_vu_id, dv.ma_dich_vu, pn.user_id, "
                    + "pn.so_luong, pn.ngay_nhap, pn.gia_nhap, pn.ghi_chu, pn.nha_cung_cap_id, "
                    + "ncc.tenncc, u.vai_tro "
                    + "FROM kho_nhap_dich_vu pn "
                    + "JOIN dich_vu dv ON pn.dich_vu_id = dv.id "
                    + "JOIN user u ON pn.user_id = u.id "
                    + "JOIN nhacc ncc ON pn.nha_cung_cap_id = ncc.id "
                    + "WHERE DATE(pn.ngay_nhap) BETWEEN ? AND ?";

        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(startDate));
            stmt.setDate(2, java.sql.Date.valueOf(endDate));

            ResultSet rs = stmt.executeQuery();

          while(rs.next()) {
                Phieunhapkho pnk = new Phieunhapkho();
                pnk.setId(rs.getInt("id"));
                pnk.setMaphieuNhap(rs.getString("ma_phieu_nhap"));
                pnk.setDichvu_id(rs.getInt("dich_vu_id"));
                pnk.setMaDichvu(rs.getString("ma_dich_vu"));
                pnk.setUser_id(rs.getInt("user_id"));
                pnk.setSoLuong(rs.getInt("so_luong"));
                pnk.setNgayNhap(rs.getDate("ngay_nhap"));
                pnk.setGiaNhap(rs.getDouble("gia_nhap"));
                pnk.setGhiChu(rs.getString("ghi_chu"));
                pnk.setNhacc_id(rs.getInt("nha_cung_cap_id"));
                pnk.setTenNhacc(rs.getString("tenncc"));
                list.add(pnk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
      
       public String getNextMaPhieu() {
        String lastID = "";
        try {
            Connection con = dbConnect.dbConnection();
            String sql = "SELECT MAX(ma_phieu_nhap) FROM kho_nhap_dich_vu";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lastID = rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int nextNum = 1;
        if (lastID != null && lastID.startsWith("MPN")) {
            try {
                nextNum = Integer.parseInt(lastID.substring(3)) + 1;
            } catch (Exception e) {
                nextNum = 1;
            }
        }

        return String.format("MPN%02d", nextNum);  // ví dụ: MPN01
    }
       
    public boolean insert(Phieunhapkho obj) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO kho_nhap_dich_vu (ma_phieu_nhap, dich_vu_id, user_id, "
                   + "so_luong, ngay_nhap, gia_nhap, ghi_chu, nha_cung_cap_id, created_at, updated_at) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

        try (Connection con = dbConnect.dbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, obj.getMaphieuNhap());
            ps.setInt(2, obj.getDichvu_id());
            ps.setInt(3, obj.getUser_id());
            ps.setInt(4, obj.getSoLuong());
            ps.setDate(5, new java.sql.Date(obj.getNgayNhap().getTime()));
            ps.setDouble(6, obj.getGiaNhap());
            ps.setString(7, obj.getGhiChu());
            ps.setInt(8, obj.getNhacc_id());

            return ps.executeUpdate() > 0;
        }
    }
    
     public boolean delete(String maPhieu, int dichVuId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM kho_nhap_dich_vu WHERE ma_phieu_nhap = ? AND dich_vu_id = ?";
        try (Connection con = dbConnect.dbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maPhieu);
            ps.setInt(2, dichVuId);
            return ps.executeUpdate() > 0;
        }
    }
    
      public List<Phieunhapkho> getByMaPhieu(String maPhieu) throws SQLException, ClassNotFoundException {
        List<Phieunhapkho> list = new ArrayList<>();
        String sql = "SELECT * FROM kho_nhap_dich_vu WHERE ma_phieu_nhap = ?";
        try (Connection con = dbConnect.dbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Phieunhapkho item = new Phieunhapkho();
                item.setMaphieuNhap(rs.getString("ma_phieu_nhap"));
                item.setDichvu_id(rs.getInt("dich_vu_id"));
                item.setSoLuong(rs.getInt("so_luong"));
                item.setGiaNhap(rs.getDouble("gia_nhap"));
                item.setNgayNhap(rs.getDate("ngay_nhap"));
                item.setGhiChu(rs.getString("ghi_chu"));
                list.add(item);
            }
        }
        return list;
    }
    
       
}
