package cafeteria.dao;

import cafeteria.entity.Bill;
import cafeteria.utils.DateUtils;
import cafeteria.utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BillDAO implements DAO<Bill, Integer> {

    private final String SQL_INSERT_HOADON = "INSERT INTO hoadon(manhanvien,loaikhachhang,ngayxuathd) VALUES(?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM hoadon";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM hoadon WHERE mahoadon = ?";

    public void inserts(Bill hd) {
        JDBCHelper.executeUpdate(SQL_INSERT_HOADON,
                hd.getMaNhanVien(),
                hd.isLoaiKhachHang(),
                DateUtils.convertDateToSqlDate(hd.getNgayXuatHoaDon())
        );

    }

    @Override
    public void insert(Bill entity) {
        try {
            JDBCHelper.update(SQL_INSERT_HOADON, entity.getMaNhanVien(), entity.isLoaiKhachHang(), entity.getNgayXuatHoaDon());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Thêm thất bại");
        }
    }

    @Override
    public void delete(Integer id) {
        // không hỗ trợ
    }

    @Override
    public void update(Bill entity) {
        // không hỗ trợ
    }

    @Override
    public Bill selectByID(Integer id) {
        List<Bill> hoaDonList = this.selectBySQL(SQL_SELECT_BY_ID, id);
        if (hoaDonList.isEmpty()) {
            return null;
        }
        return hoaDonList.get(0);
    }

    @Override
    public List<Bill> selectAll() {
        return this.selectBySQL(SQL_SELECT);
    }

    @Override
    public List<Bill> selectBySQL(String sql, Object... x) {
        List<Bill> luongList = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.queryResult(sql, x);
            while (rs.next()) {
                Bill hd = new Bill();
                hd.setMaHoaDon(rs.getInt("mahoadon"));
                hd.setMaNhanVien(rs.getString("manhanvien"));
                hd.setLoaiKhachHang(rs.getBoolean("loaikhachhang"));
                hd.setNgayXuatHoaDon(rs.getDate("ngayxuathd"));
                luongList.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return luongList;
    }

    public List<Bill> getHoaDonByDate(int year, int month, int date) throws SQLException {
        String sql = "SELECT * FROM hoadon where ngayxuathd = ?";

        Calendar a = Calendar.getInstance();
        a.set(year, month - 1, date);
        Date dt = a.getTime();
        return this.selectBySQL(sql, DateUtils.convertDateToSqlDate(dt));
    }

    public Bill selectLastHoaDon() {
        List<Bill> hoaDonList = this.selectBySQL(SQL_SELECT);
        if (hoaDonList.isEmpty()) {
            return null;
        }
        return hoaDonList.get(hoaDonList.size() - 1);
    }
}
