package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Connect;

public class Event {
	
	private String event_id, event_name, event_date, event_location, event_description, organizer_id;
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
	
	public ArrayList<Event> viewAllEvents(){
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

    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();
        String query = "SELECT * FROM event";

        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                events.add(new Event(
                        rs.getString("event_id"),
                        rs.getString("event_name"),
                        rs.getString("event_date"),
                        rs.getString("event_location"),
                        rs.getString("event_description"),
                        rs.getString("organizer_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public ArrayList<Event> fetchEvents(String organizerId) {
        ArrayList<Event> events = new ArrayList<>();
        String query = organizerId == null 
            ? "SELECT * FROM event" // Fetch all events if no organizerId is specified
            : "SELECT * FROM event WHERE organizer_id = ?";

        try {
            PreparedStatement ps = connect.prepareStatement(query);
            if (organizerId != null) {
                ps.setString(1, organizerId);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                events.add(new Event(
                    rs.getString("event_id"),
                    rs.getString("event_name"),
                    rs.getString("event_date"),
                    rs.getString("event_location"),
                    rs.getString("event_description"),
                    rs.getString("organizer_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

}
