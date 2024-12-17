package view;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ViewAddGuest {

    Scene addGuestScene;
    VBox addGuestVB, addGuestContainer;
    Label addGuestTitle, addGuestEM, haveAcc;
    GridPane addGuestFill;
    Button addGuestBtn, toLogin;
    TextField nameField, emailField;
    Label nameLbl, emailLbl;
    ComboBox<String> genderCB;
    HBox bottomAuth;

    public void initAddGuest() {
        addGuestVB = new VBox();
        addGuestContainer = new VBox();
        addGuestScene = new Scene(addGuestVB, 1000, 700);

        addGuestTitle = new Label("Add Guest");
        addGuestFill = new GridPane();

        nameLbl = new Label("Name");
        nameField = new TextField();

        emailLbl = new Label("Email");
        emailField = new TextField();

        genderCB = new ComboBox<>();
        genderCB.getItems().addAll("Male", "Female", "Other");

        addGuestBtn = new Button("Add Guest");

        bottomAuth = new HBox();
        addGuestEM = new Label();

        setEventHandler();
    }

    public void initAddGuestComponent() {
        addGuestFill.add(nameLbl, 0, 0);
        addGuestFill.add(nameField, 1, 0);

        addGuestFill.add(emailLbl, 0, 2);
        addGuestFill.add(emailField, 1, 2);

        addGuestFill.add(new Label("Gender"), 0, 4);
        addGuestFill.add(genderCB, 1, 4);

        addGuestContainer.getChildren().addAll(addGuestTitle, addGuestFill, addGuestEM, addGuestBtn);
        addGuestVB.getChildren().add(addGuestContainer);
    }

    public void addGuestStyling() {
        addGuestVB.setAlignment(Pos.CENTER);
        addGuestVB.setBackground(new Background(new BackgroundFill(Color.web("#133E87"), CornerRadii.EMPTY, null)));

        addGuestContainer.setAlignment(Pos.CENTER); 
        addGuestContainer.setMaxWidth(500);
        addGuestContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));

        addGuestTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        addGuestContainer.setMargin(addGuestTitle, new Insets(50, 0, 30, 0));

        nameLbl.setFont(Font.font(15));
        nameField.setMinWidth(300);

        emailLbl.setFont(Font.font(15));
        emailField.setMinWidth(300);

        genderCB.setMinWidth(300);

        addGuestFill.setAlignment(Pos.CENTER); 
        addGuestFill.setVgap(10);
        addGuestFill.setHgap(30);
        addGuestFill.setPadding(new Insets(10));

        addGuestBtn.setPadding(new Insets(10, 0, 10, 0));
        addGuestBtn.setMinWidth(100);
        addGuestBtn.setTextFill(Color.WHITE);
        addGuestBtn.setBackground(new Background(new BackgroundFill(Color.web("#133E87"), CornerRadii.EMPTY, null)));
        addGuestBtn.setFont(Font.font(15));

        addGuestContainer.setMargin(addGuestBtn, new Insets(20, 0, 0, 0));
    }

    public void setEventHandler() {
        addGuestBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameField.getText();
                String email = emailField.getText();
                String gender = genderCB.getValue();

                if (name.isEmpty() || email.isEmpty() || gender == null) {
                    addGuestEM.setText("All fields are required!");
                    addGuestEM.setTextFill(Color.RED);
                    return;
                }

                UserController uController = new UserController();

                String message = uController.register(email, name, email, gender);

                addGuestEM.setText(message);
                if (message.equals("Guest added successfully!")) {
                    nameField.clear();
                    emailField.clear();
                    genderCB.setValue(null);
                }
            }
        });
    }

    public Scene getScene() {
        return addGuestScene;
    }

    public ViewAddGuest() {
        initAddGuest();
        initAddGuestComponent();
        addGuestStyling();
    }
}
