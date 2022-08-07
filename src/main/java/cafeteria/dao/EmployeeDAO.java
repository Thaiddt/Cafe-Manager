package cafeteria.dao;

import cafeteria.entity.Employee;
import cafeteria.utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements DAO<Employee, String>{

    private final String SQL_INSERT_NHANVIEN = "INSERT INTO nhanvien(manhanvien,tennhanvien,loainhanvien,gioitinh,ngaysinh,diachi,sdt,email,matkhau,hesoluong,hinh,trangthai)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String SQL_UPDATE_NHANVIEN = "UPDATE nhanvien SET tennhanvien = ?, loainhanvien = ?, gioitinh = ?, ngaysinh = ?, diachi = ?, sdt = ?, email = ?, matkhau = ?, hesoluong = ?, hinh = ?, trangthai = ? WHERE manhanvien = ?";
    private final String SQL_DELETE_NHANVIEN = "DELETE FROM nhanvien where manhanvien = ?";
    private final String SQL_SELECT = "SELECT * FROM nhanvien";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM nhanvien WHERE manhanvien = ?";
    
    @Override
    public void insert(Employee entity) {
        try {
            JDBCHelper.update(SQL_INSERT_NHANVIEN,  entity.getMaNhanVien(),
                                                    entity.getTenNhanVien(),
                                                    entity.getLoaiNhanVien(),
                                                    entity.isGioiTinh(),
                                                    entity.getNgaySinh(),
                                                    entity.getDiaChi(),
                                                    entity.getSdt(),
                                                    entity.getEmail(),
                                                    entity.getMatKhau(),
                                                    entity.getHeSoLuong(),
                                                    entity.getHinh(),
                                                    entity.isTrangThai());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Thêm thất bại");
        }
    }

    @Override
    public void delete(String id) {
        try {
            JDBCHelper.update(SQL_DELETE_NHANVIEN, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Xóa thất bại");
        }
    }

    @Override
    public void update(Employee entity) {
        try {
            JDBCHelper.update(SQL_UPDATE_NHANVIEN,  entity.getTenNhanVien(),
                                                    entity.getLoaiNhanVien(),
                                                    entity.isGioiTinh(),
                                                    entity.getNgaySinh(),
                                                    entity.getDiaChi(),
                                                    entity.getSdt(),
                                                    entity.getEmail(),
                                                    entity.getMatKhau(),
                                                    entity.getHeSoLuong(),
                                                    entity.getHinh(),
                                                    entity.isTrangThai(),
                                                    entity.getMaNhanVien());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cập nhật thất bại");
        }
    }

    @Override
    public Employee selectByID(String id) {
        ArrayList<Employee> nhanVienList = selectBySQL(SQL_SELECT_BY_ID, id);
        if (nhanVienList.isEmpty()) {
            return null;
        }
        return nhanVienList.get(0);
    }

    @Override
    public ArrayList<Employee> selectAll() {
        return this.selectBySQL(SQL_SELECT);
    }
    
    public Employee selectByEmail(String email) {
        String sql = "SELECT * FROM nhanvien WHERE email = ?";
        ArrayList<Employee> nhanVienList = this.selectBySQL(sql, email);
        
        return nhanVienList.isEmpty() ? null : nhanVienList.get(0);
    }
    
    public Employee selectByPhone(String phone) {
        String sql = "SELECT * FROM nhanvien WHERE sdt = ?";
        ArrayList<Employee> nhanVienList = this.selectBySQL(sql, phone);
        
        return nhanVienList.isEmpty() ? null : nhanVienList.get(0);
    }

    @Override
    public ArrayList<Employee> selectBySQL(String sql,Object...x) {
        ArrayList<Employee> nhanVienList = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.queryResult(sql, x);
            while (rs.next()) {
                Employee nv = new Employee();
                nv.setMaNhanVien(rs.getString("manhanvien"));
                nv.setTenNhanVien(rs.getString("tennhanvien"));
                nv.setLoaiNhanVien(rs.getString("loainhanvien"));
                nv.setGioiTinh(rs.getBoolean("gioitinh"));
                nv.setNgaySinh(rs.getDate("ngaysinh"));
                nv.setDiaChi(rs.getString("diachi"));
                nv.setSdt(rs.getString("sdt"));
                nv.setEmail(rs.getString("email"));
                nv.setMatKhau(rs.getString("matkhau"));
                nv.setHeSoLuong(rs.getInt("hesoluong"));
                nv.setHinh(rs.getString("hinh"));
                nv.setTrangThai(rs.getBoolean("trangthai"));
                nhanVienList.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVienList;
    }
    
    public List<Employee> selectByName(String name) {
        String sql = "SELECT * FROM nhanvien WHERE tennhanvien LIKE ? AND trangthai = 1";
        return this.selectBySQL(sql, "%"+name+"%");
    }
}
