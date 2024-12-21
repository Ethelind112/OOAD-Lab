package view;

import controller.EventOrganizerController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Guest;

public class ViewAddGuest {

    private Scene scene;
    private String email;
    private String eventID;
    private EventOrganizerController eventOrganizerController;

    public ViewAddGuest() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label eventIdLabel = new Label("Event ID:");
        TextField eventIdField = new TextField();
        eventIdField.setText(eventID); 
        eventIdField.setEditable(false); 

        Label guestIdLabel = new Label("Guest ID:");
        TextField guestIdField = new TextField();

        Button addButton = new Button("Add Guest");
        Label resultLabel = new Label();

        grid.add(eventIdLabel, 0, 0);
        grid.add(eventIdField, 1, 0);
        grid.add(guestIdLabel, 0, 1);
        grid.add(guestIdField, 1, 1);
        grid.add(addButton, 1, 2);
        grid.add(resultLabel, 1, 3);

        addButton.setOnAction(e -> {
            String eventId = eventIdField.getText();
            String guestId = guestIdField.getText();

            Guest guest = new Guest();
            guest.setUser_id(guestId);

            String result = eventOrganizerController.addGuest(eventId, guest);
            resultLabel.setText(result);
        });

        this.scene = new Scene(grid, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }

    public void setEmail(String email) {
        this.email = email;
        this.eventOrganizerController = new EventOrganizerController(null, email);
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}
