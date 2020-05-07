package service;


import dao.UserDaoJDBCimpl;
import dao.UserDaoHibernateImpl;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserServiceHibernate {
    private static UserServiceHibernate userServiceHibernate;

    private SessionFactory sessionFactory;

    private UserServiceHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserServiceHibernate getInstance() {
        if (userServiceHibernate == null) {
            userServiceHibernate = new UserServiceHibernate(DBHelper.getSessionFactory());
        }
        return userServiceHibernate;
    }

    public UserServiceHibernate() {
    }


    public User getUserById(Long id) {
        User user = null;
        try {
            user = new UserDaoHibernateImpl(sessionFactory.openSession()).getUserByIdDao(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        return new UserDaoHibernateImpl(sessionFactory.openSession()).
                getAllUsersDao();
    }

    public boolean checkUserByName(String name) {

        try {
            return new UserDaoHibernateImpl(sessionFactory.openSession()).checkUserByNameDao(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUserByLogin(String login) {

        try {
            return new UserDaoHibernateImpl(sessionFactory.openSession()).checkUserByLoginDao(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addUser(User user) {
        if (user.getName() != null && user.getName().length() > 0
                && user.getLogin() != null && user.getLogin().length() > 0
                && user.getPassword() != null && user.getPassword().length() > 0) {
            if (checkUserByName(user.getName()) && (checkUserByLogin(user.getLogin()))) {
                new UserDaoHibernateImpl(sessionFactory.openSession()).addUserDao(user);
            }
        }
    }


    public void deleteUserById(Long id) {
        new UserDaoHibernateImpl(sessionFactory.openSession()).deleteUserByIdDao(id);
    }

    public void updateUser(User user) {

        if (user.getName() != null && user.getName().length() > 0
                && user.getLogin() != null && user.getLogin().length() > 0
                && user.getPassword() != null && user.getPassword().length() > 0) {

            new UserDaoHibernateImpl(sessionFactory.openSession()).updateUserDao(user);

        }
    }


    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("db_example?").
                    append("user=root&").
                    append("password=password");

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDaoJDBCimpl getUserDAO() {
        return new UserDaoJDBCimpl(getMysqlConnection());
    }
}
