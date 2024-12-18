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

public class ViewEditProduct {
	String email;
	
	Scene editProductScene;
	VBox uPContainer, middleContainer;
	BorderPane uPPage;
	Label uPTitle, uPEM;
	GridPane uPFill;
	Button editBtn;
	Label pName, pDesc;
	TextField pNameField, pDescField;
	
	public void initEditProduct() {
		
		uPPage = new BorderPane();
		
		uPContainer = new VBox();
		middleContainer = new VBox();
		editProductScene = new Scene(uPPage, 1000, 700);
		uPTitle = new Label("Update Profile");
		uPEM = new Label();
		uPFill = new GridPane();
		
		pName = new Label("Email");
		pDesc = new Label("Password");
		
		pNameField = new TextField();
		pDescField = new TextField();
		
		editBtn = new Button("Update");
	}
	
	public void initEditProductComponent() {
		uPFill.add(pName, 0, 0);
		uPFill.add(pNameField, 1, 0);
		
		uPFill.add(pDesc, 0, 1);
		uPFill.add(pDescField, 1, 1);
		
		uPContainer.getChildren().addAll(uPTitle, uPFill, uPEM, editBtn);
		middleContainer.getChildren().add(uPContainer);
		uPPage.setCenter(middleContainer);
	}
	
	public void editProductStyling() {
		uPPage.setStyle("-fx-background-color: #133E87;");
		
		middleContainer.setAlignment(Pos.CENTER);
		uPContainer.setAlignment(Pos.CENTER);
		uPContainer.setMaxWidth(500);
		uPContainer.setStyle("-fx-background-color: white;");
		
		uPTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		uPContainer.setMargin(uPTitle, new Insets(50,0,30,0));
		
		pName.setFont(Font.font(15));
		pNameField.setMinWidth(300);
		pNameField.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		pDesc.setFont(Font.font(15));
		pDescField.setMinWidth(300);
		pDescField.setStyle("-fx-background-color: white; -fx-border-color: #e6e6e6");
		
		uPFill.setAlignment(Pos.CENTER);//menengahkan loginFill
		uPFill.setVgap(10);
		uPFill.setHgap(30);
		uPFill.setPadding(new Insets(10));
		
		editBtn.setPadding(new Insets(10, 0, 10, 0));
		editBtn.setMinWidth(100);
		editBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		editBtn.setFont(Font.font(15));
		
		uPEM.setTextFill(javafx.scene.paint.Color.RED);
		
		uPContainer.setMargin(editBtn, new Insets(30, 0, 50, 0));
	}
	
	public void addProduct() {
		initEditProductComponent();
		editProductStyling();
	}
	
	public ViewEditProduct(String email) {
		this.email = email;
		
		initEditProduct();
		addProduct();
	}

	public void setChangeProfileButton(EventHandler<ActionEvent> handler) {
		editBtn.setOnAction(handler);
	}
	
	public String getName() {
		return pNameField.getText();
	}
	
	public String getDescription() {
		return pDescField.getText();
	}
	
	public void setErrorMessage(String message) {
		uPEM.setText(message);
	}
	
	public Scene getScene() {
		return editProductScene;
	}

}
