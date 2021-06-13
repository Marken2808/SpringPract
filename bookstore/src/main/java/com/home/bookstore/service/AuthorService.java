package com.home.bookstore.service;

import com.home.bookstore.model.Author;
import com.home.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> listAll(){
        return authorRepository.findAll();
    }

    public Author get(Long id){
        return authorRepository.findById(id).get();
    }

    public void delete(Long id){
        authorRepository.deleteById(id);
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

}
