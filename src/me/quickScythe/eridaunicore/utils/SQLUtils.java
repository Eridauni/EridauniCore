package me.quickScythe.eridaunicore.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

public class SQLUtils {

	static Connection con;
	static String user;
	static String pass;
	static String host;
	static String address;
	static String database;

	public static Connection getConnection() {
		user = "u720689672_admin";
		pass = "p9139804385";
		host = "mysql.hostinger.co.uk";
		database = "	u720689672_stats";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found !!");
			return null;
		}
		System.out.println("MySQL JDBC Driver Registered!");
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, pass);
			System.out.println("SQL Connection to database established!");
			return con;
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			return null;
		}
	}

	public static ResultSet sendQuery(String command) {
		if(con == null) return null;
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(command);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
//			runCommand("INSERT INTO Money (Player, Balance) VALUES (?,?)", new String[] {"" + Bukkit.getPlayer("QuickScythe").getUniqueId(), "100"});
		}
		return null;
	}

	public static void runCommand(String cmd, String[] values) {
		try {
			PreparedStatement stmt = con.prepareStatement(cmd);
			for (int i = 0; i != values.length - 1;) {
				stmt.setString(i, values[i]);
				i = i - 1;
			}
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void updateTokens(Player player, int amount){
		try{
			Statement st = con.createStatement();
			st.executeUpdate("UPDATE Money SET Balance=" + amount + " WHERE Player='" + player.getUniqueId() + "'");
		} catch(Exception ex){
			try {
				PreparedStatement pre = con.prepareStatement("INSERT INTO Money(Player, Balance) VALUES (?,?)");
				pre.setString(1, player.getUniqueId() + "");
				pre.setString(2, amount + "");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	public static void closeConnection() {
		try {
			if ((con != null) && (!con.isClosed())) {
				System.out.println("Connection closed.");
				con.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
