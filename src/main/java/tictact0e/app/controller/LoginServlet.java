package tictact0e.app.controller;

import tictact0e.app.entity.User;
import tictact0e.app.service.factory.FactoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class LoginServlet extends HttpServlet {

    private Collection users;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usernameField = req.getParameter("userField");
        String passwordField = req.getParameter("passwordField");

        try {
            users = FactoryService.getInstance().getUserService().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Object ob : users){
            User user = (User) ob;
            if (user.getUsername().equals(usernameField) && user.getPassword().equals(passwordField)){
                HttpSession session = req.getSession(true);
                session.setAttribute("user", usernameField);
                try {
                    Collection books = FactoryService.getInstance().getBookService().getAll();
                    req.setAttribute("booksList", books);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            }
        }

        if (req.getParameter("registration") != null){
            try {
                //int newUserId = DBManagement.getInstance().getNewUserId();
                int newUserId = FactoryService.getInstance().getUserService().getNewUserId();
                req.setAttribute("newUserId", newUserId);
                req.getRequestDispatcher("/RegistrationFrame.jsp").forward(req, resp);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);

    }

}
