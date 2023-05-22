package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String createUserTableSql = "CREATE TABLE IF NOT EXISTS users ("
                + "id BIGINT AUTO_INCREMENT,"//"id BIGINT PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "lastName VARCHAR(255),"
                + "age TINYINT UNSIGNED,"
                + "PRIMARY KEY (id)"
                + ")";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createUserTableSql); //create new table if not exist
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String dropTableSql = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropTableSql);
            System.out.println("Table dropped successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String insertSql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection()) {

            try (PreparedStatement stat = connection.prepareStatement(insertSql)) {
                stat.setString(1, name);
                stat.setString(2, lastName);
                stat.setByte(3, age);
                stat.executeUpdate();
                System.out.println("User " + name + " added to database!");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String deleteUserSql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getConnection()) {

            try (PreparedStatement stat = connection.prepareStatement(deleteUserSql)) {
                stat.setLong(1, id);
                int quantityDeleted = stat.executeUpdate();
                if (quantityDeleted > 0) {
                    System.out.println("User with ID " + id + " deleted from database!");
                } else {
                    System.out.println("User with ID " + id + " not found in database!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        int usersPrintedOutCounter = 0;
        List<User> userList = new ArrayList<>();
        String allUsersSql = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(allUsersSql)) {
            if (statement.execute()) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("lastName");
                    byte age = resultSet.getByte("age");

                    User user = new User(name, lastName, age);
                    user.setId(id);
                    userList.add(user);
                    System.out.println(user);
                    usersPrintedOutCounter++;
                }
            }

            if (usersPrintedOutCounter > 0) {
                System.out.println("Table printed out successfully");
            } else {
                System.out.println("No users for print in table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        String deleteAllsql = "DELETE FROM users";

        try (Connection connection = Util.getConnection()) {

            try (PreparedStatement stat = connection.prepareStatement(deleteAllsql)) {

                int quantityDeleted = stat.executeUpdate();
                if (quantityDeleted > 0) {
                    System.out.println("All users deleted from database!");
                } else {
                    System.out.println("No Users deleted in database!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
