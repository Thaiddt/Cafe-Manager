USE AppBanHang
GO

CREATE PROCEDURE pro_doanhthu
    @nam INT
AS
BEGIN
SELECT
    MONTH(NgayXuatHD) AS thang,
    dbo.func_getHoaDonLonNhat(MONTH(NgayXuatHD), YEAR(NgayXuatHD)) AS hoadonlonnhat,
    dbo.func_getHoaDonNhoNhat(MONTH(NgayXuatHD), YEAR(NgayXuatHD)) AS hoadonnhonhat,
    SUM(dbo.SanPham.DonGia) AS tongtien
FROM
    dbo.ChiTietHD JOIN dbo.HoaDon ON HoaDon.MaHoaDon = ChiTietHD.MaHoaDon
    JOIN dbo.SanPham ON SanPham.MaSanPham = ChiTietHD.MaSanPham
WHERE
    YEAR(NgayXuatHD) = @nam
GROUP BY
    MONTH(NgayXuatHD), YEAR(NgayXuatHD)
END
GO

CREATE PROCEDURE pro_luongNhanVien
    @thang INT, @nam INT
AS
BEGIN
SELECT
    dbo.NhanVien.MaNhanVien AS manhanvien,
    dbo.NhanVien.TenNhanVien AS tennhanvien,
    MONTH(dbo.Luong.NgayDiLam) AS thoigian,
    dbo.func_getTongGioLam(dbo.NhanVien.MaNhanVien, MONTH(NgayDiLam), YEAR(NgayDiLam)) AS tongGioLam
FROM
    dbo.NhanVien JOIN dbo.Luong ON Luong.MaNhanVien = NhanVien.MaNhanVien
WHERE
    MONTH(luong.NgayDiLam) = @thang AND YEAR(luong.NgayDiLam) = @nam
GROUP BY
    NhanVien.MaNhanVien,
    dbo.NhanVien.TenNhanVien,
    MONTH(luong.NgayDiLam),
    YEAR(NgayDiLam)
END
GO

CREATE PROCEDURE pro_mathangbanchay
    @thang INT, @nam INT, @nuocngoai BIT
AS
BEGIN
SELECT
    TOP 5
		dbo.SanPham.MaSanPham AS masanpham,
        dbo.SanPham.TenSanPham AS tensanpham,
    MONTH(dbo.HoaDon.NgayXuatHD) AS thoigian,
    COUNT(dbo.ChiTietHD.MaSanPham) AS soluong
FROM
    dbo.ChiTietHD JOIN dbo.HoaDon ON HoaDon.MaHoaDon = ChiTietHD.MaHoaDon
    JOIN dbo.SanPham ON SanPham.MaSanPham = ChiTietHD.MaSanPham
WHERE
    MONTH(hoadon.NgayXuatHD) = @thang AND
    YEAR(hoadon.NgayXuatHD) = @nam AND
    dbo.HoaDon.LoaiKhachHang = @nuocngoai
GROUP BY
    dbo.SanPham.MaSanPham,
    dbo.SanPham.TenSanPham,
    MONTH(dbo.HoaDon.NgayXuatHD)
ORDER BY
    COUNT(dbo.ChiTietHD.MaSanPham) DESC
END
go
CREATE PROCEDURE pro_hoaDonChiTiet
    @maHoaDon INT
AS
BEGIN
SELECT
    ChiTietHD.MaSanPham AS masanpham,
    dbo.SanPham.TenSanPham AS tensanpham,
    COUNT(dbo.ChiTietHD.MaSanPham) AS soluong,
    dbo.SanPham.DonGia AS gia
FROM
    dbo.ChiTietHD JOIN dbo.SanPham ON SanPham.MaSanPham = ChiTietHD.MaSanPham
WHERE
        dbo.ChiTietHD.MaHoaDon = @maHoaDon
GROUP BY
    TenSanPham, DonGia, ChiTietHD.MaSanPham

END