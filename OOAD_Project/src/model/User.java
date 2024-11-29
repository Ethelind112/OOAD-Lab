package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import model.User;
import util.Connect;

public class User {

	private String user_id, user_email, user_name, user_password, user_role;
	private Connect connect = Connect.getInstance();
	
	public User(String id, String email, String name, String password, String role) {
		this.user_id = id;
		this.user_email = email;
		this.user_name = name;
		this.user_password = password;
		this.user_role = role;
	}
	
	public User() {
	}
	
	public void register(String email, String name, String password, String role) {
		
		String readDateQuery = "SELECT * FROM user";
		
		ResultSet readData = connect.execute(readDateQuery);
		
		int count = 0;
		
		try {
			while(readData.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DecimalFormat formats = new DecimalFormat("00000");
		
		String id = formats.format(count + 1);
		
//		generate id
		String insertQuery = String.format("INSERT INTO User (user_id, user_email, user_name, user_password, user_role) VALUES ('%s', '%s', '%s', '%s', '%s')", id, email, name, password, role);
		
		connect.executeUpdate(insertQuery);
	}
	
	public User getUserByEmail(String email) {
		String readDateQuery = String.format("SELECT * FROM user WHERE user_email = '%s'", email);
		
		ResultSet readData = connect.execute(readDateQuery);
		
		try {
			if(readData != null && readData.next())
				return new User(readData.getString("user_id"), readData.getString("user_email"), readData.getString("user_name"), readData.getString("user_password"), readData.getString("user_role"));
		} catch (SQLException e) {
			return null;
		}
		
		return null;
	}
	
	public User getUserByUsername(String name) {
		String readDateQuery = String.format("SELECT * FROM user WHERE user_name = '%s'", name);
		
		ResultSet readData = connect.execute(readDateQuery);
		
		try {
			if(readData != null && readData.next())
				return new User(readData.getString("user_id"), readData.getString("user_email"), readData.getString("user_name"), readData.getString("user_password"), readData.getString("user_role"));
		} catch (SQLException e) {
			return null;
		}
		return null;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

}
