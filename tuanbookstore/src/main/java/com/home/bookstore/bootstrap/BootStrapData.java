package com.home.bookstore.bootstrap;

import com.home.bookstore.model.*;
import com.home.bookstore.repositories.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

        //-------------------

        Author linh = new Author("Linh", "Pham");
        Book book2 = new Book("Book2", "654321");

        linh.getBooks().add(book2);
        book2.getAuthors().add(linh);
        book2.setPublisher(publisher);

        publisher.getBooks().add(book2);

        authorRepository.save(linh);
        bookRepository.save(book2);

        //--------------------------

        Role ADMIN = new Role("ADMIN");
        User admin = new User("admin","$2a$10$bN7OWEvi6rTqJEYbZfDOg.FHmG.xPTDxJR1k9LzsR4O6Nt8zuIKwq",true);
        admin.setRole(ADMIN);
        ADMIN.getUsers().add(admin);
        roleRepository.save(ADMIN);
        userRepository.save(admin);

        Role EDITOR = new Role("EDITOR");
        User zxc = new User("zxc","$2a$10$o.YnxFEK0yGalXn2MjkgUuWhEEQMeRLdO4zMSWAMsajHv3gLqZSIq",true);
        zxc.setRole(EDITOR);
        EDITOR.getUsers().add(zxc);
        roleRepository.save(EDITOR);
        userRepository.save(zxc);

        User qwe = new User("qwe","$2a$10$FsK85pF/8rwVoNKeM4ZJm.v8XMzTIy2BCjGXvkJkOeUKXBmaR6XYy",true);
        qwe.setRole(EDITOR);
        EDITOR.getUsers().add(qwe);
        userRepository.save(qwe);

        Role GUEST = new Role("GUEST");
        User tuan = new User("tuan","$2a$10$CuftvJ7q2YoNOXVZHZ/LaeKFtpdKkUhcQoRHJdmM4g5ltkqONtUA.",true);
        tuan.setRole(GUEST);
        GUEST.getUsers().add(tuan);
        roleRepository.save(GUEST);
        userRepository.save(tuan);

        System.out.println("No. Books: " + bookRepository.count());
        System.out.println("Publishers Book: " + publisher.getBooks().size());
        System.out.println("No. Users: " + userRepository.count());

    }
}
