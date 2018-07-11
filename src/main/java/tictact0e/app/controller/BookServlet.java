package tictact0e.app.controller;

import tictact0e.app.logic.DBManagement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class BookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchField = req.getParameter("searchField");

        Collection books = new ArrayList();

        if (searchField == null || searchField.isEmpty()) {
            try {
                books = DBManagement.getInstance().getAllBooks();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            try {
                books = DBManagement.getInstance().getByName(searchField);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        req.setAttribute("booksList", books);
        req.getRequestDispatcher("/MainFrame.jsp").forward(req, resp);

    }
}
