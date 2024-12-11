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
import javafx.scene.control.PasswordField;
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

public class ViewRegister {

	Scene regisScene;
	VBox regisVB, regisContainer;
	Label regisTitle, regisEM, haveAcc;
	GridPane regisFill;
	Button regisBtn, toLogin;
	TextField emailFieldR, usernameFieldR;
	PasswordField passFieldR;
	Label emailLblR, passLblR, usernameLbl, roleLbl;
	PasswordField passField;
	ComboBox<String> roleCB;
	HBox bottomAuthR;
	
//	Function untuk deklarasi setiap element pada halaman register
	public void initRegister() {
		regisVB = new VBox();
		regisContainer = new VBox();
		regisScene = new Scene(regisVB, 1000, 700);
		
		regisTitle = new Label("Register");
		regisFill = new GridPane();
		
		emailLblR = new Label("Email");
		emailFieldR = new TextField();
		
		usernameLbl = new Label("Username");
		usernameFieldR = new TextField();
		
		passLblR = new Label("Password");
		passFieldR = new PasswordField();
		
		roleLbl = new Label("Role");
		roleCB = new ComboBox<>();
		regisBtn = new Button("Register");
		
		bottomAuthR = new HBox();
		haveAcc = new Label("Already have account?");
		regisEM = new Label();
		toLogin = new Button("Login");
		
	}
	
//	Membentuk struktur register page yang terdiri dari komponen-komponennya
	public void initRegisComponent() {
		regisFill.add(emailLblR, 0, 0);
		regisFill.add(emailFieldR, 1, 0);
		
		regisFill.add(usernameLbl, 0, 2);
		regisFill.add(usernameFieldR, 1, 2);
		
		regisFill.add(passLblR, 0, 4);
		regisFill.add(passFieldR, 1, 4);
		
		regisFill.add(roleLbl, 0, 6);
		regisFill.add(roleCB, 1, 6);
		
		bottomAuthR.getChildren().addAll(haveAcc, toLogin);
		
		regisContainer.getChildren().addAll(regisTitle, regisFill, regisEM, regisBtn, bottomAuthR);
		regisVB.getChildren().add(regisContainer);
	}
	
	public void registStyling() {
		regisVB.setAlignment(Pos.CENTER);
		regisVB.setBackground(new Background(new BackgroundFill(Color.web("#133E87"), CornerRadii.EMPTY, null)));
		
		regisContainer.setAlignment(Pos.CENTER); //menengahkan posisi container
		regisContainer.setMaxWidth(500);
		regisContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
		
		regisTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		regisContainer.setMargin(regisTitle, new Insets(50,0,30,0));
		
		emailLblR.setFont(Font.font(15));
		emailFieldR.setMinWidth(300);
		
		usernameLbl.setFont(Font.font(15));
		usernameFieldR.setMinWidth(300);
		
		passLblR.setFont(Font.font(15));
		passFieldR.setMinWidth(300);
		
		roleLbl.setFont(Font.font(15));
		roleCB.getItems().addAll("Event Organizer", "Vendor", "Guest", "Admin");
		roleCB.setMinWidth(300);
		
		regisFill.setAlignment(Pos.CENTER); //menengahkan regisFill
		regisFill.setVgap(10);
		regisFill.setHgap(30);
		regisFill.setPadding(new Insets(10));
		
		regisBtn.setPadding(new Insets(10, 0, 10, 0));
		regisBtn.setMinWidth(100);
		regisBtn.setTextFill(Color.WHITE);
		regisBtn.setBackground(new Background(new BackgroundFill(Color.web("#133E87"), CornerRadii.EMPTY, null)));
		regisBtn.setFont(Font.font(15));
		
		bottomAuthR.setAlignment(Pos.CENTER); //menengahkan bottomAuthR
		toLogin.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
		toLogin.setTextFill(Color.web("#133E87"));
		toLogin.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		
		regisEM.setTextFill(Color.RED);
		
		regisContainer.setMargin(regisBtn, new Insets(20, 0, 0, 0));
		regisContainer.setMargin(bottomAuthR, new Insets(10, 0, 50, 0));
	}
	
	public void register() {
		initRegisComponent();
		registStyling();
	}
	
//	public void setEventHandler() {
//		regisBtn.setOnAction(e -> {
//
////			mengambil data dari inputan
//			String email = emailFieldR.getText();
//			String uName = usernameFieldR.getText();
//			String pass = passFieldR.getText();
//			String role = "";
//			
//			if(roleCB != null) {
//				role = roleCB.getValue();
//			}
//			
//			UserController uController = new UserController();
//			
////			proses registrasi ke controller
//			String message = uController.register(email, uName, pass, role);
//
//			if(message == "success") {
//				if(role.equalsIgnoreCase("Admin")) {
//					ViewEvents view = new ViewEvents(email);
//					Main.redirect(view.eventScene);
//				}else if(role.equalsIgnoreCase("Guest")) {
//					ViewInvitation view = new ViewInvitation(email);
//					Main.redirect(view.invitationScene);
//				}else if(role.equalsIgnoreCase("Event Organizer")) {
//					ViewEvents view = new ViewEvents(email);
//					Main.redirect(view.eventScene);
//				}else if(role.equalsIgnoreCase("Vendor")) {
//					ViewInvitation view = new ViewInvitation(email);
//					Main.redirect(view.invitationScene);
//				}
//			}
//			
//			regisEM.setText(message);
//		});
//		
//		toLogin.setOnAction(e -> {
//			ViewLogin view = new ViewLogin();
//			Main.redirect(view.loginScene);
//		});
//	}
	
	public String getEmailInput() {
		return emailFieldR.getText();
	}
	
	public String getUserNameInput() {
		return usernameFieldR.getText();
	}
	
	public String getPasswordInput() {
		return passFieldR.getText();
	}
	
	public String getRoleInput() {
		String role = "";
		
		if(roleCB != null) {
			role = roleCB.getValue();
		}
		
		return role;
	}
	
	public void setErrorMessage(String message) {
		regisEM.setText(message);
	}
	
	public void setRegisButton(EventHandler<ActionEvent> handler) {
		regisBtn.setOnAction(handler);
	}

	public void setToLoginButton(EventHandler<ActionEvent> handler) {
		toLogin.setOnAction(handler);
	}
	
	public Scene getScene() {
		return regisScene;
	}
	
	public ViewRegister() {
		initRegister();
		register();
		
		Main.redirect(regisScene);
	}
	
}
