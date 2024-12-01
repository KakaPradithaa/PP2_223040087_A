package pertemuan7.PendaftaranSd.src.model;

public class Student {
    private int id;
    private String name;
    private String email;
    private int age;
    private String gender;
    private String hobbies;
    private String address;
    private int distance;

    // Konstruktor yang digunakan untuk menambah atau memperbarui data siswa
    public Student(String name, String email, int age, String gender, String hobbies, String address, int distance) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.hobbies = hobbies;
        this.address = address;
        this.distance = distance;
    }

    // Konstruktor dengan ID (misalnya untuk memuat data siswa yang ada di database)
    public Student(int id, String name, String email, int age, String gender, String hobbies, String address, int distance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.hobbies = hobbies;
        this.address = address;
        this.distance = distance;
    }

    // Getter dan Setter (tambahkan jika perlu)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
