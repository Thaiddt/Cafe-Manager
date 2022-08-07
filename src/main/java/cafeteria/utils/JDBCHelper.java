package cafeteria.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCHelper {

    private static Connection con;
    private static final String USER = "admin";
    private static final String PASSWORD = "NguyenVanSuong@2001";
//    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String CONNECT = "jdbc:postgresql://0.0.0.0:5432/appbanhang";

    public static void openConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        con = DriverManager.getConnection(CONNECT, USER, PASSWORD);
    }

    private static PreparedStatement getPrepareStatement(String sql, Object... parameter) throws SQLException {
        PreparedStatement pre = compareTo(sql);
        addParameterTo(pre, parameter);
        return pre;
    }

    // kiem tra sql la procedure stored hay sql binh thuong
    private static PreparedStatement compareTo(String sql) throws SQLException {
        return sql.startsWith("{") ? con.prepareCall(sql) : con.prepareStatement(sql);
    }

    // them cac tham so vao PreparedStatement
    private static void addParameterTo(PreparedStatement pre, Object... x) throws SQLException {
        for (int i = 0; i < x.length; i++) {
            pre.setObject(i + 1, x[i]);
        }
    }

    public static int update(String sql, Object... parameter) throws SQLException {
        return getPrepareStatement(sql, parameter).executeUpdate();
    }

    public static ResultSet queryResult(String sql, Object... parameter) throws SQLException {
        return getPrepareStatement(sql, parameter).executeQuery();
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            try {
                stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECT, USER, PASSWORD);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);
        } else {
            pstmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }
}
