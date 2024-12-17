package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Admin;
import model.Event;
import model.User;
import view.Main;
import view.ViewChangeProfile;
import view.ViewEvents;
import view.ViewLogin;
import view.ViewUser;

public class AdminController {
	
	private static Admin admin = new Admin();
	private ViewEvents acceptedInvView;
	private ViewUser userView;
	private ViewChangeProfile changeProfileView;
	private String email;

	public AdminController() {
		// TODO Auto-generated constructor stub
	}

	public AdminController(ViewEvents acceptedInvView, String email) {
		this.acceptedInvView = acceptedInvView;
		this.email = email;
		
		acceptedInvView.setAdminMenu();
		
//		set hal yang dilakukan saat click user menu button
		acceptedInvView.setUserMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toUserPage(email);
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
	
	public AdminController(ViewUser userView, String email) {
		this.userView = userView;
		this.email = email;
		
		userView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toChangeProfilePage(email);
			}
		});
		
		userView.setEventMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toEventPage(email);
			}
		});
		
//		set hal yang dilakukan pada saat click delete button
		userView.setADeleteButton(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
	//			mengambil data dari inputan
				User selectedUser = userView.getUserTable().getSelectionModel().getSelectedItem();
				if(selectedUser != null) {
					userView.setErrorMessage("");
					
//					mengambil userid yang diclick
					String userId = selectedUser.getUser_id();
					
//					proses delete user menggunakan user class (mengikuti sequence diagram)
					admin.deleteUser(userId);
					
					userView.setTable();
					
				}else {
//					bila tidak ada yang diselect maka minta user untuk click invitationnya
					userView.setErrorMessage("Choose the user bellow");
				}
			}
		});
	}
	
	public AdminController(ViewChangeProfile changeProfileView, String email) {
		this.changeProfileView = changeProfileView;
		this.email = email;
		
		changeProfileView.setAdminMenu();
		
//		set hal yang dilakukan saat click user menu button
		changeProfileView.setUserMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
//				AdminController adminC = new AdminController(userView, email);
				Main.toUserPage(email);
			}
		});
	
		changeProfileView.setEventMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toEventPage(email);
			}
		});
	}
	
	public void deleteEvent(String eventID) {
		admin.deleteEvent(eventID);
	}
	
	public void deleteUser(String userID) {
		admin.deleteUser(userID);	
	}
	
	public ArrayList<Event> viewEvents() {
		return admin.viewEvents();
	}
	
	public ArrayList<User> viewUsers() {
		return admin.viewUser();
	}

}
