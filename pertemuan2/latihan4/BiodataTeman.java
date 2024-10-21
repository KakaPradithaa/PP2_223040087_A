package latihan4;

import java.awt.event.*;
import javax.swing.*;

public class BiodataTeman extends JFrame {

    private boolean checkBoxSelected; // Variabel untuk mengecek apakah checkbox dicentang

    public BiodataTeman() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.checkBoxSelected = false; // Default tidak dicentang

        // Label dan TextField untuk nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(130, 40, 100, 10);

        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(130, 60, 200, 30);

        // Label dan TextField untuk nomor telepon
        JLabel labelTelepon = new JLabel("Nomor Telepon:");
        labelTelepon.setBounds(130, 100, 150, 10);

        JTextField textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(130, 120, 200, 30);

        // Label dan Radio Button untuk jenis kelamin
        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(130, 160, 150, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true);
        radioButton1.setBounds(130, 180, 100, 30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(130, 210, 100, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // Checkbox untuk warga negara asing
        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(130, 250, 200, 30);

        // Listener untuk checkbox
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                checkBoxSelected = e.getStateChange() == ItemEvent.SELECTED;
            }
        });

        // Tombol untuk menambahkan data
        JButton button = new JButton("Tambah");
        button.setBounds(130, 290, 100, 40);

        // TextArea untuk menampilkan hasil biodata
        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(130, 340, 300, 200);
        txtOutput.setEditable(false); // Tidak bisa diedit secara manual

        // ActionListener untuk tombol tambah
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String telepon = textFieldTelepon.getText();
                String jenisKelamin = "";

                // Validasi input
                if (nama.isEmpty() || telepon.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua field!");
                } else {
                    // Memeriksa radio button mana yang dipilih
                    if (radioButton1.isSelected()) {
                        jenisKelamin = radioButton1.getText();
                    } else if (radioButton2.isSelected()) {
                        jenisKelamin = radioButton2.getText();
                    }

                    // Menambahkan data ke JTextArea
                    txtOutput.append("Nama: " + nama + "\n");
                    txtOutput.append("Nomor Telepon: " + telepon + "\n");
                    txtOutput.append("Jenis Kelamin: " + jenisKelamin + "\n");

                    if (checkBoxSelected) {
                        txtOutput.append("Warga Negara: Ya\n");
                    } else {
                        txtOutput.append("Warga Negara: Bukan\n");
                    }

                    txtOutput.append("=================================\n");

                    // Mengosongkan TextField setelah menambah data
                    textFieldNama.setText("");
                    textFieldTelepon.setText("");
                }
            }
        });

        // Menambahkan komponen ke JFrame
        this.add(button);
        this.add(textFieldNama);
        this.add(labelNama);
        this.add(textFieldTelepon);
        this.add(labelTelepon);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(checkBox); // Tambahkan checkbox ke JFrame
        this.add(txtOutput);

        // Mengatur ukuran dan tata letak JFrame
        this.setSize(500, 600);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BiodataTeman b = new BiodataTeman();
                b.setVisible(true);
            }
        });
    }
}
