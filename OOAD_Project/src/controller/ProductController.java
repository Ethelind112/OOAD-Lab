package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Event;
import model.Products;
import view.Main;
import view.ViewAddProducts;
import view.ViewEditProduct;
import view.ViewManageVendor;

public class ProductController {
	private ViewManageVendor managevendor;
	private ViewAddProducts addproduct;
	private ViewEditProduct editproduct;
	private String email;
	
	public ProductController() {
		
	}
	
	public ProductController(ViewManageVendor managevendor,ViewAddProducts addProduct, String email) {
		this.managevendor = managevendor;
		this.email = email;
		
		//ngatur logika click addbutton
		managevendor.setAddButtonAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
//				Main.toAddProductPage(email);
			}
		});
		
//		//ngatur logika click editbutton
//		managevendor.setEditButton(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				Products selectedProduct = managevendor.getInvitationTable().getSelectionModel().getSelectedItem();
//				if(selectedProduct != null) {
//					managevendor.setErrorMessage("");
//					
////					mengambil eventid yang diclick
//					String eventID = selectedInvitation.getEvent_id();
//					
////					proses accept invitation menggunakan invitation class (mengikuti sequence diagram)
//					invitation.acceptInvitation(eventID);
//					
//					invitationView.setTable();
//					
//				}else {
////					bila tidak ada yang diselect maka minta user untuk click invitationnya
//					invitationView.setErrorMessage("Choose the invitation bellow");
//				}
//				Main.toEditProduct(email);
//			}
//		});
//		
		//ngatur logika click backbutton
		managevendor.setbackButton(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.toEventPageVendor(email);
			}
		});
	}

}
