package ua.kture.ioshchenko.dao;

import ua.kture.ioshchenko.model.User;

public interface UserDAO {
    public User add(User user);

    public User get(String email, String password);
}
