package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Admin;
import model.Event;
import model.Guest;
import model.User;
import model.Vendor;
import view.Main;
import view.ViewChangeProfile;
import view.ViewEvents;
import view.ViewLogin;
import view.ViewUser;

public class AdminController {
	
	private ViewEvents eventView;
	private ViewUser userView;
	private ViewChangeProfile changeProfileView;
	private String email;

	public AdminController() {
		// TODO Auto-generated constructor stub
	}

	public AdminController(ViewEvents eventView, String email) {
		this.eventView = eventView;
		this.email = email;
		
		eventView.setAdminMenu();
		
		loadEventList();
		
//		set hal yang dilakukan saat click user menu button
		eventView.setUserMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toUserPage(email);
			}
		});
		
//		set hal yang dilakukan saat click change profile menu button
		eventView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toChangeProfilePage(email);
			}
		});
		
		eventView.setATransactionButton(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				mengambil data dari inputan
				Event selectedEvent = eventView.getEventTable().getSelectionModel().getSelectedItem();
				if(selectedEvent != null) {
					Main.toEventDetailPage(email, selectedEvent.getEvent_id());
					
//					Guest guest = new Guest();
//					guest.getGuestByTransactionId(selectedEvent.getEvent_id());
//					
//					Vendor vendor = new Vendor();
//					vendor.getVendorByTransactionId(selectedEvent.getEvent_id());
				}else {
					eventView.setErrorMessage("Choose the event above!");
				}
			}
		});

		
//		set hal yang dilakukan saat click delete untuk hapus event
		eventView.setADeleteButton(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				mengambil data dari inputan
				Event selectedEvent = eventView.getEventTable().getSelectionModel().getSelectedItem();
				if(selectedEvent != null) {
					
//					mengambil userid yang diclick
					String eventId = selectedEvent.getEvent_id();
					
//					proses delete user menggunakan user class (mengikuti sequence diagram)
					eventView.setErrorMessage(selectedEvent.deleteEvent(eventId));
					
					loadEventList();
					
				}else {
//					bila tidak ada yang diselect maka minta user untuk click invitationnya
					eventView.setErrorMessage("Choose the event above!");
				}
			}
		});
		
//		set hal yang dilakukan saat click event untuk redirect ke event detail
//		eventView.setEventDetailButton(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				Event selectedEvent = eventView.getEventTable().getSelectionModel().getSelectedItem();
//				Main.toEventDetailPage(email, selectedEvent.getEvent_id());
//			}
//		});
	}
	
	public AdminController(ViewUser userView, String email) {
		this.userView = userView;
		this.email = email;
		
		loadUserList();
		
		userView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toChangeProfilePage(email);
			}
		});
		
		userView.setEventMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toEventPageAdmin(email);
			}
		});
		
//		set hal yang dilakukan pada saat click delete button
		userView.setADeleteButton(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
	//			mengambil data dari inputan
				User selectedUser = userView.getUserTable().getSelectionModel().getSelectedItem();
				if(selectedUser != null) {
//					mengambil userid yang diclick
					String userId = selectedUser.getUser_id();
					
//					proses delete user menggunakan user class (mengikuti sequence diagram)
					userView.setErrorMessage(selectedUser.deleteUser(userId));
					
//					userView.setTable();
					loadUserList();
					
				}else {
//					bila tidak ada yang diselect maka minta user untuk click invitationnya
					userView.setErrorMessage("Choose the user above!");
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
				Main.toEventPageAdmin(email);
			}
		});
	}
	
	public void loadEventList() {
		ArrayList<Event> events = viewAllEvents();
		
		ObservableList<Event> eventData = FXCollections.observableArrayList(events);
		
		eventView.setEventList(eventData);
	}
	
	public void loadUserList() {
		ArrayList<User> users = viewUsers();
		
		ObservableList<User> userData = FXCollections.observableArrayList(users);
		
		userView.setUserList(userData);
	}
	
	public ArrayList<Event> viewAllEvents() {
		Event events = new Event();
		return events.viewAllEvents();
	}
	
	public ArrayList<User> viewUsers() {
		User users = new User();
		return users.viewUsers();
	}

}
