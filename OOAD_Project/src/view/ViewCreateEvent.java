package view;

import controller.EventOrganizerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.EventOrganizer;

public class ViewCreateEvent {

    private EventOrganizerController controller;
    private Label resultLabel;
    private TextField eventNameField;
    private TextField eventDateField;
    private TextField eventLocationField;
    private TextArea eventDescriptionArea;

    public ViewCreateEvent(EventOrganizerController controller) {
        this.controller = controller;
    }

    public void display(Stage stage) {
        eventNameField = new TextField();
        eventNameField.setPromptText("Event Name");

        eventDateField = new TextField();
        eventDateField.setPromptText("Event Date (YYYY-MM-DD)");

        eventLocationField = new TextField();
        eventLocationField.setPromptText("Event Location");

        eventDescriptionArea = new TextArea();
        eventDescriptionArea.setPromptText("Event Description");
        eventDescriptionArea.setWrapText(true);
        eventDescriptionArea.setPrefRowCount(3);

        Button createButton = new Button("Create Event");
        createButton.setOnAction(e -> handleCreateEvent());

        resultLabel = new Label("");

        VBox formLayout = new VBox(10, eventNameField, eventDateField, eventLocationField, eventDescriptionArea, createButton, resultLabel);
        formLayout.setAlignment(Pos.CENTER);
        formLayout.setPadding(new Insets(20));

        Scene scene = new Scene(formLayout, 400, 350);
        stage.setTitle("Create Event");
        stage.setScene(scene);
        stage.show();
    }

    private void handleCreateEvent() {
        String name = eventNameField.getText().trim();
        String date = eventDateField.getText().trim();
        String location = eventLocationField.getText().trim();
        String description = eventDescriptionArea.getText().trim();

        if (name.isEmpty() || date.isEmpty() || location.isEmpty() || description.isEmpty()) {
            resultLabel.setText("All fields are required.");
            return;
        }

        String result = controller.createEvent(name, date, location, description);
        resultLabel.setText(result);
    }
}
