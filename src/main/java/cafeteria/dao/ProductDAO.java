/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.dao;

import cafeteria.entity.Product;
import cafeteria.utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO<Product, String>{

    private final String SQL_INSERT_SANPHAM = "INSERT INTO sanpham(masanpham, maloaisanpham, tensanpham, dongia, hinhanh, mota) VALUES(?,?,?,?,?,?)";
    private final String SQL_UPDATE_SANPHAM = "UPDATE sanpham SET maloaisanpham = ?, tensanpham = ?, dongia = ?, hinhanh = ?, mota =? WHERE masanpham = ?";
    private final String SQL_DELETE_SANPHAM = "DELETE FROM sanpham where masanpham = ?";
    private final String SQL_SELECT = "SELECT * FROM sanpham";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM sanpham WHERE masanpham = ?";
    
    @Override
    public void insert(Product entity) {
        try {
            JDBCHelper.update(SQL_INSERT_SANPHAM,entity.getMaSanPham(),
                                                 entity.getMaLoaiSanPham(),
                                                 entity.getTenSanPham(),
                                                 entity.getDonGia(),
                                                 entity.getHinhAnh(),
                                                 entity.getMoTa());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Thêm thất bại");
        }
    }

    @Override
    public void delete(String id) {
        try {
            JDBCHelper.update(SQL_DELETE_SANPHAM, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Xóa thất bại");
        }
    }

    @Override
    public void update(Product entity) {
        try {
            JDBCHelper.update(SQL_UPDATE_SANPHAM, entity.getMaLoaiSanPham(),
                                                  entity.getTenSanPham(),
                                                  entity.getDonGia(),
                                                  entity.getHinhAnh(),
                                                  entity.getMoTa(),
                                                  entity.getMaSanPham());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cập nhật thất bại");
        }
    }

    @Override
    public Product selectByID(String id) {
        List<Product> sanPhamList = selectBySQL(SQL_SELECT_BY_ID, id);
        if (sanPhamList.isEmpty()) {
            return null;
        }
        return sanPhamList.get(0);
    }

    @Override
    public List<Product> selectAll() {
        return this.selectBySQL(SQL_SELECT);
    }

    @Override
    public List<Product> selectBySQL(String sql,Object...x) {
        List<Product> sanPhamList = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.queryResult(sql, x);
            while (rs.next()) {
                Product sp = new Product();
                sp.setMaSanPham(rs.getString("masanpham"));
                sp.setMaLoaiSanPham(rs.getInt("maloaisanpham"));
                sp.setTenSanPham(rs.getString("tensanpham"));
                sp.setDonGia(rs.getInt("dongia"));
                sp.setHinhAnh(rs.getString("hinhanh"));
                sp.setMoTa(rs.getString("mota"));
                sanPhamList.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }
    
    public List<Product> selectByTen(String tenSanPham, Integer maloai) {
        String sql = "SELECT * FROM sanpham WHERE tensanpham LIKE ? AND maloaisanpham = ?";
        
        return this.selectBySQL(sql, "%"+tenSanPham+"%", maloai);
    }
    
    public List<Product> selectByTen(String tenSanPham) {
        String sql = "SELECT * FROM sanpham WHERE tensanpham LIKE ?";
        
        return this.selectBySQL(sql, "%"+tenSanPham+"%");
    }
    
    public List<Product> selectByMaLoai(int maLoaiSanPham) {
        String sql = "SELECT * FROM sanpham WHERE maloaisanpham = ?";
        return this.selectBySQL(sql, maLoaiSanPham);
    }
}
