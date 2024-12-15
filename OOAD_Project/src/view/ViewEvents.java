package view;

import java.util.ArrayList;

import controller.EventController;
import controller.GuestController;
import controller.InvitationController;
import controller.UserController;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;

public class ViewEvents {
	
	String email;
	
	Scene eventScene;
	BorderPane eventPage;
	VBox eventContainer;
	Label eventTitle, eventDescription;
	TableView<Event> eventTable;
	ObservableList<Event> eventData;
	
	MenuBar menubar;
	Menu invitation, event, updateProfile;
	MenuItem iInvitation, iEvent, iUpdateProfile;
	
	TableRow<Event> row;
	
	public void initInvitation() {
		
		eventPage = new BorderPane();
		eventContainer = new VBox();
		eventScene = new Scene(eventPage, 1000, 700);
		eventTitle = new Label("Events");
		eventDescription = new Label("This is your event list, select the event from the table bellow to view the details");
		eventTable = new TableView<>();
		
		menubar = new MenuBar();
		invitation = new Menu("Invitations");
		event = new Menu("Events");
		updateProfile = new Menu("Update Profile");
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		iUpdateProfile = new MenuItem("Update Profile");
	}
	
	public void setTable() {
		
		
		TableColumn<Event,String> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("event_id"));
		idColumn.setMinWidth(eventContainer.getWidth()/6);
		
		TableColumn<Event,String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("event_name"));
		nameColumn.setMinWidth(eventContainer.getWidth()/6);
		
		TableColumn<Event,String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("event_date"));
		dateColumn.setMinWidth(eventContainer.getWidth()/6);
		
		TableColumn<Event,String> locationColumn = new TableColumn<>("Location");
		locationColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("event_location"));
		locationColumn.setMinWidth(eventContainer.getWidth()/6);
		
		TableColumn<Event,String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("event_description"));
		descriptionColumn.setMinWidth(eventContainer.getWidth()/6);
		
		TableColumn<Event,String> organizerColumn = new TableColumn<>("Organizer");
		organizerColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("organizer_id"));
		organizerColumn.setMinWidth(eventContainer.getWidth()/6);
		
		eventTable.getColumns().addAll(idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn, organizerColumn);
		
		eventData = FXCollections.observableArrayList();
		eventTable.setItems(eventData);
	}
	
	public void initInvitationComponent() {
		
		setTable();
		
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(invitation, event, updateProfile);
		
		eventContainer.getChildren().addAll(eventTitle, eventDescription, eventTable);
		eventPage.setTop(menubar);
		eventPage.setCenter(eventContainer);
	}
	
	public void invitationStyling(){
		eventPage.setStyle("-fx-background-color: white;");
	
		eventContainer.setMaxWidth(900);
		
		menubar.setPadding(new Insets(10, 10, 10, 10));
		
		eventTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		eventContainer.setMargin(eventTitle, new Insets(50,0,10,0));
		eventContainer.setMargin(eventDescription, new Insets(0,0,50,0));
		eventContainer.setAlignment(Pos.TOP_CENTER);
	}
	
	public void invitation() {
		initInvitationComponent();
		invitationStyling();
	}
	
	public void setEventDetailButton(EventHandler<MouseEvent> handler) {
		
		eventTable.setRowFactory((TableView<Event> e) -> {
			row = new TableRow<>();
			row.setOnMouseClicked(handler);
			return row;
		});
	}
	
	public void setChangeProfileMenu(EventHandler<ActionEvent> handler) {
		iUpdateProfile.setOnAction(handler);
	}
	
	public void setInvitationMenu(EventHandler<ActionEvent> handler) {
		iInvitation.setOnAction(handler);
	}
	
	public void setEventList(ObservableList<Event> events) {
		eventData.setAll(events);
	}
	
	public TableView<Event> getEventTable(){
		return eventTable;
	}
	
	public ViewEvents(String email) {
		this.email = email;
		
		initInvitation();
		invitation();
		
		Main.redirect(eventScene);
	}
	
	public Scene getScene() {
		return eventScene;
	}

}
