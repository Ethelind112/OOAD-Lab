package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Event;
import model.Products;
import model.User;
import model.Vendor;
import view.Main;
import view.ViewChangeProfile;
import view.ViewEventDetails;
import view.ViewEvents;
import view.ViewInvitation;
import view.ViewManageVendor;

public class VendorController {
	private static User user = new User();
	
	private ViewEvents acceptedInvView;
	private ViewManageVendor managevendor;
	private ProductController productCont;
	private String email;
	private Products products;
	
	public VendorController() {
		
	}
	
	public VendorController(ViewManageVendor managevendor, String email) {
		this.managevendor = managevendor;
		this.email = email;
		User user = new User().getUserByEmail(email);
		
		//mengatur logika supaya nameTF dan descTF bisa dipenuhin oleh data selected item onclick
		managevendor.getProductTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			if(newSelection != null) {
				managevendor.getNameTF().setText(newSelection.getProducts_name());
				managevendor.getDescTF().setText(newSelection.getProducts_description());
			}
		});
		
		//set on click logic for add btn in ViewManageVendor
		managevendor.setAddButtonAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//run adding product to product
				String message = manageVendorInput(managevendor.getDescTF().getText(), managevendor.getNameTF().getText());
				refreshTableData();
				managevendor.setErrorMessage(message);
			}
		});

		//set on click logic for back btn in ViewManageVendor
		managevendor.setbackButton(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.toEventPageVendorFromManageVendor(user.getUser_email());
			}
		});

		//set on click logic for edit btn in ViewManageVendor
		managevendor.setEditButton(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//pas click, dia ngisi tf dengan data
				//baru kita edit text
				//nanti dia ambil text di tf baru panggil fungsi update
				String id = managevendor.getProductTable().getSelectionModel().getSelectedItem().getProducts_id();
				String tempName = managevendor.getProductTable().getSelectionModel().getSelectedItem().getProducts_name();
				String tempDesc = managevendor.getProductTable().getSelectionModel().getSelectedItem().getProducts_description();
				
				//cek keunikan input nama dan description
				String inputCheck = manageVendorInput(tempDesc, tempName);
				
				if(inputCheck.equals("success")) {
					String message = updateProductDetails(id, managevendor.getNameTF().getText(), managevendor.getDescTF().getText());
					refreshTableData();
					managevendor.setErrorMessage(message);
				}
				else {
					managevendor.setErrorMessage(inputCheck);
				}
			}
		});
	}
	
	public VendorController(ViewEvents acceptedInvView, String email) {
		this.acceptedInvView = acceptedInvView;
		this.email = email;
		
		loadEventList();
		
		acceptedInvView.setVendorMenu();
		
		acceptedInvView.setInvitationMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toInvitationPage(email);
			}
		});
		
		acceptedInvView.setChangeProfileMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toChangeProfilePage(email);
			}
		});
		
		acceptedInvView.setEventDetailButton(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Event selectedEvent = acceptedInvView.getEventTable().getSelectionModel().getSelectedItem();
				Main.toEventDetailPage(email, selectedEvent.getEvent_id());
			}
			
		});
		
		acceptedInvView.setManageVendorMenu(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.toEventPageVendor(email);
			}
		});
	}
	
	//logic to clear and reload data for tableview
	public void refreshTableData() {
	    ObservableList<Products> productData = FXCollections.observableArrayList();
	    ArrayList<Products> products = getProductData();
	    productData.addAll(products);
	    managevendor.setpData(productData); // Update the ViewManageVendor table
	}

	
	public ArrayList<Products> getProductData(){
		this.productCont = new ProductController();
		return productCont.getProductData();
	}
//	
	public void loadEventList() {
		ArrayList<Event> invitation = viewAcceptedEvents(email);
		
		ObservableList<Event> eventData = FXCollections.observableArrayList(invitation);
		acceptedInvView.setEventList(eventData);
	}
	
	public String acceptInvitation(String eventID) {
		Vendor vendor = new Vendor();
		return vendor.acceptInvitation(eventID);
	}
	
	public ArrayList<Event> viewAcceptedEvents(String email){
		Vendor vendor = new Vendor();
		return vendor.viewAcceptedEvents(email);
	}
	
	
	public String addProducts(String name, String desc) {
		this.productCont = new ProductController();
		return productCont.addProduct(name, desc);
	}
	
	public String manageVendorInput(String desc, String name) {
		this.productCont = new ProductController();
		return productCont.ManageVendorInput(desc, name);
	}
	
	public String updateProductDetails(String id, String newName, String newDescription) {
		this.productCont = new ProductController();
		return productCont.updateProductDetails(id, newName, newDescription);
	}
	
}

