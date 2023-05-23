package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        Transaction transaction = null;
        try (Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT AUTO_INCREMENT," +
//                    "id BIGINT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "lastName VARCHAR(255), " +
                    "age TINYINT UNSIGNED, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
//            session.getTransaction().commit();
            System.out.println("New table created in database!");

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();// Roll back the transaction
            }
        }


    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;

        String dropTableSql = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(dropTableSql).executeUpdate();
            transaction.commit();
            System.out.println("Table dropped (removed from database)!");

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();// Roll back the transaction
            }

        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;

        try (Session session = Util.getSession()) {
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("user id -> " + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();// Roll back the transaction
            }

        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try (Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);// User found, delete the user
                System.out.println("User with ID " + id + " deleted from the database!");
            } else {
                System.out.println("User with ID " + id + " do not exist in database!");// User not found
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();// Roll back the transaction
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;

        List<User> userList = null;

        try (Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("from User").getResultList();
            transaction.commit();

            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();// Roll back the transaction
            }

        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;

        try (Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
            System.out.println("All users deleted from the database!");

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();// Roll back the transaction
            }

        }


    }
}
