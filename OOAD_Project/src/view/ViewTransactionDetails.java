package view;

import controller.EventOrganizerController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTransactionDetails extends JPanel {

    private JTextField eventIDField;
    private JTextArea resultTextArea;
    private EventOrganizerController controller;

    public ViewTransactionDetails(EventOrganizerController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1,2,5,5));
        topPanel.add(new JLabel("Event ID:"));
        eventIDField = new JTextField();
        topPanel.add(eventIDField);

        add(topPanel, BorderLayout.NORTH);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton viewButton = new JButton("View Transaction Details");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventID = eventIDField.getText().trim();
                String result = controller.viewOrganizedTransactionDetails(eventID);
                resultTextArea.setText(result);
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(viewButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
