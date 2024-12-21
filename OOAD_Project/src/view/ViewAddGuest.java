package view;

import controller.EventOrganizerController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Guest;

public class ViewAddGuest extends Application{

	private ViewEvents view;
	private String email;
    private EventOrganizerController eventOrganizerController = new EventOrganizerController(view, email);
    Scene scene;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Guest to Event");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label eventIdLabel = new Label("Event ID:");
        TextField eventIdField = new TextField();

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

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public Scene getScene() {
		return scene;
	}
}
