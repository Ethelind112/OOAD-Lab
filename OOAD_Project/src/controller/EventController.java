package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Event;
import model.User;
import view.Main;
import view.ViewChangeProfile;
import view.ViewEventDetails;
import view.ViewEvents;
import view.ViewInvitation;

public class EventController {
	
	String email;
	ViewEventDetails eventDetView;

	public EventController() {
		
	}
	
//	handle view event details untuk semua role
	public EventController(ViewEventDetails eventDetView, String email, String eventID) {
		this.eventDetView = eventDetView;
		this.email = email;
		
//		mengambil user dari email
		User user = new User().getUserByEmail(email);
		
//		if(user.getUser_role().equalsIgnoreCase("guest")) {
//			eventDetView.setGuestMenu();
//		}
		
//		set hal yang dilakukan saat click invitation menu button
		eventDetView.setInvitationMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ViewInvitation view = new ViewInvitation(email);
				InvitationController iController = new InvitationController(view, email);
				Main.redirect(view.getScene());
			}
		});
		
//		set hal yang dilakukan saat click change profile menu button
		eventDetView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ViewChangeProfile view = new ViewChangeProfile();
				UserController uController = new UserController(view, email);
				Main.redirect(view.getScene());
			}
		});
		
//		set hal yang dilakukan saat click event menu button
		eventDetView.setEventMenu(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				UserController uController = new UserController();
				ViewEvents view = new ViewEvents(uController.getUserByEmail(email).getUser_email());
				String role = new UserController().getUser().getUser_role();
				
//				proses controller berdasarkan rolenya
				if(role.equalsIgnoreCase("Vendor")) {
					VendorController gController = new VendorController(view, uController.getUser().getUser_email());
				}
				else if (role.equalsIgnoreCase("Guest")) {
					GuestController gController = new GuestController(view, uController.getUser().getUser_email());
				}
				Main.redirect(view.getScene());
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
		Event event = new Event();
		return event.viewEventDetails(eventID);
	}

}
