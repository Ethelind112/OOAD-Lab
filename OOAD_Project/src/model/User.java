package model;

import java.sql.PreparedStatement;
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
	
//	Dalam flow sequence diagram yang diberikan pada soal, proses register di userController telah getUsername dan getEmail dari User model, 
//	sehingga pengecekan keunikan kedua attribute dilakukan pada controller (dapat juga dilihat pada gambar)
//	Namun karena pada class diagram ada function untuk checkRegisterInput di User model, maka dilakukan pengecekan kembali
	public String checkRegisterInput(String email, String name, String password) {

//		mendapatkan user dengan email dan name yang ada di parameter, digunakan untuk melakukan pengecekan keunikan
		User userName = getUserByUsername(name);
		User userEmail = getUserByEmail(email);
		
//		melakukan pengecekan bila user yang didapatkan dari email sudah terdaftar atau belum	
		if(userEmail != null) {
			return "Email Is Already Registered";
		}
		
//		melakukan pengecekan bila user yang didapatkan dari username sudah terdaftar atau belum	
		if(userName != null) {
			return "Username Is Already Used";
		}
		
//		melakukan pengecekan password
		if(password.isEmpty()) {
			return "Please Fill All The Field";
		}else if(password.length() < 5) {
			return "Password must at least 5 characters long";
		}
		
//		mengembalikan success message bila berhasil
		return "success";
	}
	
//	melakukan proses registrasi
	public String register(String email, String name, String password, String role) {
		
		String message = checkRegisterInput(email, name, password);
		
		if(!message.equals("success")) {
			return message;
		}
		
//		mengambil semua user dari database
		String readDateQuery = "SELECT * FROM user";
		ResultSet readData = connect.execute(readDateQuery);
		
//		Generate ID
		int count = 0;
		
		try {
//			menghitung jumlah user yang sudah ada
			while(readData.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DecimalFormat formats = new DecimalFormat("00000");
		String id = formats.format(count + 1);
		
//		memasukan data ke database
		String insertQuery = "INSERT INTO User (user_id, user_email, user_name, user_password, user_role) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.prepareStatement(insertQuery);
		
		try {
			ps.setString(1, id);
			ps.setString(2, email);
			ps.setString(3, name);
			ps.setString(4, password);
			ps.setString(5, role);
			ps.executeUpdate();
		} catch (SQLException e) {
//			mengirim error message bila gagal
			return "fail";
		}
		
//		mengirimkan success message bila berhasil memasukan data ke database
		return "success";
	}
	
	public String login(String email, String password) {
		//Check ada email ato gak di database
		User user = getUserByEmail(email);
				
		if(user == null) {
			return "Email Not Found";
		}
		
//		check ke database email sama password sama gak
		if(!user.getUser_password().equals(password)) {
			return "Password and Email Don't Match";
		}
		
		return "success";
	}
	
//	mengecek bila input dari change profile dapat berhasil dimasukan ke database
	public String checkChangeProfileInput(String id, String email, String name, String oldPassword, String newPassword) {
		String query = "UPDATE user SET user_email = ?, user_name = ?, user_password = ? WHERE user_id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, email);
			ps.setString(2, name);
			
			if(newPassword.equals("") || newPassword == null) {
				ps.setString(3, oldPassword);
			}else {
				ps.setString(3, newPassword);
			}
			
			ps.setString(4, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			return "fail";
		}
		
		return "success";
	}
	
//	untuk change profile disini ada tambahan parameter id (tidak mengikuti class diagram) karena email dan username (atribut yang unik) dapat diganti (email dan username baru)
//	dan untuk bisa melakukan update memerlukan suatu identitas dari user yang membedakan dari user lain. 
//	Bila email dan name sama sama diganti menjadi yang baru dan tidak ada ID, maka query tidak akan menemukan current user yang akan diupdate
	public String changeProfile(String id, String email, String name, String oldPassword, String newPassword) {
		return checkChangeProfileInput(id, email, name, oldPassword, newPassword);
	}
	
	public User getUserByEmail(String email) {
		String readDateQuery = "SELECT * FROM user WHERE user_email = ?";
		
		PreparedStatement ps = connect.prepareStatement(readDateQuery);
		ResultSet readData = null;
		
		try {
			ps.setString(1, email);
			readData = ps.executeQuery();
			
			if(readData != null && readData.next())
				return new User(readData.getString("user_id"), readData.getString("user_email"), readData.getString("user_name"), readData.getString("user_password"), readData.getString("user_role"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	
	public User getUserByUsername(String name) {
		String readDateQuery = "SELECT * FROM user WHERE user_name = ?";
		
		PreparedStatement ps = connect.prepareStatement(readDateQuery);
		ResultSet readData = null;
		
		try {
			ps.setString(1, name);
			readData = ps.executeQuery();
			
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
