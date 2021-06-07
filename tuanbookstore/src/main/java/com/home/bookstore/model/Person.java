package com.home.bookstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Person {

    @Id
    private UUID id;

    private String name;

    public Person() {}


    public Person (@JsonProperty("id") UUID id,
                   @JsonProperty("name") String name){
        this.id = id;
        this.name = name;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
