package com.home.bookstore.bootstrap;

import com.home.bookstore.model.Author;
import com.home.bookstore.model.Book;
import com.home.bookstore.model.Publisher;
import com.home.bookstore.repositories.AuthorRepository;
import com.home.bookstore.repositories.BookRepository;

import com.home.bookstore.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher("NXB", "123 zxc");
        publisherRepository.save(publisher);

        Author marken = new Author("Marken", "Nguyen");
        Book book1 = new Book("Book1", "123456");

        marken.getBooks().add(book1);
        book1.getAuthors().add(marken);
        book1.setPublisher(publisher);

        publisher.getBooks().add(book1);

        authorRepository.save(marken);
        bookRepository.save(book1);
        publisherRepository.save(publisher);

        //-------------------

        Author linh = new Author("Linh", "Pham");
        Book book2 = new Book("Book2", "654321");

        linh.getBooks().add(book2);
        book2.getAuthors().add(linh);
        book2.setPublisher(publisher);

        publisher.getBooks().add(book2);

        authorRepository.save(linh);
        bookRepository.save(book2);
        publisherRepository.save(publisher);


        System.out.println("No. Books: " + bookRepository.count());

        System.out.println("Publishers Book: " + publisher.getBooks().size());

    }
}
