package com.sevensky.hibernate_intro.repositories;

import com.sevensky.hibernate_intro.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader,Long> {
}
