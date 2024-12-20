package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;

import java.util.ArrayList;

import controller.EventOrganizerController;

public class ViewEventDetails extends Application {

    private ViewEvents view;
    private String email;
    private EventOrganizerController eventOrganizerController = new EventOrganizerController(view, email);
    private ObservableList<Event> eventItems;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("View Event Details");
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        Label eventListLabel = new Label("Organized Events:");
        ListView<Event> eventListView = new ListView<>();
        eventItems = FXCollections.observableArrayList();
        ArrayList<Event> events = eventOrganizerController.viewOrganizedEvents();
        eventItems.addAll(events);

        eventListView.setCellFactory(lv -> new ListCell<Event>() {
            private TextField editField = new TextField();
            private Button editButton = new Button("Edit");
            private HBox content = new HBox(10);
            private Label eventLabel = new Label();

            {
                content.setAlignment(Pos.CENTER_LEFT);
                editButton.setOnAction(event -> {
                    Event item = getItem();
                    if (item != null) {
                        if (content.getChildren().contains(editButton)) {
                            editField.setText(item.getEvent_name());
                            content.getChildren().clear();
                            Button saveButton = new Button("Save");
                            Button cancelButton = new Button("Cancel");
                            
                            saveButton.setOnAction(e -> {
                                String newName = editField.getText();
                                String result = eventOrganizerController.editEventName(item.getEvent_id(), newName);
                                if (result.contains("successfully")) {
                                    item.setEvent_name(newName);
                                    updateItem(item, false);
                                }
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText(result);
                                alert.showAndWait();
                            });
                            
                            cancelButton.setOnAction(e -> {
                                updateItem(item, false);
                            });
                            
                            content.getChildren().addAll(editField, saveButton, cancelButton);
                            HBox.setHgrow(editField, Priority.ALWAYS);
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Event item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    eventLabel.setText(item.getEvent_id() + " - " + item.getEvent_name());
                    if (!content.getChildren().contains(editField)) {
                        content.getChildren().clear();
                        content.getChildren().addAll(eventLabel, editButton);
                    }
                    setGraphic(content);
                }
            }
        });
        
        eventListView.setItems(eventItems);

        Label eventDetailsLabel = new Label("Event Details:");
        TextArea eventDetailsArea = new TextArea();
        eventDetailsArea.setEditable(false);

        eventListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String details = eventOrganizerController.viewOrganizedTransactionDetails(newValue.getEvent_id());
                eventDetailsArea.setText(details);
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

        root.getChildren().addAll(
            eventListLabel, 
            eventListView, 
            eventDetailsLabel, 
            eventDetailsArea, 
            addVendorButton, 
            addGuestButton
        );

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
