package tictact0e.app.controller;

import tictact0e.app.logic.DBManagement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class MainFrameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Collection books = DBManagement.getInstance().getAllBooks();

            req.setAttribute("booksList", books);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }
}
