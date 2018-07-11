package tictact0e.app.logic;

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

    private void getBooksCollection(ResultSet resultSet, Collection books) throws SQLException {

        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt(1));
            book.setName(resultSet.getString(2));
            book.setAutor(resultSet.getString(3));
            book.setPrice(resultSet.getDouble(4));
            books.add(book);

        }
    }
}
