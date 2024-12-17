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
		
		acceptedInvView.setGuestMenu();
		
//		set hal yang dilakukan saat click invitation menu button
		acceptedInvView.setInvitationMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toInvitationPage(email);
			}
		});
		
//		set hal yang dilakukan saat click change profile menu button
		acceptedInvView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toChangeProfilePage(email);
			}
		});
		
//		set hal yang dilakukan saat click event untuk redirect ke event detail
		acceptedInvView.setEventDetailButton(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Event selectedEvent = acceptedInvView.getEventTable().getSelectionModel().getSelectedItem();
				Main.toEventDetailPage(email, selectedEvent.getEvent_id());
			}
			
		});
	}
	
//	untuk mengeload data event ke dalam tabel di view
	public void loadEventList() {
		ArrayList<Event> invitation = viewAcceptedEvents(email);
		
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
