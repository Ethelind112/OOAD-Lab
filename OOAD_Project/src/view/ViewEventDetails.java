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
	Menu invitation, event, updateProfile, manageVendor;
	MenuItem iInvitation, iEvent, iUpdateProfile, iManageVendor;
	
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
		invitation = new Menu("Invitations");
		event = new Menu("Events");
		updateProfile = new Menu("Update Profile");
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		iUpdateProfile = new MenuItem("Update Profile");
		
		String role = new UserController().getUser().getUser_role();
		if (role.equalsIgnoreCase("Vendor")) {
			manageVendor = new Menu("Manage Vendor");
			iManageVendor = new MenuItem("Manage Vendor");
		}
	}
	
	public void initDetailComponent() {
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		if(iManageVendor != null) {
			manageVendor.getItems().addAll(iManageVendor);
			menubar.getMenus().addAll(invitation, event, updateProfile, manageVendor);
		}
		
		menubar.getMenus().addAll(invitation, event, updateProfile);
		
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
		
		Main.redirect(eventDetailScene);
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
