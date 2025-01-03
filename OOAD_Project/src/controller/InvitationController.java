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
	private User user;
	private Invitation invitation;
	
	public InvitationController() {
		
	}
	
//	handle view invitation
	public InvitationController(ViewInvitation invitationView, String email) {
		this.invitationView = invitationView;
		this.email = email;
		invitation = new Invitation();
		
		user = new User().getUserByEmail(email);
		
//		memasukan data bila ada ke tabel di view
		loadInvitationList();
		
		if(user.getUser_role().equalsIgnoreCase("guest")) {
			invitationView.setGuestMenu();
		}else {
			invitationView.setVendorMenu();
			
			//hal yang dilakukan saat click managevendor
			invitationView.setManageVendor(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					Main.toManageVendor(email);
				}
			});
		}
		
//		set hal yang dilakukan saat click change profile menu button
		invitationView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toChangeProfilePage(email);
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
					
//					proses accept invitation menggunakan invitation class (mengikuti sequence diagram)
					invitation.acceptInvitation(eventID, user.getUser_id());
					
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
					Main.toEventPageGuest(email);
				}
			});
		}else if(user.getUser_role().equalsIgnoreCase("vendor")) {
			
			invitationView.setEventMenu(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Main.toEventPageVendor(email);
				}
			});
		}

	}
	
	public void acceptInvitation(String eventID) {
		invitation.acceptInvitation(eventID, user.getUser_id());
	}
	
//	untuk mengeload data invitation ke dalam tabel di view
	public void loadInvitationList() {
		ArrayList<Event> invitation = getInvitations(this.email);
		
		ObservableList<Event> invitationData = FXCollections.observableArrayList(invitation);
		
		invitationView.setInvitationList(invitationData);
	}
	
//	untuk mengeload data invitation ke dalam tabel di view
	public ArrayList<Event> getInvitations(String email) {
		return invitation.getInvitations(email);
	}
}
