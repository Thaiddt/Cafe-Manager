/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cafeteria.entity.Salary;
import cafeteria.utils.DateUtils;
import cafeteria.utils.JDBCHelper;

public class SalaryDAO implements DAO<Salary, Integer>{

    private final String SQL_INSERT_LUONG = "INSERT INTO luong(manhanvien,ngaydilam,gioden,giora,ghichu) VALUES(?,?,?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM luong";
    
    @Override
    public void insert(Salary entity) {
        try {
            JDBCHelper.update(SQL_INSERT_LUONG, entity.getMaNhanVien(),
                                                DateUtils.convertDateToSqlDate(entity.getNgayDiLam()),
                                                DateUtils.getTimeSql(entity.getGioDen()),
                                                DateUtils.getTimeSql(entity.getGioVe()),
                                                entity.getGhiChu());
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
    public void update(Salary entity) {
        // không hỗ trợ
    }

    @Override
    public Salary selectByID(Integer id) {
        // không hỗ trợ
        return null;
    }

    @Override
    public List<Salary> selectAll() {
        return this.selectBySQL(SQL_SELECT);
    }

    @Override
    public List<Salary> selectBySQL(String sql,Object...x) {
        List<Salary> luongList = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.queryResult(sql, x);
            while (rs.next()) {
                Salary lg = new Salary();
                lg.setMaLuong(rs.getInt("maluong"));
                lg.setMaNhanVien(rs.getString("manhanvien"));
                lg.setNgayDiLam(rs.getDate("ngaydilam"));
                lg.setGioDen(rs.getString("gioden"));
                lg.setGioVe(rs.getString("giora"));
                lg.setGhiChu(rs.getString("ghichu"));
                luongList.add(lg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return luongList;
    }
   
    public List<Salary> selectByName(String name) {
        String sql = "SELECT * FROM luong WHERE ngaydilam = ? AND maNhanVien in (SELECT maNhanVien FROM nhanvien WHERE tennhanvien LIKE ?)";
        return this.selectBySQL(sql, new java.sql.Date(new Date().getTime()) ,"%"+name+"%");
    }
}
