package pertemuan7.Membership.src.view.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonActionListener implements ActionListener {
    private MainFrame mainFrame;

    public MainButtonActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFrame.getButtonJenisMember()) {
            // Logika untuk ketika tombol Jenis Member ditekan
            mainFrame.showJenisMemberFrame();
        } else if (e.getSource() == mainFrame.getButtonMember()) {
            // Logika untuk ketika tombol Member ditekan
            mainFrame.showMemberFrame();
        }
    }
}
