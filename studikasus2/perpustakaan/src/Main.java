
import controller.BookController;
import view.BookView;

public class Main {

    public static void main(String[] args) {
        // Membuat View
        BookView bookView = new BookView();

        // Membuat Controller
        new BookController(bookView);

        // Tampilkan View
        bookView.setVisible(true);
    }
}
