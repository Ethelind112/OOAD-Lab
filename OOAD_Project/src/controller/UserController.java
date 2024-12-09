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

	public User getUserByEmail(String email) {
		return user.getUserByEmail(email);
	}
	
	public User getUserByUsername(String name) {
		return user.getUserByUsername(name);
	}

	//	Mengecek inputan email user saat registrasi
	public String checkEmail(String email) {
		if(email.isEmpty()) {
			return "Please Fill All The Field";
		}
		
		return "";
	}
	
//	Mengecek inputan nama user saat registrasi
	public String checkuName(String uName) {
		//bila email kosong, keluarkan error message
		if(uName.isEmpty()) {
			return "Please Fill All The Field";
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
//		mendapatkan user dengan email dan name yang ada di parameter, digunakan untuk melakukan pengecekan keunikan
		User userName = getUserByUsername(name);
		User userEmail = getUserByEmail(email);
		
//		melakukan pengecekan inputan email, name, dan password yang harus sesuai ketentuan
		String message = checkRegisterInput(email, name, password);
		
//		melakukan pengecekan bila role telah dipilih atau belum
		if(checkRole(role) != "") {
			return checkRole(role);
		}
		
//		melakukan pengecekan bila user yang didapatkan dari email sudah terdaftar atau belum	
		if(userEmail != null) {
			return "Email Is Already Registered";
		}
		
//		melakukan pengecekan bila user yang didapatkan dari username sudah terdaftar atau belum	
		if(userName != null) {
			return "Username Is Already Used";
		}
		
//		Masukin data ke database bila tidak error
		
		if(message == "") {
			User user = new User();
			message = user.register(email, name, password, role);
			
			this.user = user;
			
//			mengirim success message
			return "success";
		}
		
//		mengirim error message	
		return message;
	}
	
	
	public String checkEmailLogin(String email) {
		//bila email kosong, kirimkan error message
		if(email.isEmpty()) {
			return "Please Fill All The Field";
		}
		
		return "success";
	}
	
	public String checkPasswordLogin(String pass) {
		//bila password kosong, kirimkan error message
		if(pass.isEmpty()) {
			return "Please Fill All The Field";
		}
		
		return "success";
	}
	
//	untuk login tidak memiliki flow dalam sequence dan activity sehingga dibuat dengan flow sendiri
	public String login(String email, String password) {
		
		if(checkEmailLogin(email).equals("Please Fill All The Field")) {
			return checkEmailLogin(email);
		}
		
		if(!checkPasswordLogin(password).equals("success")) {
			return checkPasswordLogin(password);
		}
		
		String message = new User().login(email, password);
		
		if(!message.equals("success")) {
			return message;
		}
		
		User user= getUserByEmail(email);
		
		this.user = user;
		return "success";
	}
	
	public String checkChangeProfileInput(String email, String name, String oldPassword, String newPassword) {
		User currUser = getUserByEmail(this.user.getUser_email()); 
		
		String newEmail = this.user.getUser_email();
		String newName = this.user.getUser_name();
		String newOldPassword = this.user.getUser_password();
		
		if(!email.equals("")) {
			if(email.equals(newEmail)) {
				return "The email you inputted is the same as you old email";
			}
		}
		
		if(!name.equals("")) {
			if(name.equals(newName)) {
				return "The username you inputted is the same as you old username";
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
		
		return "success";
	}
	
	public String changeProfile(String email, String name, String oldPassword, String newPassword) {
		User currUser = getUserByEmail(this.user.getUser_email()); 
		
		String newEmail = currUser.getUser_email();
		String newName = currUser.getUser_name();
		String newOldPassword = currUser.getUser_password();
		
		String tempMessage = checkChangeProfileInput(email, name, oldPassword, newPassword);
		
//		bila validasi basic tidak berhasil, maka kembalikan error message
		if(!tempMessage.equals("success")) {
			return tempMessage;
		}
		

		if(!email.equals("")) {
//			mengambil user dari email yang dimasukan dan check keunikan
			User temp = getUserByEmail(email);
			
			if(temp == null) {
				newEmail = email;
			}else {
				return "The email you entered already registered";
			}
		}
		
		
		if(!name.equals("")) {
//			mengambil user dari username yang dimasukan dan check keunikan
			User temp = getUserByUsername(name);
			
			if(temp == null) {
				newName = name;
			}else {
				return "The username you entered already used";
			}
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
