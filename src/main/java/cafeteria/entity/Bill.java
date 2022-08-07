/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.entity;

import java.util.Date;

public class Bill {
    private int maHoaDon;
    private String maNhanVien;
    private boolean loaiKhachHang;
    private Date ngayXuatHoaDon;

    public Bill() {
    }

    public Bill(int maHoaDon, String maNhanVien, boolean loaiKhachHang, Date ngayXuatHoaDon) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.loaiKhachHang = loaiKhachHang;
        this.ngayXuatHoaDon = ngayXuatHoaDon;
    }



    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public boolean isLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(boolean loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }

    public Date getNgayXuatHoaDon() {
        return ngayXuatHoaDon;
    }

    public void setNgayXuatHoaDon(Date ngayXuatHoaDon) {
        this.ngayXuatHoaDon = ngayXuatHoaDon;
    }
    
    
    
    
}
