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
            userService.saveUser("Ivan", "Ivanov", (byte) 25);
            userService.saveUser("Petr", "Petrov", (byte) 26);
            userService.saveUser("Sidor", "Sidorov", (byte) 27);
            userService.saveUser("Egor", "Egorov", (byte) 28);
            userService.getAllUsers(); //get all users from DB and print it out
            userService.removeUserById(1); // delete user
//            userService.cleanUsersTable();
//            userService.getAllUsers(); //get all users from DB and print it out
            userService.dropUsersTable(); // delete table if exist

        
    }
}
