package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person show(int userId){
        return jdbcTemplate.query("SELECT * FROM Person WHERE user_id=?", new Object[]{userId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth) VALUES(?, ?)", person.getFullName(), person.getYearOfBirth());
    }

    public void update(int userId, Person personUpdate){
        jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_birth=? WHERE user_id=?", personUpdate.getFullName(), personUpdate.getYearOfBirth(), userId);
    }

    public void delete(int userId){
        //people.removeIf(person -> person.getId() == id);
        jdbcTemplate.update("DELETE FROM Person WHERE user_id=?", userId);
    }

    public List<Book> getBooksByPersonId(int userId){
        return jdbcTemplate.query("SELECT * FROM Book WHERE user_id = ?", new Object[]{userId}, new BeanPropertyRowMapper<>(Book.class));
    }
}
