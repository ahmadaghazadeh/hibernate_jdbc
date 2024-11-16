package com.sevensky.hibernate_intro.repositories;

import com.sevensky.hibernate_intro.domain.Book;
import com.sevensky.hibernate_intro.domain.Category;
import com.sevensky.hibernate_intro.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}





