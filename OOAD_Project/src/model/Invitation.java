package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import util.Connect;

public class Invitation {

	private String invitation_id, event_id, invitation_status, invitation_role;
	private Connect connect = Connect.getInstance();
	
	public Invitation() {
		
	}

//	process accept invitation dengan mengubah invitation status menjadi accepted
//	di class diagram acceptinvitation ada Guest. Namun di sequence diagram proses ada di Invitation
//	Terdapat penambahan userID agar saat diupdate statusnya, hanya userid yang ada di dalam event yang terupdate
//	bila hanya eventid yang di passing seperti di class diagram, maka semua invitation yang memiliki eventid tersebut terupdate (untuk semua user yang terdaftar dalam event)
	public String acceptInvitation(String eventID, String userID) {
		String updateQuery = "UPDATE invitation SET invitation_status = 'accepted' WHERE event_id = ? AND user_id = ?";
		PreparedStatement ps = connect.prepareStatement(updateQuery);
		
		try {
			ps.setString(1, eventID);
			ps.setString(2, userID);
			ps.executeUpdate();
			
			insertTransaction(eventID, userID);
			
			return "success";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	public void insertTransaction(String eventID, String userID) {
		
//		bila berhasil
//		mengambil user paling akhir dari database
		String readDateQuery = "SELECT * FROM transactions ORDER BY transaction_id DESC LIMIT 1";
		ResultSet readData = connect.execute(readDateQuery);
		
//		generate ID
		int currID = 0;
		
		try {
			if(readData.next()) {
				currID = Integer.parseInt(readData.getString("user_id"));
			}
		} catch (NumberFormatException e1) {
			return;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			return;
		}
		
		
//		membuat format ID
		DecimalFormat formats = new DecimalFormat("00000");
		String id = formats.format(currID + 1);
		
		String insertQuery = "INSERT INTO transactions (transaction_id, amount, transaction_date, event_id, userID) " +
                "VALUES (?, ?, ?, ?, ?)";
		
		 LocalDate currentDate = LocalDate.now();  
		
		PreparedStatement ps = connect.prepareStatement(insertQuery);
		
		try {
			ps.setString(1, id);
			ps.setString(2, "20000");
			ps.setString(3, currentDate.toString());
			ps.setString(4, eventID);
			ps.setString(5, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Event> getInvitations(String email) {
//		Cari id dari user yang memiliki email tersebut
		String readUserQuery = "SELECT user_id FROM user WHERE user_email = ?";
		
		PreparedStatement ps = connect.prepareStatement(readUserQuery);
		ResultSet readData = null;
		
		try {
			ps.setString(1, email);
			readData = ps.executeQuery();
			
//			bila user ditemukan maka mengambil data invitation event
			if(readData != null && readData.next()) {
				String id = readData.getString("user_id");
				
//				Cari invitation dari user_id diatas
				String readInvitationQuery = "SELECT event_id FROM invitation WHERE user_id = ? AND invitation_status = 'pending'";
				
				PreparedStatement ps1 = connect.prepareStatement(readInvitationQuery);
				
				ps1.setString(1, id);
				ResultSet readInvitationData = ps1.executeQuery();
				
//				untuk menampung eventid yang terkumpul
				ArrayList<String> eventId = new ArrayList<>();
				
//				memasukan event id ke arraylist bila ditemukan eventid yang belum di accept
				while(readInvitationData.next()) {
					System.out.println(readInvitationData.getString("event_id"));
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

}
