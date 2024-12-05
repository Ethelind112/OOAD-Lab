package controller;

import model.Admin;

public class AdminController {

	public AdminController() {
		// TODO Auto-generated constructor stub
	}
	
	public void deleteEvent(String eventID) {
		Admin admin = new Admin();
		admin.deleteEvent(eventID);
	}
	
	public void deleteUser(String userID) {
		Admin admin = new Admin();
		admin.deleteUser(userID);	
	}

}
