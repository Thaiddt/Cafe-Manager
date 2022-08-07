

create table NhanVien
(
    MaNhanVien   varchar(50) not null primary key,
    TenNhanVien  varchar(50) not null,
    LoaiNhanVien varchar(50) not null,
    GioiTinh     bit         not null,
    NgaySinh     date        not null,
    DiaChi       varchar(50) not null,
    SDT          varchar(15) not null,
    Email        varchar(50) not null,
    MatKhau      varchar(15) not null,
    HeSoLuong    float       not null,
    Hinh         varchar(50) not null,
    trangthai int
);
create table LoaiSanPham
(
    MaLoaiSanPham     serial not null primary key,
    TenLoaiSanPham    varchar(50) not null
);
create table SanPham
(
    MaSanPham     varchar(50) not null primary key,
    MaLoaiSanPham int         not null,
    TenSanPham    varchar(50) not null,
    DonGia        int         not null,
    HinhAnh       varchar(50),
    MoTa          varchar(50),
    foreign key (MaLoaiSanPham) references LoaiSanPham
);
create table HoaDon
(
    MaHoaDon      serial primary key,
    MaNhanVien    varchar(50) not null,
    LoaiKhachHang varchar(50) not null,
    NgayXuatHD    date        not null,
    GhiChu        varchar(50),
    foreign key (MaNhanVien) references NhanVien
);
create table ChiTietHD
(
    MaHoaDon  int         not null,
    MaSanPham varchar(50) not null,
    MoTa      varchar(50),
    foreign key (MaHoaDon) references HoaDon,
    foreign key (MaSanPham) references SanPham
);
create table Luong
(
    MaLuong    serial not null primary key,
    MaNhanVien varchar(50) not null,
    NgayDiLam  date        not null,
    GioDen     time(0)     not null,
    GioRa      time(0)     not null,
    GhiChu     varchar(50),
    foreign key (MaNhanVien) references NhanVien
)
