package ua.kture.ioshchenko.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.kture.ioshchenko.model.User;

import java.sql.*;

@Repository
public class UserDAOImpl implements UserDAO {

	private final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?;";
	private final String INSERT_USER = "INSERT INTO users VALUES (default, ?, ?);";
	private final String UPDATE_USER = "UPDATE users SET password=? WHERE email=?";
	private final Logger logger = Logger.getLogger(UserDAOImpl.class);

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
			rollback(connection);

		} finally {
			closePreparedStatement(connection, pstmt, null);
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

			pstmt = connection.prepareStatement(FIND_USER_BY_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = unMap(rs);
			}
			
		} catch (Exception e) {
			logger.error("Not get user.", e);
			rollback(connection);
		} finally {

			closePreparedStatement(connection, pstmt, rs);

		}
		return user;
	}

	@Override
	public void update(User user) {

	}

	private User unMap(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getInt("id"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));

		return user;
	}

	private void rollback(Connection connection) {
		try {

			if (connection != null) {
				connection.rollback();
			}
		} catch (SQLException e) {
			logger.trace("Roll back error.");
		}
	}

	private void closePreparedStatement(Connection connection,
			PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.trace("Close error.");
		}
	}

	private void closeStatement(Connection connection, Statement stmt,
			ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.trace("Close error.");
		}
	}
}
