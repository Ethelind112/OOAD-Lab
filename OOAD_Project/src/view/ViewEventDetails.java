package view;

import controller.EventController;
import controller.GuestController;
import controller.UserController;
import controller.VendorController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;

public class ViewEventDetails {
	
	private Scene eventDetailScene;
	VBox eventDetailContainer;
	BorderPane eventDetailPage;
	Label eventName, eventDescription, eventDate, eventLocation, dateLbl, locationLbl;
	
	HBox location, date;
	
	MenuBar menubar;
	Menu invitation, event, updateProfile, users, manageVendor, createEvent;
	MenuItem iInvitation, iEvent, iUpdateProfile, iUsers, iManageVendor, iCreateEvent;
	
	public void initEventDetail() {
		eventDetailContainer = new VBox();
		eventDetailPage = new BorderPane();
		eventDetailScene = new Scene(eventDetailPage, 1000, 700);
		eventName = new Label();
		eventDescription = new Label();
		eventDate = new Label();
		eventLocation = new Label();
		dateLbl = new Label("Date: ");
		locationLbl = new Label("Location: ");
		location = new HBox();
		date = new HBox();
		
		menubar = new MenuBar();
		updateProfile = new Menu("Update Profile");
		iUpdateProfile = new MenuItem("Update Profile");
	}
	
	public void initDetailComponent() {
		location.getChildren().addAll(locationLbl, eventLocation);
		date.getChildren().addAll(dateLbl, eventDate);
		eventDetailContainer.getChildren().addAll(eventName, eventDescription, date, location);
		eventDetailPage.setTop(menubar);
		eventDetailPage.setCenter(eventDetailContainer);
	}
	
	public void eventDetailStyling() {
		eventDetailPage.setStyle("-fx-background-color: white;");
		eventDetailContainer.setMaxWidth(900);
		menubar.setPadding(new Insets(10, 10, 10, 10));
		
		eventName.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		eventDetailContainer.setMargin(eventName, new Insets(50,0,10,0));
		eventDetailContainer.setMargin(eventDescription, new Insets(0,0,20,0));
		eventDetailContainer.setAlignment(Pos.TOP_CENTER);
		location.setAlignment(Pos.TOP_CENTER);
		date.setAlignment(Pos.TOP_CENTER);
	}
	
	public ViewEventDetails() {
		initEventDetail();
		initDetailComponent();
		eventDetailStyling();
	}
	
	public void setGuestMenu() {
		invitation = new Menu("Invitations");
		event = new Menu("Events");
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(invitation, event, updateProfile);

	}
	
	public void setAdminMenu() {
		users = new Menu("Users");
		event = new Menu("Events");
		
		iUsers = new MenuItem("Users");
		iEvent = new MenuItem("Accepted Events");
		
		users.getItems().addAll(iUsers);
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(event, users, updateProfile);

	}
	
	public void setVendorMenu() {
		invitation = new Menu("Invitations");
		event = new Menu("Events");
		manageVendor = new Menu("Manage Vendor");
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		iManageVendor = new MenuItem("Manage Vendor");
		
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		manageVendor.getItems().addAll(iManageVendor);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(invitation, event, manageVendor, updateProfile);

	}
	
	public void setEventOrganizerMenu() {
		invitation = new Menu("Invitations");
		event = new Menu("Events");
		createEvent = new Menu("Create Event");
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		iCreateEvent = new MenuItem("Create Event");
		
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		createEvent.getItems().addAll(iCreateEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(invitation, event, createEvent, updateProfile);

	}
	
	public Scene getScene() {
		return eventDetailScene;
	}
	
	public void setInvitationMenu(EventHandler<ActionEvent> handler) {
		iInvitation.setOnAction(handler);
	}
	
	public void setManageVendorMenu(EventHandler<ActionEvent> handler) {
		iManageVendor.setOnAction(handler);
	}
	
	public void setChangeProfileMenu(EventHandler<ActionEvent> handler) {
		iUpdateProfile.setOnAction(handler);
	}
	
	public void setEventMenu(EventHandler<ActionEvent> handler) {
		iEvent.setOnAction(handler);
	}
	
	public void setEventName(String name) {
		eventName.setText(name);
	}
	
	public void setEventDesc(String desc) {
		eventDescription.setText(desc);
	}
	
	public void setEventDate(String date) {
		eventDate.setText(date);
	}
	
	public void setEventLoc(String location) {
		eventLocation.setText(location);
	}
}
