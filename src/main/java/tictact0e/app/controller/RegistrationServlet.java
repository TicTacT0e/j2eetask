package tictact0e.app.controller;

import tictact0e.app.entity.User;
import tictact0e.app.service.factory.FactoryService;
import tictact0e.app.validators.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder errors = new StringBuilder();

        try{
            if (req.getParameter("newUserId") != null && req.getParameter("ok") != null){

                //Collection registeredUsers = DBManagement.getInstance().getAllUsers();
                Collection registeredUsers = FactoryService.getInstance().getUserService().getAll();

                if(req.getParameter("usernameField") == null || req.getParameter("usernameField").isEmpty()) {
                    errors.append("Username field is empty. ");
                }

                if(req.getParameter("passwordField") == null || req.getParameter("passwordField").isEmpty()) {
                    errors.append("Password field is empty. ");
                }

                if(req.getParameter("repeatedPassword") == null || req.getParameter("repeatedPassword").isEmpty()) {
                    errors.append("Confirm password field is empty. ");
                }

                if(req.getParameter("emailField") == null || req.getParameter("emailField").isEmpty()) {
                    errors.append("Email field is empty. ");
                }

                if(!EmailValidator.validate(req.getParameter("emailField"))){
                    errors.append("This is not an email. ");
                }

                if(!req.getParameter("passwordField").equals(req.getParameter("repeatedPassword"))){
                    errors.append("Passwords do not match. ");
                }

                for (Object ob : registeredUsers){
                    User registeredUser = (User) ob;

                    if(req.getParameter("emailField").equals(registeredUser.getEmail())){
                        errors.append("This email is registered. ");
                    }

                    if(req.getParameter("usernameField").equals(registeredUser.getUsername())){
                        errors.append("This nickname is registered. ");
                    }
                }

                if (errors.length() == 0){
                   User user = new User();
                   user.setId(Integer.parseInt(req.getParameter("newUserId")));
                   user.setUsername(req.getParameter("usernameField"));
                   user.setPassword(req.getParameter("passwordField"));
                   user.setEmail(req.getParameter("emailField"));

                  // DBManagement.getInstance().insertUsers(user);
                    FactoryService.getInstance().getUserService().insert(user);
                   req.getRequestDispatcher("/LoginFrame.jsp").forward(req, resp);
                } else {
                    int newUserId = Integer.parseInt(req.getParameter("newUserId"));
                    req.setAttribute("newUserId", newUserId);
                    req.setAttribute("errors", errors.toString());
                    req.getRequestDispatcher("/RegistrationFrame.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        if (req.getParameter("cancel") != null) {
            req.getRequestDispatcher("/LoginFrame.jsp").forward(req, resp);
        }


    }
}
