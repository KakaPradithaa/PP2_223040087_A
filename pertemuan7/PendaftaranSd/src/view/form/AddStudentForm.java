package pertemuan7.PendaftaranSd.src.view.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import pertemuan6.src.dao.StudentDAO;
import pertemuan6.src.model.Student;

public class AddStudentForm extends JPanel {
    private JPanel mainPanel;
    private JPanel addStudentPanel;
    private JPanel viewStudentPanel;
    private JTextField nameField, emailField, addressField;
    private JSpinner ageSpinner;
    private JRadioButton maleButton, femaleButton;
    private JCheckBox sportsCheck, artCheck, musicCheck;
    private JSlider distanceSlider;
    private JButton submitButton, updateButton, deleteButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentDAO studentDAO;
    private int selectedStudentId = -1;

    public AddStudentForm() throws SQLException {
        studentDAO = new StudentDAO();

        mainPanel = new JPanel(new CardLayout());

        addStudentPanel = createAddStudentPanel();
        viewStudentPanel = createViewStudentPanel();

        mainPanel.add(addStudentPanel, "AddStudent");
        mainPanel.add(viewStudentPanel, "ViewStudent");

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        switchPanel("ViewStudent");

        JPanel navPanel = new JPanel();
        JButton addStudentButton = new JButton("Tambah Siswa");
        JButton viewStudentButton = new JButton("Lihat Siswa");
        navPanel.add(addStudentButton);
        navPanel.add(viewStudentButton);

        addStudentButton.addActionListener(e -> switchPanel("AddStudent"));
        viewStudentButton.addActionListener(e -> switchPanel("ViewStudent"));

        add(navPanel, BorderLayout.NORTH);
    }

    private JPanel createAddStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        addressField = new JTextField(20);
        ageSpinner = new JSpinner(new SpinnerNumberModel(6, 5, 12, 1));

        maleButton = new JRadioButton("Laki-laki");
        femaleButton = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        sportsCheck = new JCheckBox("Olahraga");
        artCheck = new JCheckBox("Seni");
        musicCheck = new JCheckBox("Musik");

        distanceSlider = new JSlider(0, 5000, 1000);
        distanceSlider.setMajorTickSpacing(1000);
        distanceSlider.setPaintTicks(true);
        distanceSlider.setPaintLabels(true);

        submitButton = new JButton("Daftar");

        addFormField(panel, gbc, "Nama:", nameField, 0);
        addFormField(panel, gbc, "Email:", emailField, 1);
        addFormField(panel, gbc, "Usia:", ageSpinner, 2);
        addFormField(panel, gbc, "Jenis Kelamin:", maleButton, 3);
        panel.add(femaleButton, setGbcPosition(gbc, 1, 4));
        addFormField(panel, gbc, "Hobi:", sportsCheck, 5);
        panel.add(artCheck, setGbcPosition(gbc, 1, 6));
        panel.add(musicCheck, setGbcPosition(gbc, 1, 7));
        addFormField(panel, gbc, "Alamat:", addressField, 8);
        addFormField(panel, gbc, "Jarak ke Sekolah:", distanceSlider, 9);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        submitButton.addActionListener(e -> {
            if (selectedStudentId == -1) {
                addStudent(); // New student
            } else {
                updateStudent(); // Update existing student
            }
        });

        return panel;
    }

    private JPanel createViewStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Nama", "Email", "Usia", "Jenis Kelamin", "Hobi", "Alamat", "Jarak ke Sekolah"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> updateStudentFromTable());
        deleteButton.addActionListener(e -> deleteStudentFromTable());

        loadStudentData();
        return panel;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int gridY) {
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private GridBagConstraints setGbcPosition(GridBagConstraints gbc, int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        return gbc;
    }

    private void switchPanel(String panelName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, panelName);
    }

    private void loadStudentData() {
        tableModel.setRowCount(0);
        List<Student> students = studentDAO.getAllStudents();
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getAge(),
                student.getGender(),
                student.getHobbies(),
                student.getAddress(),
                student.getDistance()
            });
        }
    }

    private void addStudent() {
        String name = nameField.getText();
        String email = emailField.getText();
        int age = (int) ageSpinner.getValue();
        String gender = maleButton.isSelected() ? "Laki-laki" : "Perempuan";
        String hobbies = getSelectedHobbies();
        String address = addressField.getText();
        int distance = distanceSlider.getValue();

        Student student = new Student(name, email, age, gender, hobbies, address, distance);
        studentDAO.addStudent(student);

        loadStudentData();
        clearFormFields();
        JOptionPane.showMessageDialog(this, "Siswa berhasil ditambahkan");
    }

    private void updateStudent() {
        String name = nameField.getText();
        String email = emailField.getText();
        int age = (int) ageSpinner.getValue();
        String gender = maleButton.isSelected() ? "Laki-laki" : "Perempuan";
        String hobbies = getSelectedHobbies();
        String address = addressField.getText();
        int distance = distanceSlider.getValue();

        studentDAO.updateStudent(selectedStudentId, name, email, age, gender, hobbies, address, distance);

        loadStudentData();
        clearFormFields();
        selectedStudentId = -1;
        JOptionPane.showMessageDialog(this, "Siswa berhasil di update!");
    }

    private void updateStudentFromTable() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            selectedStudentId = (int) tableModel.getValueAt(selectedRow, 0);
            String name = (String) tableModel.getValueAt(selectedRow, 1);
            String email = (String) tableModel.getValueAt(selectedRow, 2);

            nameField.setText(name);
            emailField.setText(email);

            switchPanel("AddStudent");
        }
    }

    private void deleteStudentFromTable() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            int studentId = (int) tableModel.getValueAt(selectedRow, 0);
            studentDAO.deleteStudent(studentId);
            loadStudentData();
            JOptionPane.showMessageDialog(this, "Siswa berhasil dihapus");
        }
    }

    private String getSelectedHobbies() {
        StringBuilder hobbies = new StringBuilder();
        if (sportsCheck.isSelected()) hobbies.append("Olahraga ");
        if (artCheck.isSelected()) hobbies.append("Seni ");
        if (musicCheck.isSelected()) hobbies.append("Musik");
        return hobbies.toString().trim();
    }

    private void clearFormFields() {
        nameField.setText("");
        emailField.setText("");
        addressField.setText("");
        ageSpinner.setValue(6);
        maleButton.setSelected(false);
        femaleButton.setSelected(false);
        sportsCheck.setSelected(false);
        artCheck.setSelected(false);
        musicCheck.setSelected(false);
        distanceSlider.setValue(1000);
    }
}
