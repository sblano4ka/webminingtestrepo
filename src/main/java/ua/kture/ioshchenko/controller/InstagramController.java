package ua.kture.ioshchenko.controller;

import com.dropbox.core.DbxException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kture.ioshchenko.api.dropbox.DropBoxAPI;
import ua.kture.ioshchenko.api.instagram.InstagramAuthentication;
import ua.kture.ioshchenko.api.instagram.InstagramEndpoints;
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
    private InstagramAuthentication instagramAuthentication;
    @Autowired
    private InstagramEndpoints instagramEndpoints;
    @Autowired
    private UserService userService;

    @Autowired
    private DropBoxAPI dropBoxAPI;


    @RequestMapping(value = "/instagram/usercode", method = RequestMethod.GET)
    public String getAuthUrl(Model model) {
        return "redirect:" + instagramAuthentication.getAuthUrl();
    }

    @RequestMapping(value = "/instagram/accesss", method = RequestMethod.GET)
    public String getAccessCodeAndHubVerifuToken(HttpServletResponse response, HttpServletRequest request,
                                                 @RequestParam String code,
                                                 Model model) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            JSONObject jsonObject = instagramAuthentication.getAuccess(code);
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

        JSONArray jsonArray = new JSONArray(stringBuilder.toString());

        JSONObject jsonObjectId = jsonArray.getJSONObject(0);
        JSONObject data = jsonObjectId.getJSONObject("data");

        User user = userService.getUserByInstagramUserId(jsonObjectId.get("object_id").toString());
        String url = instagramEndpoints.getImageUrl(data.get("media_id").toString(), user.getInstagramAccessToken());

        try {
            dropBoxAPI.uploadFile(url, user.getDropBoxAccessToken());
        } catch (DbxException e) {
            log.error("Uload error", e);
        }
    }

}
