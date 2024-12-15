package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Event;
import model.Vendor;
import view.Main;
import view.ViewChangeProfile;
import view.ViewEventDetails;
import view.ViewEvents;
import view.ViewInvitation;

public class VendorController {
	private ViewEvents acceptedInvView;
	private String email;
	
	public VendorController() {
		
	}
	
	public VendorController(ViewEvents acceptedInvView, String email) {
		this.acceptedInvView = acceptedInvView;
		this.email = email;
		
		loadEventList();
		
		acceptedInvView.setInvitationMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ViewInvitation view = new ViewInvitation(email);
				Main.redirect(view.getScene());
			}
		});
		
		acceptedInvView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ViewChangeProfile view = new ViewChangeProfile();
				UserController uController = new UserController(view, email);
				Main.redirect(view.getScene());
			}
		});
		
		acceptedInvView.setEventDetailButton(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Event selectedEvent = acceptedInvView.getEventTable().getSelectionModel().getSelectedItem();
				ViewEventDetails view = new ViewEventDetails(selectedEvent.getEvent_id());
				Main.redirect(view.getScene());
			}
			
		});
	}
	
	public void loadEventList() {
		VendorController vController = new VendorController();
		UserController uController = new UserController();
		ArrayList<Event> invitation = vController.viewAcceptedEvents(uController.getUser().getUser_email());
		
		ObservableList<Event> eventData = FXCollections.observableArrayList(invitation);
		acceptedInvView.setEventList(eventData);
	}
	
	public String acceptInvitation(String eventID) {
		Vendor vendor = new Vendor();
		return vendor.acceptInvitation(eventID);
	}
	
	public ArrayList<Event> viewAcceptedEvents(String email){
		Vendor vendor = new Vendor();
		return vendor.viewAcceptedEvents(email);
	}
	
}

