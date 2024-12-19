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
		this.product = new Products();
	}
	
	public String ManageVendorInput(String desc, String name) {
		if (product == null) {
			return "Product model is not initialized.";
		}
        return product.ManageVendorInput(desc, name);
    }
	
	public String addProduct(String products_name, String products_desc) {
		if (product == null) {
			return "Product model is not initialized.";
		}
        return product.addProduct(products_name, products_desc);
    }
	
	public String updateProductDetails(String id, String newName, String newDescription) {
		if (product == null) {
			return "Product model is not initialized.";
		}
        return product.updateProductDetails(id, newName, newDescription);
    }
	
	public ArrayList<Products> getProductData(){
		if (product == null) {
			return new ArrayList<>();
		}
        return product.getProductData();
    }
}
