package tictact0e.app.dao;

import tictact0e.app.entity.Book;

import java.sql.SQLException;
import java.util.Collection;

public interface BookDao {

    Collection getAll() throws SQLException;

    Collection getByName(String name) throws SQLException;

    Book getById(int id) throws SQLException;

    void delete(Book book) throws SQLException;

    void insert(Book book) throws SQLException;

    void update(Book book) throws SQLException;

    int getNewBookId() throws SQLException;
}
