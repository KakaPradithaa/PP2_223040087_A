package model;

import java.math.BigDecimal;

public class Book {

    private int id; // Primary Key
    private String title;
    private String author;
    private String publisher;
    private int year;
    private BigDecimal price;
    private String category;

    // Constructor dengan parameter (untuk inisialisasi data)
    public Book(String title, String author, String publisher, int year, BigDecimal price, String category) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.price = price;
        this.category = category;
    }

    // Default Constructor (penting untuk MyBatis)
    public Book() {
    }

    // Getter dan Setter dengan validasi sederhana
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher != null && !publisher.trim().isEmpty()) {
            this.publisher = publisher;
        } else {
            throw new IllegalArgumentException("Publisher cannot be null or empty");
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year > 0) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Year must be greater than 0");
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price != null && price.compareTo(BigDecimal.ZERO) >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be non-negative");
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category != null && !category.trim().isEmpty()) {
            this.category = category;
        } else {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
    }

    // Override toString() untuk debugging dan logging
    @Override
    public String toString() {
        return "Book{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", author='" + author + '\''
                + ", publisher='" + publisher + '\''
                + ", year=" + year
                + ", price=" + price
                + ", category='" + category + '\''
                + '}';
    }
}
