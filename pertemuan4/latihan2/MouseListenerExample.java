package pertemuan4.latihan2;

import javax.swing.*;
import java.awt.event.*;

public class MouseListenerExample {
    public static void main(String[] args) {
        //Membuat Frame
        JFrame frame = new JFrame("MouseListener Example");
        
        //Membuat label untuk menampilkan pesan
        JLabel label = new JLabel("Arahkan dan klik mouse pada area ini.");
        label.setBounds(50, 50, 300, 30);

        //Menambahkan MouseListener ke label
        label.addMouseListener(new MouseListener() {
            //Dijalankan ketika mouse di klik (klik kiri, kanan atau tengah)
            public void mouseClicked(MouseEvent e) {
                label.setText("Mouse Clicked at: ("+ e.getX() + ", " + e.getY() + ")");
            }

            //Dijalankan ketika mouse ditekan (tanpa dilepaskan)
            public void mousePressed(MouseEvent e) {
                label.setText("Mouse pressed at: ("+ e.getX() + ", " + e.getY() + ")");
            }

            //Dijalankan ketika mouse dilepaskan setelah ditekan
            public void mouseReleased(MouseEvent e) {
                label.setText("Mouse Realeased at : (" + e.getX() + ", "+ e.getY() +")");
            }
            //Dijalankan ketika mouse masuk ke area komponen
            public void mouseEntered(MouseEvent e) {
                label.setText("Mouse Entered the area ");
            }
            //Dijalankan ketika mouse keluar dari area komponen
            public void mouseExited(MouseEvent e) {
                label.setText("Mouse Exited the area");
            }
        });

        frame.add(label);

        //setting frame
        frame.setSize(400, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
