package ua.kture.ioshchenko.controller;

import com.dropbox.core.DbxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kture.ioshchenko.api.DropBoxAPI;
import ua.kture.ioshchenko.model.User;
import ua.kture.ioshchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DropBoxController {

    @Autowired
    private DropBoxAPI dropBoxAPI;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/dropbox/authorize", method = RequestMethod.GET)
    public String authorizePage(Model model) {
        return "drop_box_authorize";
    }

    @RequestMapping(value = "/dropbox/code", method = RequestMethod.POST)
    public String getAccessCode(@RequestParam String code, Model model, HttpServletRequest request) {
        try {
            String token = dropBoxAPI.getAccessToken(code);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            user.setDropBoxAccessToken(token);
            userService.update(user);
            return "redirect:/";
        } catch (DbxException e) {
            model.addAttribute("message", "Please input correct code from DropBox.");
        }
        return "drop_box_authorize";
    }


    @RequestMapping(value = "/dropbox/usercode", method = RequestMethod.GET)
    public String getAuthUrl(Model model) {
        return "redirect:" + dropBoxAPI.getAuthUrl();
    }

}
