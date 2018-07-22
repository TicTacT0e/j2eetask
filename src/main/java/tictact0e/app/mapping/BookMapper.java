package tictact0e.app.mapping;


import tictact0e.app.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class BookMapper {

    public static Collection getBooks(ResultSet resultSet) throws SQLException {
        Collection books = new ArrayList();
        Book book;

        while (resultSet.next()) {
            book = mapping(resultSet);
            books.add(book);
        }

        return books;
    }

    public static Book getBook(ResultSet resultSet) throws SQLException {
        Book book;
        if(resultSet.next()){
            book = mapping(resultSet);
            return book;
        } else {
            return null;
        }
    }


    private static Book mapping(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        book.setPrice(resultSet.getDouble("price"));
        return book;
    }

}
