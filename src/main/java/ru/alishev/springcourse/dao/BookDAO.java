package ru.alishev.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final SessionFactory sessionFactory;
    @Autowired
    public BookDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Book b", Book.class).getResultList();
    }

    //Пусть пока так будет
//    @Transactional
//    public Optional<Book> show(String fullName) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("FROM Book WHERE name = :fullName", Book.class).stream().findAny();
//    }

    @Transactional
    public Book show(int bookId){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, bookId);
    }

    @Transactional
    public void save(Book book){
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }
    @Transactional
    public void update(int bookId, Book bookUpdate){
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE book_id=?", bookUpdate.getName(), bookUpdate.getAuthor(), bookUpdate.getYear(), bookId);
        Session session = sessionFactory.getCurrentSession();
        Book bookToBeUpdate = session.get(Book.class, bookId);
        bookToBeUpdate.setName(bookUpdate.getName());
        bookToBeUpdate.setAuthor(bookUpdate.getAuthor());
        bookToBeUpdate.setYear(bookUpdate.getYear());
    }
    @Transactional
    public void delete(int bookId){
        //people.removeIf(person -> person.getId() == id);
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class,bookId));

    }

    //вроде норм
    @Transactional
    public Optional<Person> getBookOwner(int bookId){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT b.owner FROM Book b WHERE b.id = :bookId", Person.class).setParameter("bookId", bookId).stream().findAny();
    }
    @Transactional
    public void release(int bookId){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, bookId);
        book.setOwner(null);
    }
    @Transactional
    public void assign(int bookId, Person selectPerson){
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, bookId);
        book.setOwner(selectPerson);
    }
}
