package ua.kture.ioshchenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kture.ioshchenko.dao.UserDAO;
import ua.kture.ioshchenko.model.User;


@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;


    public void add(User user) {
        userDAO.add(user);
    }


    public User get(String email) {
        return userDAO.get(email);
    }

    public void update(User user) {
        userDAO.update(user);
    }
}
