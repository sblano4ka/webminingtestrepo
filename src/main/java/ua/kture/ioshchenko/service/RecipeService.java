package ua.kture.ioshchenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kture.ioshchenko.bean.RecipeBean;
import ua.kture.ioshchenko.dao.RecipeDAO;
import ua.kture.ioshchenko.model.Recipe;
import ua.kture.ioshchenko.model.User;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeDAO recipeDAO;


    public void add(RecipeBean recipe, User user) {
        recipeDAO.add(recipe, user.getId());
    }

    public List<Recipe> getUserRecipes(User user) {
        return recipeDAO.geUserRecipes(user.getId());
    }
}
