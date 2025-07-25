/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhaCC;
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
public class NhaCCDao {
    public List<NhaCC> getAllNhaCC(){
        Connection conn = null;
        Statement statement = null;
        List<NhaCC> list = new ArrayList<>();
        
        try {
            conn = dbConnect.dbConnection();
            String sql = "select * from nhacc";
            
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                NhaCC ncc = new NhaCC();
                ncc.setId(rs.getInt("id"));
                ncc.setMancc(rs.getString("mancc"));
                ncc.setTenncc(rs.getString("tenncc"));
                ncc.setSdt(rs.getString("sdt"));
                ncc.setDiachi(rs.getString("diachi"));
                ncc.setTrangthai(rs.getString("trangthai"));
                
                list.add(ncc);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhaCCDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCCDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return list;
    }
    
    public boolean insert(NhaCC ncc) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = dbConnect.dbConnection();
            String sql = "insert into nhacc(mancc, tenncc, sdt, diachi, trangthai) values (?,?,?,?,?)";
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, ncc.getMancc());
            statement.setString(2, ncc.getTenncc());
            statement.setString(3, ncc.getSdt());
            statement.setString(4, ncc.getDiachi());
            statement.setString(5, ncc.getTrangthai());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhaCCDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCCDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public boolean update(NhaCC ncc) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = dbConnect.dbConnection();
            String sql = "update nhacc set mancc =?, tenncc =?, sdt = ?, diachi= ?, trangthai = ? where mancc = ?";
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, ncc.getMancc());
            statement.setString(2, ncc.getTenncc());
            statement.setString(3, ncc.getSdt());
            statement.setString(4, ncc.getDiachi());
            statement.setString(5, ncc.getTrangthai());
            statement.setString(6, ncc.getMancc());

            int row = statement.executeUpdate();
            return row > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhaCCDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCCDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
