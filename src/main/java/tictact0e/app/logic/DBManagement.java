package tictact0e.app.logic;

import tictact0e.app.model.Book;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class DBManagement
{
    private static DBManagement ourInstance = null;
    private static Connection connection;



    public static synchronized DBManagement getInstance() {

        if (ourInstance == null){
            try {
                ourInstance = new DBManagement();
                InitialContext initialContext = new InitialContext();
                Context context = (Context) initialContext.lookup("java:comp/env");
                DataSource dataSource = (DataSource) context.lookup("jdbc/books_web");
                connection = dataSource.getConnection();
            } catch (NamingException | SQLException e){
                e.printStackTrace();
            }

        }

        return ourInstance;

    }

    private DBManagement() {
    }

    public ArrayList getAllBooks () throws SQLException{
        ArrayList<Book> books = new ArrayList<Book>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books_web.content;");
        while (resultSet.next()){
            Book book = new Book();
            book.setId(resultSet.getInt(1));
            book.setName(resultSet.getString(2));
            book.setAutor(resultSet.getString(3));
            book.setPrice(resultSet.getDouble(4));
            books.add(book);
        }
        resultSet.close();
        statement.close();
        return books;
    }
}
