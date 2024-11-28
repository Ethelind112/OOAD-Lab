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

public class Main extends Application {

//	register components
	Scene regisScene;
	VBox regisVB, regisContainer;
	Label regisTitle, regisPM, regisEM, regisRM, regisUM, haveAcc;
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
	Label loginTitle, loginPM, loginEM, noAcc;
	GridPane loginFill;
	Button loginBtn, toRegis;
	Label emailLbl, passLbl;
	TextField emailField, usernameField;
	HBox bottomAuthL;
	
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
		regisPM = new Label();
		regisEM = new Label();
		regisRM = new Label();
		regisUM = new Label();
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
		
		loginPM = new Label(); //dibiarkan kosong dan diisi saat ada error message
		loginEM = new Label(); //dibiarkan kosong dan diisi saat ada error message
		toRegis = new Button("Register");
		
	}
	
//	Function khusus untuk membuat komponen semua page
	public void init() {
		initRegister();
		initLogin();
	}
	
//	Membentuk struktur register page yang terdiri dari komponen-komponennya
	public void initRegisComponent() {
		regisFill.add(emailLblR, 0, 0);
		regisFill.add(emailFieldR, 1, 0);
		regisFill.add(regisEM, 1, 1);
		
		regisFill.add(usernameLbl, 0, 2);
		regisFill.add(usernameFieldR, 1, 2);
		regisFill.add(regisUM, 1, 3);
		
		regisFill.add(passLblR, 0, 4);
		regisFill.add(passFieldR, 1, 4);
		regisFill.add(regisPM, 1, 5);
		
		regisFill.add(roleLbl, 0, 6);
		regisFill.add(roleCB, 1, 6);
		regisFill.add(regisRM, 1, 7);
		
		bottomAuthR.getChildren().addAll(haveAcc, toLogin);
		
		regisContainer.getChildren().addAll(regisTitle, regisFill, regisBtn, bottomAuthR);
		regisVB.getChildren().add(regisContainer);
	}
	
//	Membentuk struktur login page yang terdiri dari komponen-komponennya
	public void initLoginComponent() {
		loginFill.add(emailLbl, 0, 0);
		loginFill.add(emailField, 1, 0);
		loginFill.add(loginEM, 1, 1);
		
		loginFill.add(passLbl, 0, 2);
		loginFill.add(passField, 1, 2);
		loginFill.add(loginPM, 1, 3);
		
		bottomAuthL.getChildren().addAll(noAcc, toRegis);
		
		loginContainer.getChildren().addAll(loginTitle, loginFill, loginBtn, bottomAuthL);
		loginVb.getChildren().add(loginContainer);
		
	}
	
