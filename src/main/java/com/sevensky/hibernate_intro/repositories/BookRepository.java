package com.sevensky.hibernate_intro.repositories;

import com.sevensky.hibernate_intro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

