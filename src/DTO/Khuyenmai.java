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
public class Khuyenmai {

    private String magg;
    private int soluong;
    private Date ngaybdau, ngaykthuc;
    private double apdunghd, giatri;
    private String hinhThuc;
    private String vaitro;
    private int maNV;
    private String maDv, tenDv;
    private int idDvu;
    private int id;

    public Khuyenmai() {
    }
    
    public Khuyenmai(String magg, int soluong, Date ngaybdau, Date ngaykthuc, double apdunghd, double giatri, String hinhThuc, String vaitro) {
        this.magg = magg;
        this.soluong = soluong;
        this.ngaybdau = ngaybdau;
        this.ngaykthuc = ngaykthuc;
        this.apdunghd = apdunghd;
        this.giatri = giatri;
        this.hinhThuc = hinhThuc;
        this.vaitro = vaitro;

    }

    public int getIdDvu() {
        return idDvu;
    }

    public void setIdDvu(int idDvu) {
        this.idDvu = idDvu;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaDv() {
        return maDv;
    }

    public void setMaDv(String maDv) {
        this.maDv = maDv;
    }

    public String getTenDv() {
        return tenDv;
    }

    public void setTenDv(String tenDv) {
        this.tenDv = tenDv;
    }

    public String getMagg() {
        return magg;
    }

    public void setMagg(String magg) {
        this.magg = magg;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Date getNgaybdau() {
        return ngaybdau;
    }

    public void setNgaybdau(Date ngaybdau) {
        this.ngaybdau = ngaybdau;
    }

    public Date getNgaykthuc() {
        return ngaykthuc;
    }

    public void setNgaykthuc(Date ngaykthuc) {
        this.ngaykthuc = ngaykthuc;
    }

    public double getApdunghd() {
        return apdunghd;
    }

    public void setApdunghd(double apdunghd) {
        this.apdunghd = apdunghd;
    }

    public double getGiatri() {
        return giatri;
    }

    public void setGiatri(double giatri) {
        this.giatri = giatri;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }
}
