package ua.kture.ioshchenko.dao;

import ua.kture.ioshchenko.bean.RecipeBean;
import ua.kture.ioshchenko.model.Recipe;

import java.util.List;


public interface RecipeDAO {

    void add(RecipeBean recipeBean, long userId);

    void remove(RecipeBean recipeBean, long userId);


    List<Recipe> geUserRecipes(long id);
}
