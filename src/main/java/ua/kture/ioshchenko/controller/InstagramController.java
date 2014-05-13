package ua.kture.ioshchenko.controller;

import com.dropbox.core.DbxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kture.ioshchenko.api.InstagramAPI;

@Controller
public class InstagramController {

    @Autowired
    private InstagramAPI instagramAPI;

    @RequestMapping(value = "/instagram/usercode", method = RequestMethod.GET)
    public String getAuthUrl(Model model) {
        return "redirect:" + instagramAPI.getAuthUrl();
    }

    @RequestMapping(value = "/instagram/accesss", method = RequestMethod.GET)
    public String getAccessCode(@RequestParam String code, Model model) {

        String token = instagramAPI.getAccessToken(code);
         model.addAttribute("inst",token) ;

        return "drop_box_authorize";
    }
}
