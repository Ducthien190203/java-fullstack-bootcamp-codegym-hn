package vn.codegym.thuchanh2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/thuchanh2?useSSL=false";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "ducthien2014";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
