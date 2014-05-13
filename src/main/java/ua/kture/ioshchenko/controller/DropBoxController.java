package ua.kture.ioshchenko.controller;

import com.dropbox.core.DbxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kture.ioshchenko.api.DropBoxAPI;

@Controller
public class DropBoxController {

    @Autowired
    private DropBoxAPI dropBoxAPI;


    @RequestMapping(value = "/dropbox/authorize", method = RequestMethod.GET)
    public String authorizePage(Model model) {
        return "drop_box_authorize";
    }

    @RequestMapping(value = "/dropbox/code", method = RequestMethod.POST)
    public String getAccessCode(@RequestParam String code, Model model) {
        try {
            String token = dropBoxAPI.getAccessToken(code);
            return "redirect:/";
        } catch (DbxException e) {
            model.addAttribute("error", "Please input correct code from DropBox.");
        }
        return "drop_box_authorize";
    }


    @RequestMapping(value = "/dropbox/usercode", method = RequestMethod.GET)
    public String getAuthUrl(Model model) {
        return "redirect:" + dropBoxAPI.getAuthUrl();
    }

}
