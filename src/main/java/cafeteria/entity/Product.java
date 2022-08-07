/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.entity;

public class Product {
    private String maSanPham;
    private int maLoaiSanPham;
    private String tenSanPham;
    private int donGia;
    private String hinhAnh;
    private String moTa;

    public Product() {
    }

    public Product(String maSanPham, int maLoaiSanPham, String tenSanPham, int donGia, String hinhAnh, String moTa) {
        this.maSanPham = maSanPham;
        this.maLoaiSanPham = maLoaiSanPham;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
