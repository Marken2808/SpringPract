package com.home.bookstore.controller;

import com.home.bookstore.model.Book;
import com.home.bookstore.repositories.BookRepository;
import com.home.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){
        model.addAttribute("books", bookService.listAll());
        return "books/list";
    }

    @RequestMapping("newBook")
    public String newBook (Model model){
        Book book = new Book();
        model.addAttribute(book);
        return "books/newBook";
    }

    @RequestMapping(value = "saveBook", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @RequestMapping("editBook/{id}")
    public ModelAndView editBook(@PathVariable (name = "id") Long id){
        ModelAndView mav = new ModelAndView("id");
        Book book = bookService.get(id);
        mav.addObject("book", book);
        return mav;
    }

    @RequestMapping("deleteBook/{id}")
    public String deleteBook(@PathVariable (name = "id") Long id){
        bookService.delete(id);
        return "redirect:/books";
    }

}
