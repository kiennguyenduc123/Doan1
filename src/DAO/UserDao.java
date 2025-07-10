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
            String sql = "select * from user";
            statement = con.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User us = new User(
                        rs.getString("ma_khachhang"),
                        rs.getString("tendangnhap"),
                        rs.getString("matkhau"),
                        rs.getString("hoten"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("dia_chi"),
//                        rs.getString("vaitro"),
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
    
    public boolean login(String username, String password) {
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = dbConnect.dbConnection();
            String sql = "select * from user where tendangnhap = ? AND matkhau = ?";
            statement = con.prepareStatement(sql);
            
            statement.setString(1, username);
            statement.setString(2, PasswordUtils.hashPassword(password));
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
            String sql = "insert into user (ma_khachhang, tendangnhap, hoten, email, sdt, dia_chi, matkhau) values(?,?,?,?,?,?,?)";
            statement = con.prepareStatement(sql);
            
            statement.setString(1, kh.getMakhachhang());
            statement.setString(2, kh.getTendangnhap());
            statement.setString(3, kh.getHoten());
            statement.setString(4, kh.getEmail());
            statement.setString(5, kh.getSdt());
            statement.setString(6, kh.getDiachi());
            statement.setString(7, kh.getMatkhau());
            
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
            String sql = "update user set ma_khachhang = ?,  tendangnhap =?, hoten = ?, email = ?, sdt = ?, dia_chi = ?, matkhau = ? where ma_khachhang = ?";
            statement = con.prepareStatement(sql);

            statement.setString(1, kh.getMakhachhang());
            statement.setString(2, kh.getTendangnhap());
            statement.setString(3, kh.getHoten());
            statement.setString(4, kh.getEmail());
            statement.setString(5, kh.getSdt());
            statement.setString(6, kh.getDiachi());
            statement.setString(7, kh.getMatkhau());
            statement.setString(8, kh.getMakhachhang());


            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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
      
      
      public List<User> searchUser(String ma_khachhang) throws SQLException {
        List<User> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = dbConnect.dbConnection();
            String sql = "select * from user where ma_khachhang like ?";
            statement = con.prepareStatement(sql);

            statement.setString(1, "%" + ma_khachhang + "%");
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User us = new User(
                        rs.getString("ma_khachhang"),
                        rs.getString("tendangnhap"),
                        rs.getString("matkhau"),
                        rs.getString("hoten"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("dia_chi"),
//                        rs.getString("vaitro"),
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
}

