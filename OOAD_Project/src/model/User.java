package model;

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
	
	public void register(String email, String name, String password, String role) {
		
//		generate id
		String insertQuery = String.format("INSERT INTO User (user_id, user_email, user_name, user_password, user_role) VALUES ('%s', '%s', '%s', '%s', '%s')", this.user_id, email, name, password, role);
		
		connect.executeUpdate(insertQuery);
	}
	
}
