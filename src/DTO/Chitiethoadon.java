/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.List;

/**
 *
 * @author Kiennguyen
 */
public class Chitiethoadon {
    private int id;
    private int id_hoa_don, id_dich_vu;
    private String ma_dich_vu, ten_dich_vu;
    private int soLuong;
    private double donGia;
     
       private List<Chitietlichdat> lichThueList;

    public List<Chitietlichdat> getLichThueList() {
        return lichThueList;
    }

    public void setLichThueList(List<Chitietlichdat> lichThueList) {
        this.lichThueList = lichThueList;
    }
    public Chitiethoadon() {}

    public Chitiethoadon(int id, int id_hoa_don, int id_dich_vu, String ma_dich_vu, String ten_dich_vu, int soLuong, double donGia) {
        this.id = id;
        this.id_hoa_don = id_hoa_don;
        this.id_dich_vu = id_dich_vu;
        this.ma_dich_vu = ma_dich_vu;
        this.ten_dich_vu = ten_dich_vu;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_hoa_don() {
        return id_hoa_don;
    }

    public void setId_hoa_don(int id_hoa_don) {
        this.id_hoa_don = id_hoa_don;
    }

    public int getId_dich_vu() {
        return id_dich_vu;
    }

    public void setId_dich_vu(int id_dich_vu) {
        this.id_dich_vu = id_dich_vu;
    }

    public String getMa_dich_vu() {
        return ma_dich_vu;
    }

    public void setMa_dich_vu(String ma_dich_vu) {
        this.ma_dich_vu = ma_dich_vu;
    }

    public String getTen_dich_vu() {
        return ten_dich_vu;
    }

    public void setTen_dich_vu(String ten_dich_vu) {
        this.ten_dich_vu = ten_dich_vu;
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
    
    public double getTongTien() {
        return donGia * soLuong;
    }
    
   public void setTongTien(int soLuong, double donGia) {
    double ttien = soLuong * donGia;
}
    
}
