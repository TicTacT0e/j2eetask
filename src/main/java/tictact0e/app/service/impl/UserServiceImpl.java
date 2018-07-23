package tictact0e.app.service.impl;

import tictact0e.app.dao.factory.FactoryDao;
import tictact0e.app.entity.User;
import tictact0e.app.service.UserService;

import java.sql.SQLException;
import java.util.Collection;

public class UserServiceImpl implements UserService{

    @Override
    public Collection getAll() throws SQLException {
        return FactoryDao.getInstance().getUserDao().getAll();
    }

    @Override
    public User getById(int id) throws SQLException {
        return FactoryDao.getInstance().getUserDao().getById(id);
    }

    @Override
    public void delete(User user) throws SQLException {
        FactoryDao.getInstance().getUserDao().delete(user);
    }

    @Override
    public void insert(User user) throws SQLException {
        FactoryDao.getInstance().getUserDao().insert(user);
    }

    @Override
    public void update(User user) throws SQLException {
        FactoryDao.getInstance().getUserDao().update(user);
    }

    @Override
    public int getNewUserId() throws SQLException {
        return FactoryDao.getInstance().getUserDao().getNewUserId();
    }
}
