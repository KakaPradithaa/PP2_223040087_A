package controller;

import model.*;
import view.UserPdf;
import view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class UserController {

    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;

    // Constructor
    public UserController(UserView view, UserMapper mapper, UserPdf pdf) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;

        // Adding listeners
        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    // Listener to add user
    class AddUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if (!name.isEmpty() && !email.isEmpty()) {
                // Disable button and set status label
                view.setStatusLabel("Proses menambahkan user...");

                // Using SwingWorker to perform the action in a background thread
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        try (SqlSession session = MyBatisUtil.getSqlSession()) {
                            UserMapper mapper = session.getMapper(UserMapper.class);
                            User user = new User();
                            user.setName(name);
                            user.setEmail(email);
                            mapper.insertUser(user);
                            session.commit();
                        } catch (Exception ex) {
                            SwingUtilities.invokeLater(() -> {
                                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
                            });
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(view, "User added successfully!");
                            view.setStatusLabel("User added.");
                        });
                    }
                };
                worker.execute();
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

    // Listener to refresh user list
    class RefreshListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try (SqlSession session = MyBatisUtil.getSqlSession()) {
                // Using mapper passed through constructor
                List<User> users = mapper.getAllUsers();
                String[] userArray = users.stream()
                        .map(u -> u.getName() + " (" + u.getEmail() + ")")
                        .toArray(String[]::new);
                view.setUserList(userArray);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            }
        }
    }

    // Listener to export user data to PDF
    class ExportListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setStatusLabel("Proses mengekspor data...");

            // Using SwingWorker to perform the export action
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try (SqlSession session = MyBatisUtil.getSqlSession()) {
                        List<User> users = mapper.getAllUsers();
                        pdf.exportPdf(users);
                    } catch (Exception ex) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
                        });
                    }
                    return null;
                }

                @Override
                protected void done() {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(view, "User data exported to PDF successfully!");
                        view.setStatusLabel("Data diekspor.");
                    });
                }
            };
            worker.execute();
        }
    }
}
