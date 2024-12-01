package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.Connect;

public class Guest extends User {

	String accepted_invitations;
	private Connect connect = Connect.getInstance();
	
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

}
