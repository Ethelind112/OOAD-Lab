package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewEventDetails {
	
	String eventId;

	Scene eventDetailScene;
	VBox eventDetailContainer;
	BorderPane eventDetailPage;
	Label eventName, eventDescription, eventDate, eventLocation, dateLbl, locationLbl;
	
	HBox location, date;
	
	MenuBar menubar;
	Menu invitation, event, updateProfile;
	MenuItem iInvitation, iEvent, iUpdateProfile;
	
	public void initEventDetail() {
		eventDetailContainer = new VBox();
		eventDetailPage = new BorderPane();
		eventDetailScene = new Scene(eventDetailPage, 1000, 700);
		eventName = new Label("");
		eventDescription = new Label("");
		eventDate = new Label("");
		eventLocation = new Label("");
		dateLbl = new Label("Date: ");
		locationLbl = new Label("Location");
		location = new HBox();
		date = new HBox();
		
		menubar = new MenuBar();
		invitation = new Menu("Invitations");
		event = new Menu("Accepted Events");
		updateProfile = new Menu("Update Profile");
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		iUpdateProfile = new MenuItem("Update Profile");
	}
	
	public void initDetailComponent() {
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(invitation, event, updateProfile);
		
		location.getChildren().addAll(locationLbl, eventLocation);
		date.getChildren().addAll(dateLbl, eventDate);
		eventDetailContainer.getChildren().addAll(eventName, eventDescription, date, location);
		eventDetailPage.setTop(menubar);
		eventDetailPage.setCenter(eventDetailContainer);
	}
	
	public ViewEventDetails(String eventId) {
		this.eventId = eventId;
		initEventDetail();
		initDetailComponent();
		
		Main.redirect(eventDetailScene);
	}

}
