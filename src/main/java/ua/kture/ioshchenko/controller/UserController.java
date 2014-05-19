package ua.kture.ioshchenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kture.ioshchenko.dao.UserDAO;
import ua.kture.ioshchenko.model.User;
import ua.kture.ioshchenko.service.UserService;

import java.nio.file.attribute.UserPrincipalLookupService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String addUser(Model model) {
        User user = new User();
        user.setEmail("mail");
        user.setPassword("password");
        userService.add(user);
        return "drop_box_authorize";
    }
}
