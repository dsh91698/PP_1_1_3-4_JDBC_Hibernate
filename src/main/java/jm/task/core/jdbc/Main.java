package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
//        UserDao userService = new UserDaoJDBCImpl();
        UserService userService = new UserServiceImpl();
            userService.createUsersTable(); // create table if NOT exist
            userService.saveUser("Alex", "Ivanov", (byte) 125);
            userService.saveUser("Petr", "Petrov", (byte) 36);
            userService.saveUser("Sidor", "Sidorov", (byte) 227);
            userService.saveUser("Egor", "Egorov", (byte) 18);
            userService.saveUser("Igor", "Igorov", (byte) 38);
            userService.getAllUsers(); //get all users from DB and print it out
            userService.removeUserById(3); // delete user
            userService.cleanUsersTable(); // remove ALL users from table
            userService.getAllUsers(); // check if all users removed
            userService.dropUsersTable(); // delete/drop table if exist

        
    }
}
