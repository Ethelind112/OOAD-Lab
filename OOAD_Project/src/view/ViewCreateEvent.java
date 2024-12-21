package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;

import java.util.ArrayList;
import java.util.Random;

public class ViewCreateEvent {

    private TextField eventNameField = new TextField();
    private TextField eventDateField = new TextField();
    private TextField eventLocationField = new TextField();
    private TextField eventDescriptionField = new TextField();
    private Label errorLabel = new Label();

    private Scene scene;
    private TableView<Event> eventTable = new TableView<>();
    private ObservableList<Event> eventData = FXCollections.observableArrayList();

    public ViewCreateEvent() {
        VBox layout = new VBox(10);

        eventNameField.setPromptText("Enter Event Name");
        eventDateField.setPromptText("Enter Event Date");
        eventLocationField.setPromptText("Enter Event Location");
        eventDescriptionField.setPromptText("Enter Event Description");

        layout.getChildren().addAll(
                eventNameField,
                eventDateField,
                eventLocationField,
                eventDescriptionField,
                errorLabel,
                eventTable
        );

        setupEventTable();

        scene = new Scene(layout, 600, 500);
    }

    public void setCreateButton(EventHandler<ActionEvent> handler) {
        Button createButton = new Button("Create Event");
        createButton.setOnAction(handler);

        ((VBox) scene.getRoot()).getChildren().add(createButton); 
    }

    private void setupEventTable() {
        TableColumn<Event, String> idColumn = new TableColumn<>("Event ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("event_id"));

        TableColumn<Event, String> nameColumn = new TableColumn<>("Event Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("event_name"));

        TableColumn<Event, String> dateColumn = new TableColumn<>("Event Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("event_date"));

        TableColumn<Event, String> locationColumn = new TableColumn<>("Event Location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("event_location"));

        TableColumn<Event, String> descriptionColumn = new TableColumn<>("Event Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("event_description"));

        eventTable.getColumns().addAll(idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn);

        refreshEventTable();
    }

    private void refreshEventTable() {
        Event eventModel = new Event();
        ArrayList<Event> events = eventModel.fetchEvents(null); // Fetch all events

        if (events != null) {
            eventData.setAll(events);
            eventTable.setItems(eventData);
        } else {
            errorLabel.setText("Failed to load event data.");
        }
    }

    private String generateRandomOrganizerId() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000) + 10000; 
        return "ORG" + randomNumber;
    }

    public String getEventName() {
        return eventNameField.getText();
    }

    public String getEventDate() {
        return eventDateField.getText();
    }

    public String getEventLocation() {
        return eventLocationField.getText();
    }

    public String getEventDescription() {
        return eventDescriptionField.getText();
    }

    public String getOrganizerId() {
        return generateRandomOrganizerId(); 
    }

    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    public Scene getScene() {
        return scene;
    }

    public void display() {
        Stage stage = new Stage();
        stage.setTitle("Create Event");
        stage.setScene(scene);
        stage.show();
    }
    
    public TableView<Event> getEventTable(){
    	return eventTable;
    }
    
    public void setEventList(ObservableList<Event> events) {
	    eventData.setAll(events);
	    eventTable.setItems(eventData); 
	}
}
