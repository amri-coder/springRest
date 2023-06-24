package com.wildSchool.springRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildSchool.springRest.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{


	List<Book> findByTitleContainingOrAuthorContainingOrDescriptionContaining(String searchTerm, String searchTerm2,
			String searchTerm3);
}
