package tictact0e.app.service.factory;

import tictact0e.app.service.BookService;
import tictact0e.app.service.UserService;
import tictact0e.app.service.impl.BookServiceImpl;
import tictact0e.app.service.impl.UserServiceImpl;

public class FactoryService {
    private static FactoryService ourInstance = new FactoryService();

    public static FactoryService getInstance() {
        return ourInstance;
    }

    private FactoryService() {}

    private UserService userService = new UserServiceImpl();

    private BookService bookService = new BookServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
