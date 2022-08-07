package cafeteria.dao;

import cafeteria.entity.Category;
import cafeteria.utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements DAO<Category, Integer>{

    private final String SQL_INSERT_Category = "INSERT INTO LoaiSanPham(TenLoaiSanPham) VALUES(?)";
    private final String SQL_DELETE_Category = "DELETE FROM LoaiSanPham WHERE maCategory = ?";
    private final String SQL_SELECT = "SELECT * FROM LoaiSanPham";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM LoaiSanPham WHERE maloaisanphm = ?";
    
    @Override
    public void insert(Category entity) {
        try {
            JDBCHelper.update(SQL_INSERT_Category,entity.getTenLoaiSanPham());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Thêm thất bại");
        }
    }

    @Override
    public void delete(Integer id) { 
        try {
            JDBCHelper.update(SQL_DELETE_Category, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category entity) {
        // không hỗ trợ
    }

    @Override
    public Category selectByID(Integer id) {
        List<Category> hoaDonList = this.selectBySQL(SQL_SELECT_BY_ID, id);
        if (hoaDonList.isEmpty())
            return null;
        return hoaDonList.get(0);
    }

    @Override
    public List<Category> selectAll() {
        return this.selectBySQL(SQL_SELECT);
    }

    @Override
    public List<Category> selectBySQL(String sql,Object...x) {
        List<Category> CategoryList = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.queryResult(sql, x);
            while (rs.next()) {
                Category lsp = new Category();
                lsp.setMaLoaiSanPham(rs.getInt("MaLoaiSanPham"));
                lsp.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
                CategoryList.add(lsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CategoryList;
    }
}
