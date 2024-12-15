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
		invitation = new Menu("Invitations");
		event = new Menu("Events");
		updateProfile = new Menu("Update Profile");
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		iUpdateProfile = new MenuItem("Update Profile");
		
//		tambahin if else untuk element menu vendor
		String role = new UserController().getUser().getUser_role();
		if (role.equalsIgnoreCase("Vendor")) {
			manageVendor = new Menu("Manage Vendor");
			iManageVendor = new MenuItem("Manage Vendor");
		}
	}
	
	public void setTable() {
		InvitationController iController = new InvitationController();
//		UserController uController = new UserController();
		ArrayList<Event> invitation = iController.getInvitations(this.email);
		
		ObservableList<Event> invitationData = FXCollections.observableArrayList(invitation);
		
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
		
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		if(iManageVendor != null) {
			manageVendor.getItems().addAll(iManageVendor);
			menubar.getMenus().addAll(invitation, event, updateProfile, manageVendor);
		}
		menubar.getMenus().addAll(invitation, event, updateProfile);
		
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
	
	public void invitation() {
		initInvitationComponent();
		invitationStyling();
	}

	public void setEventHandler() {
		acceptBtn.setOnAction(e -> {
			Event selectedInvitation = invitationTable.getSelectionModel().getSelectedItem();
			if(selectedInvitation != null) {
				invitationEM.setText("");
				String eventID = selectedInvitation.getEvent_id();
				String role = new UserController().getUser().getUser_role();
				
				if(role.equalsIgnoreCase("Vendor")) {
					VendorController vController = new VendorController();
					vController.acceptInvitation(eventID);
				}
				else if (role.equalsIgnoreCase("Guest")) {
					GuestController gController = new GuestController();
					gController.acceptInvitation(eventID);
				}
				setTable();
				
			}else {
				invitationEM.setText("Choose the invitation bellow");
			}
			System.out.println("pressed");
		});
		

		iEvent.setOnAction(e -> {
			ViewEvents view = new ViewEvents(this.email);
			String role = new UserController().getUser().getUser_role();
			
			if(role.equalsIgnoreCase("Vendor")) {
				VendorController vController = new VendorController(view, email);
			}
			else if (role.equalsIgnoreCase("Guest")) {
				GuestController gController = new GuestController(view, email);
			}
			Main.redirect(view.eventScene);
		});
		
		iUpdateProfile.setOnAction(e -> {
			ViewChangeProfile view = new ViewChangeProfile();
			UserController uController = new UserController(view, email);
			Main.redirect(view.updateProfileScene);
		});
		
//		setonaction click menuitem tambahan vendor
		iManageVendor.setOnAction(e ->{
			
		});
	}
	
	public ViewInvitation(String email) {
		this.email = email;
		
		initInvitation();
		invitation();
		setEventHandler();
		Main.redirect(invitationScene);
	}

	public Scene getScene() {
		return invitationScene;
	}
	
}
