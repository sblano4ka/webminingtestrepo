package ua.kture.ioshchenko.dao;

import org.apache.log4j.Logger;

import java.sql.*;

public class ConnectionUtil {
    private static final Logger logger = Logger.getLogger(ConnectionUtil.class);

    public static void rollback(Connection connection) {
        try {

            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.trace("Roll back error.");
        }
    }

    public static void closePreparedStatement(Connection connection,
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

    public static void closeStatement(Connection connection, Statement stmt,
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
