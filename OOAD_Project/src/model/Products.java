package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import util.Connect;

public class Products {

	private String products_id, products_name, products_desc;
	private Connect connect = Connect.getInstance();
	
	public Products() {
		
	}

	public Products(String products_id, String products_name, String products_description) {
		this.products_id = products_id;
		this.products_name = products_name;
		this.products_desc = products_description;
	}
	
	//get the product information through products_name
	public Products getProductsByName(String name) {
		String readDateQuery = "SELECT * FROM products WHERE products_name = ?";
		
		PreparedStatement ps = connect.prepareStatement(readDateQuery);
		ResultSet readData = null;
		
		try {
			ps.setString(1, name);
			readData = ps.executeQuery();
			
//			bila ditemukan, buat products model baru untuk di return
			if(readData != null && readData.next())
				return new Products(readData.getString("products_id"), readData.getString("products_name"), readData.getString("products_desc"));
		} catch (SQLException e) {
			return null;
		}
		
//		bila tidak ditemukan maka return null
		return null;
	}
	
	//validating the inputted product name and description
	public String ManageVendorInput(String desc, String name) {
		
		if(name.isEmpty()) {
			return "Please input product's name";
		}
		else {
//			mendapatkan products dengan email dan name yang ada di parameter, digunakan untuk melakukan pengecekan keunikan
			Products product = getProductsByName(name);
		
//			melakukan pengecekan bila user yang didapatkan dari username sudah terdaftar atau belum	
			if(product != null) {
				//Memperbolehkan penambahan barang dengan nama sama tapi beda description
				if(product.getProducts_name().equalsIgnoreCase(name) && product.getProducts_description().equalsIgnoreCase(desc)) {
					return "There exist such products already";
				}
			}
			
//			melakukan pengecekan description
			if(desc.isEmpty()) {
				return "Please Fill All The Field";
			}else if(desc.length() > 200) {
				return "Description are at most 200 characters long";
			}
		}
//		mengembalikan success message bila berhasil
		return "success";
	}

	//fungsi untuk menambah products ke dalam database
	public String addProduct(String products_name, String products_desc) {

//		mengambil semua user dari database
		String readDateQuery = "SELECT * FROM products";
		ResultSet readData = connect.execute(readDateQuery);
		
//		Generate ID
		int count = 0;
		
		try {
//			menghitung jumlah user yang sudah ada
			while(readData.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		membuat format ID
		DecimalFormat formats = new DecimalFormat("00000");
		String id = formats.format(count + 1);
		
//		memasukan data ke database
		String insertQuery = "INSERT INTO products (products_id, products_name, products_desc) VALUES (?, ?, ?)";
		PreparedStatement ps = connect.prepareStatement(insertQuery);
		
		try {
			ps.setString(1, id);
			ps.setString(2, products_name);
			ps.setString(3, products_desc);
			ps.executeUpdate();
		} catch (SQLException e) {
//			mengirim error message bila gagal
			return "fail";
		}
		
//		mengirimkan success message bila berhasil memasukan data ke database
		return "success";
	}
	
	
	//fungsi edit data product di table saat mengklik button edit di ViewManageVendor
	public String manageVendorE(String id, String newName, String newDescription) {
        String updateQuery = "UPDATE products SET products_name = ?, products_desc = ? WHERE products_id = ?";
        try (PreparedStatement ps = connect.prepareStatement(updateQuery)) {
            ps.setString(1, newName);
            ps.setString(2, newDescription);
            ps.setString(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating product.";
        }
        return "success";
    }
	
	public ArrayList<Products> getProductData() {
	    ArrayList<Products> productList = new ArrayList<>();
	    String readDateQuery = "SELECT * FROM products"; 
	    
	    PreparedStatement ps = connect.prepareStatement(readDateQuery);
		ResultSet readData = connect.execute(readDateQuery);

	    try {
	        while (readData != null && readData.next()) {
	            String id = readData.getString("products_id");
	            String name = readData.getString("products_name");
	            String desc = readData.getString("products_desc");
	            productList.add(new Products(id, name, desc));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return productList;
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
		return products_desc;
	}

	public void setProducts_description(String products_description) {
		this.products_desc = products_description;
	}
}