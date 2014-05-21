package ua.kture.ioshchenko.controller;

import com.dropbox.core.DbxException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kture.ioshchenko.api.InstagramAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Controller
public class InstagramController {

    @Autowired
    private InstagramAPI instagramAPI;
    private Logger log = Logger.getLogger(InstagramController.class);

    @RequestMapping(value = "/instagram/usercode", method = RequestMethod.GET)
    public String getAuthUrl(Model model) {
        return "redirect:" + instagramAPI.getAuthUrl();
    }

    @RequestMapping(value = "/instagram/accesss", method = RequestMethod.GET)
    public String getAccessCodeAndHubVerifuToken(HttpServletResponse response,
                                                 @RequestParam(required = false) String code,
                                                 @RequestParam(value = "hub.challenge", required = false) String chalenge,
                                                 @RequestParam(value = "hub.verify_token", required = false) String verifyToken,
                                                 Model model) {

        if (code != null) {
            String token = null;
            try {
                token = instagramAPI.getAccessToken(code);
                instagramAPI.createSubscriptions("verify123");

            } catch (IOException e) {
                log.error("Access token error", e);
            }
            model.addAttribute("inst", token);
        } else try {
            log.info("HUB --->>> " + chalenge + "  verify toke: " + verifyToken);
            PrintWriter out = response.getWriter();
            out.print(chalenge);
            out.flush();
        } catch (IOException e) {
            log.error("HUB error ", e);
        }
        return "drop_box_authorize";
    }

    @RequestMapping(value = "/instagram/accesss", method = RequestMethod.POST)
    public void success(HttpServletRequest request) {
        log.info("   Success REGISTRATION ---->>");

        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            log.info("PARAMETR -->> " + paramName);
        }

    }


    @RequestMapping(value = "/instagram/test", method = RequestMethod.GET)
    public void successTest() {
        log.info("   TEST  ---->>");

    }


}
