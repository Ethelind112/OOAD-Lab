package view;

import controller.EventController;
import controller.GuestController;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Event;
import model.User;

public class ViewEventDetails {
	
	private Scene eventDetailScene;
	VBox eventDetailContainer, guestC, vendorC;
	HBox allUserContainer, btnHB;
	BorderPane eventDetailPage;
	Label eventName, eventDescription, eventDate, eventLocation, dateLbl, locationLbl, guestLbl, vendorLbl;
	
	HBox location, date;
	
	MenuBar menubar;
	Menu invitation, event, updateProfile, users, manageVendor;
	MenuItem iInvitation, iEvent, iUpdateProfile, iUsers, iManageVendor;
	
	TableView<User> guestTable, vendorTable;
	ObservableList<User> guestData, vendorData;
	
	Button addGuest, addVendor;
	
	private Label pageTitle;
    private Label eventNameLbl, eventDateLbl, eventLocationLbl, eventDescLbl;
    private TextField eventNameField, eventDateField, eventLocationField;
    private TextArea eventDescArea;
    private Label errorMessage;
	
	public void initEventDetail() {
		eventDetailContainer = new VBox();
		guestC = new VBox();
		vendorC = new VBox();
		allUserContainer = new HBox(100);
		
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
		
		guestTable = new TableView<>();
		vendorTable = new TableView<>();
		
		guestLbl = new Label("Guest Attendee List");
		vendorLbl = new Label("Vendor Attendee List");
		
		btnHB = new HBox(200);
		addGuest = new Button("Add Guest");
		addGuest.setVisible(false);
		addVendor = new Button("Add Vendor");
		addVendor.setVisible(false);
		
		allUserContainer.setVisible(false);
	}
	
	public void initDetailComponent() {
		setGuestTable();
		setVendorTable();
		
		location.getChildren().addAll(locationLbl, eventLocation);
		date.getChildren().addAll(dateLbl, eventDate);
		
		btnHB.getChildren().addAll(addGuest, addVendor);
		
//		eventDetailContainer.getChildren().addAll(eventName, eventDescription, date, location);
		allUserContainer.getChildren().addAll(guestC, vendorC);
		eventDetailContainer.getChildren().addAll(eventName, eventDescription, date, location, allUserContainer, btnHB);
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
		
		btnHB.setAlignment(Pos.CENTER);
		btnHB.setPadding(new Insets(10, 0, 0, 0));
		
		addGuest.setPadding(new Insets(10, 0, 10, 0));
		addGuest.setMinWidth(150);
		addGuest.setTextFill(Color.WHITE);
		addGuest.setBackground(new Background(new BackgroundFill(Color.web("#b20000"), CornerRadii.EMPTY, null)));
		
		addVendor.setPadding(new Insets(10, 0, 10, 0));
		addVendor.setMinWidth(150);
		addVendor.setTextFill(Color.WHITE);
		addVendor.setBackground(new Background(new BackgroundFill(Color.web("#b20000"), CornerRadii.EMPTY, null)));
		
		guestC.setMaxWidth(580);
		vendorC.setMaxWidth(580);
		guestC.setMargin(guestLbl, new Insets(20,0,0,0));
		vendorC.setMargin(vendorLbl, new Insets(20,0,0,0));
		allUserContainer.setAlignment(Pos.TOP_CENTER);
	}
	
	public ViewEventDetails() {
		initEventDetail();
		initDetailComponent();
		eventDetailStyling();
	}
	
	public void setGuestTable() {
		
		TableColumn<User,String> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<User, String>("user_id"));
		idColumn.setMinWidth(guestC.getWidth()/8);
		
		TableColumn<User,String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("user_email"));
		emailColumn.setMinWidth(guestC.getWidth()/6);
		
		TableColumn<User,String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("user_name"));
		nameColumn.setMinWidth(guestC.getWidth()/8);
		
		guestTable.getColumns().addAll(idColumn, emailColumn, nameColumn);
		guestData = FXCollections.observableArrayList();
		guestTable.setItems(guestData);
		
		guestC.getChildren().addAll(guestLbl, guestTable);
	}
	
	public void setVendorTable() {
		
		TableColumn<User,String> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<User, String>("user_id"));
		idColumn.setMinWidth(vendorC.getWidth()/8);
		
		TableColumn<User,String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("user_email"));
		emailColumn.setMinWidth(vendorC.getWidth()/6);
		
		TableColumn<User,String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("user_name"));
		nameColumn.setMinWidth(vendorC.getWidth()/8);
		
		vendorTable.getColumns().addAll(idColumn, emailColumn, nameColumn);
		vendorData = FXCollections.observableArrayList();
		vendorTable.setItems(vendorData);
		
		vendorC.getChildren().addAll(vendorLbl, vendorTable);
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
		iEvent = new MenuItem("Events");
		
		users.getItems().addAll(iUsers);
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(event, users, updateProfile);
		
		allUserContainer.setVisible(true);
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
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(invitation, event, updateProfile);
		
		allUserContainer.setVisible(true);
		btnHB.setVisible(true);
		addGuest.setVisible(true);
		addVendor.setVisible(true);
	}
	
	public Scene getScene() {
		return eventDetailScene;
	}
	
	public void setGuestList(ObservableList<User> users) {
		guestData.setAll(users);
	}
	
	public void setVendorList(ObservableList<User> users) {
		vendorData.setAll(users);
	}
	
	public void setAddGuest(EventHandler<ActionEvent> handler) {
		
		 addGuest.setOnAction(handler);
	}
	
	public void setAddVendor(EventHandler<ActionEvent> handler) {
		
		 addVendor.setOnAction(handler);
	}
	
	public void setUserMenu(EventHandler<ActionEvent> handler) {
		iUsers.setOnAction(handler);
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
