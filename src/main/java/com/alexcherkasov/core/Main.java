package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoHibernateImpl();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        if (sessionFactory != null) {

            System.out.println("Подключение к базе данных успешно установлено.");

            userDao.saveUser("Иван", "Иванов", (byte) 25);
            userDao.saveUser("Петр", "Петров", (byte) 30);
            userDao.saveUser("Сидор", "Сидоров", (byte) 22);
            userDao.saveUser("Владыка", "Владыкин", (byte) 28);

            userDao.cleanUsersTable();
            userDao.dropUsersTable();
            System.out.println(userDao.getAllUsers());

        } else {
            System.out.println("Не удалось подключиться к базе данных.");
        }

    }

}