/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.User;
import Utils.dbConnect;
import Utils.PasswordUtils;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kiennguyen
 */
public class UserDao {

    
    public List<User> getUserList() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "select * from user where vai_tro = 'customer'";
            statement = con.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User us = new User(
                );
                
                us.setId(rs.getInt("id"));
                us.setMakhachhang(rs.getString("ma_khachhang"));
                us.setTendangnhap(rs.getString("tendangnhap"));
                us.setMatkhau(rs.getString("matkhau"));
                us.setHoten(rs.getString("hoten"));
                us.setSdt(rs.getString("sdt"));
                us.setEmail(rs.getString("email"));
                us.setDiachi(rs.getString("dia_chi"));
                us.setVaitro(rs.getString("vai_tro")); // Chú ý: `vai_tro` đúng theo tên cột trong DB
                us.setDiemtichluy(rs.getInt("diem_tich_luy"));
                list.add(us);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if(statement != null) {
                statement.close();
            }
            if(con != null) {
                con.close();
            }
        }   
        return list;
    }
    
    public User login(String username, String password) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "select * from user where tendangnhap = ? AND matkhau = ?";
            statement = con.prepareStatement(sql);

            statement.setString(1, username);
            statement.setString(2, PasswordUtils.hashPassword(password));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User us = new User();

                us.setId(rs.getInt("id"));
                us.setMakhachhang(rs.getString("ma_khachhang"));
                us.setTendangnhap(rs.getString("tendangnhap"));
                us.setMatkhau(rs.getString("matkhau"));
                us.setHoten(rs.getString("hoten"));
                us.setSdt(rs.getString("sdt"));
                us.setEmail(rs.getString("email"));
                us.setDiachi(rs.getString("dia_chi"));
                us.setVaitro(rs.getString("vai_tro")); // Chú ý: `vai_tro` đúng theo tên cột trong DB
                us.setDiemtichluy(rs.getInt("diem_tich_luy"));
                return us;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean register(User user) {
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = dbConnect.dbConnection();
            String sql = "insert into user(tendangnhap, matkhau, hoten, sdt, email, dia_chi, vai_tro) values (?,?,?,?,?,?,?)";
            statement = con.prepareStatement(sql);
            
            statement.setString(1 , user.getTendangnhap());
            statement.setString(2, PasswordUtils.hashPassword(user.getMatkhau()));
            statement.setString(3, user.getHoten());
            statement.setString(4, user.getSdt());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getDiachi());
            statement.setString(7, user.getVaitro());
            
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public boolean insertUser(User kh) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "insert into user (ma_khachhang, tendangnhap, hoten, email, sdt, dia_chi, matkhau ,diem_tich_luy) values(?,?,?,?,?,?,?,?)";
            statement = con.prepareStatement(sql);
            
            statement.setString(1, kh.getMakhachhang());
            statement.setString(2, kh.getTendangnhap());
            statement.setString(3, kh.getHoten());
            statement.setString(4, kh.getEmail());
            statement.setString(5, kh.getSdt());
            statement.setString(6, kh.getDiachi());
            statement.setString(7, kh.getMatkhau());
            statement.setInt(8, kh.getDiemtichluy());
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateUser(User kh) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "update user set ma_khachhang = ?, tendangnhap = ?, hoten = ?, email = ?, sdt = ?, dia_chi = ?, matkhau = ?, diem_tich_luy = ? where ma_khachhang = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, kh.getMakhachhang());
            statement.setString(2, kh.getTendangnhap());
            statement.setString(3, kh.getHoten());
            statement.setString(4, kh.getEmail());
            statement.setString(5, kh.getSdt());
            statement.setString(6, kh.getDiachi());
            statement.setString(7, kh.getMatkhau());
            statement.setInt(8, kh.getDiemtichluy());
            statement.setString(9, kh.getMakhachhang());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
      
      public boolean deleteUser(String maKH) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "delete from user where ma_khachhang = ?";
            statement = con.prepareStatement(sql);
            
           
            statement.setString(1, maKH);
            
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
      
      public List<User> searchUser(String keyword) throws SQLException {
        List<User> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "SELECT * FROM user WHERE "
                    + "ma_khachhang LIKE ? OR "
                    + "tendangnhap LIKE ? OR "
                    + "hoten LIKE ? OR "
                    + "sdt LIKE ? OR "
                    + "email LIKE ?";
            statement = con.prepareStatement(sql);
            String query = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) {
                statement.setString(i, query);
            }
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User us = new User(
                        rs.getString("ma_khachhang"),
                        rs.getString("tendangnhap"),
                        rs.getString("hoten"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("dia_chi"),
                        rs.getString("matkhau"),
                        rs.getString("vai_tro"),
                        rs.getInt("diem_tich_luy")
                );
                list.add(us);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if(statement != null) {
                statement.close();
            }
            if(con != null) {
                con.close();
            }
        }   
        return list;
    }
      
      public User getById(String maKH) {
        User kh = null;
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = dbConnect.dbConnection();
            String sql = "SELECT * FROM user WHERE ma_KhachHang = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, maKH);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                kh = new User();
                kh.setMakhachhang(rs.getString("ma_KhachHang"));
                kh.setHoten(rs.getString("hoten"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiemtichluy(rs.getInt("diem_tich_luy"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kh;
    }
      
        public boolean updateDiem(String maKH, int diemMoi) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnect.dbConnection();
            String sql = "UPDATE user SET diem_tich_luy = ? WHERE ma_khachhang = ?";

            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, diemMoi);
            statement.setString(2, maKH);
            int affected = statement.executeUpdate();

            return affected > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LichdatDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LichdatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
                return false;
    }
        
    
     public List<User> getNhanvienList() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "select * from user where vai_tro = 'employee' ";
            statement = con.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User us = new User(
                );
                 us.setId(rs.getInt("id"));
                us.setMakhachhang(rs.getString("ma_khachhang"));
                us.setTendangnhap(rs.getString("tendangnhap"));
                us.setHoten(rs.getString("hoten"));
                us.setSdt(rs.getString("sdt"));
                us.setEmail(rs.getString("email"));
                us.setDiachi(rs.getString("dia_chi"));
                us.setMatkhau(rs.getString("matkhau"));
                us.setVaitro(rs.getString("vai_tro")); // Chú ý: `vai_tro` đúng theo tên cột trong DB
                us.setDiemtichluy(rs.getInt("diem_tich_luy"));
                list.add(us);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if(statement != null) {
                statement.close();
            }
            if(con != null) {
                con.close();
            }
        }   
        return list;
    }
      public boolean insertNhanvien(User kh) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "insert into user (ma_khachhang, tendangnhap, hoten, email, sdt, dia_chi, matkhau, vai_tro) values(?,?,?,?,?,?,?,?)";
            statement = con.prepareStatement(sql);
            
            statement.setString(1, kh.getMakhachhang());
            statement.setString(2, kh.getTendangnhap());
            statement.setString(3, kh.getHoten());
            statement.setString(4, kh.getEmail());
            statement.setString(5, kh.getSdt());
            statement.setString(6, kh.getDiachi());
            statement.setString(7, kh.getMatkhau());
            statement.setString(8, kh.getVaitro());
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
      public boolean updateNhanvien(User kh) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "update user set ma_khachhang = ?,  tendangnhap =?, hoten = ?, email = ?, sdt = ?, dia_chi = ?, matkhau = ?, vai_tro = ? where ma_khachhang = ?";
            statement = con.prepareStatement(sql);

           statement.setString(1, kh.getMakhachhang());
            statement.setString(2, kh.getTendangnhap());
            statement.setString(3, kh.getHoten());
            statement.setString(4, kh.getEmail());
            statement.setString(5, kh.getSdt());
            statement.setString(6, kh.getDiachi());
            statement.setString(7, kh.getMatkhau());
            statement.setString(8, kh.getVaitro());
            statement.setString(9, kh.getMakhachhang());



            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
      public boolean deleteNhanvien(String maKH) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "delete from user where ma_khachhang = ?";
            statement = con.prepareStatement(sql);
            
           
            statement.setString(1, maKH);
            
            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
      
    public List<User> searchNhanvien(String keyword) throws SQLException {
        List<User> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "SELECT * FROM user WHERE "
                    + "ma_khachhang LIKE ? OR "
                    + "tendangnhap LIKE ? OR "
                    + "hoten LIKE ? OR "
                    + "sdt LIKE ? OR "
                    + "email LIKE ?";
            statement = con.prepareStatement(sql);
            String query = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) {
                statement.setString(i, query);
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User us = new User(
                        rs.getString("ma_khachhang"),
                        rs.getString("tendangnhap"),
                        rs.getString("hoten"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("dia_chi"),
                        rs.getString("matkhau"),
                        rs.getString("vai_tro"),
                        rs.getInt("diem_tich_luy")
                );
                list.add(us);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
      
    
     public String getHoTenById(int idUser) {
        String sql = "SELECT hoten FROM user WHERE id = ?";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("hoten");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không rõ";
}

}

