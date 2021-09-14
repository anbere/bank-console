package com.andres.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {

		Connection con = null;

		Driver postgresDriver = new Driver();

		DriverManager.registerDriver(postgresDriver);

		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema";
		String username = "postgres";
		String password = "piagNetO";
		
		con = DriverManager.getConnection(url, username, password);
		
		return con;
	}
}
