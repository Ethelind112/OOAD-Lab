package view;

import java.util.ArrayList;

import controller.GuestController;
import controller.InvitationController;
import controller.UserController;
import controller.VendorController;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;
import model.Invitation;

public class ViewInvitation {
	
	private String email;

	Scene invitationScene;
	BorderPane invitationPage;
	VBox invitationContainer;
	Label invitationTitle, invitationDescription, invitationEM;
	TableView<Event> invitationTable;
	Button acceptBtn;
	ObservableList<Event> invitationData;
	
	MenuBar menubar;
	Menu invitation, event, updateProfile, manageVendor;
	MenuItem iInvitation, iEvent, iUpdateProfile, iManageVendor;
	
	public void initInvitation() {
		invitationPage = new BorderPane();
		invitationContainer = new VBox();
		invitationScene = new Scene(invitationPage, 1000, 700);
		invitationTitle = new Label("Invitation");
		invitationDescription = new Label("This is your invitation, select the invitation from the table bellow before clicking on the accept button.");
		invitationEM = new Label("");
		acceptBtn = new Button("Accept");
		invitationTable = new TableView<>();
		
		menubar = new MenuBar();
		
		updateProfile = new Menu("Update Profile");
		
		iUpdateProfile = new MenuItem("Update Profile");
	}
	
	public void setTable() {
		
		invitationData = FXCollections.observableArrayList();
		
		TableColumn<Event,String> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("event_id"));
		idColumn.setMinWidth(invitationContainer.getWidth()/8);
		
		TableColumn<Event,String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("event_name"));
		nameColumn.setMinWidth(invitationContainer.getWidth()/6);
		
		TableColumn<Event,String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("event_date"));
		dateColumn.setMinWidth(invitationContainer.getWidth()/8);
		
		TableColumn<Event,String> locationColumn = new TableColumn<>("Location");
		locationColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("event_location"));
		locationColumn.setMinWidth(invitationContainer.getWidth()/8);
		
		TableColumn<Event,String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("event_description"));
		descriptionColumn.setMinWidth(invitationContainer.getWidth()/3);
		
		TableColumn<Event,String> organizerColumn = new TableColumn<>("Organizer");
		organizerColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("organizer_id"));
		organizerColumn.setMinWidth(invitationContainer.getWidth()/8);
		
		invitationTable.getColumns().addAll(idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn, organizerColumn);
		invitationTable.setItems(invitationData);
	}
	
	public void initInvitationComponent() {
		
		setTable();
		
		invitationContainer.getChildren().addAll(invitationTitle, invitationDescription, acceptBtn, invitationEM, invitationTable);
		invitationPage.setTop(menubar);
		invitationPage.setCenter(invitationContainer);
	}
	
	public void invitationStyling(){
		invitationContainer.setStyle("-fx-background-color: white;");
	
		invitationContainer.setMaxWidth(900);
		
		menubar.setPadding(new Insets(10, 10, 10, 10));
		
		invitationTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		invitationContainer.setMargin(invitationTitle, new Insets(50,0,10,0));
		invitationContainer.setAlignment(Pos.TOP_CENTER);
		
		acceptBtn.setPadding(new Insets(10, 0, 10, 0));
		acceptBtn.setMinWidth(100);
		acceptBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		acceptBtn.setFont(Font.font(15));
		
		invitationContainer.setMargin(acceptBtn, new Insets(20, 0, 10, 0));
		invitationContainer.setMargin(invitationEM, new Insets(0, 0, 30, 0));
		
		invitationPage.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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
	
	public void invitation() {
		initInvitationComponent();
		invitationStyling();
	}
	
	public ViewInvitation(String email) {
		this.email = email;
		
		initInvitation();
		invitation();
		Main.redirect(invitationScene);
	}

	public Scene getScene() {
		return invitationScene;
	}
	
	public void setErrorMessage(String message) {
		invitationEM.setText(message);
	}
	
	public void setChangeProfileMenu(EventHandler<ActionEvent> handler) {
		iUpdateProfile.setOnAction(handler);
	}
	
	public void setManageVendor(EventHandler<ActionEvent> handler) {
		iManageVendor.setOnAction(handler);
	}
	
	public void setEventMenu(EventHandler<ActionEvent> handler) {
		iEvent.setOnAction(handler);
	}
	
	public void setAcceptButton(EventHandler<ActionEvent> handler) {
		acceptBtn.setOnAction(handler);
	}
	
	public TableView<Event> getInvitationTable(){
		return invitationTable;
	}
	
	public void setInvitationList(ObservableList<Event> invitations) {
		invitationData.setAll(invitations);
	}
	
}
