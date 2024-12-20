package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;

import java.util.ArrayList;

import controller.EventOrganizerController;

public class ViewEventDetails extends Application {

    private ViewEvents view;
	private String email;
	private EventOrganizerController eventOrganizerController = new EventOrganizerController(view, email);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("View Event Details");
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        Label eventListLabel = new Label("Organized Events:");
        ListView<String> eventListView = new ListView<>();
        ObservableList<String> eventItems = FXCollections.observableArrayList();
        ArrayList<Event> events = eventOrganizerController.viewOrganizedEvents();

        for (Event event : events) {
            eventItems.add(event.getEvent_id() + " - " + event.getEvent_name());
        }
        
        eventListView.setItems(eventItems);

        Label eventDetailsLabel = new Label("Event Details:");
        TextArea eventDetailsArea = new TextArea();
        eventDetailsArea.setEditable(false);

        eventListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String selectedEventID = newValue.split(" - ")[0];
                String details = eventOrganizerController.viewOrganizedTransactionDetails(selectedEventID);
                eventDetailsArea.setText(details);
            }
        });
        
        Label editEventLabel = new Label("Edit Event Name:");
        TextField newNameField = new TextField();
        Button updateNameButton = new Button("Update Event Name");
        Label editResultLabel = new Label();

        updateNameButton.setOnAction(e -> {
            String selectedEvent = eventListView.getSelectionModel().getSelectedItem();
            if (selectedEvent != null) {
                String selectedEventID = selectedEvent.split(" - ")[0];
                String newName = newNameField.getText();
                String result = eventOrganizerController.editEventName(selectedEventID, newName);
                editResultLabel.setText(result);
                eventItems.clear();
                for (Event event : eventOrganizerController.viewOrganizedEvents()) {
                    eventItems.add(event.getEvent_id() + " - " + event.getEvent_name());
                }
            } else {
                editResultLabel.setText("Please select an event first.");
            }
        });

        Button addVendorButton = new Button("Add Vendor");
        addVendorButton.setOnAction(e -> {
            ViewAddVendor viewAddVendor = new ViewAddVendor();
            try {
                viewAddVendor.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button addGuestButton = new Button("Add Guest");
        addGuestButton.setOnAction(e -> {
            ViewAddGuest viewAddGuest = new ViewAddGuest();
            try {
                viewAddGuest.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(eventListLabel, eventListView, eventDetailsLabel, eventDetailsArea, editEventLabel, newNameField, updateNameButton, editResultLabel, addVendorButton, addGuestButton);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
