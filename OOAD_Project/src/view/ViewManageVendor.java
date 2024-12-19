package view;

import java.util.ArrayList;

import controller.VendorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;
import model.Products;

public class ViewManageVendor {
	String email;
	
	Scene MGscene;
	BorderPane MGpage;
	VBox MGcontainer;
	GridPane input;
	Label MGtitle, MGdesc, MGem, newName, newDesc;
	TextField nameTF, descTF;
	TableView<Products> pTable;
	ArrayList<Products> products;
	Button editBtn, backBtn, addBtn;
	HBox buttonHB;
	ObservableList<Products> pData;
	
	public void initProduct() {
		MGpage = new BorderPane();
		buttonHB = new HBox();
		MGcontainer = new VBox();
		input = new GridPane();
		MGscene = new Scene(MGpage, 1000, 700);
		MGtitle = new Label("Manage Product");
		MGdesc = new Label("Manage your product's information from the table below");
		newName = new Label("Name: ");
		newDesc = new Label("Description: ");
		nameTF = new TextField();
		descTF = new TextField();
		MGem = new Label("");
		editBtn = new Button("Edit");
		backBtn = new Button("Back");
		addBtn = new Button("Add");
		
		pTable = new TableView<>();
	}
	
	public void setTable(){
			pData = FXCollections.observableArrayList();
			
			TableColumn<Products,String> idColumn = new TableColumn<>("Id");
			idColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("products_id"));
			idColumn.setMinWidth(MGpage.getWidth()/3);
			
			TableColumn<Products,String> nameColumn = new TableColumn<>("Name");
			nameColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("products_name"));
			nameColumn.setMinWidth(MGpage.getWidth()/3);
			
			TableColumn<Products,String> descColumn = new TableColumn<>("Description");
			descColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("products_description"));
			descColumn.setMinWidth(MGpage.getWidth()/3);
			
			pTable.getColumns().addAll(idColumn, nameColumn, descColumn);
			pTable.setItems(pData);

	}
	
	public void initComponent() {
		setTable();
		
		input.add(newName, 0, 0);
		input.add(nameTF, 1, 0);
		
		input.add(newDesc, 0, 1);
		input.add(descTF, 1, 1);
		
		input.add(buttonHB, 0, 2);
		
		buttonHB.getChildren().addAll(editBtn, addBtn);
		MGcontainer.getChildren().addAll(MGtitle, MGdesc, backBtn, MGem);
		MGpage.setTop(MGcontainer);
		MGpage.setCenter(pTable);
		MGpage.setBottom(input);
	}
	
	public void productStyle(){
		MGcontainer.setStyle("-fx-background-color: white;");
	
		MGcontainer.setMaxWidth(1000);
		
		MGtitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		MGcontainer.setMargin(MGtitle, new Insets(10));
		MGcontainer.setAlignment(Pos.TOP_CENTER);
		
		backBtn.setPadding(new Insets(10));
		backBtn.setMinWidth(100);
		backBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		backBtn.setFont(Font.font(15));
		
		buttonHB.setSpacing(10);
		buttonHB.setAlignment(Pos.CENTER);
		
		newName.setMinWidth(100);
		newDesc.setMinWidth(100);
		input.setAlignment(Pos.CENTER);
		input.setVgap(20);
		input.setHgap(20);
		
		pTable.setMaxHeight(700);

		MGcontainer.setMargin(MGem, new Insets(0, 0, 30, 0));
		
		MGpage.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void product() {
		initComponent();
		productStyle();
	}
	
	public ViewManageVendor(String email) {
		this.email = email;
		
		initProduct();
		product();
		Main.redirect(MGscene);
	}

	public Scene getMGscene() {
		return MGscene;
	}
	
	public void setErrorMessage(String message) {
		MGem.setText(message);
	}

	public void setEditButton(EventHandler<ActionEvent> handler) {
		editBtn.setOnAction(handler);
	}
	
	public void setbackButton(EventHandler<ActionEvent> handler) {
		backBtn.setOnAction(handler);
	}
	
	public void setAddButtonAction(EventHandler<ActionEvent> handler) {
	    addBtn.setOnAction(handler);
	}
	
	public TextField getNameTF() {
		return nameTF;
	}

	public TextField getDescTF() {
		return descTF;
	}

	public TableView<Products> getProductTable(){
		return pTable;
	}
	
	public void updateTable(ArrayList<Products> updatedProducts) {
	    ObservableList<Products> updatedData = FXCollections.observableArrayList(updatedProducts);
	    pTable.setItems(updatedData); // This updates the TableView with the new data
	}

	public void setpData(ObservableList<Products> pData) {
	        this.pData = pData;
	        pTable.setItems(pData); // Bind the table to the data
	}
}