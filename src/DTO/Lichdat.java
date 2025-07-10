/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.security.Timestamp;
import java.util.Date;

/**
 *
 * @author Kiennguyen
 */
public class Lichdat {

    private int id;
    private String maLichThue;
    private int idKhachHang;
    private Date ngayDat, ngaySuDung, ngayTra;
    private String tinhTrang, ghiChu;
    private int nhanVienId;
    private Timestamp createAt, updatedAt;
    private String tenKhachHang;
    private int sdt;
    public Lichdat() {
    }

    public Lichdat(int id, String maLichThue, int idKhachHang, Date ngayDat, Date ngaySuDung, Date ngayTra, String tinhTrang, String ghiChu, int nhanVienId, Timestamp createAt, Timestamp updatedAt, String tenKhachHang,int sdt) {
        this.id = id;
        this.maLichThue = maLichThue;
        this.idKhachHang = idKhachHang;
        this.ngayDat = ngayDat;
        this.ngaySuDung = ngaySuDung;
        this.ngayTra = ngayTra;
        this.tinhTrang = tinhTrang;
        this.ghiChu = ghiChu;
        this.nhanVienId = nhanVienId;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaLichThue() {
        return maLichThue;
    }

    public void setMaLichThue(String maLichThue) {
        this.maLichThue = maLichThue;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgaySuDung() {
        return ngaySuDung;
    }

    public void setNgaySuDung(Date ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getNhanVienId() {
        return nhanVienId;
    }

    public void setNhanVienId(int nhanVienId) {
        this.nhanVienId = nhanVienId;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }
    
    

}
