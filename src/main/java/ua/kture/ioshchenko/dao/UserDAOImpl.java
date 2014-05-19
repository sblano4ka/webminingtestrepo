package ua.kture.ioshchenko.dao;

import org.springframework.stereotype.Repository;
import ua.kture.ioshchenko.model.User;

@Repository
public class UserDAOImpl implements UserDAO {


    @Override
    public void add(User user) {

    }

    @Override
    public User get(String email, String password) {
        return null;
    }

    @Override
    public void update(User user) {

    }
}
