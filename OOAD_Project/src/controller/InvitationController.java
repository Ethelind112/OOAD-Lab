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

	private ViewInvitation invitationView;
	private String email;
	
	public InvitationController() {
		
	}
	
//	handle view invitation
	public InvitationController(ViewInvitation invitationView, String email) {
		this.invitationView = invitationView;
		this.email = email;
		
		UserController uController = new UserController();
		User user = uController.getUserByEmail(email);
		
//		memasukan data bila ada ke tabel di view
		loadInvitationList();
		
//		set hal yang dilakukan saat click change profile menu button
		invitationView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ViewChangeProfile view = new ViewChangeProfile();
				UserController uController = new UserController(view, email);
				Main.redirect(view.getScene());
			}
		});
		
//		set hal yang dilakukan saat click accept button
		invitationView.setAcceptButton(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
//				mengambil event yang diclick di tabel
				Event selectedInvitation = invitationView.getInvitationTable().getSelectionModel().getSelectedItem();
				if(selectedInvitation != null) {
					invitationView.setErrorMessage("");
					
//					mengambil eventid yang diclick
					String eventID = selectedInvitation.getEvent_id();
					String role = new UserController().getUser().getUser_role();
					
//					proses accept invitation berdasarkan role
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
//					bila tidak ada yang diselect maka minta user untuk click invitationnya
					invitationView.setErrorMessage("Choose the invitation bellow");
				}
			}
		});
		
//		proses controller berdasarkan rolenya sebelum redirect ke event
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
	
//	untuk mengeload data invitation ke dalam tabel di view
	public void loadInvitationList() {
		ArrayList<Event> invitation = getInvitations(this.email);
		
		ObservableList<Event> invitationData = FXCollections.observableArrayList(invitation);
		
		invitationView.setInvitationList(invitationData);
	}
	
//	untuk mengeload data invitation ke dalam tabel di view
	public ArrayList<Event> getInvitations(String email) {
		Invitation invitation = new Invitation();
		return invitation.getInvitations(email);
	}
}
