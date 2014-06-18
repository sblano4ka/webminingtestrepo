package ua.kture.ioshchenko.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kture.ioshchenko.bean.*;
import ua.kture.ioshchenko.model.User;
import ua.kture.ioshchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserRestController {
    private Logger logger = Logger.getLogger(UserRestController.class);
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/rest/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessageBean login(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {
        User user = userService.get(email);
        ResponseMessageBean responseMessage = new ResponseMessageBean();
        if (user != null) {
            if (password.equals(user.getPassword())) {
                responseMessage.setStatus(ua.kture.ioshchenko.bean.ResponseStatus.SUCCESS);
                return responseMessage;
            }

        }
        responseMessage.setStatus(ua.kture.ioshchenko.bean.ResponseStatus.FAILD);
        responseMessage.setMessage("User with this login and password doesn't exist");
        return responseMessage;
    }


    @RequestMapping(value = "/rest/registration", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessageBean registration(@RequestParam String email, @RequestParam String password,
                                        @RequestParam String confirmPassword, Model model, HttpServletRequest request) {
        ResponseMessageBean responseMessage = new ResponseMessageBean();

        if (password.equals(confirmPassword) && !password.isEmpty() && !email.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            userService.add(user);
            responseMessage.setStatus(ua.kture.ioshchenko.bean.ResponseStatus.SUCCESS);
            return responseMessage;
        } else {
            responseMessage.setStatus(ua.kture.ioshchenko.bean.ResponseStatus.FAILD);
            responseMessage.setMessage("Please input correct email and password. All input fields.");
            return responseMessage;
        }
    }
}
