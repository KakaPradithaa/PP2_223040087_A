package pertemuan7.PendaftaranSd.src.view.main;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import pertemuan6.src.view.form.AddStudentForm;

public class SDRegistrationApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create JFrame and set properties
            JFrame frame = new JFrame("Pendaftaran SD");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            try {
                // Add AddStudentForm panel to the frame
                frame.add(new AddStudentForm());
            } catch (SQLException ex) {
                Logger.getLogger(SDRegistrationApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.setVisible(true);
        });
    }
}
