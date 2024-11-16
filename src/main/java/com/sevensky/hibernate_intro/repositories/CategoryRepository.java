package com.sevensky.hibernate_intro.repositories;

import com.sevensky.hibernate_intro.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
