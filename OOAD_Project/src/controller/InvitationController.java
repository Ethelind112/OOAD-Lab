package controller;

import java.util.ArrayList;

import model.Event;
import model.Invitation;
import view.ViewEvents;

public class InvitationController {

	public InvitationController() {
		
	}
	
	public ArrayList<Event> getInvitations(String email) {
		Invitation invitation = new Invitation();
		return invitation.getInvitations(email);
	}
}
