package controller;

import java.util.ArrayList;

import model.Event;
import model.Guest;

public class GuestController {
	
	public GuestController() {
		// TODO Auto-generated constructor stub
	}

	public String acceptInvitation(String eventID) {
		Guest guest = new Guest();
		
		return guest.acceptInvitation(eventID);
	}
	
	public ArrayList<Event> viewAcceptedEvents(String email){
		Guest guest = new Guest();
		return guest.viewAcceptedEvents(email);
	}
	
}
