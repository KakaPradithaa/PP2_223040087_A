package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import model.Book;

public class BookView extends JFrame {

    private JTable table;
    private JTextField titleField, authorField, publisherField, yearField, priceField, categoryField;
    private JButton addButton, updateButton, deleteButton, refreshButton;
    private JPanel panel;

    public BookView() {
        // Inisialisasi komponen
        setTitle("Book Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Membuat tabel
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Title", "Author", "Publisher", "Year", "Price", "Category"}
        ));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Pilih hanya 1 baris

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel untuk form input
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 5, 5));

        formPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        formPanel.add(titleField);

        formPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        formPanel.add(authorField);

        formPanel.add(new JLabel("Publisher:"));
        publisherField = new JTextField();
        formPanel.add(publisherField);

        formPanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        formPanel.add(yearField);

        formPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        formPanel.add(priceField);

        formPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        formPanel.add(categoryField);

        // Tombol aksi
        addButton = new JButton("Add Book");
        updateButton = new JButton("Update Book");
        deleteButton = new JButton("Delete Book");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    // Mendapatkan data dari form
    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getAuthorField() {
        return authorField;
    }

    public JTextField getPublisherField() {
        return publisherField;
    }

    public JTextField getYearField() {
        return yearField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getCategoryField() {
        return categoryField;
    }

    public JTable getTable() {
        return table;
    }

    // Menambahkan listener untuk tombol
    public void addAddBookListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addUpdateBookListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteBookListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addRefreshButtonListener(ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    public void addTableSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    // Memperbarui tabel buku
    public void updateBookTable(List<Book> books) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Menghapus data lama
        for (Book book : books) {
            model.addRow(new Object[]{
                book.getId(), book.getTitle(), book.getAuthor(),
                book.getPublisher(), book.getYear(), book.getPrice(), book.getCategory()
            });
        }
    }

    // Mengosongkan input fields
    public void clearInputFields() {
        titleField.setText("");
        authorField.setText("");
        publisherField.setText("");
        yearField.setText("");
        priceField.setText("");
        categoryField.setText("");
    }
}
