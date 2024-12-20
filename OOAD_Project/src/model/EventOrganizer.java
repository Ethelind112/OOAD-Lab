package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import util.Connect;

public class EventOrganizer extends User {
    
    private Connect connect = Connect.getInstance();

    public EventOrganizer() {
        super();
    }

    public EventOrganizer(String id, String email, String name, String password, String role, Connect connect) {
		super(id, email, name, password, role);
		this.connect = connect;
	}
    
    public String createEvent(String eventName, String eventDate, String eventLocation, String eventDescription, String organizerId) {
        String readQuery = "SELECT COUNT(*) as total FROM event";
        String insertQuery = "INSERT INTO event (event_id, event_name, event_date, event_location, event_description, organizer_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps1 = connect.prepareStatement(readQuery);
            ResultSet rs = ps1.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt("total");
            }
            DecimalFormat format = new DecimalFormat("00000");
            String eventID = format.format(count + 1);

            PreparedStatement ps2 = connect.prepareStatement(insertQuery);
            ps2.setString(1, eventID);
            ps2.setString(2, eventName);
            ps2.setString(3, eventDate);
            ps2.setString(4, eventLocation);
            ps2.setString(5, eventDescription);
            ps2.setString(6, organizerId);
            System.out.println("Insert Query: " + ps2.toString()); 
            int rowsInserted = ps2.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Event added successfully: " + eventID + " by organizer: " + organizerId);
                return "Event created successfully with ID: " + eventID;
            } else {
                System.out.println("Failed to add event.");
                return "Error: Event could not be added.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to create event.";
        }
    }

    public String addVendor(String eventID, Vendor vendor) {
        String checkEventQuery = "SELECT * FROM event WHERE event_id = ?";
        String insertVendorQuery = "INSERT INTO event_vendor (event_id, vendor_id) VALUES (?, ?)";
        
        try {
            PreparedStatement ps1 = connect.prepareStatement(checkEventQuery);
            ps1.setString(1, eventID);
            ResultSet rs = ps1.executeQuery();
            
            if (rs.next()) {
                PreparedStatement ps2 = connect.prepareStatement(insertVendorQuery);
                ps2.setString(1, eventID);
                ps2.setString(2, vendor.getUser_id());
                ps2.executeUpdate();
                return "Vendor successfully added to the event.";
            } else {
                return "Error: Event not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to add vendor.";
        }
    }

    public String addGuest(String eventID, Guest guest) {
        String checkEventQuery = "SELECT * FROM event WHERE event_id = ?";
        String countInvQuery = "SELECT COUNT(*) as total FROM invitation";
        String insertInviteQuery = "INSERT INTO invitation (invitation_id, event_id, user_id, invitation_status) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement psEvent = connect.prepareStatement(checkEventQuery);
            psEvent.setString(1, eventID);
            ResultSet rsEvent = psEvent.executeQuery();

            if (!rsEvent.next()) {
                return "Error: Event not found.";
            }

            PreparedStatement psCount = connect.prepareStatement(countInvQuery);
            ResultSet rsCount = psCount.executeQuery();
            int count = 0;
            if (rsCount.next()) {
                count = rsCount.getInt("total");
            }

            DecimalFormat format = new DecimalFormat("00000");
            String invitationID = format.format(count + 1);

            PreparedStatement psInsert = connect.prepareStatement(insertInviteQuery);
            psInsert.setString(1, invitationID);
            psInsert.setString(2, eventID);
            psInsert.setString(3, guest.getUser_id());
            psInsert.setString(4, "pending");
            psInsert.executeUpdate();

            return "Guest invitation successfully created with invitation ID: " + invitationID;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to add guest.";
        }
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
        ArrayList<Event> events = new ArrayList<>();
        String query = "SELECT * FROM event WHERE organizer_id = ?";
        
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, this.getUser_id()); 
            System.out.println("Fetching events for organizer: " + this.getUser_id());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Event fetched: " + rs.getString("event_name")); // Debug
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
    
    public String viewOrganizedTransactionDetails(String eventID) {
        String eventQuery = "SELECT * FROM event WHERE event_id = ? AND organizer_id = ?";
        String transactionQuery = "SELECT * FROM transactions WHERE event_id = ?";
        StringBuilder transactionDetails = new StringBuilder();

        try {
            PreparedStatement psEvent = connect.prepareStatement(eventQuery);
            psEvent.setString(1, eventID);
            psEvent.setString(2, this.getUser_id());
            ResultSet rsEvent = psEvent.executeQuery();

            if (rsEvent.next()) {
                transactionDetails.append("Event Details: \n");
                transactionDetails.append("Event ID: ").append(rsEvent.getString("event_id")).append("\n");
                transactionDetails.append("Event Name: ").append(rsEvent.getString("event_name")).append("\n");
                transactionDetails.append("Date: ").append(rsEvent.getString("event_date")).append("\n");
                transactionDetails.append("Location: ").append(rsEvent.getString("event_location")).append("\n");
                transactionDetails.append("Description: ").append(rsEvent.getString("event_description")).append("\n\n");

                PreparedStatement psTransaction = connect.prepareStatement(transactionQuery);
                psTransaction.setString(1, eventID);
                ResultSet rsTransaction = psTransaction.executeQuery();

                transactionDetails.append("Transaction Details:\n");

                while (rsTransaction.next()) {
                    transactionDetails.append("Transaction ID: ").append(rsTransaction.getString("transaction_id")).append("\n");
                    transactionDetails.append("Amount: ").append(rsTransaction.getString("amount")).append("\n");
                    transactionDetails.append("Date: ").append(rsTransaction.getString("transaction_date")).append("\n");
                    transactionDetails.append("Vendor ID: ").append(rsTransaction.getString("vendor_id")).append("\n");
                    transactionDetails.append("---\n");
                }
                return transactionDetails.toString();
            } else {
                return "Error: Event not found or you are not the organizer.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to fetch transaction details.";
        }
    }
}
