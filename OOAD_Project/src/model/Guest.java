package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Connect;

public class Guest extends User {

	private String accepted_invitations;
	private Connect connect = Connect.getInstance();
	
//	process accept invitation dengan mengubah invitation status menjadi accepted
//	di class diagram acceptinvitation ada di Guest dan Vendor. Namun di sequence diagram proses ada di Invitation
	
//	Terdapat penambahan userID agar saat diupdate statusnya, hanya userid yang ada di dalam event yang terupdate
//	bila hanya eventid yang di passing seperti di class diagram, maka semua invitation yang memiliki eventid tersebut terupdate (untuk semua user yang terdaftar dalam event)
	public String acceptInvitation(String eventID, String userID) {
		String updateQuery = "UPDATE invitation SET invitation_status = 'accepted' WHERE event_id = ? AND user_id = ?";
		PreparedStatement ps = connect.prepareStatement(updateQuery);
		
		try {
			ps.setString(1, eventID);
			ps.setString(2, userID);
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
//	process mendapatkan accepted event
	public ArrayList<Event> viewAcceptedEvents(String email) {
//		Cari id dari user yang memiliki email tersebut
		String readUserQuery = "SELECT user_id FROM user WHERE user_email = ?";
		
		PreparedStatement ps = connect.prepareStatement(readUserQuery);
		ResultSet readData = null;
		
		try {
			ps.setString(1, email);
			readData = ps.executeQuery();
			
//			bila data user ditemukan maka melakukan pengambilan data accepted event
			if(readData != null && readData.next()) {
				String id = readData.getString("user_id");
				
//				Cari invitation dari user_id diatas yang sudah accepted
				String readInvitationQuery = "SELECT event_id FROM invitation WHERE user_id = ? AND invitation_status = 'accepted'";
				
				PreparedStatement ps1 = connect.prepareStatement(readInvitationQuery);
				
				ps1.setString(1, id);
				ResultSet readInvitationData = ps1.executeQuery();
				
//				untuk menampung eventid yang terkumpul
				ArrayList<String> eventId = new ArrayList<>();
				
//				memasukan event id ke arraylist bila ditemukan eventid yang accepted
				while(readInvitationData.next()) {
					eventId.add(readInvitationData.getString("event_id"));
				}
				
//				Cari event dari event_id yang ditemukan dalam invitation sesuai user_id
				ArrayList<Event> invitedEvent = new ArrayList<>();
				for(int i = 0; i < eventId.size(); i++) {
					String readEventQuery = "SELECT * FROM event WHERE event_id = ?";
					
					PreparedStatement ps2 = connect.prepareStatement(readEventQuery);
					
					ps2.setString(1, eventId.get(i));
					ResultSet readEventData = ps2.executeQuery();

					while(readEventData.next()) {
						invitedEvent.add(new Event(readEventData.getString("event_id"), readEventData.getString("event_name"), readEventData.getString("event_date"), readEventData.getString("event_location"), readEventData.getString("event_description"), readEventData.getString("organizer_id")));
					}
					
				}
				
				return invitedEvent;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		bila tidak menemukan data yang diperlukan maka return null
		return null;
	}

	public ArrayList<User> getGuestByTransactionId(String event_id) {
		ArrayList<User> users = new ArrayList<>();
		String readDateQuery = "SELECT g.* FROM user g JOIN transactions t ON g.user_id = t.userID WHERE t.event_id = ? AND user_role = ?";
		
		PreparedStatement ps = connect.prepareStatement(readDateQuery);
		ResultSet readData = null;
		
		try {
			ps.setString(1, event_id);
			ps.setString(2, "Guest");
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
