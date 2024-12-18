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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ViewLogin {

	Scene loginScene;
	VBox loginVb, loginContainer;
	Label loginTitle, loginEM, noAcc;
	GridPane loginFill;
	Button loginBtn, toRegis;
	Label emailLbl, passLbl;
	TextField emailField, usernameField;
	HBox bottomAuthL;
	PasswordField passField;
	
//	Function untuk deklarasi setiap element pada halaman login
	public void initLogin() {
		loginVb = new VBox();
		loginContainer = new VBox();
		loginScene = new Scene(loginVb, 1000, 700);
		loginTitle = new Label("Login");
		loginFill = new GridPane();
		loginBtn = new Button("Login");
		
		emailLbl = new Label("Email");
		emailField = new TextField();
		
		passLbl = new Label("Password");
		passField = new PasswordField();
		
		bottomAuthL = new HBox();
		noAcc = new Label("No account yet?");
		
		loginEM = new Label(); //dibiarkan kosong dan diisi saat ada error message
		toRegis = new Button("Register");
		
	}
	
//	Membentuk struktur login page yang terdiri dari komponen-komponennya
	public void initLoginComponent() {
		loginFill.add(emailLbl, 0, 0);
		loginFill.add(emailField, 1, 0);
//		loginFill.add(loginEM, 1, 1);
		
		loginFill.add(passLbl, 0, 2);
		loginFill.add(passField, 1, 2);
//		loginFill.add(loginPM, 1, 3);
		
		bottomAuthL.getChildren().addAll(noAcc, toRegis);
		
		loginContainer.getChildren().addAll(loginTitle, loginFill, loginEM, loginBtn, bottomAuthL);
		loginVb.getChildren().add(loginContainer);
		
	}
	
//	Menambah styling untuk page login 
	public void loginStyling(){
		loginVb.setAlignment(Pos.CENTER); //menengahkan posisi container
		loginVb.setStyle("-fx-background-color: #133E87;");
		loginVb.setBackground(new Background(new BackgroundFill(Color.web("#133E87"), CornerRadii.EMPTY, null)));
		
		loginContainer.setAlignment(Pos.CENTER);
		loginContainer.setMaxWidth(500);
//		loginContainer.setStyle("-fx-background-color: white;");
		loginContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
		
		loginTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		loginContainer.setMargin(loginTitle, new Insets(50,0,30,0));
		
		emailLbl.setFont(Font.font(15));
		emailField.setMinWidth(300);
//		emailField.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		passLbl.setFont(Font.font(15));
		passField.setMinWidth(300);
//		passField.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		loginFill.setAlignment(Pos.CENTER);//menengahkan loginFill
		loginFill.setVgap(10);
		loginFill.setHgap(30);
		loginFill.setPadding(new Insets(10));
		
		loginBtn.setPadding(new Insets(10, 0, 10, 0));
		loginBtn.setMinWidth(100);
//		loginBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		loginBtn.setTextFill(Color.WHITE);
		loginBtn.setBackground(new Background(new BackgroundFill(Color.web("#133E87"), CornerRadii.EMPTY, null)));
		loginBtn.setFont(Font.font(15));
		
		bottomAuthL.setAlignment(Pos.CENTER); //menengahkan bottomAuthL
//		toRegis.setStyle("-fx-background-color: transparent; -fx-text-fill: #133E87;");
		toRegis.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, null)));
		toRegis.setTextFill(Color.web("#133E87"));
		toRegis.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		
//		loginEM.setStyle("-fx-text-fill: red;");
		loginEM.setTextFill(Color.RED);
		
		loginContainer.setMargin(loginBtn, new Insets(20, 0, 0, 0));
		loginContainer.setMargin(bottomAuthL, new Insets(10, 0, 50, 0));
	}
	
//	Membentuk login page secara keseluruhan, termasuk struktur dan design
	public void login() {
		initLoginComponent();
		loginStyling();
	}
	
	public void setErrorMessage(String message) {
		loginEM.setText(message);
	}
	
	public String getEmailInput() {
		return emailField.getText();
	}
	
	public String getPasswordInput()
	{
		return passField.getText();
	}
	
	public void setLoginButton(EventHandler<ActionEvent> handler) {
		loginBtn.setOnAction(handler);
	}
	
	public void setToRegisButton(EventHandler<ActionEvent> handler) {
		toRegis.setOnAction(handler);
	}
	
	public Scene getScene() {
		return loginScene;
	}
	
	public ViewLogin() {
		initLogin();
		login();
	}

}
