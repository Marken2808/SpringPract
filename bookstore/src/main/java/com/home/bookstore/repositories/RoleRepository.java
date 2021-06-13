package com.home.bookstore.repositories;

import com.home.bookstore.model.Book;
import com.home.bookstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
