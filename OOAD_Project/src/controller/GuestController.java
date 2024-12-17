package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Event;
import model.Guest;
import view.Main;
import view.ViewChangeProfile;
import view.ViewEventDetails;
import view.ViewEvents;
import view.ViewInvitation;

public class GuestController {
	
	private ViewEvents acceptedInvView;
	private String email;
	
	public GuestController() {
		
	}

//	handle acceptedinvitationview untuk guest
	public GuestController(ViewEvents acceptedInvView, String email) {
		this.acceptedInvView = acceptedInvView;
		this.email = email;
		
//		memasukan data bila ada ke tabel di view
		loadEventList();
		
//		set hal yang dilakukan saat click invitation menu button
		acceptedInvView.setInvitationMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ViewInvitation view = new ViewInvitation(email);
				InvitationController iController = new InvitationController(view, email);
				Main.redirect(view.getScene());
			}
		});
		
//		set hal yang dilakukan saat click change profile menu button
		acceptedInvView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ViewChangeProfile view = new ViewChangeProfile();
				UserController uController = new UserController(view, email);
				Main.redirect(view.getScene());
			}
		});
		
//		set hal yang dilakukan saat click event untuk redirect ke event detail
		acceptedInvView.setEventDetailButton(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Event selectedEvent = acceptedInvView.getEventTable().getSelectionModel().getSelectedItem();
				ViewEventDetails view = new ViewEventDetails();
				EventController eController = new EventController(view, email, selectedEvent.getEvent_id());
				Main.redirect(view.getScene());
			}
			
		});
	}
	
//	untuk mengeload data event ke dalam tabel di view
	public void loadEventList() {
		GuestController gController = new GuestController();
		UserController uController = new UserController();
		ArrayList<Event> invitation = gController.viewAcceptedEvents(uController.getUser().getUser_email());
		
		ObservableList<Event> eventData = FXCollections.observableArrayList(invitation);
		
		acceptedInvView.setEventList(eventData);
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
