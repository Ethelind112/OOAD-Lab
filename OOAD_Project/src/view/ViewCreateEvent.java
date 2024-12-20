package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewCreateEvent {
    
    Scene createEventScene;
    VBox ceContainer, middleContainer;
    BorderPane cePage;
    Label ceTitle, ceEM;
    GridPane ceFill;
    Button createBtn;
    String email;
    
    Label eventNameLbl, eventDateLbl, eventLocationLbl, eventDescLbl;
    TextField eventNameField, eventDateField, eventLocationField, eventDescField;
	Stage primaryStage;
    
    public void initCreateEvent() {
        cePage = new BorderPane();
        ceContainer = new VBox();
        middleContainer = new VBox();
        createEventScene = new Scene(cePage, 1000, 700);
        ceTitle = new Label("Create New Event");
        ceEM = new Label();
        ceFill = new GridPane();
        
        eventNameLbl = new Label("Event Name");
        eventDateLbl = new Label("Event Date");
        eventLocationLbl = new Label("Event Location");
        eventDescLbl = new Label("Event Description");
        
        eventNameField = new TextField();
        eventDateField = new TextField();
        eventLocationField = new TextField();
        eventDescField = new TextField();
        
        createBtn = new Button("Create");
    }
    
    public void initCreateEventComponent() {
        ceFill.add(eventNameLbl, 0, 0);
        ceFill.add(eventNameField, 1, 0);
        
        ceFill.add(eventDateLbl, 0, 1);
        ceFill.add(eventDateField, 1, 1);
        
        ceFill.add(eventLocationLbl, 0, 2);
        ceFill.add(eventLocationField, 1, 2);
        
        ceFill.add(eventDescLbl, 0, 3);
        ceFill.add(eventDescField, 1, 3);
        
        ceContainer.getChildren().addAll(ceTitle, ceFill, ceEM, createBtn);
        middleContainer.getChildren().add(ceContainer);
        cePage.setCenter(middleContainer);
    }
    
    public void createEventStyling() {
        cePage.setStyle("-fx-background-color: #133E87;");
        
        middleContainer.setAlignment(Pos.CENTER);
        ceContainer.setAlignment(Pos.CENTER);
        ceContainer.setMaxWidth(800);
        ceContainer.setStyle("-fx-background-color: white;");
        
        ceTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        ceContainer.setMargin(ceTitle, new Insets(50,0,30,0));
        
        String labelStyle = "-fx-font-size: 15;";
        eventNameLbl.setStyle(labelStyle);
        eventDateLbl.setStyle(labelStyle);
        eventLocationLbl.setStyle(labelStyle);
        eventDescLbl.setStyle(labelStyle);
        
        String fieldStyle = "-fx-background-color: white; -fx-border-color: #e6e6e6";
        eventNameField.setStyle(fieldStyle);
        eventDateField.setStyle(fieldStyle);
        eventLocationField.setStyle(fieldStyle);
        eventDescField.setStyle(fieldStyle);
        
        eventNameField.setMinWidth(300);
        eventDateField.setMinWidth(300);
        eventLocationField.setMinWidth(300);
        eventDescField.setMinWidth(300);
        
        ceFill.setAlignment(Pos.CENTER);
        ceFill.setVgap(10);
        ceFill.setHgap(30);
        ceFill.setPadding(new Insets(10));
        
        createBtn.setPadding(new Insets(10, 0, 10, 0));
        createBtn.setMinWidth(100);
        createBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
        createBtn.setFont(Font.font(15));
        
        ceEM.setTextFill(Color.RED);
        
        ceContainer.setMargin(createBtn, new Insets(30, 0, 50, 0));
    }
    
    
    
    public void createEvent() {
        initCreateEventComponent();
        createEventStyling();
    }
    
    public ViewCreateEvent(String email) {
        this.email = email;
        initCreateEvent();
        createEvent();
    }
    
    public void setCreateButton(EventHandler<ActionEvent> handler) {
        createBtn.setOnAction(handler);
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
        return eventDescField.getText();
    }
    
    public void setErrorMessage(String message) {
        ceEM.setText(message);
    }
    
    public Scene getScene() {
        return createEventScene;
    }
}
