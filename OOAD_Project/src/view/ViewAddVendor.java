package view;

import controller.EventOrganizerController;
import model.Vendor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAddVendor extends JPanel {

    private JTextField eventIDField;
    private JTextField vendorIDField;
    private JTextField vendorEmailField;
    private JTextField vendorNameField;
    private JTextField vendorPasswordField;
    private JLabel resultLabel;
    private EventOrganizerController controller;

    public ViewAddVendor(EventOrganizerController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        JLabel instructions = new JLabel("Enter the event and vendor details to add a vendor to the event.");
        add(instructions, BorderLayout.NORTH);

        formPanel.add(new JLabel("Event ID:"));
        eventIDField = new JTextField();
        formPanel.add(eventIDField);

        formPanel.add(new JLabel("Vendor ID:"));
        vendorIDField = new JTextField();
        formPanel.add(vendorIDField);

        formPanel.add(new JLabel("Vendor Email:"));
        vendorEmailField = new JTextField();
        formPanel.add(vendorEmailField);

        formPanel.add(new JLabel("Vendor Name:"));
        vendorNameField = new JTextField();
        formPanel.add(vendorNameField);

        formPanel.add(new JLabel("Vendor Password:"));
        vendorPasswordField = new JTextField();
        formPanel.add(vendorPasswordField);

        add(formPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Vendor to Event");
        resultLabel = new JLabel("");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventID = eventIDField.getText().trim();
                String vID = vendorIDField.getText().trim();
                String vEmail = vendorEmailField.getText().trim();
                String vName = vendorNameField.getText().trim();
                String vPassword = vendorPasswordField.getText().trim();

                Vendor vendor = new Vendor();
                String result = controller.addVendor(eventID, vendor);
                resultLabel.setText(result);
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(addButton, BorderLayout.WEST);
        bottomPanel.add(resultLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
