package view;

import controller.AdminController;
import controller.EventController;
import controller.GuestController;
import controller.InvitationController;
import controller.UserController;
import controller.VendorController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	public static void toInvitationPage(String email) {
		ViewInvitation view = new ViewInvitation(email);
		InvitationController iController = new InvitationController(view, email);
		
		redirect(view.getScene());
	}
	
	public static void toChangeProfilePage(String email) {
		ViewChangeProfile view = new ViewChangeProfile();
		UserController uController = new UserController(view, email);
		redirect(view.getScene());
	}

	public static void toEventPageGuest(String email) {
		UserController uController = new UserController();
		
		ViewEvents view = new ViewEvents(email);
		GuestController gController = new GuestController(view, email);
		redirect(view.getScene());
	}
	
	public static void toEventPageVendor(String email) {
		UserController uController = new UserController();
		
		ViewEvents view = new ViewEvents(email);
		VendorController gController = new VendorController(view, email);
		redirect(view.getScene());
	}
	
	public static void toManageVendor(String email) {
		ViewManageVendor view = new ViewManageVendor(email);
		VendorController vcontroller = new VendorController(view, email);
		redirect(view.getMGscene());
	}
	
	public static void toEventPageAdmin(String email) {
		UserController uController = new UserController();
		
		ViewEvents view = new ViewEvents(email);
		AdminController adminC = new AdminController(view, email);
		redirect(view.getScene());
	}
	
	public static void toEventPageEO(String email) {
		UserController uController = new UserController();
		
		ViewEvents view = new ViewEvents(email);
		
//		Tambahin Controller EO
		
		redirect(view.getScene());
	}
	
	public static void toEventDetailPage(String email, String eventID) {
		ViewEventDetails view = new ViewEventDetails();
		EventController eController = new EventController(view, email, eventID);
		redirect(view.getScene());
	}
	
	public static void toRegisterPage() {
		ViewRegister view = new ViewRegister();
		UserController uController = new UserController(view);
		Main.redirect(view.getScene());
	}
	
	public static void toLoginPage() {
		ViewLogin view = new ViewLogin();
		UserController uController = new UserController(view);
		Main.redirect(view.getScene());
	}
	
	public static void toUserPage(String email) {
		ViewUser view = new ViewUser();
//		UserController uController = new UserController(view);
		AdminController adminC = new AdminController(view, email);
		Main.redirect(view.getScene());
	}
	
	public static void redirect(Scene scene) {
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setMaxWidth(1000);
		ViewRegister view = new ViewRegister();
		UserController uController = new UserController(view);
		redirect(view.getScene());
	}

}
