/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Loaidichvu;
import Utils.dbConnect;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoaidichvuDao {
    public List<Loaidichvu> getAllLoai() {
        Connection conn = null;
        Statement statement = null;
        
        List<Loaidichvu> list = new ArrayList<>();
        
        try {
            conn = dbConnect.dbConnection();
            String sql = "select * from loai_dich_vu";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                Loaidichvu ldv = new Loaidichvu();
                ldv.setId(rs.getInt("id"));
                ldv.setMaloai(rs.getString("maloai"));
                ldv.setTenloai(rs.getString("tenloai"));
                
                list.add(ldv);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoaidichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoaidichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public boolean insert(Loaidichvu ldv) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = dbConnect.dbConnection();
            String sql = "insert into loai_dich_vu(maloai, tenloai) values(?,?)";
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, ldv.getMaloai());
            statement.setString(2, ldv.getTenloai());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoaidichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoaidichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public boolean update(Loaidichvu ldv) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = dbConnect.dbConnection();
            String sql = "update loai_dich_vu set maloai = ?, tenloai = ? where maloai = ?";
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, ldv.getMaloai());
            statement.setString(2, ldv.getTenloai());
             statement.setString(3, ldv.getMaloai());
            int row = statement.executeUpdate();
            return row > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoaidichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoaidichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
