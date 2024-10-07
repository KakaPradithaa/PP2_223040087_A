package tugas1;

import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class BiodataTabungan extends JFrame {

    public BiodataTabungan() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(resetItem);
        menu.add(exitItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        // Label dan TextField untuk nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(30, 20, 100, 25);
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(150, 20, 200, 25);

        // Label dan TextField untuk nomor telepon
        JLabel labelTelepon = new JLabel("Nomor Telepon:");
        labelTelepon.setBounds(30, 60, 100, 25);
        JTextField textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(150, 60, 200, 25);

        // Label dan Radio Button untuk jenis kelamin
        JLabel labelKelamin = new JLabel("Jenis Kelamin:");
        labelKelamin.setBounds(30, 100, 100, 25);
        JRadioButton radioButtonLaki = new JRadioButton("Laki-Laki");
        radioButtonLaki.setBounds(150, 100, 100, 25);
        JRadioButton radioButtonPerempuan = new JRadioButton("Perempuan");
        radioButtonPerempuan.setBounds(250, 100, 100, 25);
        ButtonGroup bgKelamin = new ButtonGroup();
        bgKelamin.add(radioButtonLaki);
        bgKelamin.add(radioButtonPerempuan);

        // JList untuk jenis tabungan
        JLabel labelTabungan = new JLabel("Jenis Tabungan:");
        labelTabungan.setBounds(30, 140, 100, 25);
        String[] jenisTabungan = {"Tabungan Emas", "Tabungan Haji", "Tabungan Pendidikan", "Tabungan Umum"};
        JList<String> listTabungan = new JList<>(jenisTabungan);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Menambahkan JScrollPane untuk scroll pada JList
        JScrollPane scrollPane = new JScrollPane(listTabungan);
        scrollPane.setBounds(150, 140, 200, 100); // Ukuran JList di dalam JScrollPane

        // JSpinner untuk frekuensi transaksi per bulan
        JLabel labelFrekuensi = new JLabel("Frekuensi Transaksi:");
        labelFrekuensi.setBounds(30, 260, 150, 25);
        SpinnerNumberModel transaksiModel = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner spinnerTransaksi = new JSpinner(transaksiModel);
        spinnerTransaksi.setBounds(150, 260, 200, 25);

        // JSpinner untuk tanggal lahir
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(30, 300, 100, 25);
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        JSpinner spinnerTanggalLahir = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
        spinnerTanggalLahir.setBounds(150, 300, 200, 25);

        // Password Field dan Confirm Password
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(30, 340, 100, 25);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 340, 200, 25);

        JLabel labelConfirmPassword = new JLabel("Confirm Password:");
        labelConfirmPassword.setBounds(30, 380, 120, 25);
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(150, 380, 200, 25);

        // Tombol untuk mendaftarkan nasabah
        JButton buttonDaftar = new JButton("Daftar");
        buttonDaftar.setBounds(150, 420, 100, 40);

        // JTextArea untuk menampilkan hasil biodata
        JTextArea txtOutput = new JTextArea();
        txtOutput.setBounds(30, 480, 320, 150);
        txtOutput.setEditable(false); // Tidak bisa diedit secara manual

        // ActionListener untuk tombol daftar
        buttonDaftar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String telepon = textFieldTelepon.getText();
                String jenisKelamin = radioButtonLaki.isSelected() ? "Laki-Laki" : "Perempuan";
                String jenisTabunganDipilih = listTabungan.getSelectedValue();
                int frekuensi = (int) spinnerTransaksi.getValue();
                Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Validasi input
                if (nama.isEmpty() || telepon.isEmpty() || jenisTabunganDipilih == null) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua field!");
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Password dan Confirm Password tidak cocok!");
                } else {
                    // Menampilkan data ke JTextArea
                    txtOutput.append("Nama: " + nama + "\n");
                    txtOutput.append("Nomor Telepon: " + telepon + "\n");
                    txtOutput.append("Jenis Kelamin: " + jenisKelamin + "\n");
                    txtOutput.append("Jenis Tabungan: " + jenisTabunganDipilih + "\n");
                    txtOutput.append("Frekuensi Transaksi: " + frekuensi + " kali/bulan\n");
                    txtOutput.append("Tanggal Lahir: " + dateFormat.format(tanggalLahir) + "\n");
                    txtOutput.append("Password cocok!\n");
                    txtOutput.append("=================================\n");

                    // Mengosongkan field setelah menambah data
                    textFieldNama.setText("");
                    textFieldTelepon.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                    bgKelamin.clearSelection(); // Mengosongkan pilihan jenis kelamin
                }
            }
        });

        // ActionListener untuk menu Reset dan Exit
        resetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldNama.setText("");
                textFieldTelepon.setText("");
                listTabungan.clearSelection();
                passwordField.setText("");
                confirmPasswordField.setText("");
                spinnerTransaksi.setValue(1);
                spinnerTanggalLahir.setValue(new Date());
                txtOutput.setText("");
                bgKelamin.clearSelection(); // Mengosongkan pilihan jenis kelamin
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Menambahkan komponen ke JFrame
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelTelepon);
        this.add(textFieldTelepon);
        this.add(labelKelamin);
        this.add(radioButtonLaki);
        this.add(radioButtonPerempuan);
        this.add(labelTabungan);
        this.add(scrollPane); // Menambahkan JScrollPane ke JFrame
        this.add(labelFrekuensi);
        this.add(spinnerTransaksi);
        this.add(labelTanggalLahir);
        this.add(spinnerTanggalLahir);
        this.add(labelPassword);
        this.add(passwordField);
        this.add(labelConfirmPassword);
        this.add(confirmPasswordField);
        this.add(buttonDaftar);
        this.add(txtOutput);

        // Mengatur ukuran dan tata letak JFrame
        this.setSize(400, 650);
        this.setLayout(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BiodataTabungan();
            }
        });
    }
}
