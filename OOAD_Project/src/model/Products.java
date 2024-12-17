package model;

import util.Connect;

public class Products {

	private String products_id, products_name, products_description;
	private Connect connect = Connect.getInstance();
	
	public Products() {
		
	}

	public Products(String products_id, String products_name, String products_description) {
		this.products_id = products_id;
		this.products_name = products_name;
		this.products_description = products_description;
	}

	public String getProducts_id() {
		return products_id;
	}

	public void setProducts_id(String products_id) {
		this.products_id = products_id;
	}

	public String getProducts_name() {
		return products_name;
	}

	public void setProducts_name(String products_name) {
		this.products_name = products_name;
	}

	public String getProducts_description() {
		return products_description;
	}

	public void setProducts_description(String products_description) {
		this.products_description = products_description;
	}
}