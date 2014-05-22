package ua.kture.ioshchenko.controller;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kture.ioshchenko.api.InstagramAPI;
import ua.kture.ioshchenko.model.User;
import ua.kture.ioshchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class InstagramController {
    private Logger log = Logger.getLogger(InstagramController.class);

    @Autowired
    private InstagramAPI instagramAPI;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/instagram/usercode", method = RequestMethod.GET)
    public String getAuthUrl(Model model) {
        return "redirect:" + instagramAPI.getAuthUrl();
    }

    @RequestMapping(value = "/instagram/accesss", method = RequestMethod.GET)
    public String getAccessCodeAndHubVerifuToken(HttpServletResponse response, HttpServletRequest request,
                                                 @RequestParam String code,
                                                 Model model) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            JSONObject jsonObject = instagramAPI.getAuccess(code);
            JSONObject userID = jsonObject.getJSONObject("user");

            user.setInstagramUserId(userID.getString("id"));
            user.setInstagramAccessToken(jsonObject.get("access_token").toString());
            userService.update(user);

        } catch (IOException e) {
            log.error("Error to access to user account in instagram.", e);
        }

        return "redirect:/";
    }


    @RequestMapping(value = "/instagram/accesss", method = RequestMethod.POST)
    public void success(HttpServletRequest request) throws IOException {
        log.info("User public photo ---->>");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        if (br != null) {
            stringBuilder.append(br.readLine());
        }

        log.info("JSON -->>  " + stringBuilder.toString());


        JSONArray jsonArray = new JSONArray(stringBuilder.toString());

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        log.info("JSON ID -->>  " + jsonObject.get("object_id"));

    }


    @RequestMapping(value = "/instagram/test", method = RequestMethod.GET)
    public void successTest() {
        log.info("   TEST  ---->>");

    }


}
