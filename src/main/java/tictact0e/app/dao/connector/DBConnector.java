package tictact0e.app.dao.connector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnector {

    private static DBConnector ourInstance = null;
    private static Connection connection;


    public static synchronized DBConnector getInstance() {

        if (ourInstance == null) {
            try {
                ourInstance = new DBConnector();
                InitialContext initialContext = new InitialContext();
                Context context = (Context) initialContext.lookup("java:comp/env");
                DataSource dataSource = (DataSource) context.lookup("jdbc/books_web");
                connection = dataSource.getConnection();
            } catch (NamingException | SQLException e) {
                e.printStackTrace();
            }

        }

        return ourInstance;

    }

    private DBConnector() {
    }

    public Connection getConnection() {
        return connection;
    }
}
