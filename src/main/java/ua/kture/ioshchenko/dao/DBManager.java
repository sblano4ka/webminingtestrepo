package ua.kture.ioshchenko.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBManager {
    private static DBManager instance;
    private DataSource dataSource;
    private Logger logger = Logger.getLogger(DBManager.class);

    private DBManager() {
        Context context;
        try {
            context = new InitialContext();
            dataSource = (DataSource) context
                    .lookup("java:comp/env/jdbc/mysqldb");
        } catch (NamingException e) {
            logger.error("Not find context for database.", e);
        }

    }

    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    public Connection getConnection() throws SQLException,
            ClassNotFoundException {

        return dataSource.getConnection();

    }

}