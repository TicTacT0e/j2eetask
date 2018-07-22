package tictact0e.app.controller;

import tictact0e.app.connector.DBManagement;
import tictact0e.app.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

public class TestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h3> Список книг </h3>");
        printWriter.println("<table border = 1>");
        try {
            Collection books = DBManagement.getInstance().getAllBooks();
            for (Object book1 : books) {
                Book book = (Book) book1;
                printWriter.println("<tr>");
                printWriter.println("<td>" + book.getId() + "</td>");
                printWriter.println("<td>" + book.getName() + "</td>");
                printWriter.println("<td>" + book.getAuthor() + "</td>");
                printWriter.println("<td>" + book.getPrice() + "</td>");
                printWriter.println("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printWriter.println("</table>");

    }
}
