package controller;

import org.apache.ibatis.session.SqlSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Book;
import model.BookMapper;
import model.MyBatisUtil;
import view.BookView;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BookController {

    private BookView bookView;
    private BookMapper bookMapper;

    public BookController(BookView bookView) {
        this.bookView = bookView;

        // Tambahkan Listener ke View
        bookView.addAddBookListener(new AddBookListener());
        bookView.addUpdateBookListener(new UpdateBookListener());
        bookView.addDeleteBookListener(new DeleteBookListener());
        bookView.addRefreshButtonListener(new RefreshButtonListener());
        bookView.addTableSelectionListener(new TableSelectionListener());

        refreshBookTable(); // Awal load, refresh data tabel
    }

    // Method untuk refresh data tabel
    private void refreshBookTable() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            bookMapper = session.getMapper(BookMapper.class);
            List<Book> books = bookMapper.getAllBooks();
            bookView.updateBookTable(books);
            bookView.clearInputFields(); // Kosongkan form input
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(bookView, "Error: " + ex.getMessage());
        }
    }

    // Listener untuk menambahkan buku
    class AddBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Ambil input
                String title = bookView.getTitleField().getText();
                String author = bookView.getAuthorField().getText();
                String publisher = bookView.getPublisherField().getText();
                int year = Integer.parseInt(bookView.getYearField().getText());
                BigDecimal price = new BigDecimal(bookView.getPriceField().getText());
                String category = bookView.getCategoryField().getText();

                // Validasi input kosong
                if (title.isEmpty() || author.isEmpty() || publisher.isEmpty() || category.isEmpty()) {
                    JOptionPane.showMessageDialog(bookView, "Please fill in all fields.");
                    return;
                }

                // Simpan buku ke database
                Book book = new Book(title, author, publisher, year, price, category);
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    bookMapper = session.getMapper(BookMapper.class);
                    bookMapper.addBook(book);
                    session.commit();
                    refreshBookTable();
                    JOptionPane.showMessageDialog(bookView, "Book added successfully!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(bookView, "Error: " + ex.getMessage());
            }
        }
    }

    // Listener untuk memperbarui buku
    class UpdateBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = bookView.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                try {
                    int id = (int) bookView.getTable().getValueAt(selectedRow, 0);

                    String title = bookView.getTitleField().getText();
                    String author = bookView.getAuthorField().getText();
                    String publisher = bookView.getPublisherField().getText();
                    int year = Integer.parseInt(bookView.getYearField().getText());
                    BigDecimal price = new BigDecimal(bookView.getPriceField().getText());
                    String category = bookView.getCategoryField().getText();

                    Book book = new Book(title, author, publisher, year, price, category);
                    book.setId(id);

                    try (SqlSession session = MyBatisUtil.getSqlSession()) {
                        bookMapper = session.getMapper(BookMapper.class);
                        bookMapper.updateBook(book);
                        session.commit();
                        refreshBookTable();
                        JOptionPane.showMessageDialog(bookView, "Book updated successfully!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(bookView, "Error: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(bookView, "Please select a book to update.");
            }
        }
    }

    // Listener untuk menghapus buku
    class DeleteBookListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = bookView.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) bookView.getTable().getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(bookView, "Delete this book?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try (SqlSession session = MyBatisUtil.getSqlSession()) {
                        bookMapper = session.getMapper(BookMapper.class);
                        bookMapper.deleteBook(id);
                        session.commit();
                        refreshBookTable();
                        JOptionPane.showMessageDialog(bookView, "Book deleted successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(bookView, "Error: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(bookView, "Please select a book to delete.");
            }
        }
    }

    // Listener untuk tombol refresh
    class RefreshButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            refreshBookTable();
        }
    }

    // Listener untuk memilih data di tabel
    public class TableSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = bookView.getTable().getSelectedRow();
                if (selectedRow >= 0) {
                    bookView.getTitleField().setText(bookView.getTable().getValueAt(selectedRow, 1).toString());
                    bookView.getAuthorField().setText(bookView.getTable().getValueAt(selectedRow, 2).toString());
                    bookView.getPublisherField().setText(bookView.getTable().getValueAt(selectedRow, 3).toString());
                    bookView.getYearField().setText(bookView.getTable().getValueAt(selectedRow, 4).toString());
                    bookView.getPriceField().setText(bookView.getTable().getValueAt(selectedRow, 5).toString());
                    bookView.getCategoryField().setText(bookView.getTable().getValueAt(selectedRow, 6).toString());
                }
            }
        }
    }
}
