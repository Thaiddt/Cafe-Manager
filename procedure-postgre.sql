CREATE PROCEDURE pro_doanhthu(nam INT)
language 'plpgsql'
as $BODY$
BEGIN
    SELECT
         extract(MONTH FROM NgayXuatHD) AS thang,
         func_getHoaDonLonNhat(EXTRACT(MONTH FROM NgayXuatHD), EXTRACT( YEAR FROM NgayXuatHD)) AS hoadonlonnhat,
         func_getHoaDonNhoNhat(EXTRACT(MONTH FROM NgayXuatHD), EXTRACT( YEAR FROM NgayXuatHD)) AS hoadonnhonhat,
         SUM(SanPham.DonGia) AS tongtien
     FROM
         ChiTietHD JOIN HoaDon ON HoaDon.MaHoaDon = ChiTietHD.MaHoaDon
                   JOIN SanPham ON SanPham.MaSanPham = ChiTietHD.MaSanPham
     WHERE
             EXTRACT( YEAR FROM NgayXuatHD) = nam
     GROUP BY
         EXTRACT(MONTH FROM NgayXuatHD), EXTRACT(YEAR FROM NgayXuatHD);
END $BODY$;

CREATE PROCEDURE pro_luongNhanVien(thang INT, nam INT)
language 'plpgsql'
as $BODY$
BEGIN
SELECT
    NhanVien.MaNhanVien AS manhanvien,
    NhanVien.TenNhanVien AS tennhanvien,
    EXTRACT(MONTH FROM Luong.NgayDiLam) AS thoigian,
    func_getTongGioLam(NhanVien.MaNhanVien, EXTRACT(MONTH FROM NgayDiLam), EXTRACT( YEAR FROM NgayDiLam)) AS tongGioLam
FROM
    NhanVien JOIN Luong ON Luong.MaNhanVien = NhanVien.MaNhanVien
WHERE
        extract( MONTH FROM luong.NgayDiLam) = thang AND extract(YEAR FROM luong.NgayDiLam) = nam
GROUP BY
    NhanVien.MaNhanVien,
    NhanVien.TenNhanVien,
    extract(MONTH FROM luong.NgayDiLam),
    extract(YEAR FROM NgayDiLam);
END $BODY$;


create PROCEDURE pro_mathangbanchay(thang INT, nam INT, nuocngoai boolean)
    language 'plpgsql'
as $BODY$
BEGIN
    SELECT
        SanPham.MaSanPham AS masanpham,
        SanPham.TenSanPham AS tensanpham,
        EXTRACT(MONTH FROM HoaDon.NgayXuatHD) AS thoigian,
        COUNT(ChiTietHD.MaSanPham) AS soluong
    FROM
        ChiTietHD JOIN HoaDon ON HoaDon.MaHoaDon = ChiTietHD.MaHoaDon
                  JOIN SanPham ON SanPham.MaSanPham = ChiTietHD.MaSanPham
    WHERE
            extract(MONTH FROM hoadon.NgayXuatHD) = thang AND
            extract(YEAR FROM hoadon.NgayXuatHD) = nam AND
            HoaDon.LoaiKhachHang = nuocngoai
    GROUP BY
        SanPham.MaSanPham,
        SanPham.TenSanPham,
        extract(MONTH FROM HoaDon.NgayXuatHD)
    ORDER BY
        COUNT(ChiTietHD.MaSanPham) DESC limit 5;
END $BODY$;

CREATE PROCEDURE pro_hoaDonChiTiet(maHd INT)
language 'plpgsql'
as $BODY$
BEGIN
SELECT
    ChiTietHD.MaSanPham AS masanpham,
    SanPham.TenSanPham AS tensanpham,
    COUNT(ChiTietHD.MaSanPham) AS soluong,
    SanPham.DonGia AS gia
FROM
    ChiTietHD JOIN SanPham ON SanPham.MaSanPham = ChiTietHD.MaSanPham
WHERE
        ChiTietHD.MaHoaDon = maHd
GROUP BY
    TenSanPham, DonGia, ChiTietHD.MaSanPham;
END $BODY$;