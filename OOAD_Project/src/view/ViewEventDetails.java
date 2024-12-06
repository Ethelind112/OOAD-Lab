package view;

import controller.EventController;
import controller.UserController;
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
	
	String eventId;

	Scene eventDetailScene;
	VBox eventDetailContainer;
	BorderPane eventDetailPage;
	Label eventName, eventDescription, eventDate, eventLocation, dateLbl, locationLbl;
	
	HBox location, date;
	
	MenuBar menubar;
	Menu invitation, event, updateProfile;
	MenuItem iInvitation, iEvent, iUpdateProfile;
	
	public void initEventDetail(String eventID) {
		EventController eController = new EventController();
		Event currEvent = eController.viewEventDetails(eventID);
		
		eventDetailContainer = new VBox();
		eventDetailPage = new BorderPane();
		eventDetailScene = new Scene(eventDetailPage, 1000, 700);
		eventName = new Label(currEvent.getEvent_name());
		eventDescription = new Label(currEvent.getEvent_description());
		eventDate = new Label(currEvent.getEvent_date());
		eventLocation = new Label(currEvent.getEvent_location());
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
	
	public void setEventHandler() {
		iInvitation.setOnAction(e -> {
			UserController uController = new UserController();
			ViewInvitation view = new ViewInvitation(uController.getUser().getUser_email());
			Main.redirect(view.invitationScene);
		});
		
		iUpdateProfile.setOnAction(e -> {
			ViewChangeProfile view = new ViewChangeProfile();
			Main.redirect(view.updateProfileScene);
		});
		
		iEvent.setOnAction(e -> {
			UserController uController = new UserController();
			ViewEvents view = new ViewEvents(uController.getUser().getUser_email());
			Main.redirect(view.eventScene);
		});
		
	}
	
	public ViewEventDetails(String eventID) {
		this.eventId = eventId;
		initEventDetail(eventID);
		initDetailComponent();
		eventDetailStyling();
		setEventHandler();
		
		Main.redirect(eventDetailScene);
	}

}
