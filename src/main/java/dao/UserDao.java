package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {


    List<User> getAllUsersDao() throws SQLException;


    User getUserByIdDao(long id) throws SQLException;


    boolean checkUserByNameDao(String name) throws SQLException;


    boolean checkUserByLoginDao(String login) throws SQLException;


    void addUserDao(User user) throws SQLException;


    void updateUserDao(User user) throws SQLException;


    void deleteUserByIdDao(Long id) throws SQLException;

}
