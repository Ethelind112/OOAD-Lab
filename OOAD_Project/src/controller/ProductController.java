package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Event;
import model.Products;
import view.Main;
import view.ViewManageVendor;

public class ProductController {
	private String email;
	private Products product;
	
	public ProductController() {
		
	}
	
	public String ManageVendorInput(String desc, String name) {
		String message = product.ManageVendorInput(desc, name);
		return message;
	}
	
	public String addProduct(String products_name, String products_desc) {
		String message = product.addProduct(products_name, products_desc);
		return message;
	}
	
	public String updateProductDetails(String id, String newName, String newDescription) {
		String message = product.updateProductDetails(id, newName, newDescription);
		return message;
	}
	
	public ArrayList<Products> getProductData(){
		return product.getProductData();
	}

}
