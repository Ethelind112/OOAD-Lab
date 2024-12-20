package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Event;

public class ViewEvents {
    private String organizerId;

    Scene eventsScene;
    VBox evContainer, middleContainer;
    BorderPane evPage;
    Label evTitle;
    Button createEventBtn;

    TableView<Event> eventTable;
    TableColumn<Event, String> eventIdCol;
    TableColumn<Event, String> eventNameCol;
    TableColumn<Event, String> eventDateCol;
    TableColumn<Event, String> eventLocationCol;
    TableColumn<Event, Void> actionCol; 

    public ViewEvents(String organizerId) {
        this.organizerId = organizerId;
        initEvents();
        events();
    }

    public void initEvents() {
        evPage = new BorderPane();
        evContainer = new VBox();
        middleContainer = new VBox();
        eventsScene = new Scene(evPage, 1000, 700);
        evTitle = new Label("Event Management");

        eventTable = new TableView<>();
        eventIdCol = new TableColumn<>("Event ID");
        eventNameCol = new TableColumn<>("Event Name");
        eventDateCol = new TableColumn<>("Event Date");
        eventLocationCol = new TableColumn<>("Event Location");

        createEventBtn = new Button("Create Event");
    }

    public void initEventsComponent() {
        eventTable.getColumns().addAll(eventIdCol, eventNameCol, eventDateCol, eventLocationCol);

        actionCol = new TableColumn<>("Action");
        actionCol.setCellFactory(col -> {
            return new TableCell<Event, Void>() {
                private Button viewDetailsBtn = new Button("View Event Details");

                {
                    viewDetailsBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
                    viewDetailsBtn.setPadding(new Insets(5, 10, 5, 10));
                    viewDetailsBtn.setFont(Font.font(12));

                    viewDetailsBtn.setOnAction((ActionEvent event) -> {
                        Event currentEvent = getTableView().getItems().get(getIndex());
                        ViewEventDetails detailsView = new ViewEventDetails();
                        Stage detailsStage = new Stage();
                        try {
                            detailsView.start(detailsStage);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(viewDetailsBtn);
                    }
                }
            };
        });

        eventTable.getColumns().add(actionCol);

        evContainer.getChildren().addAll(evTitle, eventTable, createEventBtn);
        middleContainer.getChildren().add(evContainer);
        evPage.setCenter(middleContainer);
    }

    public void eventsStyling() {
        evPage.setStyle("-fx-background-color: #133E87;");

        middleContainer.setAlignment(Pos.CENTER);
        evContainer.setAlignment(Pos.CENTER);
        evContainer.setMaxWidth(800);
        evContainer.setStyle("-fx-background-color: white;");

        evTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        evContainer.setMargin(evTitle, new Insets(50,0,30,0));

        eventTable.setMinWidth(700);
        eventTable.setMaxHeight(300);
        evContainer.setMargin(eventTable, new Insets(0,0,30,0));

        createEventBtn.setPadding(new Insets(10, 0, 10, 0));
        createEventBtn.setMinWidth(100);
        createEventBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
        createEventBtn.setFont(Font.font(15));

        evContainer.setMargin(createEventBtn, new Insets(30, 0, 50, 0));
    }

    public void events() {
        initEventsComponent();
        eventsStyling();
    }

    public void setCreateEventButton(EventHandler<ActionEvent> handler) {
        createEventBtn.setOnAction(handler);
    }

    public Scene getScene() {
        return eventsScene;
    }

    public TableView<Event> getEventTable() {
        return eventTable;
    }
}
