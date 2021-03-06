package dao;


import exception.DBException;
import model.User;
import org.hibernate.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;

    public UserDaoHibernateImpl(Session session) {
        this.session = session;
    }


    @Override
    public List<User> getAllUsersDao() {

        Transaction transaction = session.beginTransaction();
        List<User> allUsers = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return allUsers;

    }


    @Override
    public User getUserByIdDao(long id) throws SQLException {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where id = :userId");
        List<User> userList = query.setParameter("userId", id).list();
        User user = userList.get(0);

        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public boolean checkUserByNameDao(String name) throws SQLException {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where name = :userName");
        List<User> userList = query.setParameter("userName", name).list();

        transaction.commit();
        session.close();
        if (userList.size() > 0) {
            return false;
        } else return true;
    }

    @Override
    public boolean checkUserByLoginDao(String login) throws SQLException {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where login = :userLogin");
        List<User> userList = query.setParameter("userLogin", login).list();
        transaction.commit();
        session.close();
        if (userList.size() > 0) {
            return false;
        } else return true;
    }

    @Override
    public void addUserDao(User user) throws HibernateException {
        Transaction transaction = session.beginTransaction();
        try {
            // Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }


    @Override
    public void updateUserDao(User user) throws HibernateException {
        Transaction transaction = session.beginTransaction();
      /*  session.createQuery("UPDATE User SET name=:name, login=:login, password=:password WHERE id=:id")
                .setParameter("name", user.getName())
                .setParameter("login", user.getLogin())
                .setParameter("password", user.getPassword())
                .setParameter("id", user.getId())
                .executeUpdate();

        Query query = session.createQuery("UPDATE User SET name=:name, login=:login, password=:password WHERE id=:id")
                .setParameter("name", user.getName())
                .setParameter("login", user.getLogin())
                .setParameter("password", user.getPassword())
                .setParameter("id", user.getId()); */
        String hql = "UPDATE User SET name=:name, login=:login, password=:password WHERE id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("name", user.getName());
        query.setParameter("login", user.getLogin());
        query.setParameter("password", user.getPassword());
        query.setParameter("id", user.getId());

        try {
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }


    @Override
    public void deleteUserByIdDao(Long id) throws HibernateException {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE id = :userId");
        query.setParameter("userId", id);
        // query.executeUpdate();
        try {
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

}
