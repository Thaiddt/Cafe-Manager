CREATE FUNCTION func_getHoaDonLonNhat
(thang INT, nam INT) RETURNS INTEGER
as
    $total$
declare
total integer;
BEGIN
SELECT sum(dongia) as tongTien INTO total
FROM
    ChiTietHD JOIN HoaDon ON HoaDon.MaHoaDon = ChiTietHD.MaHoaDon
              JOIN SanPham ON SanPham.MaSanPham = ChiTietHD.MaSanPham
WHERE
        extract(MONTH FROM NgayXuatHD) = thang AND extract(YEAR FROM NgayXuatHD) = nam
GROUP BY
    ChiTietHD.MaHoaDon
ORDER BY
    tongtien DESC
    limit 1;
return total;
END
$total$ LANGUAGE plpgsql;


CREATE FUNCTION func_getHoaDonNhoNhat
(thang INT, nam INT) RETURNS INTEGER
AS
    $$
declare total integer;
BEGIN
SELECT SUM(dongia) AS tongtien INTO total
FROM
    ChiTietHD JOIN HoaDon ON HoaDon.MaHoaDon = ChiTietHD.MaHoaDon
              JOIN SanPham ON SanPham.MaSanPham = ChiTietHD.MaSanPham
WHERE
        EXTRACT(MONTH FROM NgayXuatHD) = thang AND EXTRACT(YEAR FROM NgayXuatHD) = nam
GROUP BY
    ChiTietHD.MaHoaDon
ORDER BY
    tongtien ASC;
return total;

END
$$ LANGUAGE plpgsql;

CREATE FUNCTION func_getTongGioLam
(manv VARCHAR(10), thang INT, nam INT) RETURNS INTEGER
AS
    $$
declare
total integer;
BEGIN
SELECT
    SUM( EXTRACT(EPOCH FROM (giora - luong.gioden))/60)
INTO total
FROM
    Luong
WHERE
        luong.MaNhanVien = manv
  AND EXTRACT(MONTH FROM luong.NgayDiLam) = thang
  AND EXTRACT(YEAR FROM luong.NgayDiLam) = nam
GROUP BY
    luong.MaNhanVien;
return total;
END
$$ LANGUAGE plpgsql;