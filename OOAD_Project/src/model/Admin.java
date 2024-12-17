package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.AdminController;
import util.Connect;

public class Admin extends User{
	
	private Connect connect = Connect.getInstance();

	public Admin(String id, String email, String name, String password, String role) {
		super(id, email, name, password, role);
		// TODO Auto-generated constructor stub
	}
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public void deleteEvent(String eventID) {
		String query = "DELETE FROM event WHERE event_id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, eventID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String userID) {
		String query = "DELETE FROM user WHERE user_id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Event> viewEvents(){
		ArrayList<Event> events = new ArrayList<>();
		for(int i = 0; i < events.size(); i++) {
			String query = "SELECT * FROM event";
			
			PreparedStatement ps = connect.prepareStatement(query);
			ResultSet readData = null;
			
			try {
				readData = ps.executeQuery();
				
				while(readData != null && readData.next())
					events.add(new Event(readData.getString("event_id"), readData.getString("event_name"), readData.getString("event_date"), readData.getString("event_location"), readData.getString("event_description"), readData.getString("organizer_id")));
				
				return events;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<User> viewUser(){
		ArrayList<User> users = new ArrayList<>();
		for(int i = 0; i < users.size(); i++) {
			String query = "SELECT * FROM user";
			
			PreparedStatement ps = connect.prepareStatement(query);
			ResultSet readData = null;
			
			try {
				readData = ps.executeQuery();
				
				while(readData != null && readData.next())
					users.add(new User(readData.getString("user_id"), readData.getString("user_email"), readData.getString("user_name"), readData.getString("user_password"), readData.getString("user_role")));
				
				return users;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

}
