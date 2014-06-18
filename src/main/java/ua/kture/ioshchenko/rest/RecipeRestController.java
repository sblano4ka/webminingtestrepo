package ua.kture.ioshchenko.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kture.ioshchenko.bean.*;
import ua.kture.ioshchenko.model.Channel;
import ua.kture.ioshchenko.model.ChannelAction;
import ua.kture.ioshchenko.model.Recipe;
import ua.kture.ioshchenko.model.User;
import ua.kture.ioshchenko.service.ChannelService;
import ua.kture.ioshchenko.service.RecipeService;
import ua.kture.ioshchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RecipeRestController {
    @Autowired
    private ChannelService channelService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/rest/getTriggerChanel", method = RequestMethod.GET)
    @ResponseBody
    public List<Channel> getTriggerChanel(@RequestParam String type, Model model) {
        if (type.equals("this")) {
            return channelService.getAllServiceThis();
        } else {
            return channelService.getAllServiceThat();
        }
    }


    @RequestMapping(value = "/rest/getTrigger", method = RequestMethod.GET)
    @ResponseBody
    public List<ChannelAction> getTrigger(@RequestParam Long id, @RequestParam String type, Model model) {
        if (type.equals("this")) {
            return channelService.getListServiceActionThis(id);
        } else {
            return channelService.getListServiceActionThat(id);
        }
    }

    @RequestMapping(value = "/rest/createRecipe", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessageBean getTrigger(@RequestBody RecipeUserBean recipe, Model model, HttpServletRequest request) {
        User user = userService.get(recipe.getEmail());
        recipeService.add(recipe.getRecipeBean(), user);
        ResponseMessageBean responseMessage = new ResponseMessageBean();
        responseMessage.setStatus(ua.kture.ioshchenko.bean.ResponseStatus.SUCCESS);
        return responseMessage;
    }

    @RequestMapping(value = "/rest/getUserRecipes", method = RequestMethod.GET)
    @ResponseBody
    public List<Recipe> getUserRecipes(@RequestParam String email, Model model, HttpServletRequest request) {
        User user = userService.get(email);
        return recipeService.getUserRecipes(user);
    }
}
