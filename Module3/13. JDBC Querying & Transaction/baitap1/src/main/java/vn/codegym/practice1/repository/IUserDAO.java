package vn.codegym.practice1.repository;

import vn.codegym.practice1.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    void insertUser(User user) throws SQLException, ClassNotFoundException;

    User selectUser(int id) throws SQLException, ClassNotFoundException;

    List<User> selectAllUsers() throws SQLException, ClassNotFoundException;

    boolean deleteUser(int id) throws SQLException, ClassNotFoundException;

    boolean updateUser(User user) throws SQLException, ClassNotFoundException;

    User getUserById(int id) throws SQLException, ClassNotFoundException;

    void insertUserStore(User user) throws SQLException, ClassNotFoundException;

    void addUserTransaction(User user, List<Integer> permission) throws ClassNotFoundException;

    public void insertUpdateWithoutTransaction();

    public void insertUpdateUseTransaction();

    List<User> getAllUsersStore() throws SQLException, ClassNotFoundException;

    boolean updateUserStore(User user) throws SQLException, ClassNotFoundException;

    boolean deleteUserStore(int id) throws SQLException, ClassNotFoundException;

    void addUserTransaction(User user);


}
