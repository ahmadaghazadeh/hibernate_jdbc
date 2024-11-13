package com.sevensky.hibernate_intro.repositories;

import com.sevensky.hibernate_intro.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}


