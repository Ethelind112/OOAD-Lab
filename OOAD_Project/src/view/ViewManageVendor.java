package view;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
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
	FlowPane btn;
	VBox MGcontainer;
	Label MGtitle, MGdesc, MGem;
	TableView<Products> pTable;
	Button editBtn, backBtn, addBtn;
	ObservableList<Products> pData;
	
	public void initProduct() {
		MGpage = new BorderPane();
		btn = new FlowPane();
		MGcontainer = new VBox();
		MGscene = new Scene(MGpage, 1000, 700);
		MGtitle = new Label("Manage Product");
		MGdesc = new Label("Manage your product's information from the table below");
		MGem = new Label("");
		editBtn = new Button("Edit");
		backBtn = new Button("Back");
		addBtn = new Button("Add");
		
		pTable = new TableView<>();
	}
	
	public void setTable(){
		pData = FXCollections.observableArrayList();
		
		TableColumn<Products,String> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("product_id"));
		idColumn.setMinWidth(MGcontainer.getWidth()/3);
		
		TableColumn<Products,String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("product_name"));
		nameColumn.setMinWidth(MGcontainer.getWidth()/3);
		
		TableColumn<Products,String> descColumn = new TableColumn<>("Description");
		descColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("product_description"));
		descColumn.setMinWidth(MGcontainer.getWidth()/3);
		
		pTable.getColumns().addAll(idColumn, nameColumn, descColumn);
		pTable.setItems(pData);
	}
	
	public void initComponent() {
		setTable();
		
		btn.getChildren().addAll(editBtn, addBtn);
		MGcontainer.getChildren().addAll(MGtitle, MGdesc, btn, MGem, pTable);
		MGpage.setCenter(MGcontainer);
		MGpage.setBottom(backBtn);
	}
	
	public void productStyle(){
		MGcontainer.setStyle("-fx-background-color: white;");
	
		MGcontainer.setMaxWidth(900);
		
		MGtitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		MGcontainer.setMargin(MGtitle, new Insets(50,0,10,0));
		MGcontainer.setAlignment(Pos.TOP_CENTER);
		
		editBtn.setPadding(new Insets(10, 0, 10, 0));
		editBtn.setMinWidth(100);
		editBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		editBtn.setFont(Font.font(15));
		
		backBtn.setPadding(new Insets(10));
		backBtn.setMinWidth(100);
		backBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		backBtn.setFont(Font.font(15));
		
		addBtn.setPadding(new Insets(10, 0, 10, 0));
		addBtn.setMinWidth(100);
		addBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		addBtn.setFont(Font.font(15));
		
		btn.setHgap(20);
		
		MGcontainer.setMargin(editBtn, new Insets(20, 0, 10, 0));
		MGcontainer.setMargin(backBtn, new Insets(20, 0, 10, 0));
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
	
	public TableView<Products> getInvitationTable(){
		return pTable;
	}
	
	public void setInvitationList(ObservableList<Products> products) {
		pData.setAll(products);
	}	
}