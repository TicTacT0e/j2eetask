package tictact0e.app.controller;

import tictact0e.app.connector.DBManagement;
import tictact0e.app.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class MainFrameServlet extends HttpServlet {

    private Collection books;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchField = req.getParameter("searchField");

        //  Collection books = new ArrayList();

        if (searchField == null || searchField.isEmpty()) {
            try {
                books = DBManagement.getInstance().getAllBooks();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {

            try {
                if (searchField.matches("[-+]?\\d+")) {
                    int searchId = Integer.parseInt(searchField);
                    books = DBManagement.getInstance().getById(searchId);
                } else {
                    books = DBManagement.getInstance().getByName(searchField);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        int editFlag = 0;
        try {
            editFlag = checkAction(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
         * add book
         */
        if (editFlag == 1) {
            try {
                Book book = new Book();
                book.setId(DBManagement.getInstance().getNewId());

                req.setAttribute("newBook", book);
                req.getRequestDispatcher("/BookEditFrame.jsp").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        /*
         * edit book
         */
        if (editFlag == 2) {
            try {
                if (req.getParameter("booksId") != null) {
                    Book book = DBManagement.getInstance().getBookById(Integer.parseInt(req.getParameter("booksId")));

                    req.setAttribute("newBook", book);
                    req.getRequestDispatcher("/BookEditFrame.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        updateContent(req, resp);
    }

    private int checkAction(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {

        if (req.getParameter("add") != null)
            return 1;
        if (req.getParameter("edit") != null)
            return 2;
        if (req.getParameter("delete") != null) {
            if (req.getParameter("booksId") != null) {
                DBManagement.getInstance().deleteBook(Integer.parseInt(req.getParameter("booksId")));

                books = DBManagement.getInstance().getAllBooks();
                updateContent(req, resp);

            }
        }

        return 0;
    }

    private void updateContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("booksList", books);
        req.getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }
}
