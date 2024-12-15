package view;

import controller.EventOrganizerController;
import model.Guest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAddGuest extends JPanel {

    private JTextField eventIDField;
    private JTextField guestIDField;
    private JTextField guestEmailField;
    private JTextField guestNameField;
    private JTextField guestPasswordField;
    private JLabel resultLabel;
    private EventOrganizerController controller;

    public ViewAddGuest(EventOrganizerController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(7,2,5,5));

        JLabel instructions = new JLabel("Enter the event and guest details below to send an invitation. The guest will need to accept the invitation later.");
        formPanel.add(instructions);
        formPanel.add(new JLabel("")); 

        formPanel.add(new JLabel("Event ID:"));
        eventIDField = new JTextField();
        formPanel.add(eventIDField);

        formPanel.add(new JLabel("Guest ID:"));
        guestIDField = new JTextField();
        formPanel.add(guestIDField);

        formPanel.add(new JLabel("Guest Email:"));
        guestEmailField = new JTextField();
        formPanel.add(guestEmailField);

        formPanel.add(new JLabel("Guest Name:"));
        guestNameField = new JTextField();
        formPanel.add(guestNameField);

        formPanel.add(new JLabel("Guest Password:"));
        guestPasswordField = new JTextField();
        formPanel.add(guestPasswordField);

        add(formPanel, BorderLayout.CENTER);

        JButton inviteButton = new JButton("Send Invitation");
        resultLabel = new JLabel("");
        inviteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventID = eventIDField.getText().trim();
                String gID = guestIDField.getText().trim();
                String gEmail = guestEmailField.getText().trim();
                String gName = guestNameField.getText().trim();
                String gPassword = guestPasswordField.getText().trim();

                Guest guest = new Guest();
                String result = controller.addGuest(eventID, guest);
                resultLabel.setText(result);
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inviteButton, BorderLayout.WEST);
        bottomPanel.add(resultLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
