package tictact0e.app.logic;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManagement
{
    private static DBManagement ourInstance = null;
    private static Connection connection;
    private static DataSource dataSource;

    public static synchronized DBManagement getInstance() {

        if (ourInstance == null){
            try {
                ourInstance = new DBManagement();
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/books_web");
                connection = dataSource.getConnection();
            } catch (NamingException e){
                e.printStackTrace();
            } catch (SQLException e){
                e.printStackTrace();
            }

        }

        return ourInstance;

    }

    private DBManagement() {
    }
}
