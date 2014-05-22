package ua.kture.ioshchenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kture.ioshchenko.dao.UserDAO;
import ua.kture.ioshchenko.model.User;
import ua.kture.ioshchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserPrincipalLookupService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam String email, @RequestParam String password,
                               @RequestParam String confirmPassword, Model model, HttpServletRequest request) {
        if (password.equals(confirmPassword) && !password.isEmpty() && !email.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            userService.add(user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("message", "Please input correct email and password. All input fields.");
            return "registration";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {
        User user = userService.get(email);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return "redirect:/";
            }
        }
        model.addAttribute("message", "User with this email and password doesn't exist.");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/";
    }


}
