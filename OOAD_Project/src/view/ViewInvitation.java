package view;

import java.util.ArrayList;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;
import model.Invitation;

public class ViewInvitation implements EventHandler<ActionEvent> {

	Scene invitationScene;
	VBox invitationVb, invitationContainer;
	Label invitationTitle, invitationDescription, invitationEM;
	TableView<Event> invitationTable;
	Button acceptBtn;
	
	public void initInvitation() {
		invitationVb = new VBox();
		invitationContainer = new VBox();
		invitationScene = new Scene(invitationVb, 1000, 700);
		invitationTitle = new Label("Invitation");
		invitationDescription = new Label("This is your invitation, select the invitation from the table bellow before clicking on the accept button.");
		invitationEM = new Label("");
		acceptBtn = new Button("Accept");
		invitationTable = new TableView<>();
	}
	
	public void setTable() {
		InvitationController iController = new InvitationController();
		UserController uController = new UserController();
		ArrayList<Event> invitation = iController.getInvitations(uController.getUser().getUser_email());
		
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
		
		invitationContainer.getChildren().addAll(invitationTitle, invitationDescription, acceptBtn, invitationEM, invitationTable);
		invitationVb.getChildren().add(invitationContainer);
	}
	
	public void invitationStyling(){
		invitationVb.setAlignment(Pos.TOP_CENTER); //menengahkan posisi container
		invitationVb.setStyle("-fx-background-color: white;");
	
		invitationContainer.setMaxWidth(900);
		
		invitationTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		invitationContainer.setMargin(invitationTitle, new Insets(50,0,10,0));
		invitationContainer.setAlignment(Pos.TOP_CENTER);
		
		acceptBtn.setPadding(new Insets(10, 0, 10, 0));
		acceptBtn.setMinWidth(100);
		acceptBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		acceptBtn.setFont(Font.font(15));
		
		invitationContainer.setMargin(acceptBtn, new Insets(20, 0, 10, 0));
		invitationContainer.setMargin(invitationEM, new Insets(0, 0, 30, 0));
	}
	
	public void invitation() {
		initInvitationComponent();
		invitationStyling();
	}

	public void setEventHandler() {
		acceptBtn.setOnAction(this);
	}
	
	public ViewInvitation() {
		initInvitation();
		invitation();
		setEventHandler();
		Main.redirect(invitationScene);
	}

	@Override
	public void handle(ActionEvent event) {
		acceptBtn.setOnAction(e -> {
			Event selectedInvitation = invitationTable.getSelectionModel().getSelectedItem();
			if(selectedInvitation != null) {
				invitationEM.setText("");
				String eventID = selectedInvitation.getEvent_id();
				GuestController gController = new GuestController();
				gController.acceptInvitation(eventID);
				setTable();
				
			}else {
				invitationEM.setText("Choose the invitation bellow");
			}
			System.out.println("pressed");
		});
	}

}
