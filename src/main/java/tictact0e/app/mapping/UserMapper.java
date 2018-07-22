package tictact0e.app.mapping;


import tictact0e.app.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class UserMapper {


    public static Collection getUsers(ResultSet resultSet) throws SQLException {
        Collection users = new ArrayList();
        User user;

        while (resultSet.next()) {
            user = mapping(resultSet);
            users.add(user);
        }
        return users;
    }

    public static User getUser(ResultSet resultSet) throws SQLException {
        User user;

        if(resultSet.next()){
            user = mapping(resultSet);
            return user;
        } else {
            return null;
        }
    }

    private static User mapping(ResultSet resultSet) throws SQLException{
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("pass"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }

}
