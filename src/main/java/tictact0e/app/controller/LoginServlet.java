package tictact0e.app.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final static String user = "root";
    private final static String pass = "root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("userField");
        String password = req.getParameter("passwordField");

        if (username.equals(user) && password.equals(pass)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", username);
        }

        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);

    }

}
