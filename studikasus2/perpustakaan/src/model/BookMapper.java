package model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;

public interface BookMapper {

    @Select("SELECT * FROM books")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "title", column = "title"),
        @Result(property = "author", column = "author"),
        @Result(property = "publisher", column = "publisher"), // Kolom publisher
        @Result(property = "year", column = "year"),
        @Result(property = "price", column = "price"),
        @Result(property = "category", column = "category")
    })
    List<Book> getAllBooks();

    @Insert("INSERT INTO books (title, author, publisher, year, price, category) "
            + "VALUES (#{title}, #{author}, #{publisher}, #{year}, #{price}, #{category})")
    void addBook(Book book);

    @Update("UPDATE books SET title = #{title}, author = #{author}, publisher = #{publisher}, "
            + "year = #{year}, price = #{price}, category = #{category} WHERE id = #{id}")
    void updateBook(Book book);

    @Delete("DELETE FROM books WHERE id = #{id}")
    void deleteBook(int id);
}
