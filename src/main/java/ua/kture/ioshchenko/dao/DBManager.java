package ua.kture.ioshchenko.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DBManager {
	private static DBManager instance;

	@Autowired
	private DataSource dataSource;
	private Logger logger = Logger.getLogger(DBManager.class);

	public Connection getConnection() throws SQLException,
			ClassNotFoundException {

		return dataSource.getConnection();

	}

}