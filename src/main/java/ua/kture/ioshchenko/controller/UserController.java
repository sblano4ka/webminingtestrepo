package ua.kture.ioshchenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.kture.ioshchenko.dao.UserDAO;

@Controller
public class UserController {
    @Autowired
    private UserDAO userDAO;
}
