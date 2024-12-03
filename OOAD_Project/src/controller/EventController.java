package controller;

import model.Event;

public class EventController {

	public EventController() {
		// TODO Auto-generated constructor stub
	}
	
	public Event viewEventDetails(String eventID) {
		Event event = new Event();
		return event.viewEventDetails(eventID);
	}

}
