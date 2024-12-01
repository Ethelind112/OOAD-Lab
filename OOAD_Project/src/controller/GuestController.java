package controller;

import model.Guest;

public class GuestController {
	
	public GuestController() {
		// TODO Auto-generated constructor stub
	}

	public String acceptInvitation(String eventID) {
		Guest guest = new Guest();
		
		return guest.acceptInvitation(eventID);
	}
	
}
