package ru.alishev.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{book_id}")
    public String show(@PathVariable("book_id") int bookId, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDAO.show(bookId));

        Optional<Person> bookOwner = bookDAO.getBookOwner(bookId);

        if(bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else model.addAttribute("people", personDAO.index());

        return "books/show";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "books/new";
        }

        bookDAO.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{book_id}/edit")
    public String edit(Model model, @PathVariable("book_id") int bookId){
        model.addAttribute("book", bookDAO.show(bookId));
        return "books/edit";
    }

    @PatchMapping("/{book_id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("book_id") int book_id){
        if(bindingResult.hasErrors()){
            return "books/edit";
        }

        bookDAO.update(book_id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{book_id}")
    public String delete(@PathVariable("book_id") int bookId){
        bookDAO.delete(bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{book_id}/release")
    public String release(@PathVariable("book_id") int bookId){
        bookDAO.release(bookId);
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{book_id}/assign")
    public String assign(@PathVariable("book_id") int bookId, @ModelAttribute("person") Person selectPErson){
        bookDAO.assign(bookId, selectPErson);
        return "redirect:/books/" + bookId;
    }
}