//	Menambah styling untuk page register 
	public void registStyling() {
		regisVB.setAlignment(Pos.CENTER);
		regisVB.setStyle("-fx-background-color: #133E87;");
		
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
		regisBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		regisBtn.setFont(Font.font(15));
		
		bottomAuthR.setAlignment(Pos.CENTER); //menengahkan bottomAuthR
		toLogin.setStyle("-fx-background-color: transparent; -fx-text-fill: #133E87;");
		toLogin.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		
		
		regisEM.setStyle("-fx-text-fill: red;");
		regisPM.setStyle("-fx-text-fill: red;");
		regisRM.setStyle("-fx-text-fill: red;");
		regisUM.setStyle("-fx-text-fill: red;");
		
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
		loginPM.setStyle("-fx-text-fill: red;");
		
		loginContainer.setMargin(loginBtn, new Insets(20, 0, 0, 0));
		loginContainer.setMargin(bottomAuthL, new Insets(10, 0, 50, 0));
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

//	Mengecek inputan email user saat registrasi
	public boolean checkEmail(String email) {
		//bila email kosong, keluarkan error message
		if(email.isEmpty()) {
			regisEM.setText("Please fill in this filled");
			return false;
		}
		
		//get data from database and check uniqueness
		
		//bila email terisi dengan benar hilangkan error message
		regisEM.setText("");
		return true;
	}
	
//	Mengecek inputan nama user saat registrasi
	public boolean checkuName(String uName) {
		//bila email kosong, keluarkan error message
		if(uName.isEmpty()) {
			regisUM.setText("Please fill in this filled");
			return false;
		}
		
		//get data from database and check uniqueness
		
		//bila email terisi dengan benar hilangkan error message
		regisUM.setText("");
		return true;
	}
	
//	Mengecek inputan password user saat registrasi
	public boolean checkPass(String pass) {
		//bila password kosong atau panjang kurang dari 5, keluarkan error message
		if(pass.isEmpty()) {
			regisPM.setText("Please fill in this filled");
			return false;
		}else if(pass.length() < 5) {
			regisPM.setText("Password must at least 5 characters long");
			return false;
		}
		
		//bila password terisi dengan benar hilangkan error message	
		regisPM.setText("");
		return true;
	}
	 
//	Mengecek pilihan role user saat registrasi
	public boolean checkRole() {
		//bila role tidak dipilih, keluarkan error message
		if(roleCB == null || roleCB.getValue() == null) {
			regisRM.setText("Please choose role");
			return false;
		}
		
		//bila role terisi dengan benar hilangkan error message	
		regisRM.setText("");
		return true;
	}
	
//	proses registrasi user
	public boolean processRegis() {
//		mengambil data dari inputan
		String email = emailFieldR.getText();
		String uName = usernameFieldR.getText();
		String pass = passFieldR.getText();
		String role = null;
		
//		melakukan pengecekan semua inputan
		boolean checkE = checkEmail(email);
		boolean checkU = checkuName(uName);
		boolean checkP = checkPass(pass);
		boolean checkR = true;
		
//		bila role dipilih, masukan inputan kedalam variable
		if(checkRole()) {
			role = roleCB.getValue();
		}
		
//		bila semua inputan benar, maka memasukan semua data ke dalam database
		if(checkE && checkP && checkR && checkU) {
//			memasukan data ke dalam database
			
			return true;
		}
		
		return false;
	}
	
//	Mengecek inputan email user saat login
	public boolean checkEmailLogin(String email) {
		//bila email kosong, keluarkan error message
		if(email.isEmpty()) {
			loginEM.setText("Please fill in this filled");
			return false;
		}
		
		//Check matching ada email atau tidak
		
		//bila email terisi dengan benar hilangkan error message
		loginEM.setText("");
		return true;
	}
	
//	Mengecek inputan password user saat login
	public boolean checkPasswordLogin(String pass) {
		//bila password kosong, keluarkan error message
		if(pass.isEmpty()) {
			loginPM.setText("Please fill in this filled");
			return false;
		}
		
		//bila password terisi dengan benar hilangkan error message
		loginPM.setText("");
		return true;
	}
	
//	proses login user
	public boolean processLogin() {
		String email = emailField.getText();
		String pass = passField.getText();
		
		boolean checkE = checkEmailLogin(email);
		boolean checkP = checkPasswordLogin(pass);
		
//		Bila ada data yang tidak terisi dengan benar, maka return
		if(!checkE || !checkP) {
			return false;
		}
		
//		bila semua data terisi dengan benar, lakukan checking matching antara password dan email
		
//		bila checking database benar return true
		
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
		primaryStage.setScene(regisScene);
		primaryStage.show();
		
		
		regisBtn.setOnAction(e -> {
//			mendapatkan boolean apakah proses registrasi berhasil atau gagal
			boolean check = processRegis();
			
//			bila berhasil maka redirect ke page selanjutnya
			if(check) {
//				go to homepage
				
			}
			
		});
		
		toRegis.setOnAction(e -> {
			primaryStage.setScene(regisScene);
			primaryStage.show();
		});
		
		
			
		loginBtn.setOnAction(e -> {
//			mendapatkan boolean apakah proses login berhasil atau gagal
			boolean check = processLogin();
			
//			bila berhasil maka redirect ke page selanjutnya
			if(check) {
//				go to home page
			}
		});
		
		toLogin.setOnAction(e -> {
			primaryStage.setScene(loginScene);
			primaryStage.show();
		});

	}

}
