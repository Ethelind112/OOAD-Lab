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
	
	public static void toManageVendorPage(String email) {
		ViewManageVendor view = new ViewManageVendor();
		redirect(view.getMGscene());
	}

	
	public static void toEventPage(String email) {
		UserController uController = new UserController();
		
		ViewEvents view = new ViewEvents(email);
		String role = uController.getUser().getUser_role();
		
//		proses controller berdasarkan rolenya
		if(role.equalsIgnoreCase("Vendor")) {
			VendorController gController = new VendorController(view, uController.getUser().getUser_email());
		}
		else if (role.equalsIgnoreCase("Guest")) {
			GuestController gController = new GuestController(view, uController.getUser().getUser_email());
		}else if(role.equalsIgnoreCase("Admin")) {
			AdminController adminC = new AdminController(view, uController.getUser().getUser_email());
		}
		
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
	
	public static void toUserPage() {
		ViewUser view = new ViewUser();
		AdminController uController = new AdminController(view);
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
	}

}
