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
		
		refreshTableData();
		
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
				String name = managevendor.getNameTF().getText().trim();
			    String desc = managevendor.getDescTF().getText().trim();
			    
			    //validasi isi data TextField name dan desc
			    if (name.isEmpty() || desc.isEmpty()) 
			    {
			        managevendor.setErrorMessage("Name and description cannot be empty!");
			        return;
			    }
			    
			    //pemanggilan fungsi cek keunikan data yang diisi
				String message = manageVendorInput(managevendor.getDescTF().getText(), managevendor.getNameTF().getText());
				if (message.equals("success")) 
				{
			        addProducts(name, desc);
			        refreshTableData();
			        managevendor.setErrorMessage("Product added successfully.");
			    } 
				else 
				{
			        managevendor.setErrorMessage(message);
			    }
			}
		});


		//set on click logic for back btn in ViewManageVendor
		managevendor.setbackButton(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.toInvitationPage(email);
			}
		});

		//set on click logic for edit btn in ViewManageVendor
		managevendor.setEditButton(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//pas click, dia ngisi tf dengan data
				//baru kita edit text
				//nanti dia ambil text di tf baru panggil fungsi update
				Products selectedProduct = managevendor.getProductTable().getSelectionModel().getSelectedItem();
				
				//validasi apakah product sudah di click
				if (selectedProduct == null) {
	                managevendor.setErrorMessage("Please select a product to edit!");
	                return;
	            }
				
				String newName = managevendor.getNameTF().getText().trim();
			    String newDesc = managevendor.getDescTF().getText().trim();
				
				//cek apakah name dan description ada isi
				if (newName.isEmpty() || newDesc.isEmpty()) {
		                managevendor.setErrorMessage("Name and description cannot be empty!");
		                return;
		        }
				
				//cek keunikan input nama dan description
				String message = manageVendorInput(newDesc, newName);
				
				if(message.equals("success")) {
					updateProductDetails(selectedProduct.getProducts_id(), newName, newDesc);
					refreshTableData();
					managevendor.setErrorMessage(message);
				}
				else {
					managevendor.setErrorMessage(message);
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
	    ArrayList<Products> updatedProducts = new Products().getProductData();
//	    ObservableList<Products> productData = FXCollections.observableArrayList(products);
//	    managevendor.setpData(productData); // Update the ViewManageVendor table
	    managevendor.updateTable(updatedProducts);
	}

	
	public ArrayList<Products> getProductData(){
		if (productCont == null) {
            productCont = new ProductController();
        }
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
		if (productCont == null) {
            productCont = new ProductController();
        }
		//pemanggilan metode addproduct di product Controller
		String result = productCont.addProduct(name, desc);
		
		//validasi hasil fungsi diatas
		if ("success".equals(result))
        {
            refreshTableData(); // Ensure the table is refreshed after adding a product
        }
		return result;
	}
	
	public String manageVendorInput(String desc, String name) {
		if (productCont == null) {
            productCont = new ProductController();
        }
		return productCont.ManageVendorInput(desc, name);
	}
	
	public String updateProductDetails(String id, String newName, String newDescription) {
		if (productCont == null) {
            productCont = new ProductController();
        }
		return productCont.updateProductDetails(id, newName, newDescription);
	}
	
}

