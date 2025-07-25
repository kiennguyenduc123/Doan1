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
    
    public DichvuDao(){}

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

    public List<Dichvu> search(String keyword) {
        List<Dichvu> list = new ArrayList<>();
        String sql = """
        SELECT dv.id, dv.ma_dich_vu, dv.tendichvu, ldv.tenloai, dv.giathue, dv.mota, dv.image_path, dv.trang_thai
        FROM dich_vu dv
        JOIN loai_dich_vu ldv ON dv.id_loai_dich_vu = ldv.id
        WHERE dv.ma_dich_vu LIKE ?
           OR dv.tendichvu LIKE ?
           OR ldv.tenloai LIKE ?
           OR CAST(dv.giathue AS CHAR) LIKE ?
    """;

        try (Connection connection = dbConnect.dbConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            String searchValue = "%" + keyword + "%";
            statement.setString(1, searchValue);
            statement.setString(2, searchValue);
            statement.setString(3, searchValue);
            statement.setString(4, searchValue);

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
    
    public List<Dichvu> getByTenLoai(String tenLoai) {
        List<Dichvu> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            int loaiId = getLoaiIdByTen(tenLoai); // <--- dùng lại hàm bạn có sẵn

            connection = dbConnect.dbConnection();
            String sql = "SELECT dv.id, dv.ma_dich_vu, dv.tendichvu, ldv.tenloai, dv.giathue, dv.mota, dv.image_path, dv.trang_thai "
                    + "FROM dich_vu dv JOIN loai_dich_vu ldv ON dv.id_loai_dich_vu = ldv.id "
                    + "WHERE ldv.id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, loaiId);
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
        } catch (Exception ex) {
            Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DichvuDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }
    
    public Dichvu getDichVuByTen(String tenDV) {
        String sql = "SELECT * FROM dich_vu WHERE tendichvu = ?";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenDV);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Dichvu(
                        rs.getInt("id"),
                        rs.getString("ma_dich_vu"),
                        rs.getString("tendichvu"),
                        "", // tenloai (có thể bỏ qua nếu chưa cần)
                        rs.getDouble("giathue"),
                        rs.getString("mota"),
                        rs.getString("image_path"),
                        rs.getString("trang_thai")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getIdByMaDichvu(String maDichVu) {
        String sql = "SELECT id FROM dichvu WHERE ma_dich_vu = ?";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maDichVu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public Dichvu getDichVuByMa(String maDichVu) {
        String sql = "SELECT * FROM dich_vu WHERE ma_dich_vu = ?";
        try (Connection conn = dbConnect.dbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maDichVu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Dichvu dv = new Dichvu();
                dv.setId(rs.getInt("id"));
                dv.setMaDichvu(rs.getString("ma_dich_vu"));
                dv.setTendichvu(rs.getString("tendichvu"));
                dv.setGiathue(rs.getDouble("giathue"));
                return dv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getIdByMa(String maDV) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM dich_vu WHERE ma_dich_vu = ?";
        try (Connection con = dbConnect.dbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maDV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    public String getMaById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT ma_dich_vu FROM dich_vu WHERE id = ?";
        try (Connection con = dbConnect.dbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("ma_dich_vu");
            }
        }
        return "";
    }
 
 
    public List<String> getAllTenLoaiDichVu() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT tenloai FROM loai_dich_vu";
        try (Connection con = dbConnect.dbConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("tenloai"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
