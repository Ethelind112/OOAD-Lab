package controller;

import model.Event;
import model.EventOrganizer;
import model.Guest;
import model.Vendor;
import view.ViewEvents;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventOrganizerController {
    
    private EventOrganizer eventOrganizer;
    private String email;
    private ViewEvents viewEvent;
    
//	handle acceptedinvitationview untuk guest
	public EventOrganizerController(ViewEvents viewEvent, String email) {
		this.viewEvent = viewEvent;
		this.email = email;
		
//		logic buat load data di tabel
		
		viewEvent.setEventOrganizerMenu();
		viewEvent.eventOrganizerButton();
		
//		set button semua menu
		
//		set logic create event
		viewEvent.setCreateEventButton(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Create Button Clicked");
				
			}
		});
		
	}

    public EventOrganizerController(EventOrganizer eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public String createEvent(String eventName, String eventDate, String eventLocation, String eventDescription) {
        return eventOrganizer.createEvent(eventName, eventDate, eventLocation, eventDescription);
    }

    public String addVendor(String eventID, Vendor vendor) {
        return eventOrganizer.addVendor(eventID, vendor);
    }

    public String addGuest(String eventID, Guest guest) {
        return eventOrganizer.addGuest(eventID, guest);
    }

    public String editEventName(String eventID, String newEventName) {
        return eventOrganizer.editEventName(eventID, newEventName);
    }

    public ArrayList<Event> viewOrganizedEvents() {
        return eventOrganizer.viewOrganizedEvents();
    }

    public String viewOrganizedTransactionDetails(String eventID) {
        return eventOrganizer.viewOrganizedTransactionDetails(eventID);
    }
}
