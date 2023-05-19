package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBCImpl = new UserDaoJDBCImpl();
            userDaoJDBCImpl.createUsersTable(); // create table if NOT exist
            userDaoJDBCImpl.dropUsersTable(); // delete table if exist

        
    }
}
