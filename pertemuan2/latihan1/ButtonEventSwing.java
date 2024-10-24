<<<<<<< HEAD
package latihan1;

import java.awt.event.*;
import javax.swing.*; // Perbaikan: Menggunakan javax.swing

public class ButtonEventSwing extends JFrame {
    
    public ButtonEventSwing() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello World");
        label.setBounds(130, 40, 400, 10);

        JButton button = new JButton("Klik");
        button.setBounds(130, 100, 100, 40);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Hello Pemograman II");
            }
        });

        this.add(button);
        this.add(label);

        this.setSize(400, 500); // Perbaikan: setSize, bukan setSiz
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ButtonEventSwing b = new ButtonEventSwing();
                b.setVisible(true);
            }
        });
    }
}
=======
package latihan1;

import java.awt.event.*;
import javax.swing.*; // Perbaikan: Menggunakan javax.swing

public class ButtonEventSwing extends JFrame {
    
    public ButtonEventSwing() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello World");
        label.setBounds(130, 40, 400, 10);

        JButton button = new JButton("Klik");
        button.setBounds(130, 100, 100, 40);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Hello Pemograman II");
            }
        });

        this.add(button);
        this.add(label);

        this.setSize(400, 500); // Perbaikan: setSize, bukan setSiz
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ButtonEventSwing b = new ButtonEventSwing();
                b.setVisible(true);
            }
        });
    }
}
>>>>>>> 253af9e05a04262ffa0b106adcf2a27bef725974
