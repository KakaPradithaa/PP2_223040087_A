package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    private JTextField txtName = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JButton btnAdd = new JButton("Add User");
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnExport = new JButton("Export");
    private JList<String> userList = new JList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JLabel statusLabel = new JLabel("Status: Ready", JLabel.CENTER);

    public UserView() {
        setTitle("User Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for input fields (name and email)
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));  // Added spacing between components
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);

        // Panel for buttons (Add, Refresh, Export)
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnExport);

        // Add button panel to the input panel
        panel.add(buttonPanel);

        // User List
        userList.setModel(listModel);
        JScrollPane scrollPane = new JScrollPane(userList);

        // Add components to the frame
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH); // Status label added at the bottom
    }

    // Method to get name input
    public String getNameInput() {
        return txtName.getText();
    }

    // Method to get email input
    public String getEmailInput() {
        return txtEmail.getText();
    }

    // Method to display the list of users
    public void setUserList(String[] users) {
        listModel.clear();
        for (String user : users) {
            listModel.addElement(user);
        }
    }

    // Method to set status label text
    public void setStatusLabel(String status) {
        statusLabel.setText("Status: " + status);
    }

    // Method to add listener for Add button
    public void addAddUserListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    // Method to add listener for Refresh button
    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    // Method to add listener for Export button
    public void addExportListener(ActionListener listener) {
        btnExport.addActionListener(listener);
    }
}
