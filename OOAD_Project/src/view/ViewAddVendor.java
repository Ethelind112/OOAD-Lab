package view;

import controller.EventOrganizerController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Vendor;

public class ViewAddVendor extends Application {
	
	
    private ViewEvents view;
	private String email;
	private String eventID;
	private EventOrganizerController eventOrganizerController = new EventOrganizerController(view, email);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Vendor to Event");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label eventIdLabel = new Label("Event ID:");
        TextField eventIdField = new TextField();

        Label vendorIdLabel = new Label("Vendor ID:");
        TextField vendorIdField = new TextField();

        Button addButton = new Button("Add Vendor");
        Label resultLabel = new Label();

        grid.add(eventIdLabel, 0, 0);
        grid.add(eventIdField, 1, 0);
        grid.add(vendorIdLabel, 0, 1);
        grid.add(vendorIdField, 1, 1);
        grid.add(addButton, 1, 2);
        grid.add(resultLabel, 1, 3);

        addButton.setOnAction(e -> {
            String eventId = eventIdField.getText();
            String vendorId = vendorIdField.getText();

            Vendor vendor = new Vendor();
            vendor.setUser_id(vendorId);

            String result = eventOrganizerController.addVendor(eventId, vendor);
            resultLabel.setText(result);
        });

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}
}
