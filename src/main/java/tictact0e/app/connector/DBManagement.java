package tictact0e.app.connector;

import tictact0e.app.model.Book;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DBManagement {
    private static DBManagement ourInstance = null;
    private static Connection connection;


    public static synchronized DBManagement getInstance() {

        if (ourInstance == null) {
            try {
                ourInstance = new DBManagement();
                InitialContext initialContext = new InitialContext();
                Context context = (Context) initialContext.lookup("java:comp/env");
                DataSource dataSource = (DataSource) context.lookup("jdbc/books_web");
                connection = dataSource.getConnection();
            } catch (NamingException | SQLException e) {
                e.printStackTrace();
            }

        }

        return ourInstance;

    }

    private DBManagement() {
    }

    public Collection getAllBooks() throws SQLException {
        Collection books = new ArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books_web.content;");
        getBooksCollection(resultSet, books);
        resultSet.close();
        statement.close();
        return books;
    }

    public Collection getByName(String searchName) throws SQLException {
        Collection books = new ArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books_web.content WHERE name ='" +
                searchName + "'");
        getBooksCollection(resultSet, books);
        resultSet.close();
        statement.close();
        return books;
    }

    public Collection getById(int searchId) throws SQLException {
        Collection books = new ArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books_web.content WHERE id =" +
                searchId);
        getBooksCollection(resultSet, books);
        resultSet.close();
        statement.close();
        return books;
    }

    public Book getBookById(int searchId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books_web.content WHERE id =" +
                searchId);
        Book book = new Book();
        if (resultSet.next()) {
            book.setId(resultSet.getInt(1));
            book.setName(resultSet.getString(2));
            book.setAuthor(resultSet.getString(3));
            book.setPrice(resultSet.getDouble(4));
        }

        resultSet.close();
        statement.close();
        return book;
    }

    public void deleteBook(int deleteId) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM books_web.content WHERE id=" + deleteId);
        statement.close();
    }

    public void insertBook(Book book) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO books_web.content(id, name, author, price)" +
                "VALUES (" + book.getId() + ", '" + book.getName() + "', '" + book.getAuthor() + "', " + book.getPrice() + ");");
        statement.close();
    }

    public void updateBook(Book book) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE books_web.content SET name = '" + book.getName() + "', author = '" +
                book.getAuthor() + "', price = " + book.getPrice() + " WHERE id = " + book.getId() + " ;");
        statement.close();
    }

    public int getNewId() throws SQLException {
        Collection books = new ArrayList();
        books = getAllBooks();
        return books.size() + 1;
    }

    private void getBooksCollection(ResultSet resultSet, Collection books) throws SQLException {

        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt(1));
            book.setName(resultSet.getString(2));
            book.setAuthor(resultSet.getString(3));
            book.setPrice(resultSet.getDouble(4));
            books.add(book);

        }
    }
}
