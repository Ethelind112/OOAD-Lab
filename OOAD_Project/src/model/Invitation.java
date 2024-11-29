package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Connect;

public class Invitation {
	String invitation_id, event_id, user_id, invitation_status, invitation_role;
	private Connect connect = Connect.getInstance();

	public Invitation(String invitation_id, String event_id, String user_id, String invitation_status, String invitation_role) {
		this.invitation_id = invitation_id;
		this.event_id = event_id;
		this.user_id = user_id;
		this.invitation_status = invitation_status;
		this.invitation_role = invitation_role;
	}
	
//	public ArrayList<String> getInvitations(String email){
//		String readDateQuery = String.format("SELECT user_id FROM user WHERE user_email = '%s'", email);
//		
//		String id;
//		
//		ResultSet readData = connect.execute(readDateQuery);
//		try {
//			if(readData != null && readData.next())
//				id = readData.getString("user_id");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//	}

}
