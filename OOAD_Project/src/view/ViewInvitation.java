package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Invitation;

public class ViewInvitation {

	Scene invitationScene;
	VBox invitationVb, invitationContainer;
	Label invitationTitle, invitationDescription;
	TableView<Invitation> invitationTable;
	Button acceptBtn;
	
	public void initInvitation() {
		invitationVb = new VBox();
		invitationContainer = new VBox();
		invitationScene = new Scene(invitationVb, 1000, 700);
		invitationTitle = new Label("Invitation");
		invitationDescription = new Label("This is your invitation, select the from invitation from the table bellow before clicking on the accept button.");
		acceptBtn = new Button("Accept");
		invitationTable = new TableView<>();
		
	}
	
	public void initInvitationComponent() {
		TableColumn<Invitation,String> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<Invitation, String>("invitation_id"));
		
		invitationTable.getColumns().addAll(idColumn);
		
		invitationContainer.getChildren().addAll(invitationTitle, invitationDescription, acceptBtn,invitationTable);
		invitationVb.getChildren().add(invitationContainer);
	}
	
	public void invitationStyling(){
		invitationVb.setAlignment(Pos.TOP_CENTER); //menengahkan posisi container
		invitationVb.setStyle("-fx-background-color: white;");
	
		invitationContainer.setMaxWidth(900);
		
		invitationTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		invitationContainer.setMargin(invitationTitle, new Insets(50,0,10,0));
		invitationContainer.setAlignment(Pos.TOP_CENTER);
		
		acceptBtn.setPadding(new Insets(10, 0, 10, 0));
		acceptBtn.setMinWidth(100);
		acceptBtn.setStyle("-fx-text-fill: white; -fx-background-color: #133E87;");
		acceptBtn.setFont(Font.font(15));
		
		invitationContainer.setMargin(acceptBtn, new Insets(20, 0, 30, 0));
	}
	
	public void invitation() {
		initInvitationComponent();
		invitationStyling();
	}
	
	public ViewInvitation() {
		initInvitation();
		invitation();
		Main.redirect(invitationScene);
	}

}
