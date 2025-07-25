/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Kiennguyen
 */
public class Chitietlichdat {
    
    private int id;
    private int idLichThue;   
    private int idDichVu;   
    private String maDV;
    private String tenDV;
    private int soLuong;
    private double donGia;
    private Date ngay;
    private String ghiChu;
    private int idChitiethdon;
    public Chitietlichdat() {
    }

    public Chitietlichdat(String maDV, String tenDV, int soLuong, double donGia, Date ngay, String ghiChu, int id, int idChitiethdon) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ngay = ngay;
        this.ghiChu = ghiChu;
        this.id = id;
        this.idChitiethdon = idChitiethdon;
    }

    public int getIdChitiethdon() {
        return idChitiethdon;
    }

    public void setIdChitiethdon(int idChitiethdon) {
        this.idChitiethdon = idChitiethdon;
    }

    
    
    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
    public double getTinhTong() {
        return soLuong * donGia;
    }

    public int getIdLichThue() {
        return idLichThue;
    }

    public void setIdLichThue(int idLichThue) {
        this.idLichThue = idLichThue;
    }

    public int getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        this.idDichVu = idDichVu;
    }
    
    public double getTongTien(){
        return soLuong * donGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    
    
}
