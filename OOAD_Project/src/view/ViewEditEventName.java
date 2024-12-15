package view;

import controller.EventOrganizerController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewEditEventName extends JPanel {

    private JTextField eventIDField;
    private JTextField newEventNameField;
    private JLabel resultLabel;
    private EventOrganizerController controller;

    public ViewEditEventName(EventOrganizerController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2,2,5,5));
        formPanel.add(new JLabel("Event ID:"));
        eventIDField = new JTextField();
        formPanel.add(eventIDField);

        formPanel.add(new JLabel("New Event Name:"));
        newEventNameField = new JTextField();
        formPanel.add(newEventNameField);

        add(formPanel, BorderLayout.CENTER);

        JButton editButton = new JButton("Edit Event Name");
        resultLabel = new JLabel("");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventID = eventIDField.getText().trim();
                String newName = newEventNameField.getText().trim();
                String result = controller.editEventName(eventID, newName);
                resultLabel.setText(result);
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(editButton, BorderLayout.WEST);
        bottomPanel.add(resultLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
