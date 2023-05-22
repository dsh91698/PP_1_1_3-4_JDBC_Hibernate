package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT AUTO_INCREMENT," +
//                    "id BIGINT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "lastName VARCHAR(255), " +
                    "age TINYINT UNSIGNED, " +
                    "PRIMARY KEY (id))").executeUpdate();
            session.getTransaction().commit();
            System.out.println("New table created in database!");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void dropUsersTable() {

        String dropTableSql = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery(dropTableSql).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table dropped (removed from database)!");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("user id -> " + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);// User found, delete the user
                System.out.println("User with ID " + id + " deleted from the database!");
            } else {
                System.out.println("User with ID " + id + " do not exist in database!");// User not found
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;

        try (Session session = Util.getSession()) {
            session.beginTransaction();
            userList = session.createQuery("from User").getResultList();
            session.getTransaction().commit();

            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("All users deleted from the database!");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
