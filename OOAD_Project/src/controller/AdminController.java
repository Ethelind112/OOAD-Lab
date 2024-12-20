package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import view.ViewEventDetails;
import view.ViewEvents;
import view.ViewLogin;
import view.ViewUser;

public class AdminController {
	
	private ViewEvents eventView;
	private ViewUser userView;
	private ViewChangeProfile changeProfileView;
	private ViewEventDetails eventDetView;
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
					// INI DIGANTI GAAAAA
					Main.toEventDetailPage(email, selectedEvent.getEvent_id());
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
	
//	public AdminController(ViewEventDetails eventDetView, String email, String eventid) {
//		this.eventDetView = eventDetView;
//		this.email = email;
//		
//		eventDetView.setAdminMenu();
//		
//		loadGuestList(eventid);
//		loadVendorList(eventid);
//		
//		userView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				Main.toChangeProfilePage(email);
//			}
//		});
//		
//		eventDetView.setEventMenu(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				Main.toEventPageAdmin(email);
//			}
//		});
//		
//		eventDetView.setUserMenu(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
////				AdminController adminC = new AdminController(userView, email);
//				Main.toUserPage(email);
//			}
//		});
//		
//		Event currEvent = new Event();
//		currEvent.viewEventDetails(eventid);
//		
////		mengeset semua elemen berdasarkan data yang ditemukan
//		eventDetView.setEventName(currEvent.getEvent_name());
//		eventDetView.setEventDesc(currEvent.getEvent_description());
//		eventDetView.setEventDate(currEvent.getEvent_date());
//		eventDetView.setEventLoc(currEvent.getEvent_location());
//		
//	}
	
	public void loadEventList() {
		ArrayList<Event> events = viewAllEvents();
		
		ObservableList<Event> eventData = FXCollections.observableArrayList(events);
		
		eventView.setEventList(eventData);
	}
	
	public void loadUserList() {
		ArrayList<User> users = getAllUsers();
		
		ObservableList<User> userData = FXCollections.observableArrayList(users);
		
		userView.setUserList(userData);
	}
	
	public ArrayList<Event> viewAllEvents() {
		return getAllEvents();
	}
	
	public ArrayList<Event> getAllEvents() {
		Event events = new Event();
		return events.viewAllEvents();
	}
	
	public ArrayList<User> getAllUsers() {
		User users = new User();
		return users.viewUsers();
	}
	
	public ArrayList<User> getGuestByTransactionId(String event_id) {
		Guest guest = new Guest();
		return guest.getGuestByTransactionId(event_id);
	}
	
	public ArrayList<User> getVendorByTransactionId(String event_id) {
		Vendor vendor = new Vendor();
		return vendor.getVendorByTransactionId(event_id);
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
