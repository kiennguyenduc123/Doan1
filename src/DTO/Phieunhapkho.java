/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Kiennguyen
 */
public class Phieunhapkho {
    public int id;
    public String maphieuNhap, maDichvu;
    public int dichvu_id;
    public int user_id;
    public int soLuong;
    public Date ngayNhap;
    public double giaNhap;
    public String ghiChu;
    public int nhacc_id;
    public String tenNhacc, vaiTro;
    
    public Phieunhapkho(){}

    public Phieunhapkho(int id, String maphieuNhap, String maDichvu, int dichvu_id, int user_id, int soLuong, Date ngayNhap, double giaNhap, String ghiChu, int nhacc_id, String tenNhacc,String vaiTro) {
        this.id = id;
        this.maphieuNhap = maphieuNhap;
        this.maDichvu = maDichvu;
        this.dichvu_id = dichvu_id;
        this.user_id = user_id;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
        this.giaNhap = giaNhap;
        this.ghiChu = ghiChu;
        this.nhacc_id = nhacc_id;
        this.tenNhacc = tenNhacc;
        this.vaiTro = vaiTro;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaphieuNhap() {
        return maphieuNhap;
    }

    public void setMaphieuNhap(String maphieuNhap) {
        this.maphieuNhap = maphieuNhap;
    }

    public String getMaDichvu() {
        return maDichvu;
    }

    public void setMaDichvu(String maDichvu) {
        this.maDichvu = maDichvu;
    }

    public int getDichvu_id() {
        return dichvu_id;
    }

    public void setDichvu_id(int dichvu_id) {
        this.dichvu_id = dichvu_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getNhacc_id() {
        return nhacc_id;
    }

    public void setNhacc_id(int nhacc_id) {
        this.nhacc_id = nhacc_id;
    }

    public String getTenNhacc() {
        return tenNhacc;
    }

    public void setTenNhacc(String tenNhacc) {
        this.tenNhacc = tenNhacc;
    }
    
    public double getTongtien() {
        return this.soLuong * this.giaNhap;
    }
    
    
    
}
