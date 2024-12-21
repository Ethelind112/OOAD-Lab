package view;

import java.util.ArrayList;

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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Event;
import model.Products;
import model.User;

public class ViewEvents {
	
	String email;
	
	Scene eventScene;
	BorderPane eventPage;
	VBox eventContainer;
	HBox btnHB;
	Label eventTitle, eventDescription, errorM;
	TableView<Event> eventTable;
	ObservableList<Event> eventData;
	
	MenuBar menubar;
	Menu invitation, event, updateProfile, users, manageVendor;
	MenuItem iInvitation, iEvent, iUpdateProfile, iUsers, iManageVendor;
	
	TableRow<Event> row;
	
	Button delBtn, transBtn, detailsBtn;
	
	Button createEventBtn;
	
	private Stage primaryStage;
	private String organizerId;
	
	public void initInvitation() {
		
		eventPage = new BorderPane();
		eventContainer = new VBox();
		eventScene = new Scene(eventPage, 1000, 700);
		eventTitle = new Label("Events");
		eventDescription = new Label("This is your event list, select the event from the table bellow to view the details");
		eventTable = new TableView<>();
		
		menubar = new MenuBar();
		
		updateProfile = new Menu("Update Profile");
		
		iUpdateProfile = new MenuItem("Update Profile");
		
		createEventBtn = new Button("Create New Event");
		createEventBtn.setVisible(false);
		
		btnHB = new HBox(200);
		btnHB.setVisible(false);
		delBtn = new Button("Delete Event");
		delBtn.setVisible(false);
		transBtn = new Button("Event Detail");
		transBtn.setVisible(false);
        detailsBtn = new Button("View Details");
        detailsBtn.setVisible(false);
		
        eventTable = new TableView<>();
        eventTable.setRowFactory(tv -> {
            TableRow<Event> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Event rowData = row.getItem();
                    ViewEventDetails eventDetailsView = new ViewEventDetails();
                    eventDetailsView.initEventDetail();
                    eventDetailsView.initDetailComponent();
                    eventDetailsView.eventDetailStyling();

                    eventDetailsView.setEventName(rowData.getEvent_name());
                    eventDetailsView.setEventDesc(rowData.getEvent_description());
                    eventDetailsView.setEventDate(rowData.getEvent_date());
                    eventDetailsView.setEventLoc(rowData.getEvent_location());

                    Stage stage = (Stage) eventTable.getScene().getWindow();
                    stage.setScene(eventDetailsView.getScene());
                }
            });
            return row;
        });
        
        errorM = new Label();
        errorM.setVisible(false);
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
		
	    TableColumn<Event, Void> actionColumn = new TableColumn<>("Actions");
	    
        TableColumn<Event, Void> detailsColumn = new TableColumn<>("View Details");
        detailsColumn.setMinWidth(eventContainer.getWidth()/8);
        
        detailsColumn.setCellFactory(col -> new TableCell<Event, Void>() {
            private final Button viewButton = new Button("View Details");
            
            {
                viewButton.setOnAction(event -> {
                    Event rowData = getTableView().getItems().get(getIndex());
                    ViewEventDetails eventDetailsView = new ViewEventDetails();
                    eventDetailsView.initEventDetail();
                    eventDetailsView.initDetailComponent();
                    eventDetailsView.eventDetailStyling();

                    eventDetailsView.setEventName(rowData.getEvent_name());
                    eventDetailsView.setEventDesc(rowData.getEvent_description());
                    eventDetailsView.setEventDate(rowData.getEvent_date());
                    eventDetailsView.setEventLoc(rowData.getEvent_location());
 
                    Stage stage = (Stage) getScene().getWindow();
                    stage.setScene(eventDetailsView.getScene());
                });
      
                viewButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(viewButton);
                }
            }
        });

		
        eventTable.getColumns().addAll(idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn, organizerColumn, detailsColumn);
            
            btnHB.getChildren().addAll(delBtn, transBtn, detailsBtn);
            eventData = FXCollections.observableArrayList();
            eventTable.setItems(eventData);
	}
	
	
	public void initInvitationComponent() {
	    setTable();

	    eventContainer.getChildren().addAll(eventTitle, eventDescription, eventTable, createEventBtn, btnHB, errorM);
	    
	    VBox.setMargin(createEventBtn, new Insets(20, 0, 0, 0));
	    
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
		
		btnHB.setAlignment(Pos.CENTER);
		btnHB.setPadding(new Insets(10, 0, 0, 0));
		
		delBtn.setPadding(new Insets(10, 0, 10, 0));
		delBtn.setMinWidth(150);
		delBtn.setTextFill(Color.WHITE);
		delBtn.setBackground(new Background(new BackgroundFill(Color.web("#b20000"), CornerRadii.EMPTY, null)));
		delBtn.setFont(Font.font(15));
		
		transBtn.setPadding(new Insets(10, 0, 10, 0));
		transBtn.setMinWidth(150);
		transBtn.setTextFill(Color.WHITE);
		transBtn.setBackground(new Background(new BackgroundFill(Color.web("#133E87"), CornerRadii.EMPTY, null)));
		transBtn.setFont(Font.font(15));
		
		errorM.setTextFill(Color.RED);
	}
	
	public void invitation() {
		initInvitationComponent();
		invitationStyling();
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
		
		btnHB.setVisible(true);
		delBtn.setVisible(true);
		transBtn.setVisible(true);
		
		errorM.setVisible(true);
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
		event = new Menu("Events");
		
		iEvent = new MenuItem("Accepted Events");
		
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(event, updateProfile);
	}
	
	public void setADeleteButton(EventHandler<ActionEvent> handler) {
		delBtn.setOnAction(handler);
	}
	
	public void setATransactionButton(EventHandler<ActionEvent> handler) {
		transBtn.setOnAction(handler);
	}
	
    public void eventOrganizerButton() {
        btnHB.setVisible(true);
        delBtn.setVisible(true);
        transBtn.setVisible(true);
        eventPage.setBottom(createEventBtn);
    }
    
	public void setCreateEventButton(EventHandler<ActionEvent> handler) {
		
		 createEventBtn.setOnAction(handler);
	}
	
	public void setMDeleteButton(EventHandler<MouseEvent> handler) {
		eventTable.setRowFactory((TableView<Event> e) -> {
			row = new TableRow<>();
			row.setOnMouseClicked(handler);
			return row;
		});	
	}
	
	public void setErrorMessage(String message) {
		errorM.setText(message);
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
	
	public void setManageVendorMenu(EventHandler<ActionEvent> handler) {
		iManageVendor.setOnAction(handler);
	}
	
	public void setUserMenu(EventHandler<ActionEvent> handler) {
		iUsers.setOnAction(handler);
	}
	
	public void setEventList(ObservableList<Event> events) {
	    System.out.println("Setting event list with size: " + events.size());
	    eventData.setAll(events);
	    eventTable.setItems(eventData); 
	}
	
	public TableView<Event> getEventTable(){
	    return eventTable;
	}
	
	public ViewEvents(String email) {
		this.email = email;
		initInvitation();
		invitation();
	}
	
	public void setupCreateEventButton() {
		createEventBtn.setVisible(true);
	    createEventBtn.setText("Create a New Event");
	    createEventBtn.setStyle("-fx-background-color: #3c763d; -fx-text-fill: #ffffff; -fx-padding: 10px;");

	}
	
	public void setOrganizerId(String organizerId) {
	    this.organizerId = organizerId;
	}

	public void setPrimaryStage(Stage primaryStage) {
	    this.primaryStage = primaryStage;
	}

	public Scene getScene() {
		return eventScene;
	}
}
