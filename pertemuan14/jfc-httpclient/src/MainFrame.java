
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;
import org.apache.hc.client5.http.async.methods.SimpleResponseConsumer;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.apache.hc.client5.http.async.methods.SimpleRequestProducer;

public class MainFrame {

    public static void main(String[] args) {
        // Konfigurasi IO Reactor
        final IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setSoTimeout(Timeout.ofSeconds(5))
                .build();

        // Membuat HTTP Async Client
        final CloseableHttpAsyncClient client = HttpAsyncClients.custom()
                .setIOReactorConfig(ioReactorConfig)
                .build();

        client.start();

        final HttpHost target = new HttpHost("672fbf9066e42ceaf15e9a9b.mockapi.io"); // URL target menggunakan layanan Mock API
        final String requestUri = "/api/contacts";

        // Membuat frame utama
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contoh HTTP Client di Swing");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new BorderLayout());

            // Label untuk status
            JLabel statusLabel = new JLabel("Tekan tombol untuk mulai mengunduh data", JLabel.CENTER);

            // Tombol untuk memulai proses
            JButton startButton = new JButton("Mulai");

            // Progress bar
            JProgressBar progressBar = new JProgressBar(0, 100);

            // Area teks untuk menampilkan hasil
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            // Panel untuk tombol dan progress bar
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.add(startButton);
            panel.add(progressBar);

            // Tambahkan komponen ke frame
            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(panel, BorderLayout.SOUTH);

            // Listener tombol aksi
            startButton.addActionListener(e -> {
                progressBar.setIndeterminate(true);
                startButton.setEnabled(false); // Nonaktifkan tombol saat proses berjalan
                statusLabel.setText("Proses berjalan...");
                textArea.setText("");

                SimpleHttpRequest request = SimpleRequestBuilder.get()
                        .setHttpHost(target)
                        .setPath(requestUri)
                        .build();

                client.execute(
                        SimpleRequestProducer.create(request),
                        SimpleResponseConsumer.create(),
                        new FutureCallback<>() {
                    @Override
                    public void completed(final org.apache.hc.client5.http.async.methods.SimpleHttpResponse response) {
                        SwingUtilities.invokeLater(() -> {
                            try {
                                JSONParser parser = new JSONParser();
                                JSONArray contacts = (JSONArray) parser.parse(response.getBodyText());
                                for (Object obj : contacts) {
                                    JSONObject contact = (JSONObject) obj;
                                    String line = "Name: " + contact.get("name") + ", Phone: " + contact.get("phone");
                                    textArea.append(line + "\n");
                                }
                            } catch (ParseException ex) {
                                textArea.setText("Error parsing response: " + ex.getMessage());
                            }

                            progressBar.setIndeterminate(false);
                            startButton.setEnabled(true);
                            statusLabel.setText("Proses selesai");
                        });
                    }

                    @Override
                    public void failed(final Exception ex) {
                        SwingUtilities.invokeLater(() -> {
                            progressBar.setIndeterminate(false);
                            startButton.setEnabled(true);
                            statusLabel.setText("Proses gagal: " + ex.getMessage());
                        });
                    }

                    @Override
                    public void cancelled() {
                        SwingUtilities.invokeLater(() -> {
                            progressBar.setIndeterminate(false);
                            startButton.setEnabled(true);
                            statusLabel.setText("Proses dibatalkan");
                        });
                    }
                });
            });

            // Listener untuk event window
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    client.close(CloseMode.GRACEFUL);
                    System.exit(0);
                }
            });

            // Tampilkan frame
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
