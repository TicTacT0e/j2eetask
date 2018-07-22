package tictact0e.app.dao.impl;

import tictact0e.app.dao.UserDao;
import tictact0e.app.dao.connector.DBConnector;
import tictact0e.app.entity.User;
import tictact0e.app.mapping.UserMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class UserDaoImpl implements UserDao {

    @Override
    public Collection getAll() throws SQLException {
        Statement statement = DBConnector.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books_web.users;");
        Collection users = UserMapper.getUsers(resultSet);
        resultSet.close();
        statement.close();
        return users;
    }

    @Override
    public User getById(int id) throws SQLException {
        PreparedStatement preparedStatement =
                DBConnector.getInstance().getConnection().prepareStatement("SELECT * FROM books_web.users WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = UserMapper.getUser(resultSet);
        resultSet.close();
        preparedStatement.close();
        return user;
    }

    @Override
    public void delete(User user) throws SQLException {
        PreparedStatement preparedStatement =
                DBConnector.getInstance().getConnection().prepareStatement("DELETE  FROM books_web.users WHERE id=?");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void insert(User user) throws SQLException {
        PreparedStatement preparedStatement =
                DBConnector.getInstance().getConnection().prepareStatement("INSERT INTO books_web.users(id, username, pass, email) VALUES " +
                        "(?, ?, ?, ?);");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement =
                DBConnector.getInstance().getConnection().prepareStatement("UPDATE books_web.users SET username=?," +
                        " pass=?, price=? WHERE id=?");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setInt(4, user.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    @Override
    public int getNewUserId() throws SQLException {
        Collection users = new ArrayList();
        users = getAll();
        return users.size() + 1;
    }
}
