package view;

import controller.EventOrganizerController;
import model.Event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewOrganizedEvents extends JPanel {

    private JTextArea eventsTextArea;
    private EventOrganizerController controller;

    public ViewOrganizedEvents(EventOrganizerController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        eventsTextArea = new JTextArea();
        eventsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(eventsTextArea);

        JButton viewButton = new JButton("View Organized Events");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Event> events = controller.viewOrganizedEvents();
                displayEvents(events);
            }
        });

        add(viewButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void displayEvents(ArrayList<Event> events) {
        eventsTextArea.setText("");
        if (events.isEmpty()) {
            eventsTextArea.setText("No events found.");
        } else {
            for (Event ev : events) {
                eventsTextArea.append("Event ID: " + ev.getEvent_id() + "\n");
                eventsTextArea.append("Name: " + ev.getEvent_name() + "\n");
                eventsTextArea.append("Date: " + ev.getEvent_date() + "\n");
                eventsTextArea.append("Location: " + ev.getEvent_location() + "\n");
                eventsTextArea.append("Description: " + ev.getEvent_description() + "\n");
                eventsTextArea.append("Organizer ID: " + ev.getOrganizer_id() + "\n");
                eventsTextArea.append("-----------------------------------------------------\n");
            }
        }
    }
}
