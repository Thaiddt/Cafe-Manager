create
database AppBanHang
GO

use AppBanHang
create table NhanVien
(
    MaNhanVien   varchar(50) not null primary key,
    TenNhanVien  nvarchar(50) not null,
    LoaiNhanVien nvarchar(50) not null,
    GioiTinh     bit         not null,
    NgaySinh     date        not null,
    DiaChi       nvarchar(50) not null,
    SDT          varchar(15) not null,
    Email        varchar(50) not null,
    MatKhau      varchar(15) not null,
    HeSoLuong    float       not null,
    Hinh         varchar(50) not null,
    trangthai int
)
go
create table LoaiSanPham
(
    MaLoaiSanPham int identity(1,1) not null primary key,
    TenLoaiSanPham    nvarchar(50) not null
)
go
create table SanPham
(
    MaSanPham     varchar(50) not null primary key,
    MaLoaiSanPham int         not null,
    TenSanPham    nvarchar(50) not null,
    DonGia        int         not null,
    HinhAnh       varchar(50),
    MoTa          nvarchar(50),
    foreign key (MaLoaiSanPham) references LoaiSanPham
)
go

create table HoaDon
(
    MaHoaDon      int identity(1, 1) primary key,
    MaNhanVien    varchar(50) not null,
    LoaiKhachHang nvarchar(50) not null,
    NgayXuatHD    date        not null,
    GhiChu        nvarchar(50),
    foreign key (MaNhanVien) references NhanVien
)
go
create table ChiTietHD
(
    MaHoaDon  int         not null,
    MaSanPham varchar(50) not null,
    MoTa      nvarchar(50),
    foreign key (MaHoaDon) references HoaDon,
    foreign key (MaSanPham) references SanPham
)
go
create table Luong
(
    MaLuong    int identity(1,1) not null primary key,
    MaNhanVien varchar(50) not null,
    NgayDiLam  date        not null,
    GioDen     time(0)     not null,
    GioRa      time(0)     not null,
    GhiChu     nvarchar(50),
    foreign key (MaNhanVien) references NhanVien
)
