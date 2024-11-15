package com.sevensky.hibernate_intro.repositories;

import com.sevensky.hibernate_intro.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    @Nullable
    Author findByFirstName(String firstName);

    Page<Author> findByFirstNameContaining(String firstName, Pageable pageable);


}


