package view;

import controller.EventOrganizerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Event;

public class ViewEventDetails {

    private String email;
    private EventOrganizerController eventOrganizerController;
    private Event selectedEvent;

    private Scene eventDetailsScene;
    private BorderPane mainLayout;
    private VBox container;
    private GridPane detailsGrid;

    private Label pageTitle;
    private Label eventNameLbl, eventDateLbl, eventLocationLbl, eventDescLbl;
    private TextField eventNameField, eventDateField, eventLocationField;
    private TextArea eventDescArea;
    private Label errorMessage;

    private Button addVendorBtn, addGuestBtn, saveChangesBtn;

    public ViewEventDetails(String email) {
        this.email = email;

        initUI();
        createLayout();
        styleUI();
        populateEventDetails();
    }

    private void initUI() {
        mainLayout = new BorderPane();
        eventDetailsScene = new Scene(mainLayout, 600, 500);

        container = new VBox();
        detailsGrid = new GridPane();

        pageTitle = new Label("Event Details");

        eventNameLbl = new Label("Event Name:");
        eventDateLbl = new Label("Event Date:");
        eventLocationLbl = new Label("Event Location:");
        eventDescLbl = new Label("Event Description:");

        eventNameField = new TextField();
        eventDateField = new TextField();
        eventLocationField = new TextField();
        eventDescArea = new TextArea();
        
        errorMessage = new Label();

        addVendorBtn = new Button("Add Vendor");
        addGuestBtn = new Button("Add Guest");
        saveChangesBtn = new Button("Save Changes");
    }

    private void createLayout() {
        detailsGrid.add(eventNameLbl, 0, 0);
        detailsGrid.add(eventNameField, 1, 0);

        detailsGrid.add(eventDateLbl, 0, 1);
        detailsGrid.add(eventDateField, 1, 1);

        detailsGrid.add(eventLocationLbl, 0, 2);
        detailsGrid.add(eventLocationField, 1, 2);

        detailsGrid.add(eventDescLbl, 0, 3);
        detailsGrid.add(eventDescArea, 1, 3);

        container.getChildren().addAll(pageTitle, detailsGrid, errorMessage);

        HBox buttonBox = new HBox(20, addVendorBtn, addGuestBtn, saveChangesBtn);
        buttonBox.setAlignment(Pos.CENTER);

        mainLayout.setCenter(container);
        mainLayout.setBottom(buttonBox);
    }

    private void styleUI() {
        mainLayout.setStyle("-fx-background-color: #f5f5f5;");

        container.setAlignment(Pos.CENTER);
        container.setMaxWidth(500);
        container.setSpacing(20);
        container.setPadding(new Insets(20));

        detailsGrid.setAlignment(Pos.CENTER);
        detailsGrid.setHgap(20);
        detailsGrid.setVgap(15);

        pageTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        errorMessage.setTextFill(Color.RED);

        eventNameField.setMinWidth(250);
        eventDateField.setMinWidth(250);
        eventLocationField.setMinWidth(250);
        eventDescArea.setMinWidth(250);
        eventDescArea.setMinHeight(100);

        addVendorBtn.setStyle("-fx-background-color: #133E87; -fx-text-fill: white; -fx-font-size: 15;");
        addGuestBtn.setStyle("-fx-background-color: #133E87; -fx-text-fill: white; -fx-font-size: 15;");
        saveChangesBtn.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 15;");
    }

    private void populateEventDetails() {
        if (selectedEvent != null) {
            eventNameField.setText(selectedEvent.getEvent_name());
            eventDateField.setText(selectedEvent.getEvent_date());
            eventLocationField.setText(selectedEvent.getEvent_location());
            eventDescArea.setText(selectedEvent.getEvent_description());
        }
    }

    public Scene getScene() {
        return eventDetailsScene;
    }

    public void show(Stage stage) {
        stage.setScene(getScene());
        stage.show();
    }

    public void setAddVendorHandler(EventHandler<ActionEvent> handler) {
        addVendorBtn.setOnAction(handler);
    }

    public void setAddGuestHandler(EventHandler<ActionEvent> handler) {
        addGuestBtn.setOnAction(handler);
    }

    public void setSaveChangesHandler(EventHandler<ActionEvent> handler) {
        saveChangesBtn.setOnAction(handler);
    }

    public void setErrorMessage(String message) {
        errorMessage.setText(message);
    }

    public String getEventName() {
        return eventNameField.getText();
    }

    public String getEventDate() {
        return eventDateField.getText();
    }

    public String getEventLocation() {
        return eventLocationField.getText();
    }

    public String getEventDescription() {
        return eventDescArea.getText();
    }
}
