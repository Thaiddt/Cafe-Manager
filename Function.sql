USE AppBanHang
GO

CREATE FUNCTION func_getHoaDonLonNhat
(@thang INT, @nam INT) RETURNS INTEGER
AS
BEGIN
RETURN (SELECT
            TOP 1
    SUM(dongia) AS tongtien
    FROM
    dbo.ChiTietHD JOIN dbo.HoaDon ON HoaDon.MaHoaDon = ChiTietHD.MaHoaDon
    JOIN dbo.SanPham ON SanPham.MaSanPham = ChiTietHD.MaSanPham
    WHERE
    MONTH(NgayXuatHD) = @thang AND YEAR(NgayXuatHD) = @nam
    GROUP BY
    ChiTietHD.MaHoaDon
    ORDER BY
    tongtien DESC
    )
END
GO

CREATE FUNCTION func_getHoaDonNhoNhat
(@thang INT, @nam INT) RETURNS INTEGER
AS
BEGIN
RETURN (SELECT
            TOP 1
    SUM(dongia) AS tongtien
    FROM
    dbo.ChiTietHD JOIN dbo.HoaDon ON HoaDon.MaHoaDon = ChiTietHD.MaHoaDon
    JOIN dbo.SanPham ON SanPham.MaSanPham = ChiTietHD.MaSanPham
    WHERE
    MONTH(NgayXuatHD) = @thang AND YEAR(NgayXuatHD) = @nam
    GROUP BY
    ChiTietHD.MaHoaDon
    ORDER BY
    tongtien ASC
    )
END
GO

CREATE FUNCTION func_getTongGioLam
(@manhanvien NVARCHAR(10), @thang INT, @nam INT) RETURNS INTEGER
AS
BEGIN
RETURN (SELECT
            SUM( DATEDIFF(MINUTE, GioDen, GioRa))
        FROM
            dbo.Luong
        WHERE
                    luong.MaNhanVien = @manhanvien AND MONTH(luong.NgayDiLam) = @thang AND YEAR(luong.NgayDiLam) = @nam
    GROUP BY
    luong.MaNhanVien
    )
END