package tictact0e.app.controller;

import tictact0e.app.entity.Basket;
import tictact0e.app.entity.Book;
import tictact0e.app.service.factory.FactoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MainFrameServlet extends HttpServlet {

    private Collection books;
    private static int quantityInBasket = 0;
    private static double sumCost = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        search(req, resp);

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
            add(req, resp);
        }

        /*
         * edit book
         */
        if (editFlag == 2) {
            edit(req, resp);
        }

        /*
         * add to card
         */
        if (editFlag == 3) {
            addToCard(req, resp);
        }

        updateContent(req, resp);
    }

    private int checkAction(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {

        if (req.getParameter("add") != null)
            return 1;
        if (req.getParameter("edit") != null)
            return 2;
        if (req.getParameter("basket") != null)
            return 3;
        if (req.getParameter("delete") != null) {
            if (req.getParameter("booksId") != null) {
                Book book = FactoryService.getInstance().getBookService().getById(Integer.parseInt(req.getParameter("booksId")));
                FactoryService.getInstance().getBookService().delete(book);
                books = FactoryService.getInstance().getBookService().getAll();

                updateContent(req, resp);
            }
        }

        return 0;
    }

    private void updateContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("quantity", quantityInBasket);
        req.setAttribute("sumCost", sumCost);
        req.setAttribute("booksList", books);


        req.getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchField = req.getParameter("searchField");

        if (searchField == null || searchField.isEmpty()) {
            try {
                books = FactoryService.getInstance().getBookService().getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {

            try {
                if (searchField.matches("[-+]?\\d+")) {
                    int searchId = Integer.parseInt(searchField);
                    Book book = FactoryService.getInstance().getBookService().getById(searchId);
                    Collection tempBooks = new ArrayList();
                    tempBooks.add(book);
                    books = tempBooks;
                } else {
                    books = FactoryService.getInstance().getBookService().getByName(searchField);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Book book = new Book();
            book.setId(FactoryService.getInstance().getBookService().getNewBookId());

            req.setAttribute("newBook", book);
            req.getRequestDispatcher("/BookEditFrame.jsp").forward(req, resp);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter("booksId") != null) {
                Book book = FactoryService.getInstance().getBookService().getById(Integer.parseInt(req.getParameter("booksId")));

                req.setAttribute("newBook", book);
                req.getRequestDispatcher("/BookEditFrame.jsp").forward(req, resp);
            }
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void addToCard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("booksId") != null) {
                Book book = FactoryService.getInstance().getBookService().getById(Integer.parseInt(req.getParameter("booksId")));
                quantityInBasket++;
                sumCost += book.getPrice();
                Basket.getInstance().getBooksBasket().add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

