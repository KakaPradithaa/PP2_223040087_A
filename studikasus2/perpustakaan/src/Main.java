import view.BookView;
import controller.BookController;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Jalankan GUI di Event Dispatch Thread (EDT) untuk mencegah masalah concurrency
        SwingUtilities.invokeLater(() -> {
            // Membuat instance dari BookView dan BookController
            BookView bookView = new BookView();
            new BookController(bookView);

            // Menampilkan BookView di tengah layar
            bookView.setLocationRelativeTo(null);
            bookView.setVisible(true);
        });
    }
}
