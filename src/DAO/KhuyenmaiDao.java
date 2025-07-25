/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Khuyenmai;
import Utils.dbConnect;
import java.sql.*;
//import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kiennguyen
 */
public class KhuyenmaiDao {

    private Connection connection;

    public KhuyenmaiDao() {
    }

    public KhuyenmaiDao(Connection connection) {
        this.connection = connection;
    }

    public List<Khuyenmai> getAllKhuyenmai() {
        Connection conn = null;
        PreparedStatement statement = null;
        List<Khuyenmai> list = new ArrayList<>();

  
        try {
            conn = dbConnect.dbConnection();
            String sql = "SELECT km.magg,km.soluong,km.ngaybdau,km.ngaykthuc, km.ap_dung_hoa_don_tu, km.gia_tri, km.hinh_thuc ,u.vai_tro "
                    + "FROM khuyenmai km "
                    + "JOIN user u ON km.ma_nv = u.id";
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Khuyenmai a = new Khuyenmai(rs.getString("magg"),
                        rs.getInt("soluong"),
                        rs.getDate("ngaybdau"),
                        rs.getDate("ngaykthuc"),
                        rs.getDouble("ap_dung_hoa_don_tu"),
                        rs.getDouble("gia_tri"),
                        rs.getString("hinh_thuc"),
                        rs.getString("vai_tro")
                );
                list.add(a);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Khuyenmai> getAll() {
        Connection conn = null;
        PreparedStatement statement = null;
        List<Khuyenmai> list = new ArrayList<>();

        try {
            conn = dbConnect.dbConnection();
            String sql = "SELECT dv.ma_dich_vu, dv.tendichvu, km.ngaybdau, km.ngaykthuc, km.gia_tri, u.vai_tro "
                    + "FROM khuyenmai km "
                    + "JOIN user u ON km.ma_nv = u.id "
                    + "JOIN dich_vu dv ON km.id_dich_vu = dv.id";
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Khuyenmai a = new Khuyenmai();

                a.setMaDv(rs.getString("ma_dich_vu"));
                a.setTenDv(rs.getString("tendichvu"));
                a.setNgaybdau(rs.getDate("ngaybdau"));
                a.setNgaykthuc(rs.getDate("ngaykthuc"));
                a.setGiatri(rs.getDouble("gia_tri"));
                a.setVaitro(rs.getString("vai_tro"));

                list.add(a);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean InsertKhuyenMai(Khuyenmai km) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect.dbConnection();
            String sql = "INSERT INTO khuyenmai"
                    + "(magg, soluong, ngaybdau, ngaykthuc, ap_dung_hoa_don_tu, gia_tri, hinh_thuc, ma_nv) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            statement = conn.prepareStatement(sql);

            statement.setString(1, km.getMagg());
            statement.setInt(2, km.getSoluong());
            statement.setDate(3, new java.sql.Date(km.getNgaybdau().getTime()));
            statement.setDate(4, new java.sql.Date(km.getNgaykthuc().getTime()));
            statement.setDouble(5, km.getApdunghd());
            statement.setDouble(6, km.getGiatri());
            statement.setString(7, km.getHinhThuc());
            statement.setInt(8, km.getMaNV());
            return statement.executeUpdate() > 0;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean updateKhuyenMai(Khuyenmai km) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect.dbConnection();
            String sql = "UPDATE khuyenmai SET "
                    + "magg = ?, soluong = ?, ngaybdau = ?, ngaykthuc = ?, "
                    + "ap_dung_hoa_don_tu = ?, gia_tri = ?, hinh_thuc = ?, ma_nv = ? "
                    + "WHERE magg = ?";
            statement = conn.prepareStatement(sql);

            statement.setString(1, km.getMagg());
            statement.setInt(2, km.getSoluong());
            statement.setDate(3, new java.sql.Date(km.getNgaybdau().getTime()));
            statement.setDate(4, new java.sql.Date(km.getNgaykthuc().getTime()));
            statement.setDouble(5, km.getApdunghd());
            statement.setDouble(6, km.getGiatri());
            statement.setString(7, km.getHinhThuc());
            statement.setInt(8, km.getMaNV());
            statement.setString(9, km.getMagg());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean insertKhuyenMaiDV(Khuyenmai km) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect.dbConnection();
            String sql = "INSERT INTO khuyenmai"
                    + "(id_dich_vu, magg, ngaybdau, ngaykthuc, gia_tri, ma_nv) "
                    + "VALUES(?, ?, ?, ?, ?, ?)";

            statement = conn.prepareStatement(sql);
            System.out.println("ma_nv = " + km.getMaNV());

            
            statement.setInt(1, km.getIdDvu());
            statement.setString(2, km.getMagg());
            statement.setDate(3, new java.sql.Date(km.getNgaybdau().getTime()));
            statement.setDate(4, new java.sql.Date(km.getNgaykthuc().getTime()));
            statement.setDouble(5, km.getGiatri());
            statement.setInt(6, km.getMaNV());
         

            return statement.executeUpdate() > 0;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    

    public boolean updateKhuyenMaiDV(Khuyenmai km) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect.dbConnection();
            String sql = "UPDATE khuyenmai SET id_dich_vu = ?, ngaybdau = ?, ngaykthuc = ?, gia_tri = ?, ma_nv = ? WHERE id= ?";

            statement = conn.prepareStatement(sql);

            statement.setInt(1, km.getIdDvu());
            statement.setDate(2, new java.sql.Date(km.getNgaybdau().getTime()));
            statement.setDate(3, new java.sql.Date(km.getNgaykthuc().getTime()));
            statement.setDouble(4, km.getGiatri());
            statement.setInt(5, km.getMaNV()); 
            statement.setInt(6, km.getId());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean deleteKhuyenMaiDV(int id) {
        String sql = "DELETE FROM khuyenmai WHERE id = ?";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getIdByMaDV(String magg) {
        String sql = "SELECT id FROM khuyenmai WHERE magg = ?";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, magg);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int getIdDichVuByMaDv(String maDv) {
        String sql = "SELECT id FROM dich_vu WHERE ma_dich_vu = ?";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maDv);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    
    
    public int getIdByMaDV(int idDichVu, Date ngayBD, Date ngayKT) {
        String sql = "SELECT id FROM khuyenmai WHERE id_dich_vu = ? AND ngaybdau = ? AND ngaykthuc = ?";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDichVu);
            stmt.setDate(2, new java.sql.Date(ngayBD.getTime()));
            stmt.setDate(3, new java.sql.Date(ngayKT.getTime()));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // không tìm thấy
    }
    
    
    
    public String getMaggByMaDVAndDate(int maDV, Date ngayBD, Date ngayKT) {
        String sql = "SELECT magg FROM khuyenmai WHERE id_dich_vu = ? AND ngaybdau = ? AND ngaykthuc = ?";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maDV);
            stmt.setDate(2, ngayBD);
            stmt.setDate(3, ngayKT);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("magg");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public Khuyenmai getKhuyenmai(String magg) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        
        try {
            conn = dbConnect.dbConnection();
            String sql = "select * from khuyenmai where magg = ? AND soluong > 0";
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, magg);
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Khuyenmai km = new Khuyenmai();
                km.setId(rs.getInt("id"));
                km.setMagg(rs.getString("magg"));
                km.setSoluong(rs.getInt("soluong"));
                km.setNgaybdau(rs.getDate("ngaybdau"));
                km.setNgaykthuc(rs.getDate("ngaykthuc"));
                km.setGiatri(rs.getDouble("gia_tri"));
                km.setHinhThuc(rs.getString("hinh_thuc")); // tien hoặc phan_tram
                return km;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean giamSoluongKM(String magg) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect.dbConnection();
            String sql = "update khuyenmai set soluong = soluong - 1 Where magg =? AND soluong > 0";
            statement = conn.prepareStatement(sql);

            statement.setString(1, magg);

            return statement.executeUpdate() > 0;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

}
