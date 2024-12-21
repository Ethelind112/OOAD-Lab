package view;

import java.util.ArrayList;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;

public class ViewEditEvent {

	private TextField eventNameField = new TextField();
    private Label errorLabel = new Label();

    private Scene scene;
    private TableView<Event> eventTable = new TableView<>();
    private ObservableList<Event> eventData = FXCollections.observableArrayList();
    
    TableRow<Event> row;

    public ViewEditEvent() {
        VBox layout = new VBox(10);

        eventNameField.setPromptText("Enter Event Name");

        layout.getChildren().addAll(
                eventNameField,
                errorLabel,
                eventTable
        );

        setupEventTable();

        scene = new Scene(layout, 600, 500);
    }

    public void setCreateButton(EventHandler<ActionEvent> handler) {
        Button createButton = new Button("Edit Event");
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

    public String getOrganizerId() {
        return generateRandomOrganizerId(); 
    }

    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }
    
    public void setEventName(String name) {
    	eventNameField.setText(name);
    }
    
    public void setEventDetailButton(EventHandler<MouseEvent> handler) {
		eventTable.setRowFactory((TableView<Event> e) -> {
			row = new TableRow<>();
			row.setOnMouseClicked(handler);
			return row;
		});
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
    
    public String getName() {
    	return eventNameField.getText();
    }
    
    public TableView<Event> getEventTable(){
    	return eventTable;
    }
    
    public void setEventList(ObservableList<Event> events) {
	    eventData.setAll(events);
	    eventTable.setItems(eventData); 
	}

}
