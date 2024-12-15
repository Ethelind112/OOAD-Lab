package view;

import controller.EventOrganizerController;
import model.EventOrganizer;
import javax.swing.*;
import java.awt.*;

public class ViewEventOrganizer extends JFrame {

    private EventOrganizerController controller;

    public ViewEventOrganizer(EventOrganizer organizer) {
        this.controller = new EventOrganizerController(organizer);
        setTitle("Event Organizer Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("Create Event", new ViewCreateEvent(controller));
        tabbedPane.addTab("Add Vendor", new ViewAddVendor(controller));
        tabbedPane.addTab("Add Guest", new ViewAddGuest(controller));
        tabbedPane.addTab("Edit Event Name", new ViewEditEventName(controller));
        tabbedPane.addTab("View Organized Events", new ViewOrganizedEvents(controller));
        tabbedPane.addTab("View Transaction Details", new ViewTransactionDetails(controller));

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        EventOrganizer organizer = new EventOrganizer("00001", "organizer@example.com", "John Doe", "password", "Organizer");
        ViewEventOrganizer view = new ViewEventOrganizer(organizer);
        view.setVisible(true);
    }
}
