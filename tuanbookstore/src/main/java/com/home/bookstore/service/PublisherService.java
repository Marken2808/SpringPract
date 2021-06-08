package com.home.bookstore.service;

import com.home.bookstore.model.Publisher;
import com.home.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> listAll(){
        return publisherRepository.findAll();
    }

    public Publisher get(Long id){
        return publisherRepository.findById(id).get();
    }

    public void delete(Long id){
        publisherRepository.deleteById(id);
    }

    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

}
