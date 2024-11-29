package view;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ViewChangeProfile implements EventHandler<ActionEvent> {
	
	Scene updateProfileScene;
	VBox uPVb, uPContainer;
	Label uPTitle, uPEM;
	GridPane uPFill;
	Button uPBtn;
	Label emailLblUP, usernameLblUP, passLblUP, newPassLblUP;
	TextField emailFieldUP, usernameFieldUP;
	PasswordField passFieldUP, newPassFieldUP;
	
	public void initUpdateProfile() {
		UserController uController = new UserController();
		
		uPVb = new VBox();
		uPContainer = new VBox();
		updateProfileScene = new Scene(uPVb, 1000, 700);
		uPTitle = new Label("Update Profile");
		uPEM = new Label();
		uPFill = new GridPane();
		
		emailLblUP = new Label("Email");
		passLblUP = new Label("Password");
		usernameLblUP = new Label("Username");
		
		newPassLblUP = new Label("New Password");
		
		usernameFieldUP = new TextField();
		emailFieldUP = new TextField();
		passFieldUP = new PasswordField();
		newPassFieldUP = new PasswordField();
		
		usernameFieldUP.setText(uController.getUser().getUser_email());
		System.out.println(uController.getUser().getUser_email());
		
		uPBtn = new Button("Update");
		
	}
	
	public void initUpdateProfileComponent() {
		uPFill.add(emailLblUP, 0, 0);
		uPFill.add(emailFieldUP, 1, 0);
		
		uPFill.add(usernameLblUP, 0, 1);
		uPFill.add(usernameFieldUP, 1, 1);
		
		uPFill.add(passLblUP, 0, 2);
		uPFill.add(passFieldUP, 1, 2);
		
		uPFill.add(newPassLblUP, 0, 3);
		uPFill.add(newPassFieldUP, 1, 3);
		
		uPContainer.getChildren().addAll(uPTitle, uPFill, uPEM, uPBtn);
		uPVb.getChildren().add(uPContainer);
	}
	
	public void updateProfileStyling() {
		uPVb.setAlignment(Pos.CENTER); //menengahkan posisi container
		uPVb.setStyle("-fx-background-color: #133E87;");
		
		uPContainer.setAlignment(Pos.CENTER);
		uPContainer.setMaxWidth(500);
		uPContainer.setStyle("-fx-background-color: white;");
		
		uPTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		uPContainer.setMargin(uPTitle, new Insets(50,0,30,0));
		
		emailLblUP.setFont(Font.font(15));
		emailFieldUP.setMinWidth(300);
		emailFieldUP.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		usernameLblUP.setFont(Font.font(15));
		usernameFieldUP.setMinWidth(300);
		usernameFieldUP.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		passLblUP.setFont(Font.font(15));
		passFieldUP.setMinWidth(300);
		passFieldUP.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		newPassLblUP.setFont(Font.font(15));
		newPassFieldUP.setMinWidth(300);
		newPassFieldUP.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		uPFill.setAlignment(Pos.CENTER);//menengahkan loginFill
		uPFill.setVgap(10);
		uPFill.setHgap(30);
		uPFill.setPadding(new Insets(10));
		
		uPBtn.setPadding(new Insets(10, 0, 10, 0));
		uPBtn.setMinWidth(100);
		uPBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		uPBtn.setFont(Font.font(15));
		
		uPEM.setStyle("-fx-text-fill: red;");
		
		uPContainer.setMargin(uPBtn, new Insets(30, 0, 50, 0));
	}
	
	public void updateProfile() {
		initUpdateProfileComponent();
		updateProfileStyling();
	}

	public void setEventHandler() {
		uPBtn.setOnAction(this);
	}
	
	public ViewChangeProfile() {
		initUpdateProfile();
		updateProfile();
		setEventHandler();
		Main.redirect(updateProfileScene);
	}

	@Override
	public void handle(ActionEvent event) {
		uPBtn.setOnAction(e -> {
			UserController uController = new UserController();
			System.out.println(passFieldUP.getText());
			System.out.println("test");
			uPEM.setText(uController.changeProfile(emailFieldUP.getText(), usernameFieldUP.getText(), passFieldUP.getText(), newPassFieldUP.getText()));
		});
		
	}

}
