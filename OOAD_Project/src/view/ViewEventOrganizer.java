package view;

import controller.EventOrganizerController;
import model.EventOrganizer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewEventOrganizer extends Application {

    private EventOrganizerController controller;

    public ViewEventOrganizer(EventOrganizer organizer) {
        this.controller = new EventOrganizerController(organizer);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        VBox navigation = new VBox(10); 
        navigation.setAlignment(Pos.CENTER);
        
        // Create buttons for each section
        Button viewOrganizedEventsButton = new Button("View Organized Events");
        Button viewTransactionDetailsButton = new Button("View Transaction Details");
        Button addVendorButton = new Button("Add Vendor");
        Button addGuestButton = new Button("Add Guest");
        Button editEventNameButton = new Button("Edit Event Name");
        Button createEventButton = new Button("Create Event");

        viewOrganizedEventsButton.setOnAction(e -> showView(new ViewOrganizedEvents(controller)));
        viewTransactionDetailsButton.setOnAction(e -> showView(new ViewTransactionDetails(controller)));
        addVendorButton.setOnAction(e -> showView(new ViewAddVendor(controller)));
        addGuestButton.setOnAction(e -> showView(new ViewAddGuest())); // Navigate to ViewAddGuest
        editEventNameButton.setOnAction(e -> showView(new ViewEditEventName(controller)));
        createEventButton.setOnAction(e -> showView(new ViewCreateEvent(controller)));

        navigation.getChildren().addAll(
                createEventButton,
                addVendorButton,
                addGuestButton,
                editEventNameButton,
                viewOrganizedEventsButton,
                viewTransactionDetailsButton
        );

        root.setLeft(navigation);

        root.setCenter(new ViewCreateEvent(controller));

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Event Organizer Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showView(javafx.scene.layout.Region view) {
        BorderPane root = (BorderPane) this.getScene().getRoot();
        root.setCenter(view);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
