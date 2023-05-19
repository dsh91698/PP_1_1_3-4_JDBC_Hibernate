package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String createUserTableSql = "CREATE TABLE IF NOT EXISTS users ("
                + "id BIGINT PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "lastName VARCHAR(255),"
                + "age TINYINT UNSIGNED"
                + ")";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();) {
             statement.execute(createUserTableSql); //create new table if not exist
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        // SQL DROP TABLE statement
        String dropTableSql = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate(dropTableSql);
            System.out.println("Table dropped successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
