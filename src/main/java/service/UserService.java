package service;


import dao.UserDaoJDBCimpl;
import exception.DBException;
import model.User;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public UserService() {
    }

    public User getUserByName(String name) { // Почему нет исключения?????
        return getUserDaoJDBCimpl().getUserByName(name);
    }

    public User getUserById(Long id) {
        return getUserDaoJDBCimpl().getUserByIdDao(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return getUserDaoJDBCimpl().getAllUsersDao();
    }

    public boolean addUser(User user) throws SQLException {
        if (getUserByName(user.getName()) == null) {
            getUserDaoJDBCimpl().addUserDao(user);
            return true;
        }
        return false;
    }

    public void deleteUserById(Long id) throws SQLException {
        getUserDaoJDBCimpl().deleteUserByIdDao(id);
    }

    public void updateUser(User user) throws SQLException {
        getUserDaoJDBCimpl().updateUserDao(user);
    }

    public void createTable() throws DBException {
        UserDaoJDBCimpl dao = getUserDaoJDBCimpl();
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
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

    private static UserDaoJDBCimpl getUserDaoJDBCimpl() {

        return new UserDaoJDBCimpl(getMysqlConnection());
    }

}