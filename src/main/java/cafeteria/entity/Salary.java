/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.entity;

import java.util.Date;

public class Salary {
    private int maLuong;
    private String maNhanVien;
    private Date ngayDiLam;
    private String gioDen;
    private String gioVe;
    private String ghiChu;

    public Salary() {
    }

    public Salary(int maLuong, String maNhanVien, Date ngayDiLam, String gioDen, String gioVe, String ghiChu) {
        this.maLuong = maLuong;
        this.maNhanVien = maNhanVien;
        this.ngayDiLam = ngayDiLam;
        this.gioDen = gioDen;
        this.gioVe = gioVe;
        this.ghiChu = ghiChu;
    }

    public int getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(int maLuong) {
        this.maLuong = maLuong;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Date getNgayDiLam() {
        return ngayDiLam;
    }

    public void setNgayDiLam(Date ngayDiLam) {
        this.ngayDiLam = ngayDiLam;
    }

    public String getGioDen() {
        return gioDen;
    }

    public void setGioDen(String gioDen) {
        this.gioDen = gioDen;
    }

    public String getGioVe() {
        return gioVe;
    }

    public void setGioVe(String gioVe) {
        this.gioVe = gioVe;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
