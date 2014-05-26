package ua.kture.ioshchenko.dao;

import ua.kture.ioshchenko.bean.RecipeBean;


public interface RecipeDAO {

    public void add(RecipeBean recipeBean, long userId);

}
