package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public String deleteEvent(String eventID) {
		String query = "DELETE FROM event WHERE event_id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, eventID);
			ps.executeUpdate();
			return "Event succesfully deleted!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Cannot delete event!";
		}
	}
	
	public String deleteUser(String userID) {
		String query = "DELETE FROM user WHERE user_id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, userID);
			ps.executeUpdate();
			return "User succesfully deleted!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "Cannot delete user!";
		}
	}
	
	public ArrayList<Event> viewAllEvents(){
		return getAllEvents();
	}
	
	public ArrayList<Event> getAllEvents(){
		ArrayList<Event> events = new ArrayList<>();
			String query = "SELECT * FROM event";
			
			try (PreparedStatement ps = connect.prepareStatement(query);
			         ResultSet readEventData = ps.executeQuery()) {
			        
			        // Loop untuk memasukkan semua data event ke dalam list
			        while (readEventData.next()) {
			            events.add(new Event(
			                readEventData.getString("event_id"), 
			                readEventData.getString("event_name"), 
			                readEventData.getString("event_date"), 
			                readEventData.getString("event_location"), 
			                readEventData.getString("event_description"), 
			                readEventData.getString("organizer_id")
			            ));
			        }
			        
			        return events;
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			    
			    // Jika tidak ada event atau terjadi error, kembalikan null
			    return null;
	}
	
	public ArrayList<User> getAllUsers(){
		ArrayList<User> users = new ArrayList<>();
		String query = "SELECT * FROM user";
		
		try (PreparedStatement ps = connect.prepareStatement(query);
		         ResultSet readEventData = ps.executeQuery()) {
		        
		        // Loop untuk memasukkan semua data event ke dalam list
		        while (readEventData.next()) {
		            users.add(new User(
		                readEventData.getString("user_id"), 
		                readEventData.getString("user_email"), 
		                readEventData.getString("user_name"), 
		                readEventData.getString("user_password"), 
		                readEventData.getString("user_role")
		            ));
		        }
		        
		        return users;
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    // Jika tidak ada event atau terjadi error, kembalikan null
		    return null;
	}
	
	public ArrayList<User> getGuestByTransactionId(String event_id) {
		ArrayList<User> users = new ArrayList<>();
		String readDateQuery = "SELECT g.* FROM user g JOIN transactions t ON g.user_id = t.guest_id WHERE t.event_id = ?";
		
		PreparedStatement ps = connect.prepareStatement(readDateQuery);
		ResultSet readData = null;
		
		try {
			ps.setString(1, event_id);
			readData = ps.executeQuery();
			
//			bila ditemukan, buat user model baru untuk di return
			while (readData.next()) {
	            users.add(new User(
	            		readData.getString("user_id"), 
	            		readData.getString("user_email"), 
	            		readData.getString("user_name"), 
	            		readData.getString("user_password"), 
	            		readData.getString("user_role")
	            ));
	        }
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		bila tidak ditemukan maka return null
		return null;
	}
	
	public ArrayList<User> getVendorByTransactionId(String event_id) {
		ArrayList<User> users = new ArrayList<>();
		String readDateQuery = "SELECT DISTINCT g.* FROM user g JOIN transactions t ON g.user_id = t.vendor_id WHERE t.event_id = ?";
		
		PreparedStatement ps = connect.prepareStatement(readDateQuery);
		ResultSet readData = null;
		
		try {
			ps.setString(1, event_id);
			readData = ps.executeQuery();
			
//			bila ditemukan, buat user model baru untuk di return
			while (readData.next()) {
	            users.add(new User(
	            		readData.getString("user_id"), 
	            		readData.getString("user_email"), 
	            		readData.getString("user_name"), 
	            		readData.getString("user_password"), 
	            		readData.getString("user_role")
	            ));
	        }
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		bila tidak ditemukan maka return null
		return null;
	}

}
