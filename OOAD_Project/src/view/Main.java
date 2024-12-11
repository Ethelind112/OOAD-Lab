package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	public static void redirect(Scene scene) {
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setMaxWidth(1000);
		new ViewRegister();
	}

}
