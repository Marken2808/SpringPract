package com.home.bookstore.service;

import com.home.bookstore.dao.PersonDAO;
import com.home.bookstore.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    
    private final PersonDAO personDAO;

    @Autowired
    public PersonService(@Qualifier("personData") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public int addPerson(Person person) {
        return personDAO.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDAO.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDAO.selectPersonById(id);
    }

    public  int deletePerson(UUID id) {
        return personDAO.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person newPerson) {
        return personDAO.updatePersonById(id, newPerson);
    }



}











