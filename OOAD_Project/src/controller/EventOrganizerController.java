package controller;

import model.Event;
import model.EventOrganizer;
import model.Guest;
import model.User;
import model.Vendor;
import util.Connect;
import view.Main;
import view.ViewCreateEvent;
import view.ViewEditEvent;
import view.ViewEvents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EventOrganizerController {
    
    private EventOrganizer eventOrganizer;
    private Connect connect = Connect.getInstance();
    private ViewEvents viewEvent;
    private ViewCreateEvent viewCreateEvent;
    private ViewEditEvent viewEditEvent;

    public EventOrganizerController(EventOrganizer eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public EventOrganizerController(ViewEvents view, String email) {
        this.eventOrganizer = new EventOrganizer();
        this.eventOrganizer.setUser_id(new User().getUserByEmail(email).getUser_id());
        this.eventOrganizer.setUser_email(email);
        this.viewEvent = view;
        viewCreateEvent = new ViewCreateEvent();
        viewEditEvent = new ViewEditEvent();
        
        view.setEventOrganizerMenu();
        view.setupCreateEventButton();
        loadEventList();
        
        view.setCreateEventButton(new EventHandler<ActionEvent>() {
//        	ViewCreateEvent viewCreateEvent = new ViewCreateEvent();

			@Override
			public void handle(ActionEvent event) {
	            Stage stage = new Stage();
	            loadCreateEventList();
	            viewCreateEvent.setCreateButton(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						String eventName = viewCreateEvent.getEventName();
			            String eventDate = viewCreateEvent.getEventDate();
			            String eventLocation = viewCreateEvent.getEventLocation();
			            String eventDescription = viewCreateEvent.getEventDescription();
		
			            if (eventName.isEmpty() || eventDate.isEmpty() || eventLocation.isEmpty() || eventDescription.isEmpty()) {
			                viewCreateEvent.setErrorMessage("All fields must be filled!");
			                return;
			            }
			            String result = createEvent(eventName, eventDate, eventLocation, eventDescription, eventOrganizer.getUser_id());
		
			            if (result.startsWith("Event created")) {
			                loadEventList();
			                stage.close();
			            } else {
			                viewCreateEvent.setErrorMessage(result);
			            }
						
					}
				});
	            
	            stage.setScene(viewCreateEvent.getScene());
		        stage.show();
			}
		});
        
        view.setEditEventButton(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Stage stage = new Stage();
	            loadEditEventList();
	            
	            viewEditEvent.setEventDetailButton(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						Event selectedEvent = viewEditEvent.getEventTable().getSelectionModel().getSelectedItem();
						viewEditEvent.setEventName(selectedEvent.getEvent_name());
					}
	            	
				});
	            
	            viewEditEvent.setCreateButton(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						System.out.println("its in");
						if(!checkEditInput(viewEditEvent.getName()).equals("success")) {
							viewEditEvent.setErrorMessage(checkEditInput(viewEditEvent.getName()));
						}else {
							System.out.println("2");
							Event selectedEvent = viewEditEvent.getEventTable().getSelectionModel().getSelectedItem();
							String message = editEvent(viewEditEvent.getName(), selectedEvent.getEvent_id());
							loadEventList();
							loadEditEventList();
							stage.close();
						}
					}
				});
	            
	            stage.setScene(viewEditEvent.getScene());
		        stage.show();
			}
		});
        
        view.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toChangeProfilePage(email);
			}
		});
    }
    
    public String editEvent(String name, String eventID) {
    	return eventOrganizer.editEvent(name, eventID);
    }
    
    public String checkEditInput(String name) {
    	if(name.length() == 0) {
    		return "Please fill in the fields";
    	}
    	
    	return "success";
    }
    
    public void loadEditEventList() {
    	Event eventModel = new Event();
		ArrayList<Event> events = eventModel.fetchEvents(eventOrganizer.getUser_id()); 
		
		ObservableList<Event> eventData = FXCollections.observableArrayList(events);
		
		if (events != null && !events.isEmpty()) {
	        ObservableList<Event> eventList = FXCollections.observableArrayList(events);
	        eventData.setAll(eventList);
	        viewEditEvent.getEventTable().setItems(eventData);
	    } else {
	    	viewEditEvent.setErrorMessage("No events found or failed to load data.");
	    }
		
		viewEditEvent.setEventList(eventData);
    }
    
    public void loadCreateEventList() {
		Event eventModel = new Event();
		ArrayList<Event> events = eventModel.fetchEvents(eventOrganizer.getUser_id()); 
		
		ObservableList<Event> eventData = FXCollections.observableArrayList(events);
		
		if (events != null && !events.isEmpty()) {
	        ObservableList<Event> eventList = FXCollections.observableArrayList(events);
	        eventData.setAll(eventList);
	        viewCreateEvent.getEventTable().setItems(eventData);
	    } else {
	    	viewCreateEvent.setErrorMessage("No events found or failed to load data.");
	    }
		
		viewCreateEvent.setEventList(eventData);
	}

	public void loadEventList() {
		Event eventModel = new Event();
		ArrayList<Event> events = eventModel.fetchEvents(eventOrganizer.getUser_id()); 
		
		ObservableList<Event> eventData = FXCollections.observableArrayList(events);
		
		if (events != null && !events.isEmpty()) {
	        ObservableList<Event> eventList = FXCollections.observableArrayList(events);
	        eventData.setAll(eventList);
	        viewEvent.getEventTable().setItems(eventData);
	    } else {
	        viewEvent.setErrorMessage("No events found or failed to load data.");
	    }
		
		viewEvent.setEventList(eventData);
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
