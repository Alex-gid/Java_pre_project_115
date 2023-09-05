package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Table created");
            System.out.println("-------------");
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("delete from users").executeUpdate();
            System.out.println("User table drop");
            System.out.println("---------------");
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastname, byte age) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name,lastname,age);
            session.save(user);
            System.out.println("User save");
            System.out.println("-------------");
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("User by ID: " + id + " remove");
                System.out.println("-------------");
            }
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<User> userList = session.createQuery("FROM User", User.class).getResultList();
            for (User user : userList) {
                System.out.println(user);
            }
            System.out.println("--------------------");
            return userList;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            System.out.println("Table users clean");
            System.out.println("-------------------");
            transaction.commit();
        }
    }
}
