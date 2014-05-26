package ua.kture.ioshchenko.dao.MySQL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.kture.ioshchenko.bean.RecipeBean;
import ua.kture.ioshchenko.dao.ConnectionUtil;
import ua.kture.ioshchenko.dao.DBManager;
import ua.kture.ioshchenko.dao.RecipeDAO;
import ua.kture.ioshchenko.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class RecipeDAOImpl implements RecipeDAO {
    private final Logger logger = Logger.getLogger(RecipeDAOImpl.class);

    @Autowired
    private DBManager manager;

    private static final String INSERT_RECIPE = "INSERT INTO recipes VALUES (default, ?, ?, ?, ?, ?);";


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


}
