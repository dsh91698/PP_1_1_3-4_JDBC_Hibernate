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
            userDaoJDBCImpl.saveUser("Ivan", "Ivanov", (byte) 25);
            userDaoJDBCImpl.saveUser("Petr", "Petrov", (byte) 26);
            userDaoJDBCImpl.saveUser("Sidor", "Sidorov", (byte) 27);
            userDaoJDBCImpl.saveUser("Egor", "Egorov", (byte) 28);
            userDaoJDBCImpl.getAllUsers(); //get all users from DB and print it out
            userDaoJDBCImpl.removeUserById(1); // delete used
            userDaoJDBCImpl.cleanUsersTable();
            userDaoJDBCImpl.getAllUsers(); //get all users from DB and print it out
            userDaoJDBCImpl.dropUsersTable(); // delete table if exist

        
    }
}
