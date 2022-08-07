package cafeteria.dao;

import cafeteria.entity.BillDetail;
import cafeteria.utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDetailDAO implements DAO<BillDetail, Integer>{

    private final String SQL_SELECT_BY_MAHOADON = "SELECT * FROM hoadonchitiet WHERE mahoadon = ?";
    private final String SQL_INSERT_HOADONCHITIET = "INSERT INTO chitiethd(mahoadon, masanpham) VALUES (?,?)";
            
    @Override
    public void insert(BillDetail entity) {
       try {
            JDBCHelper.update(SQL_INSERT_HOADONCHITIET, entity.getMaHoaDon(), entity.getMaSanPham());
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
    public void update(BillDetail entity) {
        // không hỗ trợ
    }

    @Override
    public BillDetail selectByID(Integer id) {
        // không hỗ trợ
        return null;
    }

    @Override
    public List<BillDetail> selectAll() {
        // không hỗ trợ
        return null;
    }

    @Override
    public List<BillDetail> selectBySQL(String sql, Object...x) {
        List<BillDetail> luongList = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.queryResult(sql, x);
            while (rs.next()) {
                BillDetail hdct = new BillDetail();
                hdct.setMaHoaDon(rs.getInt("mahoadon"));
                hdct.setMaSanPham(rs.getString("masanpham"));
                luongList.add(hdct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return luongList;
    }
    
    public List<BillDetail> selectByMaHoaDon(int mahoadon) {
        return this.selectBySQL(SQL_SELECT_BY_MAHOADON, mahoadon);
    }
   
}
