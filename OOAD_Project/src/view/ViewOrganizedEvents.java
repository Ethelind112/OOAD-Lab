package view;

import controller.EventOrganizerController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;
import model.EventOrganizer;

import java.util.List;

public class ViewOrganizedEvents {

    private EventOrganizerController controller;
    private Stage stage;

    public ViewOrganizedEvents(EventOrganizer eventOrganizer, Stage stage) {
        this.controller = new EventOrganizerController(eventOrganizer);
        this.stage = stage;
    }

    public void displayOrganizedEvents() {
        List<Event> events = controller.viewOrganizedEvents();

        ListView<String> eventsListView = new ListView<>();
        for (Event event : events) {
            eventsListView.getItems().add(event.getEvent_name() + " | " + event.getEvent_date());
        }

        if (events.isEmpty()) {
            eventsListView.getItems().add("No events found.");
        }

        VBox eventListLayout = new VBox(10, eventsListView);
        eventListLayout.setAlignment(Pos.CENTER);

        Scene eventListScene = new Scene(eventListLayout, 400, 300);
        stage.setTitle("My Organized Events");
        stage.setScene(eventListScene);
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
