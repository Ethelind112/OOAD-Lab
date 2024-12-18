package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Event;
import model.User;
import model.Vendor;
import view.Main;
import view.ViewChangeProfile;
import view.ViewEventDetails;
import view.ViewEvents;
import view.ViewInvitation;
import view.ViewManageVendor;

public class VendorController {
	private ViewEvents acceptedInvView;
	private ViewManageVendor managevendor;
	private String email;
	private User user;
	
	public VendorController() {
		
	}
	
	public VendorController(ViewManageVendor managevendor, String email) {
		this.managevendor = managevendor;
		this.email = email;
		
		if(user.getUser_role().equalsIgnoreCase("Vendor")) {
			
			//set on click logic for add btn in ViewManageVendor
			managevendor.setAddButtonAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		if(user.getUser_role().equalsIgnoreCase("Vendor")) {
			//set on click logic for back btn in ViewManageVendor
			managevendor.setbackButton(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Main.toEventPageVendorFromManageVendor(email);
				}
			});
		}
		
		if(user.getUser_role().equalsIgnoreCase("Vendor")) {
			//set on click logic for edit btn in ViewManageVendor
			managevendor.setEditButton(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
	}
	
	public VendorController(ViewEvents acceptedInvView, String email) {
		this.acceptedInvView = acceptedInvView;
		this.email = email;
		
		loadEventList();
		
		acceptedInvView.setVendorMenu();
		
		acceptedInvView.setInvitationMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toInvitationPage(email);
			}
		});
		
		acceptedInvView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toChangeProfilePage(email);
			}
		});
		
		acceptedInvView.setEventDetailButton(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Event selectedEvent = acceptedInvView.getEventTable().getSelectionModel().getSelectedItem();
				Main.toEventDetailPage(email, selectedEvent.getEvent_id());
			}
			
		});
		
		acceptedInvView.setManageVendorMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toEventPageVendor(email);
			}
		});
	}
//	
//	public void loadProductsList() {
//		ArrayList<Products> product = 
//	}
//	
	public void loadEventList() {
		ArrayList<Event> invitation = viewAcceptedEvents(email);
		
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

