package ru.alishev.springcourse.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p", Person.class).getResultList();
    }
//похорошему бы переписать
    @Transactional
    public Optional<Person> getPersonByFullName(String fullName) {
        Session session = sessionFactory.getCurrentSession();
        return session.byNaturalId(Person.class).using("full_name", fullName).loadOptional();
    }

    @Transactional
    public Person show(int userId){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, userId);
    }

    @Transactional
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int userId, Person personUpdate){
        Session session = sessionFactory.getCurrentSession();

        Person personToBeUpdated = session.get(Person.class, userId);
        personToBeUpdated.setFullName(personUpdate.getFullName());
        personToBeUpdated.setYearOfBirth(personUpdate.getYearOfBirth());
    }

    @Transactional
    public void delete(int userId){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, userId));
    }

//подумать и сделать
    @Transactional
    public List<Book> getBooksByPersonId(int userId){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, userId);

        Hibernate.initialize(person.getBooks());

        if(person.getBooks() == null){
            return Collections.emptyList();
        }
        return person.getBooks();
    }
}
