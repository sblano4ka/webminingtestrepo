package ua.kture.ioshchenko.dao.MySQL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.kture.ioshchenko.bean.RecipeBean;
import ua.kture.ioshchenko.dao.ChannelDAO;
import ua.kture.ioshchenko.dao.ConnectionUtil;
import ua.kture.ioshchenko.dao.DBManager;
import ua.kture.ioshchenko.dao.RecipeDAO;
import ua.kture.ioshchenko.model.ChannelAction;
import ua.kture.ioshchenko.model.Recipe;
import ua.kture.ioshchenko.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeDAOImpl implements RecipeDAO {
    private final Logger logger = Logger.getLogger(RecipeDAOImpl.class);

    @Autowired
    private DBManager manager;

    @Autowired
    private ChannelDAO channelDAO;

    private static final String INSERT_RECIPE = "INSERT INTO recipes VALUES (default, ?, ?, ?, ?, ?);";
    private static final String SELECT_USER_RECIPES = "SELECT * FROM recipes WHERE user_id=?;";


    @Override
    public void add(RecipeBean recipeBean, long userId) {
        PreparedStatement pstmt = null;
        Connection connection = null;

        try {
            connection = manager.getConnection();
            pstmt = connection.prepareStatement(INSERT_RECIPE);
            pstmt.setLong(1, recipeBean.getChannelThatId());
            pstmt.setLong(2, recipeBean.getChannelThisId());
            pstmt.setLong(3, recipeBean.getChannelActionThatId());
            pstmt.setLong(4, recipeBean.getChannelActionThisId());
            pstmt.setLong(5, userId);

            pstmt.executeUpdate();

        } catch (Exception ex) {
            logger.error("Not add recipe.", ex);
            ConnectionUtil.rollback(connection);
        } finally {
            ConnectionUtil.closePreparedStatement(connection, pstmt, null);
        }
    }

    @Override
    public void remove(RecipeBean recipeBean, long userId) {

    }

    @Override
    public List<Recipe> geUserRecipes(long id) {
        List<Recipe> list = new ArrayList<Recipe>();

        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = manager.getConnection();
            pstmt = connection.prepareStatement(SELECT_USER_RECIPES);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            List<RecipeBean>    recipeBeanList = new ArrayList<RecipeBean>();

            while (rs.next()) {
                RecipeBean recipeBean = extractRecipeBean(rs);
                recipeBeanList.add(recipeBean);
            }

            for(RecipeBean recipeBean:recipeBeanList){
                list.add(extractRecipe(recipeBean));
            }

        } catch (Exception e) {
            logger.error("Get all user recipes", e);
            ConnectionUtil.rollback(connection);
        } finally {
            ConnectionUtil.closePreparedStatement(connection, pstmt, rs);
        }

        return list;
    }


    private Recipe extractRecipe(RecipeBean recipeBean) {
        Recipe recipe = new Recipe();

        recipe.setId(recipeBean.getId());
        recipe.setChannelThat(channelDAO.getChanel(recipeBean.getChannelThatId()));
        recipe.setChannelThis(channelDAO.getChanel(recipeBean.getChannelThisId()));
        recipe.setChannelActionThat(channelDAO.getChanelActionThat(recipeBean.getChannelActionThatId()));
        recipe.setChannelActionThis(channelDAO.getChanelActionThis(recipeBean.getChannelActionThisId()));
        return recipe;
    }


    private RecipeBean extractRecipeBean(ResultSet rs) throws SQLException {
        RecipeBean recipeBean = new RecipeBean();
        recipeBean.setId(rs.getLong("id"));
        recipeBean.setChannelActionThatId(rs.getLong("channel_action_that_id"));
        recipeBean.setChannelActionThisId(rs.getLong("channel_action_this_id"));
        recipeBean.setChannelThatId(rs.getLong("channel_that_id"));
        recipeBean.setChannelThisId(rs.getLong("channel_this_id"));
        return recipeBean;
    }


}
