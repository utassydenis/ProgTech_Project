package database;

import java.sql.*;

public class MySQLConnect {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/progtech";
    private static String username = "root";
    private static String password = "";
    private static Connection connection;

    public static ResultSet executeQuery(String sql) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();

        return result;
    }

    public static void modifyDatabase(String sql) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.executeUpdate();

        connection.close();
    }

    public static void connectDatabase() throws SQLException {
        connection = DriverManager.getConnection(jdbcURL, username, password);
    }
}
