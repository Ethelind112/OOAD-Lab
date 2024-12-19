package view;

import java.util.ArrayList;

import controller.AdminController;
import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;
import model.User;

public class ViewUser {

	Scene userScene;
	
	String email;
	
	Label errorM;
	BorderPane userPage;
	VBox userContainer, userVB, delVB;
	Label userTitle;
	TableView<User> userTable;
	
	MenuBar menubar;
	Menu event, updateProfile, user;
	MenuItem iEvent, iUpdateProfile, iUser;
	Button delBtn;
	
	TableRow<User> row;
	ObservableList<User> userData;
	
	public void initUser() {
		userVB = new VBox();
		delVB = new VBox();
		
		userPage = new BorderPane();
		userContainer = new VBox();
		userScene = new Scene(userPage, 1000, 700);
		userTitle = new Label("Users");
		userTable = new TableView<>();
		
		menubar = new MenuBar();
		event = new Menu("Events");
		user = new Menu("Users");
		updateProfile = new Menu("Update Profile");
		
		iEvent = new MenuItem("Events");
		iUser = new MenuItem("Users");
		iUpdateProfile = new MenuItem("Update Profile");
		
		delBtn = new Button("Delete User");
		errorM = new Label();
	}
	
	public void setTable() {
		
		TableColumn<User,String> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<User, String>("user_id"));
		idColumn.setMinWidth(userContainer.getWidth()/8);
		
		TableColumn<User,String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("user_email"));
		emailColumn.setMinWidth(userContainer.getWidth()/6);
		
		TableColumn<User,String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("user_name"));
		nameColumn.setMinWidth(userContainer.getWidth()/8);
		
		TableColumn<User,String> roleColumn = new TableColumn<>("Role");
		roleColumn.setCellValueFactory(new PropertyValueFactory<User, String>("user_role"));
		roleColumn.setMinWidth(userContainer.getWidth()/8);
		
		userTable.getColumns().addAll(idColumn, emailColumn, nameColumn, roleColumn);
		userData = FXCollections.observableArrayList();
		userTable.setItems(userData);
		
		delVB.getChildren().add(delBtn);
		userContainer.getChildren().addAll(userTitle, userTable, delVB);
	}
	
	public void initUserComponent() {
		
		setTable();
		
		event.getItems().addAll(iEvent);
		user.getItems().addAll(iUser);
		updateProfile.getItems().addAll(iUpdateProfile);
			
		menubar.getMenus().addAll(event, user, updateProfile);
		
		userPage.setTop(menubar);
		userPage.setCenter(userContainer);
	}
	
	public void userStyling(){
		userPage.setStyle("-fx-background-color: white;");
	
		userContainer.setMaxWidth(900);
		
		menubar.setPadding(new Insets(10, 10, 10, 10));
		
		userTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		userContainer.setMargin(userTitle, new Insets(50,0,10,0));
		userContainer.setAlignment(Pos.TOP_CENTER);
		
		delVB.setAlignment(Pos.CENTER);
		delVB.setPadding(new Insets(10, 0, 0, 0));
		
		delBtn.setPadding(new Insets(10, 0, 10, 0));
		delBtn.setMinWidth(150);
		delBtn.setTextFill(Color.WHITE);
		delBtn.setBackground(new Background(new BackgroundFill(Color.web("#b20000"), CornerRadii.EMPTY, null)));
		delBtn.setFont(Font.font(15));
		
		errorM.setTextFill(Color.RED);
	}
	
	public void user() {
		initUser();
		initUserComponent();
		userStyling();
	}
	
	public void setMDeleteButton(EventHandler<MouseEvent> handler) {
		userTable.setRowFactory((TableView<User> e) -> {
			row = new TableRow<>();
			row.setOnMouseClicked(handler);
			return row;
		});	
	}
	
	public void setADeleteButton(EventHandler<ActionEvent> handler) {
		delBtn.setOnAction(handler);
	}
	
	public void setErrorMessage(String message) {
		errorM.setText(message);
	}
	
	public TableView<User> getUserTable(){
		return userTable;
	}
	
	public void setChangeProfileMenu(EventHandler<ActionEvent> handler) {
		iUpdateProfile.setOnAction(handler);
	}
	
	public void setEventMenu(EventHandler<ActionEvent> handler) {
		iEvent.setOnAction(handler);
	}
	
	public void setUserList(ObservableList<User> users) {
		userData.setAll(users);
	}
	
	public ViewUser() {
		initUser();
		user();
//		setEventHandler();
		
		Main.redirect(userScene);
	}
	
	public Scene getScene() {
		return userScene;
	}
}
