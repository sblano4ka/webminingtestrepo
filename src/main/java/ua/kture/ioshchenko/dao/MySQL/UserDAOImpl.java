package ua.kture.ioshchenko.dao.MySQL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.kture.ioshchenko.dao.ConnectionUtil;
import ua.kture.ioshchenko.dao.DBManager;
import ua.kture.ioshchenko.dao.UserDAO;
import ua.kture.ioshchenko.model.User;

import java.sql.*;

@Repository
public class UserDAOImpl implements UserDAO {

    private final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?;";
    private final String INSERT_USER = "INSERT INTO users VALUES (default, ?, ?, default, default, default);";
    private final String UPDATE_USER = "UPDATE users SET drop_box_access_token=?, "
            + "instagram_access_token=?, instagram_user_id=? WHERE email=?";


    private final String SELECT_USER_INSTAGRAM_USER_ID = "SELECT * FROM users WHERE instagram_user_id=?;";

    @Autowired
    private DBManager manager;

    @Override
    public void add(User user) {
        PreparedStatement pstmt = null;
        Connection connection = null;

        try {

            connection = manager.getConnection();

            pstmt = connection.prepareStatement(INSERT_USER);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());

            pstmt.executeUpdate();

        } catch (Exception ex) {

            logger.error("Not add user.", ex);
            ConnectionUtil.rollback(connection);

        } finally {
            ConnectionUtil.closePreparedStatement(connection, pstmt, null);
        }

    }

    @Override
    public User get(String email) {

        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection connection = null;

        try {

            connection = manager.getConnection();

            pstmt = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = unMap(rs);
            }

        } catch (Exception e) {
            logger.error("Not get user.", e);
            ConnectionUtil.rollback(connection);
        } finally {

            ConnectionUtil.closePreparedStatement(connection, pstmt, rs);

        }
        return user;
    }

    @Override
    public void update(User user) {
        PreparedStatement pstmt = null;
        Connection connection = null;

        try {

            connection = manager.getConnection();

            pstmt = connection.prepareStatement(UPDATE_USER);
            pstmt.setString(1, user.getDropBoxAccessToken());
            pstmt.setString(2, user.getInstagramAccessToken());
            pstmt.setString(3, user.getInstagramUserId());
            pstmt.setString(4, user.getEmail());

            pstmt.executeUpdate();

        } catch (Exception ex) {

            logger.error("Not update user.", ex);
            ConnectionUtil.rollback(connection);

        } finally {
            ConnectionUtil.closePreparedStatement(connection, pstmt, null);
        }

    }

    @Override
    public User getUserByInstagramUserId(String instagramUserId) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection connection = null;

        try {

            connection = manager.getConnection();

            pstmt = connection.prepareStatement(SELECT_USER_INSTAGRAM_USER_ID);
            pstmt.setString(1, instagramUserId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = unMap(rs);
            }

        } catch (Exception e) {
            logger.error("Not get user.", e);
            ConnectionUtil.rollback(connection);
        } finally {
            ConnectionUtil.closePreparedStatement(connection, pstmt, rs);
        }
        return user;
    }

    private User unMap(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setDropBoxAccessToken(rs.getString("drop_box_access_token"));
        user.setInstagramAccessToken(rs.getString("instagram_access_token"));
        user.setInstagramUserId(rs.getString("instagram_user_id"));

        return user;
    }


}
