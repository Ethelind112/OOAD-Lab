package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Event;
import model.Invitation;
import model.User;
import view.Main;
import view.ViewChangeProfile;
import view.ViewEvents;
import view.ViewInvitation;

public class InvitationController {

	ViewInvitation invitationView;
	String email;
	
	public InvitationController() {
		
	}
	
	public InvitationController(ViewInvitation invitationView, String email) {
		this.invitationView = invitationView;
		this.email = email;
		UserController uController = new UserController();
		User user = uController.getUserByEmail(email);
		
		loadInvitationList();
		
		invitationView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ViewChangeProfile view = new ViewChangeProfile();
				UserController uController = new UserController(view, email);
				Main.redirect(view.getScene());
			}
		});
		
		invitationView.setAcceptButton(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Event selectedInvitation = invitationView.getInvitationTable().getSelectionModel().getSelectedItem();
				if(selectedInvitation != null) {
					invitationView.setErrorMessage("");
					String eventID = selectedInvitation.getEvent_id();
					String role = new UserController().getUser().getUser_role();
					
					if(role.equalsIgnoreCase("Vendor")) {
						VendorController vController = new VendorController();
						vController.acceptInvitation(eventID);
					}
					else if (role.equalsIgnoreCase("Guest")) {
						GuestController gController = new GuestController();
						gController.acceptInvitation(eventID);
					}
					invitationView.setTable();
					
				}else {
					invitationView.setErrorMessage("Choose the invitation bellow");
				}
			}
		});
		
		if(user.getUser_role().equalsIgnoreCase("guest")) {
			
			invitationView.setEventMenu(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					ViewEvents view = new ViewEvents(user.getUser_email());
					GuestController gController = new GuestController(view, email);
					Main.redirect(view.getScene());
				}
			});
		}else if(user.getUser_role().equalsIgnoreCase("vendor")) {
			
			invitationView.setEventMenu(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					ViewEvents view = new ViewEvents(user.getUser_email());
					VendorController vController = new VendorController(view, email);
					Main.redirect(view.getScene());
				}
			});
		}

	}
	
	public void loadInvitationList() {
		ArrayList<Event> invitation = getInvitations(this.email);
		
		ObservableList<Event> invitationData = FXCollections.observableArrayList(invitation);
		
		invitationView.setInvitationList(invitationData);
	}
	
	public ArrayList<Event> getInvitations(String email) {
		Invitation invitation = new Invitation();
		return invitation.getInvitations(email);
	}
}
