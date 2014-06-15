package ua.kture.ioshchenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kture.ioshchenko.bean.RecipeBean;
import ua.kture.ioshchenko.dao.RecipeDAO;
import ua.kture.ioshchenko.model.Channel;
import ua.kture.ioshchenko.model.ChannelAction;
import ua.kture.ioshchenko.model.Recipe;
import ua.kture.ioshchenko.model.User;
import ua.kture.ioshchenko.service.ChannelService;
import ua.kture.ioshchenko.service.RecipeService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RecipeController {
    @Autowired
    private ChannelService channelService;

    @Autowired
    private RecipeService recipeService;


    @RequestMapping(value = "/myrecipe", method = RequestMethod.GET)
    public String myRecipePage(Model model) {
        return "my_recipe";
    }

    @RequestMapping(value = "/myrecipe/new", method = RequestMethod.GET)
    public String newRecipePage(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        if (user.getDropBoxAccessToken() == null) {
            return "redirect:/dropbox/authorize";
        }
        if (user.getInstagramAccessToken() == null) {
            return "redirect:/instagram/usercode";
        }
        return "new_recipe";
    }

    @RequestMapping(value = "/getTriggerChanel", method = RequestMethod.GET)
    @ResponseBody
    public List<Channel> getTriggerChanel(@RequestParam String type, Model model) {
        if (type.equals("this")) {
            return channelService.getAllServiceThis();
        } else {
            return channelService.getAllServiceThat();
        }
    }


    @RequestMapping(value = "/getTrigger", method = RequestMethod.GET)
    @ResponseBody
    public List<ChannelAction> getTrigger(@RequestParam Long id, @RequestParam String type, Model model) {
        if (type.equals("this")) {
            return channelService.getListServiceActionThis(id);
        } else {
            return channelService.getListServiceActionThat(id);
        }
    }

    @RequestMapping(value = "/createRecipe", method = RequestMethod.POST)
    @ResponseBody
    public RecipeBean getTrigger(@RequestBody RecipeBean recipe, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        recipeService.add(recipe, user);
        return recipe;
    }

    @RequestMapping(value = "/getUserRecipes", method = RequestMethod.GET)
    @ResponseBody
    public List<Recipe> getUserRecipes(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return recipeService.getUserRecipes(user);
    }

}
