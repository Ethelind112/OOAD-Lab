import controller.UserController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.Connect;

public class Main extends Application {
	
//	belom tau bener ato ga cara pakenya
	UserController uController = new UserController();

//	register components
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
	
//	login components
	Scene loginScene;
	VBox loginVb, loginContainer;
	Label loginTitle, loginEM, noAcc;
	GridPane loginFill;
	Button loginBtn, toRegis;
	Label emailLbl, passLbl;
	TextField emailField, usernameField;
	HBox bottomAuthL;
	
//	update profile components
	Scene updateProfileScene;
	VBox uPVb, uPContainer;
	Label uPTitle, uPEM;
	GridPane uPFill;
	Button uPBtn;
	Label emailLblUP, usernameLblUP, passLblUP, newPassLblUP;
	TextField emailFieldUP, usernameFieldUP;
	PasswordField passFieldUP, newPassFieldUP;
	
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
	
//	Function untuk deklarasi setiap element pada halaman update profile
	public void initUpdateProfile() {
		uPVb = new VBox();
		uPContainer = new VBox();
		updateProfileScene = new Scene(uPVb, 1000, 700);
		uPTitle = new Label("Update Profile");
		uPEM = new Label();
		uPFill = new GridPane();
		
		
//		changePass = new Button("Change Password");
		emailLblUP = new Label("Email");
		passLblUP = new Label("Password");
		usernameLblUP = new Label("Username");
		
		newPassLblUP = new Label("New Password");
		
		usernameFieldUP = new TextField();
		emailFieldUP = new TextField();
		passFieldUP = new PasswordField();
		newPassFieldUP = new PasswordField();
		uPBtn = new Button("Update");
		
	}
	
//	Function khusus untuk membuat komponen semua page
	public void init() {
		initRegister();
		initLogin();
		initUpdateProfile();
	}
	
//	Membentuk struktur register page yang terdiri dari komponen-komponennya
	public void initRegisComponent() {
		regisFill.add(emailLblR, 0, 0);
		regisFill.add(emailFieldR, 1, 0);
//		regisFill.add(regisEM, 1, 1);
		
		regisFill.add(usernameLbl, 0, 2);
		regisFill.add(usernameFieldR, 1, 2);
//		regisFill.add(regisUM, 1, 3);
		
		regisFill.add(passLblR, 0, 4);
		regisFill.add(passFieldR, 1, 4);
//		regisFill.add(regisPM, 1, 5);
		
		regisFill.add(roleLbl, 0, 6);
		regisFill.add(roleCB, 1, 6);
//		regisFill.add(regisRM, 1, 7);
		
		bottomAuthR.getChildren().addAll(haveAcc, toLogin);
		
		regisContainer.getChildren().addAll(regisTitle, regisFill, regisEM, regisBtn, bottomAuthR);
		regisVB.getChildren().add(regisContainer);
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
	
	public void initUpdateProfileComponent() {
		uPFill.add(emailLblUP, 0, 0);
//		uPFill.add(emailLblUPD, 1, 0);
//		uPFill.add(changeEmail, 0, 1);
		uPFill.add(emailFieldUP, 1, 0);
		
		uPFill.add(usernameLblUP, 0, 1);
//		uPFill.add(usernameLblUPD, 1, 1);
//		uPFill.add(changeUsername, 0, 3);
		uPFill.add(usernameFieldUP, 1, 1);
		
		uPFill.add(passLblUP, 0, 2);
//		uPFill.add(passLblUPD, 1, 4);
//		uPFill.add(changePassword, 0, 5);
		uPFill.add(passFieldUP, 1, 2);
//		uPFill.add(changePass, 1, 3);
		
		uPFill.add(newPassLblUP, 0, 3);
		uPFill.add(newPassFieldUP, 1, 3);
		
		uPContainer.getChildren().addAll(uPTitle, uPFill, uPEM, uPBtn);
		uPVb.getChildren().add(uPContainer);
	}
	
//	Menambah styling untuk page register 
	public void registStyling() {
		regisVB.setAlignment(Pos.CENTER);
		regisVB.setStyle("-fx-background-color: #3D4735;");
		
		regisContainer.setAlignment(Pos.CENTER); //menengahkan posisi container
		regisContainer.setMaxWidth(500);
		regisContainer.setStyle("-fx-background-color: white;");
		
		regisTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		regisContainer.setMargin(regisTitle, new Insets(50,0,30,0));
		
		emailLblR.setFont(Font.font(15));
		emailFieldR.setMinWidth(300);
		emailFieldR.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		usernameLbl.setFont(Font.font(15));
		usernameFieldR.setMinWidth(300);
		usernameFieldR.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		passLblR.setFont(Font.font(15));
		passFieldR.setMinWidth(300);
		passFieldR.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		roleLbl.setFont(Font.font(15));
		roleCB.getItems().addAll("Event Organizer", "Vendor", "Guest");
		roleCB.setMinWidth(300);
		roleCB.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		regisFill.setAlignment(Pos.CENTER); //menengahkan regisFill
		regisFill.setVgap(10);
		regisFill.setHgap(30);
		regisFill.setPadding(new Insets(10));
		
		regisBtn.setPadding(new Insets(10, 0, 10, 0));
		regisBtn.setMinWidth(100);
		regisBtn.setStyle("-fx-text-fill: white; -fx-background-color: #A37F66;");
		regisBtn.setFont(Font.font(15));
		
		bottomAuthR.setAlignment(Pos.CENTER); //menengahkan bottomAuthR
		toLogin.setStyle("-fx-background-color: transparent; -fx-text-fill: #5F6F52;");
		toLogin.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		
		
		regisEM.setStyle("-fx-text-fill: red;");
		
		regisContainer.setMargin(regisBtn, new Insets(20, 0, 0, 0));
		regisContainer.setMargin(bottomAuthR, new Insets(10, 0, 50, 0));
	}
	
//	Menambah styling untuk page login 
	public void loginStyling(){
		loginVb.setAlignment(Pos.CENTER); //menengahkan posisi container
		loginVb.setStyle("-fx-background-color: #133E87;");
		
		loginContainer.setAlignment(Pos.CENTER);
		loginContainer.setMaxWidth(500);
		loginContainer.setStyle("-fx-background-color: white;");
		
		loginTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		loginContainer.setMargin(loginTitle, new Insets(50,0,30,0));
		
		emailLbl.setFont(Font.font(15));
		emailField.setMinWidth(300);
		emailField.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		passLbl.setFont(Font.font(15));
		passField.setMinWidth(300);
		passField.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		loginFill.setAlignment(Pos.CENTER);//menengahkan loginFill
		loginFill.setVgap(10);
		loginFill.setHgap(30);
		loginFill.setPadding(new Insets(10));
		
		loginBtn.setPadding(new Insets(10, 0, 10, 0));
		loginBtn.setMinWidth(100);
		loginBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		loginBtn.setFont(Font.font(15));
		
		bottomAuthL.setAlignment(Pos.CENTER); //menengahkan bottomAuthL
		toRegis.setStyle("-fx-background-color: transparent; -fx-text-fill: #133E87;");
		toRegis.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		
		loginEM.setStyle("-fx-text-fill: red;");
		
		loginContainer.setMargin(loginBtn, new Insets(20, 0, 0, 0));
		loginContainer.setMargin(bottomAuthL, new Insets(10, 0, 50, 0));
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
		
//		changePass.setStyle("-fx-background-color: transparent; -fx-text-fill: #133E87;");
//		changePass.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		
		uPBtn.setPadding(new Insets(10, 0, 10, 0));
		uPBtn.setMinWidth(100);
		uPBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		uPBtn.setFont(Font.font(15));
		
		uPEM.setStyle("-fx-text-fill: red;");
		
		uPContainer.setMargin(uPBtn, new Insets(30, 0, 50, 0));
	}
	
//	Membentuk register page secara keseluruhan, termasuk struktur dan design
	public void register() {
		initRegisComponent();
		registStyling();
	}
	
//	Membentuk login page secara keseluruhan, termasuk struktur dan design
	public void login() {
		initLoginComponent();
		loginStyling();
	}
	
	public void updateProfile() {
		initUpdateProfileComponent();
		updateProfileStyling();
	}
	
//	proses registrasi user
	public boolean processRegis() {
		
//		mengambil data dari inputan
		String email = emailFieldR.getText();
		String uName = usernameFieldR.getText();
		String pass = passFieldR.getText();
		String role = "";
		
		if(roleCB != null) {
			role = roleCB.getValue();
		}
		
//		proses registrasi ke controller
		String message = uController.register(email, uName, pass, role);

		if(message == "success") {
			return true;
		}
		
		regisEM.setText(message);
		return false;
	}
	
//	proses login user
	public boolean processLogin() {
		String email = emailField.getText();
		String pass = passField.getText();
		
		String message = uController.login(email, pass);

		if(message == "success") {
			return true;
		}
		
		loginEM.setText(message);
		return false;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		register();
		login();
		updateProfile();
		primaryStage.setScene(regisScene);
		primaryStage.show();
		
		
		regisBtn.setOnAction(e -> {
			boolean check = processRegis();
			
			if(check) {
//				redirect ke page lain
			}
			
		});
		
		toRegis.setOnAction(e -> {
			primaryStage.setScene(regisScene);
			primaryStage.show();
		});
		
		
		loginBtn.setOnAction(e -> {
			boolean check = processLogin();
			
			if(check) {
//				redirect ke page lain
			}
		});
		
		toLogin.setOnAction(e -> {
			primaryStage.setScene(loginScene);
			primaryStage.show();
		});
		

	}

}
