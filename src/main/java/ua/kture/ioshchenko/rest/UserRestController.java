package ua.kture.ioshchenko.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kture.ioshchenko.bean.RecipeBean;
import ua.kture.ioshchenko.bean.UserBean;
import ua.kture.ioshchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserRestController {
    private Logger logger = Logger.getLogger(UserRestController.class);
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/rest/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(Model model, HttpServletRequest request) {
        logger.info("!!!!!");


        return "success";
    }
}
