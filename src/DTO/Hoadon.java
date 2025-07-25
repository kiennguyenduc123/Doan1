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
public class Hoadon {
    
    public int id;
    public String maHd;
    public int iddonHang;
    public String maKH;
    public Date ngayttoan;
    public double tongTien;
    public int idUser;
    public String hoTen;
    public int idMagg;
    public String Magg;
    public int diem_tichluy;
    public String vaiTro;
    public int diemDadung;
    public Hoadon() {}

    public Hoadon(int id, String maHd, int iddonHang, String maKH, Date ngayttoan, double tongTien, int idUser, String hoTen, int idMagg, String Magg, int diem_tichluy, String vaiTro,int diemDadung) {
        this.id = id;
        this.maHd = maHd;
        this.iddonHang = iddonHang;
        this.maKH = maKH;
        this.ngayttoan = ngayttoan;
        this.tongTien = tongTien;
        this.idUser = idUser;
        this.hoTen = hoTen;
        this.idMagg = idMagg;
        this.Magg = Magg;
        this.diem_tichluy = diem_tichluy;
        this.vaiTro = vaiTro;
        this.diemDadung = diemDadung;
    }

    public int getDiemDadung() {
        return diemDadung;
    }

    public void setDiemDadung(int diemDadung) {
        this.diemDadung = diemDadung;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public int getIddonHang() {
        return iddonHang;
    }

    public void setIddonHang(int iddonHang) {
        this.iddonHang = iddonHang;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayttoan() {
        return ngayttoan;
    }

    public void setNgayttoan(Date ngayttoan) {
        this.ngayttoan = ngayttoan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getIdMagg() {
        return idMagg;
    }

    public void setIdMagg(int idMagg) {
        this.idMagg = idMagg;
    }

    public String getMagg() {
        return Magg;
    }

    public void setMagg(String Magg) {
        this.Magg = Magg;
    }

    public int getDiem_tichluy() {
        return diem_tichluy;
    }

    public void setDiem_tichluy(int diem_tichluy) {
        this.diem_tichluy = diem_tichluy;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
    
    
}
