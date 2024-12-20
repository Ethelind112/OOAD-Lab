package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Event;
import model.Guest;
import model.User;
import model.Vendor;
import view.Main;
import view.ViewChangeProfile;
import view.ViewEventDetails;
import view.ViewEvents;
import view.ViewInvitation;

public class EventController {
	
	private String email;
	private ViewEventDetails eventDetView;
	private Event event;

	public EventController() {
		
	}
	
//	handle view event details untuk semua role
	public EventController(ViewEventDetails eventDetView, String email, String eventID) {
		this.eventDetView = eventDetView;
		this.email = email;
		this.event = new Event();
		
//		mengambil user dari email
		User user = new User().getUserByEmail(email);
		
		if(user.getUser_role().equalsIgnoreCase("guest")) {
			eventDetView.setGuestMenu();
			
//			set hal yang dilakukan saat click invitation menu button
			eventDetView.setInvitationMenu(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					Main.toInvitationPage(email);
				}
			});
		}else if(user.getUser_role().equalsIgnoreCase("admin")) {
			eventDetView.setAdminMenu();
			
			loadGuestList(eventID);
			loadVendorList(eventID);
			
			eventDetView.setUserMenu(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Main.toUserPage(email);
				}
			});
		}else if(user.getUser_role().equalsIgnoreCase("vendor")) {
			eventDetView.setVendorMenu();
//			System.out.println("Role: " + user.getUser_role());
					
//			//hal yang dilakukan saat click manage vendor menu button
//			eventDetView.setManageVendorMenu(new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent event) {
//					Main.toManageVendor(email);
//				}
//			});
			
			
//			set hal yang dilakukan saat click invitation menu button
			eventDetView.setInvitationMenu(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					Main.toInvitationPage(email);
				}
			});
		}else if(user.getUser_role().equalsIgnoreCase("event organizer")) {
			eventDetView.setEventOrganizerMenu();
		}
		
//		set hal yang dilakukan saat click change profile menu button
		eventDetView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toChangeProfilePage(email);
			}
		});
		
//		set hal yang dilakukan saat click event menu button
		eventDetView.setEventMenu(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(user.getUser_role().equalsIgnoreCase("Admin")) {
					Main.toEventPageAdmin(user.getUser_email());
				}else if(user.getUser_role().equalsIgnoreCase("Guest")){
					Main.toEventPageGuest(user.getUser_email());
				}else if(user.getUser_role().equalsIgnoreCase("Event Organizer")) {
					Main.toEventPageEO(user.getUser_email());
				}else if(user.getUser_role().equalsIgnoreCase("Vendor")) {
					Main.toEventPageVendor(user.getUser_email());
				}
			}
		});
		
//		membuat event model dari event yang didapatkan
		Event currEvent = viewEventDetails(eventID);
		
//		mengeset semua elemen berdasarkan data yang ditemukan
		eventDetView.setEventName(currEvent.getEvent_name());
		eventDetView.setEventDesc(currEvent.getEvent_description());
		eventDetView.setEventDate(currEvent.getEvent_date());
		eventDetView.setEventLoc(currEvent.getEvent_location());
	}
	
	public Event viewEventDetails(String eventID) {
		return event.viewEventDetails(eventID);
	}
	
	public void loadGuestList(String id) {
		Guest guest = new Guest();
		ArrayList<User> users = guest.getGuestByTransactionId(id);
		
		ObservableList<User> userData = FXCollections.observableArrayList(users);
		
		eventDetView.setGuestList(userData);
	}
	
	public void loadVendorList(String id) {
		Vendor vendor = new Vendor();
		ArrayList<User> users = vendor.getVendorByTransactionId(id);
		
		ObservableList<User> userData = FXCollections.observableArrayList(users);
		
		eventDetView.setVendorList(userData);
	}

}
