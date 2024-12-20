package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Connect;

public class Vendor extends User {
	private String accepted_invitations;
	private Connect connect = Connect.getInstance();
	
	//ngatur logika adding product (name dan description) vendor
	public String manageVendor() {
		return "success";
	}
	
	public String acceptInvitation(String eventID) {
		String updateQuery = "UPDATE invitation SET invitation_status = 'accepted' WHERE event_id = ?";
		PreparedStatement ps = connect.prepareStatement(updateQuery);
		
		try {
			ps.setString(1, eventID);
			ps.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	
	public ArrayList<Event> viewAcceptedEvents(String email) {
//		Cari id dari user yang memiliki email tersebut
		String readUserQuery = "SELECT user_id FROM user WHERE user_email = ?";
		
		PreparedStatement ps = connect.prepareStatement(readUserQuery);
		ResultSet readData = null;
		
		try {
			ps.setString(1, email);
			readData = ps.executeQuery();
			
			if(readData != null && readData.next()) {
				String id = readData.getString("user_id");
				
//				Cari invitation dari user_id diatas
				String readInvitationQuery = "SELECT event_id FROM invitation WHERE user_id = ? AND invitation_status = 'accepted'";
				
				PreparedStatement ps1 = connect.prepareStatement(readInvitationQuery);
				
				ps1.setString(1, id);
				ResultSet readInvitationData = ps1.executeQuery();
				
				ArrayList<String> eventId = new ArrayList<>();
				
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
		return null;
	}
	
	public ArrayList<User> getVendorByTransactionId(String event_id) {
		ArrayList<User> users = new ArrayList<>();
		String readDateQuery = "SELECT g.* FROM user g JOIN transactions t ON g.user_id = t.vendor_id WHERE t.event_id = ?";
		
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
	
	public String checkManageVendorInput(String desc, String name) {
		if(name.isEmpty()) {
			return "Please input product's name";
		}
		else {
//			mendapatkan products dengan email dan name yang ada di parameter, digunakan untuk melakukan pengecekan keunikan
			Products product = new Products().getProductsByName(name);
		
//			melakukan pengecekan bila user yang didapatkan dari username sudah terdaftar atau belum	
			if(product != null) {
				//Memperbolehkan penambahan barang dengan nama sama tapi beda description
				if(product.getProducts_name().equalsIgnoreCase(name) && product.getProducts_description().equalsIgnoreCase(desc)) {
					return "There exist such products already";
				}
			}
			
//			melakukan pengecekan description
			if(desc.isEmpty()) {
				return "Please Fill All The Field";
			}else if(desc.length() > 200) {
				return "Description are at most 200 characters long";
			}
		}
//		mengembalikan success message bila berhasil
		return "success";
	}

}

