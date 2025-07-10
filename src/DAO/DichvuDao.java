/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Dichvu;
import Utils.dbConnect;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DichvuDao {

    private Connection connection;

    public DichvuDao(Connection connection) {
        this.connection = connection;
    }

    public List<Dichvu> getAllDichvu() throws SQLException {
        List<Dichvu> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dbConnect.dbConnection();
            String sql = "SELECT dv.id, dv.ma_dich_vu, dv.tendichvu, ldv.tenloai, dv.giathue, dv.mota, dv.image_path, dv.trang_thai "
                    + "FROM dich_vu dv JOIN loai_dich_vu ldv ON dv.id_loai_dich_vu = ldv.id";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                        Dichvu dv = new Dichvu(
                        rs.getInt("id"),
                        rs.getString("ma_dich_vu"),
                        rs.getString("tendichvu"),
                        rs.getString("tenloai"),
                        rs.getDouble("giathue"),
                        rs.getString("mota"),
                        rs.getString("image_path"),
                        rs.getString("trang_thai")
                );
                list.add(dv);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public boolean insert(Dichvu dv) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dbConnect.dbConnection();
            String sql = "insert into dich_vu(ma_dich_vu,tendichvu, id_loai_dich_vu, mota, giathue,image_path,trang_thai) values (?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, dv.getMaDichvu());
            statement.setString(2, dv.getTendichvu());
            statement.setInt(3, getLoaiIdByTen(dv.getLoaidichvu()));
            statement.setString(4, dv.getMota());
            statement.setDouble(5, dv.getGiathue());
            statement.setString(6, dv.getImagePath());
            statement.setString(7, dv.getTrangthai());

            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    public boolean update(Dichvu dv) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dbConnect.dbConnection();
            String sql = "update dich_vu set ma_dich_vu = ?, tendichvu = ? , id_loai_dich_vu = ?, giathue = ?, mota = ?, image_path = ?, trang_thai = ? where ma_dich_vu = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, dv.getMaDichvu());
            statement.setString(2, dv.getTendichvu());
            statement.setInt(3, getLoaiIdByTen(dv.getLoaidichvu()));
            statement.setDouble(4, dv.getGiathue());
            statement.setString(5, dv.getMota());
            statement.setString(6, dv.getImagePath());
            statement.setString(7, dv.getTrangthai());
            statement.setString(8, dv.getMaDichvu());

            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    public boolean delete(String maDichvu) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dbConnect.dbConnection();
            String sql = "delete from dich_vu where ma_dich_vu = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, maDichvu);

            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Dichvu> search(String tendichvu) {
        List<Dichvu> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dbConnect.dbConnection();
             String sql = "SELECT dv.id, dv.ma_dich_vu, dv.tendichvu, ldv.tenloai, dv.giathue, dv.mota, dv.image_path, dv.trang_thai "
                    + "FROM dich_vu dv JOIN loai_dich_vu ldv ON dv.id_loai_dich_vu = ldv.id where tendichvu like ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + tendichvu + "%");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                 Dichvu dv = new Dichvu(
                        rs.getInt("id"),
                        rs.getString("ma_dich_vu"),
                        rs.getString("tendichvu"),
                        rs.getString("tenloai"),
                        rs.getDouble("giathue"),
                        rs.getString("mota"),
                        rs.getString("image_path"),
                        rs.getString("trang_thai")
                );
                list.add(dv);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
     public List<Dichvu> filterByLoai(String trangThai) {
        List<Dichvu> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dbConnect.dbConnection();
             String sql = "SELECT dv.id, dv.ma_dich_vu, dv.tendichvu, ldv.tenloai, dv.giathue, dv.mota, dv.image_path, dv.trang_thai "
           + "FROM dich_vu dv JOIN loai_dich_vu ldv ON dv.id_loai_dich_vu = ldv.id WHERE dv.trang_thai = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1,trangThai);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                 Dichvu dv = new Dichvu(
                        rs.getInt("id"),
                        rs.getString("ma_dich_vu"),
                        rs.getString("tendichvu"),
                        rs.getString("tenloai"),
                        rs.getDouble("giathue"),
                        rs.getString("mota"),
                        rs.getString("image_path"),
                        rs.getString("trang_thai")
                );
                list.add(dv);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }


    public int getLoaiIdByTen(String tenLoai) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dbConnect.dbConnection();
            String sql = "select id from loai_dich_vu where tenLoai = ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, tenLoai);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            throw new SQLException("Không tìm thấy loại dịch vụ: " + tenLoai);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
