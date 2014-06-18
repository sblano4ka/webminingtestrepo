package ua.kture.ioshchenko.bean;

public class RecipeUserBean {
    private RecipeBean recipeBean;
    private String email;

    public RecipeBean getRecipeBean() {
        return recipeBean;
    }

    public void setRecipeBean(RecipeBean recipeBean) {
        this.recipeBean = recipeBean;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
