package com.sevensky.hibernate_intro;

import com.sevensky.hibernate_intro.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HibernateIntroApplicationTests {

	@Autowired
	BookRepository bookRepository;

	@Test
	void testBookRepository()  {
		long count=bookRepository.count();

		assertThat(count).isGreaterThan(0);
	}

	@Test
	void contextLoads() {
	}

}
