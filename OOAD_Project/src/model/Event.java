package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Connect;

public class Event {
	
	String event_id, event_name, event_date, event_location, event_description, organizer_id;
	private Connect connect = Connect.getInstance();

	public Event() {
		
	}

	public Event(String event_id, String event_name, String event_date, String event_location, String event_description, String organizer_id) {
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_date = event_date;
		this.event_location = event_location;
		this.event_description = event_description;
		this.organizer_id = organizer_id;
	}

//	mengambil data detail dari event
	public Event viewEventDetails(String eventID) {
//		mengambil semua kolum data dari database yang memiliki eventid tersebut
		String readEventQuery = "SELECT * FROM event WHERE event_id = ?";
		
		PreparedStatement ps = connect.prepareStatement(readEventQuery);
		ResultSet readData = null;
		
		try {
			ps.setString(1, eventID);
			readData = ps.executeQuery();
			
//			bila ditemukan data event, maka buat event model untuk di return
			if(readData != null && readData.next())
				return new Event(readData.getString("event_id"), readData.getString("event_name"), readData.getString("event_date"), readData.getString("event_location"), readData.getString("event_description"), readData.getString("organizer_id"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
//		bila tidak ditemukan maka return null
		return null;
	}
	
	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	public String getEvent_location() {
		return event_location;
	}

	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public String getOrganizer_id() {
		return organizer_id;
	}

	public void setOrganizer_id(String organizer_id) {
		this.organizer_id = organizer_id;
	}
	
	
	
}
