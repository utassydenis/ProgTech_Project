package database;

import java.sql.*;

public class MySQLConnect {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/progtech";
    private static String username = "root";
    private static String password = "";

    public static ResultSet executeQuery(String sql) throws SQLException {

        Connection connection = DriverManager.getConnection(jdbcURL, username, password);

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);

        connection.close();

        return result;
    }

    public static void modifyDatabase(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcURL, username, password);

        Statement statement = connection.createStatement();

        statement.executeUpdate(sql);
    }
}
