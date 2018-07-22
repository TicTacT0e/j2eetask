package tictact0e.app.dao.factory;

import tictact0e.app.dao.BookDao;
import tictact0e.app.dao.UserDao;
import tictact0e.app.dao.impl.BookDaoImpl;
import tictact0e.app.dao.impl.UserDaoImpl;

public class FactoryDao {

    private static FactoryDao ourInstance = new FactoryDao();

    public static FactoryDao getInstance() {
        return ourInstance;
    }

    private FactoryDao() {}

    private UserDao userDao = new UserDaoImpl();

    private BookDao bookDao = new BookDaoImpl();

    public UserDao getUserDao() {
        return userDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }
}
