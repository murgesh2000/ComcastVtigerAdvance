package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {

	Connection conn;

	public void getDbConnection() {
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:33307/vtigercrm540", "root", "root");
		} catch (SQLException e) {	
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public void getDbConnection(String URL, String USN, String PWD) {
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn = DriverManager.getConnection(URL, USN, PWD);
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}

	}

	public ResultSet executeSelectQuery(String query) {
		ResultSet resultSet = null;
		try {
			Statement stat = conn.createStatement();
			resultSet = stat.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultSet;
	}

	public int exceuteNonSelectQuery(String query) {
		int result = 0;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return result;
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}
