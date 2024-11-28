package controller;

import model.User;

public class UserController {

	public UserController() {
		
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
		if(role == "") {
			return "Please Fill All The Field";
		}
		
		return "";
	}
	
	public String register(String email, String name, String password, String role) {
		if(checkEmail(email) != "") {
			return checkEmail(email);
		}
		
		if(checkPass(password) != "") {
			return checkPass(password);
		}
		
		if(checkuName(name) != "") {
			return checkuName(name);
		}
		
		if(checkRole(role) != "") {
			return checkRole(role);
		}
		
//		Masukin data ke database
		
		User user = new User();
		user.register(email, name, password, role);
		
		return "success";
	}
	
	
	public String checkEmailLogin(String email) {
		//bila email kosong, kirimkan error message
		if(email.isEmpty()) {
			return "Please Fill All The Field";
		}
		
		//Check ada email ato gak di database
		User user = new User().getUserByEmail(email);
		
		if(email != null) {
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
		
		
		return "success";
	}
	
}
