package com.sevensky.hibernate_intro.repositories;

import com.sevensky.hibernate_intro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, Long> {
}

