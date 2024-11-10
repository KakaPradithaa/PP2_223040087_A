package pertemuan6.src.dao;

import pertemuan6.src.db.MySqlConnection;
import pertemuan6.src.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private Connection connection;

    public StudentDAO() throws SQLException {
        // Mendapatkan koneksi ke database
        this.connection = MySqlConnection.getConnection();
    }

    // Menambahkan siswa baru ke database
    public void addStudent(Student student) {
        String query = "INSERT INTO students (name, email, age, gender, hobbies, address, distance) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getGender());
            statement.setString(5, student.getHobbies());
            statement.setString(6, student.getAddress());
            statement.setInt(7, student.getDistance());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Memperbarui data siswa berdasarkan ID
    public void updateStudent(int id, String name, String email, int age, String gender, String hobbies, String address, int distance) {
        String query = "UPDATE students SET name = ?, email = ?, age = ?, gender = ?, hobbies = ?, address = ?, distance = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, age);
            statement.setString(4, gender);
            statement.setString(5, hobbies);
            statement.setString(6, address);
            statement.setInt(7, distance);
            statement.setInt(8, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menghapus siswa berdasarkan ID
    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mendapatkan semua data siswa dari database
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String hobbies = resultSet.getString("hobbies");
                String address = resultSet.getString("address");
                int distance = resultSet.getInt("distance");

                students.add(new Student(id, name, email, age, gender, hobbies, address, distance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}
