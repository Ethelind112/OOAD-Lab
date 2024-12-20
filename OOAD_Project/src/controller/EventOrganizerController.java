package controller;

import model.Event;
import model.EventOrganizer;
import model.Guest;
import model.Vendor;
import util.Connect;
import view.ViewEvents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class EventOrganizerController {
    
    private EventOrganizer eventOrganizer;
    private Connect connect = Connect.getInstance();

    public EventOrganizerController(EventOrganizer eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public EventOrganizerController(ViewEvents view, String email) {
        this.eventOrganizer = new EventOrganizer();
        this.eventOrganizer.setUser_email(email);
    }

    public String createEvent(String eventName, String eventDate, String eventLocation, String eventDescription, String organizerId) {
        String insertQuery = "INSERT INTO event (event_id, event_name, event_date, event_location, event_description, organizer_id) " +
                             "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            String eventId = generateUniqueEventId(); // Generate a unique event ID

            // Generate a random organizer ID if it's null or empty
            if (organizerId == null || organizerId.isEmpty()) {
                organizerId = generateRandomOrganizerId();
            }

            PreparedStatement ps = connect.prepareStatement(insertQuery);
            ps.setString(1, eventId);
            ps.setString(2, eventName);
            ps.setString(3, eventDate);
            ps.setString(4, eventLocation);
            ps.setString(5, eventDescription);
            ps.setString(6, organizerId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return "Event created successfully with Organizer ID: " + organizerId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error creating event: " + e.getMessage();
        }
        return "Failed to create event.";
    }

    private String generateRandomOrganizerId() {
        Random random = new Random();
        String zero = "00";
        int randomNumber = random.nextInt(900) + 100; 
        return zero + randomNumber; 
    }

    private String generateUniqueEventId() {
        String query = "SELECT MAX(event_id) AS max_id FROM event";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String maxId = rs.getString("max_id");
                if (maxId != null) {
                    int nextId = Integer.parseInt(maxId) + 1;
                    return String.format("%05d", nextId); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "00001"; 
    }

	public String addVendor(String eventID, Vendor vendor) {
        return eventOrganizer.addVendor(eventID, vendor);
    }

    public String addGuest(String eventID, Guest guest) {
        return eventOrganizer.addGuest(eventID, guest);
    }
    
    

    public String editEventName(String eventID, String newEventName) {
        String updateQuery = "UPDATE event SET event_name = ? WHERE event_id = ?";

        try {
            PreparedStatement ps = connect.prepareStatement(updateQuery);
            ps.setString(1, newEventName);
            ps.setString(2, eventID);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return "Event name successfully updated.";
            } else {
                return "Error: Event not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to update event name.";
        }
    }

    public ArrayList<Event> viewOrganizedEvents() {
        return eventOrganizer.viewOrganizedEvents();
    }

    public String viewOrganizedTransactionDetails(String eventID) {
        return eventOrganizer.viewOrganizedTransactionDetails(eventID);
    }
    
    public ArrayList<Event> getOrganizedEvents() {
        return eventOrganizer.viewOrganizedEvents();
    }
    
}
