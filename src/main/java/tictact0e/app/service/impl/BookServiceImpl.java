package tictact0e.app.service.impl;

import tictact0e.app.dao.factory.FactoryDao;
import tictact0e.app.entity.Book;
import tictact0e.app.service.BookService;

import java.sql.SQLException;
import java.util.Collection;

public class BookServiceImpl implements BookService {

    private FactoryDao factoryDao;

    @Override
    public Collection getAll() throws SQLException {
        return FactoryDao.getInstance().getBookDao().getAll();
    }

    @Override
    public Collection getByName(String name) throws SQLException {
        return FactoryDao.getInstance().getBookDao().getByName(name);
    }

    @Override
    public Book getById(int id) throws SQLException {
        return FactoryDao.getInstance().getBookDao().getById(id);
    }

    @Override
    public void delete(Book book) throws SQLException {
        FactoryDao.getInstance().getBookDao().delete(book);
    }

    @Override
    public void insert(Book book) throws SQLException {
        FactoryDao.getInstance().getBookDao().insert(book);
    }

    @Override
    public void update(Book book) throws SQLException {
        FactoryDao.getInstance().getBookDao().update(book);
    }

    @Override
    public int getNewBookId() throws SQLException {
        return FactoryDao.getInstance().getBookDao().getNewBookId();
    }
}
