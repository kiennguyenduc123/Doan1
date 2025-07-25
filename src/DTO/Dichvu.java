/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiennguyen
 */
public class Dichvu {
    private int id;
    private String tendichvu, loaidichvu;
    private double giathue;
    private String mota;
    private String imagePath;
    private String maDichvu;
    private String trangthai;
    
    
    public Dichvu(){}

    public Dichvu(int id,String maDichvu, String tendichvu, String loaidichvu, double giathue, String mota, String imagePath, String trangthai) {
        this.id = id;
        this.tendichvu = tendichvu;
        this.loaidichvu = loaidichvu;
        this.giathue = giathue;
        this.mota = mota;
        this.imagePath = imagePath;
        this.maDichvu = maDichvu;
        this.trangthai = trangthai;
    }

    public String getMaDichvu() {
        return maDichvu;
    }

    public void setMaDichvu(String maDichvu) {
        this.maDichvu = maDichvu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTendichvu() {
        return tendichvu;
    }

    public void setTendichvu(String tendichvu) {
        this.tendichvu = tendichvu;
    }

    public String getLoaidichvu() {
        return loaidichvu;
    }

    public void setLoaidichvu(String loaidichvu) {
        this.loaidichvu = loaidichvu;
    }

    public double getGiathue() {
        return giathue;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public void setGiathue(double giathue) {
        this.giathue = giathue;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
     @Override
    public String toString() {
        return this.getTendichvu();
    }
}
