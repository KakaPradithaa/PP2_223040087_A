<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuan6.Tugas1;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SDRegistrationApp extends JFrame {
    private JPanel mainPanel;
    private JTable registrationTable;
    private DefaultTableModel tableModel;

    public SDRegistrationApp() {
        setTitle("Pendaftaran Murid SD Baru");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuStudent = new JMenu("Murid");
        JMenuItem addStudentItem = new JMenuItem("Tambah Murid");
        JMenuItem viewStudentsItem = new JMenuItem("Lihat Murid");

        // Menu Item Actions
        addStudentItem.addActionListener(e -> openAddStudentForm());
        viewStudentsItem.addActionListener(e -> openViewStudents());

        menuStudent.add(addStudentItem);
        menuStudent.add(viewStudentsItem);
        menuBar.add(menuStudent);
        setJMenuBar(menuBar);

        // Main Panel
        mainPanel = new JPanel(new CardLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Table to show student data
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nama");
        tableModel.addColumn("Email");
        tableModel.addColumn("Usia");
        tableModel.addColumn("Jenis Kelamin");
        tableModel.addColumn("Hobi");
        tableModel.addColumn("Alamat");
        tableModel.addColumn("Jarak Rumah ke Sekolah");

        registrationTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(registrationTable);
        mainPanel.add(scrollPane, "viewStudents");
    }

    private void openAddStudentForm() {
        JPanel addStudentPanel = new JPanel();
        addStudentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Components
        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(6, 5, 12, 1));
        JRadioButton maleButton = new JRadioButton("Laki-laki");
        JRadioButton femaleButton = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        
        JCheckBox sportsCheck = new JCheckBox("Olahraga");
        JCheckBox artCheck = new JCheckBox("Seni");
        JCheckBox musicCheck = new JCheckBox("Musik");
        
        JTextField addressField = new JTextField(20);
        
        // JSlider for distance to school
        JLabel distanceLabel = new JLabel("Jarak Rumah ke Sekolah (meter):");
        JSlider distanceSlider = new JSlider(0, 5000, 1000);
        distanceSlider.setMajorTickSpacing(1000);
        distanceSlider.setMinorTickSpacing(100);
        distanceSlider.setPaintTicks(true);
        distanceSlider.setPaintLabels(true);

        JButton submitButton = new JButton("Daftar");

        // Submit button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                int age = (int) ageSpinner.getValue();
                String gender = maleButton.isSelected() ? "Laki-laki" : "Perempuan";
                
                String hobbies = "";
                if (sportsCheck.isSelected()) hobbies += "Olahraga, ";
                if (artCheck.isSelected()) hobbies += "Seni, ";
                if (musicCheck.isSelected()) hobbies += "Musik, ";
                if (!hobbies.isEmpty()) hobbies = hobbies.substring(0, hobbies.length() - 2); // Remove last comma

                String address = addressField.getText();
                int distance = distanceSlider.getValue();

                // Add row to table
                tableModel.addRow(new Object[]{name, email, age, gender, hobbies, address, distance});
                clearFields(nameField, emailField, ageSpinner, addressField, genderGroup, sportsCheck, artCheck, musicCheck, distanceSlider);
            }
        });

        // Adding components to addStudentPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        addStudentPanel.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(emailField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(new JLabel("Usia:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(ageSpinner, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(new JLabel("Jenis Kelamin:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(maleButton, gbc);
        gbc.gridx = 1;
        gbc.gridy++;
        addStudentPanel.add(femaleButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(new JLabel("Hobi:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(sportsCheck, gbc);
        gbc.gridx = 1;
        gbc.gridy++;
        addStudentPanel.add(artCheck, gbc);
        gbc.gridx = 1;
        gbc.gridy++;
        addStudentPanel.add(musicCheck, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(new JLabel("Alamat:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(addressField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(distanceLabel, gbc);
        gbc.gridx = 1;
        addStudentPanel.add(distanceSlider, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        addStudentPanel.add(submitButton, gbc);

        // Switch to add student panel
        mainPanel.removeAll();
        mainPanel.add(addStudentPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void openViewStudents() {
        // Switch to view students panel
        mainPanel.removeAll();
        JScrollPane scrollPane = new JScrollPane(registrationTable);
        mainPanel.add(scrollPane, "viewStudents");
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void clearFields(JTextField nameField, JTextField emailField, JSpinner ageSpinner,
                             JTextField addressField, ButtonGroup genderGroup, 
                             JCheckBox sportsCheck, JCheckBox artCheck, 
                             JCheckBox musicCheck, JSlider distanceSlider) {
        nameField.setText("");
        emailField.setText("");
        ageSpinner.setValue(6);
        addressField.setText("");
        genderGroup.clearSelection();
        sportsCheck.setSelected(false);
        artCheck.setSelected(false);
        musicCheck.setSelected(false);
        distanceSlider.setValue(1000);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SDRegistrationApp app = new SDRegistrationApp();
            app.setVisible(true);
        });
    }
}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuan6.Tugas1;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SDRegistrationApp extends JFrame {
    private JPanel mainPanel;
    private JTable registrationTable;
    private DefaultTableModel tableModel;

    public SDRegistrationApp() {
        setTitle("Pendaftaran Murid SD Baru");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuStudent = new JMenu("Murid");
        JMenuItem addStudentItem = new JMenuItem("Tambah Murid");
        JMenuItem viewStudentsItem = new JMenuItem("Lihat Murid");

        // Menu Item Actions
        addStudentItem.addActionListener(e -> openAddStudentForm());
        viewStudentsItem.addActionListener(e -> openViewStudents());

        menuStudent.add(addStudentItem);
        menuStudent.add(viewStudentsItem);
        menuBar.add(menuStudent);
        setJMenuBar(menuBar);

        // Main Panel
        mainPanel = new JPanel(new CardLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Table to show student data
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nama");
        tableModel.addColumn("Email");
        tableModel.addColumn("Usia");
        tableModel.addColumn("Jenis Kelamin");
        tableModel.addColumn("Hobi");
        tableModel.addColumn("Alamat");
        tableModel.addColumn("Jarak Rumah ke Sekolah");

        registrationTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(registrationTable);
        mainPanel.add(scrollPane, "viewStudents");
    }

    private void openAddStudentForm() {
        JPanel addStudentPanel = new JPanel();
        addStudentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Components
        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(6, 5, 12, 1));
        JRadioButton maleButton = new JRadioButton("Laki-laki");
        JRadioButton femaleButton = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        
        JCheckBox sportsCheck = new JCheckBox("Olahraga");
        JCheckBox artCheck = new JCheckBox("Seni");
        JCheckBox musicCheck = new JCheckBox("Musik");
        
        JTextField addressField = new JTextField(20);
        
        // JSlider for distance to school
        JLabel distanceLabel = new JLabel("Jarak Rumah ke Sekolah (meter):");
        JSlider distanceSlider = new JSlider(0, 5000, 1000);
        distanceSlider.setMajorTickSpacing(1000);
        distanceSlider.setMinorTickSpacing(100);
        distanceSlider.setPaintTicks(true);
        distanceSlider.setPaintLabels(true);

        JButton submitButton = new JButton("Daftar");

        // Submit button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                int age = (int) ageSpinner.getValue();
                String gender = maleButton.isSelected() ? "Laki-laki" : "Perempuan";
                
                String hobbies = "";
                if (sportsCheck.isSelected()) hobbies += "Olahraga, ";
                if (artCheck.isSelected()) hobbies += "Seni, ";
                if (musicCheck.isSelected()) hobbies += "Musik, ";
                if (!hobbies.isEmpty()) hobbies = hobbies.substring(0, hobbies.length() - 2); // Remove last comma

                String address = addressField.getText();
                int distance = distanceSlider.getValue();

                // Add row to table
                tableModel.addRow(new Object[]{name, email, age, gender, hobbies, address, distance});
                clearFields(nameField, emailField, ageSpinner, addressField, genderGroup, sportsCheck, artCheck, musicCheck, distanceSlider);
            }
        });

        // Adding components to addStudentPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        addStudentPanel.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(emailField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(new JLabel("Usia:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(ageSpinner, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(new JLabel("Jenis Kelamin:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(maleButton, gbc);
        gbc.gridx = 1;
        gbc.gridy++;
        addStudentPanel.add(femaleButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(new JLabel("Hobi:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(sportsCheck, gbc);
        gbc.gridx = 1;
        gbc.gridy++;
        addStudentPanel.add(artCheck, gbc);
        gbc.gridx = 1;
        gbc.gridy++;
        addStudentPanel.add(musicCheck, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(new JLabel("Alamat:"), gbc);
        gbc.gridx = 1;
        addStudentPanel.add(addressField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        addStudentPanel.add(distanceLabel, gbc);
        gbc.gridx = 1;
        addStudentPanel.add(distanceSlider, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        addStudentPanel.add(submitButton, gbc);

        // Switch to add student panel
        mainPanel.removeAll();
        mainPanel.add(addStudentPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void openViewStudents() {
        // Switch to view students panel
        mainPanel.removeAll();
        JScrollPane scrollPane = new JScrollPane(registrationTable);
        mainPanel.add(scrollPane, "viewStudents");
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void clearFields(JTextField nameField, JTextField emailField, JSpinner ageSpinner,
                             JTextField addressField, ButtonGroup genderGroup, 
                             JCheckBox sportsCheck, JCheckBox artCheck, 
                             JCheckBox musicCheck, JSlider distanceSlider) {
        nameField.setText("");
        emailField.setText("");
        ageSpinner.setValue(6);
        addressField.setText("");
        genderGroup.clearSelection();
        sportsCheck.setSelected(false);
        artCheck.setSelected(false);
        musicCheck.setSelected(false);
        distanceSlider.setValue(1000);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SDRegistrationApp app = new SDRegistrationApp();
            app.setVisible(true);
        });
    }
}
>>>>>>> bb08e241d3858e128b5046747775fcceb689a6d2
