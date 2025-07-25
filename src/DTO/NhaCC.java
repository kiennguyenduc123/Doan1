/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiennguyen
 */
public class NhaCC {
    public int id;
    public String mancc, tenncc, sdt, diachi,trangthai;
    
    public NhaCC(){}

    public NhaCC(int id, String mancc, String tenncc, String sdt, String diachi, String trangthai) {
        this.id = id;
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.sdt = sdt;
        this.diachi = diachi;
        this.trangthai = trangthai;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    
    @Override
    public String toString() {
        return this.getTenncc();
    }
    
}
