package com.sevensky.hibernate_intro.repositories;

import com.sevensky.hibernate_intro.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
