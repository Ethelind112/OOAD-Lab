package view;

import controller.EventOrganizerController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewEditEventName {

    private EventOrganizerController controller;
    private Stage primaryStage;
    
    public ViewEditEventName(EventOrganizerController controller, Stage stage) {
        this.controller = controller;
        this.primaryStage = stage;
        initialize();
    }

    private void initialize() {
        Label eventIDLabel = new Label("Event ID:");
        Label newEventNameLabel = new Label("New Event Name:");
        TextField eventIDField = new TextField();
        TextField newEventNameField = new TextField();
        Label resultLabel = new Label();
        Button editButton = new Button("Edit Event Name");

        editButton.setOnAction(event -> {
            String eventID = eventIDField.getText().trim();
            String newName = newEventNameField.getText().trim();
            String result = controller.editEventName(eventID, newName);
            resultLabel.setText(result);
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #f4f4f4;");
    
        layout.getChildren().addAll(eventIDLabel, eventIDField, newEventNameLabel, newEventNameField, editButton, resultLabel);

        eventIDLabel.setFont(new Font("Arial", 14));
        newEventNameLabel.setFont(new Font("Arial", 14));
        editButton.setFont(new Font("Arial", 14));

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Edit Event Name");
        primaryStage.show();
    }
}
