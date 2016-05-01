package me.quickScythe.eridaunicore.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class IDatabase {

	public Connection connection;
	private Properties properties;
	private String user;
	private String pass;
	private String url;

	public IDatabase(String host, String database, Integer port, String username, String password) {
		this.properties = new Properties();
		this.user = username;
		this.pass = password;
		this.properties.setProperty("user", username);
		this.properties.setProperty("password", password);
		this.url = "jdbc:mysql://" + host + ":" + port + "/" + database;
	}

	public Boolean init() {
		try {
			if (connection != null && !connection.isClosed()) {
				return true;
			}
			this.connection = DriverManager.getConnection(url, user, pass);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	public ResultSet query(String query, Object... values) {
		try {
			PreparedStatement statement = prepare(query, values);
			if (statement == null) {
				return null;
			}
			return statement.executeQuery();
		} catch (Exception exception) {
			return null;
		}
	}

	public Integer update(String query, Object... values) {
		try {
			PreparedStatement statement = prepare(query, values);
			if (statement == null) {
				return -1;
			}
			return statement.executeUpdate();
		} catch (Exception exception) {
			return -1;
		}
	}

	private PreparedStatement prepare(String query, Object... values) {
		try {
			if (!init()) {
				return null;
			}
			PreparedStatement statement = connection.prepareStatement(query);
			for (int i = 0; i < values.length; i++) {
				statement.setObject(i + 1, values[i]);
			}
			return statement;
		} catch (Exception exception) {
			return null;
		}
	}
}
