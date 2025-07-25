/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Kiennguyen
 */
public class DichVuItem {
     private int id;
         private String maDv;      // mã dịch vụ dạng chuỗi, ví dụ "MDV05"
    private String displayName;

    public DichVuItem(int id,String maDv , String displayName) {
        this.id = id;
        this.maDv = maDv;
        this.displayName = displayName;
    }
    
    public DichVuItem(){}

    public int getId() {
        return id;
    }
    
    public String getMaDv() {
        return maDv;
    }

    @Override
    public String toString() {
        return displayName; // để hiển thị tên dịch vụ trong combo box
    }
    
    
}
