package view;

import controller.EventOrganizerController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCreateEvent extends JPanel {

    private JTextField eventNameField;
    private JTextField eventDateField;
    private JTextField eventLocationField;
    private JTextArea eventDescriptionArea;
    private JLabel resultLabel;
    private EventOrganizerController controller;

    public ViewCreateEvent(EventOrganizerController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5,2,5,5));
        
        formPanel.add(new JLabel("Event Name:"));
        eventNameField = new JTextField();
        formPanel.add(eventNameField);

        formPanel.add(new JLabel("Event Date (YYYY-MM-DD):"));
        eventDateField = new JTextField();
        formPanel.add(eventDateField);

        formPanel.add(new JLabel("Event Location:"));
        eventLocationField = new JTextField();
        formPanel.add(eventLocationField);

        formPanel.add(new JLabel("Event Description:"));
        eventDescriptionArea = new JTextArea(3,20);
        JScrollPane descScroll = new JScrollPane(eventDescriptionArea);
        formPanel.add(descScroll);

        add(formPanel, BorderLayout.CENTER);

        JButton createButton = new JButton("Create Event");
        resultLabel = new JLabel("");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = eventNameField.getText().trim();
                String date = eventDateField.getText().trim();
                String location = eventLocationField.getText().trim();
                String description = eventDescriptionArea.getText().trim();

                String result = controller.createEvent(name, date, location, description);
                resultLabel.setText(result);
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(createButton, BorderLayout.WEST);
        bottomPanel.add(resultLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
