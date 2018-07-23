package tictact0e.app.dao.impl;

import tictact0e.app.dao.BookDao;
import tictact0e.app.dao.connector.DBConnector;
import tictact0e.app.entity.Book;
import tictact0e.app.mapping.BookMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


public class BookDaoImpl implements BookDao {

    @Override
    public Collection getAll() throws SQLException {
        Statement statement = DBConnector.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM books_web.content");
        Collection books = BookMapper.getBooks(resultSet);
        statement.close();
        resultSet.close();
        return books;
    }

    @Override
    public Collection getByName(String name) throws SQLException {
        PreparedStatement preparedStatement =
                DBConnector.getInstance().getConnection().prepareStatement("SELECT * FROM books_web.content WHERE name=?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        Collection books = BookMapper.getBooks(resultSet);
        preparedStatement.close();
        resultSet.close();
        return books;
    }

    @Override
    public Book getById(int id) throws SQLException {
        PreparedStatement preparedStatement =
                DBConnector.getInstance().getConnection().prepareStatement("SELECT * FROM books_web.content WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Book book = BookMapper.getBook(resultSet);
        preparedStatement.close();
        resultSet.close();
        return book;
    }

    @Override
    public void delete(Book book) throws SQLException {
        PreparedStatement preparedStatement =
                DBConnector.getInstance().getConnection().prepareStatement("DELETE  FROM books_web.content WHERE id=?");
        preparedStatement.setInt(1, book.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void insert(Book book) throws SQLException {
        PreparedStatement preparedStatement =
                DBConnector.getInstance().getConnection().prepareStatement("INSERT INTO books_web.content(id, name, " +
                        "author, price) VALUE (?, ?, ?, ?);");
        preparedStatement.setInt(1, book.getId());
        preparedStatement.setString(2, book.getName());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setDouble(4, book.getPrice());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void update(Book book) throws SQLException {
        PreparedStatement preparedStatement =
                DBConnector.getInstance().getConnection().prepareStatement("UPDATE books_web.content SET name=?, author=?, " +
                        "price=? WHERE  id=?");
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setDouble(3, book.getPrice());
        preparedStatement.setInt(4, book.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public int getNewBookId() throws SQLException {
        Collection books = new ArrayList();
        books = getAll();
        return books.size() + 1;
    }
}
