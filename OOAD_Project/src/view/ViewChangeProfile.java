package view;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ViewChangeProfile {
	
	Scene updateProfileScene;
	VBox uPContainer, middleContainer;
	BorderPane uPPage;
	Label uPTitle, uPEM;
	GridPane uPFill;
	Button uPBtn;
	Label emailLblUP, usernameLblUP, passLblUP, newPassLblUP;
	TextField emailFieldUP, usernameFieldUP;
	PasswordField passFieldUP, newPassFieldUP;
	
	MenuBar menubar;
	Menu invitation, event, updateProfile, users, manageVendor, createEvent;
	MenuItem iInvitation, iEvent, iUpdateProfile, iUsers, iManageVendor, iCreateEvent;
	
	public void initUpdateProfile() {
		
		uPPage = new BorderPane();
		
		uPContainer = new VBox();
		middleContainer = new VBox();
		updateProfileScene = new Scene(uPPage, 1000, 700);
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
		
		uPBtn = new Button("Update");
		
		menubar = new MenuBar();
		
		updateProfile = new Menu("Update Profile");
		
		iUpdateProfile = new MenuItem("Update Profile");
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
		middleContainer.getChildren().add(uPContainer);
		uPPage.setTop(menubar);
		uPPage.setCenter(middleContainer);
	}
	
	public void updateProfileStyling() {
		uPPage.setStyle("-fx-background-color: #133E87;");
		
		middleContainer.setAlignment(Pos.CENTER);
		uPContainer.setAlignment(Pos.CENTER);
		uPContainer.setMaxWidth(500);
		uPContainer.setStyle("-fx-background-color: white;");
		menubar.setPadding(new Insets(10, 10, 10, 10));
		
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
		
		uPEM.setTextFill(javafx.scene.paint.Color.RED);
		
		uPContainer.setMargin(uPBtn, new Insets(30, 0, 50, 0));
	}
	
	public void setGuestMenu() {
		invitation = new Menu("Invitations");
		event = new Menu("Events");
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(invitation, event, updateProfile);

	}
	
	public void setAdminMenu() {
		users = new Menu("Users");
		event = new Menu("Events");
		
		iUsers = new MenuItem("Users");
		iEvent = new MenuItem("Accepted Events");
		
		users.getItems().addAll(iUsers);
		event.getItems().addAll(iEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(event, users, updateProfile);

	}
	
	public void setVendorMenu() {
		invitation = new Menu("Invitations");
		event = new Menu("Events");
		manageVendor = new Menu("Manage Vendor");
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		iManageVendor = new MenuItem("Manage Vendor");
		
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		manageVendor.getItems().addAll(iManageVendor);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(invitation, event, manageVendor, updateProfile);

	}
	
	public void setEventOrganizerMenu() {
		invitation = new Menu("Invitations");
		event = new Menu("Events");
		createEvent = new Menu("Create Event");
		
		iInvitation = new MenuItem("Invitation");
		iEvent = new MenuItem("Accepted Events");
		iCreateEvent = new MenuItem("Create Event");
		
		invitation.getItems().addAll(iInvitation);
		event.getItems().addAll(iEvent);
		createEvent.getItems().addAll(iCreateEvent);
		updateProfile.getItems().addAll(iUpdateProfile);
		
		menubar.getMenus().addAll(invitation, event, createEvent, updateProfile);

	}
	
	public void updateProfile() {
		initUpdateProfileComponent();
		updateProfileStyling();
	}
	
	public ViewChangeProfile() {
		initUpdateProfile();
		updateProfile();
		Main.redirect(updateProfileScene);
	}

	public void setChangeProfileButton(EventHandler<ActionEvent> handler) {
		uPBtn.setOnAction(handler);
	}
	
	public void setInvitationMenu(EventHandler<ActionEvent> handler) {
		iInvitation.setOnAction(handler);
	}
	
	public void setEventMenu(EventHandler<ActionEvent> handler) {
		iEvent.setOnAction(handler);
	}
	
	public void setUserMenu(EventHandler<ActionEvent> handler) {
		iUsers.setOnAction(handler);
	}
	
	public String getEmail() {
		return emailFieldUP.getText();
	}
	
	public String getUserName() {
		return usernameFieldUP.getText();
	}
	
	public String getPassword() {
		return passFieldUP.getText();
	}
	
	public String getNewPassword() {
		return newPassFieldUP.getText();
	}
	
	public void setErrorMessage(String message) {
		uPEM.setText(message);
	}
	
	public Scene getScene() {
		return updateProfileScene;
	}

}
