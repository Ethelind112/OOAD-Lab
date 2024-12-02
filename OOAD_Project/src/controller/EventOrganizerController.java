package controller;

import model.Event;
import model.EventOrganizer;
import model.Guest;
import model.Vendor;

import java.util.ArrayList;

public class EventOrganizerController {
    
    private EventOrganizer eventOrganizer;

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
