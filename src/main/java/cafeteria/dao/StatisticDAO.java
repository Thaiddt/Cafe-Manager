/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cafeteria.utils.JDBCHelper;

public class StatisticDAO {
    
    public List<Object[]> getDoanhThu(int nam) throws SQLException {
        String cols[] = {"thang","hoadonnhonhat","hoadonlonnhat","tongtien"};
        String sqlpro = "{CALL pro_doanhthu(?)}";
        
        return getProcedure(sqlpro, cols, nam);
    }
    
    public List<Object[]> getMatHangBanChay(int thang, int nam, boolean isForeigner) throws SQLException{
        String cols[] = {"masanpham","tensanpham","thoigian","soluong"};
        String sqlpro = "CALL pro_mathangbanchay(?,?,?)";
        
        return getProcedure(sqlpro, cols, thang, nam, isForeigner);
    }
    
    public List<Object[]> getLuongNhanVien(int thang, int nam) throws SQLException{
        String cols[] = {"manhanvien","tennhanvien","thoigian","tonggiolam"};
        String sqlpro = "{CALL pro_luongnhanvien(?,?)}";
        
        return getProcedure(sqlpro, cols, thang, nam);
    }
    
    private List<Object[]> getProcedure(String sqlPro,String[] cols, Object...parameter) throws SQLException {
        List<Object[]> obList = new ArrayList<>();
        ResultSet rs = JDBCHelper.queryResult(sqlPro, parameter);
        while (rs.next()) {
            Object[] obArray = new Object[cols.length];
            for (int i = 0; i < obArray.length; i++) {
                obArray[i] = rs.getObject(cols[i]);
            }
            obList.add(obArray);
        }
        return obList;
    }
    
    public List<Object[]> getHoaDonChiTiet(int mahoadon) throws SQLException {
        String cols[] = {"tensanpham","soluong","gia"};
        String sqlpro = "{CALL pro_hoadonchitiet(?)}";
        
        return getProcedure(sqlpro, cols, mahoadon);
    }
}
