package com.home.bookstore.service;


import com.home.bookstore.model.Book;
import com.home.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> listAll(){
        return bookRepository.findAll();
    }

    public Book get(Long id){
        return bookRepository.findById(id).get();
    }

    public void delete(Long id){
        bookRepository.deleteById(id);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

}
