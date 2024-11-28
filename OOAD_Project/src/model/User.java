package model;

public class User {

	private String user_id, user_email, user_name, user_password, user_role;
	
	public User(String email, String name, String password, String role) {
		this.user_email = email;
		this.user_name = name;
		this.user_password = password;
		this.user_role = role;
	}

}
