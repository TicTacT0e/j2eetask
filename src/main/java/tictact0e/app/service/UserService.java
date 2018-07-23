package tictact0e.app.service;

import tictact0e.app.entity.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UserService {

    Collection getAll() throws SQLException;

    User getById(int id) throws SQLException;

    void delete(User user) throws SQLException;

    void insert(User user) throws SQLException;

    void update(User user) throws SQLException;

    int getNewUserId() throws SQLException;
}
