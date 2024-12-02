package controller;

import model.User;

public class UserController {

	private static User user = new User();
	
	public UserController() {
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	//	Mengecek inputan email user saat registrasi
	public String checkEmail(String email) {
		if(email.isEmpty()) {
			return "Please Fill All The Field";
		}
		
		//cek keunikan dari database
		User user = new User().getUserByEmail(email);
		
		if(user != null) {
			return "Email Is Already Registered";
		}
		
		return "";
	}
	
//	Mengecek inputan nama user saat registrasi
	public String checkuName(String uName) {
		//bila email kosong, keluarkan error message
		if(uName.isEmpty()) {
			return "Please Fill All The Field";
		}
		
		//check keunikan dari database
		User user = new User().getUserByUsername(uName);
		
		if(user != null) {
			return "Username Is Already Used";
		}
		
		return "";
	}
	
//	Mengecek inputan password user saat registrasi
	public String checkPass(String pass) {
		//bila password kosong atau panjang kurang dari 5, keluarkan error message
		if(pass.isEmpty()) {
			return "Please Fill All The Field";
		}else if(pass.length() < 5) {
			return "Password must at least 5 characters long";
		}
		
		return "";
	}
	 
//	Mengecek pilihan role user saat registrasi
	public String checkRole(String role) {
		//bila role tidak dipilih, keluarkan error message
		if(role == "" || role == null) {
			return "Please Fill All The Field";
		}
		
		return "";
	}
	
	public String checkRegisterInput(String email, String name, String password) {
		if(checkEmail(email) != "") {
			return checkEmail(email);
		}
		
		if(checkPass(password) != "") {
			return checkPass(password);
		}
		
		if(checkuName(name) != "") {
			return checkuName(name);
		}
		
		return "";
	}
	
	public String register(String email, String name, String password, String role) {
		String message = checkRegisterInput(email, name, password);
		
		if(checkRole(role) != "") {
			return checkRole(role);
		}
		
//		Masukin data ke database
		
		if(message == "") {
			User user = new User();
			user.register(email, name, password, role);
			
			this.user = user;
			
			return "success";
		}
		return message;
	}
	
	
	public String checkEmailLogin(String email) {
		//bila email kosong, kirimkan error message
		if(email.isEmpty()) {
			return "Please Fill All The Field";
		}
		
		//Check ada email ato gak di database
		User user = new User().getUserByEmail(email);
		
		if(user != null) {
			return "";
		}
		
		return "Email Not Found";
	}
	
	public String checkPasswordLogin(String pass) {
		//bila password kosong, kirimkan error message
		if(pass.isEmpty()) {
			return "Please Fill All The Field";
		}
		
		return "";
	}
	
	public String login(String email, String password) {
		if(checkEmailLogin(email) != "") {
			return checkEmailLogin(email);
		}
		
		if(checkPasswordLogin(password) != "") {
			return checkPasswordLogin(password);
		}
		
//		check ke database email sama password sama gak
		User user = new User().getUserByEmail(email);
		
		if(!user.getUser_password().equals(password)) {
			return "Password and Email Don't Match";
		}
		
		this.user = user;
		return "success";
	}
	
	public String changeProfile(String email, String name, String oldPassword, String newPassword) {
		User currUser = this.user.getUserByEmail(this.user.getUser_email()); 
		
		String newEmail = this.user.getUser_email();
		String newName = this.user.getUser_name();
		String newOldPassword = this.user.getUser_password();
		
		if(!email.equals("")) {
			if(email.equals(newEmail)) {
				return "The email you inputted is the same as you old email";
			}
			
			User temp = new User().getUserByEmail(email);
			
			if(temp == null) {
				newEmail = email;
			}else {
				return "The email you entered already registered";
			}
		}
		
		if(!name.equals("")) {
			if(name.equals(newName)) {
				return "The username you inputted is the same as you old username";
			}
			
			User temp = new User().getUserByUsername(name);
			
			if(temp == null) {
				newName = name;
			}else {
				return "The username you entered already used";
			}
		}
		
		if(!oldPassword.equals("")) {
			
			if(!oldPassword.equals(newOldPassword)) {
				return "Wrong old password";
			}else {
				if(newPassword.equals("") || newPassword == null) {
					return "Input your new password to change password or\n leave the old password field blank to update the rest profile data";
				}
				
				String message = checkPass(newPassword);
				
				if(!message.equals("")) {
					return message;
				}
				
			}
		}
		
		if(!newPassword.equals("") && oldPassword.equals("")) {
			return "Input your old password";
		}
		
		if(email.equals("") && name.equals("") && oldPassword.equals("")) {
			return "Nothing is updated";
		}
		
		
		
		String message = currUser.changeProfile(currUser.getUser_id(), newEmail, newName, newOldPassword, newPassword);
		
		if(!message.equals("fail")) {
			this.user.setUser_email(newEmail);
			this.user.setUser_name(newName);
			
			if(!newPassword.equals("")) {
				this.user.setUser_password(newPassword);
			}
		}
		
		return message;
	}

}
