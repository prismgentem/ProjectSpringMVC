package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

//    public Optional<Book> show(String fullName) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE full_name=?", new Object[]{fullName},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }

    public Book show(int bookId){
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int bookId, Book bookUpdate){
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE book_id=?", bookUpdate.getName(), bookUpdate.getAuthor(), bookUpdate.getYear(), bookId);
    }

    public void delete(int bookId){
        //people.removeIf(person -> person.getId() == id);
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", bookId);
    }
}
