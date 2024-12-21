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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Event;
import model.User;

public class ViewEventDetails {

    private Scene eventDetailScene;
    private VBox eventDetailContainer, guestC, vendorC;
    private HBox allUserContainer;
    private BorderPane eventDetailPage;
    private Label eventName, eventDescription, eventDate, eventLocation, dateLbl, locationLbl, guestLbl, vendorLbl;

    private HBox location, date;

    private MenuBar menubar;
    private Menu invitation, event, updateProfile, users, manageVendor;
    private MenuItem iInvitation, iEvent, iUpdateProfile, iUsers, iManageVendor;

    private TableView<User> guestTable, vendorTable;
    private ObservableList<User> guestData, vendorData;

    private Button addGuestBtn, addVendorBtn;

    public void initEventDetail() {
        eventDetailContainer = new VBox(20); 
        guestC = new VBox(10);
        vendorC = new VBox(10);
        allUserContainer = new HBox(100);

        eventDetailPage = new BorderPane();
        eventDetailScene = new Scene(eventDetailPage, 1000, 700);

        eventName = new Label();
        eventDescription = new Label();
        eventDate = new Label();
        eventLocation = new Label();
        dateLbl = new Label("Date: ");
        locationLbl = new Label("Location: ");

        location = new HBox(10); 
        date = new HBox(10); 

        menubar = new MenuBar();
        updateProfile = new Menu("Update Profile");
        iUpdateProfile = new MenuItem("Update Profile");

        guestTable = new TableView<>();
        vendorTable = new TableView<>();

        guestLbl = new Label("Guest Attendee List");
        vendorLbl = new Label("Vendor Attendee List");

        allUserContainer.setVisible(true);
    }

    public void initDetailComponent() {
        setGuestTable();
        setVendorTable();

        guestTable.setPrefHeight(300);
        vendorTable.setPrefHeight(300);
        guestTable.setPrefWidth(400);
        vendorTable.setPrefWidth(400);

        addGuestBtn = new Button("Add Guest");
        addVendorBtn = new Button("Add Vendor");

        addGuestBtn.setStyle("-fx-background-color: #006e00; -fx-text-fill: white; -fx-padding: 6px;");
        addVendorBtn.setStyle("-fx-background-color: #006e00; -fx-text-fill: white; -fx-padding: 6px;");

        HBox guestBtnContainer = new HBox(addGuestBtn);
        HBox vendorBtnContainer = new HBox(addVendorBtn);
        
        guestBtnContainer.setAlignment(Pos.CENTER);
        vendorBtnContainer.setAlignment(Pos.CENTER);
        
        guestC.getChildren().addAll(guestLbl, guestTable, guestBtnContainer);
        vendorC.getChildren().addAll(vendorLbl, vendorTable, vendorBtnContainer);

        location.getChildren().addAll(locationLbl, eventLocation);
        date.getChildren().addAll(dateLbl, eventDate);

        allUserContainer.getChildren().addAll(guestC, vendorC);
        allUserContainer.setAlignment(Pos.CENTER); 

        eventDetailContainer.getChildren().addAll(
            eventName, 
            eventDescription, 
            date, 
            location, 
            allUserContainer
        );

        eventDetailPage.setTop(menubar);
        eventDetailPage.setCenter(eventDetailContainer);
    }

    public void eventDetailStyling() {
        eventDetailPage.setStyle("-fx-background-color: white;");
        eventDetailContainer.setMaxWidth(900);
        eventDetailContainer.setPadding(new Insets(20)); 
        menubar.setPadding(new Insets(10));

        eventName.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        eventDetailContainer.setMargin(eventName, new Insets(50, 0, 10, 0));
        eventDetailContainer.setMargin(eventDescription, new Insets(0, 0, 20, 0));
        eventDetailContainer.setAlignment(Pos.TOP_CENTER);
        
        location.setAlignment(Pos.CENTER);
        date.setAlignment(Pos.CENTER);

        guestC.setPadding(new Insets(10));
        vendorC.setPadding(new Insets(10));
        
        guestC.setMaxWidth(450);
        vendorC.setMaxWidth(450);
        
        guestLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        vendorLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
     
        addGuestBtn.setPrefWidth(150);
        addVendorBtn.setPrefWidth(150);

        allUserContainer.setPadding(new Insets(20));
        allUserContainer.setAlignment(Pos.CENTER);
    }

    public void setGuestTable() {
        TableColumn<User, String> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        idColumn.setPrefWidth(100);

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        emailColumn.setPrefWidth(200);

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        nameColumn.setPrefWidth(150);

        guestTable.getColumns().addAll(idColumn, emailColumn, nameColumn);
        guestData = FXCollections.observableArrayList();
        guestTable.setItems(guestData);
    }

    public void setVendorTable() {
        TableColumn<User, String> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        idColumn.setPrefWidth(100);

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        emailColumn.setPrefWidth(200);

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        nameColumn.setPrefWidth(150);

        vendorTable.getColumns().addAll(idColumn, emailColumn, nameColumn);
        vendorData = FXCollections.observableArrayList();
        vendorTable.setItems(vendorData);
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

    public void setAddGuestBtnHandler(EventHandler<ActionEvent> handler) {
        addGuestBtn.setOnAction(handler);
    }

    public void setAddVendorBtnHandler(EventHandler<ActionEvent> handler) {
        addVendorBtn.setOnAction(handler);
    }
}
