package tictact0e.app.controller;

import tictact0e.app.entity.Book;
import tictact0e.app.service.factory.FactoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class BookEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            if (req.getParameter("newBook") != null && req.getParameter("ok") != null) {
                int tempFlag;

                tempFlag = FactoryService.getInstance().getBookService().getNewBookId();

                if (tempFlag == Integer.parseInt(req.getParameter("newBook"))) {
                    insertBook(req);
                } else {
                    updateBook(req);
                }
            }

            Collection books;
            //books = DBManagement.getInstance().getAllBooks();
            books = FactoryService.getInstance().getBookService().getAll();

            req.setAttribute("booksList", books);
            req.getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void insertBook(HttpServletRequest req) throws SQLException {
        Book book = prepareBook(req);
        FactoryService.getInstance().getBookService().insert(book);
    }

    private void updateBook(HttpServletRequest req) throws SQLException {
        Book book = prepareBook(req);
        FactoryService.getInstance().getBookService().update(book);
    }

    private Book prepareBook(HttpServletRequest req) {
        Book book = new Book();
        book.setId(Integer.parseInt(req.getParameter("newBook")));
        book.setName(req.getParameter("booksNameField"));
        book.setAuthor(req.getParameter("authorField"));
        book.setPrice(Double.parseDouble(req.getParameter("priceField")));
        return book;
    }
}
