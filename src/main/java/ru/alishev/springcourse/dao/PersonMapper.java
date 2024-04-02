//package ru.alishev.springcourse.dao;
//
//import org.springframework.jdbc.core.RowMapper;
//import ru.alishev.springcourse.models.Person;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class PersonMapper implements RowMapper<Person> {
//    @Override
//    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//        Person person = new Person();
//
//        person.setUserId(resultSet.getInt("user_id"));
//        person.setFullName(resultSet.getString("full_name"));
//        person.setYearOfBirth(resultSet.getInt("year_of_birth"));
//
//        return person;
//
//    }
//}
