package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ViewChangeEventName {
    private VBox root;
    private TextField eventNameField;
    private Button changeButton;
    private Label errorLabel;
    private Scene scene;

    public ViewChangeEventName() {
        root = new VBox(10);
        root.setPadding(new Insets(10));

        Label label = new Label("Edit Event Name");
        eventNameField = new TextField();
        changeButton = new Button("Save");
        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        root.getChildren().addAll(label, eventNameField, changeButton, errorLabel);
        scene = new Scene(root, 300, 200);
    }

    public void setChangeButton(EventHandler<ActionEvent> handler) {
        changeButton.setOnAction(handler);
    }

    public String getEventName() {
        return eventNameField.getText();
    }

    public void setErrorMessage(String msg) {
        errorLabel.setText(msg);
    }

    public Scene getScene() {
        return scene;
    }
}
