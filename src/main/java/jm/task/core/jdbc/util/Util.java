package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String URL = "jdbc:mysql://localhost:3306/kataTest";
    private final static String USER = "root";
    private final static String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        System.out.println("getting connection to database ... ");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
